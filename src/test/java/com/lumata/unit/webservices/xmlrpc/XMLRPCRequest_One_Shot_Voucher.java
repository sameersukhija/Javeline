package com.lumata.unit.webservices.xmlrpc;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.gui.xmlrpc.type.XMLRPCRequest;
import com.lumata.e4o.schema.tenant.Token;

import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCParam.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCComponent.*;

public class XMLRPCRequest_One_Shot_Voucher {
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( "tenant" ) );
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void allocateOffers() throws Exception {
				
		final Long msisdn = 3399900009L;
		final Integer tokenLimit = 10;
		
		ArrayList<String> tokens = getActiveTokens( msisdn );
		
		for( int t = 0; ( t < tokenLimit && t < tokens.size() ); t++ ) {
			
			String token_code = tokens.get( t );
			
			System.out.println( "TOKEN: " + token_code );
			
			ClientResponse<String> response = XMLRPCRequest.offeroptimizer_allocate.call( 	actruleServer, 
					xmlrpcBody(
						authentication( superman.getUsername(), superman.getPassword() ),
						string( msisdn ),
						string( token_code )
					)
				);

			System.out.println( response.getEntity().toString() );

		
		}
		
	}
	
	@Test(enabled=false, priority = 1 )
	public void acceptAllOffers() throws Exception {
				
		final Long msisdn = 3399900003L;
		
		ArrayList<String> tokens = getTokensWithOffers( msisdn );
		
		for( int t = 0; t < tokens.size(); t++ ) {
			
			String token_code = tokens.get( t );
			
			System.out.println( "TOKEN: " + token_code );
			
			ClientResponse<String> response = XMLRPCRequest.offeroptimizer_accept.call( 	actruleServer, 
					xmlrpcBody(
						authentication( superman.getUsername(), superman.getPassword() ),
						string( msisdn ),
						string( token_code ),
						arrayInt( 1002 )
					)
				);

			System.out.println( response.getEntity().toString() );

		
		}
		
	}
		
	public ArrayList<String> getActiveTokens( Long msisdn ) throws Exception {
	
		ArrayList<String> tokens = new ArrayList<String>();
		
		Token tokenTable = new Token();
		
		String query = select().
						from( tokenTable ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ),
								and( 	
										op( Token.Fields.has_offers_associated ).eq( 0 ),
										op( Token.Fields.qty_current_redeems ).eq( 0 )
								)
						).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			tokens.add( rs.getString( Token.Fields.token_code.name() ) );
			
		}
		
		return tokens;
		
	}
	
	public ArrayList<String> getTokensWithOffers( Long msisdn ) throws Exception {
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		Token tokenTable = new Token();
		
		String query = select().
						from( tokenTable ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ),
								and( 	
										op( Token.Fields.has_offers_associated ).get( 0 )
								)
						).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			tokens.add( rs.getString( Token.Fields.token_code.name() ) );
			
		}
		
		return tokens;
		
	}
	
	@AfterClass
	public void end() {
		
		mysql.close();
		
	}
	
	
}
