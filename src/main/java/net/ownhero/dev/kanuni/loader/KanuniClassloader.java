/**
 * 
 */
package net.ownhero.dev.kanuni.loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationDefaultAttribute;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.MemberValueVisitor;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.annotations.bevahiors.NoneNull;
import net.ownhero.dev.kanuni.annotations.bounds.RangeChar;
import net.ownhero.dev.kanuni.annotations.bounds.RangeDouble;
import net.ownhero.dev.kanuni.annotations.bounds.RangeFloat;
import net.ownhero.dev.kanuni.annotations.bounds.RangeInteger;
import net.ownhero.dev.kanuni.annotations.bounds.RangeLong;
import net.ownhero.dev.kanuni.annotations.compare.Equals;
import net.ownhero.dev.kanuni.annotations.compare.EqualsInt;
import net.ownhero.dev.kanuni.annotations.compare.Greater;
import net.ownhero.dev.kanuni.annotations.compare.GreaterInt;
import net.ownhero.dev.kanuni.annotations.compare.GreaterOrEqual;
import net.ownhero.dev.kanuni.annotations.compare.Less;
import net.ownhero.dev.kanuni.annotations.compare.LessInt;
import net.ownhero.dev.kanuni.annotations.compare.LessOrEqual;
import net.ownhero.dev.kanuni.annotations.compare.NotEquals;
import net.ownhero.dev.kanuni.annotations.compare.NotEqualsInt;
import net.ownhero.dev.kanuni.annotations.factories.Creator;
import net.ownhero.dev.kanuni.annotations.map.ContainsKey;
import net.ownhero.dev.kanuni.annotations.map.ContainsValue;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.annotations.simple.Contains;
import net.ownhero.dev.kanuni.annotations.simple.Empty;
import net.ownhero.dev.kanuni.annotations.simple.MaxSize;
import net.ownhero.dev.kanuni.annotations.simple.MinSize;
import net.ownhero.dev.kanuni.annotations.simple.Negative;
import net.ownhero.dev.kanuni.annotations.simple.NotEmpty;
import net.ownhero.dev.kanuni.annotations.simple.NotNegative;
import net.ownhero.dev.kanuni.annotations.simple.NotNull;
import net.ownhero.dev.kanuni.annotations.simple.NotPositive;
import net.ownhero.dev.kanuni.annotations.simple.Null;
import net.ownhero.dev.kanuni.annotations.simple.Positive;
import net.ownhero.dev.kanuni.annotations.simple.Size;
import net.ownhero.dev.kanuni.annotations.simple.ValidIndex;
import net.ownhero.dev.kanuni.annotations.string.AlphaNumString;
import net.ownhero.dev.kanuni.annotations.string.AlphaString;
import net.ownhero.dev.kanuni.annotations.string.AsciiString;
import net.ownhero.dev.kanuni.annotations.string.ByteString;
import net.ownhero.dev.kanuni.annotations.string.DigitString;
import net.ownhero.dev.kanuni.annotations.string.DoubleString;
import net.ownhero.dev.kanuni.annotations.string.EmptyString;
import net.ownhero.dev.kanuni.annotations.string.FloatString;
import net.ownhero.dev.kanuni.annotations.string.HexString;
import net.ownhero.dev.kanuni.annotations.string.IntegerString;
import net.ownhero.dev.kanuni.annotations.string.Length;
import net.ownhero.dev.kanuni.annotations.string.LongString;
import net.ownhero.dev.kanuni.annotations.string.Lowercase;
import net.ownhero.dev.kanuni.annotations.string.Matches;
import net.ownhero.dev.kanuni.annotations.string.MaxLength;
import net.ownhero.dev.kanuni.annotations.string.MinLength;
import net.ownhero.dev.kanuni.annotations.string.NotEmptyString;
import net.ownhero.dev.kanuni.annotations.string.SameLength;
import net.ownhero.dev.kanuni.annotations.string.ShortString;
import net.ownhero.dev.kanuni.annotations.string.Trimmed;
import net.ownhero.dev.kanuni.annotations.string.Uppercase;
import net.ownhero.dev.kanuni.conditions.Condition;
import net.ownhero.dev.kanuni.conditions.StringCondition;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public final class KanuniClassloader extends ClassLoader {
	
	/**
	 * This internal class is used to extract integer values from
	 * {@link MemberValue}s and {@link ArrayMemberValue}s.
	 */
	class IntegerValueVisitor implements MemberValueVisitor {
		
		private final List<Integer> list;
		
		public IntegerValueVisitor(final List<Integer> list) {
			this.list = list;
		}
		
		public void visitAnnotationMemberValue(final AnnotationMemberValue node) {
		}
		
		public void visitArrayMemberValue(final ArrayMemberValue node) {
			for (MemberValue mv : node.getValue()) {
				mv.accept(this);
			}
		}
		
		public void visitBooleanMemberValue(final BooleanMemberValue node) {
		}
		
		public void visitByteMemberValue(final ByteMemberValue node) {
		}
		
		public void visitCharMemberValue(final CharMemberValue node) {
		}
		
		public void visitClassMemberValue(final ClassMemberValue node) {
		}
		
		public void visitDoubleMemberValue(final DoubleMemberValue node) {
		}
		
		public void visitEnumMemberValue(final EnumMemberValue node) {
		}
		
		public void visitFloatMemberValue(final FloatMemberValue node) {
		}
		
		public void visitIntegerMemberValue(final IntegerMemberValue node) {
			this.list.add(node.getValue());
		}
		
		public void visitLongMemberValue(final LongMemberValue node) {
		}
		
		public void visitShortMemberValue(final ShortMemberValue node) {
		}
		
		public void visitStringMemberValue(final StringMemberValue node) {
		}
		
	}
	
	/**
	 * holds all known kanuni annotations that target for parameters in methods
	 * and constructors
	 */
	private static final Map<String, Creator> parameterAnnotations   = new HashMap<String, Creator>();
	
	/**
	 * holds all known kanuni annotations that target for method declarations
	 */
	private static final Map<String, Creator> methodAnnotations      = new HashMap<String, Creator>();
	
	/**
	 * holds all known kanuni annotations that target for constructor
	 * declarations
	 */
	private static final Map<String, Creator> constructorAnnotations = new HashMap<String, Creator>();
	
	/**
	 * the {@link ClassPool} instance to manage classes loaded by javassist. For
	 * a list of packages loaded by the bootstrap classloader see documentation
	 * of {@link KanuniClassloader#loadClass(String)}.
	 */
	private static ClassPool                  classPool              = ClassPool.getDefault();
	
	/**
	 * this flag specifies if we should manipulate the bytecode on load, i.e.
	 * adding assertions to the code. We only add the conditions if assertions
	 * are enabled.
	 */
	private static boolean                    assertionsEnabled      = false;
	
	private static boolean                    debug                  = System.getProperty("KanuniDebug") != null;
	
	/**
	 * @param memberValue
	 * @return
	 */
	public static Integer[] convertMarkerIndexes(final ArrayMemberValue memberValue) {
		final LinkedList<Integer> markerIndexes = new LinkedList<Integer>();
		IntegerValueVisitor visitor = new KanuniClassloader().new IntegerValueVisitor(markerIndexes);
		
		for (MemberValue meValue : memberValue.getValue()) {
			meValue.accept(visitor);
		}
		
		return markerIndexes.toArray(new Integer[0]);
	}
	
	/**
	 * @param annotation
	 * @return
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
	
	/**
	 * @param annotation
	 * @param memberName
	 * @return
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
	 * @return the assertionsEnabled
	 */
	public static boolean isAssertionsEnabled() {
		return assertionsEnabled;
	}
	
	/**
	 * holds a list of current instrumentation that will be applied to a method
	 * or constructor. This is not thread safe and not required to be since
	 * every thread has it's own classloader instance.
	 */
	private final LinkedList<String> instrumentations = new LinkedList<String>();
	
	static {
		// this is the only proper way to check for assertions to be enabled.
		try {
			assert (false);
		} catch (AssertionError e) {
			assertionsEnabled = true;
			if (debug) {
				System.err.println("Specification checks enabled.");
			}
		}
		
		// in general, this should not be necessary, since
		// ClassPool.getDefault() should already do this. But in some scenarios
		// this might not work properly according to the javassist
		// documentation.
		classPool.appendSystemPath();
		
		// array of kanuni annotations to be supported/processed by the kanuni
		// classloader
		//
		
		//@formatter:off
		
		Class<?>[] kanuniAnnotations = {
				net.ownhero.dev.kanuni.annotations.simple.NoneNull.class,
				NoneNull.class,
				RangeChar.class,
				RangeDouble.class,
				RangeFloat.class,
				RangeInteger.class,
				RangeLong.class,
				Equals.class,
				EqualsInt.class,
				Greater.class,
				GreaterInt.class,
				GreaterOrEqual.class,
				Less.class,
				LessInt.class,
				LessOrEqual.class,
				NotEquals.class,
				NotEqualsInt.class,
				ContainsKey.class,
				ContainsValue.class,
				FactoryClass.class,
				Marker.class,
				Contains.class,
				Empty.class,
				MaxSize.class,
				MinSize.class,
				Negative.class,
				NoneNull.class,
				NotEmpty.class,
				NotNegative.class,
				NotNull.class,
				NotPositive.class,
				Null.class,
				Positive.class,
				Size.class,
				ValidIndex.class,
				AlphaNumString.class,
				AlphaString.class,
				AsciiString.class,
				ByteString.class,
				DigitString.class,
				DoubleString.class,
				EmptyString.class,
				FloatString.class,
				HexString.class,
				IntegerString.class,
				Length.class,
				LongString.class,
				Lowercase.class,
				Matches.class,
				MaxLength.class,
				MinLength.class,
				NotEmptyString.class,
				SameLength.class,
				ShortString.class,
				Trimmed.class,
				Uppercase.class
		};
		
		//@formatter:on
		
		// register annotations from above according to their meta annotations
		for (Class<?> kanuniAnnotation : kanuniAnnotations) {
			FactoryClass factoryClass = kanuniAnnotation.getAnnotation(FactoryClass.class);
			Target target = kanuniAnnotation.getAnnotation(Target.class);
			
			try {
				if ((target != null) && (factoryClass != null)) {
					for (ElementType t : target.value()) {
						if (debug) {
							System.err.println("Registering annotation " + kanuniAnnotation);
						}
						switch (t) {
							case PARAMETER:
								parameterAnnotations.put(kanuniAnnotation.getCanonicalName(),
								                         (Creator) factoryClass.value().newInstance());
								break;
							case METHOD:
								methodAnnotations.put(kanuniAnnotation.getCanonicalName(),
								                      (Creator) factoryClass.value().newInstance());
								break;
							case CONSTRUCTOR:
								constructorAnnotations.put(kanuniAnnotation.getCanonicalName(),
								                           (Creator) factoryClass.value().newInstance());
								break;
							default:
						}
					}
				}
			} catch (InstantiationException e) {
				throw new RuntimeException("Could not instantiate kanuni factory `" + factoryClass.value() + "`.", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Could not instantiate kanuni factory `" + factoryClass.value() + "`.", e);
			}
		}
	}
	
	/**
	 * @param interfaze
	 * @return
	 */
	public static Collection<Class<?>> getInterfaces(final Class<?> interfaze) {
		LinkedList<Class<?>> ifaces = new LinkedList<Class<?>>();
		
		Class<?>[] interfaces = interfaze.getInterfaces();
		
		for (Class<?> clazz : interfaces) {
			ifaces.add(clazz);
			Collection<Class<?>> collection = getInterfaces(clazz);
			
			if (collection != null) {
				ifaces.addAll(collection);
			}
		}
		
		return ifaces;
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
	
	/**
	 * Wrapper to add an instrumentation to the list of the instance.
	 * 
	 * @param behavior
	 * @param instrumentation
	 */
	private void addInstrumentation(final CtBehavior behavior,
	                                final String instrumentation) {
		Condition.notNull(instrumentation, "Valid instrumentations may never be null, but got (null) for %s.",
		                  behavior.getName());
		StringCondition.notEmpty(instrumentation, "Valid instrumentations may never be empty for %s.",
		                         behavior.getName());
		
		if (debug) {
			System.err.println("Adding instrumentation: " + instrumentation.trim());
		}
		
		this.instrumentations.add(instrumentation);
	}
	
	/**
	 * @param annotation
	 * @return
	 */
	private int getMarkerIndex(final Annotation annotation) {
		MemberValue memberValue = (MemberValue) getMemberValue(annotation, "value");
		final LinkedList<Integer> markerIndexes = new LinkedList<Integer>();
		IntegerValueVisitor visitor = new IntegerValueVisitor(markerIndexes);
		
		memberValue.accept(visitor);
		
		return markerIndexes.iterator().next();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
	@Override
	public Class<?> loadClass(final String name) throws ClassNotFoundException {
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
				|| name.startsWith("org.eclipse.")) {
			//@formatter:on
			if (debug) {
				System.err.println("Delegating loading for " + name);
			}
			return getParent().loadClass(name);
		} else {
			try {
				// load class that might be annotated with kanuni annotations
				if (debug) {
					System.err.println("Intervene loading for " + name);
				}
				CtClass ctClass = classPool.get(name);
				
				int i = name.lastIndexOf('.');
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
				
				// only instrument if assertions are enabled
				return assertionsEnabled
				? processAnnotations(ctClass).toClass()
				: ctClass.toClass();
			} catch (NotFoundException e) {
				throw new ClassNotFoundException(e.getMessage(), e);
			} catch (CannotCompileException e) {
				// in case of an compile error, try the parent bootloader
				return getParent().loadClass(name);
			}
		}
	}
	
	/**
	 * @param cc
	 * @return
	 */
	private CtClass processAnnotations(final CtClass cc) {
		// process methods
		processBehaviors(cc);
		
		return cc;
	}
	
	/**
	 * @param loadedClass
	 * @param behavior
	 */
	private void processBehaviorAnnotations(final CtClass loadedClass,
	                                        final CtBehavior behavior) {
		MethodInfo methodInfo = behavior.getMethodInfo();
		Map<Integer, SortedSet<String>> markers = new HashMap<Integer, SortedSet<String>>();
		
		// first process parameters to find markers
		ParameterAnnotationsAttribute attributes = (ParameterAnnotationsAttribute) methodInfo.getAttribute(ParameterAnnotationsAttribute.visibleTag);
		
		if (attributes != null) {
			Annotation[][] annotations = attributes.getAnnotations();
			
			// find all markers
			markers = processMarkers(behavior, annotations);
			
			// process parameter annotations
			processBehaviorParameterAnnotations(behavior, annotations, markers);
		}
		
		// determine annotationsAttribute
		AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
		
		/*
		 * If the annotationsAttribute is not null, the class under subject is
		 * annotated. Now get all visible annotations and check if we control
		 * this annotations.
		 */
		if (annotationsAttribute != null) {
			Annotation[] annotations = annotationsAttribute.getAnnotations();
			
			// for each visible annotation on the behavior
			for (Annotation annotation : annotations) {
				// we are responsible for this annotation
				if (methodAnnotations.containsKey(annotation.getTypeName())
						|| constructorAnnotations.containsKey(annotation.getTypeName())) {
					Creator creator = methodAnnotations.get(annotation.getTypeName());
					
					if (creator == null) {
						creator = constructorAnnotations.get(annotation.getTypeName());
					}
					
					if (debug) {
						System.err.println("Requesting instrumentation for " + behavior.getName() + " of type "
						                   + annotation.getTypeName());
					}
					
					addInstrumentation(behavior, creator.createBehaviorInstrumentation(annotation, behavior, markers));
				}
			}
		}
	}
	
	/**
	 * @param behavior
	 * @param annotations
	 * @param markers
	 * @return
	 */
	private Map<Integer, SortedSet<String>> processBehaviorParameterAnnotations(final CtBehavior behavior,
	                                                                            final Annotation[][] annotations,
	                                                                            final Map<Integer, SortedSet<String>> markers) {
		int i = 1;
		// for each parameter
		for (Annotation[] parAnnotations : annotations) {
			// for each annotation
			for (Annotation annotation : parAnnotations) {
				if (!annotation.getTypeName().equals(Marker.class.getCanonicalName())
						&& parameterAnnotations.containsKey(annotation.getTypeName())) {
					// method.getMethodInfo().getAttributes().get(i - 1);
					// String parameterName = attributeInfo.getName();
					
					Creator creator = parameterAnnotations.get(annotation.getTypeName());
					
					String parameterName = "$" + (i);
					CtClass parameterType = null;
					
					try {
						parameterType = behavior.getParameterTypes()[i - 1];
					} catch (NotFoundException e) {
						if (debug) {
							e.printStackTrace();
						}
					}
					
					if (debug) {
						System.err.println("Requesting instrumentation for " + behavior.getName() + " of type "
						                   + annotation.getTypeName() + " on parameter " + parameterName + " with type "
						                   + parameterType.getName());
					}
					
					addInstrumentation(behavior, creator.createParameterInstrumentation(annotation, behavior,
					                                                                    parameterName, parameterType,
					                                                                    markers));
				}
			}
			++i;
		}
		return markers;
	}
	
	/**
	 * @param loadedClass
	 */
	private void processBehaviors(final CtClass loadedClass) {
		CtMethod[] declaredMethods = loadedClass.getDeclaredMethods();
		CtConstructor[] declaredConstructors = loadedClass.getDeclaredConstructors();
		
		// list to hold all behaviors (methods and constructors of the loaded
		// class)
		List<CtBehavior> constructorsAndMethods = new LinkedList<CtBehavior>();
		
		// merge methods and constructors into a list of behaviors
		constructorsAndMethods.addAll(Arrays.asList(declaredConstructors));
		constructorsAndMethods.addAll(Arrays.asList(declaredMethods));
		
		for (CtBehavior behavior : constructorsAndMethods) {
			// process all annotations for the corresponding behavior
			// (constructor/method)
			processBehaviorAnnotations(loadedClass, behavior);
			
			ListIterator<String> iterator = this.instrumentations.listIterator(this.instrumentations.size());
			
			// insert at beginning of the behavior in reverse order
			while (iterator.hasPrevious()) {
				String instrumentation = iterator.previous();
				
				try {
					behavior.insertBefore(instrumentation);
				} catch (CannotCompileException e) {
					if (debug) {
						e.printStackTrace();
					}
				}
			}
			
			this.instrumentations.clear();
		}
	}
	
	/**
	 * @param behavior
	 * @param annotations
	 * @return
	 */
	private Map<Integer, SortedSet<String>> processMarkers(final CtBehavior behavior,
	                                                       final Annotation[][] annotations) {
		HashMap<Integer, SortedSet<String>> map = new HashMap<Integer, SortedSet<String>>();
		
		int i = 1;
		// for each parameter
		for (Annotation[] parAnnotations : annotations) {
			// for each annotation
			for (Annotation annotation : parAnnotations) {
				if (annotation.getTypeName().equals(Marker.class.getCanonicalName())) {
					String parameterName = "$" + (i);
					
					int markerIndex = getMarkerIndex(annotation);
					
					if (!map.containsKey(markerIndex)) {
						map.put(markerIndex, new TreeSet<String>());
					}
					
					map.get(markerIndex).add(parameterName);
				}
			}
			++i;
		}
		return map;
	}
}
