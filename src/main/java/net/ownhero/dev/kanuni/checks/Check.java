/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.Collection;
import java.util.Map;

import net.ownhero.dev.kanuni.exceptions.violations.CommonCheckViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CommonNotNullViolation;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.collections.Predicate;

/**
 * The Class Check.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class Check {
	
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
		 * Sets the message.
		 *
		 * @param message the message
		 * @return the none null predicate
		 */
		public NoneNullPredicate setMessage(final String message) {
			this.string = message;
			return this;
		}
		
		/**
		 * Sets the message.
		 *
		 * @param formatString the format string
		 * @param arguments the arguments
		 * @return the current instance of the {@link NoneNullPredicate}
		 */
		public NoneNullPredicate setMessage(final String formatString,
		                                    final Object... arguments) {
			this.string = String.format(formatString, arguments).toString();
			return this;
		}
		
	}
	
	/** The Constant noneNullPredicate. */
	final static NoneNullPredicate noneNullPredicate = new NoneNullPredicate();
	
	/**
	 * Check.
	 *
	 * @param condition the condition
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void check(final boolean condition,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!condition) {
				throw new CommonCheckViolation(Check.getCallerString()
				        + String.format("Condition evaluated to false. Violation: %s",
				                        String.format(formatString, arguments).toString()));
			}
		}
	}
	
	/**
	 * Gets the caller string.
	 *
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
	 * Not null.
	 *
	 * @param object the object
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notNull(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (object == null) {
				throw new CommonNotNullViolation(Check.getCallerString()
				        + String.format("Argument should not be (null). Violation: %s",
				                        String.format(formatString, arguments).toString()));
			}
		}
	}
}
