package com.lumata.e4o.generators.cdr;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.CDR.DELETE;
import com.lumata.e4o.system.cdr.types.CDRLifeCycleDelete;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSSHService;
import com.lumata.e4o.testing.common.TCSSHServices;

@TCSSHServices(
	@TCSSHService( ssh_server = "nfsdata", ssh_user = "root" )
)
public class GenerateCDRLifeCycleDelete extends ParentTestCase {
	
	@Test( enabled = true )
	public void cdr_lifecycle_preferences() throws IOFileException, FieldException {
		
		System.out.println( "-----------------------------" );
		System.out.println( "cdr_lifecycle" );

		CDRLifeCycleDelete cdrLCP = new CDRLifeCycleDelete();
				
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_lifecycle_delete_" + currentTimestamp + ".csv";
		
		cdrLCP.setOutputPath( "/cdr/", fileName );
				
		// today
		Calendar date = Calendar.getInstance();
		
		// add 1 day to date
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
	
		cdrLCP.setMsisdnStrategyIncrement( 3399900201L, 1 );
		cdrLCP.setDateStrategyFixed( date );
		cdrLCP.setDeleteStrategyFixed( DELETE.YES );
				
		cdrLCP.addLines( 100 );
				
		cdrLCP.print();
		
		cdrLCP.save();
		
		final String REMOTE_FOLDER = "/nfsdata/files/cdr/deposit/LIFECYCLE_DELETE_CDR/";
		
		cdrLCP.send( sshl.get( "nfsdata", "root" ).getService(), REMOTE_FOLDER, sshl.get( "nfsdata", "root" ).getUser() );
		
		System.out.println( "File name: " + cdrLCP.getFileName() );
		
	}

}
