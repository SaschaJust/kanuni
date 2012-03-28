/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

/**
 * The Class CheckViolation.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class CheckViolation extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2034530386306958199L;
	
	/**
	 * Instantiates a new check violation.
	 */
	public CheckViolation() {
		super();
	}
	
	/**
	 * Instantiates a new check violation.
	 *
	 * @param string the string
	 */
	public CheckViolation(final String string) {
		super(string);
	}
	
	/**
	 * Instantiates a new check violation.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public CheckViolation(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * Instantiates a new check violation.
	 *
	 * @param arg0 the arg0
	 */
	public CheckViolation(final Throwable arg0) {
		super(arg0);
	}
	
}
