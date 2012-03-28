/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionNotContainsViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionNotContainsViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName()
	                                                     + "#notContains check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection not contains violation.
	 */
	public CollectionNotContainsViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection not contains violation.
	 *
	 * @param string the string
	 */
	public CollectionNotContainsViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection not contains violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionNotContainsViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection not contains violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionNotContainsViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
