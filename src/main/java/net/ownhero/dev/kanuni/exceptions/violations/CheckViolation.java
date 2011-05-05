/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.violations;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class CheckViolation extends RuntimeException {
	
	private static final long serialVersionUID = -2034530386306958199L;
	
	/**
	 * 
	 */
	public CheckViolation() {
		super();
	}
	
	public CheckViolation(final String string) {
		super(string);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public CheckViolation(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public CheckViolation(final Throwable arg0) {
		super(arg0);
	}
	
}
