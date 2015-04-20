package com.lumata.e4o.generators.subscribers;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
public class GenerateSubscribersOptions extends ParentTestCase {

	final boolean GENERATE_OPTIONS = true;
	
	@Test( enabled = GENERATE_OPTIONS )
	public void generateOptions() throws GeneratorException {
		
		final Long SUBSCRIBERS_OPTIONS_TO_GENERATE = 2000L;		
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.insertOptions( "option_", SUBSCRIBERS_OPTIONS_TO_GENERATE );
		
	}
	
}
