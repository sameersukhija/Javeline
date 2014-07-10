package com.lumata.e4o.gui.common;

import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Filter.and;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.XMLRPCParserException;
import com.lumata.e4o.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.e4o.gui.xmlrpc.XMLRPCChannel;
import com.lumata.e4o.gui.xmlrpc.XMLRPCRelation;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultFault;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultSuccess;
import com.lumata.e4o.gui.xmlrpc.XMLRPCSubscriber;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser.ResultType;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;

public class RegressionSuiteXMLRPC {

	/**
	 * default delay is 100 msec
	 */
	private static long XMLRPC_CALL_DELAY = 100;

	/**
	 * 
	 */
	private NetworkEnvironment env;
	private Mysql mysql;

	/**
	 * Default seed for subscribers msisdn
	 */
	private final String default_msisdn_seed = "331234501";
	
	/**
	 * max lenght for msisdn
	 */
	private final Integer MaxMsisdnLength = 12;

	/**
	 * Intermediate object to handle XMLRPC init
	 */
	private Server gui = null;
	private User user = null;
	
	/**
	 * Print to standard output during execution
	 */
	private static final Boolean PRINT2STDOUT__ = Boolean.TRUE;
	
	private static String envPath = null;
	private static String envFile = null;
	
	/**
	 * XMLRPC Request / Result
	 */
	private XMLRPCResultParser responseParser = null;
	private XMLRPCResultFault resultFault = null;
	
	/**
	 * Exchange variable for tests
	 */
	private String nextTestMsisdn = null;

	/**
	 * 
	 * @param browser
	 * @param environment
	 * @param tenant
	 * @param gui_server
	 * @param user_name
	 * @throws NetworkEnvironmentException
	 */
	@Parameters({ "browser", "environment", "tenant", "gui_server", "user_name" })
	@BeforeClass
	public void init(	@Optional("FIREFOX") String browser,
						@Optional("E4O_QA") String environment,
						@Optional("qa") String tenant, 
						@Optional("actrule") String gui_server,
						@Optional("superman") String user_name	) throws NetworkEnvironmentException {
		
		envPath = "input/environments";
		envFile = environment;
		
		Reporter.log( "Init testing environment with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + envPath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + envFile, PRINT2STDOUT__);		

		env = new NetworkEnvironment( envPath, envFile, IOFileUtils.IOLoadingType.RESOURCE);

		Reporter.log( "Startup MySql driver with schema \""+tenant+"\".", PRINT2STDOUT__);
		
		mysql = new Mysql(env.getDataSource(tenant));

		gui = env.getServer(gui_server);
		user = gui.getUser(user_name);		
	}

	/**
	 * Create subscriber with wrong xmlrpc requests ( some mandatory field is missing )
	 * 
	 * @param inputSeed
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 1)
	public void createNewSubscriberWithMissingMinimalParameters( @Optional(default_msisdn_seed) String inputSeed ) throws XMLRPCParserException {

		Reporter.log( "Create New Subscriber With Missing Minimal Parameters", PRINT2STDOUT__);
		
		String msisdn = generateNewMsisdn(inputSeed);

		// Case 1 ( only msisdn sent )
		Reporter.log( "Case 1 -> Only msisdn is sent", PRINT2STDOUT__);
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "5");
		Assert.assertEquals(resultFault.getMessage(), "missing mandatory param subscription_date");

		waitState();

		// Case 2 ( only msisdn, subscription_date sent )
		Reporter.log( "Case 2 -> Only msisdn & subscription date are sent", PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "5");
		Assert.assertEquals(resultFault.getMessage(), "missing mandatory param rate_plan");

		waitState();

		// Case 3 ( only msisdn, subscription_date, rate_plan sent )
		Reporter.log( "Case 3 -> Only msisdn, subscription date & rate plan are sent", PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "5");
		Assert.assertEquals(resultFault.getMessage(), "missing mandatory param status");

		waitState();

		// Case 4 ( only msisdn, subscription_date, rate_plan, status sent )
		Reporter.log( "Case 4 -> Only msisdn, subscription date, rate plan & status are sent", PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "5");
		Assert.assertEquals(resultFault.getMessage(), "missing mandatory param in_tag");

		waitState();

		// Case 5 ( only msisdn, subscription_date, rate_plan, status, in_tag sent )
		Reporter.log( "Case 5 -> Only msisdn, subscription date, rate plan, status & IN tag are sent", PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "5");
		Assert.assertEquals(resultFault.getMessage(), "missing mandatory param network");

		waitState();

		// Case 6 ( Success - Minimal parameters sent - msisdn,
		// subscription_date, rate_plan, status, in_tag, network )
		Reporter.log( "Case 6 -> Only msisdn, subscription date, rate plan, status, IN tag & network are sent.", PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");
		
		Reporter.log( "Success!", PRINT2STDOUT__);
		
		// DB check
		// Technical debt
	}
	
	/**
	 * It returns today in compliant print standard for application
	 * 
	 * @return
	 */
	private String today() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String resp = sdf.format(new Date()); 
		
		return resp;
	}

	/**
	 * Generate a new MSISDN for current application starting from a seed
	 * 
	 * @param inputSeed
	 * 
	 * @return new msisdn for current installation
	 */
	private String generateNewMsisdn(String inputSeed) {
		
		String msisdn = inputSeed;
		String candidateMsisdn = null;
		
		Reporter.log( "Generate a new msisdn for current application starting from seed : " + inputSeed, PRINT2STDOUT__);
		
		do {
			candidateMsisdn = null;
			candidateMsisdn = completeMsisdn(inputSeed, MaxMsisdnLength);
		}
		while ( existSubscriber(candidateMsisdn) );
		
		msisdn = candidateMsisdn;
		
		Reporter.log( "New msisdn for current application will be " + msisdn, PRINT2STDOUT__);
		
		return msisdn;
	}

	@Parameters("inputSeed")
	@Test(priority = 1)
	public void createNewSubscriberWithOptionalParams( @Optional(default_msisdn_seed) String inputSeed ) throws XMLRPCParserException {

		String msisdn = generateNewMsisdn(inputSeed);

		Reporter.log("Create a subscribers with optional parameters.", PRINT2STDOUT__);

		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");
		
		String imei = "741258965412365478";
		String imsi = "740000000412365478";
		String gender = "MALE";
		String salary = "78000";
		String tongue = "ENG";
		
		Map<String, String> optionalParams = new HashMap<String, String>();
		optionalParams.put("imei", imei);
		optionalParams.put("imsi", imsi);
		optionalParams.put("gender", gender);
		optionalParams.put("salary", salary);
		optionalParams.put("tongue", tongue);

		for (Entry<String, String> entry : optionalParams.entrySet()) 
			Reporter.log( "Optional parameter -> " + entry.getKey() + "\t Value -> " + entry.getValue(), PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.params.name(), optionalParams);
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");
		
		// DB check
		// Technical debt
	}
	
	/**
	 * Complete seed to max length
	 * 
	 * @param inputSeed
	 * @param maxLength
	 * @return
	 */
	private String completeMsisdn(String inputSeed, Integer maxLength) {
		
		String resp = inputSeed;
		Random rnd = new Random(System.currentTimeMillis());
		resp += rnd.nextInt(new Double(Math.pow(10, maxLength - inputSeed.length())).intValue());
		
		return resp;
	}

	/**
	 * Create subscriber with wrong xmlrpc requests ( some parameter is wrong )
	 * 
	 * @param inputSeed
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 2)
	public void createNewSubscriberWithWrongMinimalParameters( @Optional(default_msisdn_seed) String inputSeed ) throws XMLRPCParserException {

		Reporter.log( "Create New Subscriber With Wrong Minimal Parameters", PRINT2STDOUT__);
		
		String msisdn = generateNewMsisdn(inputSeed);
		
		// Minimal parameters
		Reporter.log( "Create a set of correct minimal parameters :", PRINT2STDOUT__);
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");

		for (Entry<String, Object> entry : subscriberParams.entrySet()) 
			Reporter.log( "Parameter -> " + entry.getKey() + "\t Value -> " + entry.getValue(), PRINT2STDOUT__);
		
		waitState();

		// Case 1 ( wrong msisdn )
		Reporter.log( "Case 1 -> replace with a wrong \"msisdn\" value.", PRINT2STDOUT__);
		
		String wrong = "wrong_msisdn";
		
		Reporter.log( "Parameter -> " + XMLRPCSubscriber.Params.msisdn.name() + "\t Value -> " + wrong, PRINT2STDOUT__);
		
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), wrong);
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "2");
		Assert.assertEquals(resultFault.getMessage(), "unable to create subscriber");
		
		// replace with correct value
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);

		waitState();

		// Case 2 ( wrong subscription_date )
		Reporter.log( "Case 2 -> replace with a wrong \"subscription date\" value.", PRINT2STDOUT__);
		
		wrong = "2014-01-";
		
		Reporter.log( "Parameter -> " + XMLRPCSubscriber.Params.subscription_date.name() + "\t Value -> " + wrong, PRINT2STDOUT__);		
		
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), wrong);
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "invalid subscription_date " + wrong);
		
		// replace with correct value
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());

		waitState();

		// Case 3 ( wrong rate_plan )
		Reporter.log( "Case 3 -> replace with a wrong \"rate plan\" value.", PRINT2STDOUT__);
		
		wrong = "WRONG_RATE_PLAN";
		
		Reporter.log( "Parameter -> " + XMLRPCSubscriber.Params.rate_plan.name() + "\t Value -> " + wrong, PRINT2STDOUT__);		
		
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), wrong);
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "invalid rate_plan " + wrong);
		
		// replace with correct value
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");

		waitState();

		// Case 4 ( wrong status )
		Reporter.log( "Case 4 -> replace with a wrong \"status\" value.", PRINT2STDOUT__);
		
		wrong = "wrong status";
		
		Reporter.log( "Parameter -> " + XMLRPCSubscriber.Params.status.name() + "\t Value -> " + wrong, PRINT2STDOUT__);		
		
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), wrong);
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "invalid status " + wrong);
		
		// replace with correct value
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		
		waitState();

		// Case 5 ( wrong in_tag )
		Reporter.log( "Case 5 -> replace with a wrong \"IN tag\" value.", PRINT2STDOUT__);
		
		wrong = "WRONG IN TAG";
		
		Reporter.log( "Parameter -> " + XMLRPCSubscriber.Params.in_tag.name() + "\t Value -> " + wrong, PRINT2STDOUT__);		
		
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "WRONG IN TAG");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "invalid in_tag " + wrong);
		
		// replace with correct value
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");

		waitState();

		// Case 6 ( wrong network )
		Reporter.log( "Case 6 -> replace with a wrong \"network\" value.", PRINT2STDOUT__);
		
		wrong = "wrong network";
		
		Reporter.log( "Parameter -> " + XMLRPCSubscriber.Params.network.name() + "\t Value -> " + wrong, PRINT2STDOUT__);		
		
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "wrong network");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "invalid network " + wrong);
		
		// replace with correct value
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");

		waitState();

		// Case 7 ( Success )
		Reporter.log( "Case 7 -> Success creation without wrong parameters.", PRINT2STDOUT__);
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");
		
		// DB check
		// Technical debt
	}

	/**
	 * Create subscriber with wrong xmlrpc requests ( some no mandatory parameter is wrong )
	 * 
	 * @param inputSeed
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 3)
	public void createNewSubscriberWithWrongAllParameters( @Optional(default_msisdn_seed) String inputSeed ) throws XMLRPCParserException {

		String msisdn = generateNewMsisdn(inputSeed);

		// Minimal parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");

		waitState();

		// Case 1 ( wrong profile )
		subscriberParams.put(XMLRPCSubscriber.Params.profile.name(), "wrong profile");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "invalid profile wrong profile");

		waitState();

		// Case 2 ( wrong correspondence between profile and rate plan - correct
		// value: prepaid but used account - account has not statuses in
		// statuses table)
		subscriberParams.put(XMLRPCSubscriber.Params.profile.name(), "postpaid");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");

		Subscribers sbTable = new Subscribers();
		SupportedRatePlan srpTable = new SupportedRatePlan();

		String query = select(Subscribers.Fields.msisdn)
				.from(sbTable)
				.join(srpTable)
				.on(op(Subscribers.Fields.rate_plan_id).eq(
						SupportedRatePlan.Fields.rate_plan_id),
						/*
						 * the profile_id specified in the supported_rate_plan (
						 * prepaid ) is put in the subscribers table and not
						 * postpaid profile sent with the xmlrpc call)
						 */
						and(op(Subscribers.Fields.profile_id).eq(
								SupportedRatePlan.Fields.profile_id)))
				.where(op(Subscribers.Fields.msisdn).eq(msisdn)).build();

		ResultSet rs = mysql.execQuery(query);

		boolean found = false;

		try {
			while (rs.next()) {
				found = true;
			}
		} catch (SQLException e) {
			Assert.fail(e.getMessage());
		}

		Assert.assertTrue(found);

		subscriberParams.put(XMLRPCSubscriber.Params.profile.name(), "prepaid");
		subscriberParams.put(XMLRPCSubscriber.Params.subprofile.name(), "");

		waitState();

		if ( existSubscriber(msisdn)) {
			
			Reporter.log( "Requested MSISDN ("+msisdn+") already exist -> delete it before test.", PRINT2STDOUT__);
			
			deleteViaXmlrpc(msisdn);
		}

		waitState();

		// Case 3 ( all parameters ( channels and relations excluded )
		responseParser = this.xmlrpc(HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");
	}

	/**
	 * Create subscriber using channel parameters
	 * 
	 * @param inputSeed
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 4, enabled = false)
	public void createNewSubscriberUsingChannelsParameters( @Optional(default_msisdn_seed) String inputSeed) throws XMLRPCParserException {

		String msisdn = generateNewMsisdn(inputSeed);

		Map<String, XMLRPCChannel> channels_list = new HashMap<String, XMLRPCChannel>();
		XMLRPCChannel sms_channel = new XMLRPCChannel("SMS1", msisdn, "true");
		channels_list.put("sms_channel", sms_channel);

		// Parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");
		subscriberParams.put(XMLRPCSubscriber.Params.profile.name(), "prepaid");
		subscriberParams.put(XMLRPCSubscriber.Params.subprofile.name(), "");
		subscriberParams.put(XMLRPCSubscriber.Params.channels.name(), channels_list);

		waitState();

		// Case 1 ( wrong channel name )
		sms_channel.setName("SMS1");
		responseParser = this.xmlrpc(HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "Invalid channel SMS1");
		sms_channel.setName("SMS");

		waitState();

		// Case 2 ( wrong address )
		sms_channel.setAddress("");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "5");
		Assert.assertEquals(resultFault.getMessage(), "Missing address for channel SMS");
		sms_channel.setAddress(msisdn);

		waitState();

		// Case 3 ( wrong active )
		sms_channel.setActive("wrong active value");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "Invalid active for channel SMS");
		sms_channel.setActive("true");

		waitState();

		// Case 4 ( Success )
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");
	}

	@AfterMethod
	public void tearDown() {
		
		waitState();
	}
	
	/**
	 * Create subscriber using relation parameters
	 * 
	 * @param inputSeed
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 5, enabled = false)
	public void createNewSubscriberUsingRealtionsParameters( @Optional(default_msisdn_seed) String inputSeed) throws XMLRPCParserException {

		String msisdn = generateNewMsisdn(inputSeed);

		Map<String, XMLRPCChannel> channels_list = new HashMap<String, XMLRPCChannel>();
		XMLRPCChannel sms_channel = new XMLRPCChannel("SMS", msisdn, "true");
		channels_list.put("sms_channel", sms_channel);

		Map<String, XMLRPCRelation> relations_list = new HashMap<String, XMLRPCRelation>();
		XMLRPCRelation relation = new XMLRPCRelation("account", msisdn);
		relations_list.put("relation", relation);

		// Parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");
		subscriberParams.put(XMLRPCSubscriber.Params.profile.name(), "prepaid");
		subscriberParams.put(XMLRPCSubscriber.Params.subprofile.name(), "");
		subscriberParams.put(XMLRPCSubscriber.Params.channels.name(), channels_list);
		subscriberParams.put(XMLRPCSubscriber.Params.relations.name(), relations_list);

		waitState();

		// Case 1 ( wrong relation type )
		relation.setType("wrong account");
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "6");
		Assert.assertEquals(resultFault.getMessage(), "Invalid relation wrong account");
		relation.setType("account");

		waitState();

		// Case 2 ( wrong related msisdn )
		String related_msisdn = msisdn.substring(0, msisdn.length() - 1);

		while (related_msisdn.equals(msisdn.substring(0, msisdn.length() - 1))
				|| related_msisdn.equals(msisdn)) {

			related_msisdn = related_msisdn
					+ String.valueOf(((int) Math.random() * 9));
		}

		if ( existSubscriber(msisdn)) {
			
			Reporter.log( "Requested MSISDN ("+msisdn+") already exist -> delete it before test.", PRINT2STDOUT__);
			
			deleteViaXmlrpc(msisdn);
		}
		
		relation.setSponsor(related_msisdn);
		responseParser = this.xmlrpc(
				HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber,
				subscriberParams);
		resultFault = RegressionSuiteXMLRPC.getFault(responseParser);
		Assert.assertEquals(resultFault.getCode(), "100");
		Assert.assertEquals(resultFault.getMessage(),
				"subscriber not found with msisdn " + related_msisdn);

		waitState();

		// Case 3 Create second msisdn
		Map<String, Object> subscriberParams2 = new HashMap<String, Object>();
		subscriberParams2.put(XMLRPCSubscriber.Params.msisdn.name(),
				related_msisdn);
		subscriberParams2.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams2.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams2.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams2.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams2.put(XMLRPCSubscriber.Params.network.name(), "mobile");
		subscriberParams2
				.put(XMLRPCSubscriber.Params.profile.name(), "prepaid");
		responseParser = this.xmlrpc(
				HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber,
				subscriberParams2);
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC
				.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");

		waitState();

		// Case 4 ( Success )
		responseParser = this.xmlrpc(
				HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber,
				subscriberParams);
		resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);
		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");

	}

	/**
	 * 
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 6)
	public void getWrongSubscriber( @Optional(default_msisdn_seed) String inputSeed) throws XMLRPCParserException {

		Reporter.log( "Query via XMLRPC an unexisting subscribers to application.", PRINT2STDOUT__);
		
		String msisdn = generateNewMsisdn(inputSeed);
		
		waitState();

		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);

		ArrayList<String> params = new ArrayList<String>();
		
		params.add(HTTPXMLRPCForm.getAuthenticationParam(user.getUsername(), user.getPassword()));
		params.add(HTTPXMLRPCForm.getSubscriber(subscriberParams));

		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_getSubscriber.call(gui.getLink() + "xmlrpc/", params);

		XMLRPCResultParser responseParser = new XMLRPCResultParser(response.getEntity().toString());

		XMLRPCResultFault resultFault = RegressionSuiteXMLRPC.getFault(responseParser);

		Assert.assertNotNull(resultFault);

		Assert.assertEquals(resultFault.getCode(), "100");

		Assert.assertEquals(resultFault.getMessage(), "subscriber not found for msisdn " + msisdn);

		Reporter.log( "Success!", PRINT2STDOUT__);
	}

	/**
	 * Check the subscriber doesn't exist
	 * 
	 * @param inputSeed
	 * @throws XMLRPCParserException
	 */
	@Parameters("inputSeed")
	@Test(priority = 7)
	public void createNewSubscriber(@Optional(default_msisdn_seed) String inputSeed) throws XMLRPCParserException {

		String msisdn = generateNewMsisdn(inputSeed);

		waitState();

		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);
		subscriberParams.put(XMLRPCSubscriber.Params.subscription_date.name(), today());
		subscriberParams.put(XMLRPCSubscriber.Params.profile.name(), "prepaid");
		subscriberParams.put(XMLRPCSubscriber.Params.subprofile.name(), "");
		subscriberParams.put(XMLRPCSubscriber.Params.rate_plan.name(), "FUN");
		subscriberParams.put(XMLRPCSubscriber.Params.status.name(), "active");
		subscriberParams.put(XMLRPCSubscriber.Params.in_tag.name(), "QAIN");
		subscriberParams.put(XMLRPCSubscriber.Params.network.name(), "mobile");

		ArrayList<String> params = new ArrayList<String>();
		params.add(HTTPXMLRPCForm.getAuthenticationParam(user.getUsername(), user.getPassword()));
		params.add(HTTPXMLRPCForm.getSubscriber(subscriberParams));

		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber.call(gui.getLink() + "xmlrpc/", params);
		XMLRPCResultParser responseParser = new XMLRPCResultParser(response.getEntity().toString());
		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);

		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");

		Reporter.log( "msisdn created : " + msisdn, PRINT2STDOUT__);
		
		nextTestMsisdn = msisdn;
	}

	/**
	 * Get not existing user
	 * 
	 * @param msisdn
	 * @throws XMLRPCParserException
	 */
	@Test(priority = 8, dependsOnMethods={"createNewSubscriber"})
	public void getExistingSubscriber() throws XMLRPCParserException {

		Reporter.log( "Query via XMLRPC an existing msisdn -> " + nextTestMsisdn, PRINT2STDOUT__);
		
		String msisdn = nextTestMsisdn;
		
		Assert.assertTrue( existSubscriber(msisdn) , "Subscriber " + msisdn + " is NOT present into DB application!");

		waitState();

		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);

		ArrayList<String> params = new ArrayList<String>();
		params.add(HTTPXMLRPCForm.getAuthenticationParam(user.getUsername(), user.getPassword()));
		params.add(HTTPXMLRPCForm.getSubscriber(subscriberParams));

		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_getSubscriber.call(gui.getLink() + "xmlrpc/", params);
		XMLRPCResultParser responseParser = new XMLRPCResultParser(response.getEntity().toString());

		Map<ResultType, Object> result = responseParser.parse();

		XMLRPCSubscriber subscriber = (XMLRPCSubscriber) result.get(ResultType.SUBSCRIBER);

		Assert.assertNotNull(subscriber);
		Assert.assertEquals(subscriber.getMsisdn(), msisdn);
		
		Reporter.log( "Subscriber " + msisdn + " is returned via XMLRPC.", PRINT2STDOUT__);
	}

	/**
	 * 
	 * @param msisdn
	 * @throws XMLRPCParserException
	 */
	@Test(priority = 9, dependsOnMethods={"createNewSubscriber","getExistingSubscriber"})
	public void deleteExistingSubscriber() throws XMLRPCParserException {

		Reporter.log( "Delete via XMLRPC an existing msisdn -> " + nextTestMsisdn, PRINT2STDOUT__);
		
		String msisdn = nextTestMsisdn;
		
		Assert.assertTrue( existSubscriber(msisdn) , "Subscriber " + msisdn + " is NOT present into DB application!");

		deleteViaXmlrpc(msisdn);
		
		Assert.assertTrue( !existSubscriber(msisdn) , "Subscriber " + msisdn + " is STILL present into DB application after XMLRPC detele!");

		Reporter.log( "The subscriber "+ msisdn +" was deleted.", PRINT2STDOUT__);
	}

	/**
	 * Execute a delete request via XMLRPC of requested msisdn
	 * 
	 * @param msisdn
	 * 
	 * @throws XMLRPCParserException 
	 */
	private void deleteViaXmlrpc(String msisdn) throws XMLRPCParserException {
		
		waitState();

		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put(XMLRPCSubscriber.Params.msisdn.name(), msisdn);

		ArrayList<String> params = new ArrayList<String>();
		params.add(HTTPXMLRPCForm.getAuthenticationParam(user.getUsername(),user.getPassword()));
		params.add(HTTPXMLRPCForm.getSubscriber(subscriberParams));

		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_deleteSubscriber.call(gui.getLink() + "xmlrpc/", params);

		responseParser = new XMLRPCResultParser(response.getEntity().toString());

		XMLRPCResultSuccess resultSuccess = RegressionSuiteXMLRPC.getSuccess(responseParser);

		Assert.assertNotNull(resultSuccess);
		Assert.assertEquals(resultSuccess.getBoolean(), "0");
	}

	/**
	 * 
	 * @param callType
	 * @param subscriberParams
	 * @return
	 */
	private XMLRPCResultParser xmlrpc(HTTPXMLRPCForm.CallTypes callType,
			Map<String, Object> subscriberParams) {

		ArrayList<String> params = new ArrayList<String>();
		params.add(HTTPXMLRPCForm.getAuthenticationParam(
				user.getUsername(),user.getPassword()));
		params.add(HTTPXMLRPCForm.getSubscriber(subscriberParams));

		ClientResponse<String> response = callType.call(gui.getLink()
				+ "xmlrpc/", params);

		XMLRPCResultParser responseParser = new XMLRPCResultParser(response
				.getEntity().toString());

		return responseParser;

	}

	/**
	 * Check on current tenant schema if number is assigned to subscriber
	 * 
	 * @param msisdn to query
	 * 
	 * @return true if exist a subscribers with passed msisdn
	 */
	private boolean existSubscriber(String msisdn) {

		Subscribers subscribersTable = new Subscribers();

		String query = select(Subscribers.Fields.msisdn).from(subscribersTable)
				.where(op(Subscribers.Fields.msisdn).eq(msisdn)).build();

		ResultSet rs = mysql.execQuery(query);

		boolean found = false;

		try {
			while (rs.next()) {
				found = true;
			}
		} catch (SQLException e) {
			
			Assert.fail(e.getMessage());
		}

		return found;
	}

	/**
	 * 
	 * @param responseParser
	 * @return
	 * @throws XMLRPCParserException
	 */
	private static XMLRPCResultSuccess getSuccess(XMLRPCResultParser responseParser) throws XMLRPCParserException {

		Map<ResultType, Object> result = responseParser.parse();

		XMLRPCResultSuccess success = (XMLRPCResultSuccess) result.get(ResultType.BOOLEAN);

		return success;

	}

	/**
	 * 
	 * @param responseParser
	 * @return
	 * @throws XMLRPCParserException
	 */
	private static XMLRPCResultFault getFault(XMLRPCResultParser responseParser) throws XMLRPCParserException {

		Map<ResultType, Object> result = responseParser.parse();

		XMLRPCResultFault fault = (XMLRPCResultFault) result.get(ResultType.FAULT);

		return fault;

	}

	/**
	 * Default wait state based on general delay
	 */
	private static void waitState() {

		try {
			Thread.sleep(XMLRPC_CALL_DELAY);
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}

}
