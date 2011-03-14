/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.array;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorContains;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorContains.class)
@Target (value = { ElementType.PARAMETER })
public @interface ArrayContains {
	
	int[] marker() default 1;
	
	String value() default "";
}