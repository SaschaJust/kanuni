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
package net.ownhero.dev.kanuni.annotations.file;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.ownhero.dev.kanuni.annotations.factories.CreatorFile;
import net.ownhero.dev.kanuni.annotations.meta.FactoryClass;

/**
 * The Interface Readable.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@FactoryClass (CreatorFile.class)
@Target (value = { ElementType.PARAMETER })
public @interface Readable {
	
	/**
	 * Value.
	 * 
	 * @return the string
	 */
	String value() default "";
}
