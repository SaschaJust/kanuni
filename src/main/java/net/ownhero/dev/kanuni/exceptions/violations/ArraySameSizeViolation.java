/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class ArraySameSizeViolation extends CheckViolation {
	
	private static final String message          = ArrayCheck.class.getSimpleName() + "#sameSize check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public ArraySameSizeViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public ArraySameSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public ArraySameSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public ArraySameSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
