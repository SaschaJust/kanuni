/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public abstract class Creator {
	
	/**
	 * Used to request an instrumentation for a given annotation/behavior pair.
	 * 
	 * @param annotation
	 * @param behavior
	 * @param markers
	 * @return a string representation of the instrumentation that have to be
	 *         added to the behavior
	 */
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported behavior ("
		        + behavior.getName() + ") annotation: " + annotation.getTypeName());
	}
	
	/**
	 * Used to request an instrumentation for a given annotation/parameter pair.
	 * 
	 * @param annotation
	 * @param behavior
	 * @param parameterName
	 * @param parameterType
	 * @param markers
	 * @return a string representation of the instrumentation that have to be
	 *         added to the behavior
	 */
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter (" + parameterName
		        + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName());
	}
	
}
