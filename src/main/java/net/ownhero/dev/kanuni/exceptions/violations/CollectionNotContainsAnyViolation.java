/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionNotContainsAnyViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionNotContainsAnyViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName()
	                                                     + "#notContainsAny check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection not contains any violation.
	 */
	public CollectionNotContainsAnyViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection not contains any violation.
	 *
	 * @param string the string
	 */
	public CollectionNotContainsAnyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection not contains any violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionNotContainsAnyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection not contains any violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionNotContainsAnyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
