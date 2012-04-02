/**
 * 
 */
package net.ownhero.dev.kanuni.instrumentation;

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
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
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
import net.ownhero.dev.kanuni.annotations.compare.GreaterOrEqualInt;
import net.ownhero.dev.kanuni.annotations.compare.Less;
import net.ownhero.dev.kanuni.annotations.compare.LessInt;
import net.ownhero.dev.kanuni.annotations.compare.LessOrEqual;
import net.ownhero.dev.kanuni.annotations.compare.LessOrEqualInt;
import net.ownhero.dev.kanuni.annotations.compare.NotEquals;
import net.ownhero.dev.kanuni.annotations.compare.NotEqualsInt;
import net.ownhero.dev.kanuni.annotations.factories.Creator;
import net.ownhero.dev.kanuni.annotations.file.Directory;
import net.ownhero.dev.kanuni.annotations.file.Executable;
import net.ownhero.dev.kanuni.annotations.file.ExecutableFile;
import net.ownhero.dev.kanuni.annotations.file.Exists;
import net.ownhero.dev.kanuni.annotations.file.File;
import net.ownhero.dev.kanuni.annotations.file.Hidden;
import net.ownhero.dev.kanuni.annotations.file.Readable;
import net.ownhero.dev.kanuni.annotations.file.ReadableDirectory;
import net.ownhero.dev.kanuni.annotations.file.ReadableWritableDirectory;
import net.ownhero.dev.kanuni.annotations.file.ReadableWritableFile;
import net.ownhero.dev.kanuni.annotations.file.Writable;
import net.ownhero.dev.kanuni.annotations.file.WritableDirectory;
import net.ownhero.dev.kanuni.annotations.file.WritableFile;
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
import net.ownhero.dev.kanuni.annotations.specifics.Container;
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
import net.ownhero.dev.kanuni.conditions.ArrayCondition;
import net.ownhero.dev.kanuni.conditions.BoundsCondition;
import net.ownhero.dev.kanuni.conditions.CollectionCondition;
import net.ownhero.dev.kanuni.conditions.CompareCondition;
import net.ownhero.dev.kanuni.conditions.Condition;
import net.ownhero.dev.kanuni.conditions.FileCondition;
import net.ownhero.dev.kanuni.conditions.MapCondition;
import net.ownhero.dev.kanuni.conditions.StringCondition;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;

import com.sun.tools.javac.comp.Check;

/**
 * The Class KanuniInstrumenter.
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class KanuniInstrumenter {
	
	/**
	 * This internal class is used to extract integer values from {@link MemberValue}s and {@link ArrayMemberValue}s.
	 */
	class IntegerValueVisitor implements MemberValueVisitor {
		
		/** The list. */
		private final List<Integer> list;
		
		/**
		 * Instantiates a new integer value visitor.
		 * 
		 * @param list
		 *            the list
		 */
		public IntegerValueVisitor(final List<Integer> list) {
			this.list = list;
		}
		
		/*
		 * (non-Javadoc)
		 * @see
		 * javassist.bytecode.annotation.MemberValueVisitor#visitAnnotationMemberValue(javassist.bytecode.annotation
		 * .AnnotationMemberValue)
		 */
		@Override
		public void visitAnnotationMemberValue(final AnnotationMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitArrayMemberValue(javassist.bytecode.annotation.
		 * ArrayMemberValue)
		 */
		@Override
		public void visitArrayMemberValue(final ArrayMemberValue node) {
			for (final MemberValue mv : node.getValue()) {
				mv.accept(this);
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitBooleanMemberValue(javassist.bytecode.annotation.
		 * BooleanMemberValue)
		 */
		@Override
		public void visitBooleanMemberValue(final BooleanMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitByteMemberValue(javassist.bytecode.annotation.
		 * ByteMemberValue)
		 */
		@Override
		public void visitByteMemberValue(final ByteMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitCharMemberValue(javassist.bytecode.annotation.
		 * CharMemberValue)
		 */
		@Override
		public void visitCharMemberValue(final CharMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitClassMemberValue(javassist.bytecode.annotation.
		 * ClassMemberValue)
		 */
		@Override
		public void visitClassMemberValue(final ClassMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitDoubleMemberValue(javassist.bytecode.annotation.
		 * DoubleMemberValue)
		 */
		@Override
		public void visitDoubleMemberValue(final DoubleMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitEnumMemberValue(javassist.bytecode.annotation.
		 * EnumMemberValue)
		 */
		@Override
		public void visitEnumMemberValue(final EnumMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitFloatMemberValue(javassist.bytecode.annotation.
		 * FloatMemberValue)
		 */
		@Override
		public void visitFloatMemberValue(final FloatMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitIntegerMemberValue(javassist.bytecode.annotation.
		 * IntegerMemberValue)
		 */
		@Override
		public void visitIntegerMemberValue(final IntegerMemberValue node) {
			this.list.add(node.getValue());
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitLongMemberValue(javassist.bytecode.annotation.
		 * LongMemberValue)
		 */
		@Override
		public void visitLongMemberValue(final LongMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitShortMemberValue(javassist.bytecode.annotation.
		 * ShortMemberValue)
		 */
		@Override
		public void visitShortMemberValue(final ShortMemberValue node) {
			// unused
		}
		
		/*
		 * (non-Javadoc)
		 * @see javassist.bytecode.annotation.MemberValueVisitor#visitStringMemberValue(javassist.bytecode.annotation.
		 * StringMemberValue)
		 */
		@Override
		public void visitStringMemberValue(final StringMemberValue node) {
			// unused
		}
		
	}
	
	/** holds all known kanuni annotations that target for parameters in methods and constructors. */
	private static final Map<String, Creator> parameterAnnotations   = new HashMap<String, Creator>();
	
	/** holds all known kanuni annotations that target for method declarations. */
	private static final Map<String, Creator> methodAnnotations      = new HashMap<String, Creator>();
	
	/** holds all known kanuni annotations that target for constructor declarations. */
	private static final Map<String, Creator> constructorAnnotations = new HashMap<String, Creator>();
	
	/**
	 * the {@link ClassPool} instance to manage classes loaded by javassist. For a list of packages loaded by the
	 * bootstrap classloader see documentation of {@link KanuniClassloader#loadClass(String)}.
	 */
	private static ClassPool                  classPool              = ClassPool.getDefault();
	
	/**
	 * this flag specifies if we should manipulate the bytecode on load, i.e. adding assertions to the code. We only add
	 * the conditions if assertions are enabled.
	 */
	private static boolean                    assertionsEnabled      = false;
	
	public static final String                fileClass              = FileCondition.class.getCanonicalName();
	
	/**
	 * Forces the instrumentations to insert {@link Check}s instead of {@link Condition}s.
	 */
	private static final boolean              useExceptions          = System.getProperty("KanuniExceptions") != null;
	
	/** The Constant exceptionsEnabled. */
	private static final boolean              exceptionsEnabled      = System.getProperty("KanuniDisableExceptions") == null;
	
	/** kanuni debugging enabled. */
	public static final boolean               debug                  = System.getProperty("KanuniDebug") != null;
	
	/** The Constant simpleClass. */
	public static final String                simpleClass            = Condition.class.getCanonicalName();
	
	/** The Constant arrayClass. */
	public static final String                arrayClass             = ArrayCondition.class.getCanonicalName();
	
	/** The Constant stringClass. */
	public static final String                stringClass            = StringCondition.class.getCanonicalName();
	
	/** The Constant mapClass. */
	public static final String                mapClass               = MapCondition.class.getCanonicalName();
	
	/** The Constant collectionClass. */
	public static final String                collectionClass        = CollectionCondition.class.getCanonicalName();
	
	/** The Constant compareClass. */
	public static final String                compareClass           = CompareCondition.class.getCanonicalName();
	
	/** The Constant boundsClass. */
	public static final String                boundsClass            = BoundsCondition.class.getCanonicalName();
	
	/**
	 * Assertions enabled.
	 * 
	 * @return the assertionsEnabled
	 */
	public static boolean assertionsEnabled() {
		return assertionsEnabled;
	}
	
	/**
	 * Convert marker indexes.
	 * 
	 * @param memberValue
	 *            the member value
	 * @return an array containing all marker indexes to a memberValue
	 */
	public static Integer[] convertMarkerIndexes(final ArrayMemberValue memberValue) {
		final LinkedList<Integer> markerIndexes = new LinkedList<Integer>();
		final IntegerValueVisitor visitor = new KanuniInstrumenter().new IntegerValueVisitor(markerIndexes);
		
		for (final MemberValue meValue : memberValue.getValue()) {
			meValue.accept(visitor);
		}
		
		return markerIndexes.toArray(new Integer[0]);
	}
	
	/**
	 * Exceptions enabled.
	 * 
	 * @return the exceptionsenabled
	 */
	public static boolean exceptionsEnabled() {
		return exceptionsEnabled;
	}
	
	/**
	 * Gets the declared member names.
	 * 
	 * @param annotation
	 *            the annotation
	 * @return returns a set with all fields of the annotation
	 */
	public static Set<String> getDeclaredMemberNames(final Annotation annotation) {
		try {
			final CtClass ctClass = classPool.get(annotation.getTypeName());
			final HashSet<String> retSet = new HashSet<String>();
			
			for (final CtBehavior ctBehavior : ctClass.getDeclaredMethods()) {
				retSet.add(ctBehavior.getName());
			}
			
			return retSet;
		} catch (final NotFoundException e) {
			e.printStackTrace();
			return new HashSet<String>();
		}
	}
	
	/**
	 * Gets the member value.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param memberName
	 *            the member name
	 * @return the actual value of a member of the annotation
	 */
	public static Object getMemberValue(final Annotation annotation,
	                                    final String memberName) {
		Object memberValue = annotation.getMemberValue(memberName);
		
		if (memberValue == null) {
			CtClass ctClass;
			
			try {
				ctClass = classPool.get(annotation.getTypeName());
				
				final MethodInfo info = ctClass.getDeclaredMethod(memberName).getMethodInfo();
				final AnnotationDefaultAttribute ada = (AnnotationDefaultAttribute) info.getAttribute(AnnotationDefaultAttribute.tag);
				memberValue = ada.getDefaultValue();
			} catch (final NotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return memberValue;
	}
	
	/**
	 * Use exceptions.
	 * 
	 * @return the useExceptions
	 */
	public static boolean useExceptions() {
		return useExceptions;
	}
	
	/**
	 * holds a list of current instrumentation that will be applied to a method or constructor. This is not thread safe
	 * and not required to be since every thread has it's own classloader instance.
	 */
	private final LinkedList<String> instrumentations = new LinkedList<String>();
	
	static {
		// this is the only proper way to check for assertions to be enabled.
		try {
			assert (false);
		} catch (final AssertionError e) {
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
//		
//		final Set<Class<?>> kanuniAnnotations  = new HashSet<Class<?>>();
//		try {
//	        final Collection<Class<?>> allClasses = ClassFinder.getAllClasses(Stub.class.getPackage(), 0);
//	        
//	        if (debug) {
//	            System.err.println(String.format("Checking %s potential annotations to register.", allClasses.size()));
//            }
//	        for (final Class<?> c : allClasses) {
//	        	if (c.isAnnotation()) {
//	        		if (c.getAnnotation(FactoryClass.class) != null) {
//	        			kanuniAnnotations.add(c);
//	        		}
//	        	}
//	        }
//        } catch (final ClassNotFoundException ignore) { //ignore
//        } catch (final IOException ignore) { //ignore
//        }
		
		final Class<?>[] kanuniAnnotations = {
				net.ownhero.dev.kanuni.annotations.simple.NoneNull.class,
				NoneNull.class,
				RangeChar.class,
				RangeDouble.class,
				RangeFloat.class,
				RangeInteger.class,
				RangeLong.class,
				Container.class,
				Equals.class,
				EqualsInt.class,
				Greater.class,
				GreaterInt.class,
				GreaterOrEqual.class,
				GreaterOrEqualInt.class,
				Less.class,
				LessInt.class,
				LessOrEqual.class,
				LessOrEqualInt.class,
				NotEquals.class,
				NotEqualsInt.class,
				ContainsKey.class,
				ContainsValue.class,
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
				Uppercase.class,
				Directory.class,
				Executable.class,
				ExecutableFile.class,
				Exists.class,
				File.class,
				Hidden.class,
				net.ownhero.dev.kanuni.annotations.file.MaxSize.class,
				net.ownhero.dev.kanuni.annotations.file.MinSize.class,
				Readable.class,
				ReadableDirectory.class,
				ReadableWritableDirectory.class,
				ReadableWritableFile.class,
				Writable.class,
				WritableDirectory.class,
				WritableFile.class
		};
		
		//@formatter:on
		
		// register annotations from above according to their meta annotations
		for (final Class<?> kanuniAnnotation : kanuniAnnotations) {
			final FactoryClass factoryClass = kanuniAnnotation.getAnnotation(FactoryClass.class);
			final Target target = kanuniAnnotation.getAnnotation(Target.class);
			
			try {
				if ((target != null) && (factoryClass != null)) {
					for (final ElementType t : target.value()) {
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
			} catch (final InstantiationException e) {
				throw new RuntimeException("Could not instantiate kanuni factory `" + factoryClass.value() + "`.", e);
			} catch (final IllegalAccessException e) {
				throw new RuntimeException("Could not instantiate kanuni factory `" + factoryClass.value() + "`.", e);
			}
		}
	}
	
	/**
	 * Gets the interfaces.
	 * 
	 * @param interfaze
	 *            the interfaze
	 * @return a collection containing all interfaces of the given class
	 */
	public static Collection<Class<?>> getInterfaces(final Class<?> interfaze) {
		final LinkedList<Class<?>> ifaces = new LinkedList<Class<?>>();
		
		final Class<?>[] interfaces = interfaze.getInterfaces();
		
		for (final Class<?> clazz : interfaces) {
			ifaces.add(clazz);
			final Collection<Class<?>> collection = getInterfaces(clazz);
			
			if (collection != null) {
				ifaces.addAll(collection);
			}
		}
		
		return ifaces;
	}
	
	/**
	 * Wrapper to add an instrumentation to the list of the instance.
	 * 
	 * @param behavior
	 *            the behavior
	 * @param instrumentation
	 *            the instrumentation
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
	 * Gets the marker index.
	 * 
	 * @param annotation
	 *            the annotation
	 * @return the index of the marker using an IntegerValueVisitor
	 */
	private int getMarkerIndex(final Annotation annotation) {
		final MemberValue memberValue = (MemberValue) getMemberValue(annotation, "value");
		final LinkedList<Integer> markerIndexes = new LinkedList<Integer>();
		final IntegerValueVisitor visitor = new IntegerValueVisitor(markerIndexes);
		
		memberValue.accept(visitor);
		
		return markerIndexes.iterator().next();
	}
	
	/**
	 * Process annotations.
	 * 
	 * @param cc
	 *            the cc
	 * @return a CtClass instance after being instrumented
	 */
	public CtClass processAnnotations(final CtClass cc) {
		if (debug) {
			System.err.println("Parsing " + cc.getName());
		}
		// process methods
		processBehaviors(cc);
		
		return cc;
	}
	
	/**
	 * Process behavior annotations.
	 * 
	 * @param loadedClass
	 *            the loaded class
	 * @param behavior
	 *            the behavior
	 */
	private void processBehaviorAnnotations(final CtClass loadedClass,
	                                        final CtBehavior behavior) {
		final MethodInfo methodInfo = behavior.getMethodInfo();
		Map<Integer, SortedSet<String>> markers = new HashMap<Integer, SortedSet<String>>();
		
		// first process parameters to find markers
		final ParameterAnnotationsAttribute attributes = (ParameterAnnotationsAttribute) methodInfo.getAttribute(ParameterAnnotationsAttribute.visibleTag);
		
		if (attributes != null) {
			final Annotation[][] annotations = attributes.getAnnotations();
			
			// find all markers
			markers = processMarkers(behavior, annotations);
			
			// process parameter annotations
			processBehaviorParameterAnnotations(behavior, annotations, markers);
		}
		
		// determine annotationsAttribute
		final AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
		
		/*
		 * If the annotationsAttribute is not null, the class under subject is annotated. Now get all visible
		 * annotations and check if we control this annotations.
		 */
		if (annotationsAttribute != null) {
			final Annotation[] annotations = annotationsAttribute.getAnnotations();
			
			// for each visible annotation on the behavior
			for (final Annotation annotation : annotations) {
				// we are responsible for this annotation
				if (methodAnnotations.containsKey(annotation.getTypeName())
				        || constructorAnnotations.containsKey(annotation.getTypeName())) {
					if (debug) {
						System.err.println("Processing annotation " + annotation.getTypeName());
					}
					
					Creator creator = methodAnnotations.get(annotation.getTypeName());
					
					if (creator == null) {
						creator = constructorAnnotations.get(annotation.getTypeName());
					}
					
					if (debug) {
						System.err.println("Requesting instrumentation for " + behavior.getName() + " of type "
						        + annotation.getTypeName());
					}
					
					try {
						addInstrumentation(behavior,
						                   creator.createBehaviorInstrumentation(annotation, behavior, markers));
					} catch (final MalformedAnnotationException e) {
						System.err.println("Error processing annotation on " + behavior.getLongName() + ", annotation "
						        + annotation.getTypeName() + ": " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Process behavior parameter annotations.
	 * 
	 * @param behavior
	 *            the behavior
	 * @param annotations
	 *            the annotations
	 * @param markers
	 *            the markers
	 * @return a map from integer (marker index) to the corresponding argument aliases
	 */
	private Map<Integer, SortedSet<String>> processBehaviorParameterAnnotations(final CtBehavior behavior,
	                                                                            final Annotation[][] annotations,
	                                                                            final Map<Integer, SortedSet<String>> markers) {
		int i = 1;
		// for each parameter
		for (final Annotation[] parAnnotations : annotations) {
			// for each annotation
			for (final Annotation annotation : parAnnotations) {
				if (!annotation.getTypeName().equals(Marker.class.getCanonicalName())
				        && parameterAnnotations.containsKey(annotation.getTypeName())) {
					if (debug) {
						System.err.println("Processing annotation " + annotation.getTypeName());
					}
					
					final Creator creator = parameterAnnotations.get(annotation.getTypeName());
					
					final String parameterName = "$" + (i);
					CtClass parameterType = null;
					
					try {
						parameterType = behavior.getParameterTypes()[i - 1];
					} catch (final NotFoundException e) {
						if (debug) {
							e.printStackTrace();
						}
					}
					
					if (debug) {
						System.err.println("Requesting instrumentation for " + behavior.getName() + " of type "
						        + annotation.getTypeName() + " on parameter " + parameterName + " with type "
						        + (parameterType != null
						                                ? parameterType.getName()
						                                : ""));
					}
					
					try {
						addInstrumentation(behavior, creator.createParameterInstrumentation(annotation, behavior,
						                                                                    parameterName,
						                                                                    parameterType, markers));
					} catch (final MalformedAnnotationException e) {
						System.err.println("Error processing annotation on " + behavior.getLongName() + ", annotation "
						        + annotation.getTypeName() + ", argument number " + i + ": " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			++i;
		}
		return markers;
	}
	
	/**
	 * Process behaviors.
	 * 
	 * @param loadedClass
	 *            the loaded class
	 */
	private void processBehaviors(final CtClass loadedClass) {
		if (debug) {
			System.err.println("Processing annotations for " + loadedClass.getName());
		}
		
		final CtMethod[] declaredMethods = loadedClass.getDeclaredMethods();
		final CtConstructor[] declaredConstructors = loadedClass.getDeclaredConstructors();
		
		// list to hold all behaviors (methods and constructors of the loaded
		// class)
		final List<CtBehavior> constructorsAndMethods = new LinkedList<CtBehavior>();
		
		// merge methods and constructors into a list of behaviors
		constructorsAndMethods.addAll(Arrays.asList(declaredConstructors));
		constructorsAndMethods.addAll(Arrays.asList(declaredMethods));
		
		for (final CtBehavior behavior : constructorsAndMethods) {
			// process all annotations for the corresponding behavior
			// (constructor/method)
			if (debug) {
				System.err.println("Processing annotations on " + behavior.getLongName());
			}
			
			processBehaviorAnnotations(loadedClass, behavior);
			
			final ListIterator<String> iterator = this.instrumentations.listIterator(this.instrumentations.size());
			
			// insert at beginning of the behavior in reverse order
			while (iterator.hasPrevious()) {
				final String instrumentation = iterator.previous();
				
				try {
					behavior.insertBefore(instrumentation);
				} catch (final CannotCompileException e) {
					if (debug) {
						e.printStackTrace();
					}
				}
			}
			
			this.instrumentations.clear();
		}
	}
	
	/**
	 * Process markers.
	 * 
	 * @param behavior
	 *            the behavior
	 * @param annotations
	 *            the annotations
	 * @return a map from integer (marker index) to the corresponding argument aliases
	 */
	private Map<Integer, SortedSet<String>> processMarkers(final CtBehavior behavior,
	                                                       final Annotation[][] annotations) {
		final HashMap<Integer, SortedSet<String>> map = new HashMap<Integer, SortedSet<String>>();
		
		int i = 1;
		// for each parameter
		for (final Annotation[] parAnnotations : annotations) {
			// for each annotation
			for (final Annotation annotation : parAnnotations) {
				if (annotation.getTypeName().equals(Marker.class.getCanonicalName())) {
					final String parameterName = "$" + (i);
					
					final int markerIndex = getMarkerIndex(annotation);
					
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
