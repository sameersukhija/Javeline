package com.lumata.e4o.generators.cdr;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.types.CDRVoucherUpload;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSSHService;
import com.lumata.e4o.testing.common.TCSSHServices;


@TCSSHServices(
	@TCSSHService( ssh_server = "collector1", ssh_user = "root" )
)
public class GenerateCDRVoucherUpload extends ParentTestCase {
	
	@Test( enabled = true )
	public void cdr_voucher_upload( Method method ) throws IOFileException, FieldException {

		final Integer NUMBER_OF_FILES_TO_GENERATE = 1;
		
		final Integer NUMBER_OF_VOUCHERS_TO_GENERATE = 100;

		CDRVoucherUpload cdrVoucherUpload = new CDRVoucherUpload();
		
		for( int f = 1; f <= NUMBER_OF_FILES_TO_GENERATE; f++ ) {
		
			String currentTimestamp = Format.getSystemTimestamp();
			
			String fileName = "cdr_voucher_upload_" + currentTimestamp + ".csv";
			
			Reporter.log( Log.CREATING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
						
			cdrVoucherUpload.setOutputPath( "/cdr/", fileName );
					
			/** current date **/
			//Calendar date = Calendar.getInstance();
			
			/** current date **/
			Calendar expiryDate = Calendar.getInstance();
			
			expiryDate.add( Calendar.DAY_OF_MONTH, 300 );
			
			/** add 1 day for each line **/
			FieldDateIncrement increment = new FieldDateIncrement();
			increment.setDayIncrement( 1 );
			
			cdrVoucherUpload.setExpiryDateFormat( "yyyy-MM-dd HH:mm:ss" );
			
			cdrVoucherUpload.setCodeStrategyRandom( 10 );
			cdrVoucherUpload.setPartnerNameStrategyFixed( "O2" );
			cdrVoucherUpload.setOfferNameStrategyFixed( "OFF2" );
			cdrVoucherUpload.setExpiryDateStrategyFixed( expiryDate );
			cdrVoucherUpload.setFormatStrategyRandom();

			cdrVoucherUpload.addLines( NUMBER_OF_VOUCHERS_TO_GENERATE );
			
			cdrVoucherUpload.print();
			
			cdrVoucherUpload.save();

			final String REMOTE_FOLDER = "/nfsdata/files/cdr/deposit/VOUCHER_UPLOAD_CDR/";
			
			Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in local server"), LOG_TO_STD_OUT );
						
			Reporter.log( Log.PUTTING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
			
			cdrVoucherUpload.send( sshl.get( "collector1", "root" ).getService(), REMOTE_FOLDER, sshl.get( "collector1", "root" ).getUser() );
			
			Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in remote server"), LOG_TO_STD_OUT );
						
		}
		
	}

}
