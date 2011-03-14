/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.bevahiors;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorNoneNull;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorNoneNull.class)
@Target (value = { ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface NoneNull {
	
	String value() default "";
}
