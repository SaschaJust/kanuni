/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.annotations;

/**
 * The Class MalformedAnnotationException.
 *
 * @author Sascha Just <sascha.just@own-hero.net>
 */
public class MalformedAnnotationException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4158714801826713782L;
	
	/**
	 * Instantiates a new malformed annotation exception.
	 */
	public MalformedAnnotationException() {
	}
	
	/**
	 * Instantiates a new malformed annotation exception.
	 *
	 * @param arg0 the arg0
	 */
	public MalformedAnnotationException(final String arg0) {
		super(arg0);
	}
	
	/**
	 * Instantiates a new malformed annotation exception.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public MalformedAnnotationException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
		
	}
	
	/**
	 * Instantiates a new malformed annotation exception.
	 *
	 * @param arg0 the arg0
	 */
	public MalformedAnnotationException(final Throwable arg0) {
		super(arg0);
	}
	
}
