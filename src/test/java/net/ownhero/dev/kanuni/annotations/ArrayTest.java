/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class ArrayTest {
	
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
	
	AnnotatedForArray afa = new AnnotatedForArray();
	
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
	
	@Test
	public void testMaxLength3Invalid() {
		boolean err = false;
		try {
			this.afa.testArrayContainsElement(new int[] { 1, 2, 3 }, 0);
			err = true;
		} catch (AssertionError e) {
		}
		
		if (err) {
			fail();
		}
	}
	
}
