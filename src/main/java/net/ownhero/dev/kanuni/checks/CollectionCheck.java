/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.Collection;

import net.ownhero.dev.kanuni.exceptions.violations.CollectionContainsAllViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionContainsAnyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionContainsViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionEmptyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionMaxSizeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionMinSizeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionNoneNullViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionNotAllNullViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionNotContainsAllViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionNotContainsAnyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionNotContainsViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionNotEmptyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionSameSizeViolation;
import net.ownhero.dev.kanuni.exceptions.violations.CollectionSizeViolation;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * The Class CollectionCheck.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class CollectionCheck {
	
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!collection.contains(object)) {
				throw new CollectionContainsViolation(Check.getCallerString()
				        + String.format("Collection does not contain object `%s`. Violation: %s", object,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!collection.containsAll(innerCollection)) {
				throw new CollectionContainsAllViolation(Check.getCallerString()
				        + String.format("Collection does not contain all objects in `%s`. Violation: %s",
				                        innerCollection, String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!CollectionUtils.containsAny(collection, innerCollection)) {
				throw new CollectionContainsAnyViolation(Check.getCallerString()
				        + String.format("Collection does not contain any of the objects in `%s`. Violataion: %s",
				                        innerCollection, String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!collection.isEmpty()) {
				throw new CollectionEmptyViolation(Check.getCallerString()
				        + String.format("Collection is not empty. Violataion: %s",
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (collection.size() > length) {
				throw new CollectionMaxSizeViolation(Check.getCallerString()
				        + String.format("Collection exceeds max size of %s (actual size: %s). Violataion: %s", length,
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (collection.size() < length) {
				throw new CollectionMinSizeViolation(Check.getCallerString()
				        + String.format("Collection exceeds min size of %s (actual size: %s). Violataion: %s", length,
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((collection == null)
			        || (CollectionUtils.countMatches(collection,
			                                         Check.noneNullPredicate.setMessage(formatString, arguments)) != 0)) {
				throw new CollectionNoneNullViolation(Check.getCallerString()
				        + String.format("Recursive search found null element. Violataion: %s",
				                        String.format(formatString, arguments)));
			}
		}
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
		
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (collection == null) {
				throw new CollectionNotAllNullViolation(Check.getCallerString()
				        + String.format("The collection itself is (null). Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((CollectionUtils.countMatches(collection, new Predicate() {
				
				@Override
				public boolean evaluate(final Object object) {
					return object == null;
				}
			}) == collection.size())) {
				throw new CollectionNotAllNullViolation(Check.getCallerString()
				        + String.format("All elements (%s) of the collection are (null). Violation: %s",
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (collection.contains(object)) {
				throw new CollectionNotContainsViolation(Check.getCallerString()
				        + String.format("Collection contains object `%s`. Violataion: %s", object,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (collection.containsAll(innerCollection)) {
				throw new CollectionNotContainsAllViolation(Check.getCallerString()
				        + String.format("Collection contains all objects in `%s`. Violataion: %s", innerCollection,
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!CollectionUtils.containsAny(collection, innerCollection)) {
				throw new CollectionNotContainsAnyViolation(Check.getCallerString()
				        + String.format("Collection contains any of the objects in `%s`. Violataion: %s",
				                        innerCollection, String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (!collection.isEmpty()) {
				throw new CollectionNotEmptyViolation(Check.getCallerString()
				        + String.format("Collection is empty. Violataion: %s", String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (CollectionUtils.size(firstCollection) != CollectionUtils.size(secondCollection)) {
				throw new CollectionSameSizeViolation(Check.getCallerString()
				        + String.format("Collections do not have same size (`%s` vs. `%s`). Violataion: %s",
				                        CollectionUtils.size(firstCollection), CollectionUtils.size(secondCollection),
				                        String.format(formatString, arguments)));
			}
		}
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
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (collection.size() != length) {
				throw new CollectionSizeViolation(Check.getCallerString()
				        + String.format("Collection doesn't have size of %s (actual size: %s). Violataion: %s", length,
				                        collection.size(), String.format(formatString, arguments)));
			}
		}
	}
	
}
