/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CompareGreaterViolation extends CheckViolation {
	
	private static final String message          = CompareCheck.class.getSimpleName() + "#greater check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CompareGreaterViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CompareGreaterViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CompareGreaterViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CompareGreaterViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
