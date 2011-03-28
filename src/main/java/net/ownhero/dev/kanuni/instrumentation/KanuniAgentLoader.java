/**
 * 
 */
package net.ownhero.dev.kanuni.instrumentation;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author Sascha Just <sascha.just@st.cs.uni-saarland.de>
 *
 */
public class KanuniAgentLoader {
	
	private static final String packageName = "net.ownhero.dev.kanuni";
	private static final String path        = packageName.replaceAll("\\.", "/");
	
	/**
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	private static void add(final File source,
	                        final JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name = source.getPath().replace("\\", "/");
				
				if (!name.isEmpty()) {
					if (!name.endsWith("/")) {
						name += "/";
					}
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				
				for (File nestedFile : source.listFiles()) {
					add(nestedFile, target);
				}
				
				return;
			}
			
			JarEntry entry = new JarEntry(source.getPath().replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));
			
			byte[] buffer = new byte[1024];
			
			while (true) {
				int count = in.read(buffer);
				if (count == -1) {
					break;
				}
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
	
	/**
	 * @param file
	 * @param baseDirectory
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String createAgentJarFromClassFiles(final File file,
	                                                   final File baseDirectory) throws FileNotFoundException,
	                                                                            IOException {
		JarOutputStream agentJarFile = new JarOutputStream(new FileOutputStream(file), createManifest());
		
		add(baseDirectory, agentJarFile);
		
		agentJarFile.flush();
		agentJarFile.close();
		
		return file.getAbsolutePath();
	}
	
	/**
	 * @param file
	 * @param jarFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String createAgentJarFromJar(final File file,
	                                            final JarFile jarFile) throws FileNotFoundException, IOException {
		JarOutputStream agentJarFile = new JarOutputStream(new FileOutputStream(file), createManifest());
		
		for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
			JarEntry current = e.nextElement();
			
			if ((current.getName().length() > path.length())
			        && current.getName().substring(0, path.length()).equals(path)
			        && current.getName().endsWith(".class")) {
				agentJarFile.putNextEntry(current);
				jarFile.getInputStream(current);
				
				BufferedInputStream in = new BufferedInputStream(jarFile.getInputStream(current));
				
				byte[] buffer = new byte[1024];
				
				while (true) {
					int count = in.read(buffer);
					if (count == -1) {
						break;
					}
					agentJarFile.write(buffer, 0, count);
				}
				
				in.close();
				agentJarFile.closeEntry();
			}
		}
		
		agentJarFile.flush();
		agentJarFile.close();
		
		return file.getAbsolutePath();
	}
	
	/**
	 * @return
	 */
	private static Manifest createManifest() {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		manifest.getMainAttributes().put(new Attributes.Name("Agent-Class"), KanuniAgent.class.getCanonicalName());
		manifest.getMainAttributes().put(new Attributes.Name("Premain-Class"), KanuniAgent.class.getCanonicalName());
		
		return manifest;
	}
	
	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static String getAgentJar() throws FileNotFoundException, IOException, ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		if (classLoader == null) {
			throw new ClassNotFoundException("Can't get class loader.");
		}
		
		Enumeration<URL> resources = classLoader.getResources(path);
		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			String internalPath = url.getPath();
			
			if (internalPath.startsWith("file:")) {
				internalPath = internalPath.substring(5);
			}
			
			if (internalPath.contains("!")) {
				internalPath = internalPath.substring(0, internalPath.indexOf('!'));
			}
			
			System.err.println("Internal path: " + internalPath);
			
			if (internalPath.endsWith(".jar")) {
				// jar file
				if (internalPath.startsWith("kanuni")) {
					return internalPath;
				} else {
					final File tempJar = File.createTempFile("kanuni-", ".jar");
					tempJar.deleteOnExit();
					return createAgentJarFromJar(tempJar, new JarFile(internalPath));
				}
			} else {
				// class files
				final File tempJar = File.createTempFile("kanuni-", ".jar");
				tempJar.deleteOnExit();
				
				File directory = new File(internalPath);
				return createAgentJarFromClassFiles(tempJar, directory);
			}
		}
		
		throw new ClassNotFoundException();
	}
	
	/**
	 * Gets the recursive directories.
	 * 
	 * @param baseDirectory
	 *            the base directory
	 * @return the recursive directories
	 */
	public static List<File> getRecursiveDirectories(final File baseDirectory) {
		List<File> list = new LinkedList<File>();
		
		for (String subDirectoryPath : baseDirectory.list()) {
			File subDirectory = new File(baseDirectory.getAbsolutePath() + System.getProperty("file.separator")
			        + subDirectoryPath);
			if (subDirectory.isDirectory() && subDirectory.canExecute() && subDirectory.canRead()) {
				list.add(subDirectory);
				list.addAll(getRecursiveDirectories(subDirectory));
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 */
	public static void loadAgent() {
		System.err.println("Booting up kanuni...");
		String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
		int p = nameOfRunningVM.indexOf('@');
		String pid = nameOfRunningVM.substring(0, p);
		
		String jarFilePath;
		
		try {
			jarFilePath = getAgentJar();
			System.err.println("Using agent: " + jarFilePath);
			VirtualMachine vm = VirtualMachine.attach(pid);
			vm.loadAgent(jarFilePath, "");
			vm.detach();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
