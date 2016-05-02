package com.lumata.e4o.generators.cdr;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.types.CDRFileInterpretor;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSSHService;
import com.lumata.e4o.testing.common.TCSSHServices;

@TCSSHServices(
	@TCSSHService( ssh_server = "nfsdata", ssh_user = "root" )
)
public class GenerateCDRFileInterpretor extends ParentTestCase {
	
	@Test( enabled = true )
	public void cdr_dialog_sms( Method method ) throws IOFileException, FieldException {
		
		CDRFileInterpretor cdrFileInterpretor = new CDRFileInterpretor();
		
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_fileinterpretor_" + currentTimestamp + ".txt";
		
		Reporter.log( Log.CREATING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
				
		cdrFileInterpretor.setOutputPath( "/cdr/", fileName );
		
		cdrFileInterpretor.setTextStrategyRandom( 20 );
						
		cdrFileInterpretor.addLines( 5 );
		
		cdrFileInterpretor.save();
		
		final String REMOTE_FOLDER = "/nfsdata/files/cdr/deposit/FILE_INTERPRETOR/";
		
		Reporter.log( Log.SAVED.createMessage( method.getName(), fileName + " in local server"), LOG_TO_STD_OUT );
					
		Reporter.log( Log.PUTTING.createMessage( method.getName(), fileName ), LOG_TO_STD_OUT );
		
		cdrFileInterpretor.send( sshl.get( "nfsdata", "root" ).getService(), REMOTE_FOLDER, sshl.get( "nfsdata", "root" ).getUser() );
		
		cdrFileInterpretor.print();
				
	}

}
