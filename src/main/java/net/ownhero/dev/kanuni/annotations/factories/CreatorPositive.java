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
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class CreatorPositive extends Creator {
	
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
		
		StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
		String text = textMember.getValue();
		
		if (parameterType.isPrimitive()) {
			// load corresponding class and check type
			builder.append(KanuniInstrumenter.compareClass).append(".positive(");
			if (parameterType.getName().equals("double")) {
				builder.append("new Double(").append(parameterName).append(")");
			} else if (parameterType.getName().equals("long")) {
				builder.append("new Long(").append(parameterName).append(")");
			} else if (parameterType.getName().equals("int")) {
				builder.append("new Integer(").append(parameterName).append(")");
			} else if (parameterType.getName().equals("float")) {
				builder.append("new Float(").append(parameterName).append(")");
			} else if (parameterType.getName().equals("short")) {
				builder.append("new Short(").append(parameterName).append(")");
			} else {
				throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
				        + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName());
			}
			
			builder.append(String.format(", \"%s\", new Object[0]);", text))
			       .append(System.getProperty("line.separator"));
		} else {
			builder.append(KanuniInstrumenter.compareClass)
			       .append(String.format(".positive(%s, \"%s\", new Object[0]);", parameterName, text))
			       .append(System.getProperty("line.separator"));
		}
		
		return builder.toString();
	}
	
}
