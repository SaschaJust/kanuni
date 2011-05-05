/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import net.ownhero.dev.kanuni.exceptions.violations.CompareEqualsViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareGreaterOrEqualViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareGreaterViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareLessOrEqualViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareLessViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareNegativeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareNotEqualsViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareNotNegativeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CompareNotPositiveViolation;
import net.ownhero.dev.kanuni.exceptions.violations.ComparePositiveViolation;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class CompareCheck {
	
	/**
	 * @param first
	 * @param second
	 * @param formatString
	 * @param arguments
	 */
	public static final void equals(final Object first,
	                                final Object second,
	                                final String formatString,
	                                final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((first == null) || (second == null) || !first.equals(second)) {
				throw new CompareEqualsViolation(
				                                 Check.getCallerString()
				                                         + String.format("First argument should be equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
				                                                         first, second,
				                                                         String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param <T>
	 * @param original
	 * @param compareTo
	 * @param formatString
	 * @param arguments
	 */
	public static final <T> void greater(final Comparable<T> original,
	                                     final T compareTo,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((original == null) || (compareTo == null) || (original.compareTo(compareTo) <= 0)) {
				throw new CompareGreaterViolation(
				                                  Check.getCallerString()
				                                          + String.format("First argument should be greater than the second argument, but got first `%s` vs second `%s`. Violation: %s",
				                                                          original, compareTo,
				                                                          String.format(formatString, arguments)
				                                                                .toString()));
			}
		}
	}
	
	/**
	 * @param <T>
	 * @param original
	 * @param compareTo
	 * @param formatString
	 * @param arguments
	 */
	public static final <T> void greaterOrEqual(final Comparable<T> original,
	                                            final T compareTo,
	                                            final String formatString,
	                                            final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((original == null) || (compareTo == null) || (original.compareTo(compareTo) < 0)) {
				throw new CompareGreaterOrEqualViolation(
				                                         Check.getCallerString()
				                                                 + String.format("First argument should be greater than or equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
				                                                                 original, compareTo,
				                                                                 String.format(formatString, arguments)
				                                                                       .toString()));
			}
		}
	}
	
	/**
	 * @param <T>
	 * @param original
	 * @param compareTo
	 * @param formatString
	 * @param arguments
	 */
	public static final <T> void less(final Comparable<T> original,
	                                  final T compareTo,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((original == null) || (compareTo == null) || (original.compareTo(compareTo) >= 0)) {
				throw new CompareLessViolation(
				                               Check.getCallerString()
				                                       + String.format("First argument should be less than the second argument, but got first `%s` vs second `%s`. Violation: %s",
				                                                       original, compareTo,
				                                                       String.format(formatString, arguments)
				                                                             .toString()));
			}
		}
	}
	
	/**
	 * @param <T>
	 * @param original
	 * @param compareTo
	 * @param formatString
	 * @param arguments
	 */
	public static final <T> void lessOrEqual(final Comparable<T> original,
	                                         final T compareTo,
	                                         final String formatString,
	                                         final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((original == null) || (compareTo == null) || (original.compareTo(compareTo) > 0)) {
				throw new CompareLessOrEqualViolation(
				                                      Check.getCallerString()
				                                              + String.format("First argument should be less than or equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
				                                                              original, compareTo,
				                                                              String.format(formatString, arguments)
				                                                                    .toString()));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void negative(final Double number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new CompareNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be negative, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) >= 0) {
				throw new CompareNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void negative(final Float number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new CompareNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be negative, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) >= 0) {
				throw new CompareNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void negative(final Integer number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0) >= 0)) {
				throw new CompareNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void negative(final Long number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0l) >= 0)) {
				throw new CompareNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param first
	 * @param second
	 * @param formatString
	 * @param arguments
	 */
	public static final void notEquals(final Object first,
	                                   final Object second,
	                                   final String formatString,
	                                   final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((first == null) || (second == null) || first.equals(second)) {
				throw new CompareNotEqualsViolation(
				                                    Check.getCallerString()
				                                            + String.format("First argument should be NOT equal to the second argument, but got first `%s` vs second `%s`. Violation: %s",
				                                                            first, second,
				                                                            String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notNegative(final Double number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new CompareNotNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be not negative, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) < 0) {
				throw new CompareNotNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notNegative(final Float number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new CompareNotNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be not negative, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) < 0) {
				throw new CompareNotNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notNegative(final Integer number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0) < 0)) {
				throw new CompareNotNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notNegative(final Long number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0l) < 0)) {
				throw new CompareNotNegativeViolation(Check.getCallerString()
				        + String.format("Number has to be not negative, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notPositive(final Double number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new CompareNotPositiveViolation(Check.getCallerString()
				        + String.format("Number has to be not positive, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) > 0) {
				throw new CompareNotPositiveViolation(Check.getCallerString()
				        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notPositive(final Float number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new CompareNotPositiveViolation(Check.getCallerString()
				        + String.format("Number has to be not positive, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) > 0) {
				throw new CompareNotPositiveViolation(Check.getCallerString()
				        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notPositive(final Integer number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0) > 0)) {
				throw new CompareNotPositiveViolation(Check.getCallerString()
				        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void notPositive(final Long number,
	                                     final String formatString,
	                                     final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0l) > 0)) {
				throw new CompareNotPositiveViolation(Check.getCallerString()
				        + String.format("Number has to be not positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void positive(final Double number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new ComparePositiveViolation(Check.getCallerString()
				        + String.format("Number has to be positive, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.DOUBLE_ZERO) <= 0) {
				throw new ComparePositiveViolation(Check.getCallerString()
				        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void positive(final Float number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (number == null) {
				throw new ComparePositiveViolation(Check.getCallerString()
				        + String.format("Number has to be positive, but got null. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
			
			if (NumberUtils.compare(number, NumberUtils.FLOAT_ZERO) <= 0) {
				throw new ComparePositiveViolation(Check.getCallerString()
				        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void positive(final Integer number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0) <= 0)) {
				throw new ComparePositiveViolation(Check.getCallerString()
				        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param number
	 * @param formatString
	 * @param arguments
	 */
	public static final void positive(final Long number,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((number == null) || (number.compareTo(0l) <= 0)) {
				throw new ComparePositiveViolation(Check.getCallerString()
				        + String.format("Number has to be positive, but got: %s. Violation: %s", number,
				                        String.format(formatString, arguments)));
			}
		}
	}
}
