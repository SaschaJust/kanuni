/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Handles all comparison checks. Arguments are being checked to be not null.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class CompareCondition {
	
	/**
	 * Equals.
	 *
	 * @param first the first
	 * @param second the second
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void equals(final Object first,
	                                final Object second,
	                                final String formatString,
	                                final Object... arguments) {
		assert (first != null) && (second != null) && first.equals(second) : Condition.getCallerString()
		        + String.format("First argument should be equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
		                        first, second, String.format(formatString, arguments));
	}
	
	/**
	 * Greater.
	 *
	 * @param <T> the generic type
	 * @param original the original
	 * @param compareTo the compare to
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final <T> void greater(final Comparable<T> original,
	                                     final T compareTo,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (original != null) && (compareTo != null) && (original.compareTo(compareTo) > 0) : Condition.getCallerString()
		        + String.format("First argument should be greater than the second argument, but got first `%s` vs second `%s`. Violation: %s",
		                        original, compareTo, String.format(formatString, arguments).toString());
	}
	
	/**
	 * Greater or equal.
	 *
	 * @param <T> the generic type
	 * @param original the original
	 * @param compareTo the compare to
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final <T> void greaterOrEqual(final Comparable<T> original,
	                                            final T compareTo,
	                                            final String formatString,
	                                            final Object... arguments) {
		assert (original != null) && (compareTo != null) && (original.compareTo(compareTo) >= 0) : Condition.getCallerString()
		        + String.format("First argument should be greater than or equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
		                        original, compareTo, String.format(formatString, arguments).toString());
	}
	
	/**
	 * Less.
	 *
	 * @param <T> the generic type
	 * @param original the original
	 * @param compareTo the compare to
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final <T> void less(final Comparable<T> original,
	                                  final T compareTo,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (original != null) && (compareTo != null) && (original.compareTo(compareTo) < 0) : Condition.getCallerString()
		        + String.format("First argument should be less than the second argument, but got first `%s` vs second `%s`. Violation: %s",
		                        original, compareTo, String.format(formatString, arguments).toString());
	}
	
	/**
	 * Less or equal.
	 *
	 * @param <T> the generic type
	 * @param original the original
	 * @param compareTo the compare to
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final <T> void lessOrEqual(final Comparable<T> original,
	                                         final T compareTo,
	                                         final String formatString,
	                                         final Object... arguments) {
		assert (original != null) && (compareTo != null) && (original.compareTo(compareTo) <= 0) : Condition.getCallerString()
		        + String.format("First argument should be less than or equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
		                        original, compareTo, String.format(formatString, arguments).toString());
	}
	
	/**
	 * Negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void negative(final Double number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be negative, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) < 0 : Condition.getCallerString()
		        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void negative(final Float number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be negative, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) < 0 : Condition.getCallerString()
		        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void negative(final Integer number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (number != null) && (number.compareTo(0) < 0) : Condition.getCallerString()
		        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void negative(final Long number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (number != null) && (number.compareTo(0l) < 0) : Condition.getCallerString()
		        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not equals.
	 *
	 * @param first the first
	 * @param second the second
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notEquals(final Object first,
	                                   final Object second,
	                                   final String formatString,
	                                   final Object... arguments) {
		assert (first != null) && (second != null) && !first.equals(second) : Condition.getCallerString()
		        + String.format("First argument should be NOT equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
		                        first, second, String.format(formatString, arguments));
	}
	
	/**
	 * Not negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notNegative(final Double number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be not negative, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) >= 0 : Condition.getCallerString()
		        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notNegative(final Float number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be not negative, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) >= 0 : Condition.getCallerString()
		        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notNegative(final Integer number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (number != null) && (number.compareTo(0) >= 0) : Condition.getCallerString()
		        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not negative.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notNegative(final Long number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (number != null) && (number.compareTo(0l) >= 0) : Condition.getCallerString()
		        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notPositive(final Double number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be not positive, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) <= 0 : Condition.getCallerString()
		        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notPositive(final Float number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be not positive, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) <= 0 : Condition.getCallerString()
		        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notPositive(final Integer number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (number != null) && (number.compareTo(0) <= 0) : Condition.getCallerString()
		        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notPositive(final Long number,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert (number != null) && (number.compareTo(0l) <= 0) : Condition.getCallerString()
		        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void positive(final Double number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be positive, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) > 0 : Condition.getCallerString()
		        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void positive(final Float number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert number != null : Condition.getCallerString()
		        + String.format("Number has to be positive, but got null. Violation: %s", number,
		                        String.format(formatString, arguments));
		assert NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) > 0 : Condition.getCallerString()
		        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void positive(final Integer number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (number != null) && (number.compareTo(0) > 0) : Condition.getCallerString()
		        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Positive.
	 *
	 * @param number the number
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void positive(final Long number,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (number != null) && (number.compareTo(0l) > 0) : Condition.getCallerString()
		        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
		                        String.format(formatString, arguments));
	}
}
