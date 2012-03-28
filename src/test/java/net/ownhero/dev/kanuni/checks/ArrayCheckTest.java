/**
 * 
 */
package net.ownhero.dev.kanuni.checks;

import static org.junit.Assert.fail;
import net.ownhero.dev.kanuni.exceptions.violations.CheckViolation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class ArrayCheckTest.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class ArrayCheckTest {
	
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
	 * Test contains negative.
	 */
	@Test
	public void testContainsNegative() {
		Object[] objects = new Object[] { new Object(), new Object() };
		
		try {
			ArrayCheck.contains(objects, new Object(), "The array must contain this object.");
			fail("The array does not contain the object under suspect. But the 'contains' check did not fail.");
		} catch (CheckViolation e) {
		}
	}
	
	/**
	 * Test contains positive.
	 */
	@Test
	public void testContainsPositive() {
		Object[] objects = new Object[] { new Object(), new Object() };
		
		try {
			ArrayCheck.contains(objects, objects[1], "The array must contain its second object.");
		} catch (CheckViolation e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test empty negative.
	 */
	@Test
	public void testEmptyNegative() {
		fail("TODO");
	}
	
	/**
	 * Test empty positive.
	 */
	@Test
	public void testEmptyPositive() {
		fail("TODO");
	}
	
	/**
	 * Test max size negative.
	 */
	@Test
	public void testMaxSizeNegative() {
		fail("TODO");
	}
	
	/**
	 * Test max size positive.
	 */
	@Test
	public void testMaxSizePositive() {
		fail("TODO");
	}
	
	/**
	 * Test min size negative.
	 */
	@Test
	public void testMinSizeNegative() {
		fail("TODO");
	}
	
	/**
	 * Test min size positive.
	 */
	@Test
	public void testMinSizePositive() {
		fail("TODO");
	}
	
	/**
	 * Test none null negative.
	 */
	@Test
	public void testNoneNullNegative() {
		fail("TODO");
	}
	
	/**
	 * Test none null positive.
	 */
	@Test
	public void testNoneNullPositive() {
		fail("TODO");
	}
	
	/**
	 * Test not empty negative.
	 */
	@Test
	public void testNotEmptyNegative() {
		fail("TODO");
	}
	
	/**
	 * Test not empty positive.
	 */
	@Test
	public void testNotEmptyPositive() {
		fail("TODO");
	}
	
	/**
	 * Test same size negative.
	 */
	@Test
	public void testSameSizeNegative() {
		fail("TODO");
	}
	
	/**
	 * Test same size positive.
	 */
	@Test
	public void testSameSizePositive() {
		fail("TODO");
	}
	
	/**
	 * Test size negative.
	 */
	@Test
	public void testSizeNegative() {
		fail("TODO");
	}
	
	/**
	 * Test size positive.
	 */
	@Test
	public void testSizePositive() {
		fail("TODO");
	}
	
	/**
	 * Test valid index negative.
	 */
	@Test
	public void testValidIndexNegative() {
		fail("TODO");
	}
	
	/**
	 * Test valid index positive.
	 */
	@Test
	public void testValidIndexPositive() {
		fail("TODO");
	}
	
}
