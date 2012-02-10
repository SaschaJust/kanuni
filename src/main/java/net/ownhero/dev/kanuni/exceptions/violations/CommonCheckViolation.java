/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.Check;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CommonCheckViolation extends CheckViolation {
	
	private static final String message          = Check.class.getSimpleName() + "#check check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CommonCheckViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CommonCheckViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CommonCheckViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CommonCheckViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
