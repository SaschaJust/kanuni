/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionContainsViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionContainsViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName() + "#contains check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection contains violation.
	 */
	public CollectionContainsViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection contains violation.
	 *
	 * @param string the string
	 */
	public CollectionContainsViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection contains violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionContainsViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection contains violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionContainsViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
