package com.lumata.e4o.system.environment;

import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.op;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.e4o_tenant.schema.CollectedFilesStats;

public class ExpressionSystem {

	private static final Logger logger = LoggerFactory.getLogger( ExpressionSystem.class );
	
	public static Boolean isCDRProcessingCompleted( Mysql mysql, String cdrType, Calendar beginningDate ) {
		
		logger.info( Log.CHECKING.createMessage( "for cdr type ( " + cdrType + " )" ) );
		
		CollectedFilesStats cfsTable = new CollectedFilesStats();
		cfsTable.setHandler( cdrType );
		
		String query = select().from( cfsTable ).where( op( CollectedFilesStats.Fields.handler ).like( cfsTable.getHandler() ) ).build();
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
			
			while( rs.next() ) {
				
				Timestamp processedDate = rs.getTimestamp( CollectedFilesStats.Fields.processed_date.name() );
				
				if( processedDate.after( beginningDate.getTime() ) ) { return true; }
				
			}
			
		} catch( SQLException e ) {
			logger.error( e.getMessage(), e );
		} 
				
		return false;
		
	}
	
	public static Boolean waitForCDRProcessingCompleted( Mysql mysql, ArrayList<String> cdrTypes, Calendar beginningDate, long polling, long timeout ) {
		
		Boolean collectorProcessingWaiting = true;
		
		logger.info( Log.CHECKING.createMessage( "cdrs elaboration in progress" ) );
		
		long expired_time = 0;
		
		/** check cdrs elaboration */
		while( collectorProcessingWaiting ) {
			
			try { Thread.sleep( polling ); } catch( InterruptedException e ) { logger.error( e.getMessage(), e ); }
			
			for( int cdrTypeIndex = 0; cdrTypeIndex < cdrTypes.size(); cdrTypeIndex++ ) {

				collectorProcessingWaiting = !ExpressionSystem.isCDRProcessingCompleted( mysql, cdrTypes.get( cdrTypeIndex ), beginningDate );
				
			}	
			
			expired_time = expired_time + polling;
			
			if( expired_time > timeout ) { return false; }
			
		}
		
		return collectorProcessingWaiting;
		
	}
	
	
	
	
}
