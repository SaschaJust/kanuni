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

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.Predicate;

/**
 * This class provides in place specification checks using assertions. Call Condition.XYZ(...) to ensure certain
 * conditions. All methods support a specification string or a format string followed by several objects. Never make
 * calls to any methods of the objects under suspect since this will prevent the JIT compiler to ignore the method calls
 * to {@link Condition} if assertions are disabled.
 * 
 * If you require further checks extend this class correspondingly.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class Condition {
	
	/**
	 * The Class NoneNullPredicate.
	 */
	static final class NoneNullPredicate implements Predicate {
		
		/** The string. */
		String string = "unspecified";
		
		/*
		 * (non-Javadoc)
		 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
		 */
		@Override
		public boolean evaluate(final Object object) {
			if (object == null) {
				return true;
			}
			if (object instanceof Map<?, ?>) {
				MapCondition.noneNull((Map<?, ?>) object, this.string);
			} else if (object instanceof Collection<?>) {
				CollectionCondition.noneNull((Collection<?>) object, this.string);
			} else if (object.getClass().isArray()) {
				if (object.getClass().getComponentType().isAssignableFrom(Object.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Integer.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Byte.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Short.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Long.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Float.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Double.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Boolean.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Character.class)) {
					ArrayCondition.noneNull(object, this.string);
				} else {
					ArrayCondition.noneNull(object, this.string);
				}
			}
			return false;
		}
		
		/**
		 * Sets the message.
		 * 
		 * @param message
		 *            the message
		 * @return the none null predicate
		 */
		public NoneNullPredicate setMessage(final String message) {
			this.string = message;
			return this;
		}
		
		/**
		 * Sets the message.
		 * 
		 * @param formatString
		 *            the format string
		 * @param arguments
		 *            the arguments
		 * @return the current instance of {@link NoneNullPredicate}.
		 */
		public NoneNullPredicate setMessage(final String formatString,
		                                    final Object... arguments) {
			this.string = String.format(formatString, arguments).toString();
			return this;
		}
		
	}
	
	/** The Constant noneNullPredicate. */
	static final NoneNullPredicate noneNullPredicate = new Condition.NoneNullPredicate();
	
	/**
	 * All null or none.
	 * 
	 * @param object1
	 *            the object1
	 * @param object2
	 *            the object2
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void allNullOrNone(final Object object1,
	                                       final Object object2,
	                                       final String formatString,
	                                       final Object... arguments) {
		if (object1 == null) {
			assert object2 == null : getCallerString()
			        + String.format("Arguments have to be all (null) or none of them. Violation: %s",
			                        String.format(formatString, arguments).toString());
		} else {
			assert object2 != null : getCallerString()
			        + String.format("Arguments have to be all (null) or none of them. Violation: %s",
			                        String.format(formatString, arguments).toString());
		}
	}
	
	/**
	 * Check.
	 * 
	 * @param condition
	 *            the condition
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void check(final boolean condition,
	                               final String formatString,
	                               final Object... arguments) {
		assert condition : getCallerString()
		        + String.format("Condition evaluated to false. Violation: %s", String.format(formatString, arguments)
		                                                                             .toString());
	}
	
	/**
	 * Gets the caller string.
	 * 
	 * @return a string representing the line of code the condition was triggered from.
	 */
	public static final String getCallerString() {
		final Throwable throwable = new Throwable();
		
		throwable.fillInStackTrace();
		
		final Integer lineNumber = throwable.getStackTrace()[2].getLineNumber();
		final String methodName = throwable.getStackTrace()[2].getMethodName();
		final String className = throwable.getStackTrace()[2].getClassName();
		
		return "[" + className + "::" + methodName + "#" + lineNumber + "] Assertion violated: ";
	}
	
	/**
	 * Checks if is boolean.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isBoolean(final Object object,
	                                   final String formatString,
	                                   final Object... arguments) {
		assert (object != null) && (object instanceof Boolean) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is byte.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isByte(final Object object,
	                                final String formatString,
	                                final Object... arguments) {
		assert (object != null) && (object instanceof Byte) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is character.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isCharacter(final Object object,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (object != null) && (object instanceof Character) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is double.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isDouble(final Object object,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (object != null) && (object instanceof Double) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is float.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isFloat(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert (object != null) && (object instanceof Float) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is integer.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isInteger(final Object object,
	                                   final String formatString,
	                                   final Object... arguments) {
		assert (object != null) && (object instanceof Integer) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is long.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isLong(final Object object,
	                                final String formatString,
	                                final Object... arguments) {
		assert (object != null) && (object instanceof Long) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Checks if is null.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isNull(final Object object,
	                                final String formatString,
	                                final Object... arguments) {
		assert object == null : getCallerString()
		        + String.format("Argument MUST be (null). Violation: %s", String.format(formatString, arguments)
		                                                                        .toString());
	}
	
	/**
	 * Checks if is short.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void isShort(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert (object != null) && (object instanceof Double) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * None null.
	 * 
	 * @param object1
	 *            the object1
	 * @param object2
	 *            the object2
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void noneNull(final Object object1,
	                                  final Object object2,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert object1 != null : getCallerString()
		        + String.format("All arguments have to be not (null). Violation: %s",
		                        String.format(formatString, arguments).toString());
		assert object2 != null : getCallerString()
		        + String.format("All arguments have to be not (null). Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * Not null.
	 * 
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void notNull(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert object != null : getCallerString()
		        + String.format("Argument should not be (null). Violation: %s", String.format(formatString, arguments)
		                                                                              .toString());
	}
	
}
