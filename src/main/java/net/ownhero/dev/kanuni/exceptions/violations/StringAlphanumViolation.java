/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringAlphanumViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringAlphanumViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#alphanum check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string alphanum violation.
	 */
	public StringAlphanumViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string alphanum violation.
	 *
	 * @param string the string
	 */
	public StringAlphanumViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string alphanum violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringAlphanumViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string alphanum violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringAlphanumViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
