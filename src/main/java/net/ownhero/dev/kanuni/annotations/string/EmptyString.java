/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorStringType;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface EmptyString.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorStringType.class)
@Target (value = { ElementType.PARAMETER })
public @interface EmptyString {
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
