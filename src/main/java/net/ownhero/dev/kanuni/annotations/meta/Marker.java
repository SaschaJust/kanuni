/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.compare.Equals;

/**
 * This meta annotation is used in combination with other annotations. E.g. you are using {@link Equals} you need a
 * marker to specify the compare parameter.
 * 
 * <h3>Example</h3>
 * <dl>
 * <dt>
 * 
 * <pre>
 * 
 * public final void testEquals(@Equals final int a,
 *                              &#064;Marker final int b) {
 * 	
 * }
 * </pre>
 * 
 * </dt>
 * </dl>
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface Marker {
	
	/**
	 * Hint.
	 *
	 * @return the string
	 */
	String hint() default "";
	
	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value() default 1;
}
