package com.lumata.e4o.generators.cdr;

import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.CDR.DELETE;
import com.lumata.e4o.system.cdr.types.CDRLifeCycleDelete;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.testing.common.ParentTestCase;


public class GenerateCDRLifeCycleDelete  {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String collectorServer, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		environment = "E4O_POC_NE";
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		sshService = env.getService( Service.Type.ssh, collectorServer );
		
		sshUser = "root";
		
	}
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void cdr_lifecycle_preferences() throws IOFileException, FieldException {
		
		System.out.println( "-----------------------------" );
		System.out.println( "cdr_lifecycle" );

		CDRLifeCycleDelete cdrLCP = new CDRLifeCycleDelete();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_lifecycle_delete_" + currentTimestamp + ".csv";
		
		cdrLCP.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		// add 1 day to date
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
	
		cdrLCP.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrLCP.setDateStrategyFixed( date );
		cdrLCP.setDeleteStrategyFixed( DELETE.YES );
				
		cdrLCP.addLines( 10 );
				
		cdrLCP.print();
		
		cdrLCP.save();
		
		//cdrLCP.send( sshService, "/nfsdata/files/cdr/deposit/LIFECYCLE_DELETE_CDR/", sshUser );
		
		System.out.println( "File name: " + cdrLCP.getFileName() );
		
	}

}
