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
import com.lumata.e4o.system.cdr.types.CDRRevenueO2;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class GenerateCDRRevenueO2 {
	
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
				
		CDRRevenueO2 cdrRO2 = new CDRRevenueO2();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_revenue_o2_" + currentTimestamp + ".csv";
		
		cdrRO2.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		cdrRO2.setSeparator( "  " );
		
		// add 1 day to date
		cdrRO2.setRawDataStrategyFixed( "TUS2014073016031333123456789");
		cdrRO2.setRechargeAmountStrategyFixed( 2000L );
				
		cdrRO2.addLines( 20 );
				
		cdrRO2.print();
		
		cdrRO2.save();
		
		cdrRO2.send( sshService, "/nfsdata/files/cdr/deposit/topup/", sshUser );
		
		System.out.println( "File name: " + cdrRO2.getFileName() );
		
	}

}
