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
import net.ownhero.dev.kanuni.checks.ArrayCheck;
import net.ownhero.dev.kanuni.conditions.ArrayCondition;
import net.ownhero.dev.kanuni.exceptions.violations.CheckViolation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testContains() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.contains(arr, 3, "testContains");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.contains(arr, 3, "testContains");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.contains(arr, 2, "testContains");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.contains(arr, 2, "testContains");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.contains(null, 3, "testContains");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.contains(null, 3, "testContains");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.contains(arr, null, "testContains");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.contains(arr, null, "testContains");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.contains(new Object[0], 3, "testContains");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.contains(new Object[0], 3, "testContains");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testEmpty() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.empty(arr, "testEmpty");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.empty(arr, "testEmpty");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.empty(null, "testEmpty");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.empty(null, "testEmpty");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.empty(new Object[0], "testEmpty");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.empty(new Object[0], "testEmpty");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testMaxSize() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.maxSize(arr, 2, "testMaxSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.maxSize(arr, 2, "testMaxSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.maxSize(arr, 3, "testMaxSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.maxSize(arr, 3, "testMaxSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.maxSize(arr, 4, "testMaxSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.maxSize(arr, 4, "testMaxSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.maxSize(arr, 0, "testMaxSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.maxSize(arr, 0, "testMaxSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.maxSize(arr, -1, "testMaxSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.maxSize(arr, -1, "testMaxSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.maxSize(null, 3, "testMaxSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.maxSize(null, 3, "testMaxSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testMinSize() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.minSize(arr, 2, "testMinSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.minSize(arr, 2, "testMinSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.minSize(arr, 3, "testMinSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.minSize(arr, 3, "testMinSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.minSize(arr, 4, "testMinSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.minSize(arr, 4, "testMinSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.minSize(arr, 0, "testMinSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.minSize(arr, 0, "testMinSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.minSize(arr, -1, "testMinSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.minSize(arr, -1, "testMinSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.minSize(null, 3, "testMinSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.minSize(null, 3, "testMinSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testNoneNull() {
		Object[] arr = new Object[] { new Object(), new Object() };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.noneNull(arr, "testNoneNull");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.noneNull(arr, "testNoneNull");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr = new Object[] { new Object(), null };
		
		try {
			ArrayCondition.noneNull(arr, "testNoneNull");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.noneNull(arr, "testNoneNull");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr = new Object[] {};
		
		try {
			ArrayCondition.noneNull(arr, "testNoneNull");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.noneNull(arr, "testNoneNull");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr = null;
		
		try {
			ArrayCondition.noneNull(arr, "testNoneNull");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.noneNull(arr, "testNoneNull");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testNotEmpty() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.notEmpty(arr, "testNotEmpty");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.notEmpty(arr, "testNotEmpty");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.notEmpty(null, "testNotEmpty");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.notEmpty(null, "testNotEmpty");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.notEmpty(new Object[0], "testNotEmpty");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.notEmpty(new Object[0], "testNotEmpty");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testSameSize() {
		int[] arr1 = new int[] { 1, 3, 5 };
		int[] arr2 = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr1 = new int[] { 5 };
		arr2 = new int[] { 1, 3, 5 };
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr2 = new int[] { 5 };
		arr1 = new int[] { 1, 3, 5 };
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr1 = new int[] {};
		arr2 = new int[] { 1, 3, 5 };
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr1 = null;
		arr2 = new int[] { 1, 3, 5 };
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr1 = new int[] { 5 };
		arr2 = null;
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		arr1 = null;
		arr2 = null;
		
		try {
			ArrayCondition.sameSize(arr1, arr2, "testSameSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.sameSize(arr1, arr2, "testSameSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
	}
	
	@Test
	public final void testSize() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.size(arr, 2, "testSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.size(arr, 2, "testSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.size(arr, 3, "testSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.size(arr, 3, "testSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.size(arr, 4, "testSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.size(arr, 4, "testSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.size(null, 3, "testSize");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.size(null, 3, "testSize");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
	@Test
	public final void testValidIndex() {
		int[] arr = new int[] { 1, 3, 5 };
		
		boolean condition = true;
		boolean check = true;
		
		try {
			ArrayCondition.validIndex(arr, 0, "testValidIndex");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.validIndex(arr, 0, "testValidIndex");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.validIndex(arr, -1, "testValidIndex");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.validIndex(arr, -1, "testValidIndex");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.validIndex(arr, 2, "testValidIndex");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.validIndex(arr, 2, "testValidIndex");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.validIndex(arr, 3, "testValidIndex");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.validIndex(arr, 3, "testValidIndex");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
		
		try {
			ArrayCondition.validIndex(null, 0, "testValidIndex");
			condition = true;
		} catch (AssertionError e) {
			condition = false;
		}
		
		try {
			ArrayCheck.validIndex(null, 0, "testValidIndex");
			check = true;
		} catch (CheckViolation e) {
			check = false;
		}
		
		assertEquals(condition, check);
	}
	
}
