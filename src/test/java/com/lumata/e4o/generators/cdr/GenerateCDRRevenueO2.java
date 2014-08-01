package com.lumata.e4o.generators.cdr;

import java.text.SimpleDateFormat;
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
import com.lumata.e4o.system.cdr.types.CDRRevenueO2;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class GenerateCDRRevenueO2 {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
			
	private final String TOPUP_FORMAT_ = "TUS${datetime}${msisdn}";
	
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM_NE") String environment, @Optional("collector") String collectorServer, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		sshService = env.getService( Service.Type.ssh, collectorServer );
		
		sshUser = "root";
		
	}
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void cdr_revenue_o2() throws IOFileException, FieldException {
				
		CDRRevenueO2 cdrRO2 = new CDRRevenueO2();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_revenue_o2_" + currentTimestamp + "_PPIT_TOPUP.csv";
		
		cdrRO2.setOutputPath( "/cdr/", fileName );
		
		Long msisdn = 3399900001L;
		
		// today
		Calendar date = Calendar.getInstance();
		date.add( Calendar.DATE, 1 );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
				
		String topup = TOPUP_FORMAT_.replace( "${datetime}", sdf.format( date.getTime() ) ).replace( "${msisdn}", msisdn.toString() );
				
		cdrRO2.setSeparator( "  " );
		
		// add 1 day to date
		cdrRO2.setRawDataStrategyFixed( topup.toString() );
		cdrRO2.setRechargeAmountStrategyFixed( 2000L );
				
		cdrRO2.addLines( 1 );
				
		cdrRO2.print();
		
		cdrRO2.save();
		
		cdrRO2.send( sshService, "/nfsdata/files/cdr/deposit/topup/", sshUser );
		
		System.out.println( "File name: " + cdrRO2.getFileName() );
		
	}

}
