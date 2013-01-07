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

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

/**
 * @author Sascha Just <sascha.just@mozkito.org>
 * 
 */
public class StringVisitor implements ElementVisitor<Boolean, History> {
	
	/**
	 * Gets the simple name of the class.
	 * 
	 * @return the simple name of the class.
	 */
	public final String getHandle() {
		return StringVisitor.class.getSimpleName();
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visit(javax.lang.model.element.Element)
	 */
	@Override
	public Boolean visit(final Element e) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visit' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visit(javax.lang.model.element.Element, java.lang.Object)
	 */
	@Override
	public Boolean visit(final Element e,
	                     final History p) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visit' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visitExecutable(javax.lang.model.element.ExecutableElement,
	 * java.lang.Object)
	 */
	@Override
	public Boolean visitExecutable(final ExecutableElement e,
	                               final History p) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visitExecutable' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visitPackage(javax.lang.model.element.PackageElement,
	 * java.lang.Object)
	 */
	@Override
	public Boolean visitPackage(final PackageElement e,
	                            final History p) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visitPackage' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visitType(javax.lang.model.element.TypeElement, java.lang.Object)
	 */
	@Override
	public Boolean visitType(final TypeElement e,
	                         final History p) {
		// PRECONDITIONS
		
		try {
			
			throw new RuntimeException("Method 'visitType' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visitTypeParameter(javax.lang.model.element.TypeParameterElement,
	 * java.lang.Object)
	 */
	@Override
	public Boolean visitTypeParameter(final TypeParameterElement e,
	                                  final History p) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visitTypeParameter' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visitUnknown(javax.lang.model.element.Element, java.lang.Object)
	 */
	@Override
	public Boolean visitUnknown(final Element e,
	                            final History p) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visitUnknown' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.lang.model.element.ElementVisitor#visitVariable(javax.lang.model.element.VariableElement,
	 * java.lang.Object)
	 */
	@Override
	public Boolean visitVariable(final VariableElement e,
	                             final History p) {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			// return null;
			throw new RuntimeException("Method 'visitVariable' has not yet been implemented."); //$NON-NLS-1$
		} finally {
			// POSTCONDITIONS
		}
	}
}
