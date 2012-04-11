/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.bounds;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface RangeDouble.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface RangeDouble {
	
	/**
	 * Max.
	 * 
	 * @return the double
	 */
	double max();
	
	/**
	 * Min.
	 * 
	 * @return the double
	 */
	double min();
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
}
