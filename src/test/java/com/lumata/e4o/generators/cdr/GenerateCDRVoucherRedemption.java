package com.lumata.e4o.generators.cdr;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
	
	@Test( enabled = false )
	//@Test( enabled = true )
	public void cdr_voucher_redemption() throws IOFileException, FieldException {
		
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
	
		cdrVR.setMsisdnStrategyFixed( 393669393643L );
		cdrVR.setVoucherCodeStrategyFixed( "3OgEhWUi" );
		cdrVR.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrVR.setDateStrategyFixed( date );
		cdrVR.setLocationStrategyFixed( "Milan" );
		cdrVR.setPartnerStrategyFixed( "0" );
				
		cdrVR.addLines( 1 );
				
		cdrVR.print();
		
		cdrVR.save();
		
		cdrVR.send( sshService, "/nfsdata/files/cdr/deposit/VOUCHER_CDR/", sshUser );
		
		System.out.println( "File name: " + cdrVR.getFileName() );
		
	}
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void cdr_voucher_redemption_from_know_vouchers_list() throws IOFileException, FieldException {
		
		System.out.println( "-----------------------------" );
		System.out.println( "cdr_lifecycle" );

		CDRVoucherRedemption cdrVR = new CDRVoucherRedemption();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_voucher_redemption_" + currentTimestamp + ".csv";
		
		cdrVR.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		Map<String, Long> vouchers = new HashMap<String, Long>();
//		vouchers.put( "6R0YxiP5", 3399900001L );
//		vouchers.put( "vPyivngM", 3399900007L );
//		vouchers.put( "eO5NVVs8", 3399900009L );
//		vouchers.put( "vAXJAjPf", 3399900010L );
//		vouchers.put( "jXO8aqRb", 3399900014L );
		vouchers.put( "voucher", 3399900018L );

		for( Map.Entry<String, Long> voucher : vouchers.entrySet() ) {
		
			cdrVR.setMsisdnStrategyFixed( voucher.getValue() );
			cdrVR.setVoucherCodeStrategyFixed( voucher.getKey() );
			cdrVR.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
			cdrVR.setDateStrategyFixed( date );
			cdrVR.setLocationStrategyFixed( "Milan" );
			cdrVR.setPartnerStrategyFixed( "0" );
		
			cdrVR.addLines( 1 );
			
		}
						
		cdrVR.print();
		
		cdrVR.save();
		
		cdrVR.send( sshService, "/nfsdata/files/cdr/deposit/VOUCHER_CDR/", sshUser );
		
		System.out.println( "File name: " + cdrVR.getFileName() );
		
	}

}
