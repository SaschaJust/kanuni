/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import java.util.regex.Pattern;

import net.ownhero.dev.kanuni.exceptions.CheckViolation;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;
import net.ownhero.dev.kanuni.utils.KanuniUtils;
import net.ownhero.dev.kanuni.utils.KanuniUtils.NumberType;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class StringCheck {
	
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not an ascii string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.isAlphanum(string)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not an ascii string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.isAscii(string)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not an ascii string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.isDigit(string)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null (string) can not hold any length constraints. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (string.length() > 0) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is no Byte. Violation: %s", String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.BYTE)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is no Double. Violation: %s", String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.DOUBLE)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is no Float. Violation: %s", String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.FLOAT)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is no Integer. Violation: %s", String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.INTEGER)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is no Long. Violation: %s", String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && KanuniUtils.checkNumber(string, NumberType.LONG)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is no Short. Violation: %s", String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.checkNumber(string, NumberType.SHORT)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null (string) can not hold any length constraints. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (length == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid length for a string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (string.length() != length) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not an ascii string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.isLowercase(string)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (pattern == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid pattern. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!KanuniUtils.compilablePattern(pattern)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("The pattern `%s` can not be compiled. Violation: %s", pattern,
				                        String.format(formatString, arguments)));
			}
			
			if (!Pattern.matches(pattern, string)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null (string) can not hold any length constraints. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (max == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid length for a string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (max < 0) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Negative lengths (%s) are not allowed for string constraints. Violation: %s",
				                        max, String.format(formatString, arguments)));
			}
			
			if (string.length() > max) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null (string) can not hold any length constraints. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (min == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid length for a string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (min < 0) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Negative lengths (%s) are not allowed for string constraints. Violation: %s",
				                        min, String.format(formatString, arguments)));
			}
			
			if (string.length() < min) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null (string) can not hold any length constraints. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (string.length() <= 0) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (pattern == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not a valid pattern. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!KanuniUtils.compilablePattern(pattern)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("The pattern `%s` can not be compiled. Violation: %s", pattern,
				                        String.format(formatString, arguments)));
			}
			
			if (Pattern.matches(pattern, string)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("String `%s` does match the pattern: %s. Violation: %s", string, pattern,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
	/**
	 * @param strings
	 * @param formatString
	 * @param arguments
	 * @return true if all strings in the array are of same length (pairwise
	 *         compair).
	 */
	private static final boolean pairwiseSameLength(final String[] strings,
	                                                final String formatString,
	                                                final Object... arguments) {
		for (int i = 0; i < strings.length - 1; ++i) {
			sameLength(strings[i], strings[i + 1], formatString, arguments);
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (first == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Null (first string) can not hold any length compare constraints. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (second == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Null (second string) can not hold any length compare constraints. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (first.length() != second.length()) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (strings == null) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("Null array can not be checked for equal strings of equal length. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (strings.length <= 1) {
				throw new CheckViolation(
				                         Check.getCallerString()
				                                 + String.format("String array has to consist of at least 2 strings for equal length constrain checks. Violation: %s",
				                                                 String.format(formatString, arguments)));
			}
			
			if (!pairwiseSameLength(strings, formatString, arguments)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null (string) can not be checked for trimmed equality. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if (!string.equals(string.trim())) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not an ascii string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.isUppercase(string)) {
				throw new CheckViolation(Check.getCallerString()
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
		if (KanuniClassloader.isAssertionsEnabled()) {
			if (string == null) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Null is not an ascii string. Violation: %s",
				                        String.format(formatString, arguments)));
			}
			
			if ((string.length() > 0) && !KanuniUtils.isXDigit(string)) {
				throw new CheckViolation(Check.getCallerString()
				        + String.format("Not an xdigit string: %s. Violation: %s", string,
				                        String.format(formatString, arguments)));
			}
		}
	}
	
}
