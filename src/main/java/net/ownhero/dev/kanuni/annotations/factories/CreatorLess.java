/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.annotations.compare.LessDouble;
import net.ownhero.dev.kanuni.annotations.compare.LessInt;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.conditions.StringCondition;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

/**
 * The Class CreatorLess.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class CreatorLess extends Creator {
	
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
		
		if (markers.isEmpty()) {
			if (annotation.getTypeName().equals(LessInt.class.getName())) {
				IntegerMemberValue refMemberValue = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation,
				                                                                                          "ref");
				int ref = refMemberValue.getValue();
				
				builder.append(KanuniInstrumenter.compareClass);
				if (parameterType.isPrimitive()) {
					builder.append(String.format(".less(new Integer(%s), new Integer(%s), \"%s\", new Object[0]);",
					                             parameterName, ref, text));
				} else {
					builder.append(String.format(".less(%s, new Integer(%s), \"%s\", new Object[0]);", parameterName,
					                             ref, text));
				}
				builder.append(System.getProperty("line.separator"));
			} else if (annotation.getTypeName().equals(LessDouble.class.getName())) {
				DoubleMemberValue refMemberValue = (DoubleMemberValue) KanuniClassloader.getMemberValue(annotation,
				                                                                                        "ref");
				double ref = refMemberValue.getValue();
				
				builder.append(KanuniInstrumenter.compareClass);
				if (parameterType.isPrimitive()) {
					builder.append(String.format(".less(new Double(%s), new Double(%s), \"%s\", new Object[0]);",
					                             parameterName, ref, text));
				} else {
					builder.append(String.format(".less(%s, new Double(%s), \"%s\", new Object[0]);", parameterName,
					                             ref, text));
				}
				builder.append(System.getProperty("line.separator"));
			} else {
				throw new MalformedAnnotationException(annotation.getTypeName() + " requires corresponding "
				        + Marker.class.getSimpleName() + " annotation on the same behavior.");
			}
		}
		
		for (Integer markerId : markers.keySet()) {
			for (String markerParameter : markers.get(markerId)) {
				builder.append(KanuniInstrumenter.compareClass)
				       .append(String.format(".less(($w) %s, ($w) %s, \"%s\", new Object[0]);", parameterName,
				                             markerParameter, text)).append(System.getProperty("line.separator"));
			}
		}
		
		StringCondition.notEmpty(builder.toString(), "Valid instrumentations may never be empty.");
		return builder.toString();
	}
	
}
