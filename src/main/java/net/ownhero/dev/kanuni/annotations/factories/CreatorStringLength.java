/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.annotations.string.MaxLength;
import net.ownhero.dev.kanuni.annotations.string.MinLength;
import net.ownhero.dev.kanuni.annotations.string.SameLength;
import net.ownhero.dev.kanuni.conditions.CollectionCondition;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * The Class CreatorStringLength.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public final class CreatorStringLength extends Creator {
	
	/*
	 * (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#
	 * createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		final StringBuilder builder = new StringBuilder();
		
		if (annotation.getTypeName().equals(SameLength.class.getName())) {
			
			CollectionCondition.notEmpty(markers.keySet(),
			                             "Marker set of behavior annotations for " + SameLength.class.getName()
			                                     + " may not be empty.");
			
			for (final Integer markerId : markers.keySet()) {
				final StringBuilder arrayBuilder = new StringBuilder();
				final String initialPrepend = "new String[] { ";
				arrayBuilder.append(initialPrepend);
				
				for (final String markerParameter : markers.get(markerId)) {
					if (arrayBuilder.length() > initialPrepend.length()) {
						arrayBuilder.append(", ");
					}
					arrayBuilder.append(markerParameter);
				}
				
				arrayBuilder.append(" } ");
				
				final StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation,
				                                                                                          "value");
				final String text = textMember.getValue();
				
				builder.append(KanuniInstrumenter.stringClass)
				       .append(String.format(".sameLength(%s, \"%s\", new Object[0]);", arrayBuilder,
				                             StringEscapeUtils.escapeJava(text)))
				       .append(System.getProperty("line.separator"));
			}
			
		} else {
			throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported behavior ("
			        + behavior.getName() + ") annotation: " + annotation.getTypeName());
		}
		
		return builder.toString();
	}
	
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
		final StringBuilder builder = new StringBuilder();
		
		final StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
		final String text = textMember.getValue();
		
		builder.append(KanuniInstrumenter.stringClass);
		
		if (annotation.getTypeName().equals(MaxLength.class.getName())) {
			final IntegerMemberValue maxMemberValue = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation,
			                                                                                                "max");
			final int max = maxMemberValue.getValue();
			
			builder.append(String.format(".maxLength(%s, new Integer(%s)", parameterName, max));
		} else if (annotation.getTypeName().equals(MinLength.class.getName())) {
			final IntegerMemberValue maxMemberValue = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation,
			                                                                                                "min");
			final int max = maxMemberValue.getValue();
			
			builder.append(String.format(".minLength(%s, new Integer(%s)", parameterName, max));
		} else {
			throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
			        + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName());
		}
		
		builder.append(String.format(", \"%s\", new Object[0]);", StringEscapeUtils.escapeJava(text)));
		builder.append(System.getProperty("line.separator"));
		return builder.toString();
	}
	
}
