/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import net.ownhero.dev.kanuni.exceptions.violations.BoundsRangeViolation;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.lang.math.NumberRange;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class BoundsCheck {
	
	/**
	 * @param value
	 * @param min
	 * @param max
	 * @param formatString
	 * @param arguments
	 */
	public static final void range(final Character value,
	                               final Character min,
	                               final Character max,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (value == null) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Character might not be null for range check. Violation: %s", formatString));
			}
			
			if (min == null) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Minimum value might not be null for range check. Violation: %s", formatString));
			}
			
			if (max == null) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Maximum value might not be null for range check. Violation: %s", formatString));
			}
			
			if (value < min) {
				throw new BoundsRangeViolation(
				                               Check.getCallerString()
				                                       + String.format("Character `%s` does not fit minimum range condition (min: `%s`). Violation: %s",
				                                                       value, min, formatString));
			}
			
			if (value > max) {
				throw new BoundsRangeViolation(
				                               Check.getCallerString()
				                                       + String.format("Character `%s` does not fit maximum range condition (min: `%s`). Violation: %s",
				                                                       value, max, formatString));
			}
		}
	}
	
	/**
	 * @param value
	 * @param min
	 * @param max
	 * @param formatString
	 * @param arguments
	 */
	public static final void range(final Number value,
	                               final Number min,
	                               final Number max,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (value == null) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Number might not be null for range check. Violation: %s", formatString));
			}
			
			if (min == null) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Minimum value might not be null for range check. Violation: %s", formatString));
			}
			
			if (max == null) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Maximum value might not be null for range check. Violation: %s", formatString));
			}
			
			if (!new NumberRange(min, max).containsNumber(value)) {
				throw new BoundsRangeViolation(Check.getCallerString()
				        + String.format("Argument `%s` is not in specified number range (%s..%s). Violation: %s",
				                        value, min, max, String.format(formatString, arguments)));
			}
			
		}
	}
}
