/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorNegative;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Negative.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorNegative.class)
@Target (value = { ElementType.PARAMETER })
public @interface Negative {
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
