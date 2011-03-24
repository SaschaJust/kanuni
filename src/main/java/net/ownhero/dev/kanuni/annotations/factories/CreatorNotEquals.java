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
import net.ownhero.dev.kanuni.annotations.compare.NotEqualsInt;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.conditions.CompareCondition;
import net.ownhero.dev.kanuni.conditions.StringCondition;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;


/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
public class CreatorNotEquals implements Creator {
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createParameterInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.lang.String, javassist.CtClass, java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) {
		StringBuilder builder = new StringBuilder();
		
		StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
		String text = textMember.getValue();
		
		if (markers.isEmpty()) {
			if (annotation.getTypeName().equals(NotEqualsInt.class.getName())) {
				IntegerMemberValue refMemberValue = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation,
				"ref");
				int ref = refMemberValue.getValue();
				
				builder.append(CompareCondition.class.getCanonicalName()).append(".");
				if (parameterType.isPrimitive()) {
					builder.append(String.format("notEquals(new Integer(%s), new Integer(%s), \"%s\", new Object[0]);",
					                             parameterName, ref, text));
				} else {
					builder.append(String.format("notEquals(%s, new Integer(%s), \"%s\", new Object[0]);",
					                             parameterName, ref, text));
				}
				builder.append(System.getProperty("line.separator"));
			} else {
				throw new MalformedAnnotationException(annotation.getTypeName() + " requires corresponding "
				                                       + Marker.class.getSimpleName() + " annotation on the same behavior.");
			}
		}
		
		for (Integer markerId : markers.keySet()) {
			for (String markerParameter : markers.get(markerId)) {
				builder.append(CompareCondition.class.getCanonicalName()).append(".");
				builder.append(String.format("notEquals(($w) %s, ($w) %s, \"%s\", new Object[0]);", parameterName,
				                             markerParameter, text));
				builder.append(System.getProperty("line.separator"));
			}
		}
		
		StringCondition.notEmpty(builder.toString(), "Valid instrumentations may never be empty.");
		return builder.toString();
	}
	
}
