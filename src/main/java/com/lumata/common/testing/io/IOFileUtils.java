package com.lumata.common.testing.io;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.validating.Format;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * This allows to manage the input and output.
 *
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public final class IOFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger( IOFileUtils.class );
	private final static String RESOURCE_ROOT = "";
	public enum IOLoadingType { FILE, RESOURCE };
	/*private final static String RESOURCE_ROOT = "lumata-common-testing/";*/
	
	private IOFileUtils() {}
	
	public static String buildResourcePath( String resource ) throws IOFileException {
		
		try {
		
			if( !Format.isFile( resource ) ) { throw new IOFileException( "You cannot build a not valid resource ( " + resource + " )" ); }
			
			if( String.valueOf( resource.charAt( 0 )).equals("/") ) { resource = resource.substring( 1, resource.length()); }
			
			resource = RESOURCE_ROOT + resource;
			
			logger.debug( "The resource has been built ( " + resource + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		}
		
		return resource;
		
	}
	
	public static String buildResourcePath( String folder, String resource ) throws IOFileException {
		
		try {
		
			if( !Format.isFile( resource ) ) { throw new IOFileException( "You cannot build a not valid resource ( " + resource + " )" ); }
			
			if( String.valueOf( resource.charAt( 0 )).equals("/") ) { resource = resource.substring( 1, resource.length()); }
			
			if( folder == null ) { throw new IOFileException( "You cannot build a path containing a not valid folder ( null )" ); }
			
			if( folder.length() > 0 ) { if( !String.valueOf(folder.charAt( folder.length() - 1 )).equals("/") ) { folder = folder + "/"; } }
			
			resource = RESOURCE_ROOT + folder + resource;
						
			logger.debug( "The resource has been built ( " + resource + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		}
		
		return resource;
		
	}

	public static InputStream loadResourceAsInputStream( String resource ) throws IOFileException {
		
		InputStream in = null;
		
		try {
		
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream( IOFileUtils.buildResourcePath( resource ) );
			
			if( in == null ) { throw new IOFileException( "You cannot load a not existing resource ( null )" ); }
			
			logger.debug( "The resource has been loaded as input stream ( " + resource + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		}
		
		return in;
		
	}
	
	public static InputStream loadResourceAsInputStream( String folder, String resource ) throws IOFileException {
		
		InputStream in = null;
		
		try {
		
			String path = IOFileUtils.buildResourcePath( folder, resource );
			
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream( path );
			
			if( in == null ) { throw new IOFileException( "You cannot load a not existing resource ( null )" ); } 
			
			logger.debug( "The resource has been loaded as input stream ( " + path + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		}
		
		return in;
		
	}
	
	public static InputStreamReader loadResourceAsInputStreamReader( String resource ) throws IOFileException {
		
		InputStreamReader reader = null;
		
		try {
			
			reader = new InputStreamReader( Thread.currentThread().getContextClassLoader().getResourceAsStream( IOFileUtils.buildResourcePath( resource ) ), "UTF8" );
			
			logger.debug( "The resource has been loaded as input stream reader ( " + resource + " )" );
		
		} catch( UnsupportedEncodingException e ) {
				
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
						
		}
		
		return reader;
		
	}
	
	public static InputStreamReader loadResourceAsInputStreamReader( String folder, String resource ) throws IOFileException {
		
		InputStreamReader reader = null;
		
		try {
			
			String path = IOFileUtils.buildResourcePath( folder, resource );
			
			reader = new InputStreamReader( Thread.currentThread().getContextClassLoader().getResourceAsStream( path ), "UTF8" );
			
			logger.debug( "The resource has been loaded as input stream reader ( " + path + " )" );
		
		} catch( UnsupportedEncodingException e ) {
				
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
						
		}
		
		return reader;
		
	}

	public static String loadResourceAsString( String resource ) throws IOFileException {
		
		String res = null;
		
		try {
					
			res = IOUtils.toString( IOFileUtils.loadResourceAsInputStream( resource ) );
			
			logger.debug( "The resource has been loaded as string ( " + resource + " )" );
			
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
			
		}	
				
		return res;
		
	}
	
	public static String loadResourceAsString( String folder, String resource ) throws IOFileException {
		
		String res = null;
		
		try {
				
			res = IOUtils.toString( IOFileUtils.loadResourceAsInputStream( folder, resource ) );
			
			logger.debug( "The resource has been loaded as string ( " + IOFileUtils.buildResourcePath( folder, resource ) + " )" );
			
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
			
		}	
				
		return res;
		
	}
	
	public static BufferedReader loadResourceAsBufferedReader( String resource ) throws IOFileException  {
		
		BufferedReader res = null;
					
		res = new BufferedReader( IOFileUtils.loadResourceAsInputStreamReader( resource ) );
		
		logger.debug( "The resource has been loaded as buffered reader ( " + resource + " )" ); 
		
		return res;
		
	}
	
	public static BufferedReader loadResourceAsBufferedReader( String folder, String resource ) throws IOFileException  {
		
		BufferedReader res = null;
									
		res = new BufferedReader( IOFileUtils.loadResourceAsInputStreamReader( folder, resource ) );
		
		logger.debug( "The resource has been loaded as buffered reader ( " + IOFileUtils.buildResourcePath( folder, resource ) + " )" );
																		 

		return res;
		
	}
		
	public static String buildPath( String file ) throws IOFileException {
		
		if( !Format.isFile( file ) ) { throw new IOFileException( "You cannot build a not valid file ( " + file + " )" ); }
		
		if( String.valueOf( file.charAt( 0 )).equals("/") ) { file = file.substring( 1, file.length()); }
					
		logger.debug( "The file has been built ( " + file + " )" );
		
		return file;
		
	}
	
	public static String buildPath( String folder, String file ) throws IOFileException {
		
		String path = null;
		
		file = IOFileUtils.buildPath( file );
		
		if( folder == null ) { throw new IOFileException( "You cannot build a path containing a not valid folder ( null )" ); }
		
		if( folder.length() > 0 ) { if( !String.valueOf(folder.charAt( folder.length() - 1 )).equals("/") ) { folder = folder + "/"; } }
		
		path = folder + file;
		
		logger.debug( "The path has been built ( " + path + " )" );
		
		return path;
		
	}
		
	public static InputStream loadFileAsInputStream( String file ) throws IOFileException {
				
		InputStream in = null;
		
		try {
			
			in = new FileInputStream( IOFileUtils.buildPath( file ) );
			
			logger.debug( "The file has been loaded as input stream ( " + file + " )" );
		
		} catch( FileNotFoundException e ) {
				
			logger.error( e.getMessage(), e );
				
			throw new IOFileException( "The file is not valid ( " + file + " )", e );
						
		}
		
		return in;
		
	}
	
	public static InputStream loadFileAsInputStream( String folder, String file ) throws IOFileException {
		
		InputStream in = null;
		String path = null;
		
		try {
		
			path = IOFileUtils.buildPath( folder, file );
			
			in = new FileInputStream( path );
			
			logger.debug( "The file has been loaded as input stream ( " + path + " )" );
		
		} catch( FileNotFoundException e ) {
				
			logger.error( e.getMessage(), e );
				
			throw new IOFileException( "The file is not valid ( " + path + " )", e );
						
		}
			
		return in;
		
	}
	
	public static InputStreamReader loadFileAsInputStreamReader( String file ) throws IOFileException {
		
		InputStreamReader reader = null;
		
		try {
			
			reader = new FileReader( IOFileUtils.buildPath( file ) );
			
			logger.debug( "The file has been loaded as input stream reader ( " + file + " )" );
			
		} catch( FileNotFoundException e ) {
				
			logger.error( e.getMessage(), e );
				
			throw new IOFileException( "The file is not valid ( " + file + " )", e );
						
		}
		
		return reader;
		
	}

	public static InputStreamReader loadFileAsInputStreamReader( String folder, String file ) throws IOFileException {
		
		InputStreamReader reader = null;
		String path = null;
		
		try {
			
			path = IOFileUtils.buildPath( folder, file );
			
			reader = new FileReader( path );
			
			logger.debug( "The file has been loaded as input stream reader ( " + path + " )" );
			
		} catch( FileNotFoundException e ) {
				
			logger.error( e.getMessage(), e );
				
			throw new IOFileException( "The file is not valid ( " + path + " )", e );
						
		}
		
		return reader;
		
	}
	
	public static String loadFileAsString( String file ) throws IOFileException {
		
		String fl = null;
		
		try {
			
			fl = IOUtils.toString( IOFileUtils.loadFileAsInputStream( file ) );
			
			logger.debug( "The file has been loaded as string ( " + file + " )" );
																	
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
				
			throw new IOFileException( "The file is not valid ( " + file + " )", e );
						
		}
    		
		return fl;
		
	}
	
	public static String loadFileAsString( String folder, String file ) throws IOFileException {
		
		String fl = null;
		
		try {
			
			fl = IOUtils.toString( IOFileUtils.loadFileAsInputStream( folder, file ) );
			
			logger.debug( "The file has been loaded as string ( " + IOFileUtils.buildPath( folder, file ) + " )" );
																	
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
				
			throw new IOFileException( e.getMessage(), e );
						
		}
    						
		return fl;
		
	}
	
	public static BufferedReader loadFileAsBufferedReader( String file ) throws IOFileException  {
		
		BufferedReader fl = null;
				
		fl = new BufferedReader( IOFileUtils.loadFileAsInputStreamReader( file ) );
		
		logger.debug( "The file has been loaded as buffered reader ( " + file + " )" );
			
		return fl;
		
	}
	
	public static BufferedReader loadFileAsBufferedReader( String folder, String file ) throws IOFileException  {
		
		BufferedReader fl = null;
				
		fl = new BufferedReader( IOFileUtils.loadFileAsInputStreamReader( folder, file ) );
		
		logger.debug( "The file has been loaded as buffered reader ( " + IOFileUtils.buildPath( folder, file ) + " )" );
			
		return fl;
		
	}
	
	public static File buildFile( String path ) throws IOFileException {
		
		File file = null;
		
		try {
			
			file = new File( path );
			
			try {
				
				File parent = new File( file.getParent() );
				
				if( !parent.isDirectory() ) {
					
					parent.mkdirs();
					
					logger.debug( "The parent path has been created ( " + parent.toString() + " )" );
				
				} else { logger.debug( "The parent path already exists ( " + file.toString() + " )" ); } 

			} catch( NullPointerException e ) {
				
				logger.debug( "The file has not a parent path ( " + file.toString() + " )" );
			
			}
			
			if( !file.exists() ) {
				
				file.createNewFile();
				
				logger.debug( "The path has been created ( " + file.toString() + " )" );
			
			} else { logger.debug( "The path already exists ( " + file.toString() + " )" ); }

		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		}
		
		return file;

	}

	public static File createPath( String path ) throws IOFileException {
						
		return IOFileUtils.buildFile( IOFileUtils.buildPath( path ) );

	}

	public static File createPath( String folder, String file ) throws IOFileException {
		
		return IOFileUtils.buildFile( IOFileUtils.buildPath( folder, file ) );

	}
		
	public static void saveResource( String content, String resource ) throws IOFileException {
		
		File file;
		FileOutputStream fop = null;
		
		try {
 
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			file = IOFileUtils.createPath( "src/main/resources/" + RESOURCE_ROOT,  resource );
			
			fop = new FileOutputStream( file );
			
			fop.write( content.getBytes( "UTF-8" ) );
			fop.flush();
			fop.close();
 
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
		
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		} finally {
			
			if (fop != null) { try { fop.close(); } catch ( IOException e ) {} }
			
		}
		
	}
	
	public static void saveResource( String content, String folder, String resource ) throws IOFileException {
		
		File file;
		FileOutputStream fop = null;
		
		try {
			
			if( folder == null ) { throw new IOFileException( "The folder is not valid ( null )" ); }
			
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			file = IOFileUtils.createPath( "src/main/resources/" + RESOURCE_ROOT + folder,  resource );
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fop = new FileOutputStream( file );
			
			fop.write( content.getBytes( "UTF-8" ) );
			fop.flush();
			fop.close();
 
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
		
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		} finally {
			
			if (fop != null) { try { fop.close(); } catch ( IOException e ) {} }
			
		}
		
	}
	
	public static void saveResource( StringBuffer content, String resource ) throws IOFileException {
		
		File file;
		FileOutputStream fop = null;
		BufferedOutputStream os = null;
		
		try {
 
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			file = IOFileUtils.createPath( "src/main/resources/" + RESOURCE_ROOT,  resource );
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fop = new FileOutputStream( file );
						
			os = new BufferedOutputStream(fop);
			os.write( String.valueOf( content ).getBytes( "UTF-8" ) );
			os.close();			
			
			fop.close();
			
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
		
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		} finally {
			
			if (fop != null) { try { fop.close(); } catch ( IOException e ) {} }
			
			if (os != null) { try { os.close(); } catch ( IOException e ) {} }
			
		} 
		
	}
	
	public static void saveResource( StringBuffer content, String folder, String resource ) throws IOFileException {
		
		File file;
		FileOutputStream fop = null;
		BufferedOutputStream os = null;
		
		try {
			
			if( folder == null ) { throw new IOFileException( "The folder is not valid ( null )" ); }
			
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			file = IOFileUtils.createPath( "src/main/resources/" + RESOURCE_ROOT + folder,  resource );
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fop = new FileOutputStream( file );
						
			os = new BufferedOutputStream(fop);
			os.write( String.valueOf( content ).getBytes( "UTF-8" ) );
			os.close();	
 
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
		
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException( e.getMessage(), e );
		
		} finally {
			
			if (fop != null) { try { fop.close(); } catch ( IOException e ) {} }
			
			if (os != null) { try { os.close(); } catch ( IOException e ) {} }
			
		}
		
	}
	
	public static void saveFile( String content, String file ) throws IOFileException {
				
		FileWriter fl = null;
		
		try {
			
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fl = new FileWriter( IOFileUtils.createPath( file ) ); 
					
			fl.write(content);
			fl.flush();
			fl.close();
			
			logger.debug( "The content has been stored in the file ( " + file + " )" );
			
		} catch ( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException(e.getMessage(), e );
		
		} finally {
							
			if (fl != null) { try { fl.close(); } catch ( IOException e ) {} }
		
		}
		
	}
	
	public static void saveFile( String content, String folder, String file ) throws IOFileException {
		
		FileWriter fl = null;
		
		try {
			
			if( folder == null ) { throw new IOFileException( "The folder is not valid ( null )" ); }
			
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fl = new FileWriter( IOFileUtils.createPath(folder, file) ); 
					
			fl.write(content);
			fl.flush();
			fl.close();
			
			logger.debug( "The content has been stored in the file ( " + IOFileUtils.createPath(folder, file) + " )" );
			
		} catch ( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException(e.getMessage(), e );
		
		} finally {
			
			if (fl != null) { try { fl.close(); } catch ( IOException e ) {} }
		
		}
		
	}

	public static void saveFile( StringBuffer content, String file ) throws IOFileException {
		
		FileWriter fl = null;
		BufferedWriter bw = null;
		
		try {
			
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fl = new FileWriter( IOFileUtils.createPath( file ) ); 
					
			bw = new BufferedWriter(fl);
			bw.write( String.valueOf( content ) );
			bw.close();	
			
			logger.debug( "The content has been stored in the file ( " + file + " )" );
			
		} catch ( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException(e.getMessage(), e );
		
		} finally {
			
			if (fl != null) {  try { fl.close(); } catch ( IOException e ) {} }
			if (bw != null) {  try { bw.close(); } catch ( IOException e ) {} }
			
		}
		
	}
	
	public static void saveFile( StringBuffer content, String folder, String file ) throws IOFileException {
		
		FileWriter fl = null;
		BufferedWriter bw = null;
		
		try {
			
			if( folder == null ) { throw new IOFileException( "The folder is not valid ( null )" ); }
			
			if( content == null ) { throw new IOFileException( "The content is not valid ( null )" ); }
			
			if( file == null ) { throw new IOFileException( "The file is not valid ( null )" ); }
			
			fl = new FileWriter( IOFileUtils.createPath(folder, file) ); 
					
			bw = new BufferedWriter(fl);
			bw.write( String.valueOf( content ) );
			bw.close();
			
			logger.debug( "The content has been stored in the file ( " + IOFileUtils.createPath(folder, file) + " )" );
			
		} catch ( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new IOFileException(e.getMessage(), e );
		
		} finally {
			
			if (fl != null)  { try { fl.close(); } catch ( IOException e ) {} }
			if (bw != null)  { try { bw.close(); } catch ( IOException e ) {} }
			
		}
		
	}
	
		
	/*
		
	public static int countFileLine( String path ) throws IOSException {
	    
		BufferedReader reader = reader = IOSUtils.loadFileAsBuffer( path ); 
				
		return IOSUtils.countBufferLine(reader);
		
	}		
	
	public static int countBufferLine( BufferedReader reader ) throws IOSException {
	    		
		int lines = 0;
		
		try {
		
			while (reader.readLine() != null) lines++;
			
			reader.close();
		
		} catch ( IOException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
		}
			
		return lines;
		
	}
	
	*/
	
}
