/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.regex.Pattern;

import net.ownhero.dev.kanuni.exceptions.violations.StringAlphaViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringAlphanumViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringAsciiViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringDigitViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringEmptyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringIsByteViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringIsDoubleViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringIsFloatViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringIsIntegerViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringIsLongViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringIsShortViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringLengthViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringLowercaseViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringMatchesViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringMaxLengthViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringMinLengthViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringNotEmptyViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringNotMatchesViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringSameLengthViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringTrimmedViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringUppercaseViolation;
import net.ownhero.dev.kanuni.exceptions.violations.StringXdigitViolation;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;
import net.ownhero.dev.kanuni.utils.KanuniUtils;
import net.ownhero.dev.kanuni.utils.KanuniUtils.NumberType;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class StringCheck {
	
	/**
	 * Checks a given string to consist only of alphabetic characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void alpha(final String string,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isAlpha(string)) {
				throw new StringAlphaViolation(Check.getCallerString()
				        + String.format("Not an alphanum string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to consist only of Alphanum characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void alphanum(final String string,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isAlphanum(string)) {
				throw new StringAlphanumViolation(Check.getCallerString()
				        + String.format("Not an alphanum string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to consist only of Ascii characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void ascii(final String string,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isAscii(string)) {
				throw new StringAsciiViolation(Check.getCallerString()
				        + String.format("Not an ascii string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to consist only of Digit characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void digit(final String string,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isDigit(string)) {
				throw new StringDigitViolation(Check.getCallerString()
				        + String.format("Not an digit string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be empty.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void empty(final String string,
	                               final String formatString,
	                               final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0)) {
				throw new StringEmptyViolation(Check.getCallerString()
				        + String.format("String is not empty: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be a representation of a byte.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void isByte(final String string,
	                                final String formatString,
	                                final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.BYTE)) {
				throw new StringIsByteViolation(Check.getCallerString()
				        + String.format("String is no Byte: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be a representation of a double.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void isDouble(final String string,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.DOUBLE)) {
				throw new StringIsDoubleViolation(Check.getCallerString()
				        + String.format("String is no Double: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be a representation of a float
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void isFloat(final String string,
	                                 final String formatString,
	                                 final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.FLOAT)) {
				throw new StringIsFloatViolation(Check.getCallerString()
				        + String.format("String is no Float: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be a representation of an integer.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void isInteger(final String string,
	                                   final String formatString,
	                                   final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.INTEGER)) {
				throw new StringIsIntegerViolation(Check.getCallerString()
				        + String.format("String is no Integer: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be a representation of a long.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void isLong(final String string,
	                                final String formatString,
	                                final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && KanuniUtils.checkNumber(string, NumberType.LONG)) {
				throw new StringIsLongViolation(Check.getCallerString()
				        + String.format("String is no Long: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be a representation of a short.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void isShort(final String string,
	                                 final String formatString,
	                                 final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.SHORT)) {
				throw new StringIsShortViolation(Check.getCallerString()
				        + String.format("String is no Short: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to have exactly <code>length</code> characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param length
	 *            string length
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void length(final String string,
	                                final Integer length,
	                                final String formatString,
	                                final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (length == null) {
				throw new StringLengthViolation(Check.getCallerString()
				        + String.format("Null is not a valid length for a string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string != null) && (string.length() != length)) {
				throw new StringLengthViolation(Check.getCallerString()
				        + String.format("String (length=%s) does not have length %s: %s. Violation: %s",
				                        string.length(), length, string, String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to consist only of Lowercase characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void lowercase(final String string,
	                                   final String formatString,
	                                   final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isLowercase(string)) {
				throw new StringLowercaseViolation(Check.getCallerString()
				        + String.format("Not an lowercase string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to match a given java regular expression.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param pattern
	 *            the pattern to check against
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void matches(final String string,
	                                 final String pattern,
	                                 final String formatString,
	                                 final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (pattern == null) {
				throw new StringMatchesViolation(Check.getCallerString()
				        + String.format("Null is not a valid pattern. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!KanuniUtils.compilablePattern(pattern)) {
				throw new StringMatchesViolation(Check.getCallerString()
				        + String.format("The pattern `%s` can not be compiled. Violation: %s", pattern,
				                        String.format(formatString, arguments)));
			}
			
			if ((string != null) && !Pattern.matches(pattern, string)) {
				throw new StringMatchesViolation(Check.getCallerString()
				        + String.format("String `%s` does not match pattern: %s. Violation: %s", string, pattern,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to have at most <code>max</code> characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param max
	 *            maximum string length
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void maxLength(final String string,
	                                   final Integer max,
	                                   final String formatString,
	                                   final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (max == null) {
				throw new StringMaxLengthViolation(Check.getCallerString()
				        + String.format("Null is not a valid length for a string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (max < 0) {
				throw new StringMaxLengthViolation(Check.getCallerString()
				        + String.format("Negative lengths (%s) are not allowed for string constraints. Violation: %s",
				                        max, String.format(formatString, arguments)));
			}
			
			if ((string != null) && (string.length() > max)) {
				throw new StringMaxLengthViolation(Check.getCallerString()
				        + String.format("String (length=%s) does not have maximal length of %s: %s. Violation: %s",
				                        string.length(), max, string, String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to have at least <code>min</code> characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param min
	 *            minimum string length
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void minLength(final String string,
	                                   final Integer min,
	                                   final String formatString,
	                                   final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (min == null) {
				throw new StringMinLengthViolation(Check.getCallerString()
				        + String.format("Null is not a valid length for a string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (min < 0) {
				throw new StringMinLengthViolation(Check.getCallerString()
				        + String.format("Negative lengths (%s) are not allowed for string constraints. Violation: %s",
				                        min, String.format(formatString, arguments)));
			}
			
			if ((string != null) && (string.length() < min)) {
				throw new StringMinLengthViolation(Check.getCallerString()
				        + String.format("String (length=%s) does not have minimum length of %s: %s. Violation: %s",
				                        string.length(), min, string, String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be not empty.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void notEmpty(final String string,
	                                  final String formatString,
	                                  final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() <= 0)) {
				throw new StringNotEmptyViolation(Check.getCallerString()
				        + String.format("String is empty: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to NOT match a given java regular expression.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param pattern
	 *            the pattern to check against
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void notMatches(final String string,
	                                    final String pattern,
	                                    final String formatString,
	                                    final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if (pattern == null) {
				throw new StringNotMatchesViolation(Check.getCallerString()
				        + String.format("Null is not a valid pattern. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!KanuniUtils.compilablePattern(pattern)) {
				throw new StringNotMatchesViolation(Check.getCallerString()
				        + String.format("The pattern `%s` can not be compiled. Violation: %s", pattern,
				                        String.format(formatString, arguments)));
			}
			
			if ((string != null) && Pattern.matches(pattern, string)) {
				throw new StringNotMatchesViolation(Check.getCallerString()
				        + String.format("String `%s` does match the pattern: %s. Violation: %s", string, pattern,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param strings
	 * @param formatString
	 * @param arguments
	 * @return true if all strings in the array are of same length (pairwise compair).
	 */
	private static final boolean pairwiseSameLength(final String[] strings,
	                                                final String formatString,
	                                                final Object... arguments) {
		if (strings != null) {
			for (int i = 0; i < strings.length - 1; ++i) {
				sameLength(strings[i], strings[i + 1], formatString, arguments);
			}
		}
		return true;
	}
	
	/**
	 * Checks two given string to have equal length.
	 * 
	 * @param first
	 *            the first string
	 * @param second
	 *            the second string
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void sameLength(final String first,
	                                    final String second,
	                                    final String formatString,
	                                    final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((first != second) && (first.length() != second.length())) {
				throw new StringSameLengthViolation(Check.getCallerString()
				        + String.format("Strings do not have equal length (%s vs. %s): `%s` vs `%s`. Violation: %s",
				                        first.length(), second.length(), first, second,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a string array to only contain strings of equal length.
	 * 
	 * @param strings
	 *            the string array under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void sameLength(final String[] strings,
	                                    final String formatString,
	                                    final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((strings != null) && (strings.length <= 1)) {
				throw new StringSameLengthViolation(
				                                    Check.getCallerString()
				                                            + String.format("String array has to consist of at least 2 strings for equal length constrain checks. Violation: %s",
				                                                            String.format(formatString, arguments)));
			}
			
			if ((strings != null) && !pairwiseSameLength(strings, formatString, arguments)) {
				throw new StringSameLengthViolation(Check.getCallerString()
				        + String.format("Strings in the array do not have equal length. Violation: %s",
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to be trimmed.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void trimmed(final String string,
	                                 final String formatString,
	                                 final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && !string.equals(string.trim())) {
				throw new StringTrimmedViolation(Check.getCallerString()
				        + String.format("String `%s` was not trimmed. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to consist only of Uppercase characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void uppercase(final String string,
	                                   final String formatString,
	                                   final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isUppercase(string)) {
				throw new StringUppercaseViolation(Check.getCallerString()
				        + String.format("Not an uppercase string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * Checks a given string to consist only of XDigit characters.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void xdigit(final String string,
	                                final String formatString,
	                                final Object... arguments) {
		if (KanuniInstrumenter.exceptionsEnabled()) {
			if ((string != null) && (string.length() > 0) && !KanuniUtils.isXDigit(string)) {
				throw new StringXdigitViolation(Check.getCallerString()
				        + String.format("Not an xdigit string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
}
