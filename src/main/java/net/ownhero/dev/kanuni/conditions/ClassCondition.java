/*******************************************************************************
 * Copyright 2012 Kim Herzig, Sascha Just
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/
package net.ownhero.dev.kanuni.conditions;

import java.lang.reflect.Modifier;

/**
 * The Class ClassCondition.
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public final class ClassCondition {
	
	/**
	 * Gets the caller class name.
	 * 
	 * @return the caller class name
	 */
	private static final String getCallerClassName() {
		final Throwable throwable = new Throwable();
		
		throwable.fillInStackTrace();
		
		final String className = throwable.getStackTrace()[2].getClassName();
		
		return className;
	}
	
	/**
	 * Instance.
	 * 
	 * @param instance
	 *            the instance
	 * @param of
	 *            the of
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void instance(final Object instance,
	                                  final Class<?> of,
	                                  final String formatString,
	                                  final Object... arguments) {
		// TODO check of for null
		if (instance != null) {
			assert (of.isAssignableFrom(instance.getClass())) : Condition.getCallerString()
			        + String.format("Object should be instance of '%s'. Violation: %s", of.getCanonicalName(),
			                        String.format(formatString, arguments));
		}
	}
	
	/**
	 * Instantiable.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void instantiable(final Class<?> clazz,
	                                      final String formatString,
	                                      final Object... arguments) {
		final String callerClassName = getCallerClassName();
		assert clazz != null : Condition.getCallerString()
		        + String.format("Clazz is (null) but should be instantiable. Violation: %s",
		                        String.format(formatString, arguments));
		assert !Modifier.isAbstract(clazz.getModifiers()) : Condition.getCallerString()
		        + String.format("Clazz '%s' is abstract but should be instantiable. Violation: %s",
		                        clazz.getCanonicalName(), String.format(formatString, arguments));
		assert !Modifier.isInterface(clazz.getModifiers()) : Condition.getCallerString()
		        + String.format("Clazz '%s' is an interface but should be instantiable. Violation: %s",
		                        clazz.getCanonicalName(), String.format(formatString, arguments));
		try {
			final Class<?> callerClass = Class.forName(callerClassName);
			assert !Modifier.isProtected(clazz.getModifiers()) || clazz.isAssignableFrom(callerClass)
			        || clazz.getPackage().equals(callerClass.getPackage()) : Condition.getCallerString()
			        + String.format("Clazz '%s' is protected but is no superclass of '%s' and from a different package. Violation: %s",
			                        clazz.getCanonicalName(), callerClass.getCanonicalName(),
			                        String.format(formatString, arguments));
			// default visibility
			assert Modifier.isProtected(clazz.getModifiers()) || Modifier.isPublic(clazz.getModifiers())
			        || clazz.getPackage().equals(callerClass.getPackage()) : Condition.getCallerString()
			        + String.format("Clazz '%s' has default visibility and can't be instatitated from a different package ('%s'). Violation: %s",
			                        clazz.getCanonicalName(), callerClass.getPackage(),
			                        String.format(formatString, arguments));
		} catch (final ClassNotFoundException e) {
			// silently ignore
		}
		
	}
}
