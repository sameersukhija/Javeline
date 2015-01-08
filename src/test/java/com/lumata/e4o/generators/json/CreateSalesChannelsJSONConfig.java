package com.lumata.e4o.generators.json;

import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.select;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lumata.common.testing.validating.Format.NumberFormat;
import com.lumata.e4o.schema.tenant.SalesChannels;
import com.lumata.e4o.schema.tenant.SetHobbies;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class CreateSalesChannelsJSONConfig {
	
	private static final Logger logger = LoggerFactory.getLogger( CreateSalesChannelsJSONConfig.class );
	
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
	public void criterion( @Optional("input/administrationmanager/salesChannels/") String folder, @Optional("salesChannelsList") String file ) throws SQLException {
		
		file = "salesChannelsO2List";
		
		Configuration cfg = new Configuration();
        
		try {
            
			String query = select().from( new SalesChannels() ).build();
			
			ResultSet rs = mysql.execQuery( query );
			
			List<SalesChannels> salesChannelsList = new ArrayList<SalesChannels>();
			
			while( rs.next() ) {
				
				SalesChannels sc = new SalesChannels( rs );
				
				salesChannelsList.add( sc );
				
			}
			
			Template salesChannelsTpl = cfg.getTemplate("src/main/resources/templates/json/jsonSalesChannelsTpl.ftl");
             
            Map<String, Object> data = new HashMap<String, Object>();

            data.put("salesChannelsList", salesChannelsList);
            
            String path = System.getProperty( "user.dir" ) + "/src/main/resources/" + folder + file + Format.JSON_EXTENSION;
            
            Writer salesChannelsListJsonFile = new FileWriter( new File( path ) );
            salesChannelsTpl.process( data, salesChannelsListJsonFile );
            salesChannelsListJsonFile.flush();
              
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
		
	}

}
