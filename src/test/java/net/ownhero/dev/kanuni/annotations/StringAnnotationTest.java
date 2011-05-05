/**
 * 
 */
package net.ownhero.dev.kanuni.annotations;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import net.ownhero.dev.kanuni.conditions.StringConditionTest;
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
public class StringAnnotationTest {
	
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
	public void testAlphaEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.alpha(string);
		} catch (Throwable e) {
			fail("The string `" + string + "` is a valid alpha string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("AbC_dEfgH");
		
		try {
			afs.alpha(string);
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
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.alpha(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testAlphaNumEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.alphanum(string);
		} catch (AssertionError e) {
			fail("The string `" + string
			        + "` is a valid alpha-numeric string. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaNumInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("Ab12dEfgH49_");
		
		try {
			afs.alphanum(string);
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
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.alphanum(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testAlphaNumValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("AbC12dEfg6H9");
		
		try {
			afs.alphanum(string);
		} catch (AssertionError e) {
			fail("The string `" + string
			        + "` is a valid alpha-numeric string. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testAlphaValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("AbCdEfgH");
		
		try {
			afs.alpha(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid alpha string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAsciiEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.ascii(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid ascii string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testAsciiInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("The Apple™ Logo () is not pure ascii.");
		
		try {
			afs.ascii(string);
			fail("The string `" + string + "` is NOT a valid ascii string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testAsciiNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.ascii(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testAsciiValid() {
		AnnotatedForString afs = new AnnotatedForString();
		StringBuilder builder = new StringBuilder();
		for (int i = 32; i < 126; ++i) {
			builder.append((char) i);
		}
		String string = builder.toString();
		
		try {
			afs.ascii(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid ascii string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testDigitEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.digit(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid digit string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testDigitInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("0123456789a");
		
		try {
			afs.digit(string);
			fail("The string `" + string + "` is NOT a valid digit string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testDigitNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.digit(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testDigitValid() {
		AnnotatedForString afs = new AnnotatedForString();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 256; ++i) {
			builder.append(i % 10);
		}
		String string = builder.toString();
		
		try {
			afs.digit(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid digit string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testHexEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.hex(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid hex string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testHexInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = new String("0123456789af");
		
		try {
			afs.hex(string);
		} catch (AssertionError e) {
			err.add(e.getMessage());
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testHexNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.hex(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testHexValid() {
		AnnotatedForString afs = new AnnotatedForString();
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
			afs.hex(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid hex string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsByteEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.isByte(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid byte string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsByteInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("0123456789af");
		
		try {
			afs.isByte(string);
			fail("The string `" + string + "` is NOT a valid byte string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsByteNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.isByte(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testIsByteValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		for (int i = 0; i < 8; ++i) {
			string = ((byte) i) + "";
			try {
				afs.isByte(string);
			} catch (AssertionError e) {
				fail("The string `" + string + "` is a valid byte string. The assertion should not have triggered: "
				        + e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testIsDoubleEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsDoubleInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("143.03l");
		
		try {
			afs.isDouble(string);
			fail("The string `" + string + "` is NOT a valid double string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsDoubleNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testIsDoubleValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("123");
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("10.12");
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("18d");
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(".32");
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("-.17d");
		
		try {
			afs.isDouble(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid double string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsFloatEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsFloatInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("143.03d");
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `"
			        + string
			        + "` is a valid float string (although it is marked as a double it can be parsed as a float). The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(Double.MAX_VALUE + "d");
		
		try {
			afs.isFloat(string);
			fail("The string `" + string + "` is NOT a valid float string. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testIsFloatNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testIsFloatValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("123");
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("10.12");
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("18f");
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String(".32");
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("-.17f");
		
		try {
			afs.isFloat(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid float string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsIntegerEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsIntegerInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("143.0");
		
		try {
			afs.isInteger(string);
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("14a");
		
		try {
			afs.isInteger(string);
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0l");
		
		try {
			afs.isInteger(string);
			fail("The string `" + string + "` is NOT a valid integer string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsIntegerNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testIsIntegerValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("123");
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Integer.MAX_VALUE);
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Integer.MIN_VALUE);
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("0x10");
		
		try {
			afs.isInteger(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid integer string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsLongEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsLongInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("143.0");
		
		try {
			afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("14a");
		
		try {
			afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0f");
		
		try {
			afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("0x10");
		try {
			afs.isLong(string);
			fail("The string `" + string + "` is NOT a valid long string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsLongNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.isLong(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testIsLongValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("123");
		
		try {
			afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Long.MAX_VALUE);
		
		try {
			afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Long.MIN_VALUE);
		
		try {
			afs.isLong(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid long string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsShortEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testIsShortInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("143.0");
		
		try {
			afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		string = new String("14a");
		
		try {
			afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		string = new String("0f");
		
		try {
			afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("" + Integer.MAX_VALUE);
		try {
			afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = new String("" + (Short.MAX_VALUE + 1));
		try {
			afs.isShort(string);
			fail("The string `" + string + "` is NOT a valid short string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testIsShortNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.isShort(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testIsShortValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("123");
		
		try {
			afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		string = new String("0");
		
		try {
			afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Short.MAX_VALUE);
		
		try {
			afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
		string = new String("" + Short.MIN_VALUE);
		
		try {
			afs.isShort(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid short string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testLowercaseEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.lowercase(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid lowercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testLowercaseInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String[] tokens = { "Bleh" };
		
		for (String string : tokens) {
			
			try {
				afs.lowercase(string);
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
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.lowercase(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testLowercaseValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String fullname = new String(StringConditionTest.class.getCanonicalName().toLowerCase());
		
		for (String string : fullname.split("\\.")) {
			
			try {
				afs.lowercase(string);
			} catch (AssertionError e) {
				fail("The string `" + string
				        + "` is a valid lowercase string. The assertion should not have triggered: " + e.getMessage());
			}
		}
	}
	
	@Test
	public void testMatchesInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("aaabababa");
		String pattern = new String("[b]+");
		
		try {
			afs.matches1(string);
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
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("");
		String pattern = new String(".*");
		
		try {
			afs.matches2(string);
		} catch (AssertionError e) {
			fail("The string is empty and should thus match the pattern `" + pattern
			        + "`. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMatchesStringNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String string = null;
		
		try {
			afs.matches2(string);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testMatchesValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("aaabababa");
		String pattern = new String("[ab]+");
		
		try {
			afs.matches3(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` should match the pattern `" + pattern
			        + "`. The assertion should not have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testMaxLengthInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("abcd");
		
		try {
			afs.maxLength3(string);
			fail("The string `" + string + "` has not the maximal length of 3. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMaxLengthValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("abcdefg");
		try {
			afs.maxLength10(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` has length " + string.length()
			        + " and thus fits maximal length of 10. The assertion should not have triggered: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMinLengthInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("abcd");
		
		try {
			afs.minLength5(string);
			fail("The string `" + string + "` has not the minimal length of 5. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMinLengthValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String("abcdefghijklmnop");
		try {
			afs.minLength10(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` has length " + string.length()
			        + " and thus fits minimal length of 10. The assertion should not have triggered: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMultipleSameLengthInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String[] args = { "valid", "invalid", "valid", "also", "also" };
		
		try {
			afs.sameLength1(args[0], args[1], args[2], args[3], args[4]);
			fail("Arguments do not fit conditions (same length: 1, 3 but not 2). The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		args[1] = "valid";
		args[4] = "invalid";
		
		try {
			afs.sameLength1(args[0], args[1], args[2], args[3], args[4]);
			fail("Arguments do not fit conditions (same length: 1, 3 but not 2). The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testMultipleSameLengthValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String[] args = { "valid", "valid", "valid", "also", "also" };
		
		try {
			afs.sameLength1(args[0], args[1], args[2], args[3], args[4]);
		} catch (AssertionError e) {
			fail("Arguments perfectly fit conditions (same length: 1, 2, 3 and 4, 5). The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testSameLengthBothEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String first = new String();
		String second = new String();
		
		try {
			afs.sameLength(first, second);
		} catch (AssertionError e) {
			fail("Both strings are empth. The assertion should not have triggered: " + e.getMessage());
			
		}
	}
	
	@Test
	public void testSameLengthBothNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String first = null;
		String second = null;
		
		try {
			afs.sameLength(first, second);
		} catch (AssertionError e) {
			err.add("Parameter (null) shall not trigger string property violations. See: https://dev.own-hero.net/projects/kanuni/wiki/Developer_Guide#Conditions");
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testSameLengthFirstEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String first = new String();
		String second = new String("second");
		
		try {
			afs.sameLength(first, second);
			fail("The first string is empty and the second one not. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthFirstNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String first = null;
		String second = "second";
		
		try {
			afs.sameLength(first, second);
			err.add("If one of the strings is (null) and the other not, the condition is violated.");
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testSameLengthInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String first = new String("first");
		String second = new String("second");
		
		try {
			afs.sameLength(first, second);
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
		AnnotatedForString afs = new AnnotatedForString();
		String first = new String("first");
		String second = new String();
		
		try {
			afs.sameLength(first, second);
			fail("The second string is empty and the first one not. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testSameLengthSecondNull() {
		AnnotatedForString afs = new AnnotatedForString();
		List<String> err = new LinkedList<String>();
		String first = "first";
		String second = null;
		
		try {
			afs.sameLength(first, second);
			err.add("If one of the strings is (null) and the other not, the condition is violated.");
		} catch (Throwable e) {
		}
		
		if (!err.isEmpty()) {
			fail(ArrayUtils.toString(err.toArray()));
		}
	}
	
	@Test
	public void testSameLengthValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String first = new String("equal");
		String second = new String("equal");
		
		try {
			afs.sameLength(first, second);
		} catch (AssertionError e) {
			fail("The first string has length " + first.length() + " and the second string has length "
			        + second.length() + ". The assertion should NOT have triggered: " + e.getMessage());
		}
	}
	
	@Test
	public void testUppercaseEmpty() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = new String();
		
		try {
			afs.uppercase(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid uppercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
	}
	
	@Test
	public void testUppercaseInvalid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = "UPPERCASe";
		
		try {
			afs.uppercase(string);
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
		
		string = "UPPERCAS3";
		
		try {
			afs.uppercase(string);
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
			if (StringConditionTest.checkJUnitThrow(e)) {
				fail(e.getMessage());
			}
		}
	}
	
	@Test
	public void testUppercaseNull() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = null;
		
		try {
			afs.uppercase(string);
			fail("The string `" + string + "` is NOT a valid uppercase string. The assertion should have triggered.");
		} catch (AssertionError e) {
		}
	}
	
	@Test
	public void testUppercaseValid() {
		AnnotatedForString afs = new AnnotatedForString();
		String string = "UPPERCASE";
		
		try {
			afs.uppercase(string);
		} catch (AssertionError e) {
			fail("The string `" + string + "` is a valid uppercase string. The assertion should not have triggered: "
			        + e.getMessage());
		}
		
	}
}
