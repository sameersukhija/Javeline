package com.lumata.common.testing.management;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestNGUtils {

	public static boolean run( Class[] classes ) {
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses( classes );
		testng.addListener(tla);
		testng.run();
		
		return ( testng.getStatus() == 0 ? true : false );
				
	}
	
}
