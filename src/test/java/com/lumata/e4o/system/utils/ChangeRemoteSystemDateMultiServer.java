package com.lumata.e4o.system.utils;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCEKC;
import com.lumata.e4o.testing.common.TCEKCL;

/** POC architecture **/
@TCEKCL({
	@TCEKC( ssh_server="actrule1", ssh_user="root"),
	@TCEKC( ssh_server="actrule2", ssh_user="root"),
	@TCEKC( ssh_server="collector1", ssh_user="root"),
	//@TCEKC( ssh_server="collector2", ssh_user="root"),
	@TCEKC( ssh_server="nfsdata1", ssh_user="root"),
	@TCEKC( ssh_server="nfsdata2", ssh_user="root"),
	@TCEKC( ssh_server="nfsdata3", ssh_user="root"),
	@TCEKC( ssh_server="db1", ssh_user="root"),
	@TCEKC( ssh_server="db2", ssh_user="root"),
	@TCEKC( ssh_server="db3", ssh_user="root")
})
public class ChangeRemoteSystemDateMultiServer extends ParentTestCase {
	
	@Test(enabled=true, priority = 1 )
	public void ChangeRemoteSystemDateMultiServerViaSSH() throws Exception {
		
		Boolean remoteDate = false;
		
		Calendar serverDate = null;
		
		if( remoteDate ) {
		
			serverDate = ekcl.get("actrule1","root").getServerDateTime();
		
		} else {
		
			serverDate = Calendar.getInstance();
			
		}
		
		System.out.println( serverDate.getTime() );
		
		int YEAR;
		int MONTH;
		int DAY;
		int HOUR;
		int MINUTE;
		int SECOND;		
		
		int YEAR_TO_ADD = 0;
		int MONTH_TO_ADD = 0;
		int DAY_TO_ADD = 0;
		int HOUR_TO_ADD = 0;
		int MINUTE_TO_ADD = 0;
		int SECOND_TO_ADD = 0;
		 
		YEAR = serverDate.get( Calendar.YEAR );
		MONTH = serverDate.get( Calendar.MONTH );
		DAY = serverDate.get( Calendar.DAY_OF_MONTH );
		HOUR = serverDate.get( Calendar.HOUR_OF_DAY );
		MINUTE = serverDate.get( Calendar.MINUTE );
		SECOND = serverDate.get( Calendar.SECOND );								
				
		serverDate.set( 
			YEAR + YEAR_TO_ADD, 
			MONTH + MONTH_TO_ADD, 
			DAY + DAY_TO_ADD, 
			HOUR + HOUR_TO_ADD, 
			MINUTE + MINUTE_TO_ADD, 
			SECOND + SECOND_TO_ADD
		);
						
		ekcl.get("actrule1","root").setServerDatetime( serverDate, false );
		ekcl.get("actrule2","root").setServerDatetime( serverDate, false );
		ekcl.get("collector1","root").setServerDatetime( serverDate, false );
		//ekcl.get("collector2","root").setServerDatetime( serverDate, false );
		ekcl.get("nfsdata1","root").setServerDatetime( serverDate, false );
		ekcl.get("nfsdata2","root").setServerDatetime( serverDate, false );
		ekcl.get("nfsdata3","root").setServerDatetime( serverDate, false );
		ekcl.get("db1","root").setServerDatetime( serverDate, false );
		ekcl.get("db2","root").setServerDatetime( serverDate, false );
		ekcl.get("db3","root").setServerDatetime( serverDate, false );
				
	}
	
	@Test(enabled=true, priority = 2 )
	public void restartRemoteServerAfterDateChanged() throws Exception {
								
		ekcl.get("actrule1","root").expressionRestart();
		ekcl.get("actrule2","root").expressionRestart();
		
		/** ha will start the collector **/
		ekcl.get("collector1","root").collectorStop();
				
	}
	
}
