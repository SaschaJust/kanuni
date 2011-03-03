/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.Collection;

import net.ownhero.dev.kanuni.exceptions.CheckViolation;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CollectionCheck {
	
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (!collection.contains(object)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection does not contain object `%s`. Violation: %s", object,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (!collection.containsAll(innerCollection)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection does not contain all objects in `%s`. Violation: %s",
				                        innerCollection, String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (!CollectionUtils.containsAny(collection, innerCollection)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection does not contain any of the objects in `%s`. Violataion: %s",
				                        innerCollection, String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void empty(final Collection<?> collection,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (!collection.isEmpty()) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection is not empty. Violataion: %s",
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (collection.size() > length) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection exceeds max size of %s (actual size: %s). Violataion: %s", length,
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (collection.size() < length) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection exceeds min size of %s (actual size: %s). Violataion: %s", length,
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void noneNull(final Collection<?> collection,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniClassloader.isAssertionsEnabled()) {
			if ((collection == null)
			        || (CollectionUtils.countMatches(collection,
			                                         Check.noneNullPredicate.setMessage(formatString, arguments)) != 0)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Recursive search found null element. Violataion: %s",
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void notAllNull(final Collection<?> collection,
	                                    final String formatString,
	                                    final Object... arguments) {
		
		if (KanuniClassloader.isAssertionsEnabled()) {
			if ((collection == null) || (CollectionUtils.countMatches(collection, new Predicate() {
				
				public boolean evaluate(final Object object) {
					return object == null;
				}
			}) == collection.size())) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("All elements (%s) of the collection are (null). Violation: %s",
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (collection.contains(object)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection contains object `%s`. Violataion: %s", object,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (collection.containsAll(innerCollection)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection contains all objects in `%s`. Violataion: %s", innerCollection,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (!CollectionUtils.containsAny(collection, innerCollection)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection contains any of the objects in `%s`. Violataion: %s",
				                        innerCollection, String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param collection
	 * @param formatString
	 * @param arguments
	 */
	public static final void notEmpty(final Collection<?> collection,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (!collection.isEmpty()) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collection is empty. Violataion: %s", String.format(formatString, arguments)));
			}
		}
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (CollectionUtils.size(firstCollection) != CollectionUtils.size(secondCollection)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Collections do not have same size (`%s` vs. `%s`). Violataion: %s",
				                        CollectionUtils.size(firstCollection), CollectionUtils.size(secondCollection),
				                        String.format(formatString, arguments)));
			}
		}
	}
	
}
