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
package net.ownhero.dev.kanuni.annotations.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.exceptions.SpecificationException;

/**
 * Required meta annotation on all Kanuni annotations to determine which exceptions can be caused from the corresponding
 * Kanuni precondition. Also specifies the corresponding reasoning in a string. Developers have to take that array
 * indexes of <code>exceptions</code> and <code>reasons</code> are not getting mixed up.
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * @since 0.2
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.ANNOTATION_TYPE })
public @interface Throws {
	
	/**
	 * Exceptions extending {@link SpecificationException} that can be caused by this annotation.
	 * 
	 * @return the class<? extends specification exception>[]
	 */
	Class<? extends SpecificationException>[] exceptions();
	
	/**
	 * Reasons that correspond to the {@link SpecificationException} caused by the annotated annotation.
	 * 
	 * @return the string[]
	 */
	String[] reasons();
}
