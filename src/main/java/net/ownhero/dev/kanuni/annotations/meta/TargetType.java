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

/**
 * Required meta annotation on all Kanuni annotations to determine which types the annotation can actually be used on.
 * Auto-boxing is used here.
 * 
 * Keep in mind that kanuni uses a class stub {@link Array} to represent arrays due to the fact that arrays in Java are
 * represented as objects.
 * 
 * 
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * @since 0.2
 */
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (value = { ElementType.ANNOTATION_TYPE })
public @interface TargetType {
	
	/**
	 * Array of classes the annotation can be used on.
	 * 
	 * @return the class acceptabel target classes.
	 */
	Class<?>[] valid();
}
