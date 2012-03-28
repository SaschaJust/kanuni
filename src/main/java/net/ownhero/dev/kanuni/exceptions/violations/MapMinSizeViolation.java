/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.MapCheck;

/**
 * The Class MapMinSizeViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class MapMinSizeViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = MapCheck.class.getSimpleName() + "#minSize check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new map min size violation.
	 */
	public MapMinSizeViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new map min size violation.
	 *
	 * @param string the string
	 */
	public MapMinSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new map min size violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public MapMinSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new map min size violation.
	 *
	 * @param arg0 the arg0
	 */
	public MapMinSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
