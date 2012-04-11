/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.compare;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface NotEquals.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface NotEquals {
	
	/**
	 * Marker.
	 * 
	 * @return the int[]
	 */
	int[] marker() default 1;
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
	
}
