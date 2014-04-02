package com.lumata.e4o.tasks;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.expression.operators.exceptions.CDRException;

public class TestTasksMigration {
	
	//private final boolean generate_cdr_classes = true;
	//private final boolean generate_cdr = false;
	
	@Test( enabled = true )
	public void create_cdr() throws IOFileException, ClassNotFoundException, JSONSException, EnvironmentException, CDRException {

		Environment env = new Environment( "input/environments", "E4O_VM", IOFileUtils.IOLoadingType.RESOURCE );
		String tenant = "tenant";
		
		Calendar minDate = Calendar.getInstance();
		Calendar maxDate = Calendar.getInstance();
		maxDate.set( Calendar.DATE , ( maxDate.get( Calendar.DATE ) + 2 ) );
				
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put( CDR.Parameters.Env.name(), env );
		parameters.put( CDR.Parameters.Tenant.name(), tenant );
		parameters.put( CDR.Parameters.CDRCfgDir.name(), "input/cdr" );
		parameters.put( CDR.Parameters.CDRCfgFile.name(), "cdr_tasks.json" );
		parameters.put( CDR.Parameters.CDROutputDir.name(), "output/cdr" );
		parameters.put( "###date###", "@current" );
		parameters.put( "###validity_date###", "@current+1YEAR;" );
		parameters.put( "###deactivation_date###", "@current+1YEAR;+6MONTH;-7DAY;" );
						
		CDR cdr = new CDR();
				
		cdr.feeder( minDate, maxDate, parameters );		
		
	}
	
}
