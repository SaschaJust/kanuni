/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CollectionEmptyViolation extends CheckViolation {
	
	private static final String message          = CollectionCheck.class.getSimpleName() + "#empty check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CollectionEmptyViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CollectionEmptyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CollectionEmptyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CollectionEmptyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
