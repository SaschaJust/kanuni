/*
 * Copyright 2005 Sun Microsystems, Inc. All Rights Reserved. DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE
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
import java.util.Random;

import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.spi.AttachProvider;

/**
 * The Class WindowsVirtualMachine.
 */
public final class WindowsVirtualMachine extends HotSpotVirtualMachine {
	
	// An InputStream based on a pipe to the target VM
	/**
	 * The Class PipedInputStream.
	 */
	private final class PipedInputStream extends InputStream {
		
		/** The h pipe. */
		private long hPipe;
		
		/**
		 * Instantiates a new piped input stream.
		 * 
		 * @param hPipe
		 *            the h pipe
		 */
		PipedInputStream(final long hPipe) {
			this.hPipe = hPipe;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.io.InputStream#close()
		 */
		@Override
		public void close() throws IOException {
			if (this.hPipe != -1) {
				closePipe(this.hPipe);
				this.hPipe = -1;
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.io.InputStream#read()
		 */
		@Override
		public synchronized int read() throws IOException {
			final byte[] b = new byte[1];
			final int n = read(b, 0, 1);
			
			if (n == 1) {
				return b[0] & 0xff;
			} else {
				return -1;
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.io.InputStream#read(byte[], int, int)
		 */
		@Override
		public synchronized int read(final byte[] bs,
		                             final int off,
		                             final int len) throws IOException {
			if ((off < 0) || (off > bs.length) || (len < 0) || ((off + len) > bs.length) || ((off + len) < 0)) {
				throw new IndexOutOfBoundsException();
			} else if (len == 0) {
				return 0;
			}
			
			return readPipe(this.hPipe, bs, off, len);
		}
	}
	
	// The enqueue code stub (copied into each target VM).
	/** The Constant stub. */
	private static final byte[] stub;
	
	static {
		System.loadLibrary("attach");
		init(); // native initialization
		stub = generateStub(); // generate stub to copy into target process
	}
	
	/**
	 * Close pipe.
	 * 
	 * @param hPipe
	 *            the h pipe
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void closePipe(long hPipe) throws IOException;
	
	/**
	 * Close process.
	 * 
	 * @param hProcess
	 *            the h process
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void closeProcess(long hProcess) throws IOException;
	
	/**
	 * Connect pipe.
	 * 
	 * @param hPipe
	 *            the h pipe
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void connectPipe(long hPipe) throws IOException;
	
	// -- native methods
	
	/**
	 * Creates the pipe.
	 * 
	 * @param name
	 *            the name
	 * @return the long
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native long createPipe(String name) throws IOException;
	
	/**
	 * Enqueue.
	 * 
	 * @param hProcess
	 *            the h process
	 * @param stub
	 *            the stub
	 * @param cmd
	 *            the cmd
	 * @param pipeName
	 *            the pipe name
	 * @param args
	 *            the args
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void enqueue(long hProcess,
	                           byte[] stub,
	                           String cmd,
	                           String pipeName,
	                           Object... args) throws IOException;
	
	/**
	 * Generate stub.
	 * 
	 * @return the byte[]
	 */
	static native byte[] generateStub();
	
	/**
	 * Inits the.
	 */
	static native void init();
	
	/**
	 * Open process.
	 * 
	 * @param pid
	 *            the pid
	 * @return the long
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native long openProcess(int pid) throws IOException;
	
	/**
	 * Read pipe.
	 * 
	 * @param hPipe
	 *            the h pipe
	 * @param buf
	 *            the buf
	 * @param off
	 *            the off
	 * @param bufLen
	 *            the buf len
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native int readPipe(long hPipe,
	                           byte[] buf,
	                           int off,
	                           int bufLen) throws IOException;
	
	/** The h process. */
	private volatile long hProcess; // handle to the process
	                                
	/**
	 * Instantiates a new windows virtual machine.
	 * 
	 * @param provider
	 *            the provider
	 * @param id
	 *            the id
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public WindowsVirtualMachine(final AttachProvider provider, final String id) throws IOException {
		super(provider, id);
		final int pid = Integer.parseInt(id);
		this.hProcess = openProcess(pid);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sun.tools.attach.VirtualMachine#detach()
	 */
	@Override
	public void detach() throws IOException {
		synchronized (this) {
			if (this.hProcess != -1) {
				closeProcess(this.hProcess);
				this.hProcess = -1;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see sun.tools.attach.HotSpotVirtualMachine#execute(java.lang.String, java.lang.Object[])
	 */
	@Override
	InputStream execute(final String cmd,
	                    final Object... args) throws AgentLoadException, IOException {
		assert args.length <= 3; // includes null
		
		// create a pipe using a random name
		final int r = new Random().nextInt();
		final String pipeName = "\\\\.\\pipe\\javatool" + r;
		final long hPipe = createPipe(pipeName);
		
		// check if we are detached - in theory it's possible that detach is invoked
		// after this check but before we enqueue the command.
		if (this.hProcess == -1) {
			closePipe(hPipe);
			throw new IOException("Detached from target VM");
		}
		
		try {
			// enqueue the command to the process
			enqueue(this.hProcess, stub, cmd, pipeName, args);
			
			// wait for command to complete - process will connect with the
			// completion status
			connectPipe(hPipe);
			
			// create an input stream for the pipe
			final PipedInputStream is = new PipedInputStream(hPipe);
			
			// read completion status
			final int status = readInt(is);
			
			if (status != 0) {
				// special case the load command so that the right exception is thrown
				if ("load".equals(cmd)) {
					throw new AgentLoadException("Failed to load agent library");
				} else {
					throw new IOException("Command failed in target VM");
				}
			}
			
			// return the input stream
			return is;
		} catch (final IOException ioe) {
			closePipe(hPipe);
			throw ioe;
		}
	}
}
