/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class CollectionMaxSizeViolation extends CheckViolation {
	
	private static final String message          = CollectionCheck.class.getSimpleName() + "#maxSize check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CollectionMaxSizeViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CollectionMaxSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CollectionMaxSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CollectionMaxSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
