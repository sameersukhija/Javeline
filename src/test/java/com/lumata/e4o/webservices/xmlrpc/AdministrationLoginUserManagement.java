package com.lumata.e4o.webservices.xmlrpc;

import static com.lumata.common.testing.orm.Query.select;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Security;
import com.lumata.e4o.exceptions.XMLRPCParserException;
import com.lumata.e4o.schema.tenant.Groups;
import com.lumata.expression.operators.gui.administration.LoginManagementForm;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultFault;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser.ResultType;

public class AdministrationLoginUserManagement {

	private static final Logger logger = LoggerFactory.getLogger( AdministrationLoginUserManagement.class );
	
	List<Groups> groups;
	Environment env;
	int user_id = 0;
		
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		groups = this.createGroups( tenant );
				
	}
	
	@Test
	public void createUser() throws IOFileException, InterruptedException, XMLRPCParserException  {
		
		String user_prefix = "lumata_user";
		String password = "password";
		
		int user_count = 0;
		
		
		
		for( final Groups group : groups ) {
			
			for( final LoginManagementForm.GroupAccessLevel access_level : LoginManagementForm.GroupAccessLevel.values() ) {
				
				StringBuilder test_subject = new StringBuilder();
				
				user_count++;
				
				String user = user_prefix + user_count;
				
				System.out.println( "----- CREATE NEW USER ( " + user + " ) -----" );
				System.out.println( "#####: " + group.getName() );
				
				test_subject.append( "Create user \"" ).append( user )
							.append( "\" with role \"" ).append( group.getName() )
							.append( "\" and access level \"" ).append( access_level.name() ).append( "\"" );
				
				Properties prop = this.getProperties( group.getProperties() );
				System.out.println( prop.toString() );
				String hasAgencies = prop.getProperty( LoginManagementForm.GroupProperties.hasAgencies.name() );
				
				ArrayList<String> params = new ArrayList<String>();
				params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
				params.add( HTTPXMLRPCForm.getUser( user, Security.getMD5( password ), ( hasAgencies == "true" ? "Lumata" : null ), new HashMap<String,String>() {{ put( group.getName(), access_level.name() ); }} ) );
				
				//test_result.append( "\nRequest: " ).append( params.toString() );
								
				ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.user_create.call( env.getLink() + "xmlrpc/" , params );
				
				//test_result.append( "\nResponse: " ).append( response.getEntity().toString() ).append( "\n\n");				
				
				XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
				
				Map<ResultType, Object> result = responseParser.parse();
				
				XMLRPCResultFault resultFault = AdministrationLoginUserManagement.getFault( result );
							
				//TestResult.update( test_subject.toString(), ( resultFault != null ? TestResult.ResultType.FAILS : TestResult.ResultType.PASSED ), ( resultFault != null ? "Code: " + resultFault.getCode() + " - Message: " + resultFault.getMessage() : "" ) );
				
				Thread.sleep( 100 );
				
				//Assert.assertNull( AdministrationLoginUserManagement.getFault( result ) );
				
			}
								
		}	
		
		//System.out.println( TestResult.build() );
		//IOFileUtils.saveFile( test_result.toString(), "/home/adipasquale/test_results", "mobistar.txt" );
						
	}
	
	private List<Groups> createGroups( String tenant ) {		
		
		Groups groupsTable = new Groups();
		
		String query = select( Groups.Fields.id, Groups.Fields.name, Groups.Fields.properties ).from( groupsTable ).build();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ));
		
		ResultSet rs = mysql.execQuery( query );
		
		List<Groups> groups = new ArrayList<Groups>();
				
		try {
			
			while( rs.next() ) {
			
				Groups group = new Groups();
			
				group.setId( rs.getShort( Groups.Fields.id.name() ) );
				group.setName( rs.getString( Groups.Fields.name.name() ).replaceAll( "&" , "&amp;" ) );
				group.setProperties( rs.getString( Groups.Fields.properties.name() ) );
				
				groups.add( group );
				
			}
			
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
		
		}	
		
		return groups;
		
	}
	
	private static XMLRPCResultFault getFault( Map<ResultType, Object> result ) {
		
		XMLRPCResultFault fault = (XMLRPCResultFault)result.get( ResultType.FAULT );		
		
		return fault;
		
	}
	
	public Properties getProperties( String propertiesStr ) {
        
		Properties properties = new Properties();
        
		if( null != propertiesStr ) {
			
			try {
	            
				properties.load( new ByteArrayInputStream( propertiesStr.getBytes() ) );
	        
			} catch (IOException e) {
	           
				logger.error( e.getMessage(), e);
	        
			}
		
		}
	
		return properties;
    
	}
	
}
