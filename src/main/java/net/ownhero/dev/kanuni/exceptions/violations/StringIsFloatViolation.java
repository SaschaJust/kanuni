/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class StringIsFloatViolation extends CheckViolation {
	
	private static final String message          = StringCheck.class.getSimpleName() + "#isFloat check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public StringIsFloatViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public StringIsFloatViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public StringIsFloatViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public StringIsFloatViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
