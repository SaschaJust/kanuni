/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * The Class CollectionMaxSizeViolation.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class CollectionMaxSizeViolation extends CheckViolation {
	
	/** The Constant message. */
	private static final String message          = CollectionCheck.class.getSimpleName() + "#maxSize check failed. ";
	
	/** The Constant serialVersionUID. */
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * Instantiates a new collection max size violation.
	 */
	public CollectionMaxSizeViolation() {
		super(message);
	}
	
	/**
	 * Instantiates a new collection max size violation.
	 *
	 * @param string the string
	 */
	public CollectionMaxSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * Instantiates a new collection max size violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CollectionMaxSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * Instantiates a new collection max size violation.
	 *
	 * @param arg0 the arg0
	 */
	public CollectionMaxSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
