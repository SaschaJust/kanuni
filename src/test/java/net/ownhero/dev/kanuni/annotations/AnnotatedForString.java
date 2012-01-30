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
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class AnnotatedForString {
	
	/**
	 * @param string
	 */
	public void alpha(@AlphaString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void alphanum(@AlphaNumString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void ascii(@AsciiString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void digit(@DigitString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void hex(@HexString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void isByte(@ByteString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void isDouble(@DoubleString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void isFloat(@FloatString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void isInteger(@IntegerString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void isLong(@LongString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void isShort(@ShortString final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void lowercase(@Lowercase final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void matches1(@Matches (pattern = "[b]+") final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void matches2(@Matches (pattern = ".*") final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void matches3(@Matches (pattern = "[ab]+") final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void maxLength10(@MaxLength (max = 10) final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void maxLength3(@MaxLength (max = 3, value = "Testing god said: don't do!") final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void minLength10(@MinLength (min = 10) final String string) {
		
	}
	
	/**
	 * @param string
	 */
	public void minLength5(@MinLength (min = 5) final String string) {
		
	}
	
	/**
	 * @param first
	 * @param second
	 */
	@SameLength
	public void sameLength(@Marker final String first,
	                       @Marker final String second) {
		
	}
	
	/**
	 * This requires first, second and third to have same length as well as forth and fifth.
	 * 
	 * @param first
	 * @param second
	 * @param third
	 * @param forth
	 * @param fifth
	 */
	@SameLength (marker = { 1, 2 },
	             value = "This requires first, second and third to have same length as well as forth and fifth.")
	public void sameLength1(@Marker final String first,
	                        @Marker final String second,
	                        @Marker final String third,
	                        @Marker (value = 2, hint = "second length check") final String forth,
	                        @Marker (2) final String fifth) {
		
	}
	
	public void uppercase(@Uppercase final String string) {
		
	}
	
}
