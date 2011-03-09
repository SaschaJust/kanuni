/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.bounds;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorRange;
import net.ownhero.dev.kanuni.annotations.meta.ConditionPattern;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorRange.class)
@ConditionPattern ("BoundsCondition.range((Character) Character.valueOf((char) $pname$), new Character((char) '$min$'), new Character((char) '$max$'), $value$, new Object[0])")
@Target (value = { ElementType.PARAMETER })
public @interface RangeChar {
	
	char max();
	
	char min();
	
	String value() default "";
}
