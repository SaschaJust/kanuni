/**
 * 
 */
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.conditions.ArrayCondition;
import net.ownhero.dev.kanuni.conditions.CollectionCondition;
import net.ownhero.dev.kanuni.conditions.MapCondition;
import net.ownhero.dev.kanuni.exceptions.MalformedAnnotationException;
import net.ownhero.dev.kanuni.loader.KanuniClassloader;

import org.apache.commons.lang.ArrayUtils;


/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class CreatorSize implements Creator {
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createBehaviorInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.util.Map)
	 */
	@Override
	public String createBehaviorInstrumentation(final Annotation annotation,
	                                            final CtBehavior behavior,
	                                            final Map<Integer, SortedSet<String>> markers) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see net.ownhero.dev.kanuni.annotations.factories.Creator#createParameterInstrumentation(javassist.bytecode.annotation.Annotation, javassist.CtBehavior, java.lang.String, javassist.CtClass, java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) {
		StringBuilder builder = new StringBuilder();
		
		StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation, "value");
		String text = textMember.getValue();
		
		IntegerMemberValue sizeMemberValue = (IntegerMemberValue) KanuniClassloader.getMemberValue(annotation, "size");
		int size = sizeMemberValue.getValue();
		
		if (parameterType.isArray()) {
			builder.append(ArrayCondition.class.getCanonicalName())
			       .append(String.format(".size(%s, %s, \"%s\", new Object[0]);", parameterName, size, text))
			       .append(System.getProperty("line.separator"));
		} else {
			try {
				CtClass[] interfaces = parameterType.getInterfaces();
				
				Class<?>[] realInterfaces = new Class[interfaces.length];
				
				for (int i = 0; i < interfaces.length; ++i) {
					realInterfaces[i] = interfaces[i].toClass();
				}
				
				if (ArrayUtils.contains(realInterfaces, Map.class)) {
					builder.append(MapCondition.class.getCanonicalName())
					       .append(String.format(".size(%s, %s, \"%s\", new Object[0]);", parameterName, size, text))
					       .append(System.getProperty("line.separator"));
				} else if (ArrayUtils.contains(realInterfaces, Collection.class)) {
					builder.append(CollectionCondition.class.getCanonicalName())
					       .append(String.format(".size(%s, %s, \"%s\", new Object[0]);", parameterName, size, text))
					       .append(System.getProperty("line.separator"));
				} else {
					throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
					        + parameterName + ":" + parameterType.getName() + ") annotation: "
					        + annotation.getTypeName());
				}
			} catch (NotFoundException e) {
				throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
				                                       + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName(),
				                                       e);
			} catch (CannotCompileException e) {
				throw new MalformedAnnotationException(this.getClass().getName() + ": unsupported parameter ("
				                                       + parameterName + ":" + parameterType.getName() + ") annotation: " + annotation.getTypeName(),
				                                       e);
			}
		}
		
		return builder.toString();
	}
	
}
