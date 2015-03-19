package com.lumata.e4o.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.lumata.common.testing.log.Log;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class TestNGReportListener implements IReporter  {

	private static final Logger logger = LoggerFactory.getLogger( TestNGReportListener.class );
	
	private final String OUTPUT_REPORT_DIR = System.getProperty( "user.dir" ) + "/output/reports/testsuite/";
	
	private String testSuiteStartDate;
	private String testSuiteEndDate;
	private String testSuiteExecutionTime;
	
	final String PROJECT = "E4O";
	final String RELEASE = "2.4.1.14.4";
	final String CUSTOMER = "QA";	
	
	private enum TestStatus {
		SUCCESS, FAILURE, SKIP, STARTED		
	}
	
	@Override
	public void generateReport( List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory ) {
       
		logger.info( Log.CREATING.createMessage( "TestNG report" ) );
		
		for( ISuite suite : suites ) {
			
			Integer passed = 0;
			Integer failed = 0;
			Integer skipped = 0;
			
			String suiteName = suite.getName();
			
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			
			List<TestCase> testSuite = new ArrayList<TestCase>();
						
			for ( ISuiteResult sr : suiteResults.values() ) {
				
				String reportFileName = getReportFileName( PROJECT, RELEASE, CUSTOMER, suiteName );
								
				ITestContext tc = sr.getTestContext();
				
//				System.out.println( "###############" );
//				System.out.println( tc.getName() );
//				System.out.println( "###############" );
								
				try {
										
					addTestsResult( testSuite, new ArrayList<ITestResult>( tc.getPassedTests().getAllResults() ) );
					addTestsResult( testSuite, new ArrayList<ITestResult>( tc.getSkippedTests().getAllResults() ) );
					addTestsResult( testSuite, new ArrayList<ITestResult>( tc.getFailedTests().getAllResults() ) );
					
					passed += + tc.getPassedTests().getAllResults().size();
					failed += tc.getFailedTests().getAllResults().size();
					skipped += tc.getSkippedTests().getAllResults().size();
										
				} catch( ParseException e ) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
					
				}
				
				logger.info( Log.SAVED.createMessage( "TestNG suite report ( " + reportFileName + " )" ) );
								
			}
	        		
			createReport( PROJECT, RELEASE, CUSTOMER, suiteName, testSuite, passed, failed, skipped );
			
	       	//CustomReport cr = new CustomReport();
			//cr.generateReport( xmlSuites, suites, outputDirectory );
	      
	    }
		      
	}
	
	private void addTestsResult( List<TestCase> testSuite, List<ITestResult> testResultSuite ) throws ParseException {
		
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
	
	private void createReport( String project, String release, String customer, String testSuiteName, List<TestCase> testSuite, Integer passed, Integer failed, Integer skipped  ) {
			
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
			data.put("total", ( passed + failed + skipped ) );
			data.put("success", passed );
			data.put("failure", failed );
			data.put("skip", skipped );
						
			Writer file = new FileWriter( 
	        					new File( getReportFileName( PROJECT, RELEASE, CUSTOMER, testSuiteName ) ) 
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
	
	private String getReportFileName( String project, String release, String customer, String testSuiteName ) {
		
		File outputReportDir = new File( OUTPUT_REPORT_DIR );
		
		outputReportDir.mkdirs();
		
		File outputReportFile = new File( outputReportDir, project.toLowerCase() + 
				"_" +
				customer.toLowerCase() + 
				"_" +
				release.toLowerCase() + 
				"_" +        							
				"regression_report_" + testSuiteName + ".html"				
		);
		
		return outputReportFile.getAbsolutePath();
		
	}
	
}
