/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.array;

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
@ConditionPattern ("ArrayCondition.contains($pname$, $marker$, $value$, new Object[0])")
@Target (value = { ElementType.PARAMETER })
public @interface ArrayContainsElement {
	
	int[] marker() default 1;
	
	String value() default "";
}
