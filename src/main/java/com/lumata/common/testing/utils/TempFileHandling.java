package com.lumata.common.testing.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides facilities to handle temporary files (e.g. voucher code, notification template...)
 * during test execution in Selenium Grid environment.
 */
public class TempFileHandling {
	
	/**
	 * 
	 */
	private static final  Logger logger_ = LoggerFactory.getLogger( TempFileHandling.class );
	
	/**
	 * Supported temporary file extension
	 */
	public enum TempFileExtension {
		
		CSV(".csv"),
		HTML(".html");
		
		private String value = null;
		private TempFileExtension(String init) { value = init; }
		
		@Override
		public String toString() { return value; }
	}
	
	/**
	 * Creation path :
	 * 
	 * It is optional, if not provided the execution is supposed locally.
	 * 
	 * This is "local folder" and it is on machine where Selenium test is invoked.
	 * 
	 * This folder describe on local machine where temporary files are created.
	 */
	private static String creationPath = null;
	
	/**
	 * Uploading path :
	 * 
	 * It is optional, if not provided the execution is supposed locally.
	 * 
	 * This is "remote folder" and it is on machine where Selenium Node instance is running.
	 * 
	 * This folder describe on remote machine where temporary files are stored for upload.
	 */
	private static String uploadingPath = null;
		
	
	/**
	 * Configure creation path
	 * 
	 * @param value
	 */
	public static void setCreationPath(String value) {
		
		creationPath = value;
		reset = Boolean.TRUE;
	}

	/**
	 * Configure upload path
	 * 
	 * @param value
	 */
	public static void setUploadingPath(String value) {
		
		uploadingPath = value;
		reset = Boolean.TRUE;
	}	
	
	/**
	 * Singleton instance
	 */
	private static TempFileHandling instance = null;
	
	/**
	 * Reset variable - only for testing scope
	 */
	private static Boolean reset = Boolean.FALSE;
	
	/**
	 * 
	 * @return
	 */
	public static TempFileHandling getInstance() {
		
		if ( instance == null || TempFileHandling.reset ) {
			
			creationTempFolder = null;
			
			instance = new TempFileHandling();
			
			reset = Boolean.FALSE;
		}
		
		return instance;
	}
	
	/**
	 * This parameter is initialized when user provide the environment variable for shared folder handling.
	 * 
	 * If user provides the path where to save temp file this variable will be configured.
	 */
	private static Path creationTempFolder = null;
	
	/**
	 * Private constructor
	 */
	private TempFileHandling() {
		
		if ( creationPath != null )
			try {
				creationTempFolder = Paths.get(creationPath);
				
				logger_.info("Init the folder for temporary files passed via environment variables.");
				
			} catch ( InvalidPathException ex ) {
				
				logger_.error("The passed folder for temporary files is invalid : " + creationPath);
			}
		else
			logger_.info("No temporary files folder provided.");
	}
	
	/**
	 * This method returns creates a text file with temporary purpose for testing
	 * 
	 * @param creationFolder is the local path for thread that executes this code
	 * @param data is a list of text string to be written into file
	 * @param creationPrefix is the file prefix
	 * @param creationExtension is the file extension
	 * 
	 * @return the local File object created
	 * @throws IOException 
	 */
	private File createTempTestFile( Path creationFolder, List<String> data, String creationPrefix, TempFileExtension creationExtension) throws IOException {
		
		Path temp = null;
		
		if ( creationFolder == null ) // std default temp folder
			temp = Files.createTempFile( creationPrefix, creationExtension.toString());
		else
			temp = Files.createTempFile( creationFolder, creationPrefix, creationExtension.toString());
		
	    Files.write( temp, data, Charset.defaultCharset(), StandardOpenOption.WRITE);		
		
		return temp.toFile();			
	}
	
	/**
	 * This method returns creates a text file with temporary purpose for testing.
	 * This method creates the file in local test path for file system
	 * 
	 * @param data is a list of text string to be written into file
	 * @param creationPrefix is the file prefix
	 * @param creationExtension is the file extension
	 * 
	 * @return the local File object created
	 * 
	 * @throws IOException 
	 */
	public static File createTempTestFile( List<String> data, String creationPrefix, TempFileExtension creationExtension) throws IOException {
		
		return getInstance().createTempTestFile( creationTempFolder, data, creationPrefix, creationExtension);	
	}
	
	/**
	 * This methods upload file to specific web element.
	 * 
	 * This method checks if uploadingPath is provided and re-map the absolute path
	 * for remote machine (e.g. Selenium Node where test is executed) 
	 * 
	 * @param element is the element where add the file
	 * @param file2Upload is the wanted file to upload
	 * @throws IOException 
	 */
	public static void uploadFile( WebElement element, Path file2Upload) throws IOException {
		
		String toBeUploaded = null;
		
		Path remotePath = null;
		
		try {
			if ( uploadingPath != null ) {
				remotePath = Paths.get(uploadingPath);
				
				logger_.debug("Provided remote uploading path");
			}
		} catch ( InvalidPathException ex ) {
			// nothing
		}
		
		// if optional uploading path is provided AND it is not the parent of file2Upload
		// the file to be uploaded use the uploadingPath as containing folder
		if ( remotePath != null ) {
			
			if ( !file2Upload.getParent().equals(remotePath) ) {
				toBeUploaded = uploadingPath + file2Upload.getFileName().toString();
				
				logger_.info("Uploaded file in remote.");
			}
		}
		else
			toBeUploaded = file2Upload.toFile().getAbsolutePath();
			
		element.sendKeys(toBeUploaded);
	}	
}
