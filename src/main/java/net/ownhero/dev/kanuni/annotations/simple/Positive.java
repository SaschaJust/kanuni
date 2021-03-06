/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorPositive;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Positive.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorPositive.class)
@Target (value = { ElementType.PARAMETER })
public @interface Positive {
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
