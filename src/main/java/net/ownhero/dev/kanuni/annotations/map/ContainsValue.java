/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.map;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorContainsValue;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface ContainsValue.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorContainsValue.class)
@Target (value = { ElementType.PARAMETER })
public @interface ContainsValue {
	
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
