package com.lumata.e4o.scheduling.scheduledtasks;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.system.cdr.CDR;

public class TestTasksMigration {
	
	//private final boolean generate_cdr_classes = true;
	//private final boolean generate_cdr = false;
	
	@Parameters({"environment", "tenant"})
	@Test( enabled = true )
	public void create_cdr( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws IOFileException, ClassNotFoundException, JSONSException, NetworkEnvironmentException, CDRException {

		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		Calendar minDate = Calendar.getInstance();
		Calendar maxDate = Calendar.getInstance();
		maxDate.set( Calendar.DATE , ( maxDate.get( Calendar.DATE ) + 0 ) );
				
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put( CDR.Parameters.env.name(), env );
		parameters.put( CDR.Parameters.tenant.name(), tenant );
		parameters.put( CDR.Parameters.cfgDir.name(), "input/cdr" );
		parameters.put( CDR.Parameters.cfgFile.name(), "cdr_tasks.json" );
		parameters.put( "###date###", "@current" );
		parameters.put( "###validity_date###", "@current+1YEAR;" );
		parameters.put( "###deactivation_date###", "@current+1YEAR;+6MONTH;-7DAY;" );
						
		CDR cdr = new CDR();
				
		cdr.feeder( minDate, maxDate, parameters );		
		
	}
	
}
