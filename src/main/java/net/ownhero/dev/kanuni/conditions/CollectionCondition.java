/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.lang.reflect.Array;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * The Class CollectionCondition.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public final class CollectionCondition {
	
	/**
	 * Contains.
	 * 
	 * @param collection
	 *            the collection
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void contains(final Collection<?> collection,
	                                  final Object object,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert collection.contains(object) : Condition.getCallerString()
		        + String.format("Collection does not contain object `%s`. Violation: %s", object,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Contains all.
	 * 
	 * @param collection
	 *            the collection
	 * @param innerCollection
	 *            the inner collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void containsAll(final Collection<?> collection,
	                                     final Collection<?> innerCollection,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert collection.containsAll(innerCollection) : Condition.getCallerString()
		        + String.format("Collection does not contain all objects in `%s`. Violation: %s", innerCollection,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Contains any.
	 * 
	 * @param collection
	 *            the collection
	 * @param innerCollection
	 *            the inner collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void containsAny(final Collection<?> collection,
	                                     final Collection<?> innerCollection,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert CollectionUtils.containsAny(collection, innerCollection) : Condition.getCallerString()
		        + String.format("Collection does not contain any of the objects in `%s`. Violation: %s",
		                        innerCollection, String.format(formatString, arguments));
	}
	
	/**
	 * Empty.
	 * 
	 * @param collection
	 *            the collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void empty(final Collection<?> collection,
	                               final String formatString,
	                               final Object... arguments) {
		assert collection.isEmpty() : Condition.getCallerString()
		        + String.format("Collection is not empty. Violation: %s", String.format(formatString, arguments));
	}
	
	/**
	 * Max size.
	 * 
	 * @param collection
	 *            the collection
	 * @param length
	 *            the length
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void maxSize(final Collection<?> collection,
	                                 final int length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert collection.size() <= length : Condition.getCallerString()
		        + String.format("Collection exceeds max size of %s (actual size: %s). Violation: %s", length,
		                        collection.size(), String.format(formatString, arguments));
	}
	
	/**
	 * Min size.
	 * 
	 * @param collection
	 *            the collection
	 * @param length
	 *            the length
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void minSize(final Collection<?> collection,
	                                 final int length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert collection.size() >= length : Condition.getCallerString()
		        + String.format("Collection exceeds min size of %s (actual size: %s). Violation: %s", length,
		                        collection.size(), String.format(formatString, arguments));
	}
	
	/**
	 * None null.
	 * 
	 * @param collection
	 *            the collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void noneNull(final Collection<?> collection,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (collection != null)
		        && (CollectionUtils.countMatches(collection,
		                                         Condition.noneNullPredicate.setMessage(formatString, arguments)) == 0) : Condition.getCallerString()
		        + String.format("Recursive search found null element. Violation: %s",
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not all null.
	 * 
	 * @param collection
	 *            the collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void notAllNull(final Collection<?> collection,
	                                    final String formatString,
	                                    final Object... arguments) {
		assert (collection != null) : Condition.getCallerString()
		        + String.format("The collection itself is (null). Violation: %s",
		                        String.format(formatString, arguments));
		
		assert (CollectionUtils.countMatches(collection, new Predicate() {
			
			public boolean evaluate(final Object object) {
				return object == null;
			}
		}) < collection.size()) : Condition.getCallerString()
		        + String.format("All elements (%s) of the collection are (null). Violation: %s", collection.size(),
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not contains.
	 * 
	 * @param collection
	 *            the collection
	 * @param object
	 *            the object
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void notContains(final Collection<?> collection,
	                                     final Object object,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert !collection.contains(object) : Condition.getCallerString()
		        + String.format("Collection contains object `%s`. Violation: %s", object,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not contains all.
	 * 
	 * @param collection
	 *            the collection
	 * @param innerCollection
	 *            the inner collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void notContainsAll(final Collection<?> collection,
	                                        final Collection<?> innerCollection,
	                                        final String formatString,
	                                        final Object... arguments) {
		assert !collection.containsAll(innerCollection) : Condition.getCallerString()
		        + String.format("Collection contains all objects in `%s`. Violation: %s", innerCollection,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not contains any.
	 * 
	 * @param collection
	 *            the collection
	 * @param innerCollection
	 *            the inner collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void notContainsAny(final Collection<?> collection,
	                                        final Collection<?> innerCollection,
	                                        final String formatString,
	                                        final Object... arguments) {
		assert !CollectionUtils.containsAny(collection, innerCollection) : Condition.getCallerString()
		        + String.format("Collection contains any of the objects in `%s`. Violation: %s", innerCollection,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Not empty.
	 * 
	 * @param collection
	 *            the collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void notEmpty(final Collection<?> collection,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert !collection.isEmpty() : Condition.getCallerString()
		        + String.format("Collection is empty. Violation: %s", String.format(formatString, arguments));
	}
	
	/**
	 * Same size.
	 * 
	 * @param firstCollection
	 *            the first collection
	 * @param secondCollection
	 *            the second collection
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void sameSize(final Collection<?> firstCollection,
	                                  final Collection<?> secondCollection,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert CollectionUtils.size(firstCollection) == CollectionUtils.size(secondCollection) : Condition.getCallerString()
		        + String.format("Collections do not have same size (`%s` vs. `%s`). Violation: %s",
		                        CollectionUtils.size(firstCollection), CollectionUtils.size(secondCollection),
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Same size.
	 * 
	 * @param collection
	 *            the collection
	 * @param array
	 *            the array
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void sameSize(final Collection<?> collection,
	                                  final Object array,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert CollectionUtils.size(collection) == Array.getLength(array) : Condition.getCallerString()
		        + String.format("Collection does not have the same size as the array (`%s` vs. `%s`). Violation: %s",
		                        CollectionUtils.size(collection), Array.getLength(array),
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Size.
	 * 
	 * @param collection
	 *            the collection
	 * @param length
	 *            the length
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void size(final Collection<?> collection,
	                              final int length,
	                              final String formatString,
	                              final Object... arguments) {
		assert collection.size() == length : Condition.getCallerString()
		        + String.format("Collection doesn't have size of %s (actual size: %s). Violation: %s", length,
		                        collection.size(), String.format(formatString, arguments));
	}
	
	/**
	 * Valid index.
	 * 
	 * @param collection
	 *            the collection
	 * @param index
	 *            the index
	 * @param formatString
	 *            the format string
	 * @param arguments
	 *            the arguments
	 */
	public static final void validIndex(final Collection<?> collection,
	                                    final int index,
	                                    final String formatString,
	                                    final Object... arguments) {
		assert index < collection.size() : Condition.getCallerString()
		        + String.format("Collection index %s invalid for collection size %s. Violation: %s", index,
		                        collection.size(), String.format(formatString, arguments));
		assert index >= 0 : Condition.getCallerString()
		        + String.format("Collection index %s invalid for collection size %s. Violation: %s", index,
		                        collection.size(), String.format(formatString, arguments));
	}
	
}
