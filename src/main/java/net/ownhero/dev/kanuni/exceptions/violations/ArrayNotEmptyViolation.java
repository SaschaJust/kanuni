/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * The Class ArrayNotEmptyViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class ArrayNotEmptyViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = ArrayCheck.class.getSimpleName() + "#notEmpty check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new array not empty violation.
	 */
	public ArrayNotEmptyViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new array not empty violation.
	 *
	 * @param string the string
	 */
	public ArrayNotEmptyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new array not empty violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public ArrayNotEmptyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new array not empty violation.
	 *
	 * @param arg0 the arg0
	 */
	public ArrayNotEmptyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
