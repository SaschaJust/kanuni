/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.ownhero.dev.kanuni.utils.KanuniUtils;
import net.ownhero.dev.kanuni.utils.KanuniUtils.NumberType;

/**
 * Class that holds all condition checks on strings. Methods will return true on empty strings. See the method
 * descriptions for details.
 * 
 * These conditions are used by the annotations in the net.ownhero.dev.kanuni.annotations.string package.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public final class StringCondition {
	
	private static Pattern xdigit    = Pattern.compile("^\\p{XDigit}+$");
	private static Pattern ascii     = Pattern.compile("^\\p{ASCII}+$");
	private static Pattern uppercase = Pattern.compile("^\\p{Upper}+$");
	private static Pattern lowercase = Pattern.compile("^\\p{Lower}+$");
	private static Pattern digit     = Pattern.compile("^\\p{Digit}+$");
	private static Pattern alpha     = Pattern.compile("^\\p{Alpha}+$");
	private static Pattern alphanum  = Pattern.compile("^\\p{Alnum}+$");
	
	/**
	 * Checks a given string to consist only of alphabetic characters. If the string is null, the check is ignored. If
	 * the string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not an alphabetic string. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || alpha.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not an alphabetic string: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to consist only of alphanumeric characters. If the string is null, the check is ignored. If
	 * the string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not an alpha-numeric string. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || alphanum.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not an alphanumeric string: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to consist only of ASCII characters. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not an ascii string. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || ascii.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not an ascii string: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks if the given pattern can be compiled using {@link Pattern#compile(String)}.
	 * 
	 * @param pattern
	 *            the pattern under suspect
	 * @return true if the compilation was successful
	 */
	private static final boolean compilablePattern(final String pattern) {
		try {
			Pattern.compile(pattern);
		} catch (PatternSyntaxException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks a given string to consist only of digit characters. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not an digit string. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || digit.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not a digit string: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be empty. If the string is null, the check is ignored.
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
		// assert string != null : Condition.getCallerString()
		// +
		// String.format("Null (string) can not hold any length constraints. Violation: %s",
		// String.format(formatString, arguments));
		assert (string == null) || (string.length() == 0) : Condition.getCallerString()
		        + String.format("String is not empty: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to consist only of hexadecimal characters. If the string is null, the check is ignored. If
	 * the string is empty the test passes as well.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void hex(final String string,
	                             final String formatString,
	                             final Object... arguments) {
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not an hex string. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || xdigit.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not a hex string: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be a representation of a byte. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is no Byte. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || KanuniUtils.checkNumber(string, NumberType.BYTE) : Condition.getCallerString()
		        + String.format("String is no Byte: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be a representation of a double. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is no Double. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || KanuniUtils.checkNumber(string, NumberType.DOUBLE) : Condition.getCallerString()
		        + String.format("String is no Double: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be a representation of a float. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is no Float. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || KanuniUtils.checkNumber(string, NumberType.FLOAT) : Condition.getCallerString()
		        + String.format("String is no Float: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be a representation of an integer. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is no Integer. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || KanuniUtils.checkNumber(string, NumberType.INTEGER) : Condition.getCallerString()
		        + String.format("String is no Integer: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be a representation of a long. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is no Long. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || KanuniUtils.checkNumber(string, NumberType.LONG) : Condition.getCallerString()
		        + String.format("String is no Long: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be a representation of a short. If the string is null, the check is ignored. If the
	 * string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is no Short. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || KanuniUtils.checkNumber(string, NumberType.SHORT) : Condition.getCallerString()
		        + String.format("String is no Short: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to have exactly <code>length</code> characters. If the string is null, the check is
	 * ignored.
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
		// assert string != null : Condition.getCallerString()
		// +
		// String.format("Null (string) can not hold any length constraints. Violation: %s",
		// String.format(formatString, arguments));
		assert length != null : Condition.getCallerString()
		        + String.format("Null is not a valid length for a string. Violation: %s",
		                        String.format(formatString, arguments));
		assert (string == null) || (string.length() == length) : Condition.getCallerString()
		        + String.format("String (length=%s) does not have length %s: %s. Violation: %s", string.length(),
		                        length, string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to consist only of lowercase characters. If the string is null, the check is ignored. If
	 * the string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not a lowercase string. Violation: %s",
		// String.format(formatString, arguments));
		assert ((string == null) || (string.length() == 0)) || lowercase.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not a lowercase string: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to match a given java regular expression. If the string is null, the check is ignored.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not a valid string. Violation: %s",
		// String.format(formatString, arguments));
		assert pattern != null : Condition.getCallerString()
		        + String.format("Null is not a valid pattern. Violation: %s", String.format(formatString, arguments));
		assert compilablePattern(pattern) : Condition.getCallerString()
		        + String.format("The pattern `%s` can not be compiled. Violation: %s", pattern,
		                        String.format(formatString, arguments));
		assert (string == null) || Pattern.matches(pattern, string) : Condition.getCallerString()
		        + String.format("String `%s` does not match pattern: %s. Violation: %s", string, pattern,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to have at most <code>max</code> characters. If the string is null, the check is ignored.
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
		// assert string != null : Condition.getCallerString()
		// +
		// String.format("Null (string) can not hold any length constraints. Violation: %s",
		// String.format(formatString, arguments));
		assert max != null : Condition.getCallerString()
		        + String.format("Null is not a valid length for a string. Violation: %s",
		                        String.format(formatString, arguments));
		assert max >= 0 : Condition.getCallerString()
		        + String.format("Negative lengths (%s) are not allowed for string constraints. Violation: %s", max,
		                        String.format(formatString, arguments));
		assert (string == null) || (string.length() <= max) : Condition.getCallerString()
		        + String.format("String (length=%s) does not have maximal length of %s: %s. Violation: %s",
		                        string.length(), max, string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to have at least <code>min</code> characters. If the string is null, the check is ignored.
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
		// assert string != null : Condition.getCallerString()
		// +
		// String.format("Null (string) can not hold any length constraints. Violation: %s",
		// String.format(formatString, arguments));
		assert min != null : Condition.getCallerString()
		        + String.format("Null is not a valid length for a string. Violation: %s",
		                        String.format(formatString, arguments));
		assert min >= 0 : Condition.getCallerString()
		        + String.format("Negative lengths (%s) are not allowed for string constraints. Violation: %s", min,
		                        String.format(formatString, arguments));
		assert (string == null) || (string.length() >= min) : Condition.getCallerString()
		        + String.format("String (length=%s) does not have minimum length of %s: %s. Violation: %s",
		                        string.length(), min, string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be not empty. If the string is null, the check is ignored.
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
		// assert string != null : Condition.getCallerString()
		// +
		// String.format("Null (string) can not hold any length constraints. Violation: %s",
		// String.format(formatString, arguments));
		assert (string == null) || (string.length() > 0) : Condition.getCallerString()
		        + String.format("String is empty: %s. Violation: %s", string, String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to NOT match a given java regular expression. If the string is null, the check is ignored.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not a valid string. Violation: %s",
		// String.format(formatString, arguments));
		assert pattern != null : Condition.getCallerString()
		        + String.format("Null is not a valid pattern. Violation: %s", String.format(formatString, arguments));
		assert compilablePattern(pattern) : Condition.getCallerString()
		        + String.format("The pattern `%s` can not be compiled. Violation: %s", pattern,
		                        String.format(formatString, arguments));
		assert (string == null) || !Pattern.matches(pattern, string) : Condition.getCallerString()
		        + String.format("String `%s` does match the pattern: %s. Violation: %s", string, pattern,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param strings
	 *            array containing all strings to be checked for same length
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 * @return true if all strings in the given array pass the
	 *         {@link StringCondition#sameLength(String, String, String, Object...)} test (pairwise).
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
	 * Checks two given strings to have equal length. If both strings are null, the test passes as well.
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
		// assert first != null : Condition.getCallerString()
		// +
		// String.format("Null (first string) can not hold any length compare constraints. Violation: %s",
		// String.format(formatString, arguments));
		// assert second != null : Condition.getCallerString()
		// +
		// String.format("Null (second string) can not hold any length compare constraints. Violation: %s",
		// String.format(formatString, arguments));
		assert ((first == null) && (second == null)) || (first.length() == second.length()) : Condition.getCallerString()
		        + String.format("Strings do not have equal length (%s vs. %s): `%s` vs `%s`. Violation: %s",
		                        first == null
		                                     ? "null"
		                                     : first.length(), second == null
		                                                                     ? "null"
		                                                                     : second.length(), first, second,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a string array to only contain strings of equal length. The test passes if the array is null.
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
		// assert strings != null : Condition.getCallerString()
		// +
		// String.format("Null array can not be checked for equal strings of equal length. Violation: %s",
		// String.format(formatString, arguments));
		assert (strings == null) || (strings.length > 1) : Condition.getCallerString()
		        + String.format("String array has to consist of at least 2 strings for equal length constrain checks. Violation: %s",
		                        String.format(formatString, arguments));
		assert (strings == null) || pairwiseSameLength(strings, formatString, arguments) : Condition.getCallerString()
		        + String.format("Strings in the array do not have equal length. Violation: %s",
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to contain a given substring. If the string is null, the is ignored. If the string is
	 * empty, the check passes as well.
	 * 
	 * @param string
	 *            the string under suspect
	 * @param substring
	 *            the substring to be contained in the string
	 * @param formatString
	 *            the message/formatString describing the violation
	 * @param arguments
	 *            optional arguments to the formatString
	 */
	public static final void substring(final String string,
	                                   final String substring,
	                                   final String formatString,
	                                   final Object... arguments) {
		assert substring != null : Condition.getCallerString()
		        + String.format("String `%s` cannot be checked to contain (null). Violation: %s", string,
		                        String.format(formatString, arguments));
		assert ((string == null) || string.isEmpty() || string.contains(substring)) : Condition.getCallerString()
		        + String.format("String `%s` does not contain substring `%s`. Violation: %s", string, substring,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to be trimmed. If the string is null, the check is ignored. If the string is empty the test
	 * passes as well.
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
		// assert string != null : Condition.getCallerString()
		// +
		// String.format("Null (string) can not be checked for trimmed equality. Violation: %s",
		// String.format(formatString, arguments));
		assert (string == null) || string.equals(string.trim()) : Condition.getCallerString()
		        + String.format("String `%s` was not trimmed. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * Checks a given string to consist only of uppercase characters. If the string is null, the check is ignored. If
	 * the string is empty the test passes as well.
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
		// assert string != null : Condition.getCallerString()
		// + String.format("Null is not a uppercase string. Violation: %s",
		// String.format(formatString, arguments));
		assert (string == null) || (string.length() == 0) || uppercase.matcher(string).matches() : Condition.getCallerString()
		        + String.format("Not an uppercase string: %s. Violation: %s", string,
		                        String.format(formatString, arguments));
	}
}
