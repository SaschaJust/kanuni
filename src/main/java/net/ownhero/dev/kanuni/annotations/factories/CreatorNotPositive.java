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
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CreatorNotPositive implements Creator {
	
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) {
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported behavior ("
		                                       + behavior.getName() + ") annotation: " + annotation.getTypeName());
	}
	
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) {
		throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
		        + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName());
	}
	
}
