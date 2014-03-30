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
		
	public Browser() {}
	
	public Browser( JSONObject service, String type ) {
		
		this( service, Type.valueOf( type.toLowerCase() ) );
		
	}

	public Browser( JSONObject browser, Type type ) {
		
		this.browserCfg = browser;
		
		this.type = type; 
		
		if( !browserCfg.isNull("profile") ) { 
			
			this.profile = browserCfg.getJSONObject("profile"); 
			
			if( !browserCfg.getJSONObject("profile").isNull("file") ) { 
				
				this.file = browserCfg.getJSONObject("profile").getJSONObject("file"); 
				
				if( !browserCfg.getJSONObject("profile").getJSONObject("file").isNull("folderName") ) { this.fileFolderName = browserCfg.getJSONObject("profile").getJSONObject("file").getString("folderName"); }
				
				if( !browserCfg.getJSONObject("profile").getJSONObject("file").isNull("fileName") ) { this.fileName = browserCfg.getJSONObject("profile").getJSONObject("file").getString("fileName"); }
				
				if( !browserCfg.getJSONObject("profile").getJSONObject("file").isNull("loadingType") ) { this.fileLoadingType = IOFileUtils.IOLoadingType.valueOf( browserCfg.getJSONObject("profile").getJSONObject("file").getString("loadingType").toUpperCase() ); }
				
			}
						
		}
		
		if( !browserCfg.isNull("options") ) { this.options = browserCfg.getJSONObject("options"); }
				
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
