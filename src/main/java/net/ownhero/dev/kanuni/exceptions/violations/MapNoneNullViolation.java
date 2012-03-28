/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.MapCheck;

/**
 * The Class MapNoneNullViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class MapNoneNullViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = MapCheck.class.getSimpleName() + "#noneNull check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new map none null violation.
	 */
	public MapNoneNullViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new map none null violation.
	 *
	 * @param string the string
	 */
	public MapNoneNullViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new map none null violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public MapNoneNullViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new map none null violation.
	 *
	 * @param arg0 the arg0
	 */
	public MapNoneNullViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
