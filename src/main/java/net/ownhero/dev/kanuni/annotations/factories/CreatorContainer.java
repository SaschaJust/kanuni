/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * The Class CreatorContainer.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public final class CreatorContainer extends Creator {
	
	/*
	 * (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#
	 * createParameterInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.lang.String,
	 * javassist.CtClass, java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		StringBuilder builder = new StringBuilder();
		
		BooleanMemberValue allowEmptyMember = (BooleanMemberValue) KanuniInstrumenter.getMemberValue(annotation,
		                                                                                             "allowEmpty");
		boolean allowEmpty = allowEmptyMember.getValue();
		
		BooleanMemberValue allowNullMember = (BooleanMemberValue) KanuniInstrumenter.getMemberValue(annotation,
		                                                                                            "allowNull");
		boolean allowNull = allowNullMember.getValue();
		
		BooleanMemberValue forceEmptyMember = (BooleanMemberValue) KanuniInstrumenter.getMemberValue(annotation,
		                                                                                             "forceEmpty");
		boolean forceEmpty = forceEmptyMember.getValue();
		
		BooleanMemberValue forceNullMember = (BooleanMemberValue) KanuniInstrumenter.getMemberValue(annotation,
		                                                                                            "forceNull");
		boolean forceNull = forceNullMember.getValue();
		
		IntegerMemberValue maxSizeMember = (IntegerMemberValue) KanuniInstrumenter.getMemberValue(annotation, "maxSize");
		int maxSize = maxSizeMember.getValue();
		
		IntegerMemberValue minSizeMember = (IntegerMemberValue) KanuniInstrumenter.getMemberValue(annotation, "minSize");
		int minSize = minSizeMember.getValue();
		
		BooleanMemberValue noneNullMember = (BooleanMemberValue) KanuniInstrumenter.getMemberValue(annotation,
		                                                                                           "noneNull");
		boolean noneNull = noneNullMember.getValue();
		
		IntegerMemberValue sizeMember = (IntegerMemberValue) KanuniInstrumenter.getMemberValue(annotation, "size");
		int size = sizeMember.getValue();
		
		StringMemberValue textMember = (StringMemberValue) KanuniInstrumenter.getMemberValue(annotation, "spec");
		String text = textMember.getValue();
		
		if (!allowNull && forceNull) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": invalid annotation parameters ("
			        + annotation.getTypeName() + "): "
			        + "`allowNull` set to FALSE at the same time as `foceNull` is TRUE.");
		}
		
		if (!allowEmpty && forceEmpty) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": invalid annotation parameters ("
			        + annotation.getTypeName() + "): "
			        + "`allowEmpty` set to FALSE at the same time as `foceEmpty` is TRUE.");
		}
		
		if ((minSize > 0) && forceEmpty) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": invalid annotation parameters ("
			        + annotation.getTypeName() + "): " + "`minSize` set to " + minSize
			        + " at the same time as `foceEmpty` is TRUE.");
		}
		
		if (minSize > maxSize) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": invalid annotation parameters ("
			        + annotation.getTypeName() + "): " + "`minSize` set to " + minSize
			        + " at the same time as `maxSize` is set to " + maxSize + ".");
		}
		
		if ((size > 0) && ((minSize > 0) || (maxSize > 0))) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": invalid annotation parameters ("
			        + annotation.getTypeName() + "): " + "`size` set (" + size
			        + ") as a min/max size property is specified. (min=" + minSize + ", max=" + maxSize + ").");
		}
		
		if (forceNull && noneNull) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": invalid annotation parameters ("
			        + annotation.getTypeName() + "): "
			        + "`forceNull` set to TRUE at the same time as `noneNull` is FALSE.");
		}
		
		if (parameterType.isArray()) {
			if (forceNull) {
				builder.append(KanuniInstrumenter.simpleClass)
				       .append(String.format(".isNull(%s, \"%s\", new Object[0]);", parameterName,
				                             StringEscapeUtils.escapeJava(text)))
				       .append(System.getProperty("line.separator"));
			} else {
				if (!allowNull) {
					builder.append(KanuniInstrumenter.simpleClass)
					       .append(String.format(".notNull(%s, \"%s\", new Object[0]);", parameterName,
					                             StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				}
				
				if (!allowEmpty) {
					builder.append(KanuniInstrumenter.arrayClass)
					       .append(String.format(".notEmpty(%s, \"%s\", new Object[0]);", parameterName,
					                             StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				} else if (forceEmpty) {
					builder.append(KanuniInstrumenter.arrayClass)
					       .append(String.format(".isEmpty(%s, \"%s\", new Object[0]);", parameterName,
					                             StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				}
				
				if (minSize > 0) {
					builder.append(KanuniInstrumenter.arrayClass)
					       .append(String.format(".minSize(%s, new Integer(%s), \"%s\", new Object[0]);",
					                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				}
				
				if (maxSize > 0) {
					builder.append(KanuniInstrumenter.arrayClass)
					       .append(String.format(".maxSize(%s, new Integer(%s), \"%s\", new Object[0]);",
					                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				}
				
				if (size > 0) {
					builder.append(KanuniInstrumenter.arrayClass)
					       .append(String.format(".size(%s, new Integer(%s), \"%s\", new Object[0]);", parameterName,
					                             maxSize, StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				}
				
				if (noneNull) {
					builder.append(KanuniInstrumenter.arrayClass)
					       .append(String.format(".noneNull(%s, \"%s\", new Object[0]);", parameterName,
					                             StringEscapeUtils.escapeJava(text)))
					       .append(System.getProperty("line.separator"));
				}
			}
		} else {
			try {
				HashSet<Class<?>> realInterfaces = new HashSet<Class<?>>();
				Class<?> original = Class.forName(parameterType.getName());
				realInterfaces.add(original);
				if (original == String.class) {
					if (noneNull) {
						throw new MalformedAnnotationException(this.getClass().getName()
						        + ": invalid annotation parameters (" + annotation.getTypeName() + "): "
						        + "`noneNull` set for parameter type String.");
					}
					
					if (forceNull) {
						builder.append(KanuniInstrumenter.simpleClass)
						       .append(String.format(".isNull(%s, \"%s\", new Object[0]);", parameterName,
						                             StringEscapeUtils.escapeJava(text)))
						       .append(System.getProperty("line.separator"));
					} else {
						if (!allowNull) {
							builder.append(KanuniInstrumenter.simpleClass)
							       .append(String.format(".notNull(%s, \"%s\", new Object[0]);", parameterName,
							                             StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						}
						
						if (!allowEmpty) {
							builder.append(KanuniInstrumenter.stringClass)
							       .append(String.format(".notEmpty(%s, \"%s\", new Object[0]);", parameterName,
							                             StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						} else if (forceEmpty) {
							builder.append(KanuniInstrumenter.stringClass)
							       .append(String.format(".isEmpty(%s, \"%s\", new Object[0]);", parameterName,
							                             StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						}
						
						if (minSize > 0) {
							builder.append(KanuniInstrumenter.stringClass)
							       .append(String.format(".minLength(%s, new Integer(%s), \"%s\", new Object[0]);",
							                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						}
						
						if (maxSize > 0) {
							builder.append(KanuniInstrumenter.stringClass)
							       .append(String.format(".maxLength(%s, new Integer(%s), \"%s\", new Object[0]);",
							                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						}
						
						if (size > 0) {
							builder.append(KanuniInstrumenter.stringClass)
							       .append(String.format(".size(%s, new Integer(%s), \"%s\", new Object[0]);",
							                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						}
					}
				} else {
					realInterfaces.addAll(KanuniInstrumenter.getInterfaces(original));
					
					if (realInterfaces.contains(Map.class)) {
						
						if (forceNull) {
							builder.append(KanuniInstrumenter.simpleClass)
							       .append(String.format(".isNull(%s, \"%s\", new Object[0]);", parameterName,
							                             StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						} else {
							if (!allowNull) {
								builder.append(KanuniInstrumenter.simpleClass)
								       .append(String.format(".notNull(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (!allowEmpty) {
								builder.append(KanuniInstrumenter.mapClass)
								       .append(String.format(".notEmpty(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							} else if (forceEmpty) {
								builder.append(KanuniInstrumenter.mapClass)
								       .append(String.format(".isEmpty(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (minSize > 0) {
								builder.append(KanuniInstrumenter.mapClass)
								       .append(String.format(".minSize(%s, new Integer(%s), \"%s\", new Object[0]);",
								                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (maxSize > 0) {
								builder.append(KanuniInstrumenter.mapClass)
								       .append(String.format(".maxSize(%s, new Integer(%s), \"%s\", new Object[0]);",
								                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (size > 0) {
								builder.append(KanuniInstrumenter.mapClass)
								       .append(String.format(".size(%s, new Integer(%s), \"%s\", new Object[0]);",
								                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (noneNull) {
								builder.append(KanuniInstrumenter.mapClass)
								       .append(String.format(".noneNull(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
						}
					} else if (realInterfaces.contains(Collection.class)) {
						
						if (forceNull) {
							builder.append(KanuniInstrumenter.simpleClass)
							       .append(String.format(".isNull(%s, \"%s\", new Object[0]);", parameterName,
							                             StringEscapeUtils.escapeJava(text)))
							       .append(System.getProperty("line.separator"));
						} else {
							if (!allowNull) {
								builder.append(KanuniInstrumenter.simpleClass)
								       .append(String.format(".notNull(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (!allowEmpty) {
								builder.append(KanuniInstrumenter.collectionClass)
								       .append(String.format(".notEmpty(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							} else if (forceEmpty) {
								builder.append(KanuniInstrumenter.collectionClass)
								       .append(String.format(".isEmpty(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (minSize > 0) {
								builder.append(KanuniInstrumenter.collectionClass)
								       .append(String.format(".minSize(%s, new Integer(%s), \"%s\", new Object[0]);",
								                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (maxSize > 0) {
								builder.append(KanuniInstrumenter.collectionClass)
								       .append(String.format(".maxSize(%s, new Integer(%s), \"%s\", new Object[0]);",
								                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (size > 0) {
								builder.append(KanuniInstrumenter.collectionClass)
								       .append(String.format(".size(%s, new Integer(%s), \"%s\", new Object[0]);",
								                             parameterName, maxSize, StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
							
							if (noneNull) {
								builder.append(KanuniInstrumenter.collectionClass)
								       .append(String.format(".noneNull(%s, \"%s\", new Object[0]);", parameterName,
								                             StringEscapeUtils.escapeJava(text)))
								       .append(System.getProperty("line.separator"));
							}
						}
					} else {
						throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
						        + parameterName + ":" + parameterType.getName() + ") annotation: "
						        + annotation.getTypeName());
					}
				}
			} catch (ClassNotFoundException e) {
				throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
				        + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName(),
				                                       e);
			}
		}
		
		return builder.toString();
	}
	
}
