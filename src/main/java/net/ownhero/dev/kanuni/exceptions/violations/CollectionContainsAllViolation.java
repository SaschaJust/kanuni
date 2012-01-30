/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CollectionContainsAllViolation extends CheckViolation {
	
	private static final String message          = CollectionCheck.class.getSimpleName()
	                                                     + "#containsAll check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CollectionContainsAllViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CollectionContainsAllViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CollectionContainsAllViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CollectionContainsAllViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
