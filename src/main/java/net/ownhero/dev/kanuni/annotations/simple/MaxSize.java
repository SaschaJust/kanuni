/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorMaxSize;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface MaxSize.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorMaxSize.class)
@Target (value = { ElementType.PARAMETER })
public @interface MaxSize {
	
	/**
	 * Max.
	 *
	 * @return the int
	 */
	int max();
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
