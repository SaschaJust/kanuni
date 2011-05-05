/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class ArrayCondition {
	
	/**
	 * @param array
	 * @param element
	 * @return true if the element is contained in the array.
	 */
	private static final boolean contains(final Object array,
	                                      final Object element) {
		for (int i = 0; i < Array.getLength(array); ++i) {
			if (Array.get(array, i).equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param array
	 * @param element
	 * @param formatString
	 * @param arguments
	 */
	public static final void contains(final Object array,
	                                  final Object element,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be a none empty array . Violation: %s",
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be an non empty array. Violation: %s",
		                        String.format(formatString, arguments));
		assert element != null : Condition.getCallerString()
		        + String.format("Element is null, but should be a valid element of the array. Violation: %s",
		                        String.format(formatString, arguments));
		assert contains(array, element) : Condition.getCallerString()
		        + String.format("Container does not contain element `%s`. Violation: %s", element,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param array
	 * @param formatString
	 * @param arguments
	 */
	public static final void empty(final Object array,
	                               final String formatString,
	                               final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be an empty array . Violation: %s",
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be an empty array. Violation: %s",
		                        String.format(formatString, arguments));
		assert Array.getLength(array) == 0 : Condition.getCallerString()
		        + String.format("Container is not empty. Violation: %s", String.format(formatString, arguments));
	}
	
	/**
	 * @param array
	 * @param length
	 * @param formatString
	 * @param arguments
	 */
	public static final void maxSize(final Object array,
	                                 final Integer length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be an array with maximum size %s. Violation: %s", length,
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be an array with maximum size %s. Violation: %s",
		                        length, String.format(formatString, arguments));
		assert Array.getLength(array) <= length : Condition.getCallerString()
		        + String.format("Container exceeds maximum length of %s: %s. Violation: %s", length,
		                        Array.getLength(array), String.format(formatString, arguments));
	}
	
	/**
	 * @param array
	 * @param length
	 * @param formatString
	 * @param arguments
	 */
	public static final void minSize(final Object array,
	                                 final Integer length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be an array with minimum size %s. Violation: %s", length,
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be an array with minimum size %s. Violation: %s",
		                        length, String.format(formatString, arguments));
		assert Array.getLength(array) >= length : Condition.getCallerString()
		        + String.format("Container exceeds minimum length of %s: %s. Violation: %s", length,
		                        Array.getLength(array), String.format(formatString, arguments));
	}
	
	/**
	 * @param array
	 * @param formatString
	 * @param arguments
	 */
	public static final void noneNull(final Object array,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be an array without null elements. Violation: %s",
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be an array without null elements. Violation: %s",
		                        String.format(formatString, arguments));
		assert (CollectionUtils.countMatches(Arrays.asList(array),
		                                     Condition.noneNullPredicate.setMessage(formatString, arguments)) == 0) : Condition.getCallerString()
		        + String.format("Recursive search found null element in array of type %s. Violation: %s",
		                        array.getClass().getComponentType().getName(), String.format(formatString, arguments));
	}
	
	/**
	 * @param array
	 * @param formatString
	 * @param arguments
	 */
	public static final void notEmpty(final Object array,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be a none empty array . Violation: %s",
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be a none empty array. Violation: %s",
		                        String.format(formatString, arguments));
		assert Array.getLength(array) > 0 : Condition.getCallerString()
		        + String.format("Container is empty. Violation: %s", String.format(formatString, arguments));
	}
	
	/**
	 * @param firstArray
	 * @param secondArray
	 * @param formatString
	 * @param arguments
	 */
	public static final void sameSize(final Object firstArray,
	                                  final Object secondArray,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert firstArray != null : Condition.getCallerString()
		        + String.format("Arguments should be arrays of same length, but first argument is null. Violation: %s",
		                        String.format(formatString, arguments));
		assert secondArray != null : Condition.getCallerString()
		        + String.format("Arguments should be arrays of same length, but second argument is null. Violation: %s",
		                        String.format(formatString, arguments));
		assert firstArray.getClass().isArray() : Condition.getCallerString()
		        + String.format("Arguments should be arrays of same length, but first argument is not an array. Type: %s. Violation: %s",
		                        firstArray.getClass().getName(), String.format(formatString, arguments));
		assert secondArray.getClass().isArray() : Condition.getCallerString()
		        + String.format("Arguments should be arrays of same length, but second argument is not an array. Type: %s. Violation: %s",
		                        secondArray.getClass().getName(), String.format(formatString, arguments));
		assert Array.getLength(firstArray) == Array.getLength(secondArray) : Condition.getCallerString()
		        + String.format("Arrays do not have the same length: %s vs. %s. Violation: %s",
		                        Array.getLength(firstArray), Array.getLength(secondArray),
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param array
	 * @param length
	 * @param formatString
	 * @param arguments
	 */
	public static final void size(final Object array,
	                              final Integer length,
	                              final String formatString,
	                              final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Argument is null, but should be an array with maximum size %s. Violation: %s", length,
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Argument is not an array, but should be an array with maximum size %s. Violation: %s",
		                        length, String.format(formatString, arguments));
		assert Array.getLength(array) == length : Condition.getCallerString()
		        + String.format("Container does not have required size of %s: %s. Violation: %s", length,
		                        Array.getLength(array), String.format(formatString, arguments));
	}
	
	/**
	 * @param index
	 * @param array
	 * @param formatString
	 * @param arguments
	 */
	public static final void validIndex(final Object array,
	                                    final Integer index,
	                                    final String formatString,
	                                    final Object... arguments) {
		assert array != null : Condition.getCallerString()
		        + String.format("Index has to be in bounds of given array, but given array is null. Violation: %s",
		                        String.format(formatString, arguments));
		assert array.getClass().isArray() : Condition.getCallerString()
		        + String.format("Index has to be in bounds of given array, but given argument is not an array. Violation: %s",
		                        String.format(formatString, arguments));
		assert index >= 0 : Condition.getCallerString()
		        + String.format("Container index negative: %s. Violation: %s", index,
		                        String.format(formatString, arguments));
		assert Array.getLength(array) > index : Condition.getCallerString()
		        + String.format("Container index not in bounds (0..%s): %s. Violation: %s", Array.getLength(array),
		                        index, String.format(formatString, arguments));
	}
	
}
