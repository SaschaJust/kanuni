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
 * The Class AnnotatedForArray.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class AnnotatedForArray {
	
	/**
	 * Test array contains element.
	 *
	 * @param array the array
	 * @param element the element
	 */
	public final void testArrayContainsElement(@Contains (marker = 1) final int[] array,
	                                           @Marker final int element) {
		
	}
	
	/**
	 * Test array contains element2.
	 *
	 * @param array the array
	 * @param element the element
	 */
	public final void testArrayContainsElement2(@Contains (marker = 2) final int[] array,
	                                            @Marker (2) final int element) {
	}
	
	/**
	 * Test array index range.
	 *
	 * @param array the array
	 */
	public final void testArrayIndexRange(@ValidIndex (index = 2) final Float[] array) {
		
	}
	
	/**
	 * Test array is empty.
	 *
	 * @param array the array
	 */
	public final void testArrayIsEmpty(@Empty final Object[] array) {
		
	}
	
	/**
	 * Test array max size.
	 *
	 * @param array the array
	 */
	public final void testArrayMaxSize(@MaxSize (max = 4) final Long[] array) {
		
	}
	
	/**
	 * Test array min size.
	 *
	 * @param array the array
	 */
	public final void testArrayMinSize(@MinSize (min = 3) final double[] array) {
		
	}
	
	/**
	 * Test array none null.
	 *
	 * @param array the array
	 */
	public final void testArrayNoneNull(@NoneNull final Integer[] array) {
		
	}
	
	/**
	 * Test array not empty.
	 *
	 * @param array the array
	 */
	public final void testArrayNotEmpty(@NotEmpty final Character[] array) {
		
	}
	
	/**
	 * Test array size.
	 *
	 * @param array the array
	 */
	public final void testArraySize(@Size (size = 3) final byte[] array) {
		
	}
}
