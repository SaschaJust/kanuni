/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.MapCheck;

/**
 * The Class MapMaxSizeViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class MapMaxSizeViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = MapCheck.class.getSimpleName() + "#maxSize check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new map max size violation.
	 */
	public MapMaxSizeViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new map max size violation.
	 *
	 * @param string the string
	 */
	public MapMaxSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new map max size violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public MapMaxSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new map max size violation.
	 *
	 * @param arg0 the arg0
	 */
	public MapMaxSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
