/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class StringXdigitViolation extends CheckViolation {
	
	private static final String message          = StringCheck.class.getSimpleName() + "#xdigit check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public StringXdigitViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public StringXdigitViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public StringXdigitViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public StringXdigitViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
