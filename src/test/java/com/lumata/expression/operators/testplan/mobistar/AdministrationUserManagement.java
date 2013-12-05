package com.lumata.expression.operators.testplan.mobistar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Security;
import com.lumata.expression.operators.gui.administration.LoginManagementForm;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;

public class AdministrationUserManagement {

	private static final Logger logger = LoggerFactory.getLogger( AdministrationUserManagement.class );
	
	Map<String, Map<String, String>> groups;
	Environment env;
	int user_id = 0;
		
	@Parameters({"browser", "environment", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		groups = AdministrationUserManagement.createGroups();
				
	}
	
	@Test
	public void createUser() {
		
		String user_prefix = "lumata_user";
		String password = "password";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getUser( user_prefix, Security.getMD5( password ), "Lumata", groups.get( "READER_loginmanagement" ) ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.user_create.call( env.getLink() + "xmlrpc/" , params );
					
	}
	
	private static Map<String, Map<String, String>> createGroups() {
		
		Map<String, Map<String, String>> groups = new HashMap<String, Map<String, String>>();
		
		for( LoginManagementForm.DefaultGroups default_group : LoginManagementForm.DefaultGroups.values() ) {
			
			for( LoginManagementForm.GroupAccessLevel access_level : LoginManagementForm.GroupAccessLevel.values() ) {
				
				Map<String, String> group = new HashMap<String, String>();
				group.put( default_group.value(), access_level.name() );
				
 				groups.put( AdministrationUserManagement.getGroupName( default_group.name(), access_level.name() ) , group );
			
			}			
			
		}
		
		return groups;
		
	}
	
	private static String getGroupName( String default_group_name, String access_level ) {
		
		return access_level + "_" + default_group_name;
		
	}
	
}
