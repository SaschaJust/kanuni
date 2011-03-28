/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions;

/**
 * @author Sascha Just <sascha.just@own-hero.net>
 * 
 */
public class MalformedAnnotationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4158714801826713782L;
	
	/**
	 * 
	 */
	public MalformedAnnotationException() {
	}
	
	/**
	 * @param arg0
	 */
	public MalformedAnnotationException(final String arg0) {
		super(arg0);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public MalformedAnnotationException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
		
	}
	
	/**
	 * @param arg0
	 */
	public MalformedAnnotationException(final Throwable arg0) {
		super(arg0);
	}
	
}
