/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import net.ownhero.dev.kanuni.annotations.array.ArrayContainsElement;
import net.ownhero.dev.kanuni.annotations.array.ArrayContainsValue;
import net.ownhero.dev.kanuni.annotations.array.ArrayIndexRange;
import net.ownhero.dev.kanuni.annotations.array.ArrayIsEmpty;
import net.ownhero.dev.kanuni.annotations.array.ArrayMaxSize;
import net.ownhero.dev.kanuni.annotations.array.ArrayMinSize;
import net.ownhero.dev.kanuni.annotations.array.ArrayNoneNull;
import net.ownhero.dev.kanuni.annotations.array.ArrayNotEmpty;
import net.ownhero.dev.kanuni.annotations.array.ArraySize;
import net.ownhero.dev.kanuni.annotations.meta.Marker;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class AnnotatedForArray {
	
	public final void testArrayContainsElement(@ArrayContainsElement (marker = 1) final int[] array,
	                                           @Marker final int element) {
		
	}
	
	public final void testArrayContainsValue(@ArrayContainsValue (element = "4") final int[] array) {
		
	}
	
	public final void testArrayIndexRange(@ArrayIndexRange (min = 2, max = 9) final Float[] array) {
		
	}
	
	public final void testArrayIsEmpty(@ArrayIsEmpty final Object[] array) {
		
	}
	
	public final void testArrayMaxSize(@ArrayMaxSize (max = 4) final Long[] array) {
		
	}
	
	public final void testArrayMinSize(@ArrayMinSize (min = 3) final double[] array) {
		
	}
	
	public final void testArrayNoneNull(@ArrayNoneNull final Integer[] array) {
		
	}
	
	public final void testArrayNotEmpty(@ArrayNotEmpty final Character[] array) {
		
	}
	
	public final void testArraySize(@ArraySize (size = 3) final byte[] array) {
		
	}
}
