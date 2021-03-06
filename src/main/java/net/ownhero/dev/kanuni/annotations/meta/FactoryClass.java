/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

/**
 * This meta-annotation for kanuni annotations determines the factory class for that annotation and is used by the
 * {@link KanuniInstrumenter} to create the corresponding instance for instrumentation.
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 * @since 0.1
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.ANNOTATION_TYPE })
public @interface FactoryClass {
	
	/**
	 * Specifies the factory that is used to create the necessary code instrumentation.
	 * 
	 * @return the factory class
	 */
	Class<?> value();
}
