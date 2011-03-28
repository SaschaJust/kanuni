package net.ownhero.dev.kanuni.annotations.specifics;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorContainer;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 *
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorContainer.class)
@Target (value = { ElementType.PARAMETER })
public @interface Container {
	
	/**
	 * if set to false, kanuni checks the parameter for not being empty
	 */
	boolean allowEmpty() default true;
	
	/**
	 * if set to false, kanuni checks the parameter for not being null
	 */
	boolean allowNull() default true;
	
	/**
	 * if set to true, kanuni checks the parameter for being empty
	 */
	boolean forceEmpty() default false;
	
	/**
	 * if set to true, kanuni checks the parameter for being null
	 */
	boolean forceNull() default false;
	
	/**
	 * if set to a non-negative value, kanuni checks the parameter to have a maximum size of the given value
	 */
	int maxSize() default -1;
	
	/**
	 * if set to a non-negative value, kanuni checks the parameter to have a minimum size of the given value
	 */
	int minSize() default -1;
	
	/**
	 * if set to true, kanuni checks the parameter to not contain any null values
	 */
	boolean noneNull() default false;
	
	/**
	 * if set to a non-negative value, kanuni checks the parameter to have exactly the size of the given value
	 */
	int size() default -1;
	
	/**
	 * the specification/assertion string the above forced properties rely on
	 */
	String spec() default "";
}
