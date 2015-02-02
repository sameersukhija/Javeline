package com.lumata.e4o.test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.schema.tenant.Campaigns;
import com.lumata.e4o.schema.tenant.FilesMeta;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcBody;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcValidator;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeRequestAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeResponseAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.campaigns;
import static org.hamcrest.Matchers.equalTo;

public class MultiCampaignsXML {
	
	private static final Logger logger = LoggerFactory.getLogger( MultiCampaignsXML.class );
	
	public enum ExtendedParameters {
		tongue, gender, salary, imei, imsi, hobbies 
	}
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	DAOToken daoToken;
	DAOSubscribers daoSubscribers;
	
	/** VALID PARAMETERS */
	/** xmlrpc valid parameters */
	String msisdn;
	String token;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
			
	}

	@Test( enabled=true, priority = 1 )
	public void getCampaignXML() throws Exception {
		
		Pattern pattern = Pattern.compile( "([0-9]+)[.]xml" );
	      
		ArrayList<FilesMeta> fmList = new ArrayList<FilesMeta>();
		
		FilesMeta fm = new FilesMeta();
		
		String query = select().from( fm ).build();

		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
		
			FilesMeta fmRow = new FilesMeta( rs );
			
			Matcher matcher = pattern.matcher( fmRow.getName() );
			
			if( matcher.find() ) {
				
				fmRow.setName( matcher.group( 1 ) );
				
				fmList.add( fmRow );
				
			}
						
		}		
		
		System.out.println( "FileMeta size( " + fmList.size() + " )" );
		
		StringBuffer sb = new StringBuffer();
		
		for( FilesMeta fmRow : fmList ) {
			
			sb.append( fmRow.getName() ).append( ", " );
					
		}
		
		if( null != sb && sb.length() >= 2 ) { sb.setLength( sb.length() - 2 ); }
		
		ArrayList<Campaigns> campaignList = new ArrayList<Campaigns>();
		
		Campaigns campaigns = new Campaigns();
		
		//query = select().from( campaigns ).where( op( Campaigns.Fields.campaign_id ).not_in( sb.toString() ) ).build();

		query = select().from( campaigns ).build();

		rs = mysql.execQuery( query );
		
		while( rs.next() ) {
		
			Campaigns campaign = new Campaigns( rs );
			
			campaignList.add( campaign );
									
		}
				
		System.out.println( "Campaigns size( " + campaignList.size() + " )" );
		
		System.out.println( "Campaigns present in Files_Meta table and not in Campaigns table" );
		for( FilesMeta fmRow : fmList ) {
			
			boolean found = false;
			
			for( Campaigns cmRow : campaignList ) {
				
				if( cmRow.getCampaignId().equals( Short.valueOf( fmRow.getName() ) ) ) {
					
					found = true;
					
					break;
					
				}
				
			}
			
			if( !found ) {
				
				System.out.println( "campaign_id: " + fmRow.getName() );
				
			}
			
		}
		
		System.out.println( "Campaigns present in Campaigns table and not in Files_Meta table" );
		for( Campaigns cmRow : campaignList ) {
					
			boolean found = false;
			
			for( FilesMeta fmRow : fmList ) {
				
				if( cmRow.getCampaignId().equals( Short.valueOf( fmRow.getName() ) ) ) {
					
					found = true;
					
					break;
					
				}
				
			}
			
			if( !found ) {
				
				System.out.println( "campaign_id: " + cmRow.getCampaignId() );
				
			}
			
		}
		
		System.out.println( "Campaigns by statuses" );
		query = select( Campaigns.Fields.state, count( Campaigns.Fields.state ) ).from( new Campaigns() ).groupBy( Campaigns.Fields.state ).build();

		rs = mysql.execQuery( query );
		
		int campaignsCount = 0;
		
		while( rs.next() ) {
		
			String state = rs.getString( "campaigns.state" );
			Integer count = rs.getInt( "COUNT( campaigns.state )" );
			
			campaignsCount += count;
			
			System.out.println( state + " ( " + count + " )");
									
		}		
		
		XMLRPCRequest.campaignmanager_getCampaigns().call( 	
			actruleServer, 
			xmlrpcBody(
				authentication( superman )
			),
			xmlrpcOptions(
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			),
			xmlrpcValidator(
				campaigns().size( equalTo( String.valueOf( campaignsCount ) ) )
			)
		);

	}

}
