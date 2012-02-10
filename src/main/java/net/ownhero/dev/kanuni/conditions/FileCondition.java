/**
 * 
 */
package net.ownhero.dev.kanuni.conditions;

import java.io.File;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 * 
 */
public final class FileCondition {
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void directory(final File file,
	                             final String formatString,
	                             final Object... arguments) {
		assert file.isDirectory() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not an directory. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void executable(final File file,
	                              final String formatString,
	                              final Object... arguments) {
		assert file.canExecute() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not executable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void executableFile(final File file,
	                                  final String formatString,
	                                  final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isFile() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not a regular file. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canExecute() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not executable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void exists(final File file,
	                          final String formatString,
	                          final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void file(final File file,
	                        final String formatString,
	                        final Object... arguments) {
		assert file.isFile() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not an directory. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void hidden(final File file,
	                          final String formatString,
	                          final Object... arguments) {
		assert file.isHidden() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not hidden. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param size
	 *            size of the file in bytes
	 * @param formatString
	 * @param arguments
	 */
	public static void maxSize(final File file,
	                           final long size,
	                           final String formatString,
	                           final Object... arguments) {
		assert file.length() <= size : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not empty. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param size
	 *            size of the file in bytes
	 * @param formatString
	 * @param arguments
	 */
	public static void minSize(final File file,
	                           final long size,
	                           final String formatString,
	                           final Object... arguments) {
		assert file.length() >= size : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not empty. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void readable(final File file,
	                            final String formatString,
	                            final Object... arguments) {
		assert file.canRead() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not readable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void readableDirectory(final File file,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isDirectory() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not an directory. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canExecute() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not executable. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canRead() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not readable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void readableFile(final File file,
	                                final String formatString,
	                                final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isFile() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not a regular file. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canRead() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not readable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void readableWritableDirectory(final File file,
	                                             final String formatString,
	                                             final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isDirectory() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not an directory. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canExecute() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not executable. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canRead() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not readable. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canWrite() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not writable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void readableWritableFile(final File file,
	                                        final String formatString,
	                                        final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isFile() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not a regular file. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canRead() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not readable. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canWrite() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not writable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void writable(final File file,
	                            final String formatString,
	                            final Object... arguments) {
		assert file.canWrite() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not writable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void writableDirectory(final File file,
	                                     final String formatString,
	                                     final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isDirectory() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not an directory. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canExecute() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not executable. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canWrite() : Condition.getCallerString()
		        + String.format("The directory at specified location `%s` is not writable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
	/**
	 * @param file
	 * @param formatString
	 * @param arguments
	 */
	public static void writableFile(final File file,
	                                final String formatString,
	                                final Object... arguments) {
		assert file.exists() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` does not exist. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.isFile() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not a regular file. Violation: %s", file,
		                        String.format(formatString, arguments));
		assert file.canWrite() : Condition.getCallerString()
		        + String.format("The file at specified location `%s` is not writable. Violation: %s", file,
		                        String.format(formatString, arguments));
	}
	
}
