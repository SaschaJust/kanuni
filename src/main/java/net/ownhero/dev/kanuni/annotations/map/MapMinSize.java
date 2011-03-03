package net.ownhero.dev.kanuni.annotations.map;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.meta.ConditionPattern;

@Documented
@Retention (RetentionPolicy.RUNTIME)
@ConditionPattern ("MapCondition.minSize($pname$, new Integer($min$), $value$, new Object[0])")
@Target (value = { ElementType.PARAMETER })
public @interface MapMinSize {
	
	int min();
	
	String value() default "";
}
