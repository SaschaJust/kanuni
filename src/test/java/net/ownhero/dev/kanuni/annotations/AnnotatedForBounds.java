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

import net.ownhero.dev.kanuni.annotations.bounds.RangeChar;
import net.ownhero.dev.kanuni.annotations.bounds.RangeDouble;
import net.ownhero.dev.kanuni.annotations.bounds.RangeInteger;
import net.ownhero.dev.kanuni.annotations.bounds.RangeLong;

public class AnnotatedForBounds {
	
	public final void testRangeChar(@RangeChar (min = 'B', max = 'Y') final Character c) {
		
	}
	
	public final void testRangeChar2(@RangeChar (min = 'B', max = 'Y') final char c) {
		
	}
	
	public final void testRangeDouble(@RangeDouble (min = -20, max = -1) final Double d) {
		
	}
	
	public final void testRangeDouble2(@RangeDouble (min = -20, max = -1) final double d) {
		
	}
	
	public final void testRangeInteger(@RangeInteger (min = 0, max = 5) final Integer i) {
		
	}
	
	public final void testRangeInteger2(@RangeInteger (min = 0, max = 5) final int i) {
		
	}
	
	public final void testRangeLong(@RangeLong (min = 2l, max = 4l) final Long l) {
		
	}
	
	public final void testRangeLong2(@RangeLong (min = 2l, max = 4l) final long l) {
		
	}
}
