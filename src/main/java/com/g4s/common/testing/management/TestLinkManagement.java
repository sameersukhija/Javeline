package com.g4s.common.testing.management;
//package com.lumata.common.testing.management;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
//import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
//import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
//import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
//
//public class TestLinkManagement {
//
//	private TestLinkAPI tla;
//	
//	public TestLinkManagement( String url, String key ) throws TestLinkAPIException, MalformedURLException {
//		
//		tla = new TestLinkAPI( new URL( url ), key );		
//		
//	}
//	
//	public Integer[] getTestCaseCountByExecutionType( Integer testSuiteId ) {
//		
//		Integer[] testCasesCount = new Integer[]{ 0 , 0 };
//		
//		TestCase[] testCases = tla.getTestCasesForTestSuite( testSuiteId, true, TestCaseDetails.FULL );
//		
//		for( int tc = 0; tc < testCases.length; tc++ ) {
//			
//			testCasesCount[ testCases[ tc ].getExecutionType().ordinal() ]++;
//			
//		}
//		
//		return testCasesCount;
//		
//	}
//	
//	
//	
//}
