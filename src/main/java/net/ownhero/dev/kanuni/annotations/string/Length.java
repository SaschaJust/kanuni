/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.string;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorStringLength;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Length.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorStringLength.class)
@Target (value = { ElementType.PARAMETER })
public @interface Length {
	
	/**
	 * Length.
	 *
	 * @return the int
	 */
	int length();
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
