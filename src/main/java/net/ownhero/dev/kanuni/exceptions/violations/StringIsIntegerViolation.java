/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringIsIntegerViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringIsIntegerViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#isInteger check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string is integer violation.
	 */
	public StringIsIntegerViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string is integer violation.
	 *
	 * @param string the string
	 */
	public StringIsIntegerViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string is integer violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringIsIntegerViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string is integer violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringIsIntegerViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
