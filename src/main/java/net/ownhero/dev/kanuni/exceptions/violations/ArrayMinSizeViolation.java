/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class ArrayMinSizeViolation extends CheckViolation {
	
	private static final String message          = ArrayCheck.class.getSimpleName() + "#minSize check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public ArrayMinSizeViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public ArrayMinSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public ArrayMinSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public ArrayMinSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
