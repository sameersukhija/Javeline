package com.lumata.e4o.scheduling.scheduledtasks;

import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Query.insert;
import static com.lumata.common.testing.orm.Query.update;
import static com.lumata.common.testing.orm.Query.delete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.annotations.testplan.Steps;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlColumn;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.exceptions.XMLRPCParserException;
import com.lumata.e4o.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.e4o.gui.xmlrpc.XMLRPCChannel;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultFault;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultSuccess;
import com.lumata.e4o.gui.xmlrpc.XMLRPCSubscriber;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser.ResultType;
import com.lumata.e4o.schema.tenant.CollectedFilesStats;
import com.lumata.e4o.schema.tenant.CompositeBundle;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.BundleName;
import com.lumata.e4o.system.cdr.types.CDRBundle;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;
import com.lumata.e4o.system.environment.ExpressionSystem;
import com.lumata.e4o.system.fields.FieldString;

public class TestSubscriberDelayedRemovalTask {
	
	private static final Logger logger = LoggerFactory.getLogger( TestSubscriberDelayedRemovalTask.class );
	
	private enum  ChannelStatus {
		NO_CHANNELS,
		HAS_SMS_CHANNEL,
		HAS_MAIL_CHANNEL,
		HAS_ALL_CHANNELS;
	}
	
	private final long XMLRPC_CALL_DELAY = 100;
	private final long COLLECTOR_PROCESSING_POLLING = 5000;
	private final long COLLECTOR_PROCESSING_POLLING_TIMEOUT = 300000;
	
	private Mysql mysql_tenant;
	private NetworkEnvironment env;
	private ExpressionKernelCommands ekcCollector;
	private Server actruleServer;
	private User superman;
	
	private List<Long> subscribers_range;
	private JSONObject cdrs;
	private ArrayList<String> cdrTypes;
	private Map<String, Object> cdrParameters;
	private Boolean hasCDRBundle;
	
	/** initialize test */
	@Parameters({"environment", "tenant"})
	@BeforeTest( description = "set pre condition" )
	@Steps( actions = {
				"load environment configuration",
				"get mysql tenant connection",
				"load cdrs configuration",
				"set cdrs collector elaboration checks",
				"set cdr mandatory parameters",
				"set cdr palce holder parameters"
	})
	public void init( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws NetworkEnvironmentException, XMLRPCParserException, JSONSException, IOFileException, JSONException {

		logger.info( Log.LOADING.createMessage( "loading test configuration" ) );
		
		/** set subscriber creation range */
		subscribers_range = Arrays.asList( 3399900001L, 3399900010L );
		
		/** load environment */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		ekcCollector = new ExpressionKernelCommands( env.getService( Service.Type.ssh , "collector" ), "root" );
		actruleServer = env.getServer( "actrule" ); 
		superman = actruleServer.getUser( "superman" );
		
		/** get mysql tenant connection */
		mysql_tenant = new Mysql( env.getDataSource( tenant ) );
		
		/** load cdrs configuration */
		cdrs = JSONUtils.loadJSONResource( "input/cdr/SubscriberDelayedRemovalTask", "cdr_subscribers_usage.json" );

		/** check if cdr bundle will be elaborated */
		hasCDRBundle = cdrs.has( CDRBundle.class.getSimpleName() );
		
		/** load cdr types */
		cdrTypes = new ArrayList<String>();
		
		/** check the cdr will be elaborated from the collector */
		@SuppressWarnings("unchecked")
		Iterator<String> cdr = cdrs.keys();
		while( cdr.hasNext() ) {
			
			String cdrTypeName = cdr.next();
			
			if( cdrs.getJSONObject( cdrTypeName ).getBoolean( "enabled" ) ) { cdrTypes.add( cdrTypeName.toUpperCase() ); }
			
        }
		
		/** configure cdr elaboration parameters */
		cdrParameters = new HashMap<String, Object>();
		cdrParameters.put( CDR.Parameters.env.name(), env );
		cdrParameters.put( CDR.Parameters.tenant.name(), tenant );
		cdrParameters.put( CDR.Parameters.cfgDir.name(), "input/cdr/SubscriberDelayedRemovalTask" );
		//cdrParameters.put( CDR.Parameters.cfgFile.name(), "cdr_lifecycle_subscribers_creation.json" );
		cdrParameters.put( "###date###", "@current" );
		cdrParameters.put( "###past_date###", "@current-6MONTH;" );
		cdrParameters.put( "###validity_date###", "@current+6MONTH;" );
		cdrParameters.put( "###deactivation_date###", "@current+1YEAR;" );
							
	}	

	/** create subscribers */
	@Test( enabled = false, priority = 1, description = "create subscriber" )
	@Steps( actions = {
		"delete subscribers if exist",
		"set subscriber configuration",
		"insert subscriber via xmlrpc call"
	})	
	public void insertSubscribers() throws XMLRPCParserException, CDRException {

		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar date = Calendar.getInstance();
				
		for( long msisdn = subscribers_range.get( 0 ); msisdn <= subscribers_range.get( 1 ); msisdn++ ) {
			
			/** delete subscriber if exists */
			if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); }
			
			this.sleep( XMLRPC_CALL_DELAY );

			String query = update( new Subscribers() )
							.set( 	op( Subscribers.Fields.network_id  ).eq( 1 ),
									op( Subscribers.Fields.tongue ).eq( "ENG" )
							)
							.where( op( Subscribers.Fields.msisdn ).eq( msisdn ) ).build();				
						
			/** set subscriber attributes */
			Map<String, Object> subscriberParams = new HashMap<String, Object>();
			subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
			subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), sdf.format( date.getTime() ) );
			subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
			subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
			subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
			subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
						
			/** set subscriber channels */
			Map<String, XMLRPCChannel> channels_list = new HashMap<String, XMLRPCChannel>();
			
			int channelStatusesType = (int)(msisdn % ChannelStatus.values().length );
			
			switch( ChannelStatus.values()[ channelStatusesType ] ) {
			
				case HAS_SMS_CHANNEL: { 
					
					XMLRPCChannel smsChannel = new XMLRPCChannel( XMLRPCChannel.Type.SMS, String.valueOf( msisdn ), true );
					channels_list.put( "sms_channel" , smsChannel );
					
					break;
				}
				case HAS_MAIL_CHANNEL: { 
					
					FieldString randomString = new FieldString();
					randomString.setStringStrategyRandom( 6 );
					
					StringBuilder mail = new StringBuilder();
					mail.append( randomString.getString() ).append( "." ).append( randomString.getString() ).append( "@lumatagroup.com" );					
					
					XMLRPCChannel mailChannel = new XMLRPCChannel( XMLRPCChannel.Type.MAIL, mail.toString(), true );
					channels_list.put( "mail_channel" , mailChannel );
					
					break;
				}
				case HAS_ALL_CHANNELS: { 
					
					XMLRPCChannel smsChannel = new XMLRPCChannel( XMLRPCChannel.Type.SMS, String.valueOf( msisdn ), true );
					channels_list.put( "sms_channel" , smsChannel );
					
					FieldString randomString = new FieldString();
					randomString.setStringStrategyRandom( 6 );
					
					StringBuilder mail = new StringBuilder();
					mail.append( randomString.getString() ).append( "." ).append( randomString.getString() ).append( "@lumatagroup.com" );					
					
					XMLRPCChannel mailChannel = new XMLRPCChannel( XMLRPCChannel.Type.MAIL, mail.toString(), true );
					channels_list.put( "mail_channel" , mailChannel );
										
					break;
				}				
				default: { /**NO_CHANNELS*/ break; }
				
			}
			
			if( channels_list.size() > 0 ) { subscriberParams.put( XMLRPCSubscriber.Params.channels.name(), channels_list ); }		
			
			/** insert subscriber via xmlrpc call*/
			responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
			
			XMLRPCResultSuccess resultSuccess = this.getSuccess( responseParser );
			Assert.assertNotNull( resultSuccess );
			Assert.assertEquals( resultSuccess.getBoolean(), "0" );
			
			mysql_tenant.execUpdate( query );
					
			logger.info( "msisdn entered: " + msisdn );
			
		}

	}
	
	@Test( enabled = false, priority = 2, description = "insert bundles" )
	@Steps( actions = {
		"insert bundle if a cdr bundle is elaborated with increment strategy"
	})	
	public void insertBundle() throws JSONException, CDRException {
		
		/** if a cdr bundle will be elaborated */
		if( hasCDRBundle ) {
			
			CompositeBundle bundleTable = new CompositeBundle();
					
			/** load cdr bundle configuration */
			JSONObject cdrBundleCfg = cdrs.getJSONObject( CDRBundle.class.getSimpleName() );
			JSONObject bundleNameCfg = cdrBundleCfg.getJSONObject( BundleName.class.getSimpleName() );
			
			if( bundleNameCfg.getString( "strategy" ).equals( "Increment" ) ) { 
			
				JSONArray bundleNameParametersCfg = bundleNameCfg.getJSONArray( "parameters" );
				
				if( bundleNameParametersCfg.length() == 3 ) {
				
					FieldString bundle = new FieldString();
					bundle.setStringStrategyIncrement( bundleNameParametersCfg.getString( 0 ), bundleNameParametersCfg.getInt( 1 ), bundleNameParametersCfg.getInt( 2 ) );
					
					for( long bundleIndex = bundleNameParametersCfg.getInt( 1 ) + 1; bundleIndex <= cdrBundleCfg.getInt( "linesCount" ); bundleIndex++ ) {
					
						/** delete bundle if bundle index exists */
						String query = delete().from( bundleTable ).where( op( CompositeBundle.Fields.bundle ).eq( bundleIndex ) ).build();
						mysql_tenant.execUpdate( query.toString() );
												
						/** insert new bundle */
						bundleTable.setBundle( bundleIndex );
						bundleTable.setBundleName( bundle.getString() );
						
						query = insert( bundleTable ).values().build(); 
						
						mysql_tenant.execUpdate( query.toString() );
									
					}
				
				}
			
			}
		
		}
			
	}
		
	@Test( enabled = true, priority = 3, description = "cdrs elaboration and database filling" )
	@Steps( actions = {
		"load cdrs configuration",
		"elaborate cdrs",
		"wait for cdrs elaboration finished",
		"get schema tables changes"
	})	
	public void fillTenantDatabase() throws CDRException, SQLException, JSONSException, IOFileException {

		logger.info( Log.GETTING.createMessage( "schema tables sizes" ) );
		
		/** get schema table sizes */
		Map<String, Map<String, Integer>> schemaSizes = new HashMap<String, Map<String, Integer>>();
		
		schemaSizes.put( "before" , this.getSchemaContentSizes() );

		/** load cdrs json configuration */
		Calendar date = Calendar.getInstance();
		
		CDR cdr = new CDR();
		
		cdrParameters.put( CDR.Parameters.cfgFile.name(), "cdr_subscribers_usage.json" );
		
		logger.info( Log.PUTTING.createMessage( "cdrs in the remote server" ) );
		
		/** elaborate cdrs */
		cdr.feeder( date, date, cdrParameters );
		
		logger.info( Log.CHECKING.createMessage( "cdrs elaboration" ) );
		
		/** check cdrs elaboration */
		Calendar startCDRProcessingDate = ekcCollector.getServerDateTime();

		ExpressionSystem.waitForCDRProcessingCompleted( mysql_tenant, cdrTypes, startCDRProcessingDate, COLLECTOR_PROCESSING_POLLING, COLLECTOR_PROCESSING_POLLING_TIMEOUT );
		
		this.sleep( COLLECTOR_PROCESSING_POLLING );
		
		logger.info( Log.GETTING.createMessage( "schema tables sizes" ) );
		
		/** get schema table sizes */
		schemaSizes.put( "after" , this.getSchemaContentSizes() );
		
		Map<String, Integer[]> schemaSizesDiff = this.getSchemaSizesDiff( schemaSizes.get( "before" ), schemaSizes.get( "after" ) );
		
		logger.info( Log.CHECKING.createMessage( "schema tables sizes differences" ) );
		
		/** check schema tables changes */
		for( String table : schemaSizesDiff.keySet() ) {
			
			System.out.println( "Table: " + table + " ( " + schemaSizesDiff.get( table )[ 0 ] + " ) - ( " + schemaSizesDiff.get( table )[ 1 ] + " ) " ); 
		
		}
		System.out.println( "-------------------------" );
		/** check schema tables changes */
		Calendar endCDRProcessingDate = ekcCollector.getServerDateTime();
		
		Map<String, Integer> schemaUpdatesSizesDiff = this.getSchemaContentUpdatesSizes( startCDRProcessingDate, endCDRProcessingDate );
				
		for( String table : schemaUpdatesSizesDiff.keySet() ) {
			
			System.out.println( "Table: " + table + " ( " + schemaUpdatesSizesDiff.get( table ) + " )" ); 
		
		}
		
	}

	@Test( enabled = false, priority = 1 )
	public void exec() throws NetworkEnvironmentException, CDRException, JSONSException, IOFileException {

		Calendar date = Calendar.getInstance();
		
		CDR cdr = new CDR();
		
		cdr.feeder( date, date, cdrParameters );	
		
	}
	
	/** delete existing subscriber via xmlrpc call */
	public void deleteExistingSubscriber( Long msisdn ) throws XMLRPCParserException  {
				
		Assert.assertTrue( msisdn != null );
		
		this.sleep( XMLRPC_CALL_DELAY );
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
						
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword()) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		//System.out.println( params.toString() );
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_deleteSubscriber.call( actruleServer.getLink() + "xmlrpc/" , params );
				
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		XMLRPCResultSuccess resultSuccess = this.getSuccess( responseParser );
		
		Assert.assertNotNull( resultSuccess );
		
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		logger.info( "msisdn deleted: " + msisdn );
		
	}
	
	/** xmlrpc call parser */
	private XMLRPCResultParser xmlrpc( HTTPXMLRPCForm.CallTypes callType, Map<String, Object> subscriberParams ) {
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword()) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		//System.out.println( params.toString() );
		ClientResponse<String> response = callType.call( actruleServer.getLink() + "xmlrpc/" , params );
		 
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		return responseParser;
		
	}
	
	/** check if subscriber exists */
	private boolean isSubscriber( Long msisdn ) {
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).where( op( Subscribers.Fields.msisdn ).eq( msisdn ) ).build();
		
		ResultSet rs = mysql_tenant.execQuery( query );		
		
		boolean found = false;
		
		try {
			while ( rs.next() ) {
				found = true;
			}
		} catch ( SQLException e ) {}
		
		return found;
		
	}
	
	/** get xmlrpc call success */
	private XMLRPCResultSuccess getSuccess( XMLRPCResultParser responseParser ) throws XMLRPCParserException {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultSuccess success = (XMLRPCResultSuccess)result.get( ResultType.BOOLEAN );		
		
		return success;
		
	}
	
	/** get xmlrpc call failure */
	private XMLRPCResultFault getFault( XMLRPCResultParser responseParser ) throws XMLRPCParserException {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultFault fault = (XMLRPCResultFault)result.get( ResultType.FAULT );		
		
		return fault;
		
	}
	
	/** sleep */
	public void sleep( long delay ) {
		
		try { Thread.sleep( delay ); } catch( InterruptedException e ) { logger.error( e.getMessage(), e ); }	
		
	}
	
	/** get schema table sizes */
	public Map<String, Integer> getSchemaContentSizes() throws SQLException {
		
		Map<String, Integer> schemaTableSizes = new HashMap<String, Integer>();
		
		ArrayList<String> schema = MysqlUtils.getSchema( mysql_tenant );
		
		for( int i = 0; i < schema.size(); i++ ) {
			
			String tableName = schema.get( i );
			
			int tableSize = MysqlUtils.getTableSize( tableName, mysql_tenant );
			
			schemaTableSizes.put( tableName, tableSize );
			
		}
		
		return schemaTableSizes;
		
	}
	
	/** get schema table update sizes */
	public Map<String, Integer> getSchemaContentUpdatesSizes( Calendar minReferenceDate, Calendar maxReferenceDate ) throws SQLException {
		
		Map<String, Integer> schemaTableUpdatesSizes = new HashMap<String, Integer>();
		
		ArrayList<MysqlColumn.MysqlTypes> columnTypes = new ArrayList<MysqlColumn.MysqlTypes>();
		columnTypes.add( MysqlColumn.MysqlTypes.DATE );
		columnTypes.add( MysqlColumn.MysqlTypes.DATETIME );
		columnTypes.add( MysqlColumn.MysqlTypes.TIME );
		columnTypes.add( MysqlColumn.MysqlTypes.TIMESTAMP );
		
		ArrayList<String> schema = MysqlUtils.getSchema( mysql_tenant );
		
		for( int i = 0; i < schema.size(); i++ ) {
			
			String tableName = schema.get( i );

			ArrayList<String> tableColumns = MysqlUtils.getTableColumnsByTypes( tableName, columnTypes, mysql_tenant );
			
			for( int tc = 0; tc < tableColumns.size(); tc++ ) {
					
				int tableUpdatesSize = MysqlUtils.getTableUpdatesCount( tableName, tableColumns.get( tc ), minReferenceDate, maxReferenceDate, mysql_tenant );
				
				if( tableUpdatesSize > 0 ) { schemaTableUpdatesSizes.put( tableName, tableUpdatesSize ); }
				
			}
						
		}
		
		return schemaTableUpdatesSizes;
		
	}
	
	/** get schema table sizes differences */
	public Map<String, Integer[]> getSchemaSizesDiff( Map<String, Integer> schemaLeft, Map<String, Integer> schemaRight ) throws SQLException {
		
		Map<String, Integer[]> schemaSizesDiff = new HashMap<String, Integer[]>();
		
		for( String table : schemaLeft.keySet() ) {
		
			if( schemaRight.containsKey( table ) && !schemaLeft.get( table ).equals( schemaRight.get( table ) ) ) {
				
				Integer[] sizeDiff = new Integer[2];
				sizeDiff[ 0 ] = schemaLeft.get( table );
				sizeDiff[ 1 ] = schemaRight.get( table );
			
				schemaSizesDiff.put( table, sizeDiff );
				
			}			
			
		}
		
		return schemaSizesDiff;
		
	}

	/** check collector cdr processing finished */
	public Boolean checkCollectorProcessingCompleted( ArrayList<CollectedFilesStats> beforeCfsTable, ArrayList<CollectedFilesStats> afterCfsTable ) {
				
		for( int cdrIndex = 0; cdrIndex < cdrTypes.size(); cdrIndex++ ) {
			
			logger.info( Log.CHECKING.createMessage( cdrTypes.get( cdrIndex ) + " elaboration finished" ) );
			
			boolean cdrElaborated = false;
			
			for( int cfs2 = 0; cfs2 < beforeCfsTable.size(); cfs2++ ) {
				//System.out.println( afterCfsTable.get( cfs2 ).getHandler() + " = " + cdrTypes.get( cdrIndex ) );
				if( afterCfsTable.get( cfs2 ).getHandler().contains( cdrTypes.get( cdrIndex ) ) ) {
				
					cdrElaborated = true;
					
					for( int cfs1 = 0; cfs1 < beforeCfsTable.size(); cfs1++ ) {
					
						if( beforeCfsTable.get( cfs1 ).getHandler().equals( afterCfsTable.get( cfs2 ).getHandler() ) ) {
							
							if( beforeCfsTable.get( cfs1 ).getProcessedDate().equals( afterCfsTable.get( cfs2 ).getProcessedDate() ) ) { return false; }
							
						}
						
					}
					
				}
				
			}
			
			if( !cdrElaborated ) { return false; }
						
		}
		
		return true;
		
	}
	
	/** check collected_files_stats tenant table status */
	public ArrayList<CollectedFilesStats> getCollectedFilesStatsTableContent() throws SQLException {
		
		ArrayList<CollectedFilesStats> cfsTableContent = new ArrayList<CollectedFilesStats>();
		
		String query = select().from( new CollectedFilesStats() ).build();
		
		ResultSet rs = mysql_tenant.execQuery( query );
		
		while( rs.next() ) {
			
			CollectedFilesStats cfsRow = new CollectedFilesStats( rs );
			
			cfsTableContent.add( cfsRow );
			
		}
		
		return cfsTableContent;
		
	}
	
	/*
	@Parameters({"environment", "tenant"})
	@Test( enabled = true )
	public void create_cdr( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws IOFileException, ClassNotFoundException, JSONSException, NetworkEnvironmentException, EnvironmentException {

		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ExpressionKernelCommands ekc = new ExpressionKernelCommands( env.getSSHService( "actrule" ), "root" );
		
		System.out.println( ekc.execTask( 23500, 1, ExpressionKernelCommands.Task.SubscriberDelayedRemoval ) );
		
	}
	*/
	
	/*
	@Parameters({"environment", "tenant"})
	@Test( enabled = exec_ExpiredData_task )
	public void exec_ExpiredData_task( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws NetworkEnvironmentException {

		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ExpressionKernelCommands ekc = new ExpressionKernelCommands( env.getSSHService( "actrule" ), "root" );
		
		Assert.assertEquals( ekc.execTask( 23500, 1, ExpressionKernelCommands.Task.ExpiredData ), ExpressionKernelCommands.TaskStatus.OK );
				
	}
	*/
}
