package com.lumata.e4o.cdrinterface;

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
import com.lumata.e4o.system.cdr.annotations.BundleName;
import com.lumata.e4o.system.cdr.types.CDRBundle;
import com.lumata.e4o.system.csv.types.CSVString;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;
import com.lumata.e4o.system.environment.ExpressionSystem;

public class GenerateCDRFromJSON {
	
	private static final Logger logger = LoggerFactory.getLogger( GenerateCDRFromJSON.class );
	
	private final long COLLECTOR_PROCESSING_POLLING = 5000;
	private final long COLLECTOR_PROCESSING_POLLING_TIMEOUT = 300000;
	
	private Mysql mysql_tenant;
	private NetworkEnvironment env;
	private ExpressionKernelCommands ekcCollector;

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
		
		/** load environment */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ekcCollector = new ExpressionKernelCommands( env.getService( Service.Type.ssh , "collector" ), "root" );
		
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
		cdrParameters.put( CDR.Parameters.cfgFile.name(), "cdr_subscribers_usage.json" );
		//cdrParameters.put( CDR.Parameters.cfgFile.name(), "cdr_lifecycle_subscribers_creation.json" );
		cdrParameters.put( "###date###", "@current" );
		cdrParameters.put( "###past_date###", "@current-6MONTH;" );
		cdrParameters.put( "###validity_date###", "@current+6MONTH;" );
		cdrParameters.put( "###deactivation_date###", "@current+1YEAR;" );
							
	}
			
	@Test( enabled = true, priority = 3, description = "cdrs elaboration and database filling" )
	public void generateCDRFromJSON() throws CDRException, SQLException, JSONSException, IOFileException {

		/** load cdrs json configuration */
		Calendar date = Calendar.getInstance();
		
		CDR cdr = new CDR();
		
		logger.info( Log.PUTTING.createMessage( "cdrs in the remote server" ) );
		
		/** elaborate cdrs */
		cdr.feeder( date, date, cdrParameters );
		
		logger.info( Log.CHECKING.createMessage( "cdrs elaboration" ) );
		
		/** check cdrs elaboration */
		Calendar startCDRProcessingDate = ekcCollector.getServerDateTime();

		ExpressionSystem.waitForCDRProcessingCompleted( mysql_tenant, cdrTypes, startCDRProcessingDate, COLLECTOR_PROCESSING_POLLING, COLLECTOR_PROCESSING_POLLING_TIMEOUT );
				
	}

	/** sleep */
	public void sleep( long delay ) {
		
		try { Thread.sleep( delay ); } catch( InterruptedException e ) { logger.error( e.getMessage(), e ); }	
		
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

}
