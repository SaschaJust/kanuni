/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionContainsAnyViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionContainsAnyViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName()
	                                                     + "#containsAny check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection contains any violation.
	 */
	public CollectionContainsAnyViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection contains any violation.
	 *
	 * @param string the string
	 */
	public CollectionContainsAnyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection contains any violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionContainsAnyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection contains any violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionContainsAnyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
