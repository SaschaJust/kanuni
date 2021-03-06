/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.compare;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorLessOrEqual;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface LessOrEqualDouble.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorLessOrEqual.class)
@Target (value = { ElementType.PARAMETER })
public @interface LessOrEqualDouble {
	
	/**
	 * Ref.
	 *
	 * @return the double
	 */
	double ref();
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
	
}
