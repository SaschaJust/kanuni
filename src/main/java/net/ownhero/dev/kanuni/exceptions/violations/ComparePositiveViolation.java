/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * The Class ComparePositiveViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class ComparePositiveViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CompareCheck.class.getSimpleName() + "#positive check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new compare positive violation.
	 */
	public ComparePositiveViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new compare positive violation.
	 *
	 * @param string the string
	 */
	public ComparePositiveViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new compare positive violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public ComparePositiveViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new compare positive violation.
	 *
	 * @param arg0 the arg0
	 */
	public ComparePositiveViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
