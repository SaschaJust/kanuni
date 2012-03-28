/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorSize;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Size.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorSize.class)
@Target (value = { ElementType.PARAMETER })
public @interface Size {
	
	/**
	 * Size.
	 *
	 * @return the int
	 */
	int size();
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
