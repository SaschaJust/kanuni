/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.lang.reflect.Array;
import java.util.Arrays;

import net.ownhero.dev.kanuni.exceptions.CheckViolation;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class ArrayCheck {
	
	/**
	 * @param array
	 * @param element
	 * @return
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (array == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is null, but should be a none empty array . Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is not an array, but should be an non empty array. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (element == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Element is null, but should be a valid element of the array. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!element.getClass().equals(array.getClass().getComponentType())) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Element type differs from array component type. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!contains(array, element)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array does not contain element `%s`. Violation: %s", element,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param array
	 * @param message
	 */
	public static final void empty(final Object array,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (array == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is null, but should be an empty array . Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is not an array, but should be an empty array. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (Array.getLength(array) != 0) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array is not empty. Violation: %s", String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (array == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is null, but should be an array with maximum size %s. Violation: %s",
				                        length, String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Argument is not an array, but should be an array with maximum size %s. Violation: %s",
				                                                 length, String.format(formatString, arguments)));
			}
			
			if (Array.getLength(array) > length) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array exceeds maximum length of %s: %s. Violation: %s", length,
				                        Array.getLength(array), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			
			if (array == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is null, but should be an array with minimum size %s. Violation: %s",
				                        length, String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Argument is not an array, but should be an array with minimum size %s. Violation: %s",
				                                                 length, String.format(formatString, arguments)));
			}
			
			if (Array.getLength(array) < length) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array exceeds minimum length of %s: %s. Violation: %s", length,
				                        Array.getLength(array), String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param array
	 * @param formatString
	 * @param arguments
	 */
	public static final void noneNull(final Object array,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniClassloader.isAssertionsEnabled()) {
			
			if (array == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Argument is null, but should be an array without null elements. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Argument is not an array, but should be an array without null elements. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (CollectionUtils.countMatches(Arrays.asList(array),
			                                 Check.noneNullPredicate.setMessage(formatString, arguments)) != 0) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Recursive search found null element in array of type %s. Violation: %s",
				                        array.getClass().getComponentType().getName(),
				                        String.format(formatString, arguments)));
				
			}
		}
	}
	
	/**
	 * @param array
	 * @param message
	 */
	public static final void notEmpty(final Object array,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniClassloader.isAssertionsEnabled()) {
			
			if (array == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is null, but should be a none empty array . Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is not an array, but should be a none empty array. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (Array.getLength(array) == 0) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array is empty. Violation: %s", String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			
			if (firstArray == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Arguments should be arrays of same length, but first argument is null. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (secondArray == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Arguments should be arrays of same length, but second argument is null. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (!firstArray.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Arguments should be arrays of same length, but first argument is not an array. Type: %s. Violation: %s",
				                                                 firstArray.getClass().getName(),
				                                                 String.format(formatString, arguments)));
			}
			
			if (!secondArray.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Arguments should be arrays of same length, but second argument is not an array. Type: %s. Violation: %s",
				                                                 secondArray.getClass().getName(),
				                                                 String.format(formatString, arguments)));
			}
			
			if (Array.getLength(firstArray) != Array.getLength(secondArray)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Arrays do not have the same length: %s vs. %s. Violation: %s",
				                        Array.getLength(firstArray), Array.getLength(secondArray),
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			
			if (array == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Argument is null, but should be an array with maximum size %s. Violation: %s",
				                        length, String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Argument is not an array, but should be an array with maximum size %s. Violation: %s",
				                                                 length, String.format(formatString, arguments)));
			}
			
			if (Array.getLength(array) != length) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array does not have required size of %s: %s. Violation: %s", length,
				                        Array.getLength(array), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			
			if (array == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Index has to be in bounds of given array, but given array is null. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (!array.getClass().isArray()) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Index has to be in bounds of given array, but given argument is not an array. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (index < 0) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array index negative: %s. Violation: %s", index,
				                        String.format(formatString, arguments)));
			}
			
			if (Array.getLength(array) <= index) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Array index not in bounds (0..%s): %s. Violation: %s", Array.getLength(array),
				                        index, String.format(formatString, arguments)));
				
			}
		}
		
	}
	
}
