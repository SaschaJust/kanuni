/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CompareCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CompareNotNegativeViolation extends CheckViolation {
	
	private static final String message          = CompareCheck.class.getSimpleName() + "#notNegative check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CompareNotNegativeViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CompareNotNegativeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CompareNotNegativeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CompareNotNegativeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
