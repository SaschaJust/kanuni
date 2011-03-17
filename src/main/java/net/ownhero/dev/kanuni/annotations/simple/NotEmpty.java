/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorNotEmpty;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;


/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorNotEmpty.class)
@Target (value = { ElementType.PARAMETER })
public @interface NotEmpty {
	
	String value() default "";
}
