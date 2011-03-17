/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.Collection;
import java.util.Map;

import net.ownhero.dev.kanuni.exceptions.CheckViolation;

import org.apache.commons.collections.Predicate;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class Check {
	
	static final class NoneNullPredicate implements Predicate {
		
		String string = "unspecified";
		
		/*
		 * (non-Javadoc)
		 * @see
		 * org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
		 */
		public boolean evaluate(final Object object) {
			if (object == null) {
				return true;
			}
			if (object instanceof Map<?, ?>) {
				MapCheck.noneNull((Map<?, ?>) object, this.string);
			} else if (object instanceof Collection<?>) {
				CollectionCheck.noneNull((Collection<?>) object, this.string);
			} else if (object.getClass().isArray()) {
				if (object.getClass().getComponentType().isAssignableFrom(Object.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Integer.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Byte.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Short.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Long.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Float.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Double.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Boolean.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else if (object.getClass().getComponentType().isAssignableFrom(Character.class)) {
					ArrayCheck.noneNull(object, this.string);
				} else {
					ArrayCheck.noneNull(object, this.string);
				}
			}
			return false;
		}
		
		/**
		 * @param message
		 */
		public NoneNullPredicate setMessage(final String message) {
			this.string = message;
			return this;
		}
		
		/**
		 * @param formatString
		 * @param arguments
		 * @return the current instance of the {@link NoneNullPredicate}
		 */
		public NoneNullPredicate setMessage(final String formatString,
		                                    final Object... arguments) {
			this.string = String.format(formatString, arguments).toString();
			return this;
		}
		
	}
	
	final static NoneNullPredicate noneNullPredicate = new NoneNullPredicate();
	
	/**
	 * @param condition
	 * @param formatString
	 * @param arguments
	 */
	public static final void check(final boolean condition,
	                               final String formatString,
	                               final Object... arguments) {
		if (!condition) { throw new CheckViolation(Check.getCallerString()
		                                           + String.format("Condition evaluated to false. Violation: %s", String.format(formatString, arguments)
		                                                           .toString()));
		}
	}
	
	/**
	 * @return a string representing the line of code the check was called from.
	 */
	static final String getCallerString() {
		Throwable throwable = new Throwable();
		
		throwable.fillInStackTrace();
		
		Integer lineNumber = throwable.getStackTrace()[2].getLineNumber();
		String methodName = throwable.getStackTrace()[2].getMethodName();
		String className = throwable.getStackTrace()[2].getClassName();
		
		return "[" + className + "::" + methodName + "#" + lineNumber + "] Assertion violated: ";
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void notNull(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		if (object == null) {
			throw new CheckViolation(Check.getCallerString()
			                         + String.format("Argument should not be (null). Violation: %s",
			                                         String.format(formatString, arguments).toString()));
		}
	}
}
