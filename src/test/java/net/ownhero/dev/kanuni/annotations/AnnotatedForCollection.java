/*******************************************************************************
 * Copyright 2012 Kim Herzig, Sascha Just
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/

package net.ownhero.dev.kanuni.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
 * The Class AnnotatedForCollection.
 */
public class AnnotatedForCollection {
	
	/**
	 * Test collection contains element.
	 *
	 * @param array the array
	 * @param element the element
	 */
	public final void testCollectionContainsElement(@Contains final LinkedList<Integer> array,
	                                                @Marker final int element) {
		
	}
	
	/**
	 * Test collection contains element2.
	 *
	 * @param array the array
	 * @param element the element
	 */
	public final void testCollectionContainsElement2(@Contains (marker = 2) final Set<Integer> array,
	                                                 @Marker (2) final int element) {
	}
	
	/**
	 * Test collection index range.
	 *
	 * @param array the array
	 */
	public final void testCollectionIndexRange(@ValidIndex (index = 2) final HashSet<Float> array) {
		
	}
	
	/**
	 * Test collection is empty.
	 *
	 * @param array the array
	 */
	public final void testCollectionIsEmpty(@Empty final Deque<Object> array) {
		
	}
	
	/**
	 * Test collection max size.
	 *
	 * @param array the array
	 */
	public final void testCollectionMaxSize(@MaxSize (max = 4) final List<Long> array) {
		
	}
	
	/**
	 * Test collection min size.
	 *
	 * @param array the array
	 */
	public final void testCollectionMinSize(@MinSize (min = 3) final ArrayList<Integer> array) {
		
	}
	
	/**
	 * Test collection none null.
	 *
	 * @param array the array
	 */
	public final void testCollectionNoneNull(@NoneNull final LinkedList<Integer> array) {
		
	}
	
	/**
	 * Test collection not empty.
	 *
	 * @param array the array
	 */
	public final void testCollectionNotEmpty(@NotEmpty final Collection<Character> array) {
		
	}
	
	/**
	 * Test collection size.
	 *
	 * @param array the array
	 */
	public final void testCollectionSize(@Size (size = 3) final Queue<Byte> array) {
		
	}
}
