package com.lumata.common.testing.io;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOSException;
import com.lumata.common.testing.validating.Format;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Properties;

/**
 * This allows to manage the input and output.
 *
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class IOSUtils {
	
	private static final Logger logger = LoggerFactory.getLogger( IOSUtils.class );
	private static String RESOURCE_ROOT = "lumata-common-testing/";
		
	public static String buildResourcePath( String resource ) throws IOSException {
		
		try {
		
			if( !Format.isFile( resource ) ) throw new IOSException( "You cannot build a not valid resource ( " + resource + " )" );
			
			if( String.valueOf( resource.charAt( 0 )).equals("/") ) resource = resource.substring( 1, resource.length());
			
			resource = RESOURCE_ROOT + resource;
			
			logger.debug( "The resource has been built ( " + resource + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return resource;
		
	}
	
	public static String buildResourcePath( String folder, String resource ) throws IOSException {
		
		try {
		
			if( !Format.isFile( resource ) ) throw new IOSException( "You cannot build a not valid resource ( " + resource + " )" );
			
			if( String.valueOf( resource.charAt( 0 )).equals("/") ) resource = resource.substring( 1, resource.length());
			
			if( folder == null ) throw new IOSException( "You cannot build a path containing a not valid folder ( " + folder + " )" );
			
			if( folder.length() > 0 ) if( !String.valueOf(folder.charAt( folder.length() - 1 )).equals("/") ) folder = folder + "/";
			
			resource = RESOURCE_ROOT + folder + resource;
						
			logger.debug( "The resource has been built ( " + resource + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return resource;
		
	}

	public static InputStream loadResourceAsInputStream( String resource ) throws IOSException {
		
		InputStream in = null;
		
		try {
		
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream( IOSUtils.buildResourcePath( resource ) );
			
			if( in == null ) throw new IOSException( "You cannot load a not existing resource ( " + resource + " )" );
			
			logger.debug( "The resource has been loaded as input stream ( " + resource + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return in;
		
	}
	
	public static InputStream loadResourceAsInputStream( String folder, String resource ) throws IOSException {
		
		InputStream in = null;
		
		try {
		
			String path = IOSUtils.buildResourcePath( folder, resource );
			
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream( path );
			
			if( in == null ) throw new IOSException( "You cannot load a not existing resource ( " + path + " )" );
			
			logger.debug( "The resource has been loaded as input stream ( " + path + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return in;
		
	}
	
	public static InputStreamReader loadResourceAsInputStreamReader( String resource ) throws IOSException {
		
		InputStreamReader reader = null;
		
		try {
			
			reader = new InputStreamReader( Thread.currentThread().getContextClassLoader().getResourceAsStream( IOSUtils.buildResourcePath( resource ) ) );
			
			logger.debug( "The resource has been loaded as input stream reader ( " + resource + " )" );
		
		} catch( Exception e ) {
				
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
						
		}
		
		return reader;
		
	}
	
	public static InputStreamReader loadResourceAsInputStreamReader( String folder, String resource ) throws IOSException {
		
		InputStreamReader reader = null;
		
		try {
			
			String path = IOSUtils.buildResourcePath( folder, resource );
			
			reader = new InputStreamReader( Thread.currentThread().getContextClassLoader().getResourceAsStream( path ) );
			
			logger.debug( "The resource has been loaded as input stream reader ( " + path + " )" );
		
		} catch( Exception e ) {
				
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
						
		}
		
		return reader;
		
	}

	public static String loadResourceAsString( String resource ) throws IOSException {
		
		String res = null;
		
		try {
					
			res = IOUtils.toString( IOSUtils.loadResourceAsInputStream( resource ) );
			
			logger.debug( "The resource has been loaded as string ( " + resource + " )" );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
			
		}	
				
		return res;
		
	}
	
	public static String loadResourceAsString( String folder, String resource ) throws IOSException {
		
		String res = null;
		
		try {
				
			res = IOUtils.toString( IOSUtils.loadResourceAsInputStream( folder, resource ) );
			
			logger.debug( "The resource has been loaded as string ( " + IOSUtils.buildResourcePath( folder, resource ) + " )" );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
			
		}	
				
		return res;
		
	}
	
	public static BufferedReader loadResourceAsBufferedReader( String resource ) throws IOSException  {
		
		BufferedReader res = null;
				
		try {
			
			res = new BufferedReader( IOSUtils.loadResourceAsInputStreamReader( resource ) );
			
			logger.debug( "The resource has been loaded as buffered reader ( " + resource + " )" );
																			
		} catch ( IOSException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
		} 

		return res;
		
	}
	
	public static BufferedReader loadResourceAsBufferedReader( String folder, String resource ) throws IOSException  {
		
		BufferedReader res = null;
				
		try {
						
			res = new BufferedReader( IOSUtils.loadResourceAsInputStreamReader( folder, resource ) );
			
			logger.debug( "The resource has been loaded as buffered reader ( " + IOSUtils.buildResourcePath( folder, resource ) + " )" );
																			
		} catch ( IOSException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
		} 

		return res;
		
	}
		
	public static String buildPath( String file ) throws IOSException {
		
		try {
		
			if( !Format.isFile( file ) ) throw new IOSException( "You cannot build a not valid file ( " + file + " )" );
			
			if( String.valueOf( file.charAt( 0 )).equals("/") ) file = file.substring( 1, file.length());
						
			logger.debug( "The file has been built ( " + file + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return file;
		
	}
	
	public static String buildPath( String folder, String file ) throws IOSException {
		
		String path = null;
		
		try {
		
			file = IOSUtils.buildPath( file );
			
			if( folder == null ) throw new IOSException( "You cannot build a path containing a not valid folder ( " + folder + " )" );
			
			if( folder.length() > 0 ) if( !String.valueOf(folder.charAt( folder.length() - 1 )).equals("/") ) folder = folder + "/";
			
			path = folder + file;
			
			logger.debug( "The path has been built ( " + path + " )" );
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return path;
		
	}
		
	public static InputStream loadFileAsInputStream( String file ) throws IOSException {
				
		InputStream in = null;
		
		try {
		
			in = new FileInputStream( IOSUtils.buildPath( file ) );
			
			logger.debug( "The file has been loaded as input stream ( " + file + " )" );
		
		} catch( Exception e ) {
				
			logger.error( e.getMessage() );
				
			e.printStackTrace();
				
			throw new IOSException( "The file is not valid ( " + file + " )" );
						
		}
		
		return in;
		
	}
	
	public static InputStream loadFileAsInputStream( String folder, String file ) throws IOSException {
		
		InputStream in = null;
		String path = null;
		
		try {
		
			path = IOSUtils.buildPath( folder, file );
			
			in = new FileInputStream( path );
			
			logger.debug( "The file has been loaded as input stream ( " + path + " )" );
		
		} catch( Exception e ) {
				
			logger.error( e.getMessage() );
				
			e.printStackTrace();
				
			throw new IOSException( "The file is not valid ( " + path + " )" );
						
		}
			
		return in;
		
	}
	
	public static InputStreamReader loadFileAsInputStreamReader( String file ) throws IOSException {
		
		InputStreamReader reader = null;
		
		try {
			
			reader = new FileReader( IOSUtils.buildPath( file ) );
			
			logger.debug( "The file has been loaded as input stream reader ( " + file + " )" );
			
		} catch( Exception e ) {
				
			logger.error( e.getMessage() );
				
			e.printStackTrace();
				
			throw new IOSException( "The file is not valid ( " + file + " )" );
						
		}
		
		return reader;
		
	}

	public static InputStreamReader loadFileAsInputStreamReader( String folder, String file ) throws IOSException {
		
		InputStreamReader reader = null;
		String path = null;
		
		try {
			
			path = IOSUtils.buildPath( folder, file );
			
			reader = new FileReader( path );
			
			logger.debug( "The file has been loaded as input stream reader ( " + path + " )" );
			
		} catch( Exception e ) {
				
			logger.error( e.getMessage() );
				
			e.printStackTrace();
				
			throw new IOSException( "The file is not valid ( " + path + " )" );
						
		}
		
		return reader;
		
	}
	
	public static String loadFileAsString( String file ) throws IOSException {
		
		String fl = null;
		
		try {
			
			fl = IOUtils.toString( IOSUtils.loadFileAsInputStream( file ) );
			
			logger.debug( "The file has been loaded as string ( " + file + " )" );
																	
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
				
			e.printStackTrace();
				
			throw new IOSException( "The file is not valid ( " + file + " )" );
						
		}
    		
		return fl;
		
	}
	
	public static String loadFileAsString( String folder, String file ) throws IOSException {
		
		String fl = null;
		
		try {
			
			fl = IOUtils.toString( IOSUtils.loadFileAsInputStream( folder, file ) );
			
			logger.debug( "The file has been loaded as string ( " + IOSUtils.buildPath( folder, file ) + " )" );
																	
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
				
			e.printStackTrace();
				
			throw new IOSException( e.getMessage() );
						
		}
    						
		return fl;
		
	}
	
	public static BufferedReader loadFileAsBufferedReader( String file ) throws IOSException  {
		
		BufferedReader fl = null;
		
		try {
			
			fl = new BufferedReader( IOSUtils.loadFileAsInputStreamReader( file ) );
			
			logger.debug( "The file has been loaded as buffered reader ( " + file + " )" );
																			
		} catch ( IOSException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		} 
    		
		return fl;
		
	}
	
	public static BufferedReader loadFileAsBufferedReader( String folder, String file ) throws IOSException  {
		
		BufferedReader fl = null;
		
		try {
			
			fl = new BufferedReader( IOSUtils.loadFileAsInputStreamReader( folder, file ) );
			
			logger.debug( "The file has been loaded as buffered reader ( " + IOSUtils.buildPath( folder, file ) + " )" );
																			
		} catch ( IOSException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		} 
				
		return fl;
		
	}
	
	public static File buildFile( String path ) throws IOSException {
		
		File file = null;
		
		try {
			
			file = new File( path );
			
			try {
				
				File parent = new File( file.getParent() );
				
				if( !parent.isDirectory() ) {
					
					parent.mkdirs();
					
					logger.debug( "The parent path has been created ( " + parent.toString() + " )" );
				
				} else logger.debug( "The parent path already exists ( " + file.toString() + " )" );  

			} catch( Exception e ) {
				
				logger.debug( "The file has not a parent path ( " + file.toString() + " )" );
			
			}
			
			if( !file.exists() ) {
				
				file.createNewFile();
				
				logger.debug( "The path has been created ( " + file.toString() + " )" );
			
			} else logger.debug( "The path already exists ( " + file.toString() + " )" );
			
			if( file == null ) throw new IOSException( "The file is not valid ( " + path + " )" );

		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
		return file;

	}

	public static File createPath( String path ) throws IOSException {
						
		return IOSUtils.buildFile( IOSUtils.buildPath( path ) );

	}

	public static File createPath( String folder, String file ) throws IOSException {
		
		return IOSUtils.buildFile( IOSUtils.buildPath( folder, file ) );

	}
		
	public static void saveResource( String content, String resource ) throws IOSException {
		
		try {
 
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			File file = IOSUtils.createPath( "src/main/resources/" + RESOURCE_ROOT,  resource );
			
			FileOutputStream fop = new FileOutputStream( file );
			
			fop.write( content.getBytes() );
			fop.flush();
			fop.close();
 
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
			
		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
	}
	
	public static void saveResource( String content, String folder, String resource ) throws IOSException {
		
		try {
			
			if( folder == null ) throw new IOSException( "The folder is not valid ( " + folder + " )" );
			
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			File file = IOSUtils.createPath( "src/main/resources/" + RESOURCE_ROOT + folder,  resource );
			
			FileOutputStream fop = new FileOutputStream( file );
			
			fop.write( content.getBytes() );
			fop.flush();
			fop.close();
 
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
			
		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
	}
	
	public static void saveResource( StringBuffer content, String resource ) throws IOSException {
		
		try {
 
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			File file = IOSUtils.createPath( "src/main/resources/" + RESOURCE_ROOT,  resource );
			
			FileOutputStream fop = new FileOutputStream( file );
			
			BufferedOutputStream os = new BufferedOutputStream(fop);
			os.write( String.valueOf( content ).getBytes() );
			os.close();			
			
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
			
		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
	}
	
	public static void saveResource( StringBuffer content, String folder, String resource ) throws IOSException {
		
		try {
			
			if( folder == null ) throw new IOSException( "The folder is not valid ( " + folder + " )" );
			
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			File file = IOSUtils.createPath( "src/main/resources/" + RESOURCE_ROOT + folder,  resource );
			
			FileOutputStream fop = new FileOutputStream( file );
			
			BufferedOutputStream os = new BufferedOutputStream(fop);
			os.write( String.valueOf( content ).getBytes() );
			os.close();	
 
			logger.debug( "The content has been stored in the resource ( " + file.getCanonicalPath() + " )" );
			
		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException( e.getMessage() );
		
		}
		
	}
	
	public static void saveFile( String content, String file ) throws IOSException {
		
		try {
			
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			FileWriter fl = new FileWriter( IOSUtils.createPath( file ) ); 
					
			fl.write(content);
			fl.flush();
			fl.close();
			
			logger.debug( "The content has been stored in the file ( " + file + " )" );
			
		} catch ( IOException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
		}
		
	}
	
	public static void saveFile( String content, String folder, String file ) throws IOSException {
		
		try {
			
			if( folder == null ) throw new IOSException( "The folder is not valid ( " + folder + " )" );
			
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			FileWriter fl = new FileWriter( IOSUtils.createPath(folder, file) ); 
					
			fl.write(content);
			fl.flush();
			fl.close();
			
			logger.debug( "The content has been stored in the file ( " + IOSUtils.createPath(folder, file) + " )" );
			
		} catch ( IOException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
		}
		
	}

	public static void saveFile( StringBuffer content, String file ) throws IOSException {
		
		try {
			
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			FileWriter fl = new FileWriter( IOSUtils.createPath( file ) ); 
					
			BufferedWriter bw = new BufferedWriter(fl);
			bw.write( String.valueOf( content ) );
			bw.close();	
			
			logger.debug( "The content has been stored in the file ( " + file + " )" );
			
		} catch ( IOException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
		}
		
	}
	
	public static void saveFile( StringBuffer content, String folder, String file ) throws IOSException {
		
		try {
			
			if( folder == null ) throw new IOSException( "The folder is not valid ( " + folder + " )" );
			
			if( content == null ) throw new IOSException( "The content is not valid ( " + content + " )" );
			
			FileWriter fl = new FileWriter( IOSUtils.createPath(folder, file) ); 
					
			BufferedWriter bw = new BufferedWriter(fl);
			bw.write( String.valueOf( content ) );
			bw.close();
			
			logger.debug( "The content has been stored in the file ( " + IOSUtils.createPath(folder, file) + " )" );
			
		} catch ( IOException e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new IOSException(e.getMessage() );
		
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
