/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorNull;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Null.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorNull.class)
@Target (value = { ElementType.PARAMETER })
public @interface Null {
	
	/**
	 * Value.
	 *
	 * @return holds a string reasoning about the assertion.
	 */
	String value() default "";
}
