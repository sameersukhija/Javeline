package com.lumata.expression.operators.generators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		SimpleDateFormat dt = new SimpleDateFormat( date_format );
		Date date = new Date(); 
		
		final String EXTENSION = ".csv";
		final String IMPORT_FILE = "import_" + cdrFileName + EXTENSION;
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				String msisdn = rs.getString( Subscribers.Fields.msisdn.name() );
				
				output.append( msisdn ).append( "|" )
						.append( dt.format( date ) ).append( "\n" );
				
			}
			
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		IOFileUtils.saveResource( output.toString(), folder, IMPORT_FILE );
		
	}	
	
}
