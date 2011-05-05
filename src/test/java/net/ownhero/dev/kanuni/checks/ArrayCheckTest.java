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
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class ArrayCheckTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * 
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
	 * 
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
	
	@Test
	public void testEmptyNegative() {
		fail("TODO");
	}
	
	@Test
	public void testEmptyPositive() {
		fail("TODO");
	}
	
	@Test
	public void testMaxSizeNegative() {
		fail("TODO");
	}
	
	@Test
	public void testMaxSizePositive() {
		fail("TODO");
	}
	
	@Test
	public void testMinSizeNegative() {
		fail("TODO");
	}
	
	@Test
	public void testMinSizePositive() {
		fail("TODO");
	}
	
	@Test
	public void testNoneNullNegative() {
		fail("TODO");
	}
	
	@Test
	public void testNoneNullPositive() {
		fail("TODO");
	}
	
	@Test
	public void testNotEmptyNegative() {
		fail("TODO");
	}
	
	@Test
	public void testNotEmptyPositive() {
		fail("TODO");
	}
	
	@Test
	public void testSameSizeNegative() {
		fail("TODO");
	}
	
	@Test
	public void testSameSizePositive() {
		fail("TODO");
	}
	
	@Test
	public void testSizeNegative() {
		fail("TODO");
	}
	
	@Test
	public void testSizePositive() {
		fail("TODO");
	}
	
	@Test
	public void testValidIndexNegative() {
		fail("TODO");
	}
	
	@Test
	public void testValidIndexPositive() {
		fail("TODO");
	}
	
}
