/*
 * Copyright 2005-2006 Sun Microsystems, Inc. All Rights Reserved. DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE
 * HEADER. This code is free software; you can redistribute it and/or modify it under the terms of the GNU General
 * Public License version 2 only, as published by the Free Software Foundation. Sun designates this particular file as
 * subject to the "Classpath" exception as provided by Sun in the LICENSE file that accompanied this code. This code is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License version 2 for more details (a
 * copy is included in the LICENSE file that accompanied this code). You should have received a copy of the GNU General
 * Public License version 2 along with this work; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA. Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara, CA
 * 95054 USA or visit www.sun.com if you need additional information or have any questions.
 */
package sun.tools.attach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.spi.AttachProvider;

/**
 * The HotSpot implementation of com.sun.tools.attach.VirtualMachine.
 */
public abstract class HotSpotVirtualMachine extends VirtualMachine {
	
	/*
	 * The possible errors returned by JPLIS's agentmain.
	 */
	/** The Constant JNI_ENOMEM. */
	private static final int  JNI_ENOMEM             = -4;
	
	/** The Constant ATTACH_ERROR_BADJAR. */
	private static final int  ATTACH_ERROR_BADJAR    = 100;
	
	/** The Constant ATTACH_ERROR_NOTONCP. */
	private static final int  ATTACH_ERROR_NOTONCP   = 101;
	
	/** The Constant ATTACH_ERROR_STARTFAIL. */
	private static final int  ATTACH_ERROR_STARTFAIL = 102;
	
	/** The Constant defaultAttachTimeout. */
	private static final long defaultAttachTimeout   = 5000;
	
	/** The attach timeout. */
	private volatile long     attachTimeout;
	
	/**
	 * Instantiates a new hot spot virtual machine.
	 * 
	 * @param provider
	 *            the provider
	 * @param id
	 *            the id
	 */
	HotSpotVirtualMachine(final AttachProvider provider, final String id) {
		super(provider, id);
	}
	
	/**
	 * Return attach timeout based on the value of the sun.tools.attach.attachTimeout property, or the default timeout
	 * if the property is not set to a positive value.
	 * 
	 * @return the long
	 */
	long attachTimeout() {
		if (this.attachTimeout == 0) {
			synchronized (this) {
				if (this.attachTimeout == 0) {
					try {
						final String s = System.getProperty("sun.tools.attach.attachTimeout");
						this.attachTimeout = Long.parseLong(s);
					} catch (final SecurityException ignore) { //
					} catch (final NumberFormatException ignore) { //
					}
					
					if (this.attachTimeout <= 0) {
						this.attachTimeout = defaultAttachTimeout;
					}
				}
			}
		}
		
		return this.attachTimeout;
	}
	
	// Remote heap dump. The output (error message) can be read from the returned input stream.
	/**
	 * Dump heap.
	 * 
	 * @param args
	 *            the args
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream dumpHeap(final Object... args) throws IOException {
		return executeCommand("dumpheap", args);
	}
	
	/**
	 * Execute the given command in the target VM - specific platform implementation must implement this.
	 * 
	 * @param cmd
	 *            the cmd
	 * @param args
	 *            the args
	 * @return the input stream
	 * @throws AgentLoadException
	 *             the agent load exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	abstract InputStream execute(String cmd,
	                             Object... args) throws AgentLoadException, IOException;
	
	/**
	 * Convenience method for simple commands.
	 * 
	 * @param cmd
	 *            the cmd
	 * @param args
	 *            the args
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private InputStream executeCommand(final String cmd,
	                                   final Object... args) throws IOException {
		try {
			return execute(cmd, args);
		} catch (final AgentLoadException ignore) {
			throw new InternalError("Should not get here");
		}
	}
	
	// --- HotSpot specific methods ---
	
	/*
	 * (non-Javadoc)
	 * @see com.sun.tools.attach.VirtualMachine#getAgentProperties()
	 */
	@Override
	public Properties getAgentProperties() throws IOException {
		InputStream in = null;
		final Properties props = new Properties();
		
		try {
			in = executeCommand("agentProperties");
			props.load(in);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		
		return props;
	}
	
	/**
	 * Send "properties" command to target VM.
	 * 
	 * @return the system properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public Properties getSystemProperties() throws IOException {
		InputStream in = null;
		final Properties props = new Properties();
		
		try {
			in = executeCommand("properties");
			props.load(in);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		
		return props;
	}
	
	// Heap histogram (heap inspection in HotSpot).
	/**
	 * Heap histo.
	 * 
	 * @param args
	 *            the args
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream heapHisto(final Object... args) throws IOException {
		return executeCommand("inspectheap", args);
	}
	
	/**
	 * Load JPLIS agent which will load the agent JAR file and invoke the agentmain method.
	 * 
	 * @param agent
	 *            the agent
	 * @param options
	 *            the options
	 * @throws AgentLoadException
	 *             the agent load exception
	 * @throws AgentInitializationException
	 *             the agent initialization exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public void loadAgent(final String agent,
	                      final String options) throws AgentLoadException, AgentInitializationException, IOException {
		String args = agent;
		
		if (options != null) {
			args = args + '=' + options;
		}
		
		try {
			loadAgentLibrary("instrument", args);
		} catch (final AgentLoadException ignore) {
			throw new InternalError("instrument library is missing in target VM");
		} catch (final AgentInitializationException x) {
			/*
			 * Translate interesting errors into the right exception and message (FIXME: create a better interface to
			 * the instrument implementation so this isn't necessary).
			 */
			final int rc = x.returnValue();
			
			switch (rc) {
				case JNI_ENOMEM:
					throw new AgentLoadException("Insufficient memory");
				case ATTACH_ERROR_BADJAR:
					throw new AgentLoadException("Agent JAR not found or no Agent-Class attribute");
				case ATTACH_ERROR_NOTONCP:
					throw new AgentLoadException("Unable to add JAR file to system class path");
				case ATTACH_ERROR_STARTFAIL:
					throw new AgentInitializationException("Agent JAR loaded but agent failed to initialize");
				default:
					throw new AgentLoadException("Failed to load agent - unknown reason: " + rc);
			}
		}
	}
	
	/**
	 * Load agent library. If isAbsolute is true then the agent library is the absolute path to the library and thus
	 * will not be expanded in the target VM. If isAbsolute is false then the agent library is just a library name and
	 * it will be expended in the target VM.
	 * 
	 * @param agentLibrary
	 *            the agent library
	 * @param isAbsolute
	 *            the is absolute
	 * @param options
	 *            the options
	 * @throws AgentLoadException
	 *             the agent load exception
	 * @throws AgentInitializationException
	 *             the agent initialization exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void loadAgentLibrary(final String agentLibrary,
	                              final boolean isAbsolute,
	                              final String options) throws AgentLoadException,
	                                                   AgentInitializationException,
	                                                   IOException {
		final InputStream in = execute("load", agentLibrary, isAbsolute
		                                                               ? "true"
		                                                               : "false", options);
		
		try {
			final int result = readInt(in);
			
			if (result != 0) {
				throw new AgentInitializationException("Agent_OnAttach failed", result);
			}
		} finally {
			in.close();
		}
	}
	
	/**
	 * Load agent library - library name will be expanded in target VM.
	 * 
	 * @param agentLibrary
	 *            the agent library
	 * @param options
	 *            the options
	 * @throws AgentLoadException
	 *             the agent load exception
	 * @throws AgentInitializationException
	 *             the agent initialization exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public void loadAgentLibrary(final String agentLibrary,
	                             final String options) throws AgentLoadException,
	                                                  AgentInitializationException,
	                                                  IOException {
		loadAgentLibrary(agentLibrary, false, options);
	}
	
	// -- Supporting methods
	
	/**
	 * Load agent - absolute path of library provided to target VM.
	 * 
	 * @param agentLibrary
	 *            the agent library
	 * @param options
	 *            the options
	 * @throws AgentLoadException
	 *             the agent load exception
	 * @throws AgentInitializationException
	 *             the agent initialization exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public void loadAgentPath(final String agentLibrary,
	                          final String options) throws AgentLoadException,
	                                               AgentInitializationException,
	                                               IOException {
		loadAgentLibrary(agentLibrary, true, options);
	}
	
	// same as SIGQUIT
	/**
	 * Local data dump.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void localDataDump() throws IOException {
		executeCommand("datadump").close();
	}
	
	// Print command line flag.
	/**
	 * Prints the flag.
	 * 
	 * @param name
	 *            the name
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream printFlag(final String name) throws IOException {
		return executeCommand("printflag", name);
	}
	
	// -- attach timeout support
	
	/**
	 * Utility method to read an 'int' from the input stream. Ideally we should be using java.util.Scanner here but this
	 * implementation guarantees not to read ahead.
	 * 
	 * @param in
	 *            the in
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	int readInt(final InputStream in) throws IOException {
		final StringBuilder sb = new StringBuilder();
		
		// read to \n or EOF
		int n;
		final byte[] buf = new byte[1];
		
		do {
			n = in.read(buf, 0, 1);
			
			if (n > 0) {
				final char c = (char) buf[0];
				
				if (c == '\n') {
					break; // EOL found
				} else {
					sb.append(c);
				}
			}
		} while (n > 0);
		
		if (sb.length() == 0) {
			throw new IOException("Premature EOF");
		}
		
		int value;
		
		try {
			value = Integer.parseInt(sb.toString());
		} catch (final NumberFormatException ignore) {
			throw new IOException("Non-numeric value found - int expected");
		}
		
		return value;
	}
	
	// Remote ctrl-break. The output of the ctrl-break actions can be read from the input stream.
	/**
	 * Remote data dump.
	 * 
	 * @param args
	 *            the args
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream remoteDataDump(final Object... args) throws IOException {
		return executeCommand("threaddump", args);
	}
	
	// Set JVM command line flag.
	/**
	 * Sets the flag.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @return the input stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream setFlag(final String name,
	                           final String value) throws IOException {
		return executeCommand("setflag", name, value);
	}
}
