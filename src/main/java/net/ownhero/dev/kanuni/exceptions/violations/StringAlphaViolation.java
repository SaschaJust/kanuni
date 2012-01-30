/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class StringAlphaViolation extends CheckViolation {
	
	private static final String message          = StringCheck.class.getSimpleName() + "#alpha check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public StringAlphaViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public StringAlphaViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public StringAlphaViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public StringAlphaViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
