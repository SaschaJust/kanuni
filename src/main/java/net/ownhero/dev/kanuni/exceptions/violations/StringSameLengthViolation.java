/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringSameLengthViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringSameLengthViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#sameLength check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string same length violation.
	 */
	public StringSameLengthViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string same length violation.
	 *
	 * @param string the string
	 */
	public StringSameLengthViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string same length violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringSameLengthViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string same length violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringSameLengthViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
