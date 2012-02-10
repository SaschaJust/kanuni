/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.BoundsCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class BoundsRangeViolation extends CheckViolation {
	
	private static final String message          = BoundsCheck.class.getSimpleName() + "#range check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public BoundsRangeViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public BoundsRangeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public BoundsRangeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public BoundsRangeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
