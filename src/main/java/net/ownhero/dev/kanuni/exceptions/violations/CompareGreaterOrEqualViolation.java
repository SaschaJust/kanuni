/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * The Class CompareGreaterOrEqualViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CompareGreaterOrEqualViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CompareCheck.class.getSimpleName()
	                                                     + "#greaterOrEqual check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new compare greater or equal violation.
	 */
	public CompareGreaterOrEqualViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new compare greater or equal violation.
	 *
	 * @param string the string
	 */
	public CompareGreaterOrEqualViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new compare greater or equal violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CompareGreaterOrEqualViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new compare greater or equal violation.
	 *
	 * @param arg0 the arg0
	 */
	public CompareGreaterOrEqualViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
