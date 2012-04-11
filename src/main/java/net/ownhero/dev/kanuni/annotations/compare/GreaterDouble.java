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
 * The Interface GreaterDouble.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.PARAMETER })
public @interface GreaterDouble {
	
	/**
	 * Ref.
	 * 
	 * @return the double
	 */
	double ref();
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
	
}
