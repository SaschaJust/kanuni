/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.map;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface ContainsKey.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface ContainsKey {
	
	/**
	 * Marker.
	 * 
	 * @return the int[]
	 */
	int[] marker();
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
}
