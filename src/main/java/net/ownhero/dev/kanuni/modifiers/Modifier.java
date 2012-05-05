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
package net.ownhero.dev.kanuni.modifiers;

import java.io.File;

import net.ownhero.dev.kanuni.conditions.Condition;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public enum Modifier {
	EXECUTABLE ((byte) 1) {
		
		@Override
		public boolean eval(final File file) {
			// PRECONDITIONS
			
			try {
				return (file != null) && file.canExecute() && (getModifier() != null)
				                                                                     ? getModifier().eval(file)
				                                                                     : true;
			} finally {
				// POSTCONDITIONS
			}
		}
	};
	
	private byte     mask;
	private Modifier modifier = null;
	
	Modifier(final byte mask) {
		this.mask = mask;
	}
	
	public void and(final Modifier modifier) {
		this.modifier = modifier;
	}
	
	public abstract boolean eval(File file);
	
	/**
	 * @return the mask
	 */
	public byte getMask() {
		// PRECONDITIONS
		
		try {
			return this.mask;
		} finally {
			// POSTCONDITIONS
			Condition.notNull(this.mask, "Field '%s' in '%s'.", "mask", getClass().getSimpleName());
		}
	}
	
	/**
	 * @return the modifier
	 */
	public Modifier getModifier() {
		// PRECONDITIONS
		
		try {
			return this.modifier;
		} finally {
			// POSTCONDITIONS
			Condition.notNull(this.modifier, "Field '%s' in '%s'.", "modifier", getClass().getSimpleName());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// PRECONDITIONS
		
		try {
			// TODO Auto-generated method stub
			return name() + "(" + getMask() + ")";
		} finally {
			// POSTCONDITIONS
		}
	}
}
