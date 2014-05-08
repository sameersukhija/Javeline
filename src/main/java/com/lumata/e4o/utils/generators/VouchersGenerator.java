package com.lumata.e4o.utils.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;

public class VouchersGenerator {

	private static final Logger logger = LoggerFactory.getLogger( VouchersGenerator.class );
		
	private final int DEFAULT_START_INDEX = 1;
	private final int DEFAULT_END_INDEX = 100;
	
	private int entries;
	
	public VouchersGenerator() {}
	
	public int getEntries() {
		
		return this.entries;
		
	}
	
	public void setEntries( int entries ) {
		
		this.entries = entries;
		
	}
	
	public void generate( String folder, String voucherFileName, String voucherCodePrefix, IOFileUtils.IOLoadingType savingType ) {
	
		this.generate( folder, voucherFileName, voucherCodePrefix, DEFAULT_START_INDEX, DEFAULT_END_INDEX, savingType );
		
	}	
	
	public void generate( String folder, String voucherFileName, String voucherCodePrefix, int start_index, int end_index, IOFileUtils.IOLoadingType savingType ) {
    	
		try {
		
			logger.info( Log.PUTTING.createMessage( "Voucher csv file" ) );
						
			final int LISTS = 1;
			final String EXTENSION = ".csv";
			final String IMPORT_FILE = "import_" + voucherFileName + EXTENSION;
			//final String DELETE_FILE = "delete_" + voucherFileName + EXTENSION;
			
			StringBuffer output;
			
			int splitValue =  end_index / LISTS;			
						
			for( int l = 1; l <= LISTS; l++ ) {
				
				output = new StringBuffer();
								
				for( int i = ((l - 1) * splitValue ); i < ( l * splitValue ); i++ ) {
					
					String voucher_code = voucherCodePrefix + ( i + start_index );
					
					output.append( voucher_code ).append( "\n" );
						      
				}				
							
				IOFileUtils.saveResource( output, folder, IMPORT_FILE );			
				
			}
					
		} catch( Exception e ) {
			logger.info( e.getMessage(), e );
		}
	
	}
	
}
