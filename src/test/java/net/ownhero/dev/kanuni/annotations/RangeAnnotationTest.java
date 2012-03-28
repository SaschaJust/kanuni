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
 * The Class RangeAnnotationTest.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class RangeAnnotationTest {
	
	static {
		System.setProperty("KanuniExceptions", "true");
		KanuniAgent.initialize();
	}
	
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
	 * Test range char primitive invalid.
	 */
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
	
	/**
	 * Test range char primitive valid.
	 */
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
	
	/**
	 * Test range char wrapper type invalid.
	 */
	@Test
	public void testRangeCharWrapperTypeInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			afr.rangeCharWrapperType('A');
			err.add("Input: 'A'");
			Character d = 'd';
			afr.rangeCharWrapperType(d);
			err.add("Input: Character d = 'd'");
			afr.rangeCharWrapperType('1');
			err.add("Input: '1'");
		} catch (Throwable t) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range char wrapper type valid.
	 */
	@Test
	public void testRangeCharWrapperTypeValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			afr.rangeCharWrapperType('a');
			Character b = 'b';
			afr.rangeCharWrapperType(b);
			afr.rangeCharWrapperType('c');
			Character d = null;
			afr.rangeCharWrapperType(d);
		} catch (Throwable e) {
			err.add(e.getMessage() + " " + e.getClass().getCanonicalName());
			e.printStackTrace();
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range double primitive invalid.
	 */
	@Test
	public void testRangeDoublePrimitiveInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			double values[] = { Double.MAX_VALUE, -1, -0.000000001d, 3.000000001d, 10d };
			for (double value : values) {
				afr.rangeDoublePrimitive(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range double primitive valid.
	 */
	@Test
	public void testRangeDoublePrimitiveValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			double values[] = { 0.0d, 0.1d, 1.5d, 2.9d, 3.0d };
			for (double value : values) {
				afr.rangeDoublePrimitive(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range double wrapper type invalid.
	 */
	@Test
	public void testRangeDoubleWrapperTypeInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Double values[] = { Double.MAX_VALUE, -1d, -0.000000001d, 3.000000001d, 10d };
			for (Double value : values) {
				afr.rangeDoubleWrapperType(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range double wrapper type valid.
	 */
	@Test
	public void testRangeDoubleWrapperTypeValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Double values[] = { null, 0.0d, 0.1d, 1.5d, 2.9d, 3.0d };
			for (Double value : values) {
				afr.rangeDoubleWrapperType(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range float primitive invalid.
	 */
	@Test
	public void testRangeFloatPrimitiveInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			float values[] = { Float.MAX_VALUE, -1, -0.000000001f, 3.000000001f, 10f };
			for (float value : values) {
				afr.rangeFloatPrimitive(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range float primitive valid.
	 */
	@Test
	public void testRangeFloatPrimitiveValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			float values[] = { 0.0f, 0.1f, 1.5f, 2.9f, 3.0f };
			for (float value : values) {
				afr.rangeFloatPrimitive(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range float wrapper type invalid.
	 */
	@Test
	public void testRangeFloatWrapperTypeInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Float values[] = { Float.MAX_VALUE, -1f, -0.000000001f, 3.000000001f, 10f };
			for (Float value : values) {
				afr.rangeFloatWrapperType(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range float wrapper type valid.
	 */
	@Test
	public void testRangeFloatWrapperTypeValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Float values[] = { null, 0.0f, 0.1f, 1.5f, 2.9f, 3.0f };
			for (Float value : values) {
				afr.rangeFloatWrapperType(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range integer primitive invalid.
	 */
	@Test
	public void testRangeIntegerPrimitiveInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			int values[] = { 0, Integer.MIN_VALUE, 10, Integer.MAX_VALUE };
			for (int value : values) {
				afr.rangeIntegerPrimitive(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range integer primitive valid.
	 */
	@Test
	public void testRangeIntegerPrimitiveValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			int values[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			for (int value : values) {
				afr.rangeIntegerPrimitive(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range integer wrapper type invalid.
	 */
	@Test
	public void testRangeIntegerWrapperTypeInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Integer values[] = { 0, Integer.MIN_VALUE, 10, Integer.MAX_VALUE };
			for (Integer value : values) {
				afr.rangeIntegerWrapperType(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range integer wrapper type valid.
	 */
	@Test
	public void testRangeIntegerWrapperTypeValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Integer values[] = { null, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			for (Integer value : values) {
				afr.rangeIntegerWrapperType(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range long primitive invalid.
	 */
	@Test
	public void testRangeLongPrimitiveInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			long values[] = { Long.MIN_VALUE, Long.MAX_VALUE, 6l, 9l };
			for (long value : values) {
				afr.rangeLongPrimitive(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range long primitive valid.
	 */
	@Test
	public void testRangeLongPrimitiveValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			long values[] = { 7l, 8l };
			for (long value : values) {
				afr.rangeLongPrimitive(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range long wrapper type invalid.
	 */
	@Test
	public void testRangeLongWrapperTypeInvalid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Long values[] = { Long.MIN_VALUE, Long.MAX_VALUE, 6l, 9l };
			for (Long value : values) {
				afr.rangeLongWrapperType(value);
				err.add("Input: " + value);
			}
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	/**
	 * Test range long wrapper type valid.
	 */
	@Test
	public void testRangeLongWrapperTypeValid() {
		AnnotatedForRange afr = new AnnotatedForRange();
		List<String> err = new LinkedList<String>();
		
		try {
			Long values[] = { null, 7l, 8l };
			for (Long value : values) {
				afr.rangeLongWrapperType(value);
			}
		} catch (Throwable e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
}
