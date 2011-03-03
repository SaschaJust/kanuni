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
import net.ownhero.dev.kanuni.annotations.array.ArrayContainsElement;
import net.ownhero.dev.kanuni.annotations.meta.ConditionPattern;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.annotations.string.AlphaNumString;
import net.ownhero.dev.kanuni.annotations.string.AlphaString;
import net.ownhero.dev.kanuni.annotations.string.AsciiString;
import net.ownhero.dev.kanuni.annotations.string.ByteString;
import net.ownhero.dev.kanuni.annotations.string.DigitString;
import net.ownhero.dev.kanuni.annotations.string.DoubleString;
import net.ownhero.dev.kanuni.annotations.string.FloatString;
import net.ownhero.dev.kanuni.annotations.string.HexString;
import net.ownhero.dev.kanuni.annotations.string.IntegerString;
import net.ownhero.dev.kanuni.annotations.string.Length;
import net.ownhero.dev.kanuni.annotations.string.LongString;
import net.ownhero.dev.kanuni.annotations.string.Lowercase;
import net.ownhero.dev.kanuni.annotations.string.Matches;
import net.ownhero.dev.kanuni.annotations.string.MaxLength;
import net.ownhero.dev.kanuni.annotations.string.MinLength;
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
	private static final Map<String, LinkedList<String>> parameterAnnotations   = new HashMap<String, LinkedList<String>>();
	
	/**
	 * holds all known kanuni annotations that target for method declarations
	 */
	private static final Map<String, LinkedList<String>> methodAnnotations      = new HashMap<String, LinkedList<String>>();
	
	/**
	 * holds all known kanuni annotations that target for constructor
	 * declarations
	 */
	private static final Map<String, LinkedList<String>> constructorAnnotations = new HashMap<String, LinkedList<String>>();
	
	/**
	 * the {@link ClassPool} instance to manage classes loaded by javassist. For
	 * a list of packages loaded by the bootstrap classloader see documentation
	 * of {@link KanuniClassloader#loadClass(String)}.
	 */
	private static ClassPool                             classPool              = ClassPool.getDefault();
	
	/**
	 * this flag specifies if we should manipulate the bytecode on load, i.e.
	 * adding assertions to the code. We only add the conditions if assertions
	 * are enabled.
	 */
	private static boolean                               assertionsEnabled      = false;
	
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
		Class<?>[] kanuniAnnotations = { ArrayContainsElement.class,
		/*
		 * * ArrayContainsValue.class, ArrayIndexRange.class,
		 * ArrayIsEmpty.class, ArrayMaxSize.class, ArrayMinSize.class,
		 * ArrayNoneNull.class, ArrayNotEmpty.class, ArraySize.class,
		 */AlphaNumString.class, AlphaString.class, AsciiString.class, ByteString.class, DigitString.class,
		    DoubleString.class, FloatString.class, HexString.class, IntegerString.class, LongString.class,
		    Lowercase.class, Matches.class, MaxLength.class, MinLength.class, SameLength.class, ShortString.class,
		    Trimmed.class, Uppercase.class, Marker.class, Length.class };
		
		// register annotations from above according to their meta annotations
		for (Class<?> kanuniAnnotation : kanuniAnnotations) {
			ConditionPattern pattern = kanuniAnnotation.getAnnotation(ConditionPattern.class);
			Target target = kanuniAnnotation.getAnnotation(Target.class);
			
			if ((target != null) && (pattern != null)) {
				for (ElementType t : target.value()) {
					System.err.println("Registering annotation " + kanuniAnnotation);
					switch (t) {
						case PARAMETER:
							if (!parameterAnnotations.containsKey(kanuniAnnotation.getCanonicalName())) {
								parameterAnnotations.put(kanuniAnnotation.getCanonicalName(), new LinkedList<String>());
							}
							
							parameterAnnotations.get(kanuniAnnotation.getCanonicalName())
							                    .addAll(Arrays.asList(pattern.value()));
							break;
						case METHOD:
							if (!methodAnnotations.containsKey(kanuniAnnotation.getCanonicalName())) {
								methodAnnotations.put(kanuniAnnotation.getCanonicalName(), new LinkedList<String>());
							}
							
							methodAnnotations.get(kanuniAnnotation.getCanonicalName())
							                 .addAll(Arrays.asList(pattern.value()));
							break;
						case CONSTRUCTOR:
							if (!constructorAnnotations.containsKey(kanuniAnnotation.getCanonicalName())) {
								constructorAnnotations.put(kanuniAnnotation.getCanonicalName(),
								                           new LinkedList<String>());
							}
							
							constructorAnnotations.get(kanuniAnnotation.getCanonicalName())
							                      .addAll(Arrays.asList(pattern.value()));
							break;
						default:
					}
				}
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
	 * @param method
	 * @param instrumentation
	 */
	private void addInstrumentation(final CtBehavior method,
	                                final String instrumentation) {
		System.err.println("Adding instrumentation: " + instrumentation);
		this.instrumentations.add(instrumentation);
	}
	
	/**
	 * @param class1
	 * @param linkedList
	 * @return
	 */
	private String buildArray(final Class<String> class1,
	                          final LinkedList<String> linkedList) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("new ").append(class1.getCanonicalName()).append('[');
		builder.append("] {");
		
		StringBuilder innerBuilder = new StringBuilder();
		
		for (String string : linkedList) {
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
		IntegerValueVisitor ivv = new IntegerValueVisitor(markerIndexes);
		
		for (MemberValue meValue : memberValue.getValue()) {
			meValue.accept(ivv);
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
	                                          final Map<Integer, LinkedList<String>> markers,
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
		IntegerValueVisitor ivv = new IntegerValueVisitor(markerIndexes);
		
		memberValue.accept(ivv);
		
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
	                                           final String template) {
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
			instrumentation = instrumentation.replaceAll("\\$" + memberName + "\\$", memberValue.toString());
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
		
		//@formatter:on
		if (name.startsWith("sun.") || name.startsWith("com.sun.") || name.startsWith("org.omg.")
		        || name.startsWith("javax.") || name.startsWith("sunw.") || name.startsWith("java.")
		        || name.startsWith("org.w3c.dom.") || name.startsWith("org.xml.sax.") || name.startsWith("net.jini.")
		        || name.startsWith("org.eclipse.")) {
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
	 * @param cc
	 * @param ctBehavior
	 */
	private void processBehaviorAnnotations(final CtClass cc,
	                                        final CtBehavior ctBehavior) {
		MethodInfo methodInfo = ctBehavior.getMethodInfo();
		
		// first process parameters to find markers
		ParameterAnnotationsAttribute attributes = (ParameterAnnotationsAttribute) methodInfo.getAttribute(ParameterAnnotationsAttribute.visibleTag);
		Map<Integer, LinkedList<String>> markers = new HashMap<Integer, LinkedList<String>>();
		
		if (attributes != null) {
			Annotation[][] annotations = attributes.getAnnotations();
			
			markers = processBehaviorParameterAnnotations(ctBehavior, annotations);
		}
		
		AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
		if (annotationsAttribute != null) {
			Annotation[] annotations = annotationsAttribute.getAnnotations();
			for (Annotation annotation : annotations) {
				if (methodAnnotations.containsKey(annotation.getTypeName())) {
					@SuppressWarnings ("unchecked")
					Set<String> memberNames = annotation.getMemberNames();
					
					if ((memberNames != null) && !memberNames.isEmpty()) {
						// process annotations with parameters
						if (memberNames.contains("marker")) {
							ArrayMemberValue amv = (ArrayMemberValue) getMemberValue(annotation, "marker");
							
							for (int markerIndex : convertMarkerIndexes(amv)) {
								for (String template : methodAnnotations.get(annotation.getTypeName())) {
									addInstrumentation(ctBehavior,
									                   getBehaviorInstrumentation(annotation, template, markers,
									                                              markerIndex));
								}
							}
						}
					} else {
						memberNames = getDeclaredMemberNames(annotation);
						if ((memberNames != null) && !memberNames.isEmpty()) {
							if (memberNames.contains("marker")) {
								ArrayMemberValue amv = (ArrayMemberValue) getMemberValue(annotation, "marker");
								
								for (int markerIndex : convertMarkerIndexes(amv)) {
									for (String template : methodAnnotations.get(annotation.getTypeName())) {
										addInstrumentation(ctBehavior,
										                   getBehaviorInstrumentation(annotation, template, markers,
										                                              markerIndex));
									}
								}
							}
						} else {
							// TODO
							// process annotations without parameters (such as
							// @NoneNull)
						}
					}
				}
			}
		}
	}
	
	/**
	 * @param method
	 * @param annotations
	 * @return
	 */
	private Map<Integer, LinkedList<String>> processBehaviorParameterAnnotations(final CtBehavior method,
	                                                                             final Annotation[][] annotations) {
		HashMap<Integer, LinkedList<String>> map = new HashMap<Integer, LinkedList<String>>();
		
		int i = 1;
		// for each parameter
		for (Annotation[] parAnnotations : annotations) {
			// for each annotation
			for (Annotation annotation : parAnnotations) {
				if (parameterAnnotations.containsKey(annotation.getTypeName())) {
					// method.getMethodInfo().getAttributes().get(i - 1);
					// String parameterName = attributeInfo.getName();
					String parameterName = "$" + (i);
					CtClass parameterType = null;
					
					try {
						parameterType = method.getParameterTypes()[i - 1];
					} catch (NotFoundException e) {
						e.printStackTrace();
					}
					
					// handle special case for markers
					if (annotation.getTypeName().equals(Marker.class.getCanonicalName())) {
						
						int markerIndex = getMarkerIndex(annotation);
						
						if (!map.containsKey(markerIndex)) {
							map.put(markerIndex, new LinkedList<String>());
						}
						
						map.get(markerIndex).add(parameterName);
					} else {
						// handle non meta annotations
						for (String template : parameterAnnotations.get(annotation.getTypeName())) {
							addInstrumentation(method,
							                   getParameterInstrumentation(annotation, parameterName, parameterType,
							                                               template));
						}
					}
				}
			}
			++i;
		}
		return map;
	}
	
	/**
	 * @param cc
	 */
	private void processBehaviors(final CtClass cc) {
		CtMethod[] declaredMethods = cc.getDeclaredMethods();
		CtConstructor[] declaredConstructors = cc.getDeclaredConstructors();
		List<CtBehavior> constructorsAndMethods = new LinkedList<CtBehavior>();
		
		constructorsAndMethods.addAll(Arrays.asList(declaredConstructors));
		constructorsAndMethods.addAll(Arrays.asList(declaredMethods));
		
		/*
		 * TODO This will actually fail in the following scenario: Assume we got
		 * an `ArrayContainsElement` annotation. This annotation depends on a
		 * marker annotation, which most probably will be placed/processed
		 * afterwards. Thus, we have to unwind the operations from
		 * processBehaviorAnnotations(CtClass, CtBehavior) (e.g. by throwing a
		 * CompilationStalledException, which provides the dependency within its
		 * data structure) and stall the parsing of the annotation. Second we
		 * have to check for infinite loops. If we get circular dependencies
		 * (should not be possible by design currently) or missing dependencies
		 * we will have stalled parsings that can't be processed. In this case
		 * we have to terminate the program with a RuntimeError.
		 */
		for (CtBehavior method : constructorsAndMethods) {
			processBehaviorAnnotations(cc, method);
			ListIterator<String> iterator = this.instrumentations.listIterator(this.instrumentations.size());
			while (iterator.hasPrevious()) {
				String instrumentation = iterator.previous();
				try {
					method.insertBefore(instrumentation);
				} catch (CannotCompileException e) {
					e.printStackTrace();
				}
			}
			this.instrumentations.clear();
		}
	}
}
