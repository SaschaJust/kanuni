/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * The Class ArrayValidIndexViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class ArrayValidIndexViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = ArrayCheck.class.getSimpleName() + "#validIndex check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new array valid index violation.
	 */
	public ArrayValidIndexViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new array valid index violation.
	 *
	 * @param string the string
	 */
	public ArrayValidIndexViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new array valid index violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public ArrayValidIndexViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new array valid index violation.
	 *
	 * @param arg0 the arg0
	 */
	public ArrayValidIndexViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
