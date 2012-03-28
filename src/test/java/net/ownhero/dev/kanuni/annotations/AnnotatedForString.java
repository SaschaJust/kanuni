/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.annotations.string.AlphaNumString;
import net.ownhero.dev.kanuni.annotations.string.AlphaString;
import net.ownhero.dev.kanuni.annotations.string.AsciiString;
import net.ownhero.dev.kanuni.annotations.string.ByteString;
import net.ownhero.dev.kanuni.annotations.string.DigitString;
import net.ownhero.dev.kanuni.annotations.string.DoubleString;
import net.ownhero.dev.kanuni.annotations.string.FloatString;
import net.ownhero.dev.kanuni.annotations.string.HexString;
import net.ownhero.dev.kanuni.annotations.string.IntegerString;
import net.ownhero.dev.kanuni.annotations.string.LongString;
import net.ownhero.dev.kanuni.annotations.string.Lowercase;
import net.ownhero.dev.kanuni.annotations.string.Matches;
import net.ownhero.dev.kanuni.annotations.string.MaxLength;
import net.ownhero.dev.kanuni.annotations.string.MinLength;
import net.ownhero.dev.kanuni.annotations.string.SameLength;
import net.ownhero.dev.kanuni.annotations.string.ShortString;
import net.ownhero.dev.kanuni.annotations.string.Uppercase;

/**
 * The Class AnnotatedForString.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class AnnotatedForString {
	
	/**
	 * Alpha.
	 *
	 * @param string the string
	 */
	public void alpha(@AlphaString final String string) {
		
	}
	
	/**
	 * Alphanum.
	 *
	 * @param string the string
	 */
	public void alphanum(@AlphaNumString final String string) {
		
	}
	
	/**
	 * Ascii.
	 *
	 * @param string the string
	 */
	public void ascii(@AsciiString final String string) {
		
	}
	
	/**
	 * Digit.
	 *
	 * @param string the string
	 */
	public void digit(@DigitString final String string) {
		
	}
	
	/**
	 * Hex.
	 *
	 * @param string the string
	 */
	public void hex(@HexString final String string) {
		
	}
	
	/**
	 * Checks if is byte.
	 *
	 * @param string the string
	 */
	public void isByte(@ByteString final String string) {
		
	}
	
	/**
	 * Checks if is double.
	 *
	 * @param string the string
	 */
	public void isDouble(@DoubleString final String string) {
		
	}
	
	/**
	 * Checks if is float.
	 *
	 * @param string the string
	 */
	public void isFloat(@FloatString final String string) {
		
	}
	
	/**
	 * Checks if is integer.
	 *
	 * @param string the string
	 */
	public void isInteger(@IntegerString final String string) {
		
	}
	
	/**
	 * Checks if is long.
	 *
	 * @param string the string
	 */
	public void isLong(@LongString final String string) {
		
	}
	
	/**
	 * Checks if is short.
	 *
	 * @param string the string
	 */
	public void isShort(@ShortString final String string) {
		
	}
	
	/**
	 * Lowercase.
	 *
	 * @param string the string
	 */
	public void lowercase(@Lowercase final String string) {
		
	}
	
	/**
	 * Matches1.
	 *
	 * @param string the string
	 */
	public void matches1(@Matches (pattern = "[b]+") final String string) {
		
	}
	
	/**
	 * Matches2.
	 *
	 * @param string the string
	 */
	public void matches2(@Matches (pattern = ".*") final String string) {
		
	}
	
	/**
	 * Matches3.
	 *
	 * @param string the string
	 */
	public void matches3(@Matches (pattern = "[ab]+") final String string) {
		
	}
	
	/**
	 * Max length10.
	 *
	 * @param string the string
	 */
	public void maxLength10(@MaxLength (max = 10) final String string) {
		
	}
	
	/**
	 * Max length3.
	 *
	 * @param string the string
	 */
	public void maxLength3(@MaxLength (max = 3, value = "Testing god said: don't do!") final String string) {
		
	}
	
	/**
	 * Min length10.
	 *
	 * @param string the string
	 */
	public void minLength10(@MinLength (min = 10) final String string) {
		
	}
	
	/**
	 * Min length5.
	 *
	 * @param string the string
	 */
	public void minLength5(@MinLength (min = 5) final String string) {
		
	}
	
	/**
	 * Same length.
	 *
	 * @param first the first
	 * @param second the second
	 */
	@SameLength
	public void sameLength(@Marker final String first,
	                       @Marker final String second) {
		
	}
	
	/**
	 * This requires first, second and third to have same length as well as forth and fifth.
	 *
	 * @param first the first
	 * @param second the second
	 * @param third the third
	 * @param forth the forth
	 * @param fifth the fifth
	 */
	@SameLength (marker = { 1, 2 },
	             value = "This requires first, second and third to have same length as well as forth and fifth.")
	public void sameLength1(@Marker final String first,
	                        @Marker final String second,
	                        @Marker final String third,
	                        @Marker (value = 2, hint = "second length check") final String forth,
	                        @Marker (2) final String fifth) {
		
	}
	
	/**
	 * Uppercase.
	 *
	 * @param string the string
	 */
	public void uppercase(@Uppercase final String string) {
		
	}
	
}
