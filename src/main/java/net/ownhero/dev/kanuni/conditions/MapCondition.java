/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class MapCondition {
	
	/**
	 * @param map
	 * @param key
	 * @param formatString
	 * @param arguments
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
	 * @param map
	 * @param value
	 * @param formatString
	 * @param arguments
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
	 * @param map
	 * @param length
	 * @param formatString
	 * @param arguments
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
	 * @param map
	 * @param length
	 * @param formatString
	 * @param arguments
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
	 * @param map
	 * @param formatString
	 * @param arguments
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
	 * @param firstMap
	 * @param secondMap
	 * @param formatString
	 * @param arguments
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
