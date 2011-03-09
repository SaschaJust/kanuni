package net.ownhero.dev.kanuni.conditions;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.Predicate;

/**
 * This class provides in place specification checks using assertions. Call
 * Condition.XYZ(...) to ensure certain conditions. All methods support a
 * specification string or a format string followed by several objects. Never
 * make calls to any methods of the objects under suspect since this will
 * prevent the JIT compiler to ignore the method calls to {@link Condition} if
 * assertions are disabled.
 * 
 * If you require further checks extend this class correspondingly.
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public final class Condition {
	
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
		 * @param message
		 */
		public NoneNullPredicate setMessage(final String message) {
			this.string = message;
			return this;
		}
		
		/**
		 * @param formatString
		 * @param arguments
		 * @return the current instance of {@link NoneNullPredicate}.
		 */
		public NoneNullPredicate setMessage(final String formatString,
		                                    final Object... arguments) {
			this.string = String.format(formatString, arguments).toString();
			return this;
		}
		
	}
	
	static final NoneNullPredicate noneNullPredicate = new Condition.NoneNullPredicate();
	
	/**
	 * @param condition
	 * @param formatString
	 * @param arguments
	 */
	public static final void check(final boolean condition,
	                               final String formatString,
	                               final Object... arguments) {
		assert condition : getCallerString()
		        + String.format("Condition evaluated to false. Violation: %s", String.format(formatString, arguments)
		                                                                             .toString());
	}
	
	/**
	 * @return a string representing the line of code the condition was
	 *         triggered from.
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
	public static final void isBoolean(final Object object,
	                                   final String formatString,
	                                   final Object... arguments) {
		assert (object != null) && (object instanceof Boolean) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isByte(final Object object,
	                                final String formatString,
	                                final Object... arguments) {
		assert (object != null) && (object instanceof Byte) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isCharacter(final Object object,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (object != null) && (object instanceof Character) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isDouble(final Object object,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (object != null) && (object instanceof Double) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isFloat(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert (object != null) && (object instanceof Float) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isInteger(final Object object,
	                                   final String formatString,
	                                   final Object... arguments) {
		assert (object != null) && (object instanceof Integer) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isLong(final Object object,
	                                final String formatString,
	                                final Object... arguments) {
		assert (object != null) && (object instanceof Long) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isNull(final Object object,
	                                final String formatString,
	                                final Object... arguments) {
		assert object == null : getCallerString()
		        + String.format("Argument MUST be (null). Violation: %s", String.format(formatString, arguments)
		                                                                        .toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void isShort(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert (object != null) && (object instanceof Double) : getCallerString()
		        + String.format("Argument should be of type Integer. Violation: %s",
		                        String.format(formatString, arguments).toString());
	}
	
	/**
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void notNull(final Object object,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert object != null : getCallerString()
		        + String.format("Argument should not be (null). Violation: %s", String.format(formatString, arguments)
		                                                                              .toString());
	}
	
}
