package com.lumata.expression.operators.generators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.validating.Format;

public class VouchersGenerator {

	private static final Logger logger = LoggerFactory.getLogger( VouchersGenerator.class );
	
	
	private int entries;
	
	public VouchersGenerator() {}
	
	public int getEntries() {
		
		return this.entries;
		
	}
	
	public void setEntries( int entries ) {
		
		this.entries = entries;
		
	}
	
	public void generate( String folder, String voucherFileName, String voucherCodePrefix, IOFileUtils.IOLoadingType savingType ) {
    	
		try {
		
			logger.info( Log.PUTTING.createMessage( "Voucher csv file" ) );
						
			final int ENTRIES = 50;
			final int START_INDEX = 51;
			final int LISTS = 1;
			final String EXTENSION = ".csv";
			final String IMPORT_FILE = "import_" + voucherFileName + EXTENSION;
			//final String DELETE_FILE = "delete_" + PRODUCT_TYPES_LIST;
			
			StringBuffer output;
			
			int splitValue =  ENTRIES / LISTS;			
						
			for( int l = 1; l <= LISTS; l++ ) {
				
				output = new StringBuffer();
				//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				//Calendar now = Calendar.getInstance();
				//Calendar random_future_date = Calendar.getInstance();
				
				/*
				for( FIELDS field : FIELDS.values() ) {
					output.append( field ).append(";");
				}
				*/
				//output.append( "\n" );
				
				for( int i = ((l - 1) * splitValue ) + 1; i <= ( l * splitValue ); i++ ) {
					
					String voucher_code = voucherCodePrefix + ( i + START_INDEX );
					
					output.append( voucher_code ).append( "\n" );
						      
				}				
				
				logger.info( output.toString() );
				
				IOFileUtils.saveResource( output, folder, IMPORT_FILE );
				
				
			}
					
		} catch( Exception e ) {
			System.out.println( e.getMessage());
		}
	
	}
	
}
