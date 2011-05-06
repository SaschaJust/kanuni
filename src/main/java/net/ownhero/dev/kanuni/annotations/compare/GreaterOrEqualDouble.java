/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.compare;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorGreatorOrEqual;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorGreatorOrEqual.class)
@Target (value = { ElementType.PARAMETER })
public @interface GreaterOrEqualDouble {
	
	double ref();
	
	String value() default "";
	
}
