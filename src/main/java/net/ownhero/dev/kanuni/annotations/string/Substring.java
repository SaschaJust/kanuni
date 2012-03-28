/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorStringMatches;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Substring.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */

@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorStringMatches.class)
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
