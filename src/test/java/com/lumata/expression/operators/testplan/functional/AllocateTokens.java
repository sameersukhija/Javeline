package com.lumata.expression.operators.testplan.functional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.entities.Token;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class AllocateTokens {

	private static final Logger logger = LoggerFactory.getLogger( AllocateTokens.class );
	
	Environment env;	
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
				
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1, enabled = true )
	public void allocateAllTokens( @Optional("tenant") String tenant ) {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ));
				
		Token tk = new Token();
		
		String query = select().from(tk).where( op(Token.Fields.qty_current_redeems).eq( 0 ) ).build();
		System.out.println( query );
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
			while( rs.next() ) {
				
				ArrayList<String> params = new ArrayList<String>();
				params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
				params.add( HTTPXMLRPCForm.getStringParam( "393669393643" ) );
				params.add( HTTPXMLRPCForm.getStringParam( rs.getString( Token.Fields.token_code.name() ) ) );
				
				ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.offeroptimizer_allocate.call( env.getLink() + "xmlrpc/" , params );				
				
			}
		} catch (SQLException e ) {
			logger.error( e.getMessage(), e );
		}
	
	}
	
}
