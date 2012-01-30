/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import net.ownhero.dev.kanuni.annotations.compare.Equals;
import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.annotations.simple.Negative;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class AnnotatedForCompare {
	
	/**
	 * @param a
	 * @param b
	 */
	public final void testEquals(@Equals final int a,
	                             @Marker final int b) {
		
	}
	
	/**
	 * @param a
	 * @param b
	 */
	public final void testEquals2(@Equals final Double a,
	                              @Marker final Double b) {
		
	}
	
	/**
	 * @param a
	 */
	public final void testNegative(@Negative final Double a) {
		
	}
	
	/**
	 * @param a
	 */
	public final void testNegative(@Negative final long a) {
		
	}
	
}
