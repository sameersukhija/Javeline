package com.lumata.common.testing.system;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.io.IOFileUtils;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Browser {

	private static final  Logger logger = LoggerFactory.getLogger( Browser.class );
	
	public enum Type { chrome, ie, firefox, opera, safari } 
	
	private JSONObject browserCfg;
	private Enum<Type> type;
	private JSONObject profile;	
	private JSONObject file;
	private String fileFolderName;
	private String fileName;
	private JSONObject options;
	private Enum<IOFileUtils.IOLoadingType> fileLoadingType; 
		
	/**
	 * JSON Label for "Profile" section
	 */
	public static final String PROFILE_LABEL__ 		= "profile";
	
	/**
	 * JSON Label for "File" section
	 */
	public static final String FILE_LABEL__ 		= "file";
	
	/**
	 * JSON Label for "Folder Name" section
	 */
	public static final String FOLDER_NAME_LABEL__ 	= "folderName";
	
	/**
	 * JSON Label for "File Name" section
	 */
	public static final String FILE_NAME_LABEL__ 	= "fileName";	
	
	/**
	 * JSON Label for "Loading Type" section
	 */
	public static final String LOADING_TYPE_LABEL__	= "loadingType";		
	
	/**
	 * JSON Label for "Options" section
	 */
	public static final String OPTIONS_LABEL__ 		= "options";	
	
	/**
	 * 
	 */
	public Browser() {}
	
	/**
	 * 
	 * @param service
	 * @param type
	 */
	public Browser( JSONObject service, String type ) {
		
		this( service, Type.valueOf( type.toLowerCase() ) );
		
	}

	/**
	 * 
	 * @param browser
	 * @param type
	 */
	public Browser( JSONObject browser, Type type ) {
		
		logger.debug("Init Browser object : " + type);
		
		this.browserCfg = browser;
		this.type = type; 
		
		if( !browserCfg.isNull(PROFILE_LABEL__) ) { 
		
			logger.debug("Browser object (" + type + ") contains profile section.");
			
			this.profile = browserCfg.getJSONObject(PROFILE_LABEL__); 
			
			if( !this.profile.isNull(FILE_LABEL__) ) {
				
				logger.debug("Browser object (" + type + ") contains file section.");
				
				this.file = this.profile.getJSONObject(FILE_LABEL__); 
				
				if( !this.file.isNull(FOLDER_NAME_LABEL__) )
					this.fileFolderName = this.file.getString(FOLDER_NAME_LABEL__);
				
				if( !this.file.isNull(FILE_NAME_LABEL__) )
					this.fileName = this.file.getString(FILE_NAME_LABEL__);
				
				if( !this.file.isNull(LOADING_TYPE_LABEL__) )
					this.fileLoadingType = IOFileUtils.IOLoadingType.valueOf( this.file.getString(LOADING_TYPE_LABEL__).toUpperCase() ); 	
			}	
		}
		else
			logger.debug("Browser object with type " + type + " is empty.");
		
		if( !browserCfg.isNull(OPTIONS_LABEL__) )
			this.options = browserCfg.getJSONObject(OPTIONS_LABEL__); 
	}
 
	public Enum<Type> getType() {
		return this.type;
	}

	public JSONObject getProfile() {
		return this.profile;		
	}
	
	public JSONObject getFile() {
		return this.file;		
	}
	
	public String getFileFolderName() {
		return this.fileFolderName;		
	}
	
	public String getFileName() {
		return this.fileName;		
	}
	
	public Enum<IOFileUtils.IOLoadingType> getFileLoadingType() {
		return this.fileLoadingType;
	}

	public JSONObject getOptions() {
		return this.options;		
	}

	public void setType( String type ) {
		this.type = Type.valueOf( type );
	}

	public void setType( Enum<Type> type ) {
		this.type = type;
	}

	public void setProfile( JSONObject profile ) {
		this.profile = profile;
	}

	public void setFile( JSONObject file ) {
		this.file = file;		
	}
	
	public void setFileFolderName( String fileFolderName ) {
		this.fileFolderName = fileFolderName;		
	}
	
	public void setFileName( String fileName ) {
		this.fileName = fileName;		
	}
	
	public void setFileLoadingType( Enum<IOFileUtils.IOLoadingType> fileLoadingType ) {
		this.fileLoadingType = fileLoadingType;
	}

	public void setOptions( JSONObject options ) {
		this.options = options;		
	}

	@Override
	public String toString() {
				
		return ( this.browserCfg != null ? this.browserCfg.toString() : "{}" );
	}
	
}
