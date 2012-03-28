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
 * The Class AnnotatedForRange.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class AnnotatedForRange {
	
	/**
	 * Range char primitive.
	 *
	 * @param abc the abc
	 */
	public void rangeCharPrimitive(@RangeChar (min = 'a', max = 'c') final char abc) {
	}
	
	/**
	 * Range char wrapper type.
	 *
	 * @param abc the abc
	 */
	public void rangeCharWrapperType(@RangeChar (min = 'a', max = 'c') final Character abc) {
	}
	
	/**
	 * Range double primitive.
	 *
	 * @param _03d the _03d
	 */
	public void rangeDoublePrimitive(@RangeDouble (min = 0.0d, max = 3.0d) final double _03d) {
	}
	
	/**
	 * Range double wrapper type.
	 *
	 * @param _03d the _03d
	 */
	public void rangeDoubleWrapperType(@RangeDouble (min = 0.0d, max = 3.0d) final Double _03d) {
	}
	
	/**
	 * Range float primitive.
	 *
	 * @param _03f the _03f
	 */
	public void rangeFloatPrimitive(@RangeFloat (min = 0.0f, max = 3.0f) final float _03f) {
	}
	
	/**
	 * Range float wrapper type.
	 *
	 * @param _03f the _03f
	 */
	public void rangeFloatWrapperType(@RangeFloat (min = 0.0f, max = 3.0f) final Float _03f) {
	}
	
	/**
	 * Range integer primitive.
	 *
	 * @param _19i the _19i
	 */
	public void rangeIntegerPrimitive(@RangeInteger (min = 1, max = 9) final int _19i) {
	}
	
	/**
	 * Range integer wrapper type.
	 *
	 * @param _19i the _19i
	 */
	public void rangeIntegerWrapperType(@RangeInteger (min = 1, max = 9) final Integer _19i) {
	}
	
	/**
	 * Range long primitive.
	 *
	 * @param _78l the _78l
	 */
	public void rangeLongPrimitive(@RangeLong (min = 7l, max = 8l) final long _78l) {
	}
	
	/**
	 * Range long wrapper type.
	 *
	 * @param _78l the _78l
	 */
	public void rangeLongWrapperType(@RangeLong (min = 7l, max = 8l) final Long _78l) {
	}
}
