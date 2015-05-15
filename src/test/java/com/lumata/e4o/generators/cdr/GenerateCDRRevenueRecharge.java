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
import com.lumata.e4o.system.cdr.types.CDRRevenueRecharge;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class GenerateCDRRevenueRecharge {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM_NE") String environment, @Optional("collector") String collectorServer, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		environment = "E4O_QA3_NE";
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		sshService = env.getService( Service.Type.ssh, collectorServer );
		
		sshUser = "root";
		
	}
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void cdr_revenue_strategies() throws IOFileException, FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "cdr_revenue_strategies" );

		CDRRevenueRecharge cdrRevenue = new CDRRevenueRecharge();
		
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_revenue_" + currentTimestamp + ".csv";
		
		System.out.println( "file: " + fileName );
		
		cdrRevenue.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		// add 1 day to date
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
		
		cdrRevenue.setMsisdnStrategyFixed( 3399900001L );
		cdrRevenue.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrRevenue.setDateStrategyFixed( date );
		cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
		cdrRevenue.setBalanceStrategyRandom( 100L, 300L );
		cdrRevenue.setValidityDateStrategyFixed( date );
		cdrRevenue.setDeactivationDateStrategyFixed( date );
		
		cdrRevenue.addLines( 2 );
		
		cdrRevenue.print();
		
		cdrRevenue.save();
		
		cdrRevenue.send( sshService, "/nfsdata/files/cdr/deposit/REVENUE_RECHARGE_CDR/", sshUser );
		
	}

}
