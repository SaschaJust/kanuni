/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.CollectionCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class CollectionMinSizeViolation extends CheckViolation {
	
	private static final String message          = CollectionCheck.class.getSimpleName() + "#minSize check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public CollectionMinSizeViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public CollectionMinSizeViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CollectionMinSizeViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CollectionMinSizeViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
