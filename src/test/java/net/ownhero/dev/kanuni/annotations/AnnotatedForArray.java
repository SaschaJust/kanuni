/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import net.ownhero.dev.kanuni.annotations.meta.Marker;
import net.ownhero.dev.kanuni.annotations.simple.Contains;
import net.ownhero.dev.kanuni.annotations.simple.Empty;
import net.ownhero.dev.kanuni.annotations.simple.MaxSize;
import net.ownhero.dev.kanuni.annotations.simple.MinSize;
import net.ownhero.dev.kanuni.annotations.simple.NoneNull;
import net.ownhero.dev.kanuni.annotations.simple.NotEmpty;
import net.ownhero.dev.kanuni.annotations.simple.Size;
import net.ownhero.dev.kanuni.annotations.simple.ValidIndex;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class AnnotatedForArray {
	
	/**
	 * @param array
	 * @param element
	 */
	public final void testArrayContainsElement(@Contains (marker = 1) final int[] array,
	                                           @Marker final int element) {
		
	}
	
	/**
	 * @param array
	 * @param element
	 */
	public final void testArrayContainsElement2(@Contains (marker = 2) final int[] array,
	                                            @Marker (2) final int element) {
	}
	
	/**
	 * @param array
	 */
	public final void testArrayIndexRange(@ValidIndex (index = 2) final Float[] array) {
		
	}
	
	/**
	 * @param array
	 */
	public final void testArrayIsEmpty(@Empty final Object[] array) {
		
	}
	
	/**
	 * @param array
	 */
	public final void testArrayMaxSize(@MaxSize (max = 4) final Long[] array) {
		
	}
	
	/**
	 * @param array
	 */
	public final void testArrayMinSize(@MinSize (min = 3) final double[] array) {
		
	}
	
	/**
	 * @param array
	 */
	public final void testArrayNoneNull(@NoneNull final Integer[] array) {
		
	}
	
	/**
	 * @param array
	 */
	public final void testArrayNotEmpty(@NotEmpty final Character[] array) {
		
	}
	
	/**
	 * @param array
	 */
	public final void testArraySize(@Size (size = 3) final byte[] array) {
		
	}
}
