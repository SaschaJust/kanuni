/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Substring.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */

@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface Substring {
	
	/**
	 * Substring.
	 * 
	 * @return the string
	 */
	String substring();
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
}
