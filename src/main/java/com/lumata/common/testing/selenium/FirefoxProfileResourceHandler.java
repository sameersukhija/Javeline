package com.lumata.common.testing.selenium;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.lumata.common.testing.exceptions.IOFileException;

/**
 * This singleton will check consistency of Firefox Profile Folder :
 * 
 * <li> initialize temporary folder of local file system and remove old profile folder
 * <li> fetch from resource into class path existence of profile folder
 * <li> create a new profile folder to work into current instance
 * 
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 */
@Deprecated
public class FirefoxProfileResourceHandler {
	
	/**
	 * 
	 */
	private static final  Logger logger_ = LoggerFactory.getLogger( FirefoxProfileResourceHandler.class );
	
	/**
	 * Prefix for temporary folder
	 */
	private static final String prefix_ = "firefoxProfile";

	
	/**
	 * Local file system object
	 */
	private final FileSystem localFs_ = FileSystems.getDefault();	
	
	/**
	 * Singleton
	 */
	private static volatile FirefoxProfileResourceHandler instance_ = null;
	
	/**
	 * Dictionary of running temp profiles 
	 */
	private static Map<String, Path> profileDictionary_ = null;
	
	/**
	 * init dictionary for fast retrieving already prepared folder
	 */
	static {
		
		logger_.info("Init the temporary folder dictionary.");
		
		profileDictionary_ = new HashMap<>();
	}	
	
	/**
	 * Constructor
	 */
	private FirefoxProfileResourceHandler() {
		
		logger_.info("Looking for old firefox folder into \"tmp\" folder and remove them");

		DirectoryStream.Filter<Path> oldWorkingFolderFinder = new DirectoryStream.Filter<Path>() {
			@Override
			public boolean accept(Path file) throws IOException {
				return (Files.isDirectory(file) && file.getFileName().toString().startsWith(prefix_));
			}
		};

		Path tempDir = localFs_.getPath(System.getProperty("java.io.tmpdir"));

		logger_.info("Temp path -> " + tempDir.toString());

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(tempDir,
				oldWorkingFolderFinder)) {

			for (Path path2BeDelete : stream) {
				// Iterate over the paths in the directory and print filenames
				logger_.info("Find old firefox profile folder -> " + path2BeDelete.toString());

				FileUtils.deleteDirectory(path2BeDelete.toFile());
			}

		} catch (IOException e) {
			
			logger_.error("Error during cleanup temporary folder : " + e.getMessage());
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private static FirefoxProfileResourceHandler getInstance() {
		
		if ( null == instance_ ) {
			instance_ = new FirefoxProfileResourceHandler();
		}	
		
		return instance_;
	}
	
	/**
	 * Core method that handle the profile folder for firefox execution
	 * 
	 * @param resourcePath is the relative path into application class path
	 * @param resourceName is the folder name into application class path
	 * 
	 * @return a Path object with reference of profile folder on local file system
	 * 
	 * @throws IOException 
	 */
	private Path handleInitProfileFolder(String resourcePath, String resourceName) throws IOException {
		
		Path resp = null;
		
		URL resourceAsUrl = null;
	
		logger_.info("Adopted relative resource path -> ("+resourcePath+","+resourceName+")");
		
		resourceAsUrl = Thread.currentThread().getContextClassLoader().getResource(resourcePath + File.separator + resourceName);		

		// profile folder is a resource into a class path JAR
		if (resourceAsUrl.getProtocol().equals("jar")) {

			logger_.info("Resource folder is part of a classpath Jar.");
			
			// create a new temp folder into "tmp" local path
			Path tempFolder = Files.createTempDirectory(prefix_);

			Reporter.log("Create new temporany folder -> " + tempFolder.toAbsolutePath().toString());

			/* A JAR path */
			String jarPath = resourceAsUrl.getPath()
										.substring(	5, 
													resourceAsUrl.getPath().indexOf("!")
												  ); // strip out only the JAR file
			
			@SuppressWarnings("all")
			JarFile jarFile = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Enumeration<JarEntry> entries = jarFile.entries(); // gives ALL entries in jar
													
			Pattern pattern = Pattern.compile(resourceName);
			Matcher matcher = null;
			
			while (entries.hasMoreElements()) {
				
				JarEntry jarEntry = (JarEntry) entries.nextElement();
				
				matcher = pattern.matcher(jarEntry.getName());

				// if resource path is not present , continue loop
				if ( !matcher.find() )
					continue;
				
				File fout = null;

				String destElement = jarEntry.getName().substring(matcher.start());
				
				// Check if the entry is a directory
				if (jarEntry.isDirectory()) {

					// Assume directories are stored parents first then children.
					logger_.info("Extracting directory: " + jarEntry.getName());

					// This is just for demonstration purposes.
					Files.createDirectories(localFs_.getPath(tempFolder.toAbsolutePath().toString(), destElement));
				} else { // it's a file

					logger_.info("Extracting file: " + jarEntry.getName());

					fout = new File(tempFolder + File.separator + destElement);

					copyInputStream(	jarFile.getInputStream(jarEntry),
										new BufferedOutputStream(new FileOutputStream(fout)));
				}		
			}

			resp = localFs_.getPath(tempFolder.toString(), resourceName);
			
			if( null != jarFile ) { jarFile.close(); }
			
		}
		else { // resource path is a real folder for local class path

			logger_.info("Resource folder is a real folder into local file system.");
			
			resp = localFs_.getPath(resourceAsUrl.getPath());
		}
		
		return resp;
	}
	
	/**
	 * Library method to copy among streams
	 */
	private void copyInputStream(InputStream in, OutputStream out) throws IOException {

		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);

		in.close();
		out.close();
	}	
	
	/**
	 * This method returns an <b>Path</b> object with local folder ready to be used into test.
	 * 
	 * @param completeResourcePath is the complete resource path into application class path
	 * 
	 * @return a Path object with reference of profile folder on local file system
	 * 
	 * @throws IOFileException 
	 */
	public static Path prepareProfileFromResource(String completeResourcePath) throws IOFileException {
		
		String[] ans = completeResourcePath.split( File.separator );
		
		String resourceName = ans[ans.length - 1];
		StringBuilder resourcePath = new StringBuilder();
		
		for (String string : ans) {
			if ( !string.equals(ans[ans.length - 1]))
				resourcePath.append(string + File.separator );
		}
		
		// remove latest "/"
		resourcePath.deleteCharAt(resourcePath.length() - 1);
		
		return prepareProfileFromResource( resourcePath.toString(), resourceName);
	}
	
	/**
	 * This method returns an <b>Path</b> object with local folder ready to be used into test.
	 * 
	 * @param resourcePath is the relative path into application class path
	 * @param resourceName is the folder name into application class path
	 * 
	 * @return a Path object with reference of profile folder on local file system
	 * 
	 * @throws IOFileException 
	 */
	public static Path prepareProfileFromResource(String resourcePath, String resourceName) throws IOFileException {

		Path resp = null;
		
		try {
			
			String candidateResource = resourcePath + File.separator + resourceName;
			
			/**
			 * check profiles dictionary
			 */
			if ( profileDictionary_.containsKey(candidateResource) ) {
				
				logger_.debug("Looked profile is already present into running system.");
				
				resp = profileDictionary_.get(candidateResource);
			}
			else {
				
				logger_.debug("Init the requested temporary profile.");
				
				resp = getInstance().handleInitProfileFolder( resourcePath, resourceName);
				
				profileDictionary_.put(candidateResource, resp);
			}
			
		} catch (IOException e) {
			
			logger_.error("Error during firefox folder handling : " + e.getMessage());
			
			throw new IOFileException(e.getMessage());
		}
		
		return resp;
	}	
}
