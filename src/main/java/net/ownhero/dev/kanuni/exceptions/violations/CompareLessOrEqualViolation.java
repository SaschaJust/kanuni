/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * The Class CompareLessOrEqualViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CompareLessOrEqualViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CompareCheck.class.getSimpleName() + "#lessOrEqual check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new compare less or equal violation.
	 */
	public CompareLessOrEqualViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new compare less or equal violation.
	 *
	 * @param string the string
	 */
	public CompareLessOrEqualViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new compare less or equal violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CompareLessOrEqualViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new compare less or equal violation.
	 *
	 * @param arg0 the arg0
	 */
	public CompareLessOrEqualViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
