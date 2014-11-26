package com.lumata.e4o.generators.cdr;

import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.types.CDRDialogSMS;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class GenerateCDRDialogSms {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
	Mysql mysql;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "nfsdataServer", "user", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String nfsdataServer, @Optional("superman") String user, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		sshService = env.getService( Service.Type.ssh, nfsdataServer );
		
		sshUser = "root";
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Test( enabled = true )
	public void cdr_dialog_sms() throws IOFileException, FieldException {
		
		System.out.println( "-----------------------------" );
		System.out.println( "cdr_lifecycle" );

		CDRDialogSMS cdrDialogSms = new CDRDialogSMS();
		
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_dialogsms_" + currentTimestamp + ".csv";
				
		cdrDialogSms.setOutputPath( "/cdr/", fileName );
		
		Calendar date = Calendar.getInstance();
		
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );

		cdrDialogSms.setMsisdnStrategyFixed( 3399900100L );
		cdrDialogSms.setAddressStrategyFixed( 3399900100L );
		cdrDialogSms.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrDialogSms.setDateStrategyFixed( date );
		cdrDialogSms.setShortCodeStrategyFixed( "5555" );
		cdrDialogSms.setChannelNameStrategyFixed( "SMS" );
		//cdrDialogSms.setTextStrategyIncrement( "ACCEPT ", 0, 1 );
		cdrDialogSms.setTextStrategyFixed( "ACCEPT 3" );
				
		cdrDialogSms.addLines( 1 );
		
		cdrDialogSms.setMsisdnStrategyFixed( 3399900200L );
		cdrDialogSms.setAddressStrategyFixed( 3399900200L );
		cdrDialogSms.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		cdrDialogSms.setDateStrategyFixed( date );
		cdrDialogSms.setShortCodeStrategyFixed( "5555" );
		cdrDialogSms.setChannelNameStrategyFixed( "SMS" );
		//cdrDialogSms.setTextStrategyIncrement( "ACCEPT ", 0, 1 );
		cdrDialogSms.setTextStrategyFixed( "ACCEPT 3" );
				
		cdrDialogSms.addLines( 1 );
		
		cdrDialogSms.print();
		
		cdrDialogSms.save();
		
		cdrDialogSms.send( sshService, "/usr/local/actrule/data/dialog/tenant1/", sshUser );
		
		System.out.println( "File name: " + cdrDialogSms.getFileName() );
		
	}

}
