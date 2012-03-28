/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionContainsAllViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionContainsAllViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName()
	                                                     + "#containsAll check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection contains all violation.
	 */
	public CollectionContainsAllViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection contains all violation.
	 *
	 * @param string the string
	 */
	public CollectionContainsAllViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection contains all violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionContainsAllViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection contains all violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionContainsAllViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
