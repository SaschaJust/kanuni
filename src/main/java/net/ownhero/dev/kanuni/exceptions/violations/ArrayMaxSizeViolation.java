/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * The Class ArrayMaxSizeViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class ArrayMaxSizeViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = ArrayCheck.class.getSimpleName() + "#maxSize check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new array max size violation.
	 */
	public ArrayMaxSizeViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new array max size violation.
	 *
	 * @param string the string
	 */
	public ArrayMaxSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new array max size violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public ArrayMaxSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new array max size violation.
	 *
	 * @param arg0 the arg0
	 */
	public ArrayMaxSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
