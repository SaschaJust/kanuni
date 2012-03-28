/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringIsDoubleViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringIsDoubleViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#isDouble check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string is double violation.
	 */
	public StringIsDoubleViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string is double violation.
	 *
	 * @param string the string
	 */
	public StringIsDoubleViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string is double violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringIsDoubleViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string is double violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringIsDoubleViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
