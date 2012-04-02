/**
 * 
 */
package net.ownhero.dev.kanuni.utils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.math.NumberUtils;

/**
 * The Class KanuniUtils.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class KanuniUtils {
	
	/**
	 * The Enum NumberType.
	 */
	public enum NumberType {
		
		/** The BYTE. */
		BYTE,
		/** The DOUBLE. */
		DOUBLE,
		/** The FLOAT. */
		FLOAT,
		/** The INTEGER. */
		INTEGER,
		/** The LONG. */
		LONG,
		/** The SHORT. */
		SHORT;
	}
	
	/** The Constant xdigit. */
	private static final Pattern xdigit    = Pattern.compile("^\\p{XDigit}+$");
	
	/** The Constant ascii. */
	private static final Pattern ascii     = Pattern.compile("^\\p{ASCII}+$");
	
	/** The Constant uppercase. */
	private static final Pattern uppercase = Pattern.compile("^\\p{Upper}+$");
	
	/** The Constant lowercase. */
	private static final Pattern lowercase = Pattern.compile("^\\p{Lower}+$");
	
	/** The Constant digit. */
	private static final Pattern digit     = Pattern.compile("^\\p{Digit}+$");
	
	/** The Constant alpha. */
	private static final Pattern alpha     = Pattern.compile("^\\p{Alpha}+$");
	
	/** The Constant alphanum. */
	private static final Pattern alphanum  = Pattern.compile("^\\p{Alnum}+$");
	
	/**
	 * Checks a given string to be convertible to a given number type (e.g. float).
	 * 
	 * @param string
	 *            the string under suspect
	 * @param type
	 *            the number type the string has to be checked against (see {@link NumberType})
	 * @return true iff the string can be converted to the given type
	 */
	public static final boolean checkNumber(final String string,
	                                        final NumberType type) {
		try {
			switch (type) {
				case BYTE:
					Byte.parseByte(string);
					break;
				case DOUBLE:
					NumberUtils.createDouble(string);
					break;
				case FLOAT:
					NumberUtils.createFloat(string);
					break;
				case INTEGER:
					NumberUtils.createInteger(string);
					break;
				case LONG:
					NumberUtils.createLong(string);
					break;
				case SHORT:
					return NumberUtils.createInteger(string).compareTo((int) Short.MAX_VALUE) <= 0;
			}
		} catch (final NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks if the given pattern can be compiled using {@link Pattern#compile(String)}.
	 * 
	 * @param pattern
	 *            the pattern under suspect
	 * @return true if the compilation was successful
	 */
	public static final boolean compilablePattern(final String pattern) {
		try {
			Pattern.compile(pattern);
		} catch (final PatternSyntaxException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns true if the given string matches the corresponding Alpha regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#alpha
	 */
	public static boolean isAlpha(final String string) {
		return alpha.matcher(string).matches();
	}
	
	/**
	 * Returns true if the given string matches the corresponding Alphanum regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#alphanum
	 */
	public static boolean isAlphanum(final String string) {
		return alphanum.matcher(string).matches();
	}
	
	/**
	 * Returns true if the given string matches the corresponding Ascii regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#ascii
	 */
	public static boolean isAscii(final String string) {
		return ascii.matcher(string).matches();
	}
	
	/**
	 * Returns true if the given string matches the corresponding Digit regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#digit
	 */
	public static boolean isDigit(final String string) {
		return digit.matcher(string).matches();
	}
	
	/**
	 * Returns true if the given string matches the corresponding Lowercase regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#lowercase
	 */
	public static boolean isLowercase(final String string) {
		return lowercase.matcher(string).matches();
	}
	
	/**
	 * Returns true if the given string matches the corresponding Uppercase regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#uppercase
	 */
	public static boolean isUppercase(final String string) {
		return uppercase.matcher(string).matches();
	}
	
	/**
	 * Returns true if the given string matches the corresponding XDigit regular expression.
	 * 
	 * @param string
	 *            the string
	 * @return true if the given string matches the corresponding pattern.
	 * @see KanuniUtils#xdigit
	 */
	public static boolean isXDigit(final String string) {
		return xdigit.matcher(string).matches();
	}
	
}
