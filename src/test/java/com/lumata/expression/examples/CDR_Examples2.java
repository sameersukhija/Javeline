package com.lumata.expression.examples;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.CDRClassGenerator;
import com.lumata.e4o.system.cdr.types.CDRBundle;
import com.lumata.e4o.system.cdr.types.CDRCall;
import com.lumata.e4o.system.cdr.types.CDRHistory;
import com.lumata.e4o.system.cdr.types.CDRRevenue;
import com.lumata.e4o.system.cdr.types.CDRVoucher;
import com.lumata.e4o.system.csv.types.CSVBoolean;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import com.lumata.e4o.system.csv.types.CSVSchemaTable;
import com.lumata.e4o.system.csv.types.CSVString;
import com.lumata.e4o.system.csv.types.CSVDate.CDRDateFormat;

public class CDR_Examples2 {
	
	private final boolean generate_cdr_classes = true;
	private final boolean generate_cdr = false;
	
	// CDR Types generation
	@Test( enabled = generate_cdr_classes )
	public void create_cdr() throws IOFileException, ClassNotFoundException, EnvironmentException, CDRException, JSONSException {

		Environment env = new Environment( "input/environments", "E4O_VM", IOFileUtils.IOLoadingType.RESOURCE );
		String tenant = "tenant";
		
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
