/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.annotations;

/**
 * The Class AnnotationCompileError.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public class AnnotationCompileError extends Error {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8170738271611769214L;
	
	/**
	 * Instantiates a new annotation compile error.
	 */
	public AnnotationCompileError() {
	}
	
	/**
	 * Instantiates a new annotation compile error.
	 *
	 * @param arg0 the arg0
	 */
	public AnnotationCompileError(final String arg0) {
		super(arg0);
	}
	
	/**
	 * Instantiates a new annotation compile error.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public AnnotationCompileError(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * Instantiates a new annotation compile error.
	 *
	 * @param arg0 the arg0
	 */
	public AnnotationCompileError(final Throwable arg0) {
		super(arg0);
	}
	
}
