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
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported behavior ("
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
	
}
