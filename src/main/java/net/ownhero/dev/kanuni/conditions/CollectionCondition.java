/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class CollectionCondition {
	
	/**
	 * @param collection
	 * @param object
	 * @param formatString
	 * @param arguments
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
	 * @param collection
	 * @param innerCollection
	 * @param formatString
	 * @param arguments
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
	 * @param collection
	 * @param innerCollection
	 * @param formatString
	 * @param arguments
	 */
	public static final void containsAny(final Collection<?> collection,
	                                     final Collection<?> innerCollection,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert CollectionUtils.containsAny(collection, innerCollection) : Condition.getCallerString()
		+ String.format("Collection does not contain any of the objects in `%s`. Violataion: %s",
		                innerCollection, String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void empty(final Collection<?> collection,
	                               final String formatString,
	                               final Object... arguments) {
		assert collection.isEmpty() : Condition.getCallerString()
		+ String.format("Collection is not empty. Violataion: %s", String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param length
	 * @param formatString
	 * @param arguments
	 */
	public static final void maxSize(final Collection<?> collection,
	                                 final int length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert collection.size() <= length : Condition.getCallerString()
		+ String.format("Collection exceeds max size of %s (actual size: %s). Violataion: %s", length,
		                collection.size(), String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param length
	 * @param formatString
	 * @param arguments
	 */
	public static final void minSize(final Collection<?> collection,
	                                 final int length,
	                                 final String formatString,
	                                 final Object... arguments) {
		assert collection.size() >= length : Condition.getCallerString()
		+ String.format("Collection exceeds min size of %s (actual size: %s). Violataion: %s", length,
		                collection.size(), String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void noneNull(final Collection<?> collection,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert (collection != null)
		&& (CollectionUtils.countMatches(collection,
		                                 Condition.noneNullPredicate.setMessage(formatString, arguments)) == 0) : Condition.getCallerString()
		                                 + String.format("Recursive search found null element. Violataion: %s",
		                                                 String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void notAllNull(final Collection<?> collection,
	                                    final String formatString,
	                                    final Object... arguments) {
		assert (collection != null) && (CollectionUtils.countMatches(collection, new Predicate() {
			
			public boolean evaluate(final Object object) {
				return object == null;
			}
		}) < collection.size()) : Condition.getCallerString()
		+ String.format("All elements (%s) of the collection are (null). Violataion: %s", collection.size(),
		                String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param object
	 * @param formatString
	 * @param arguments
	 */
	public static final void notContains(final Collection<?> collection,
	                                     final Object object,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert !collection.contains(object) : Condition.getCallerString()
		+ String.format("Collection contains object `%s`. Violataion: %s", object,
		                String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param innerCollection
	 * @param formatString
	 * @param arguments
	 */
	public static final void notContainsAll(final Collection<?> collection,
	                                        final Collection<?> innerCollection,
	                                        final String formatString,
	                                        final Object... arguments) {
		assert !collection.containsAll(innerCollection) : Condition.getCallerString()
		+ String.format("Collection contains all objects in `%s`. Violataion: %s", innerCollection,
		                String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param innerCollection
	 * @param formatString
	 * @param arguments
	 */
	public static final void notContainsAny(final Collection<?> collection,
	                                        final Collection<?> innerCollection,
	                                        final String formatString,
	                                        final Object... arguments) {
		assert !CollectionUtils.containsAny(collection, innerCollection) : Condition.getCallerString()
		+ String.format("Collection contains any of the objects in `%s`. Violataion: %s", innerCollection,
		                String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void notEmpty(final Collection<?> collection,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert !collection.isEmpty() : Condition.getCallerString()
		+ String.format("Collection is empty. Violataion: %s", String.format(formatString, arguments));
	}
	
	/**
	 * @param firstCollection
	 * @param secondCollection
	 * @param formatString
	 * @param arguments
	 */
	public static final void sameSize(final Collection<?> firstCollection,
	                                  final Collection<?> secondCollection,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert CollectionUtils.size(firstCollection) == CollectionUtils.size(secondCollection) : Condition.getCallerString()
		+ String.format("Collections do not have same size (`%s` vs. `%s`). Violataion: %s",
		                CollectionUtils.size(firstCollection), CollectionUtils.size(secondCollection),
		                String.format(formatString, arguments));
	}
	
	/**
	 * @param collection
	 * @param length
	 * @param formatString
	 * @param arguments
	 */
	public static final void size(final Collection<?> collection,
	                              final int length,
	                              final String formatString,
	                              final Object... arguments) {
		assert collection.size() == length : Condition.getCallerString()
		+ String.format("Collection doesn't have size of %s (actual size: %s). Violataion: %s", length,
		                collection.size(), String.format(formatString, arguments));
	}
	
	
	/**
	 * @param collection
	 * @param index
	 * @param formatString
	 * @param arguments
	 */
	public static final void validIndex(final Collection<?> collection,
	                                    final int index,
	                                    final String formatString,
	                                    final Object... arguments) {
		assert index < collection.size() : Condition.getCallerString()
		+ String.format("Collection index %s invalid for collection size %s. Violataion: %s", index,
		                collection.size(), String.format(formatString, arguments));
		assert index >= 0 : Condition.getCallerString()
		+ String.format("Collection index %s invalid for collection size %s. Violataion: %s", index,
		                collection.size(), String.format(formatString, arguments));
	}
	
}
