package com.lumata.e4o.regressions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.Period;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;

//import com.lumata.e4o.regressions.schema.ConfigureEnvironment;
import com.lumata.e4o.regressions.xmlrpc.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class RegressionSuite {

	private enum TestStatus {
		SUCCESS, FAILURE, SKIP, STARTED		
	}

	private String testSuiteStartDate;
	private String testSuiteEndDate;
	private String testSuiteExecutionTime;
	
	/* 	Exec Regression Suite */
	@Test
	public void regressionTests() throws ParseException {		
		
		final String PROJECT = "E4O";
		final String RELEASE = "2.4.1.14.4";
		final String CUSTOMER = "QA";		
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setPreserveOrder( true );
		testng.setGroupByInstances( true );
		testng.setRandomizeSuites( false );
		testng.setTestClasses( suite() );
		testng.addListener(tla);		
		testng.run();
		
		List<TestCase> testSuite = new ArrayList<TestCase>();
		
		addTestsResult( tla, testSuite, tla.getFailedTests() );
		addTestsResult( tla, testSuite, tla.getSkippedTests() );
		addTestsResult( tla, testSuite, tla.getPassedTests() );
		
		createReport( PROJECT, RELEASE, CUSTOMER, testSuite, tla );
		
	}
	
	private Class<?>[] suite() {
		
		return new Class[] { 
			//ConfigureEnvironment.class,
			XMLRPCRequest_CatalogManager_Purchase.class,		
			XMLRPCRequest_Subscribermanager_CreateSubscriber.class,
			XMLRPCRequest_Subscribermanager_UpdateSubscriber.class,
			XMLRPCRequest_Subscribermanager_DeleteSubscriber.class			
		};		
		
	}
	
	private void addTestsResult( TestListenerAdapter tla, List<TestCase> testSuite, List<ITestResult> testResultSuite ) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat sdfms = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
		
		for( ITestResult test : testResultSuite ) {
			
			if( 
				null == testSuiteStartDate || 
				sdf.parse( testSuiteStartDate ).before( test.getTestContext().getStartDate() ) 
			) { testSuiteStartDate = sdf.format( test.getTestContext().getStartDate() ); }
			if( 
				null == testSuiteEndDate || 
				sdf.parse( testSuiteEndDate ).before( test.getTestContext().getEndDate() )
			) { testSuiteEndDate = sdf.format( test.getTestContext().getEndDate() ); }
			
			Period executionTime = new Period( sdf.parse( testSuiteStartDate ).getTime(), sdf.parse( testSuiteEndDate ).getTime() );
			
			testSuiteExecutionTime = 	String.format( "%02d", executionTime.getHours() ) + ":" + 
										String.format( "%02d", executionTime.getMinutes() ) + ":" + 
										String.format( "%02d", executionTime.getSeconds() );
			
			Calendar startDate = Calendar.getInstance();
		    startDate.setTimeInMillis( test.getStartMillis() );

		    StringBuilder stacktrace = new StringBuilder();
		    
			if( null != test.getThrowable() ) {
								
				StackTraceElement[] stel = test.getThrowable().getStackTrace();
			    for (int i=0; i < stel.length; i++) { stacktrace.append( stel[i].toString() + "</br>" ); }
			    			    
			}
			
			TestCase testCase = new TestCase(
				test.getTestClass().getName().replace( "com.lumata.e4o.regressions.", "" ),
				test.getName(),
				TestStatus.values()[ ( test.getStatus() - 1 ) ].name(),
				sdfms.format( startDate.getTime() ),
				String.valueOf( ( test.getEndMillis() - test.getStartMillis() ) ),
				stacktrace.toString()
			);
			

			
			testSuite.add( testCase );			
			
		}
		
	}
	
	private void createReport( String project, String release, String customer, List<TestCase> testSuite, TestListenerAdapter tla ) {
			
		Configuration cfg = new Configuration();
		try {
		
			Template template = cfg.getTemplate("src/main/resources/templates/testing/testReportTpl.ftl");
		   
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("project", project);
			data.put("release", release);
			data.put("testSuite", testSuite);
			data.put("testSuiteStartDate", testSuiteStartDate);
			data.put("testSuiteEndDate", testSuiteEndDate);
			data.put( "testSuiteExecutionTime", testSuiteExecutionTime );
			data.put("total", ( tla.getPassedTests().size() + tla.getFailedTests().size() + tla.getSkippedTests().size() ) );
			data.put("success", tla.getPassedTests().size());
			data.put("failure", tla.getFailedTests().size());
			data.put("skip", tla.getSkippedTests().size());
						
			/*
			Writer out = new OutputStreamWriter( System.out );
			template.process( data, out );
			out.flush();
		   	*/
	        Writer file = new FileWriter( 
	        					new File( 	System.getProperty( "user.dir" ) + 
	        								"/output/testing/" + 
	        								project.toLowerCase() + 
	        								"_" +
	        								customer.toLowerCase() + 
	        								"_" +
	        								release.toLowerCase() + 
	        								"_" +        							
	        								"regression_report.html" ) 
	        );
	        
	        template.process(data, file);
	        file.flush();
	        file.close();
		   	       
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}
	
}
