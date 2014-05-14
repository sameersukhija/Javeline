package com.lumata.e4o.gui.customercare;

import static com.lumata.common.testing.orm.Filter.and;
import static com.lumata.common.testing.orm.Filter.count;
import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Query.update;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.customercare.CustomerCareLightForm.CustomerCareLightRestTab;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.common.JsonConfig;
import com.lumata.e4o.schema.tenant.Campaigns;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.schema.tenant.SubsBadges;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

public class CustomerCareRestCall {

	private final boolean testsEnabled = true;

	private static final Logger logger = LoggerFactory.getLogger( CustomerCareLightViaURLSupemanRole.class );
	
	private enum RestParams { link, login, password, msisdn, ccTab, startDate, endDate, page }
	
	private long TIMEOUT = 60000;
	private long ATTEMPT_TIMEOUT = 100;
	
	private SeleniumWebDriver seleniumWebDriver;
	private String browser;
	private NetworkEnvironment env;
	private Server gui;
	@SuppressWarnings("unused")
	private User guiUser;
	private Service actruleServer;
	private Mysql mysqlTenant;
	private ExpressionKernelCommands ekc;
	
	public static class RestCall extends RestClient {
		
		private static ClientResponse<String> response;
		
		public RestCall() {
			
			super();
			
		}
		
		public static RestCall getTokenList( String link, String msisdn, CustomerCareLightRestTab ccTab, Calendar startDate, Calendar endDate, Integer page, User user ) throws Exception {
			
			return getTokenList( link, msisdn, ccTab, String.valueOf( startDate.getTimeInMillis() ), String.valueOf( endDate.getTimeInMillis() ), String.valueOf( page ), user );
			
		}
		
		public static RestCall getTokenList( String link, String msisdn, CustomerCareLightRestTab ccTab, String startDate, String endDate, String page, User user ) throws Exception {
			
			Map<RestParams, String> options = new HashMap<RestParams, String>();
			options.put( RestParams.link , ( null != link ? link : "" ) );
			options.put( RestParams.msisdn , ( null != msisdn ? msisdn : "" ) );
			options.put( RestParams.ccTab , ( null != ccTab ? ccTab.toString().toLowerCase() : "" ) );
			options.put( RestParams.startDate , ( null != startDate ? startDate : "" ) );
			options.put( RestParams.endDate , ( null != endDate ? endDate : "" ) );
			options.put( RestParams.page , ( null != page ? page : "" ) );
			
			if( null != user ) { 
				
				if( null != user.getUsername() ) { options.put( RestParams.login , user.getUsername() ); }
				
				if( null != user.getPassword() ) { options.put( RestParams.password , user.getPassword() ); }
				
			}
					
			return getTokenList( options );
			
		}
		
		public static RestCall getTokenList( Map<RestParams, String> options ) throws Exception {
			
			RestCall restCall = new RestCall();
			
			String link = "${link}rest/customercare/${msisdn}/${ccTab}?end=${endDate}&page=${page}&start=${startDate}"; 
								
			if( options != null ) {
			
				if( options.containsKey( RestParams.link ) ) { link = link.replace( "${link}" , options.get( RestParams.link ) ); }
				if( options.containsKey( RestParams.msisdn ) ) { link = link.replace( "${msisdn}" , options.get( RestParams.msisdn ) ); }
				if( options.containsKey( RestParams.ccTab ) ) { link = link.replace( "${ccTab}" , options.get( RestParams.ccTab ) ); }
				if( options.containsKey( RestParams.startDate ) ) { link = link.replace( "${startDate}" , options.get( RestParams.startDate ) ); }
				if( options.containsKey( RestParams.endDate ) ) { link = link.replace( "${endDate}" , options.get( RestParams.endDate ) ); }
				if( options.containsKey( RestParams.page ) ) { link = link.replace( "${page}" , options.get( RestParams.page ) ); }
				if( options.containsKey( RestParams.login ) || options.containsKey( RestParams.password ) ) { 
					
					restCall.cleanHeader();
					
					if( options.containsKey( RestParams.login ) ) { restCall.header( RestParams.login.name(), options.get( RestParams.login ) ); }
					
					if( options.containsKey( RestParams.password ) ) { restCall.header( RestParams.password.name(), options.get( RestParams.password ) ); }
					
				}			
				
			}
			
			response = restCall.get( link );
			
			return restCall;
			
		}
		
		public RestCall validate( Integer statusCode ) throws JSONException {
			
			JSONResponse.getInstance().validate( statusCode );
			
			return this;
			
		}
		
		public RestCall validate( String status, Integer statusCode ) throws JSONException {
			
			JSONResponse.getInstance( response.getEntity().toString() ).validate( status, statusCode );
			
			return this;
			
		}
		
		public RestCall validate( String status, Integer statusCode, Integer entitiesCount ) throws JSONException {
			
			JSONResponse.getInstance( response.getEntity().toString() ).validate( status, statusCode, entitiesCount );
			
			return this;
			
		}
				
	}
	
	public static class JSONResponse extends JsonConfig {
		
		public JSONResponse( JSONObject response ) {
			
			super( response );
			
		}
		
		public static JSONResponse getInstance() throws JSONException {
			
			JSONResponse jsonResponse = new JSONResponse( new JSONObject( "{\"status\":null,\"statusCode\":null,\"exception\":null,\"validationErrors\":null,\"businessError\":{\"errorCode\":null,\"token\":null,\"message\":null},\"value\":null}" ) );
			
			return jsonResponse;
			
		}

		public static JSONResponse getInstance( String response ) throws JSONException {
			
			JSONResponse jsonResponse = new JSONResponse( new JSONObject( response ) );
			
			return jsonResponse;
			
		}
		
		public static JSONResponse getInstance( JSONObject response ) {
			
			JSONResponse jsonResponse = new JSONResponse( response );
			
			return jsonResponse;
			
		}
	
		public String getStatus() {
			return getStringFromPath("status");
		}
		
		public Integer getStatusCode() {
			return getIntegerFromPath("statusCode");
		}
		
		public String getException() {
			return getStringFromPath("exception");
		}
				
		public JSONArray getValidationErrors() throws JSONException {
			return getJSONArrayFromPath("validationErrors");
		}
		
		public Integer getBusinessErrorCode() {
			return getIntegerFromPath("businessError.errorCode");
		}

		public String getBusinessErrorToken() {
			return getStringFromPath("businessError.token");
		}

		public String getBusinessErrorMessage() {
			return getStringFromPath("businessError.message");
		}

		public Integer getValuePage() {
			return getIntegerFromPath("value.page");
		}
		
		public Integer getValueCount() {
			return getIntegerFromPath("value.count");
		}
		
		public JSONArray getValueEntities() throws JSONException {
			return getJSONArrayFromPath("value.entities");
		}
		
		public void validate( Integer statusCode ) {
			
			Assert.assertEquals( this.getStatusCode(), ( this.getStatusCode() != null ? statusCode : null ) );
				
		}
		
		public void validate( String status, Integer statusCode ) {
			
			Assert.assertEquals( this.getStatus(), ( this.getStatus() != null ? status : null ) );
			
			Assert.assertEquals( this.getStatusCode(), ( this.getStatusCode() != null ? statusCode : null ) );
			
		}
		
		public void validate( String status, Integer statusCode, Integer entitiesCount ) throws JSONException {
			
			Assert.assertEquals( this.getStatus(), ( this.getStatus() != null ? status : null ) );
			
			Assert.assertEquals( this.getStatusCode(), ( this.getStatusCode() != null ? statusCode : null ) );
			
			Assert.assertEquals( this.getValueCount(), ( this.getValueCount() != null ? entitiesCount : null ) );
						
		}
		
	}
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create Selenium WebDriver instance */
		gui = env.getServer( gui_server );
		
		guiUser = gui.getUser( user );
		
		this.browser = browser;
	
		/** Create actrule server instance */
		actruleServer = env.getService( Service.Type.ssh, gui_server );
				
		/** create mysql tenant connection */
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
				
		/** get ssh connection with actrule server */
		ekc = new ExpressionKernelCommands( actruleServer, "root" );
	
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		//seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test(enabled=testsEnabled, priority = 1 )
	public void checkMD5Disable() throws Exception {
	
		Boolean md5Enabled = this.getMD5LoginConfguration();
		
		if( md5Enabled ) { 
			
			this.enableMD5LoginConfguration( !md5Enabled );	
			
		}
		
	}
	
	@Test( enabled=testsEnabled, dependsOnMethods = "checkMD5Disable" )
	public void checkGetTokenRestCallWithoutMD5() throws Exception {
		
		String msisdn = "393669393643";
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		
		logger.info( Log.CHECKING.createMessage( "without user and password" ) );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, null ).validate( "NO_ACCESS_GRANT", 401 );
		
		
		logger.info( Log.CHECKING.createMessage( "with empty user and without password" ) );
		
		User user = new User();
		user.setUsername( "" );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "NO_ACCESS_GRANT", 401 );
		
	
		logger.info( Log.CHECKING.createMessage( "with correct user and without password" ) );
		
		user.setUsername( "superman" );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "NO_ACCESS_GRANT", 401 );
	
		
		logger.info( Log.CHECKING.createMessage( "with correct user and empty password" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "" );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "NO_ACCESS_GRANT", 401 );
			
		
		logger.info( Log.CHECKING.createMessage( "with wrong user and wrong password" ) );
		
		user.setUsername( "wrong" );
		user.setPassword( "wrong" );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "NO_ACCESS_GRANT", 401 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user and password but empty msisdn" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "super2010Man" );
		
		RestCall.getTokenList( gui.getLink(), "", CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( 404 );
	
		
		logger.info( Log.CHECKING.createMessage( "with correct user and password but wrong msisdn" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "super2010Man" );
		
		RestCall.getTokenList( gui.getLink(), "test", CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "UNKNOW_ERROR", 500 );
	
		
		logger.info( Log.CHECKING.createMessage( "with correct user and password but not existing msisdn" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "super2010Man" );
		
		RestCall.getTokenList( gui.getLink(), "0000", CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "OK", 200 );
		
		
		/** # The min pag value is -1 but in the message is reported 0 */
		logger.info( Log.CHECKING.createMessage( "with correct user, password and msisdn but wrong page" ) );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 0, user ).validate( "WRONG_ARGS", 400 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but wrong dateRange startDate > endDate" ) );
		
		startDate.add( Calendar.DATE , 1 );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "OK", 200 );

		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but empty startDate and endDate" ) );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, "", "", "1", user ).validate( 404 );

		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but wrong format for startDate and endDate" ) );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, "2014-04-01", "2014-04-01", "1", user ).validate( 404 );
				
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but wrong format for startDate and endDate" ) );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, "startDate", "endDate", "1", user ).validate( 404 );
			
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, and dateRange" ) );
		
		startDate.add( Calendar.MONTH, -3 );
		
		endDate.set( endDate.get( Calendar.YEAR ), endDate.get( Calendar.DATE ), endDate.get( Calendar.DATE ), 23, 59, 59 );
		
		RestCall.getTokenList( gui.getLink(), msisdn, CustomerCareLightRestTab.Token, startDate, endDate, 1, user ).validate( "OK", 200, getTokenCount( msisdn ) );
	
	}
	
	private Boolean getMD5LoginConfguration() throws SQLException {
		
		Conf confTable = new Conf();
		
		confTable.setName( "md5_authentication" );
		confTable.setSection( "system" );
		
		String query = select().
						from( confTable ).
						where( 
								op( Conf.Fields.name ).eq(),
								and( op( Conf.Fields.section ).eq() )
						).
						build();
		
		ResultSet rs = mysqlTenant.execQuery( query );
		
		while( rs.next() ) {
			
			confTable.setCurrent( rs.getString( Conf.Fields.current.name() ) );
		
		}
		
		logger.info( "MD5 access is " + ( Boolean.valueOf( confTable.getCurrent() ) ? "enabled" : "disabled"  ) );
		
		return Boolean.valueOf( confTable.getCurrent() );
		
	}
	
	private void enableMD5LoginConfguration( Boolean currentValue ) throws SQLException, FormException {
		
		Conf confTable = new Conf();
		
		confTable.setName( "md5_authentication" );
		confTable.setSection( "system" );
		confTable.setCurrent( String.valueOf( currentValue ) );
			
		String query = update( confTable ).
						set( op( Conf.Fields.current ).eq() ).
						where( 
								op( Conf.Fields.name ).eq(),
								and( op( Conf.Fields.section ).eq() )
						).
						build();
				
		mysqlTenant.execUpdate( query );
		
		Assert.assertTrue( ekc.expressionRestart() );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );		
				
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT ).refresh() );
		
		seleniumWebDriver.close();
		
	}
	
	public Integer getTokenCount( String msisdn ) throws SQLException {
		
		Integer tokenCount = 0;
		
		Token tokenTable = new Token();
		Campaigns campaigns = new Campaigns();
		
		String query = select( count() ).
						from( tokenTable ).
						join( campaigns ).
						on( op( Token.Fields.feature_id ).eq( Campaigns.Fields.campaign_id ) ).
						where( op( Token.Fields.msisdn ).eq( msisdn ) ).
						build();
		
		ResultSet rs = mysqlTenant.execQuery( query );
		
		while( rs.next() ) {
			
			tokenCount = rs.getInt( "COUNT( * )" );
			
		}
		
		return tokenCount;
		
	}
	
	public Integer getBadgeCount( String msisdn ) throws SQLException {
		
		Integer badgeCount = 0;
		
		SubsBadges badgeTable = new SubsBadges();
		
		String query = select( count() ).
						from( badgeTable ).
						where( op( SubsBadges.Fields.msisdn ).eq( msisdn ) ).
						build();
		
		ResultSet rs = mysqlTenant.execQuery( query );
		
		while( rs.next() ) {
			
			badgeCount = rs.getInt( "COUNT( * )" );
			
		}
		
		return badgeCount;
		
	}
	
	@AfterClass
	public void end() {
		
		if( null != seleniumWebDriver ) { seleniumWebDriver.close(); }
		
		if( null != mysqlTenant ) { mysqlTenant.close(); }
		
	}
		
}
