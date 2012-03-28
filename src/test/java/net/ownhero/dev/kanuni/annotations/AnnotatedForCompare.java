/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import net.ownhero.dev.kanuni.annotations.compare.Equals;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.annotations.simple.Negative;

/**
 * The Class AnnotatedForCompare.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class AnnotatedForCompare {
	
	/**
	 * Test equals.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public final void testEquals(@Equals final int a,
	                             @Marker final int b) {
		
	}
	
	/**
	 * Test equals2.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public final void testEquals2(@Equals final Double a,
	                              @Marker final Double b) {
		
	}
	
	/**
	 * Test negative.
	 *
	 * @param a the a
	 */
	public final void testNegative(@Negative final Double a) {
		
	}
	
	/**
	 * Test negative.
	 *
	 * @param a the a
	 */
	public final void testNegative(@Negative final long a) {
		
	}
	
}
