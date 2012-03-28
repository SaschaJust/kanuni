/*******************************************************************************
 * Copyright 2012 Kim Herzig, Sascha Just
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/
package net.ownhero.dev.kanuni.annotations.factories;

import java.util.Map;
import java.util.SortedSet;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import net.ownhero.dev.kanuni.conditions.StringCondition;
import net.ownhero.dev.kanuni.exceptions.annotations.MalformedAnnotationException;
import net.ownhero.dev.kanuni.instrumentation.KanuniClassloader;
import net.ownhero.dev.kanuni.instrumentation.KanuniInstrumenter;

/**
 * The Class CreateFile.
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CreatorFile extends Creator {
	
	/*
	 * (non-Javadoc)
	 * @see
	 * net.ownhero.dev.kanuni.annotations.factories.Creator#createParameterInstrumentation(javassist.bytecode.annotation
	 * .Annotation, javassist.CtBehavior, java.lang.String, javassist.CtClass, java.util.Map)
	 */
	@Override
	public String createParameterInstrumentation(final Annotation annotation,
	                                             final CtBehavior behavior,
	                                             final String parameterName,
	                                             final CtClass parameterType,
	                                             final Map<Integer, SortedSet<String>> markers) throws MalformedAnnotationException {
		// PRECONDITIONS
		
		final StringBuilder builder = new StringBuilder();
		
		try {
			final StringMemberValue textMember = (StringMemberValue) KanuniClassloader.getMemberValue(annotation,
			                                                                                          "value");
			final String text = textMember.getValue();
			builder.append(KanuniInstrumenter.fileClass);
			builder.append(String.format(".%s(%s, \"%s\", new Object[0]);",
			                             annotation.getTypeName().toLowerCase()
			                                       .substring(annotation.getTypeName().lastIndexOf('.') + 1),
			                             parameterName, text));
			
			builder.append(System.getProperty("line.separator"));
			
			StringCondition.notEmpty(builder.toString(), "Valid instrumentations may never be empty.");
			return builder.toString();
		} finally {
			// POSTCONDITIONS
		}
	}
}
