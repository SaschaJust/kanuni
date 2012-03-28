/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * The Class StringMatchesViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class StringMatchesViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = StringCheck.class.getSimpleName() + "#matches check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new string matches violation.
	 */
	public StringMatchesViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new string matches violation.
	 *
	 * @param string the string
	 */
	public StringMatchesViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new string matches violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public StringMatchesViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new string matches violation.
	 *
	 * @param arg0 the arg0
	 */
	public StringMatchesViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
