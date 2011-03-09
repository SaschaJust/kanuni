/**
 * 
 */
package net.ownhero.dev.kanuni.loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import net.ownhero.dev.kanuni.annotations.bounds.RangeChar;
import net.ownhero.dev.kanuni.annotations.bounds.RangeDouble;
import net.ownhero.dev.kanuni.annotations.bounds.RangeFloat;
import net.ownhero.dev.kanuni.annotations.bounds.RangeInteger;
import net.ownhero.dev.kanuni.annotations.bounds.RangeLong;
import net.ownhero.dev.kanuni.annotations.factories.Creator;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
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
			System.err.println("Specification checks enabled.");
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
//				ArrayContainsElement.class,
//				ArrayIndexRange.class,
//				ArrayIsEmpty.class,
//				ArrayMaxSize.class,
//				ArrayMinSize.class,
//				ArrayNoneNull.class,
//				ArrayNotEmpty.class,
//				ArraySize.class,
//				AlphaNumString.class,
//				AlphaString.class,
//				AsciiString.class,
//				ByteString.class,
//				DigitString.class,
//				DoubleString.class,
//				FloatString.class,
//				HexString.class,
//				IntegerString.class,
//				LongString.class,
//				Lowercase.class,
//				Matches.class,
//				MaxLength.class,
//				MinLength.class,
//				SameLength.class,
//				ShortString.class,
//				Trimmed.class,
//				Uppercase.class,
//				Marker.class,
//				Length.class,
				RangeChar.class,
				RangeDouble.class,
				RangeFloat.class,
				RangeInteger.class,
				RangeLong.class
		};
		
		//@formatter:on
		
		// register annotations from above according to their meta annotations
		for (Class<?> kanuniAnnotation : kanuniAnnotations) {
			FactoryClass factoryClass = kanuniAnnotation.getAnnotation(FactoryClass.class);
			Target target = kanuniAnnotation.getAnnotation(Target.class);
			
			try {
				if ((target != null) && (factoryClass != null)) {
					for (ElementType t : target.value()) {
						System.err.println("Registering annotation " + kanuniAnnotation);
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
		System.err.println("Adding instrumentation: " + instrumentation);
		this.instrumentations.add(instrumentation);
	}
	
	/**
	 * @param class1
	 * @param markers
	 * @return
	 */
	private String buildArray(final Class<String> class1,
	                          final SortedSet<String> markers) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("new ").append(class1.getCanonicalName()).append('[');
		builder.append("] {");
		
		StringBuilder innerBuilder = new StringBuilder();
		
		for (String string : markers) {
			if (innerBuilder.length() != 0) {
				innerBuilder.append(", ");
			}
			innerBuilder.append("\\" + string);
		}
		
		builder.append(innerBuilder);
		builder.append("}");
		
		return builder.toString();
	}
	
	/**
	 * @param memberValue
	 * @return
	 */
	private Integer[] convertMarkerIndexes(final ArrayMemberValue memberValue) {
		final LinkedList<Integer> markerIndexes = new LinkedList<Integer>();
		IntegerValueVisitor visitor = new IntegerValueVisitor(markerIndexes);
		
		for (MemberValue meValue : memberValue.getValue()) {
			meValue.accept(visitor);
		}
		
		return markerIndexes.toArray(new Integer[0]);
	}
	
	/**
	 * @param annotation
	 * @param template
	 * @param markers
	 * @param markerIndex
	 * @return
	 */
	private String getBehaviorInstrumentation(final Annotation annotation,
	                                          final String template,
	                                          final Map<Integer, SortedSet<String>> markers,
	                                          final int markerIndex) {
		String instrumentation = String.format("%s", template);
		
		// TODO this should not be String, but the corresponding type of the
		// parameters
		// TODO make sure all parameters in markers list have the same type
		String theArray = buildArray(String.class, markers.get(markerIndex));
		
		instrumentation = instrumentation.replaceAll("\\$" + "String" + ":marker\\$", theArray);
		Pattern pattern = Pattern.compile("\\$([A-Za-z:]+)\\$");
		Matcher matcher = pattern.matcher(instrumentation);
		
		while (matcher.find()) {
			String memberName = matcher.group().substring(1);
			memberName = memberName.substring(0, memberName.length() - 1);
			
			if (!memberName.contains(":")) {
				Object memberValue = getMemberValue(annotation, memberName);
				instrumentation = instrumentation.replaceAll("\\$" + memberName + "\\$", memberValue.toString());
			}
		}
		
		// prepend package
		instrumentation = Condition.class.getPackage().getName() + "." + instrumentation + ";";
		
		// TODO This will fail, e.g. when using @Matches("$foo:bar$") - what
		// actually makes no sense.
		// But assume we would have an @StringEquals("$foo:bar$"), this would
		// break the condition for valid instrumentation.
		StringCondition.notMatches(instrumentation, "\\$[A-Za-z:]+\\$",
		                           "Some of the placeholder in the template were not processed correctly: %s",
		                           instrumentation);
		StringCondition.notEmpty(instrumentation, "The instrumentation string might never be empty.");
		
		// System.err.println("generated instrumentation: " + instrumentation);
		return instrumentation;
	}
	
	/**
	 * @param annotation
	 * @return
	 */
	private Set<String> getDeclaredMemberNames(final Annotation annotation) {
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
	 * @return
	 */
	private int getMarkerIndex(final Annotation annotation) {
		MemberValue memberValue = (MemberValue) getMemberValue(annotation, "value");
		final LinkedList<Integer> markerIndexes = new LinkedList<Integer>();
		IntegerValueVisitor visitor = new IntegerValueVisitor(markerIndexes);
		
		memberValue.accept(visitor);
		
		return markerIndexes.iterator().next();
	}
	
	/**
	 * @param annotation
	 * @param memberName
	 * @return
	 */
	private Object getMemberValue(final Annotation annotation,
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
	 * @param annotation
	 * @param parameterName
	 * @param parameterType
	 * @param template
	 * @return
	 */
	private String getParameterInstrumentation(final Annotation annotation,
	                                           final String parameterName,
	                                           final CtClass parameterType,
	                                           final String template,
	                                           final Map<Integer, SortedSet<String>> markers) {
		Condition.notNull(annotation,
		                  "When instrumention is requested for parameters the corresponding annotation might never be (null).");
		StringCondition.matches(parameterName,
		                        "\\$[0-9]+",
		                        "Parameter names must match the pattern '\\$[0-9]+' since they are replace by javassist on compile. Got: %s",
		                        parameterName);
		Condition.notNull(parameterType,
		                  "Every parameter an instrumentation is requested for has to provide a proper type, but parameterType was set to (null).");
		StringCondition.notEmpty(template,
		                         "The template of the annotation the instrumentation is build for cannot be empty.");
		
		String instrumentation = String.format("%s", template);
		
		// escape the parameterName with '\\' since '$' will cause
		// backreferencing when using replaceAll(String, String)
		instrumentation = instrumentation.replaceAll("\\$pname\\$", "\\" + parameterName);
		instrumentation = instrumentation.replaceAll("\\$ptype\\$", parameterType.getName());
		
		Pattern pattern = Pattern.compile("\\$([A-Za-z:]+)\\$");
		Matcher matcher = pattern.matcher(instrumentation);
		
		while (matcher.find()) {
			String memberName = matcher.group().substring(1);
			memberName = memberName.substring(0, memberName.length() - 1);
			Object memberValue = getMemberValue(annotation, memberName);
			if (memberName.equals("marker")) {
				ArrayMemberValue amv = (ArrayMemberValue) memberValue;
				if (amv.getValue().length > 1) {
					for (MemberValue marker : amv.getValue()) {
						String markerParameterName = " new Object[] { "
						        + markers.get(Integer.parseInt(marker.toString())).first() + " } ";
						instrumentation = instrumentation.replace("$" + memberName + "$", markerParameterName);
					}
				} else {
					// TODO check bounds
					// TODO check list to not be null
					SortedSet<String> markerNames = markers.get(Integer.parseInt(amv.getValue()[0].toString()));
					
					StringBuilder builder = new StringBuilder();
					
					if (markerNames.size() > 1) {
						final String prepend = " new Object[] { ";
						builder.append(prepend);
						for (String markerName : markerNames) {
							if (builder.length() > prepend.length()) {
								builder.append(", ");
							}
							builder.append(markerName);
						}
						builder.append(" } ");
					} else {
						String markerName = markerNames.first();
						markerNames.remove(markerName);
						builder.append(markerName);
					}
					
					instrumentation = instrumentation.replace("$" + memberName + "$", builder.toString());
				}
			} else {
				instrumentation = instrumentation.replace("$" + memberName + "$", memberValue.toString());
			}
			// TODO foreach marker
			// get marker name (i.e. $2, $3)...
			// replace $marker$ with $2, $3... correspondinly
			
		}
		
		// prepend package
		instrumentation = Condition.class.getPackage().getName() + "." + instrumentation + ";";
		
		// TODO This will fail, e.g. when using @Matches("$foo:bar$") - what
		// actually makes no sense.
		// But assume we would have an @StringEquals("$foo:bar$"), this would
		// break the condition for valid instrumentation.
		StringCondition.notMatches(instrumentation, "\\$[A-Za-z:]+\\$",
		                           "Some of the placeholder in the template were not processed correctly: %s",
		                           instrumentation);
		StringCondition.notEmpty(instrumentation, "The instrumentation string might never be empty.");
		
		// System.err.println("generated instrumentation: " + instrumentation);
		return instrumentation;
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
			return getParent().loadClass(name);
		} else {
			try {
				// load class that might be annotated with kanuni annotations
				CtClass ctClass = classPool.get(name);
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
					
					addInstrumentation(behavior, creator.createBehaviorInstrumentation(annotation, behavior, markers));
					
					// TODO remove remaining crap
					
					// @SuppressWarnings ("unchecked")
					// Set<String> memberNames = annotation.getMemberNames();
					//
					// // Add all field names (member names) of the annotation
					// // which aren't set explicitly.
					// memberNames.addAll(getDeclaredMemberNames(annotation));
					//
					// creator.createBehaviorInstrumentation(annotation,
					// behavior, markers);
					//
					// if ((memberNames != null) && !memberNames.isEmpty()) {
					// // process annotations with parameters
					// if (memberNames.contains("marker")) {
					// ArrayMemberValue amv = (ArrayMemberValue)
					// getMemberValue(annotation, "marker");
					//
					// for (int markerIndex : convertMarkerIndexes(amv)) {
					// for (String template :
					// methodAnnotations.get(annotation.getTypeName())) {
					// addInstrumentation(behavior,
					// getBehaviorInstrumentation(annotation, template, markers,
					// markerIndex));
					// }
					// }
					// }
					//
					// }
					
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
						e.printStackTrace();
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
					e.printStackTrace();
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
				if (annotation.getTypeName().equals(Marker.class.getCanonicalName())
				        && parameterAnnotations.containsKey(annotation.getTypeName())) {
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
