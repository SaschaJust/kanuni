/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

import net.ownhero.dev.kanuni.checks.ArrayCheck;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class ArrayValidIndexViolation extends CheckViolation {
	
	private static final String message          = ArrayCheck.class.getSimpleName() + "#validIndex check failed. ";
	
	private static final long   serialVersionUID = -1493037825959519846L;
	
	/**
	 * 
	 */
	public ArrayValidIndexViolation() {
		super(message);
	}
	
	/**
	 * @param string
	 */
	public ArrayValidIndexViolation(final String string) {
		super(message + string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public ArrayValidIndexViolation(final String arg0, final Throwable arg1) {
		super(message + arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public ArrayValidIndexViolation(final Throwable arg0) {
		super(message, arg0);
	}
	
}
