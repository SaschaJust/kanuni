/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.compare;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorEquals;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface EqualsInt.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorEquals.class)
@Target (value = { ElementType.PARAMETER })
public @interface EqualsInt {
	
	/**
	 * Ref.
	 *
	 * @return the int
	 */
	int ref();
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
	
}
