package com.lumata.e4o.generators.json;

import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.lumata.e4o.schema.tenant.OffoptimAlgorithm;
import com.lumata.e4o.schema.tenant.OffoptimRuleset;
import com.lumata.e4o.schema.tenant.RulesetChannel;
import com.lumata.e4o.schema.tenant.SalesChannels;
import com.lumata.e4o.schema.tenant.TokenType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class CreateRulesJSONConfig {
	
	private static final Logger logger = LoggerFactory.getLogger( CreateRulesJSONConfig.class );
	
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
	public void criterion( @Optional("input/catalogmanager/rules/") String folder, @Optional("rulesList") String file ) throws SQLException {
		
		file = "rulesO2List";
		
		Configuration cfg = new Configuration();
        
		try {

			logger.info( Log.LOADING.createMessage( "Rules Algorithm loading from remote system" ) );
			
			String query = select().from( new OffoptimAlgorithm() ).build();
			
			Map<String,OffoptimAlgorithm> offoptimAlgorithmList = new LinkedHashMap<String,OffoptimAlgorithm>();
						
			ResultSet rs = mysql.execQuery( query );
			
			while( rs.next() ) {
				
				OffoptimAlgorithm offoptimAlgorithm = new OffoptimAlgorithm( rs );
				
				offoptimAlgorithmList.put( String.valueOf( offoptimAlgorithm.getAlgorithmId() ), offoptimAlgorithm );
				
			}			
			
			System.out.println( offoptimAlgorithmList );
			
			logger.info( Log.LOADING.createMessage( "Rules Algorithm loaded from remote system" ) );

			
			logger.info( Log.LOADING.createMessage( "Token Types loading from remote system" ) );
			
			query = select().from( new TokenType() ).build();
						
			rs = mysql.execQuery( query );
			
			logger.info( Log.LOADING.createMessage( "Token Types loaded from remote system" ) );
			
			Map<String,TokenType> tokenTypesList = new LinkedHashMap<String,TokenType>();
			
			while( rs.next() ) {
				
				TokenType tokenType = new TokenType( rs );
				
				tokenTypesList.put( String.valueOf( tokenType.getTokenTypeId() ), tokenType );
				
			}
			
			logger.info( Log.PUTTING.createMessage( "Token Types configuration" ) );
		
			
			logger.info( Log.LOADING.createMessage( "Sales Channels loading from remote system" ) );
						
			query = select().from( new SalesChannels() ).build();
			
			rs = mysql.execQuery( query );
			
			logger.info( Log.LOADING.createMessage( "Sales Channels loaded from remote system" ) );
			
			Map<String,SalesChannels> salesChannelsList = new LinkedHashMap<String,SalesChannels>();
			
			while( rs.next() ) {
				
				SalesChannels sc = new SalesChannels( rs );
				
				salesChannelsList.put( String.valueOf( sc.getChannelId() ), sc );
				
			}
						
			
			logger.info( Log.LOADING.createMessage( "Rules loading from remote system" ) );
						
			query = select().from( new OffoptimRuleset() ).build();
						
			rs = mysql.execQuery( query );
						
			logger.info( Log.LOADING.createMessage( "Rules loaded from remote system" ) );
			
			List<OffoptimRuleset> rulesList = new ArrayList<OffoptimRuleset>();
			
			Map<String, ArrayList<RulesetChannel>> rulesetChannelList = new LinkedHashMap<String, ArrayList<RulesetChannel>>();
			
			while( rs.next() ) {
				
				OffoptimRuleset rule = new OffoptimRuleset( rs );
				
				rulesList.add( rule );
				
				logger.info( Log.LOADING.createMessage( "Channels loading from remote system" ) );
				
				ArrayList<RulesetChannel> channels = new ArrayList<RulesetChannel>();
				
				query = select().from( new RulesetChannel() ).where( op( RulesetChannel.Fields.ruleset_id ).eq( rule.getRulesetId() ) ).build();
				
				ResultSet rsChannel = mysql.execQuery( query );
				
				logger.info( Log.LOADING.createMessage( "Channels loaded from remote system" ) );
							
				while( rsChannel.next() ) {
					
					RulesetChannel rulesetChannel = new RulesetChannel( rsChannel );
					
					channels.add( rulesetChannel );
									
				}
				
				rulesetChannelList.put( String.valueOf( rule.getRulesetId() ), channels );
				
			}
			
			
			logger.info( Log.PUTTING.createMessage( "Rules configuration" ) );
						
			Template rulesTpl = cfg.getTemplate("src/main/resources/templates/json/jsonRulesTpl.ftl");
             
            Map<String, Object> data = new HashMap<String, Object>();

            data.put("rulesList", rulesList);
            
            data.put("offoptimAlgorithmList", offoptimAlgorithmList);
            
            data.put("tokenTypesList", tokenTypesList);
            
            data.put("rulesetChannelList", rulesetChannelList);  
            
            data.put("salesChannelsList", salesChannelsList);  
            
            		            
            String path = System.getProperty( "user.dir" ) + "/src/main/resources/" + folder + file + Format.JSON_EXTENSION;
            
            Writer rulesListJsonFile = new FileWriter( new File( path ) );
            rulesTpl.process( data, rulesListJsonFile );
            rulesListJsonFile.flush();
             
            logger.info( Log.PASSED.createMessage( "Rules configuration completed" ) );
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
		
	}

}
