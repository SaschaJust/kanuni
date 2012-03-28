/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringXdigitViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringXdigitViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#xdigit check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string xdigit violation.
	 */
	public StringXdigitViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string xdigit violation.
	 *
	 * @param string the string
	 */
	public StringXdigitViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string xdigit violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringXdigitViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string xdigit violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringXdigitViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
