/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * The Class CompareLessViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CompareLessViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CompareCheck.class.getSimpleName() + "#less check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new compare less violation.
	 */
	public CompareLessViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new compare less violation.
	 *
	 * @param string the string
	 */
	public CompareLessViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new compare less violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CompareLessViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new compare less violation.
	 *
	 * @param arg0 the arg0
	 */
	public CompareLessViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
