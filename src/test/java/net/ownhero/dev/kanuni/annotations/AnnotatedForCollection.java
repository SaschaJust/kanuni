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

public class AnnotatedForCollection {
	
	public final void testCollectionContainsElement(@Contains final LinkedList<Integer> array,
	                                                @Marker final int element) {
		
	}
	
	public final void testCollectionContainsElement2(@Contains (marker = 2) final Set<Integer> array,
	                                                 @Marker (2) final int element) {
	}
	
	public final void testCollectionIndexRange(@ValidIndex (index = 2) final HashSet<Float> array) {
		
	}
	
	public final void testCollectionIsEmpty(@Empty final Deque<Object> array) {
		
	}
	
	public final void testCollectionMaxSize(@MaxSize (max = 4) final List<Long> array) {
		
	}
	
	public final void testCollectionMinSize(@MinSize (min = 3) final ArrayList<Integer> array) {
		
	}
	
	public final void testCollectionNoneNull(@NoneNull final LinkedList<Integer> array) {
		
	}
	
	public final void testCollectionNotEmpty(@NotEmpty final Collection<Character> array) {
		
	}
	
	public final void testCollectionSize(@Size (size = 3) final Queue<Byte> array) {
		
	}
}
