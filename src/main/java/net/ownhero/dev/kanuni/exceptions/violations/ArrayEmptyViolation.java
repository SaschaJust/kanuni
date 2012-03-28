/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * The Class ArrayEmptyViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class ArrayEmptyViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = ArrayCheck.class.getSimpleName() + "#empty check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new array empty violation.
	 */
	public ArrayEmptyViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new array empty violation.
	 *
	 * @param string the string
	 */
	public ArrayEmptyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new array empty violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public ArrayEmptyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new array empty violation.
	 *
	 * @param arg0 the arg0
	 */
	public ArrayEmptyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
