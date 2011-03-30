/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import net.ownhero.dev.kanuni.annotations.bounds.RangeChar;
import net.ownhero.dev.kanuni.annotations.bounds.RangeDouble;
import net.ownhero.dev.kanuni.annotations.bounds.RangeFloat;
import net.ownhero.dev.kanuni.annotations.bounds.RangeInteger;
import net.ownhero.dev.kanuni.annotations.bounds.RangeLong;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class AnnotatedForRange {
	
	public void rangeCharPrimitive(@RangeChar (min = 'a', max = 'c') final char abc) {
	}
	
	public void rangeCharWrapperType(@RangeChar (min = 'a', max = 'c') final Character abc) {
	}
	
	public void rangeDoublePrimitive(@RangeDouble (min = 0.0d, max = 3.0d) final double _03d) {
	}
	
	public void rangeDoubleWrapperType(@RangeDouble (min = 0.0d, max = 3.0d) final Double _03d) {
	}
	
	public void rangeFloatPrimitive(@RangeFloat (min = 0.0f, max = 3.0f) final float _03f) {
	}
	
	public void rangeFloatWrapperType(@RangeFloat (min = 0.0f, max = 3.0f) final Float _03f) {
	}
	
	public void rangeIntegerPrimitive(@RangeInteger (min = 1, max = 9) final int _19i) {
	}
	
	public void rangeIntegerWrapperType(@RangeInteger (min = 1, max = 9) final Integer _19i) {
	}
	
	public void rangeLongPrimitive(@RangeLong (min = 7l, max = 8l) final long _78l) {
	}
	
	public void rangeLongWrapperType(@RangeLong (min = 7l, max = 8l) final Long _78l) {
	}
}
