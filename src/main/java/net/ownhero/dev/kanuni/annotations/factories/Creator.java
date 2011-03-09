/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public interface Creator {
	
	/**
	 * @param annotation
	 * @param behavior
	 * @param markers
	 * @return
	 */
	public String createBehaviorInstrumentation(Annotation annotation,
	                                            CtBehavior behavior,
	                                            Map<Integer, SortedSet<String>> markers);
	
	/**
	 * @param annotation
	 * @param behavior
	 * @param parameterName
	 * @param parameterType
	 * @param markers
	 * @return
	 */
	public String createParameterInstrumentation(Annotation annotation,
	                                             CtBehavior behavior,
	                                             String parameterName,
	                                             CtClass parameterType,
	                                             Map<Integer, SortedSet<String>> markers);
	
}
