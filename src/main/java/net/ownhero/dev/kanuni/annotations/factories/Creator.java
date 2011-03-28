/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public interface Creator {
	
	/**
	 * Used to request an instrumentation for a given annotation/behavior pair.
	 * 
	 * @param annotation
	 * @param behavior
	 * @param markers
	 * @return a string representation of the instrumentation that have to be
	 *         added to the behavior
	 */
	public String createBehaviorInstrumentation(Annotation annotation,
	                                            CtBehavior behavior,
	                                            Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException;
	
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
	public String createParameterInstrumentation(Annotation annotation,
	                                             CtBehavior behavior,
	                                             String parameterName,
	                                             CtClass parameterType,
	                                             Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException;
	
}
