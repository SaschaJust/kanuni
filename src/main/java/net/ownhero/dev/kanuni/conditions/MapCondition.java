/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * The Class MapCondition.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public final class MapCondition {
	
	/**
	 * Contains key.
	 *
	 * @param map the map
	 * @param key the key
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void containsKey(final Map<?, ?> map,
	                                     final Object key,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert map.containsKey(key) : Condition.getCallerString()
		        + String.format("Map %s does not contain required key `%s`. Violation: %s", map, key,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Contains value.
	 *
	 * @param map the map
	 * @param value the value
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void containsValue(final Map<?, ?> map,
	                                       final Object value,
	                                       final String formatString,
	                                       final Object... arguments) {
		assert map.containsValue(value) : Condition.getCallerString()
		        + String.format("Map %s does not contain required key `%s`. Violation: %s", map, value,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Max size.
	 *
	 * @param map the map
	 * @param length the length
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void maxSize(final Map<?, ?> map,
	                                 final int length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert map.size() <= length : Condition.getCallerString()
		        + String.format("Map exceeds max size of %s (actual size: %s). Violation: %s", length, map.size(),
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Min size.
	 *
	 * @param map the map
	 * @param length the length
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void minSize(final Map<?, ?> map,
	                                 final int length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert map.size() >= length : Condition.getCallerString()
		        + String.format("Map exceeds min size of %s (actual size: %s). Violation: %s", length, map.size(),
		                        String.format(formatString, arguments));
	}
	
	/**
	 * None null.
	 *
	 * @param map the map
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void noneNull(final Map<?, ?> map,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (map != null)
		        && (CollectionUtils.countMatches(map.values(),
		                                         Condition.noneNullPredicate.setMessage(formatString, arguments)) == 0) : Condition.getCallerString()
		        + String.format("Recursive search found null element. Violation: %s",
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not empty.
	 *
	 * @param map the map
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void notEmpty(final Map<?, ?> map,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert !map.isEmpty() : Condition.getCallerString()
		        + String.format("Recursive search found null element. Violation: %s",
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Same size.
	 *
	 * @param firstMap the first map
	 * @param secondMap the second map
	 * @param formatString the format string
	 * @param arguments the arguments
	 */
	public static final void sameSize(final Map<?, ?> firstMap,
	                                  final Map<?, ?> secondMap,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert firstMap != null : Condition.getCallerString()
		        + String.format("First map is null. Violation: %s", String.format(formatString, arguments));
		assert secondMap != null : Condition.getCallerString()
		        + String.format("Second map is null. Violation: %s", String.format(formatString, arguments));
		assert CollectionUtils.size(firstMap) == CollectionUtils.size(secondMap) : Condition.getCallerString()
		        + String.format("Maps do not have same size (`%s` vs. `%s`). Violation: %s",
		                        CollectionUtils.size(firstMap), CollectionUtils.size(secondMap),
		                        String.format(formatString, arguments));
	}
}
