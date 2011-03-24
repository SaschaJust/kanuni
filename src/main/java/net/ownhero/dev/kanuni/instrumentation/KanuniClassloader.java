/**
 * 
 */
package net.ownhero.dev.kanuni.instrumentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationDefaultAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class KanuniClassloader extends ClassLoader {
	
	/**
	 * the {@link ClassPool} instance to manage classes loaded by javassist. For
	 * a list of packages loaded by the bootstrap classloader see documentation
	 * of {@link KanuniClassloader#loadClass(String)}.
	 */
	private static ClassPool             classPool = ClassPool.getDefault();
	
	private static Map<String, Class<?>> cache     = new HashMap<String, Class<?>>();
	
	/**
	 * @param annotation
	 * @return returns a set with all fields of the annotation
	 */
	public static Set<String> getDeclaredMemberNames(final Annotation annotation) {
		try {
			CtClass ctClass = classPool.get(annotation.getTypeName());
			HashSet<String> retSet = new HashSet<String>();
			
			for (CtBehavior ctBehavior : ctClass.getDeclaredMethods()) {
				retSet.add(ctBehavior.getName());
			}
			
			return retSet;
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new HashSet<String>();
		}
	}
	
	private final KanuniInstrumenter instrumenter = new KanuniInstrumenter();
	
	static {
		// in general, this should not be necessary, since
		// ClassPool.getDefault() should already do this. But in some scenarios
		// this might not work properly according to the javassist
		// documentation.
		classPool.appendSystemPath();
		
	}
	
	/**
	 * @param annotation
	 * @param memberName
	 * @return the actual value of a member of the annotation
	 */
	public static Object getMemberValue(final Annotation annotation,
	                                    final String memberName) {
		Object memberValue = annotation.getMemberValue(memberName);
		
		if (memberValue == null) {
			CtClass ctClass;
			
			try {
				ctClass = classPool.get(annotation.getTypeName());
				
				MethodInfo info = ctClass.getDeclaredMethod(memberName).getMethodInfo();
				AnnotationDefaultAttribute ada = (AnnotationDefaultAttribute) info.getAttribute(AnnotationDefaultAttribute.tag);
				memberValue = ada.getDefaultValue();
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return memberValue;
	}
	
	/**
	 * This constructor will never be called by the VM itself. If you call the
	 * constructor manually make sure that {@link KanuniClassloader} isn't the
	 * system's class loader. Otherwise this will cause a stack overflow.
	 * 
	 * The only reason that this constructor exists is for testing purpose. You
	 * can load single annotated classes and test the annotations under suspect.
	 */
	public KanuniClassloader() {
		KanuniClassloader.classPool.insertClassPath(new ClassClassPath(this.getClass()));
		KanuniClassloader.classPool.insertClassPath(new LoaderClassPath(getSystemClassLoader()));
	}
	
	/**
	 * The constructor called when bootstrapping the VM and having the system
	 * bootloader set to {@link KanuniClassloader}.
	 * 
	 * @param arg0
	 *            the parent class loader
	 */
	public KanuniClassloader(final ClassLoader arg0) {
		super(arg0);
		KanuniClassloader.classPool.insertClassPath(new ClassClassPath(this.getClass()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
	@Override
	public Class<?> loadClass(final String name) throws ClassNotFoundException {
		int i = name.lastIndexOf('.');
		//@formatter:off
		
		/* skip classes that may not be stubbed
		 * (taken from the JUnit exclude list)
		 * 
		 * sun.*
		 * com.sun.*
		 * org.omg.*
		 * javax.*
		 * sunw.*
		 * java.*
		 * org.w3c.dom.*
		 * org.xml.sax.*
		 * net.jini.*
		 */
		if (name.startsWith("sun.")
				|| name.startsWith("com.sun.")
				|| name.startsWith("org.omg.")
				|| name.startsWith("javax.")
				|| name.startsWith("sunw.")
				|| name.startsWith("java.")
				|| name.startsWith("org.w3c.dom.")
				|| name.startsWith("org.xml.sax.")
				|| name.startsWith("net.jini.")
				|| name.startsWith("org.eclipse.")
				|| name.startsWith("org.ccil.")
				|| (i < 0)) {
			//@formatter:on
			if (KanuniInstrumenter.debug) {
				System.err.println("Delegating loading for " + name);
			}
			return getParent().loadClass(name);
		} else {
			try {
				if (cache.containsKey(name)) {
					return cache.get(name);
				}
				
				// load class that might be annotated with kanuni annotations
				if (KanuniInstrumenter.debug) {
					System.err.println("Intervene loading for " + name);
				}
				
				CtClass ctClass = classPool.get(name);
				
				if (i != -1) {
					String pname = name.substring(0, i);
					if (getPackage(pname) == null) {
						try {
							definePackage(pname, null, null, null, null, null, null, null);
						} catch (IllegalArgumentException e) {
							// ignore. maybe the package object for the same
							// name has been created just right away.
						}
					}
				}
				
				Class<?> c = null;
				// only instrument if assertions are enabled
				if (KanuniInstrumenter.isAssertionsEnabled()) {
					ctClass = this.instrumenter.processAnnotations(ctClass);
				}
				
				try {
					c = ctClass.toClass();
				} catch (NullPointerException e) {
					System.err.println(">$>$>$>$>$");
					System.err.println("$$$$$$$$$$ " + ctClass.getName());
				}
				
				cache.put(name, c);
				return c;
			} catch (NotFoundException e) {
				throw new ClassNotFoundException(e.getMessage(), e);
			} catch (CannotCompileException e) {
				// in case of an compile error, try the parent bootloader
				return getParent().loadClass(name);
			}
		}
	}
	
}
