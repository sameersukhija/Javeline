package com.lumata.e4o.test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.generators.container.FilesData;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.FilesMeta;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class CampaignXML {
	
	private static final Logger logger = LoggerFactory.getLogger( CampaignXML.class );
	
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
		
		//superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
			
	}

	@Test( enabled=true, priority = 1 )
	public void getCampaignXML() throws Exception {
		
		FilesData fd = new FilesData();
		fd.setId( 9 );
		
		String query = select().from( fd ).where( op( FilesData.Fields.id  ).eq() ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		String xml = new String();
		
		while( rs.next() ) {
			
			xml = rs.getString( FilesData.Fields.content.name() );
			
		} 
		
		IOFileUtils.saveResource( xml, "campaignmanager", "campaign.xml" );

	}

}
