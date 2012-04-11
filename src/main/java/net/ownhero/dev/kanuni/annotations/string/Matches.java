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
 * The Interface Matches.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface Matches {
	
	/**
	 * Pattern.
	 * 
	 * @return the string
	 */
	String pattern();
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
}
