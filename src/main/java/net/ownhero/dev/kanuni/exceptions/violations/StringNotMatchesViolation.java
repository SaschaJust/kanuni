/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.StringCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class StringNotMatchesViolation extends CheckViolation {
	
	private static final String message          = StringCheck.class.getSimpleName() + "#notMatches check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public StringNotMatchesViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public StringNotMatchesViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public StringNotMatchesViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public StringNotMatchesViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
