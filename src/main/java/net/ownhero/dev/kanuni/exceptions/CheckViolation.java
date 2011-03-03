/**
 * 
 */
package net.ownhero.dev.kanuni.exceptions;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public class CheckViolation extends RuntimeException {
	
	private String            message          = null;
	
	private static final long serialVersionUID = -2034530386306958199L;
	
	public CheckViolation(final String string) {
		this.message = string;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
