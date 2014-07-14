package com.lumata.e4o.utils.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.e4o.system.cdr.CDRClassGenerator;


public class GenerateCDRTypes {

	private static final Logger logger = LoggerFactory.getLogger( GenerateCDRTypes.class );
	
	@Test( priority = 1, enabled = true )
	public void generateCDRTypes() throws ClassNotFoundException, IOFileException {
		
		CDRClassGenerator cdrTypesGenerator = new CDRClassGenerator();
		
		cdrTypesGenerator.generate();
		
	}
			
}
