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
import net.ownhero.dev.kanuni.conditions.Condition;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CreatorNoneNull implements Creator {
	
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
		StringBuilder builder = new StringBuilder();
		
		for (Integer markerId : markers.keySet()) {
			for (String markerParameter : markers.get(markerId)) {
				builder.append(Condition.class.getPackage().getName()).append(".");
				
				StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
				String text = textMember.getValue();
				
				builder.append(String.format("Condition.notNull(%s, \"%s\", new Object[0])", markerParameter,
				                             StringEscapeUtils.escapeJava(text)));
				
				builder.append(";");
				builder.append(System.getProperty("line.separator"));
			}
		}
		
		return builder.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#
	 * createParameterInstrumentation(javassist.bytecode.annotation.Annotation,
	 * javassist.CtBehavior, java.lang.String, javassist.CtClass, java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) {
		// TODO check for collections/maps/arrays
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
		                                       + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName());
	}
	
}
