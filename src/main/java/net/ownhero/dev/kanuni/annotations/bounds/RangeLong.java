/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.bounds;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorRange;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface RangeLong.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorRange.class)
@Target (value = { ElementType.PARAMETER })
public @interface RangeLong {
	
	/**
	 * Max.
	 *
	 * @return the long
	 */
	long max();
	
	/**
	 * Min.
	 *
	 * @return the long
	 */
	long min();
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
