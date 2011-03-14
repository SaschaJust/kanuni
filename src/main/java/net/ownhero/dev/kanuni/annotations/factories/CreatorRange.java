/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.annotations.bounds.RangeChar;
import net.ownhero.dev.kanuni.annotations.bounds.RangeDouble;
import net.ownhero.dev.kanuni.annotations.bounds.RangeFloat;
import net.ownhero.dev.kanuni.annotations.bounds.RangeInteger;
import net.ownhero.dev.kanuni.annotations.bounds.RangeLong;
import net.ownhero.dev.kanuni.conditions.BoundsCondition;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CreatorRange implements Creator {
	
	/*
	 * (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#
	 * createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation,
	 * javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) {
		throw new MalformedAnnotationException(CreatorStringType.class.getName() + ": unsupported behavior ("
		        + behavior.getName() + ") annotation: " + annotation.getTypeName());
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * net.ownhero.dev.kanuni.annotations.factories.Creator#createInstrumentation
	 * (javassist.bytecode.annotation.Annotation, javassist.CtClass,
	 * java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(BoundsCondition.class.getPackage().getName()).append(".");
		
		if (annotation.getTypeName().equals(RangeChar.class.getName())) {
			// process RangeChar
			CharMemberValue minMember = (CharMemberValue) KanuniClassloader.getMemberValue(annotation, "min");
			CharMemberValue maxMember = (CharMemberValue) KanuniClassloader.getMemberValue(annotation, "max");
			
			char min = minMember.getValue();
			char max = maxMember.getValue();
			
			StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
			String text = textMember.getValue();
			
			if (parameterType.isPrimitive()) {
				builder.append(String.format("BoundsCondition.range(new Character('%s'), new Character('%s'), new Character('%s'), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			} else {
				builder.append(String.format("BoundsCondition.range((Character) %s, new Character('%s'), new Character('%s'), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			}
		} else if (annotation.getTypeName().equals(RangeDouble.class.getName())) {
			// process RangeDouble
			DoubleMemberValue minMember = (DoubleMemberValue) KanuniClassloader.getMemberValue(annotation, "min");
			DoubleMemberValue maxMember = (DoubleMemberValue) KanuniClassloader.getMemberValue(annotation, "max");
			
			double min = minMember.getValue();
			double max = maxMember.getValue();
			
			StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
			String text = textMember.getValue();
			
			if (parameterType.isPrimitive()) {
				builder.append(String.format("BoundsCondition.range(new Double(%s), new Double(%s), new Double(%s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			} else {
				builder.append(String.format("BoundsCondition.range((Double) %s, new Double(%s), new Double(%s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			}
		} else if (annotation.getTypeName().equals(RangeFloat.class.getName())) {
			// process RangeFloat
			FloatMemberValue minMember = (FloatMemberValue) KanuniClassloader.getMemberValue(annotation, "min");
			FloatMemberValue maxMember = (FloatMemberValue) KanuniClassloader.getMemberValue(annotation, "max");
			
			float min = minMember.getValue();
			float max = maxMember.getValue();
			
			StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
			String text = textMember.getValue();
			
			if (parameterType.isPrimitive()) {
				builder.append(String.format("BoundsCondition.range(new Float(%s), new Float(%s), new Float(%s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			} else {
				builder.append(String.format("BoundsCondition.range((Float) %s, new Float(%s), new Float(%s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			}
		} else if (annotation.getTypeName().equals(RangeInteger.class.getName())) {
			// process RangeInteger
			IntegerMemberValue minMember = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation, "min");
			IntegerMemberValue maxMember = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation, "max");
			
			int min = minMember.getValue();
			int max = maxMember.getValue();
			
			StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
			String text = textMember.getValue();
			
			if (parameterType.isPrimitive()) {
				builder.append(String.format("BoundsCondition.range(new Integer(%s), new Integer(%s), new Integer(%s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			} else {
				builder.append(String.format("BoundsCondition.range((Integer) %s, new Integer(%s), new Integer(%s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			}
		} else if (annotation.getTypeName().equals(RangeLong.class.getName())) {
			// process RangeLong
			LongMemberValue minMember = (LongMemberValue) KanuniClassloader.getMemberValue(annotation, "min");
			LongMemberValue maxMember = (LongMemberValue) KanuniClassloader.getMemberValue(annotation, "max");
			
			long min = minMember.getValue();
			long max = maxMember.getValue();
			
			StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
			String text = textMember.getValue();
			
			if (parameterType.isPrimitive()) {
				builder.append(String.format("BoundsCondition.range(new Long(%s), new Long((long) %s), new Long((long) %s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			} else {
				builder.append(String.format("BoundsCondition.range((Long) %s, new Long((long) %s), new Long((long) %s), \"%s\", new Object[0])",
				                             parameterName, min, max, StringEscapeUtils.escapeJava(text)));
			}
		} else {
			throw new MalformedAnnotationException();
		}
		
		builder.append(";");
		
		return builder.toString();
	}
	
	// private String getParameterInstrumentation(final Annotation annotation,
	// final String parameterName,
	// final CtClass parameterType,
	// final String template,
	// final Map<Integer, SortedSet<String>> markers) {
	// Condition.notNull(annotation,
	// "When instrumention is requested for parameters the corresponding annotation might never be (null).");
	// StringCondition.matches(parameterName,
	// "\\$[0-9]+",
	// "Parameter names must match the pattern '\\$[0-9]+' since they are replace by javassist on compile. Got: %s",
	// parameterName);
	// Condition.notNull(parameterType,
	// "Every parameter an instrumentation is requested for has to provide a proper type, but parameterType was set to (null).");
	// StringCondition.notEmpty(template,
	// "The template of the annotation the instrumentation is build for cannot be empty.");
	//
	// String instrumentation = String.format("%s", template);
	//
	// // escape the parameterName with '\\' since '$' will cause
	// // backreferencing when using replaceAll(String, String)
	// instrumentation = instrumentation.replaceAll("\\$pname\\$", "\\" +
	// parameterName);
	// instrumentation = instrumentation.replaceAll("\\$ptype\\$",
	// parameterType.getName());
	//
	// Pattern pattern = Pattern.compile("\\$([A-Za-z:]+)\\$");
	// Matcher matcher = pattern.matcher(instrumentation);
	//
	// while (matcher.find()) {
	// String memberName = matcher.group().substring(1);
	// memberName = memberName.substring(0, memberName.length() - 1);
	// Object memberValue = getMemberValue(annotation, memberName);
	// if (memberName.equals("marker")) {
	// ArrayMemberValue amv = (ArrayMemberValue) memberValue;
	// if (amv.getValue().length > 1) {
	// for (MemberValue marker : amv.getValue()) {
	// String markerParameterName = " new Object[] { "
	// + markers.get(Integer.parseInt(marker.toString())).first() + " } ";
	// instrumentation = instrumentation.replace("$" + memberName + "$",
	// markerParameterName);
	// }
	// } else {
	// // TODO check bounds
	// // TODO check list to not be null
	// SortedSet<String> markerNames =
	// markers.get(Integer.parseInt(amv.getValue()[0].toString()));
	//
	// StringBuilder builder = new StringBuilder();
	//
	// if (markerNames.size() > 1) {
	// final String prepend = " new Object[] { ";
	// builder.append(prepend);
	// for (String markerName : markerNames) {
	// if (builder.length() > prepend.length()) {
	// builder.append(", ");
	// }
	// builder.append(markerName);
	// }
	// builder.append(" } ");
	// } else {
	// String markerName = markerNames.first();
	// markerNames.remove(markerName);
	// builder.append(markerName);
	// }
	//
	// instrumentation = instrumentation.replace("$" + memberName + "$",
	// builder.toString());
	// }
	// } else {
	// instrumentation = instrumentation.replace("$" + memberName + "$",
	// memberValue.toString());
	// }
	// // TODO for each marker
	// // get marker name (i.e. $2, $3)...
	// // replace $marker$ with $2, $3... correspondingly
	//
	// }
	//
	// // prepend package
	// instrumentation = Condition.class.getPackage().getName() + "." +
	// instrumentation + ";";
	//
	// // TODO This will fail, e.g. when using @Matches("$foo:bar$") - what
	// // actually makes no sense.
	// // But assume we would have an @StringEquals("$foo:bar$"), this would
	// // break the condition for valid instrumentation.
	// StringCondition.notMatches(instrumentation, "\\$[A-Za-z:]+\\$",
	// "Some of the placeholder in the template were not processed correctly: %s",
	// instrumentation);
	// StringCondition.notEmpty(instrumentation,
	// "The instrumentation string might never be empty.");
	//
	// // System.err.println("generated instrumentation: " + instrumentation);
	// return instrumentation;
	// }
	
}
