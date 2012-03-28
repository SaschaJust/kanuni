/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.Map;

import net.ownhero.dev.kanuni.exceptions.violations.MapContainsKeyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.MapContainsValueViolation;
import net.ownhero.dev.kanuni.exceptions.violations.MapMaxSizeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.MapMinSizeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.MapNoneNullViolation;
import net.ownhero.dev.kanuni.exceptions.violations.MapSameSizeViolation;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.collections.CollectionUtils;

/**
 * The Class MapCheck.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class MapCheck {
	
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!map.containsKey(key)) {
				throw new MapContainsKeyViolation(Check.getCallerString()
				        + String.format("Map %s does not contain required key `%s`. Violation: %s", map, key,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!map.containsValue(value)) {
				throw new MapContainsValueViolation(Check.getCallerString()
				        + String.format("Map %s does not contain required key `%s`. Violation: %s", map, value,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (map.size() > length) {
				throw new MapMaxSizeViolation(Check.getCallerString()
				        + String.format("Map exceeds max size of %s (actual size: %s). Violation: %s", length,
				                        map.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (map.size() < length) {
				
				throw new MapMinSizeViolation(Check.getCallerString()
				        + String.format("Map exceeds min size of %s (actual size: %s). Violation: %s", length,
				                        map.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((map == null)
			        || (CollectionUtils.countMatches(map.values(),
			                                         Check.noneNullPredicate.setMessage(formatString, arguments)) > 0)) {
				throw new MapNoneNullViolation(Check.getCallerString()
				        + String.format("Recursive search found null element. Violation: %s",
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (firstMap == null) {
				throw new MapSameSizeViolation(Check.getCallerString()
				        + String.format("First map is null. Violation: %s", String.format(formatString, arguments)));
			}
			
			if (secondMap == null) {
				throw new MapSameSizeViolation(Check.getCallerString()
				        + String.format("Second map is null. Violation: %s", String.format(formatString, arguments)));
			}
			
			if (CollectionUtils.size(firstMap) != CollectionUtils.size(secondMap)) {
				throw new MapSameSizeViolation(Check.getCallerString()
				        + String.format("Maps do not have same size (`%s` vs. `%s`). Violation: %s",
				                        CollectionUtils.size(firstMap), CollectionUtils.size(secondMap),
				                        String.format(formatString, arguments)));
			}
		}
	}
	
}
