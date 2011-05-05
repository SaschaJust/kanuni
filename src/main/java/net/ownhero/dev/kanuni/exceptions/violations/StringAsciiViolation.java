/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class StringAsciiViolation extends CheckViolation {
	
	private static final String message          = StringCheck.class.getSimpleName() + "#ascii check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public StringAsciiViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public StringAsciiViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public StringAsciiViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public StringAsciiViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
