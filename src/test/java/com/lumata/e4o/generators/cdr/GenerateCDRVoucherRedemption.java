package com.lumata.e4o.generators.cdr;

import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.types.CDRLifeCycle;
import com.lumata.e4o.system.cdr.types.CDRVoucherRedemption;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class GenerateCDRVoucherRedemption {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String collectorServer, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
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

		CDRVoucherRedemption cdrVR = new CDRVoucherRedemption();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_lifecycle_" + currentTimestamp + ".csv";
		
		cdrVR.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		// add 1 day to date
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
	
		cdrVR.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrVR.setVoucherCodeStrategyFixed( "voucher61" );
		cdrVR.setDateFormat( "yyyy-MM-dd hh:mm:ss" );
		cdrVR.setDateStrategyFixed( date );
		cdrVR.setLocationStrategyFixed( "Milan" );
		cdrVR.setPartnerStrategyFixed( "Lumata" );
				
		cdrVR.addLines( 1 );
				
		cdrVR.print();
		
		cdrVR.save();
		
		cdrVR.send( sshService, "/nfsdata/files/cdr/deposit/VOUCHER_CDR/", sshUser );
		
		System.out.println( "File name: " + cdrVR.getFileName() );
		
	}

}
