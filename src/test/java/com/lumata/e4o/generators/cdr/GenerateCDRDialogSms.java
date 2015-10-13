package com.lumata.e4o.generators.cdr;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.types.CDRDialogSMS;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSSHService;
import com.lumata.e4o.testing.common.TCSSHServices;

@TCSSHServices(
	@TCSSHService( ssh_server = "nfsdata", ssh_user = "root" )
)
public class GenerateCDRDialogSms extends ParentTestCase {
	
	@Test( enabled = true )
	public void cdr_dialog_sms( Method method ) throws IOFileException, FieldException {
		
		CDRDialogSMS cdrDialogSms = new CDRDialogSMS();
		
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_dialogsms_" + currentTimestamp + ".csv";
		
		Reporter.log( Log.CREATING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
				
		cdrDialogSms.setOutputPath( "/cdr/", fileName );
		
		Calendar date = Calendar.getInstance();
		
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );

		cdrDialogSms.setMsisdnStrategyFixed( 3399900001L );
		cdrDialogSms.setAddressStrategyFixed( 3399900001L );
		cdrDialogSms.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrDialogSms.setDateStrategyFixed( date );
		cdrDialogSms.setShortCodeStrategyFixed( "5555" );
		cdrDialogSms.setChannelNameStrategyFixed( "SMS" );
		//cdrDialogSms.setTextStrategyIncrement( "ACCEPT ", 0, 1 );
		cdrDialogSms.setTextStrategyFixed( "ACCEPT 1" );
				
		cdrDialogSms.addLines( 1 );
		
		cdrDialogSms.setMsisdnStrategyFixed( 3399900002L );
		cdrDialogSms.setAddressStrategyFixed( 3399900002L );
		cdrDialogSms.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrDialogSms.setDateStrategyFixed( date );
		cdrDialogSms.setShortCodeStrategyFixed( "5555" );
		cdrDialogSms.setChannelNameStrategyFixed( "SMS" );
		//cdrDialogSms.setTextStrategyIncrement( "ACCEPT ", 0, 1 );
		cdrDialogSms.setTextStrategyFixed( "ACCEPT 1" );
				
		cdrDialogSms.addLines( 1 );
		
		cdrDialogSms.save();
		
		//System.out.println( "File name: " + cdrDialogSms.getFileName() );
		
		final String REMOTE_FOLDER = "/usr/local/actrule/data/dialog/tenant1/";
		
		Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in local server"), LOG_TO_STD_OUT );
					
		Reporter.log( Log.PUTTING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
		
		cdrDialogSms.send( sshl.get( "nfsdata", "root" ).getService(), REMOTE_FOLDER, sshl.get( "nfsdata", "root" ).getUser() );
		
		cdrDialogSms.print();
				
	}

}
