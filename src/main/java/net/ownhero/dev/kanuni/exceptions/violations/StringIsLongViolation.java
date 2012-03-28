/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringIsLongViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringIsLongViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#isLong check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string is long violation.
	 */
	public StringIsLongViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string is long violation.
	 *
	 * @param string the string
	 */
	public StringIsLongViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string is long violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringIsLongViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string is long violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringIsLongViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
