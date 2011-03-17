/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import static org.junit.Assert.fail;

import net.ownhero.dev.kanuni.conditions.StringCondition;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class StringConditionTest {
	
	public static final boolean checkJUnitThrow(final Throwable e) {
		boolean ret = false;
		ret |= e == null;
		ret |= e.getStackTrace() == null;
		ret |= e.getStackTrace().length < 2;
		ret |= e.getStackTrace()[0].getClassName().equals(org.junit.Assert.class.getCanonicalName());
		return ret;
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			assert (false);
			fail("These methods test assertions. There is no point in assertion testing if assertions are turned off.");
		} catch (AssertionError e) {
		}
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
	public void testAlphaEmpty() {
		String string = new String();
		
		try {
			StringCondition.alpha(string, "Validate alpha string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid alpha string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaInvalid() {
		String string = new String("AbC_dEfgH");
		
		try {
			StringCondition.alpha(string, "Validate alpha string");
			fail("The string `" + string + "` is NOT a valid alpha string. The assertion should have triggered.");
		} catch (AssertionError e) {
			boolean junit = checkJUnitThrow(e);
			if (junit) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNull() {
		String string = null;
		
		try {
			StringCondition.alpha(string, "Validate alpha string");
			fail("The string `" + string + "` is NOT a valid alpha string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNumEmpty() {
		String string = new String();
		
		try {
			StringCondition.alphanum(string, "Validate alpha-numeric string");
		} catch (AssertionError e) {
			fail("The string `" + string
			        + "` is a valid alpha-numeric string. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaNumInvalid() {
		String string = new String("Ab12dEfgH49_");
		
		try {
			StringCondition.alphanum(string, "Validate alpha-numeric string");
			fail("The string `" + string
			        + "` is NOT a valid alpha-numeric string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNumNull() {
		String string = null;
		
		try {
			StringCondition.alphanum(string, "Validate alpha-numeric string");
			fail("The string `" + string
			        + "` is NOT a valid alpha-numeric string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNumValid() {
		String string = new String("AbC12dEfg6H9");
		
		try {
			StringCondition.alphanum(string, "Validate alpha-numeric string");
		} catch (AssertionError e) {
			fail("The string `" + string
			        + "` is a valid alpha-numeric string. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaValid() {
		String string = new String("AbCdEfgH");
		
		try {
			StringCondition.alpha(string, "Validate alpha string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid alpha string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAsciiEmpty() {
		String string = new String();
		
		try {
			StringCondition.ascii(string, "Validate ascii string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid ascii string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAsciiInvalid() {
		String string = new String("The Apple™ Logo () is not pure ascii.");
		
		try {
			StringCondition.ascii(string, "Validate ascii string");
			fail("The string `" + string + "` is NOT a valid ascii string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAsciiNull() {
		String string = null;
		
		try {
			StringCondition.ascii(string, "Validate ascii string");
			fail("The string `" + string + "` is NOT a ascii string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAsciiValid() {
		StringBuilder builder = new StringBuilder();
		for (int i = 32; i < 126; ++i) {
			builder.append((char) i);
		}
		String string = builder.toString();
		
		try {
			StringCondition.ascii(string, "Validate ascii string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid ascii string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testDigitEmpty() {
		String string = new String();
		
		try {
			StringCondition.digit(string, "Validate digit string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid digit string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testDigitInvalid() {
		String string = new String("0123456789a");
		
		try {
			StringCondition.digit(string, "Validate digit string");
			fail("The string `" + string + "` is NOT a valid digit string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testDigitNull() {
		String string = null;
		
		try {
			StringCondition.digit(string, "Validate digit string");
			fail("The string `" + string + "` is NOT a digit string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testDigitValid() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 256; ++i) {
			builder.append(i % 10);
		}
		String string = builder.toString();
		
		try {
			StringCondition.digit(string, "Validate digit string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid digit string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testHexEmpty() {
		String string = new String();
		
		try {
			StringCondition.digit(string, "Validate hex string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid hex string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testHexInvalid() {
		String string = new String("0123456789af");
		
		try {
			StringCondition.digit(string, "Validate hex string");
			fail("The string `" + string + "` is NOT a valid hex string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testHexNull() {
		String string = null;
		
		try {
			StringCondition.digit(string, "Validate hex string");
			fail("The string `" + string + "` is NOT a hex string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testHexValid() {
		char[] chars = new char[22];
		int j = 0;
		
		// 48..57, 65..69, 97..101
		for (int i = 48; i <= 57; ++i) {
			chars[j++] = (char) i;
		}
		for (int i = 65; i <= 69; ++i) {
			chars[j++] = (char) i;
		}
		for (int i = 97; i <= 101; ++i) {
			chars[j++] = (char) i;
		}
		
		StringBuilder builder = new StringBuilder();
		j = 13;
		for (int i = 0; i < 256; ++i) {
			// pseudo randomness :)
			j = j * (i + 1) % 256 + 1;
			builder.append(j % chars.length);
		}
		String string = builder.toString();
		
		try {
			StringCondition.digit(string, "Validate hex string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid hex string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsByteEmpty() {
		String string = new String();
		
		try {
			StringCondition.isByte(string, "Validate byte string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid byte string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsByteInvalid() {
		String string = new String("0123456789af");
		
		try {
			StringCondition.isByte(string, "Validate byte string");
			fail("The string `" + string + "` is NOT a valid byte string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsByteNull() {
		String string = null;
		
		try {
			StringCondition.isByte(string, "Validate byte string");
			fail("The string `" + string + "` is NOT a byte string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsByteValid() {
		String string = new String();
		for (int i = 0; i < 8; ++i) {
			string = ((byte) i) + "";
			try {
				StringCondition.isByte(string, "Validate byte string");
			} catch (AssertionError e) {
				fail("The string `" + string + "` is a valid byte string. The assertion should not have triggered: "
				        + e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testIsDoubleEmpty() {
		String string = new String();
		
		try {
			StringCondition.isDouble(string, "Validate double string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsDoubleInvalid() {
		String string = new String("143.03l");
		
		try {
			StringCondition.isDouble(string, "Validate double string");
			fail("The string `" + string + "` is NOT a valid double string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsDoubleNull() {
		String string = null;
		
		try {
			StringCondition.isDouble(string, "Validate double string");
			fail("The string `" + string + "` is NOT a byte double. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsDoubleValid() {
		String string = new String("123");
		
		try {
			StringCondition.isDouble(string, "Validate double string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("10.12");
		
		try {
			StringCondition.isDouble(string, "Validate double string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("18d");
		
		try {
			StringCondition.isDouble(string, "Validate double string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(".32");
		
		try {
			StringCondition.isDouble(string, "Validate double string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("-.17d");
		
		try {
			StringCondition.isDouble(string, "Validate double string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsFloatEmpty() {
		String string = new String();
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsFloatInvalid() {
		String string = new String("143.03d");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `"
			        + string
			        + "` is a valid float string (although it is marked as a double it can be parsed as a float). The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(Double.MAX_VALUE + "d");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
			fail("The string `" + string + "` is NOT a valid float string. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testIsFloatNull() {
		String string = null;
		
		try {
			StringCondition.isFloat(string, "Validate float string");
			fail("The string `" + string + "` is NOT a byte float. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsFloatValid() {
		String string = new String("123");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("10.12");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("18f");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(".32");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("-.17f");
		
		try {
			StringCondition.isFloat(string, "Validate float string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsIntegerEmpty() {
		String string = new String();
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsIntegerInvalid() {
		String string = new String("143.0");
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("14a");
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0l");
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsIntegerNull() {
		String string = null;
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
			fail("The string `" + string + "` is NOT a byte integer. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsIntegerValid() {
		String string = new String("123");
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Integer.MAX_VALUE);
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Integer.MIN_VALUE);
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("0x10");
		
		try {
			StringCondition.isInteger(string, "Validate integer string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsLongEmpty() {
		String string = new String();
		
		try {
			StringCondition.isLong(string, "Validate long string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsLongInvalid() {
		String string = new String("143.0");
		
		try {
			StringCondition.isLong(string, "Validate long string");
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("14a");
		
		try {
			StringCondition.isLong(string, "Validate long string");
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0f");
		
		try {
			StringCondition.isLong(string, "Validate long string");
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0x10");
		try {
			StringCondition.isLong(string, "Validate long string");
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsLongNull() {
		String string = null;
		
		try {
			StringCondition.isLong(string, "Validate long string");
			fail("The string `" + string + "` is NOT a byte long. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsLongValid() {
		String string = new String("123");
		
		try {
			StringCondition.isLong(string, "Validate long string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			StringCondition.isLong(string, "Validate long string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Long.MAX_VALUE);
		
		try {
			StringCondition.isLong(string, "Validate long string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Long.MIN_VALUE);
		
		try {
			StringCondition.isLong(string, "Validate long string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsShortEmpty() {
		String string = new String();
		
		try {
			StringCondition.isShort(string, "Validate short string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsShortInvalid() {
		String string = new String("143.0");
		
		try {
			StringCondition.isShort(string, "Validate short string");
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		string = new String("14a");
		
		try {
			StringCondition.isShort(string, "Validate short string");
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		string = new String("0f");
		
		try {
			StringCondition.isShort(string, "Validate short string");
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("" + Integer.MAX_VALUE);
		try {
			StringCondition.isShort(string, "Validate short string");
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("" + (Short.MAX_VALUE + 1));
		try {
			StringCondition.isShort(string, "Validate short string");
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsShortNull() {
		String string = null;
		
		try {
			StringCondition.isShort(string, "Validate short string");
			fail("The string `" + string + "` is NOT a byte short. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsShortValid() {
		String string = new String("123");
		
		try {
			StringCondition.isShort(string, "Validate short string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			StringCondition.isShort(string, "Validate short string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Short.MAX_VALUE);
		
		try {
			StringCondition.isShort(string, "Validate short string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Short.MIN_VALUE);
		
		try {
			StringCondition.isShort(string, "Validate short string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testLowercaseEmpty() {
		String string = new String();
		
		try {
			StringCondition.lowercase(string, "Validate lowercase string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid lowercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testLowercaseInvalid() {
		String[] tokens = { "Bleh", "bluB", "3", "_", "ABC", "abc de f" };
		
		for (String string : tokens) {
			
			try {
				StringCondition.lowercase(string, "Validate lowercase string");
				fail("The string `" + string
				        + "` is NOT a valid lowercase string. The assertion should have triggered.");
			} catch (AssertionError e) {
				if (checkJUnitThrow(e)) {
					fail(e.getMessage());
				}
			}
		}
	}
	
	@Test
	public void testLowercaseNull() {
		String string = null;
		
		try {
			StringCondition.lowercase(string, "Validate lowercase string");
			fail("The string `" + string + "` is NOT a valid lowercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testLowercaseValid() {
		String fullname = new String(StringConditionTest.class.getCanonicalName().toLowerCase());
		
		for (String string : fullname.split("\\.")) {
			
			try {
				StringCondition.lowercase(string, "Validate lowercase string");
			} catch (AssertionError e) {
				fail("The string `" + string
				        + "` is a valid lowercase string. The assertion should not have triggered: " + e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesInvalid() {
		String string = new String("aaabababa");
		String pattern = new String("[b]+");
		
		try {
			StringCondition.matches(string, pattern, "Validate matches pattern...");
			fail("The string `" + string + "` should not match the pattern `" + pattern
			        + "`. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesPatternEmpty() {
		String string = new String("string");
		String pattern = new String("");
		
		try {
			StringCondition.matches(string, pattern, "Validate matches pattern...");
			fail("The pattern is empty and thus a string cannot by matched against it. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesPatternNull() {
		String string = new String("string");
		String pattern = null;
		
		try {
			StringCondition.matches(string, pattern, "Validate matches pattern...");
			fail("The pattern is null and thus a string cannot by matched against it. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesStringEmpty() {
		String string = new String("");
		String pattern = new String(".*");
		
		try {
			StringCondition.matches(string, pattern, "Validate matches pattern...");
		} catch (AssertionError e) {
			fail("The string is empty and should thus match the pattern `" + pattern
			        + "`. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMatchesStringNull() {
		String string = null;
		String pattern = new String(".*");
		
		try {
			StringCondition.matches(string, pattern, "Validate matches pattern...");
			fail("The string is null and thus does not match any pattern. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesValid() {
		String string = new String("aaabababa");
		String pattern = new String("[ab]+");
		
		try {
			StringCondition.matches(string, pattern, "Validate matches pattern...");
		} catch (AssertionError e) {
			fail("The string `" + string + "` should match the pattern `" + pattern
			        + "`. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMaxLengthEmpty() {
		String string = new String();
		
		try {
			StringCondition.maxLength(string, 3, "Validate maximal length of string to be 3.");
			
		} catch (AssertionError e) {
			fail("The string `" + string + "` is empty. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMaxLengthInvalid() {
		String string = new String("abcd");
		
		try {
			StringCondition.maxLength(string, 3, "Validate maximal length of string to be 3.");
			fail("The string `" + string + "` has not the maximal length of 3. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMaxLengthNegative() {
		String string = new String("abcdefghijklmnop");
		try {
			StringCondition.maxLength(string, -10, "Validate maximal length of string to be -10.");
			fail("The maximal length is set to a negative value. This should not be allowed. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testMaxLengthNull() {
		String string = null;
		
		try {
			StringCondition.maxLength(string, 0, "Validate maximal length of string to be 0.");
			fail("The string `"
			        + string
			        + "` is null. Thus the maximum length constraint of 0 does not hold. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMaxLengthValid() {
		String string = new String("abcdefg");
		try {
			StringCondition.maxLength(string, 10, "Validate maximal length of string to be 10.");
		} catch (AssertionError e) {
			fail("The string `" + string + "` has length " + string.length()
			        + " and thus fits maximal length of 10. The assertion should not have triggered: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMinLengthEmpty() {
		String string = new String();
		
		try {
			StringCondition.minLength(string, 3, "Validate minimal length of string to be 3.");
			fail("The string `" + string + "` is empty. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testMinLengthInvalid() {
		String string = new String("abcd");
		
		try {
			StringCondition.minLength(string, 5, "Validate minimal length of string to be 5.");
			fail("The string `" + string + "` has not the minimal length of 5. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMinLengthNegative() {
		String string = new String("abcdefghijklmnop");
		try {
			StringCondition.minLength(string, -10, "Validate minimal length of string to be -10.");
			fail("The minimal length is set to a negative value. This should not be allowed. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testMinLengthNull() {
		String string = null;
		
		try {
			StringCondition.minLength(string, 0, "Validate minimal length of string to be 0.");
			fail("The string `"
			        + string
			        + "` is null. Thus the minimum length constraint of 0 does not hold. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMinLengthValid() {
		String string = new String("abcdefghijklmnop");
		try {
			StringCondition.minLength(string, 10, "Validate minimal length of string to be 10.");
		} catch (AssertionError e) {
			fail("The string `" + string + "` has length " + string.length()
			        + " and thus fits minimal length of 10. The assertion should not have triggered: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testSameLengthBothEmpty() {
		String first = new String();
		String second = new String();
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
		} catch (AssertionError e) {
			fail("Both strings are empth. The assertion should not have triggered: " + e.getMessage());
			
		}
	}
	
	@Test
	public void testSameLengthFirstEmpty() {
		String first = new String();
		String second = new String("second");
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
			fail("The first string is empty and the second one not. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthFirstNull() {
		String first = null;
		String second = new String("second");
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
			fail("The first string is null and string length compare is thus not valid. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testSameLengthInvalid() {
		String first = new String("first");
		String second = new String("second");
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
			fail("The first string has length " + first.length() + " and the second string has length "
			        + second.length() + ". The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthSecondEmpty() {
		String first = new String("first");
		String second = new String();
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
			fail("The second string is empty and the first one not. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthSecondNull() {
		String first = new String("first");
		String second = null;
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
			fail("The second string is null and string length compare is thus not valid. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthValid() {
		String first = new String("equal");
		String second = new String("equal");
		
		try {
			StringCondition.sameLength(first, second, "Validate same length");
		} catch (AssertionError e) {
			fail("The first string has length " + first.length() + " and the second string has length "
			        + second.length() + ". The assertion should NOT have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testUppercaseEmpty() {
		String string = new String();
		
		try {
			StringCondition.uppercase(string, "Validate uppercase string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid uppercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testUppercaseInvalid() {
		String string = "UPPERCASe";
		
		try {
			StringCondition.uppercase(string, "Validate uppercase string");
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = "UPPERCAS3";
		
		try {
			StringCondition.uppercase(string, "Validate uppercase string");
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testUppercaseNull() {
		String string = null;
		
		try {
			StringCondition.uppercase(string, "Validate uppercase string");
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testUppercaseValid() {
		
		String string = "UPPERCASE";
		
		try {
			StringCondition.uppercase(string, "Validate uppercase string");
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid uppercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
	}
}
