package com.lumata.expression.operators.generators;

import org.testng.annotations.Test;

public class CDRGenerator {

	@Test
	public void test() {
		
		CDR cdr = new CDR();
		
		cdr.setMsisdn( "312345901" );
		//cdr.setDate( "2014-01-30" );
		
		cdr.add( CDR.Types.CALL );
		
		cdr.print();
		
	}
	
}
