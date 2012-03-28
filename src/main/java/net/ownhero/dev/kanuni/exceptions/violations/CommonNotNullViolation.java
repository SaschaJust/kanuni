/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.Check;

/**
 * The Class CommonNotNullViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CommonNotNullViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = Check.class.getSimpleName() + "#notNull check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new common not null violation.
	 */
	public CommonNotNullViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new common not null violation.
	 *
	 * @param string the string
	 */
	public CommonNotNullViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new common not null violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CommonNotNullViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new common not null violation.
	 *
	 * @param arg0 the arg0
	 */
	public CommonNotNullViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
