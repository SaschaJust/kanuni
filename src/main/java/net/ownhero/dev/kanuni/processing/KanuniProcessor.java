/***********************************************************************************************************************
 * Copyright 2011 Kim Herzig, Sascha Just
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 **********************************************************************************************************************/

package net.ownhero.dev.kanuni.processing;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import net.ownhero.dev.kanuni.annotations.string.Ascii;

/**
 * @author Sascha Just <sascha.just@mozkito.org>
 * 
 */
@SupportedAnnotationTypes ({ "net.ownhero.dev.kanuni.annotations.string.Ascii" })
@SupportedSourceVersion (SourceVersion.RELEASE_7)
public class KanuniProcessor extends AbstractProcessor {
	
	/**
	 * Gets the simple name of the class.
	 * 
	 * @return the simple name of the class.
	 */
	public final String getHandle() {
		return KanuniProcessor.class.getSimpleName();
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 * javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(final Set<? extends TypeElement> annotations,
	                       final RoundEnvironment roundEnv) {
		// PRECONDITIONS
		
		try {
			for (final Element element : roundEnv.getElementsAnnotatedWith(Ascii.class)) {
				final Ascii annotation = element.getAnnotation(Ascii.class);
				this.processingEnv.getMessager().printMessage(Kind.NOTE, "Yeeehaaa " + annotation); //$NON-NLS-1$
				
				final History history = new History();
				final Boolean result = element.accept(new StringVisitor(), history);
				this.processingEnv.getMessager().printMessage(Kind.NOTE,
				                                              StringVisitor.class.getSimpleName() + " -> " + result); //$NON-NLS-1$
			}
			
			return true;
		} finally {
			// POSTCONDITIONS
		}
	}
}
