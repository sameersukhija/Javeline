package com.lumata.e4o.generators.json;

import static com.lumata.common.testing.orm.Query.select;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.schema.tenant.TokenType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class CreateTokenTypesJSONConfig {
	
	private static final Logger logger = LoggerFactory.getLogger( CreateTokenTypesJSONConfig.class );
	
	private NetworkEnvironment env;
	private Mysql mysql;
	
	@Parameters({ "environment", "tenant" })
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Parameters({ "folder", "file" })
	@Test( enabled = true )
	public void criterion( @Optional("input/catalogmanager/tokenTypes/") String folder, @Optional("tokenTypesList") String file ) throws SQLException {
		
		file = "tokenTypesO2List";
		
		Configuration cfg = new Configuration();
        
		try {
            			
			String query = select().from( new TokenType() ).build();
			
			logger.info( Log.LOADING.createMessage( "Token Types loading from remote system" ) );
			
			ResultSet rs = mysql.execQuery( query );
			
			logger.info( Log.LOADING.createMessage( "Token Types loaded from remote system" ) );
			
			List<TokenType> tokenTypesList = new ArrayList<TokenType>();
			
			while( rs.next() ) {
				
				TokenType tokenType = new TokenType( rs );
				
				tokenTypesList.add( tokenType );
				
			}
			
			logger.info( Log.PUTTING.createMessage( "Token Types configuration" ) );
						
			Template tokenTypesTpl = cfg.getTemplate("src/main/resources/templates/json/jsonTokenTypesTpl.ftl");
             
            Map<String, Object> data = new HashMap<String, Object>();

            data.put("tokenTypesList", tokenTypesList);
            
            String path = System.getProperty( "user.dir" ) + "/src/main/resources/" + folder + file + Format.JSON_EXTENSION;
            
            Writer tokenTypesListJsonFile = new FileWriter( new File( path ) );
            tokenTypesTpl.process( data, tokenTypesListJsonFile );
            tokenTypesListJsonFile.flush();
             
            logger.info( Log.PASSED.createMessage( "Token Types configuration completed" ) );
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
		
	}

}
