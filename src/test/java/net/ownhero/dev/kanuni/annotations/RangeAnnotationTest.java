/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import net.ownhero.dev.kanuni.instrumentation.KanuniAgent;

import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
public class RangeAnnotationTest {
	
	static {
		KanuniAgent.initialize();
	}
	
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
	
	@Test
	public void testRangeCharPrimitiveInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			afr.rangeCharPrimitive('A');
			err.add("Input: 'A'");
			char d = 'd';
			afr.rangeCharPrimitive(d);
			err.add("Input: char d = 'd'");
			afr.rangeCharPrimitive('1');
			err.add("Input: '1'");
		} catch (Throwable t) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testRangeCharPrimitiveValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			afr.rangeCharPrimitive('a');
			char b = 'b';
			afr.rangeCharPrimitive(b);
			afr.rangeCharPrimitive('c');
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
}
