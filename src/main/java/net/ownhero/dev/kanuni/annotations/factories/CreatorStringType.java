/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
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
import net.ownhero.dev.kanuni.annotations.string.LongString;
import net.ownhero.dev.kanuni.annotations.string.Lowercase;
import net.ownhero.dev.kanuni.annotations.string.NotEmptyString;
import net.ownhero.dev.kanuni.annotations.string.ShortString;
import net.ownhero.dev.kanuni.annotations.string.Trimmed;
import net.ownhero.dev.kanuni.annotations.string.Uppercase;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * The Class CreatorStringType.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public final class CreatorStringType extends Creator {
	
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
		
		StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
		String text = textMember.getValue();
		
		builder.append(KanuniInstrumenter.stringClass).append(".");
		
		if (annotation.getTypeName().equals(AlphaNumString.class.getName())) {
			builder.append("alphanum");
		} else if (annotation.getTypeName().equals(AlphaString.class.getName())) {
			builder.append("alpha");
		} else if (annotation.getTypeName().equals(AsciiString.class.getName())) {
			builder.append("ascii");
		} else if (annotation.getTypeName().equals(DigitString.class.getName())) {
			builder.append("digit");
		} else if (annotation.getTypeName().equals(EmptyString.class.getName())) {
			builder.append("empty");
		} else if (annotation.getTypeName().equals(HexString.class.getName())) {
			builder.append("hex");
		} else if (annotation.getTypeName().equals(ByteString.class.getName())) {
			builder.append("isByte");
		} else if (annotation.getTypeName().equals(DoubleString.class.getName())) {
			builder.append("isDouble");
		} else if (annotation.getTypeName().equals(FloatString.class.getName())) {
			builder.append("isFloat");
		} else if (annotation.getTypeName().equals(IntegerString.class.getName())) {
			builder.append("isInteger");
		} else if (annotation.getTypeName().equals(LongString.class.getName())) {
			builder.append("isLong");
		} else if (annotation.getTypeName().equals(ShortString.class.getName())) {
			builder.append("isShort");
		} else if (annotation.getTypeName().equals(Lowercase.class.getName())) {
			builder.append("lowercase");
		} else if (annotation.getTypeName().equals(NotEmptyString.class.getName())) {
			builder.append("notEmpty");
		} else if (annotation.getTypeName().equals(Trimmed.class.getName())) {
			builder.append("trimmed");
		} else if (annotation.getTypeName().equals(Uppercase.class.getName())) {
			builder.append("uppercase");
		} else {
			throw new MalformedAnnotationException(this.getClass().getName() + " does not support annotation: "
			        + annotation.getTypeName());
		}
		
		builder.append(String.format("(%s, \"%s\", new Object[0]);", parameterName, StringEscapeUtils.escapeJava(text)));
		builder.append(System.getProperty("line.separator"));
		
		return builder.toString();
	}
	
}
