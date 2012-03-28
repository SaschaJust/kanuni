/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.BoundsCheck;

/**
 * The Class BoundsRangeViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class BoundsRangeViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = BoundsCheck.class.getSimpleName() + "#range check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new bounds range violation.
	 */
	public BoundsRangeViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new bounds range violation.
	 *
	 * @param string the string
	 */
	public BoundsRangeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new bounds range violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public BoundsRangeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new bounds range violation.
	 *
	 * @param arg0 the arg0
	 */
	public BoundsRangeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
