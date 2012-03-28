/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorStringType;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;
import net.ownhero.dev.kanuni.annotations.simple.NotNull;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;

/**
 * Annotation to assert a string is either empty (but not null) or consists only of alpha-numeric characters. If the
 * string is null, the annotation will <b>not</b> trigger a violation. If you want the string also be checked to be not
 * null use the {@link NotNull} annotation in addition.
 * 
 * The annotation will cause the {@link KanuniClassloader} to insert appropriate assertions at the beginning of the
 * method/constructor at load time.
 * 
 * <h3>Example 1</h3>
 * <dl>
 * <dt>
 * 
 * <pre>
 * public void someFunction(@DoubleString final String string) {
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
 * 	A(@DoubleString(&quot;According to spec 123.45 the submitted string has to represent a double.&quot;) 
 * 	                  final String string) {
 * 		...
 * 	}
 * }
 * 
 * 
 * 
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
 * 		StringCondition.alphanum(string, &quot;According to spec 123.45 the submitted string has to represent a double.&quot;);
 * 		...
 * 	}
 * }
 * 
 * 
 * 
 * 
 * 
 * </pre>
 * 
 * </dt></dl>
 * 
 * @see AlphaNumString
 * @see AlphaString
 * @see AsciiString
 * @see ByteString
 * @see CreatorStringType
 * @see DigitString
 * @see DoubleString
 * @see EmptyString
 * @see FloatString
 * @see HexString
 * @see IntegerString
 * @see LongString
 * @see Lowercase
 * @see NotEmptyString
 * @see ShortString
 * @see Trimmed
 * @see Uppercase
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorStringType.class)
@Target (value = { ElementType.PARAMETER })
public @interface DoubleString {
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
