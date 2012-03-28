/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * The Class CompareNegativeViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CompareNegativeViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CompareCheck.class.getSimpleName() + "#negative check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new compare negative violation.
	 */
	public CompareNegativeViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new compare negative violation.
	 *
	 * @param string the string
	 */
	public CompareNegativeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new compare negative violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CompareNegativeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new compare negative violation.
	 *
	 * @param arg0 the arg0
	 */
	public CompareNegativeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
