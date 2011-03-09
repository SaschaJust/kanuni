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
public class CreatorRange implements Creator {
	
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
		return "System.err.println(\"Behavior instrumentation for: " + behavior.getName() + " \");";
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * net.ownhero.dev.kanuni.annotations.factories.Creator#createInstrumentation
	 * (javassist.bytecode.annotation.Annotation, javassist.CtClass,
	 * java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) {
		
		return "System.err.println(\"Behavior instrumentation for: " + behavior.getName() + ", " + parameterName
		        + " \");";
	}
	
}
