package com.lumata.e4o.notification.dialogmanager;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.log.Log;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCActiveMQ;
import com.lumata.e4o.testing.common.TCMysqlJMailerMaster;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;

@TCOwners(@TCOwner(name = "Arcangelo Di Pasquale", email = "arcangelo.dipasquale@lumatagroup.com"))
@TCMysqlJMailerMaster
@TCActiveMQ( servers={"dm1"} )
public class JMailer extends ParentTestCase {

	@Test(enabled=true)
	public void createDailyMTTable() {
				
		Reporter.log( Log.LOADING.createMessage( this.getClass().getSimpleName(), "creating mt table" ), LOG_TO_STD_OUT );
		
		String mtTable = activemq.get( "dm1" ).getMtTableName();
		
		activemq.get( "dm1" ).createMtTable( mysqlJMailerMaster, mtTable );
		
		Reporter.log( Log.LOADING.createMessage( this.getClass().getSimpleName(), "created mt table ( " + mtTable + " )" ), LOG_TO_STD_OUT );
		
	}
	
}
