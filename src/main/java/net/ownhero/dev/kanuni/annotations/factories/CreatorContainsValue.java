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
public class CreatorContainsValue implements Creator {
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(Annotation annotation,
	                                            CtBehavior behavior,
	                                            Map<Integer, SortedSet<String>> markers) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createParameterInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.lang.String, javassist.CtClass, java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(Annotation annotation,
	                                             CtBehavior behavior,
	                                             String parameterName,
	                                             CtClass parameterType,
	                                             Map<Integer, SortedSet<String>> markers) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
