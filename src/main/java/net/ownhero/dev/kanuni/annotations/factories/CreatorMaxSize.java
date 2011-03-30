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
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
public final class CreatorMaxSize extends Creator {
	
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
		
		IntegerMemberValue maxMemberValue = (IntegerMemberValue) KanuniInstrumenter.getMemberValue(annotation, "max");
		int max = maxMemberValue.getValue();
		
		if (parameterType.isArray()) {
			builder.append(KanuniInstrumenter.arrayClass)
			       .append(String.format(".maxSize(%s, new Integer(%s), \"%s\", new Object[0]);", parameterName, max,
			                             text)).append(System.getProperty("line.separator"));
		} else {
			try {
				HashSet<Class<?>> realInterfaces = new HashSet<Class<?>>();
				Class<?> original = Class.forName(parameterType.getName());
				realInterfaces.add(original);
				if (original == String.class) {
					builder.append(KanuniInstrumenter.stringClass)
					       .append(String.format(".maxLength(%s, new Integer(%s), \"%s\", new Object[0]);",
					                             parameterName, max, text))
					       .append(System.getProperty("line.separator"));
				} else {
					realInterfaces.addAll(KanuniInstrumenter.getInterfaces(original));
					
					if (realInterfaces.contains(Map.class)) {
						builder.append(KanuniInstrumenter.mapClass)
						       .append(String.format(".maxSize((java.util.Map) %s, %s, \"%s\", new Object[0]);",
						                             parameterName, max, text))
						       .append(System.getProperty("line.separator"));
					} else if (realInterfaces.contains(Collection.class)) {
						builder.append(KanuniInstrumenter.collectionClass)
						       .append(String.format(".maxSize((java.util.Collection) %s, %s,  \"%s\", new Object[0]);",
						                             parameterName, max, text))
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
