/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringAlphaViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringAlphaViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#alpha check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string alpha violation.
	 */
	public StringAlphaViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string alpha violation.
	 *
	 * @param string the string
	 */
	public StringAlphaViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string alpha violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringAlphaViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string alpha violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringAlphaViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
