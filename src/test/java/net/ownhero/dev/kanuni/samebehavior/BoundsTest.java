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

package net.ownhero.dev.kanuni.samebehavior;

import static org.junit.Assert.assertEquals;
import net.ownhero.dev.kanuni.checks.BoundsCheck;
import net.ownhero.dev.kanuni.conditions.BoundsCondition;
import net.ownhero.dev.kanuni.exceptions.violations.CheckViolation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class BoundsTest.
 */
public class BoundsTest {
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test range.
	 */
	@Test
	public final void testRange() {
		Character[][] characters1 = new Character[][] { new Character[] { 'c', 'c', 'd' },
		        new Character[] { 'c', 'd', 'd' }, new Character[] { 'c', 'e', 'g' },
		        new Character[] { 'c', 'b', 'd' }, new Character[] { 'c', 'a', 'b' },
		        new Character[] { null, 'c', 'd' }, new Character[] { 'c', null, 'd' },
		        new Character[] { 'c', 'c', null }, new Character[] { 'c', null, null } };
		
		Number[][] numberRanges = new Number[][] {
		
		new Short[] { 123, 123, 124 }, new Short[] { 123, 124, 124 }, new Short[] { 123, 1425, 1824 },
		        new Short[] { 123, 78, 23 }, new Short[] { 123, 1, 500 }, new Short[] { 123, -1, 200 },
		        new Short[] { 123, null, 124 }, new Short[] { 123, 123, null }, new Short[] { 123, null, null },
		        new Short[] { null, 123, 124 }, new Short[] { 123, 1, 10 },
		        
		        new Integer[] {},
		        
		        new Long[] {},
		        
		        new Double[] {},
		        
		        new Float[] {} };
		
		boolean condition = true;
		boolean check = true;
		
		for (Character[] c1s : characters1) {
			try {
				BoundsCondition.range(c1s[0], c1s[1], c1s[2], "testRange");
				condition = true;
			} catch (AssertionError e) {
				condition = false;
			}
			
			try {
				BoundsCheck.range(c1s[0], c1s[1], c1s[2], "testRange");
				check = true;
			} catch (CheckViolation e) {
				check = false;
			}
			
			assertEquals(condition, check);
			
		}
		
		for (Number[] numbers : numberRanges) {
			try {
				BoundsCondition.range(numbers[0], numbers[1], numbers[2], "testRange");
				condition = true;
			} catch (AssertionError e) {
				condition = false;
			}
			
			try {
				BoundsCheck.range(numbers[0], numbers[1], numbers[2], "testRange");
				check = true;
			} catch (CheckViolation e) {
				check = false;
			}
			
			assertEquals(condition, check);
			
		}
	}
}
