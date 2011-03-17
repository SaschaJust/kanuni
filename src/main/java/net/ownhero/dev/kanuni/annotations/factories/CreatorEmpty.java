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
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.conditions.ArrayCondition;
import net.ownhero.dev.kanuni.conditions.CollectionCondition;
import net.ownhero.dev.kanuni.conditions.MapCondition;
import net.ownhero.dev.kanuni.conditions.StringCondition;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;


/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
public class CreatorEmpty implements Creator {
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) {
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported behavior ("
		                                       + behavior.getName() + ") annotation: " + annotation.getTypeName());
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
		
		if (parameterType.isArray()) {
			builder.append(ArrayCondition.class.getCanonicalName())
			.append(String.format(".empty(%s, \"%s\", new Object[0]);", parameterName, text))
			.append(System.getProperty("line.separator"));
		} else {
			try {
				HashSet<Class<?>> realInterfaces = new HashSet<Class<?>>();
				Class<?> original = Class.forName(parameterType.getName());
				
				if (original == String.class) {
					builder.append(StringCondition.class.getCanonicalName())
					       .append(String.format(".empty(%s, \"%s\", new Object[0]);", parameterName, text))
					       .append(System.getProperty("line.separator"));
				} else {
					realInterfaces.add(original);
					realInterfaces.addAll(KanuniClassloader.getInterfaces(original));
					
					if (realInterfaces.contains(Map.class)) {
						builder.append(MapCondition.class.getCanonicalName())
						.append(String.format(".empty((java.util.Map) %s, \"%s\", new Object[0]);", parameterName,
						                      text))
						                      .append(System.getProperty("line.separator"));
					} else if (realInterfaces.contains(Collection.class)) {
						builder.append(CollectionCondition.class.getCanonicalName())
						.append(String.format(".empty((java.util.Collection) %s, \"%s\", new Object[0]);",
						                      parameterName, text))
						                      .append(System.getProperty("line.separator"));
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
