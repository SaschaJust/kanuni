/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions.annotations;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class AnnotationCompileError extends Error {
	
	private static final long serialVersionUID = -8170738271611769214L;
	
	/**
	 * 
	 */
	public AnnotationCompileError() {
	}
	
	/**
	 * @param arg0
	 */
	public AnnotationCompileError(final String arg0) {
		super(arg0);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public AnnotationCompileError(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * @param arg0
	 */
	public AnnotationCompileError(final Throwable arg0) {
		super(arg0);
	}
	
}
