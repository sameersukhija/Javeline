package com.lumata.e4o.generators.subscribers;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
public class GenerateSubscribersHobbies extends ParentTestCase {

	final boolean GENERATE_DEFAULT_HOBBIES = true;
	final boolean GENERATE_CUSTOM_HOBBIES = false;
	
	@Test( enabled = GENERATE_DEFAULT_HOBBIES )
	public void generateDefaultHobbies() throws GeneratorException {
				
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.insertDefaultHobbies();
		
	}
	
	@Test( enabled = GENERATE_CUSTOM_HOBBIES )
	public void generateCustomHobbies() throws GeneratorException {
				
		String[] hobbies = new String[] { "Football", "Dance" };
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.insertHobbies( hobbies );
		
	}
	
}
