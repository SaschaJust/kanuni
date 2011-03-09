/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.meta.ConditionPattern;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;

/**
 * Annotation to assert a string is either empty (but not null) or consists only
 * of alpha-numeric characters. The annotation will cause the
 * {@link KanuniClassloader} to insert appropriate assertions at the beginning
 * of the method/constructor at load time.
 * 
 * <h3>Example 1</h3>
 * <dl>
 * <dt>
 * 
 * <pre>
 * public void someFunction(@FloatString final String string) {
 * 	...
 * }
 * </pre>
 * 
 * </dt>
 * </dl>
 * <br />
 * This code will be translated by the {@link KanuniClassloader} to:
 * <dl>
 * <dt>
 * 
 * <pre>
 * public void someFunction(final String string) {
 *  StringCondition.alphanum(string);
 *  ...
 * }
 * </pre>
 * 
 * </dt>
 * </dl>
 * 
 * <h3>Example 2</h3>
 * 
 * <dt>
 * <dl>
 * 
 * <pre>
 * public class A {
 * 	
 * 	A(@FloatString(&quot;According to spec 123.45 the submitted string has to represent a float.&quot;) 
 * 	                  final String string) {
 * 		...
 * 	}
 * }
 * 
 * 
 * </pre>
 * 
 * </dt></dl> This code will be translated by the {@link KanuniClassloader} to:
 * 
 * 
 * <dt>
 * <dl>
 * 
 * <pre>
 * public class A {
 * 	
 * 	A(final String string) { 
 * 		StringCondition.alphanum(string, &quot;According to spec 123.45 the submitted string has to represent a float.&quot;);
 * 		...
 * 	}
 * }
 * 
 * 
 * </pre>
 * 
 * </dt></dl>
 * 
 * 
 * 
 * @see AlphaNumString
 * @see AlphaString
 * @see AsciiString
 * @see ByteString
 * @see DigitString
 * @see DoubleString
 * @see FloatString
 * @see HexString
 * @see IntegerString
 * @see LongString
 * @see Lowercase
 * @see ShortString
 * @see Trimmed
 * @see Uppercase
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@ConditionPattern ("StringCondition.isFloat($pname$, $value$, new Object[0])")
@Target (value = { ElementType.PARAMETER })
public @interface FloatString {
	
	String value() default "";
}
