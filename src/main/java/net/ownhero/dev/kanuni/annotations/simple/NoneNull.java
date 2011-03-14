/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.simple;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorNoneNull;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorNoneNull.class)
@Target (value = { ElementType.PARAMETER })
public @interface NoneNull {
	
	/**
	 * @return holds a string reasoning about the assertion.
	 */
	String value() default "";
}
