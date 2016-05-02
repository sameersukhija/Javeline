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
	@TCSSHService( ssh_server = "nfsdata", ssh_user = "root" )
)
public class GenerateCDRRevenueRechargeMultiFiles extends ParentTestCase {
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void cdr_revenue_multi_files( Method method ) throws IOFileException, FieldException {

		final Integer NUMBER_OF_FILES_TO_GENERATE = 1;

		CDRRevenueRecharge cdrRevenue = new CDRRevenueRecharge();
		
		for( int f = 1; f <= NUMBER_OF_FILES_TO_GENERATE; f++ ) {
		
			String currentTimestamp = Format.getSystemTimestamp();
			
			String fileName = "cdr_revenue_" + currentTimestamp + ".csv";
			
			Reporter.log( Log.CREATING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
						
			cdrRevenue.setOutputPath( "/cdr/", fileName );
					
			/** current date **/
			Calendar date = Calendar.getInstance();
			
			/** add 1 day for each line **/
			FieldDateIncrement increment = new FieldDateIncrement();
			increment.setDayIncrement( 1 );
			
			cdrRevenue.setMsisdnStrategyIncrement( 3399900001L, 1 );		
			cdrRevenue.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
			cdrRevenue.setDateStrategyFixed( date );
			cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
			cdrRevenue.setBalanceStrategyRandom( 100L, 300L );
			cdrRevenue.setValidityDateStrategyFixed( date );
			cdrRevenue.setDeactivationDateStrategyFixed( date );
			
			cdrRevenue.addLines( 100 );
			
			cdrRevenue.print();
			
			cdrRevenue.save();
			
			final String REMOTE_FOLDER = "/gluster01/files/cdr/deposit/REVENUE_RECHARGE_CDR/";
			
			final Integer FTP_CUSTOMER_01_UID = 615;
			
			Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in local server"), LOG_TO_STD_OUT );
						
			Reporter.log( Log.PUTTING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
			
			cdrRevenue.send( sshl.get( "nfsdata", "root" ).getService(), REMOTE_FOLDER, sshl.get( "nfsdata", "root" ).getUser() );
			
			Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in remote server"), LOG_TO_STD_OUT );
					
			cdrRevenue.chown( sshl.get( "nfsdata", "root" ).getService(), sshl.get( "nfsdata", "root" ).getUser(), REMOTE_FOLDER, fileName, FTP_CUSTOMER_01_UID );
						
		}
		
	}

}
