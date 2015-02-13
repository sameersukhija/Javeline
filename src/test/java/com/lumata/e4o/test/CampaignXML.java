package com.lumata.e4o.test;

import java.sql.ResultSet;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class CampaignXML {
	
//	private static final Logger logger = LoggerFactory.getLogger( CampaignXML.class );
	
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

	@Test( enabled=false, priority = 1 )
	public void getBasicCampaignXML() throws Exception {
		
		FilesData fd = new FilesData();
		fd.setId( 9 );
		
		String query = select().from( fd ).where( op( FilesData.Fields.id  ).eq() ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		String xml = new String();
		
		while( rs.next() ) {
			
			xml = rs.getString( FilesData.Fields.content.name() );
			
		} 
		
		IOFileUtils.saveResource( xml, "campaignmanager", "campaigns.xml" );

	}
	
	@Test( enabled=true, priority = 1 )
	public void getCampaignXML() throws Exception {
		
		FilesMeta fm = new FilesMeta();
		fm.setName( "285.xml" );
		
		String query = select().from( fm ).where( op( FilesMeta.Fields.name  ).eq() ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		FilesData fd = new FilesData();
		
		while( rs.next() ) {
						
			FilesMeta fmRow = new FilesMeta( rs );
			
			fd.setId( fmRow.getId() );
			
		} 
		
		query = select().from( fd ).where( op( FilesData.Fields.id  ).eq() ).build();
		
		rs = mysql.execQuery( query );
		
		String xml = new String();
		
		while( rs.next() ) {
			
			xml = rs.getString( FilesData.Fields.content.name() );
			
		} 
		
		IOFileUtils.saveResource( xml, "campaignmanager", "campaigns_285.xml" );

	}

}
