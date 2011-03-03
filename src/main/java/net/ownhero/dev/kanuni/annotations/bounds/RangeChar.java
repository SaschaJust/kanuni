/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.bounds;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.meta.ConditionPattern;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@ConditionPattern ("BoundsCondition.range($pname$, $min$, $max$, $value$, new Object[0])")
@Target (value = { ElementType.PARAMETER })
public @interface RangeChar {
	
	char max();
	
	char min();
	
	String value() default "";
}
