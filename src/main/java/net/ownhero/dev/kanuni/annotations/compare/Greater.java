/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.compare;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorGreater;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Greater.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorGreater.class)
@Target (value = { ElementType.PARAMETER })
public @interface Greater {
	
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
