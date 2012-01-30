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
import net.ownhero.dev.kanuni.conditions.Condition;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;

/**
 * This annotation can be used on constructors and methods. The {@link KanuniClassloader} will translate this to a
 * series of {@link Condition#notNull(Object, String, Object...)} method calls for each parameter of the annotated
 * behavior.
 * 
 * <h3>Example</h3>
 * <dl>
 * <dt>
 * 
 * <pre>
 * &#064;NoneNull
 * public void someFunction(final String string, final int i, final Float f) {
 * 	...
 * }
 * </pre>
 * 
 * </dt>
 * </dl>
 * 
 * will be translated to:
 * <dl>
 * <dt>
 * 
 * <pre>
 * public void someFunction(final String string, final int i, final Float f) {
 * 	Condition.notNull(string, "");
 * 	Condition.notNull(new Integer(i), "");
 * 	Condition.notNull(f, "");
 * 	...
 * }
 * </pre>
 * 
 * 
 * </dt>
 * </dl>
 * 
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorNoneNull.class)
@Target (value = { ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface NoneNull {
	
	String value() default "";
}
