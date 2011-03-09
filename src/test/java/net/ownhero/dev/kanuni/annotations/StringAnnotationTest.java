/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import static org.junit.Assert.fail;
import net.ownhero.dev.kanuni.conditions.StringConditionTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class StringAnnotationTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// ClassLoader cl = new KanuniClassloader();
		// Class<?> afsClass =
		// cl.loadClass(StringAnnotationTest.class.getPackage().getName() +
		// ".AnnotatedForString");
		// AnnotatedForString afs = (AnnotatedForString) afsClass.newInstance();
		// afs.maxLength3("123456789");
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private AnnotatedForString afs;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.afs = new AnnotatedForString();
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
			this.afs.alpha(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid alpha string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaInvalid() {
		String string = new String("AbC_dEfgH");
		
		try {
			this.afs.alpha(string);
			fail("The string `" + string + "` is NOT a valid alpha string. The assertion should have triggered.");
		} catch (AssertionError e) {
			boolean junit = StringConditionTest.checkJUnitThrow(e);
			if (junit) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNull() {
		String string = null;
		
		try {
			this.afs.alpha(string);
			fail("The string `" + string + "` is NOT a valid alpha string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNumEmpty() {
		String string = new String();
		
		try {
			this.afs.alphanum(string);
		} catch (AssertionError e) {
			fail("The string `" + string
			        + "` is a valid alpha-numeric string. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaNumInvalid() {
		String string = new String("Ab12dEfgH49_");
		
		try {
			this.afs.alphanum(string);
			fail("The string `" + string
			        + "` is NOT a valid alpha-numeric string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNumNull() {
		String string = null;
		
		try {
			this.afs.alphanum(string);
			fail("The string `" + string
			        + "` is NOT a valid alpha-numeric string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAlphaNumValid() {
		String string = new String("AbC12dEfg6H9");
		
		try {
			this.afs.alphanum(string);
		} catch (AssertionError e) {
			fail("The string `" + string
			        + "` is a valid alpha-numeric string. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaValid() {
		String string = new String("AbCdEfgH");
		
		try {
			this.afs.alpha(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid alpha string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAsciiEmpty() {
		String string = new String();
		
		try {
			this.afs.ascii(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid ascii string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAsciiInvalid() {
		String string = new String("The Apple™ Logo () is not pure ascii.");
		
		try {
			this.afs.ascii(string);
			fail("The string `" + string + "` is NOT a valid ascii string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAsciiNull() {
		String string = null;
		
		try {
			this.afs.ascii(string);
			fail("The string `" + string + "` is NOT a ascii string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
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
			this.afs.ascii(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid ascii string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testDigitEmpty() {
		String string = new String();
		
		try {
			this.afs.digit(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid digit string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testDigitInvalid() {
		String string = new String("0123456789a");
		
		try {
			this.afs.digit(string);
			fail("The string `" + string + "` is NOT a valid digit string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testDigitNull() {
		String string = null;
		
		try {
			this.afs.digit(string);
			fail("The string `" + string + "` is NOT a digit string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
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
			this.afs.digit(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid digit string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testHexEmpty() {
		String string = new String();
		
		try {
			this.afs.digit(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid hex string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testHexInvalid() {
		String string = new String("0123456789af");
		
		try {
			this.afs.digit(string);
			fail("The string `" + string + "` is NOT a valid hex string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testHexNull() {
		String string = null;
		
		try {
			this.afs.digit(string);
			fail("The string `" + string + "` is NOT a hex string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
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
			this.afs.digit(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid hex string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsByteEmpty() {
		String string = new String();
		
		try {
			this.afs.isByte(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid byte string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsByteInvalid() {
		String string = new String("0123456789af");
		
		try {
			this.afs.isByte(string);
			fail("The string `" + string + "` is NOT a valid byte string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsByteNull() {
		String string = null;
		
		try {
			this.afs.isByte(string);
			fail("The string `" + string + "` is NOT a byte string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
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
				this.afs.isByte(string);
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
			this.afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsDoubleInvalid() {
		String string = new String("143.03l");
		
		try {
			this.afs.isDouble(string);
			fail("The string `" + string + "` is NOT a valid double string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsDoubleNull() {
		String string = null;
		
		try {
			this.afs.isDouble(string);
			fail("The string `" + string + "` is NOT a byte double. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsDoubleValid() {
		String string = new String("123");
		
		try {
			this.afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("10.12");
		
		try {
			this.afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("18d");
		
		try {
			this.afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(".32");
		
		try {
			this.afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("-.17d");
		
		try {
			this.afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsFloatEmpty() {
		String string = new String();
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsFloatInvalid() {
		String string = new String("143.03d");
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `"
			        + string
			        + "` is a valid float string (although it is marked as a double it can be parsed as a float). The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(Double.MAX_VALUE + "d");
		
		try {
			this.afs.isFloat(string);
			fail("The string `" + string + "` is NOT a valid float string. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testIsFloatNull() {
		String string = null;
		
		try {
			this.afs.isFloat(string);
			fail("The string `" + string + "` is NOT a byte float. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsFloatValid() {
		String string = new String("123");
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("10.12");
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("18f");
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(".32");
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("-.17f");
		
		try {
			this.afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsIntegerEmpty() {
		String string = new String();
		
		try {
			this.afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsIntegerInvalid() {
		String string = new String("143.0");
		
		try {
			this.afs.isInteger(string);
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("14a");
		
		try {
			this.afs.isInteger(string);
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0l");
		
		try {
			this.afs.isInteger(string);
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsIntegerNull() {
		String string = null;
		
		try {
			this.afs.isInteger(string);
			fail("The string `" + string + "` is NOT a byte integer. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsIntegerValid() {
		String string = new String("123");
		
		try {
			this.afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			this.afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Integer.MAX_VALUE);
		
		try {
			this.afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Integer.MIN_VALUE);
		
		try {
			this.afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("0x10");
		
		try {
			this.afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsLongEmpty() {
		String string = new String();
		
		try {
			this.afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsLongInvalid() {
		String string = new String("143.0");
		
		try {
			this.afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("14a");
		
		try {
			this.afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0f");
		
		try {
			this.afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0x10");
		try {
			this.afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsLongNull() {
		String string = null;
		
		try {
			this.afs.isLong(string);
			fail("The string `" + string + "` is NOT a byte long. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsLongValid() {
		String string = new String("123");
		
		try {
			this.afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			this.afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Long.MAX_VALUE);
		
		try {
			this.afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Long.MIN_VALUE);
		
		try {
			this.afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsShortEmpty() {
		String string = new String();
		
		try {
			this.afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsShortInvalid() {
		String string = new String("143.0");
		
		try {
			this.afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		string = new String("14a");
		
		try {
			this.afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		string = new String("0f");
		
		try {
			this.afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("" + Integer.MAX_VALUE);
		try {
			this.afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("" + (Short.MAX_VALUE + 1));
		try {
			this.afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsShortNull() {
		String string = null;
		
		try {
			this.afs.isShort(string);
			fail("The string `" + string + "` is NOT a byte short. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsShortValid() {
		String string = new String("123");
		
		try {
			this.afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			this.afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Short.MAX_VALUE);
		
		try {
			this.afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Short.MIN_VALUE);
		
		try {
			this.afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testLowercaseEmpty() {
		String string = new String();
		
		try {
			this.afs.lowercase(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid lowercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testLowercaseInvalid() {
		String[] tokens = { "Bleh" };
		
		for (String string : tokens) {
			
			try {
				this.afs.lowercase(string);
				fail("The string `" + string
				        + "` is NOT a valid lowercase string. The assertion should have triggered.");
			} catch (AssertionError e) {
				if (StringConditionTest.checkJUnitThrow(e)) {
					fail(e.getMessage());
				}
			}
		}
	}
	
	@Test
	public void testLowercaseNull() {
		String string = null;
		
		try {
			this.afs.lowercase(string);
			fail("The string `" + string + "` is NOT a valid lowercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testLowercaseValid() {
		String fullname = new String(StringConditionTest.class.getCanonicalName().toLowerCase());
		
		for (String string : fullname.split("\\.")) {
			
			try {
				this.afs.lowercase(string);
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
			this.afs.matches1(string);
			fail("The string `" + string + "` should not match the pattern `" + pattern
			        + "`. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesStringEmpty() {
		String string = new String("");
		String pattern = new String(".*");
		
		try {
			this.afs.matches2(string);
		} catch (AssertionError e) {
			fail("The string is empty and should thus match the pattern `" + pattern
			        + "`. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMatchesStringNull() {
		String string = null;
		new String(".*");
		
		try {
			this.afs.matches2(string);
			fail("The string is null and thus does not match any pattern. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesValid() {
		String string = new String("aaabababa");
		String pattern = new String("[ab]+");
		
		try {
			this.afs.matches3(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` should match the pattern `" + pattern
			        + "`. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMaxLengthInvalid() {
		String string = new String("abcd");
		
		try {
			this.afs.maxLength3(string);
			fail("The string `" + string + "` has not the maximal length of 3. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMaxLengthValid() {
		String string = new String("abcdefg");
		try {
			this.afs.maxLength10(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` has length " + string.length()
			        + " and thus fits maximal length of 10. The assertion should not have triggered: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMinLengthInvalid() {
		String string = new String("abcd");
		
		try {
			this.afs.minLength5(string);
			fail("The string `" + string + "` has not the minimal length of 5. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMinLengthValid() {
		String string = new String("abcdefghijklmnop");
		try {
			this.afs.minLength10(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` has length " + string.length()
			        + " and thus fits minimal length of 10. The assertion should not have triggered: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMultipleSameLengthInvalid() {
		String[] args = { "valid", "invalid", "valid", "also", "also" };
		
		try {
			this.afs.sameLength1(args[0], args[1], args[2], args[3], args[4]);
			fail("Arguments do not fit conditions (same length: 1, 3 but not 2). The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		args[1] = "valid";
		args[4] = "invalid";
		
		try {
			this.afs.sameLength1(args[0], args[1], args[2], args[3], args[4]);
			fail("Arguments do not fit conditions (same length: 1, 3 but not 2). The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMultipleSameLengthValid() {
		String[] args = { "valid", "valid", "valid", "also", "also" };
		
		try {
			this.afs.sameLength1(args[0], args[1], args[2], args[3], args[4]);
		} catch (AssertionError e) {
			fail("Arguments perfectly fit conditions (same length: 1, 2, 3 and 4, 5). The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testSameLengthBothEmpty() {
		String first = new String();
		String second = new String();
		
		try {
			this.afs.sameLength(first, second);
		} catch (AssertionError e) {
			fail("Both strings are empth. The assertion should not have triggered: " + e.getMessage());
			
		}
	}
	
	@Test
	public void testSameLengthFirstEmpty() {
		String first = new String();
		String second = new String("second");
		
		try {
			this.afs.sameLength(first, second);
			fail("The first string is empty and the second one not. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthFirstNull() {
		String first = null;
		String second = new String("second");
		
		try {
			this.afs.sameLength(first, second);
			fail("The first string is null and string length compare is thus not valid. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testSameLengthInvalid() {
		String first = new String("first");
		String second = new String("second");
		
		try {
			this.afs.sameLength(first, second);
			fail("The first string has length " + first.length() + " and the second string has length "
			        + second.length() + ". The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthSecondEmpty() {
		String first = new String("first");
		String second = new String();
		
		try {
			this.afs.sameLength(first, second);
			fail("The second string is empty and the first one not. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthSecondNull() {
		String first = new String("first");
		String second = null;
		
		try {
			this.afs.sameLength(first, second);
			fail("The second string is null and string length compare is thus not valid. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthValid() {
		String first = new String("equal");
		String second = new String("equal");
		
		try {
			this.afs.sameLength(first, second);
		} catch (AssertionError e) {
			fail("The first string has length " + first.length() + " and the second string has length "
			        + second.length() + ". The assertion should NOT have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testUppercaseEmpty() {
		String string = new String();
		
		try {
			this.afs.uppercase(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid uppercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testUppercaseInvalid() {
		String string = "UPPERCASe";
		
		try {
			this.afs.uppercase(string);
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = "UPPERCAS3";
		
		try {
			this.afs.uppercase(string);
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testUppercaseNull() {
		String string = null;
		
		try {
			this.afs.uppercase(string);
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testUppercaseValid() {
		
		String string = "UPPERCASE";
		
		try {
			this.afs.uppercase(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid uppercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
	}
}
