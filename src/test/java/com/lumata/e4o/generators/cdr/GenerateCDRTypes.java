package com.lumata.e4o.generators.cdr;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.e4o.system.cdr.CDRClassGenerator;


public class GenerateCDRTypes {

	@Test( priority = 1, enabled = true )
	public void generateCDRTypes() throws ClassNotFoundException, IOFileException {
		
		CDRClassGenerator cdrTypesGenerator = new CDRClassGenerator();
		
		cdrTypesGenerator.generate();
		
	}
			
}
