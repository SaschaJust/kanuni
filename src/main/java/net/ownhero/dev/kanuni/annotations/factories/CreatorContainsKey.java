/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.conditions.MapCondition;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
public class CreatorContainsKey implements Creator {
	
	/*
	 * (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#
	 * createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation,
	 * javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported behavior ("
		        + behavior.getName() + ") annotation: " + annotation.getTypeName());
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
	                                             final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		StringBuilder builder = new StringBuilder();
		
		StringMemberValue textMember = (StringMemberValue) KanuniInstrumenter.getMemberValue(annotation, "value");
		String text = textMember.getValue();
		
		try {
			HashSet<Class<?>> realInterfaces = new HashSet<Class<?>>();
			Class<?> original = Class.forName(parameterType.getName());
			realInterfaces.add(original);
			realInterfaces.addAll(KanuniInstrumenter.getInterfaces(original));
			
			if (realInterfaces.contains(Map.class)) {
				for (Integer markerId : markers.keySet()) {
					for (String markerParameter : markers.get(markerId)) {
						builder.append(MapCondition.class.getCanonicalName()).append(".");
						builder.append(String.format("containsKey((java.util.Map) %s, ($w) %s, \"%s\", new Object[0]);",
						                             parameterName, markerParameter, text));
						builder.append(System.getProperty("line.separator"));
					}
				}
			} else {
				throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
				        + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName());
			}
			
		} catch (ClassNotFoundException e) {
			throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
			        + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName(), e);
		}
		
		return builder.toString();
	}
	
}
