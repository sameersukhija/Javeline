package com.lumata.expression.operators.generators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;

import static com.lumata.common.testing.orm.Query.select;
import com.lumata.expression.operators.entities.Subscribers;

public class CDRMSISDNGenerator {

	private static final Logger logger = LoggerFactory.getLogger( CDRMSISDNGenerator.class );
	
	private static String date_format = "yyyy-MM-dd";
	//private static String date_format = "yyyy-MM-dd HH:mm:ss";
	
	public static void generate( Mysql mysql, String folder, String cdrFileName, IOFileUtils.IOLoadingType savingType ) throws IOFileException {
		
		StringBuilder output = new StringBuilder();
		
		final SimpleDateFormat dt = new SimpleDateFormat( date_format );
		final Calendar date = Calendar.getInstance();
		final Calendar original_validity_date = Calendar.getInstance();
		//final Calendar original_deactivation_date = Calendar.getInstance();
		
		final String EXTENSION = ".csv";
		final String IMPORT_FILE = "import_" + cdrFileName + EXTENSION;
		
		final int MIN_AMOUNT = 10;
		final int MAX_AMOUNT = 100;
				
		final int MIN_BALANCE = 1;
		final int MAX_BALANCE = 1000;
		
		final int MIN_DELAY = 100;
		final int MAX_DELAY = 1000;
		
		final ArrayList<String> TYPES = new ArrayList<String>( Arrays.asList( "reload", "paiement", "invoice" ));
				
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				String msisdn = rs.getString( Subscribers.Fields.msisdn.name() );
				
				final int random_amount = MIN_AMOUNT + (int)( Math.random() * ( MAX_AMOUNT - MIN_AMOUNT ) ) ; 
				final int random_balance = MIN_BALANCE + (int)( Math.random() * ( MAX_BALANCE - MIN_BALANCE ) ) ;
				final int random_delay = MIN_DELAY + (int)( Math.random() * ( MAX_DELAY - MIN_DELAY ) ) ;
								
				final Calendar random_validity_date = Calendar.getInstance();
				random_validity_date.set( ( original_validity_date.get( Calendar.YEAR ) + ( 1 + (int)( Math.random() * 3 ) ) ), ( original_validity_date.get( Calendar.MONTH ) + ( 1 + (int)( Math.random() * 12 ) ) ), ( original_validity_date.get( Calendar.DATE ) + ( 1 + (int)( Math.random() * 12 ) ) ) );
				
				final Calendar random_deactivation_date = Calendar.getInstance();
				random_deactivation_date.set( ( random_validity_date.get( Calendar.YEAR ) + ( 1 + (int)( Math.random() * 3 ) ) ), ( random_validity_date.get( Calendar.MONTH ) + ( 1 + (int)( Math.random() * 12 ) ) ), ( random_validity_date.get( Calendar.DATE ) + ( 1 + (int)( Math.random() * 12 ) ) ) );
				
				final String random_type = TYPES.get( ( (int)( Math.random() * ( TYPES.size() - 1 ) ) ) );
				
						/* msisdn */
				output.append( msisdn ).append( "|" )
						/* date */
						.append( dt.format( date.getTime() ) ).append( "|" )
						/* amount */
						.append( random_amount ).append( "|" )
						/* balance */
						.append( random_balance ).append( "|" )
						/* validity_date */
						.append( dt.format( random_validity_date.getTime() ) ).append( "|" )
						/* deactivation_date */
						.append( dt.format( random_deactivation_date.getTime() ) ).append( "|" )
						/* type */
						.append( random_type ).append( "|" )
						/* delay */
						.append( random_delay ).append( "\n" );
				
			}
			
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		IOFileUtils.saveResource( output.toString(), folder, IMPORT_FILE );
		
	}
	
}
