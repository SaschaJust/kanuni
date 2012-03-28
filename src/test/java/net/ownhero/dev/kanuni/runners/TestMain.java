/**
 * 
 */
package net.ownhero.dev.kanuni.runners;

import java.util.LinkedList;
import java.util.List;

import net.ownhero.dev.kanuni.annotations.AnnotatedForArray;
import net.ownhero.dev.kanuni.annotations.AnnotatedForBounds;
import net.ownhero.dev.kanuni.annotations.AnnotatedForCollection;
import net.ownhero.dev.kanuni.annotations.AnnotatedForCompare;
import net.ownhero.dev.kanuni.annotations.AnnotatedForString;
import net.ownhero.dev.kanuni.instrumentation.KanuniAgent;

/**
 * The Class TestMain.
 *
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 */
public final class TestMain {
	
	/**
	 * The Class Error.
	 */
	class Error {
		
		/** The test. */
		private String              test;
		
		/** The message. */
		private String              message;
		
		/** The trace. */
		private StackTraceElement[] trace;
		
		/**
		 * Instantiates a new error.
		 *
		 * @param test the test
		 * @param message the message
		 * @param trace the trace
		 */
		Error(final String test, final String message, final StackTraceElement[] trace) {
			setTest(test);
			setMessage(message);
			setTrace(trace);
		}
		
		/**
		 * Gets the message.
		 *
		 * @return the message
		 */
		public String getMessage() {
			return this.message;
		}
		
		/**
		 * Gets the test.
		 *
		 * @return the test
		 */
		public String getTest() {
			return this.test;
		}
		
		/**
		 * Gets the trace.
		 *
		 * @return the trace
		 */
		public StackTraceElement[] getTrace() {
			return this.trace;
		}
		
		/**
		 * Sets the message.
		 *
		 * @param message the message to set
		 */
		public void setMessage(final String message) {
			this.message = message;
		}
		
		/**
		 * Sets the test.
		 *
		 * @param test the test to set
		 */
		public void setTest(final String test) {
			this.test = test;
		}
		
		/**
		 * Sets the trace.
		 *
		 * @param trace the trace to set
		 */
		public void setTrace(final StackTraceElement[] trace) {
			this.trace = trace;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Test failed: ");
			builder.append(getTest()).append(ls);
			builder.append(">>> ").append(getMessage()).append(ls);
			for (StackTraceElement element : getTrace()) {
				builder.append(">   ").append(element).append(ls);
			}
			builder.append(ls);
			
			return builder.toString();
		}
	}
	
	static {
		KanuniAgent.initialize();
	}
	
	/** The Constant ls. */
	private final static String ls     = System.getProperty("line.separator");
	
	/** The errors. */
	private static List<Error>  errors = new LinkedList<Error>();
	
	/**
	 * Adds the error.
	 *
	 * @param test the test
	 * @param reason the reason
	 */
	private static void addError(final String test,
	                             final String... reason) {
		TestMain.errors.add(new TestMain().new Error(test, reason.length > 0
		                                                                    ? reason[0]
		                                                                    : "", new Throwable().getStackTrace()));
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		AnnotatedForString afs = new AnnotatedForString();
		new AnnotatedForCollection();
		AnnotatedForArray afa = new AnnotatedForArray();
		new AnnotatedForBounds();
		new AnnotatedForCompare();
		
		try {
			afs.maxLength3("123456789");
			addError("maxLength3_invalid");
		} catch (AssertionError e) {
		}
		
		try {
			afs.alpha("123456789");
			addError("alpha_invalid");
		} catch (AssertionError e) {
		}
		
		try {
			afs.sameLength("123", "12345");
			addError("sameLength_invalid");
		} catch (AssertionError e) {
		}
		
		try {
			afs.sameLength1("123", "123", "123", "12345", "12345");
		} catch (AssertionError e) {
			addError("sameLength1_valid");
		}
		
		try {
			afs.sameLength1("123", "123", "123", "123456", "12345");
			addError("sameLength1_invalid");
		} catch (AssertionError e) {
		}
		
		try {
			afs.sameLength1("123", "123", "1234", "12345", "12345");
			addError("sameLength1_invalid");
		} catch (AssertionError e) {
		}
		
		try {
			afa.testArrayContainsElement(new int[] { 1, 2, 3 }, 0);
			addError("testArrayContainsElement_invalid");
		} catch (AssertionError e) {
		}
		
		try {
			afa.testArrayContainsElement(new int[] { 1, 2, 3 }, 2);
		} catch (AssertionError e) {
			addError("testArrayContainsElement_valid");
		}
		
		System.err.println("Errors: " + errors.size());
		for (Error error : errors) {
			System.err.println(error);
		}
	}
	
}
