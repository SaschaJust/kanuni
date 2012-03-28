/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionEmptyViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionEmptyViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName() + "#empty check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection empty violation.
	 */
	public CollectionEmptyViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection empty violation.
	 *
	 * @param string the string
	 */
	public CollectionEmptyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection empty violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionEmptyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection empty violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionEmptyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
