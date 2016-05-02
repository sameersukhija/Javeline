package com.lumata.e4o.generators.cdr;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.types.CDRRevenueRecharge;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSSHService;
import com.lumata.e4o.testing.common.TCSSHServices;

@TCSSHServices(
	@TCSSHService( ssh_server = "collector1", ssh_user = "root" )
)
public class GenerateCDRRevenueRecharge extends ParentTestCase {

	@Test( enabled = true )
	public void cdr_revenue_strategies( Method method ) throws IOFileException, FieldException {

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
		//cdrRevenue.setMsisdnStrategyRandom( 3399900001L, 3399900100L);		
		cdrRevenue.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrRevenue.setDateStrategyFixed( date );
		cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
		cdrRevenue.setBalanceStrategyRandom( 100L, 300L );
		cdrRevenue.setValidityDateStrategyFixed( date );
		cdrRevenue.setDeactivationDateStrategyFixed( date );
		
		cdrRevenue.addLines( 50 );
		
		cdrRevenue.print();
		
		cdrRevenue.save();
		
		final String REMOTE_FOLDER = "/nfsdata/files/cdr/deposit/REVENUE_RECHARGE_CDR/";
		
		cdrRevenue.send( sshl.get( "collector1", "root" ).getService(), REMOTE_FOLDER, sshl.get( "collector1", "root" ).getUser() );
		
		Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in remote server"), LOG_TO_STD_OUT );
		
	}

}
