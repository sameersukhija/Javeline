package com.lumata.e4o.testing.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.Period;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.Mail;
import com.lumata.common.testing.network.MailClient;
import com.lumata.common.testing.network.RestClient;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

//configuration in case to use pod51015.outlook.com mail server
//@Mail(
//	protocol = "smtp",
//	fromRecipient = "arcangelo.dipasquale@lumatagroup.com",
//	toRecipients = { "arcangelo.dipasquale@lumatagroup.com" },
//	host = "pod51015.outlook.com",
//	port = 587,
//	starttlsEnabled = true,
//	authorizationRequired = true,
//	user = "arcangelo.dipasquale@lumatagroup.com",
//	password = ""
//)
@Mail(
	protocol = "smtp",
	fromRecipient = "qa.e4o.all@lumatagroup.com",
//	toRecipients = { "qa.e4o.all@lumatagroup.com" },
	toRecipients = { "arcangelo.dipasquale@lumatagroup.com" },
	host = "internal.mailservices.lumata.int",
	port = 25,
	starttlsEnabled = false,
	authorizationRequired = false,
	user = "",
	password = ""
)
public class TestNGReportListener implements IReporter  {

	private static final Logger logger = LoggerFactory.getLogger( TestNGReportListener.class );
	
	private final String TEMPLATE_REPORT_FILE = "src/test/resources/templates/testing/testReportTpl.ftl";	
	private final String OUTPUT_REPORT_DIR = System.getProperty( "user.dir" ) + "/output/reports/testsuite/";
	private final String FIXED_REPORT_FILE_NAME = "E4O_Regression_Suite_Report.html";
	
	private final String PROJECT = "E4O";
	private final String CUSTOMER = "QA";
	
	private final String JENKINS_JOB_URL = "http://ci.lumata.int/job/{jenkinsJobName}/{jenkinsBuildNumber}/api/json";
	
	private String release = "";
	private String testEnvironment = "";
	private String testPlatform = "Linux";
	private String testBrowser = "Firefox";
	private String testSuiteStartDate;
	private String testSuiteEndDate;
	private String testSuiteExecutionTime;
	private Boolean testSuiteNotification = false;
	private String testSuiteStatus = "";
	
	private String testJenkinsJobName = "";
	private String testJenkinsBuildNumber = "";
	private String testJenkinsBuildLink = "";
	private String testJenkinsExecutionTime = "";
	private Boolean testJenkinsExecution = false;
	
	private SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	private SimpleDateFormat sdfms = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );

	private Writer resultReport;
	
	private enum TestStatus {
		SUCCESS, FAILURE, SKIP, STARTED		
	}
	
	@Override
	public void generateReport( List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory ) {
       
		logger.info( Log.CREATING.createMessage( "TestNG report" ) );
		
//		XmlSuite xmlSuite = xmlSuites.get( 0 );
//		
//		Map<String,String> xmlSuiteParameters = xmlSuite.getAllParameters();
		
		for( ISuite suite : suites ) {
			
			Integer passed = 0;
			Integer failed = 0;
			Integer skipped = 0;
			
			release = suite.getParameter( "e4oReleaseParam" );
			
			JSONObject networkEnvironmentParams = new JSONObject( suite.getParameter( "networkEnvironmentParams" ) );
			
			if( null != networkEnvironmentParams && networkEnvironmentParams.has( "envFile" ) ) {
			
				testEnvironment = networkEnvironmentParams.getString( "envFile" );
			
			}
			
			JSONObject seleniumWebDriverParams = new JSONObject( suite.getParameter( "seleniumWebDriverParams" ) );
			
			if( null != seleniumWebDriverParams && seleniumWebDriverParams.has( "type" ) ) {
			
				if( seleniumWebDriverParams.getString( "type" ).equals( "local" ) ) {
					
					testPlatform = "LOCAL";
					
					testBrowser = seleniumWebDriverParams.getJSONObject( "local" ).getString( "browserName" );
				
				} else {
					
					testPlatform = seleniumWebDriverParams.getJSONObject( "remote" ).getJSONObject( "capability" ).getString( "platform" );;
					
					testBrowser = seleniumWebDriverParams.getJSONObject( "remote" ).getJSONObject( "capability" ).getString( "browserName" );				
					
				}
			
			}
			
			String suiteName = suite.getName();
			
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			
			List<TestCase> testSuite = new ArrayList<TestCase>();
			
			Date suiteStartDate = null;
			
			Date suiteEndDate = null;
			
			for ( ISuiteResult sr : suiteResults.values() ) {
				
				Date suiteCurrStartDate = sr.getTestContext().getStartDate();
				
				Date suiteCurrEndDate = sr.getTestContext().getEndDate();
								
				if( null == suiteStartDate ) { 
					
					suiteStartDate = suiteCurrStartDate;
					
				} else {
					
					if( suiteCurrStartDate.before( suiteStartDate ) ) {
						
						suiteStartDate = suiteCurrStartDate;
						
					}
					
				}
				
				if( null == suiteEndDate ) { 
					
					suiteEndDate = suiteCurrEndDate;
					
				} else {
					
					if( suiteCurrEndDate.after( suiteEndDate ) ) {
						
						suiteEndDate = suiteCurrEndDate;
						
					}
					
				}
								
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
											
			}
	        			
			if( null != suiteStartDate ) { 
				
				testSuiteStartDate = sdf.format( suiteStartDate );
				
			}
			
			if( null != suiteEndDate ) { 
				
				testSuiteEndDate = sdf.format( suiteEndDate );
				
			}
			
			if( null != testSuiteStartDate &&
				!testSuiteStartDate.isEmpty() &&
				null != testSuiteEndDate &&
				!testSuiteEndDate.isEmpty()	
			) {
				
				Period executionTime;
				try {
					
					executionTime = new Period( sdf.parse( testSuiteStartDate ).getTime(), sdf.parse( testSuiteEndDate ).getTime() );

					testSuiteExecutionTime = 	String.format( "%02d", executionTime.getHours() ) + ":" + 
							String.format( "%02d", executionTime.getMinutes() ) + ":" + 
							String.format( "%02d", executionTime.getSeconds() );

				} catch (ParseException e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				}				
				
			}
			
			try {
				
				testJenkinsJobName = System.getProperty( "jenkinsJobName" );
				
				testJenkinsBuildNumber = System.getProperty( "jenkinsBuildNumber" );
				
				testJenkinsExecution = ( 
					null != testJenkinsJobName && 
					!testJenkinsJobName.isEmpty() && 
					null != testJenkinsBuildNumber && 
					!testJenkinsBuildNumber.isEmpty() ?											
					true :
					false 
				);
				

				if( testJenkinsExecution ) {
					
					String jenkinsUrl = JENKINS_JOB_URL.replace( "{jenkinsJobName}" , testJenkinsJobName ).replace( "{jenkinsBuildNumber}" , testJenkinsBuildNumber ); 
					
					RestClient rc = new RestClient();
					
					try {
						
						JSONObject jenkinsInfo = new JSONObject( rc.get( jenkinsUrl ).getEntity() );
						
						Period jenkinsExecutionTime = new Period( jenkinsInfo.getLong( "estimatedDuration" ) );

						testJenkinsExecutionTime = String.format( "%02d", jenkinsExecutionTime.getHours() ) + ":" + 
								String.format( "%02d", jenkinsExecutionTime.getMinutes() ) + ":" + 
								String.format( "%02d", jenkinsExecutionTime.getSeconds() );

						testJenkinsBuildLink = jenkinsInfo.getString( "url" );
						
						System.out.println( jenkinsInfo );
						
					} catch (Exception e) {
					
						logger.error( Log.FAILED.createMessage( e.getMessage() ) );
					
					}
					
				}
							
			} catch ( Exception e ) {
			
				logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
			}
			
			if( failed > 0 ) {
				
				testSuiteStatus = "UNSTABLE";
				
			} else {
				
				testSuiteStatus = "STABLE";
				
			}
			
			String reportFileName = getReportFileName( PROJECT, release, CUSTOMER, suiteName );
			
			createReport( suiteName, testSuite, passed, failed, skipped );
			
			logger.info( Log.SAVED.createMessage( "TestNG suite report ( " + reportFileName + " )" ) );
			
			sendReport( PROJECT, release, CUSTOMER, "Regression Suite" );
				
			//CustomReport cr = new CustomReport();
			//cr.generateReport( xmlSuites, suites, outputDirectory );
	      
	    }
		      
	}
	
	private void addTestsResult( List<TestCase> testSuite, List<ITestResult> testResultSuite ) throws ParseException {
				
		for( ITestResult test : testResultSuite ) {
		
			Calendar startDate = Calendar.getInstance();
		    startDate.setTimeInMillis( test.getStartMillis() );

		    StringBuilder stacktrace = new StringBuilder();
		    
			if( null != test.getThrowable() ) {
								
				StackTraceElement[] stel = test.getThrowable().getStackTrace();
			    for (int i=0; i < stel.length; i++) { stacktrace.append( stel[i].toString() + "</br>" ); }
			    			    
			}
			System.out.println( TestStatus.values()[ ( test.getStatus() - 1 ) ].name() );
			TestCase testCase = new TestCase(
				test.getTestClass().getName().replace( "com.lumata.e4o.", "" ),
				test.getName(),
				TestStatus.values()[ ( test.getStatus() - 1 ) ].name(),
				sdfms.format( startDate.getTime() ),
				String.valueOf( ( test.getEndMillis() - test.getStartMillis() ) ),
				stacktrace.toString()
			);
						
			testSuite.add( testCase );			
			
		}
		
	}
	
	private void createReport( String testSuiteName, List<TestCase> testSuite, Integer passed, Integer failed, Integer skipped  ) {
			
		Configuration cfg = new Configuration();
		try {
		
			Template template = cfg.getTemplate( TEMPLATE_REPORT_FILE );
		   
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("project", PROJECT);
			data.put("release", release);
			data.put("testEnvironment", testEnvironment);
			data.put("testPlatform", WordUtils.capitalize( testPlatform.toLowerCase() ) );
			data.put("testBrowser", WordUtils.capitalize( testBrowser.toLowerCase() ));
			data.put("testSuite", testSuite);
			data.put("testSuiteStartDate", testSuiteStartDate);
			data.put("testSuiteEndDate", testSuiteEndDate);
			data.put( "testSuiteExecutionTime", testSuiteExecutionTime );
			data.put( "testSuiteStatus", testSuiteStatus );
			data.put("total", ( passed + failed + skipped ) );
			data.put("success", passed );
			data.put("failure", failed );
			data.put("skip", skipped );
			data.put("testJenkinsExecution", testJenkinsExecution);
			data.put("testJenkinsJobName", testJenkinsJobName);
			data.put("testJenkinsBuildNumber", testJenkinsBuildNumber);
			data.put("testJenkinsBuildLink", testJenkinsBuildLink);
			data.put( "testJenkinsExecutionTime", testJenkinsExecutionTime );
						
			/**
			 * generate report document
			 */
			testSuiteNotification = true;
			data.put("testSuiteNotification", testSuiteNotification);
			resultReport = new StringWriter();
			template.process(data, resultReport);
						
			/**
			 * store email report document - with dynamic file name
			 */
			testSuiteNotification = false;
	        data.put("testSuiteNotification", testSuiteNotification);
	        Writer file = new FileWriter( 
	        	new File( getReportFileName( PROJECT, release, CUSTOMER, testSuiteName ) ) 
	        );
	        
	        template.process(data, file);
	        file.flush();
	        file.close();
	        
	        /**
			 * store jenkins report document - with static file name to show in jenkins
			 */
	        file = new FileWriter( 
	        	new File( getFixedReportFileName() ) 
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
		
		String ts = new SimpleDateFormat("yyyyMMddHHmmss").format( new Timestamp( System.currentTimeMillis() ) );
		
		File outputReportFile = new File( outputReportDir, 
				project + 
				"_" +
				customer + 
				"_" +
				release + 
				"_" +        							
				"Report_" + 
				testSuiteName.replace( " ", "" ) + 
				"_" + 
				ts +
				".html"				
		);
		
		return outputReportFile.getAbsolutePath();
		
	}
	
	private String getFixedReportFileName() {
		
		File outputReportDir = new File( OUTPUT_REPORT_DIR );
		
		outputReportDir.mkdirs();
		
		File outputReportFile = new File( outputReportDir, FIXED_REPORT_FILE_NAME );
		
		return outputReportFile.getAbsolutePath();
		
	}
	
	public void sendReport( String project, String release, String customer, String testSuiteName ) {
		
		try {
			
			String subject = 	testSuiteName + 
								" " + project + 
								" - " + 
								customer + 
								" ( " + 
								release +								 
								" )" +								
								( 	null != testJenkinsBuildNumber && 
									null != testSuiteStatus && 
									!testSuiteStatus.isEmpty() ? 
									" [ " +
									( null != testJenkinsBuildNumber ? "build " + testJenkinsBuildNumber : "" ) +
									( null != testSuiteStatus && !testSuiteStatus.isEmpty() ? ( null != testJenkinsBuildNumber ? " - " : "" ) + testSuiteStatus.toLowerCase() : "" ) +
									" ] " : 
									""
								);
			
			Mail mail = this.getClass().getAnnotation( Mail.class );
			
			if( null != mail ) { 
				
				MailClient.getInstance( mail ).send( subject , resultReport.toString() ); 
				
			}
			
		} catch( MessagingException e ) {}

	}
	
}
