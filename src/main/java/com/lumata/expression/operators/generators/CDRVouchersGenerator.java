package com.lumata.expression.operators.generators;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;

public class CDRVouchersGenerator {

	private static String msisdn = "331234561";
	private static String voucher_prefix = "voucher";
	private static String date_format = "yyyy-MM-dd HH:mm:ss";
	private static String[] locations = { "Milan", "Grenoble", "Palermo" };
	
	public static void generate( String folder, String voucherFileName, String voucherCodePrefix, int start_index, int end_index, IOFileUtils.IOLoadingType savingType ) throws IOFileException {
		
		StringBuilder output = new StringBuilder();
		
		final String EXTENSION = ".csv";
		final String IMPORT_FILE = "import_" + voucherFileName + EXTENSION;
		
		for( int i = start_index; i <= end_index; i++ ) {
			
			SimpleDateFormat dt = new SimpleDateFormat( date_format ); 
			Date date = new Date(); 
			int index = (int)(Math.random() * locations.length );
			
			output.append( msisdn ).append( "|" )
					.append( voucherCodePrefix ).append( i ).append( "|" )
					.append( dt.format( date ) ).append( "|" )
					.append( locations[ index ] )
					.append( "\n" );				
			
		}
		
		IOFileUtils.saveResource( output.toString(), folder, IMPORT_FILE );
		
	}
	
	
	
	
}
