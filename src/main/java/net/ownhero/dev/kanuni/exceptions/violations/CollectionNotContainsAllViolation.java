/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CollectionNotContainsAllViolation extends CheckViolation {
	
	private static final String message          = CollectionCheck.class.getSimpleName()
	                                                     + "#notContainsAll check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CollectionNotContainsAllViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CollectionNotContainsAllViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CollectionNotContainsAllViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CollectionNotContainsAllViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
