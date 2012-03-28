/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.MapCheck;

/**
 * The Class MapContainsKeyViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class MapContainsKeyViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = MapCheck.class.getSimpleName() + "#containsKey check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new map contains key violation.
	 */
	public MapContainsKeyViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new map contains key violation.
	 *
	 * @param string the string
	 */
	public MapContainsKeyViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new map contains key violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public MapContainsKeyViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new map contains key violation.
	 *
	 * @param arg0 the arg0
	 */
	public MapContainsKeyViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
