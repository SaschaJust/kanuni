/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.MapCheck;

/**
 * The Class MapContainsValueViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class MapContainsValueViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = MapCheck.class.getSimpleName() + "#containsValue check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new map contains value violation.
	 */
	public MapContainsValueViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new map contains value violation.
	 *
	 * @param string the string
	 */
	public MapContainsValueViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new map contains value violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public MapContainsValueViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new map contains value violation.
	 *
	 * @param arg0 the arg0
	 */
	public MapContainsValueViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
