/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class StringMatchesViolation extends CheckViolation {
	
	private static final String message          = StringCheck.class.getSimpleName() + "#matches check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public StringMatchesViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public StringMatchesViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public StringMatchesViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public StringMatchesViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
