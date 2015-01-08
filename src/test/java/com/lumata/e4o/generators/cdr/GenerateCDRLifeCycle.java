package com.lumata.e4o.generators.cdr;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;


import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.types.CDRLifeCycle;
import com.lumata.e4o.system.fields.FieldDateIncrement;


public class GenerateCDRLifeCycle {
	
	NetworkEnvironment env;
	Mysql mysql;
	Service sshService;
	String sshUser = "root";
	User superman;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("collector") String collectorServer, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		mysql = new Mysql( env.getDataSource( tenant ) );
		
		sshService = env.getService( Service.Type.ssh, collectorServer );
		
		sshUser = "root";
		
	}
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void cdr_lifecycle_preferences() throws IOFileException, FieldException, SQLException {
		
		System.out.println( "-----------------------------" );
		System.out.println( "cdr_lifecycle" );

		CDRLifeCycle cdrLCP = new CDRLifeCycle();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_lifecycle_" + currentTimestamp + ".csv";
		
		cdrLCP.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		// add 1 day to date
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
	
		// Set<String> options = getOptions();
		
		Set<String> options = new LinkedHashSet<String>();
		for( int o = 1; o <= 1024; o++ ) {
		
			options.add( "option_" + o );
						
		}
		
		cdrLCP.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrLCP.setDateStrategyFixed( date );
		cdrLCP.setNewImeiStrategyIncrement( 300000000000000L, Integer.valueOf( RandomStringUtils.randomNumeric( 9 ) ) );
		cdrLCP.setNewImsiStrategyIncrement( 300000000000000L, Integer.valueOf( RandomStringUtils.randomNumeric( 9 ) ) );
		cdrLCP.setNewSubscriptionDateStrategyFixed( date );
		cdrLCP.setNewProfileStrategyFixed( "prepaid" );
		cdrLCP.setNewRatePlanStrategyFixed( "FUN" );
		cdrLCP.setNewStatusStrategyFixed( CDR.SUBSTATUS.ACTIVE );
		cdrLCP.setNewTongueStrategyFixed( "ENG" );
		cdrLCP.setNewInTagStrategyFixed( "QAIN" );
		cdrLCP.setNewHobbiesStrategyRandom( 4 );
		cdrLCP.setNewGenderStrategyRandom();
		cdrLCP.setNewSalaryStrategyFixed( "2000" );
		cdrLCP.setNewAddressStrategyFixed( "sms" );
		cdrLCP.setNewSponsorStrategyFixed( "" );		
		
		cdrLCP.addLines( 20 );
				
		cdrLCP.print();
		
		//cdrLCP.save();
		
		//cdrLCP.send( sshService, "/nfsdata/files/cdr/deposit/LIFECYCLE_CDR/", sshUser );
		
		System.out.println( "File name: " + cdrLCP.getFileName() );
		
	}
	
}
