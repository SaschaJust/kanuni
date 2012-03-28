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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.spi.AttachProvider;

/**
 * Linux implementation of HotSpotVirtualMachine.
 */
public final class LinuxVirtualMachine extends HotSpotVirtualMachine {
	
	/**
	 * InputStream for the socket connection to get target VM.
	 */
	private final class SocketInputStream extends InputStream {
		
		/** The s. */
		int s;
		
		/**
		 * Instantiates a new socket input stream.
		 * 
		 * @param s
		 *            the s
		 */
		SocketInputStream(final int s) {
			this.s = s;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.io.InputStream#close()
		 */
		@Override
		public void close() throws IOException {
			LinuxVirtualMachine.close(this.s);
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
			
			return LinuxVirtualMachine.read(this.s, bs, off, len);
		}
	}
	
	// Indicates if this machine uses the old LinuxThreads.
	/** The is linux threads. */
	static boolean              isLinuxThreads;
	
	// The patch to the socket file created by the target VM.
	/** The path. */
	String                      path;
	
	// protocol version
	/** The Constant PROTOCOL_VERSION. */
	private static final String PROTOCOL_VERSION        = "1";
	
	// known errors
	/** The Constant ATTACH_ERROR_BADVERSION. */
	private static final int    ATTACH_ERROR_BADVERSION = 101;
	
	static {
		System.loadLibrary("attach");
		isLinuxThreads = isLinuxThreads();
	}
	
	/**
	 * Check permissions.
	 * 
	 * @param path
	 *            the path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void checkPermissions(String path) throws IOException;
	
	/**
	 * Close.
	 * 
	 * @param fd
	 *            the fd
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void close(int fd) throws IOException;
	
	/**
	 * Connect.
	 * 
	 * @param fd
	 *            the fd
	 * @param path
	 *            the path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void connect(int fd,
	                           String path) throws IOException;
	
	/**
	 * Gets the linux threads manager.
	 * 
	 * @param pid
	 *            the pid
	 * @return the linux threads manager
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native int getLinuxThreadsManager(int pid) throws IOException;
	
	/**
	 * Checks if is linux threads.
	 * 
	 * @return true, if is linux threads
	 */
	static native boolean isLinuxThreads();
	
	// -- native methods
	
	/**
	 * Read.
	 * 
	 * @param fd
	 *            the fd
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
	static native int read(int fd,
	                       byte[] buf,
	                       int off,
	                       int bufLen) throws IOException;
	
	/**
	 * Send quit to.
	 * 
	 * @param pid
	 *            the pid
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void sendQuitTo(int pid) throws IOException;
	
	/**
	 * Send quit to children of.
	 * 
	 * @param pid
	 *            the pid
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void sendQuitToChildrenOf(int pid) throws IOException;
	
	/**
	 * Socket.
	 * 
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native int socket() throws IOException;
	
	/**
	 * Write.
	 * 
	 * @param fd
	 *            the fd
	 * @param buf
	 *            the buf
	 * @param off
	 *            the off
	 * @param bufLen
	 *            the buf len
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static native void write(int fd,
	                         byte[] buf,
	                         int off,
	                         int bufLen) throws IOException;
	
	/**
	 * Attaches to the target VM.
	 * 
	 * @param provider
	 *            the provider
	 * @param vmid
	 *            the vmid
	 * @throws AttachNotSupportedException
	 *             the attach not supported exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public LinuxVirtualMachine(final AttachProvider provider, final String vmid) throws AttachNotSupportedException,
	        IOException {
		super(provider, vmid);
		
		// This provider only understands pids.
		final int pid = Integer.parseInt(vmid);
		
		// Find the socket file. If not found then we attempt to start the
		// attach mechanism in the target VM by sending it a QUIT signal.
		// Then we attempt to find the socket file again.
		this.path = findSocketFile(pid);
		
		if (this.path == null) {
			final File f = createAttachFile(pid);
			
			try {
				// On LinuxThreads each thread is a process and we don't have the
				// pid of the VMThread which has SIGQUIT unblocked. To workaround
				// this we get the pid of the "manager thread" that is created
				// by the first call to pthread_create. This is parent of all
				// threads (except the initial thread).
				if (isLinuxThreads) {
					final int mpid = getLinuxThreadsManager(pid);
					assert mpid >= 1;
					sendQuitToChildrenOf(mpid);
				} else {
					sendQuitTo(pid);
				}
				
				// give the target VM time to start the attach mechanism
				int i = 0;
				final long delay = 200;
				final int retries = (int) (attachTimeout() / delay);
				
				do {
					try {
						Thread.sleep(delay);
					} catch (final InterruptedException ignore) { // ignore
					}
					
					this.path = findSocketFile(pid);
					i++;
				} while ((i <= retries) && (this.path == null));
				
				if (this.path == null) {
					throw new AttachNotSupportedException("Unable to open socket file: target process not responding "
					        + "or HotSpot VM not loaded");
				}
			} finally {
				f.delete();
			}
		}
		
		// Check that the file owner/permission to avoid attaching to
		// bogus process
		checkPermissions(this.path);
		
		// Check that we can connect to the process
		// - this ensures we throw the permission denied error now rather than
		// later when we attempt to enqueue a command.
		final int s = socket();
		
		try {
			connect(s, this.path);
		} finally {
			close(s);
		}
	}
	
	// On Solaris/Linux a simple handshake is used to start the attach mechanism
	// if not already started. The client creates a .attach_pid<pid> file in the
	// target VM's working directory (or /tmp), and the SIGQUIT handler checks
	// for the file.
	/**
	 * Creates the attach file.
	 * 
	 * @param pid
	 *            the pid
	 * @return the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private File createAttachFile(final int pid) throws IOException {
		final String fn = ".attach_pid" + pid;
		String localPath = "/proc/" + pid + "/cwd/" + fn;
		File f = new File(localPath);
		
		try {
			f.createNewFile();
		} catch (final IOException ignore) {
			localPath = "/tmp/" + fn;
			f = new File(localPath);
			f.createNewFile();
		}
		
		return f;
	}
	
	/**
	 * Detach from the target VM.
	 */
	@Override
	public void detach() {
		synchronized (this) {
			if (this.path != null) {
				this.path = null;
			}
		}
	}
	
	/**
	 * Execute the given command in the target VM.
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
	@Override
	InputStream execute(final String cmd,
	                    final Object... args) throws AgentLoadException, IOException {
		assert args.length <= 3; // includes null
		
		// did we detach?
		String p;
		
		synchronized (this) {
			if (this.path == null) {
				throw new IOException("Detached from target VM");
			}
			
			p = this.path;
		}
		
		// create UNIX socket
		final int s = socket();
		
		// connect to target VM
		try {
			connect(s, p);
		} catch (final IOException x) {
			close(s);
			throw x;
		}
		
		IOException ioe = null;
		
		// connected - write request
		// <ver> <cmd> <args...>
		try {
			writeString(s, PROTOCOL_VERSION);
			writeString(s, cmd);
			
			for (int i = 0; i < 3; i++) {
				if ((i < args.length) && (args[i] != null)) {
					writeString(s, (String) args[i]);
				} else {
					writeString(s, "");
				}
			}
		} catch (final IOException x) {
			ioe = x;
		}
		
		// Create an input stream to read reply.
		final SocketInputStream sis = new SocketInputStream(s);
		
		// Read the command completion status.
		int completionStatus;
		
		try {
			completionStatus = readInt(sis);
		} catch (final IOException x) {
			sis.close();
			if (ioe != null) {
				throw ioe;
			} else {
				throw x;
			}
		}
		
		if (completionStatus != 0) {
			sis.close();
			
			// In the event of a protocol mismatch then the target VM
			// returns a known error so that we can throw a reasonable error.
			if (completionStatus == ATTACH_ERROR_BADVERSION) {
				throw new IOException("Protocol mismatch with target VM");
			}
			
			// Special-case the "load" command so that the right exception is thrown.
			if ("load".equals(cmd)) {
				throw new AgentLoadException("Failed to load agent library");
			} else {
				throw new IOException("Command failed in target VM");
			}
		}
		
		// Return the input stream so that the command output can be read.
		return sis;
	}
	
	// Return the socket file for the given process.
	// Checks working directory of process for .java_pid<pid>. If not
	// found it looks in /tmp.
	/**
	 * Find socket file.
	 * 
	 * @param pid
	 *            the pid
	 * @return the string
	 */
	private String findSocketFile(final int pid) {
		// First check for a .java_pid<pid> file in the working directory
		// of the target process
		final String fn = ".java_pid" + pid;
		String localPath = "/proc/" + pid + "/cwd/" + fn;
		File f = new File(localPath);
		
		if (!f.exists()) {
			// Not found, so try /tmp
			localPath = "/tmp/" + fn;
			f = new File(localPath);
			
			if (!f.exists()) {
				return null; // not found
			}
		}
		
		return localPath;
	}
	
	/**
	 * Write/sends the given to the target VM. String is transmitted in UTF-8 encoding.
	 * 
	 * @param fd
	 *            the fd
	 * @param s
	 *            the s
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void writeString(final int fd,
	                         final String s) throws IOException {
		if (s.length() > 0) {
			byte[] b;
			
			try {
				b = s.getBytes("UTF-8");
			} catch (final UnsupportedEncodingException ignore) {
				throw new InternalError();
			}
			
			write(fd, b, 0, b.length);
		}
		
		final byte[] b = new byte[1];
		b[0] = 0;
		write(fd, b, 0, 1);
	}
}
