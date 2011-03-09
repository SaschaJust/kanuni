/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.ANNOTATION_TYPE })
public @interface FactoryClass {
	
	Class<?> value();
}
