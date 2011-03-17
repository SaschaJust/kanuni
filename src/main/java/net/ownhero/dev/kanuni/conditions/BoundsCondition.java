/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import org.apache.commons.lang.math.NumberRange;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class BoundsCondition {
	
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
		assert value != null : Condition.getCallerString()
		        + String.format("Character might not be null for range check. Violation: %s", formatString);
		assert min != null : Condition.getCallerString()
		        + String.format("Minimum value might not be null for range check. Violation: %s", formatString);
		assert max != null : Condition.getCallerString()
		        + String.format("Maximum value might not be null for range check. Violation: %s", formatString);
		assert value >= min : Condition.getCallerString()
		        + String.format("Character `%s` does not fit minimum range condition (min: `%s`). Violation: %s",
		                        value, min, formatString);
		assert value <= max : Condition.getCallerString()
		        + String.format("Character `%s` does not fit maximum range condition (min: `%s`). Violation: %s",
		                        value, max, formatString);
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
		assert value != null : Condition.getCallerString()
		        + String.format("Number might not be null for range check. Violation: %s", formatString);
		assert min != null : Condition.getCallerString()
		        + String.format("Minimum value might not be null for range check. Violation: %s", formatString);
		assert max != null : Condition.getCallerString()
		        + String.format("Maximum value might not be null for range check. Violation: %s", formatString);
		assert new NumberRange(min, max).containsNumber(value) : Condition.getCallerString()
		        + String.format("Argument `%s` is not in specified number range (%s..%s). Violation: %s", value, min,
		                        max, String.format(formatString, arguments));
	}
}
