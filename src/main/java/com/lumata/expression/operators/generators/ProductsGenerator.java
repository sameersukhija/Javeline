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

public class ProductsGenerator {

	private static final Logger logger = LoggerFactory.getLogger( ProductsGenerator.class );
	
	private enum FIELDS { 
		
		NAME,
		EXTERNAL_ID,
		DESCRIPTION,
		SUPPLIER,
		START_DATE,
		END_DATE,
		STOCK,
		PRODUCT_TYPES,
		SPECIFIC_CHARACTERISTICS,
		UNITARY_COST,
		LIST_PRICE,
		RELATED_PRODUCTS,
		URL;
		
	}
	
	private int entries;
	private String loadingFolder;
	private String storingFolder;
	private String loadingFileName;
	private String storingFileName;
	
	public ProductsGenerator() {}
	
	public int getEntries() {
		
		return this.entries;
		
	}
	
	public void setEntries( int entries ) {
		
		this.entries = entries;
		
	}
	
	public void generate( String folder, String productTypesFileName, IOFileUtils.IOLoadingType savingType ) {
    	
		try {
		
			logger.info( Log.PUTTING.createMessage( "Product csv file" ) );
			
			//
			
			final int ENTRIES = 100;
			final int LISTS = 1;
			final String EXTENSION = ".csv";
			final String IMPORT_FILE = "import_" + productTypesFileName + EXTENSION;
			//final String DELETE_FILE = "delete_" + PRODUCT_TYPES_LIST;
			
			StringBuffer output;
			
			int splitValue =  ENTRIES / LISTS;			
			
			String[] suppliers = { "Mobistar", "Orange", "O2", "TIM" };
			String[] product_types = { "Physical", "Network", "Voucher" };
			
			for( int l = 1; l <= LISTS; l++ ) {
				
				output = new StringBuffer();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar now = Calendar.getInstance();
				Calendar random_future_date = Calendar.getInstance();
				
				for( FIELDS field : FIELDS.values() ) {
					output.append( field ).append(";");
				}
				
				output.append( "\n" );
				
				for( int i = ((l - 1) * splitValue ) + 1; i <= ( l * splitValue ); i++ ) {
					
					String product_type = product_types[(int)(Math.random() * product_types.length)];
					String name = product_type + " ( " + i + " )";
					String external_id = String.valueOf( i );
					String description = product_type + " description ( " + i + " )";
					String supplier = suppliers[(int)(Math.random() * suppliers.length)];
					String start_date = dateFormat.format(now.getTime());					
					random_future_date.add( Calendar.DAY_OF_YEAR, ((int)Math.round(Math.random() * 365 )));
					String end_date = dateFormat.format(random_future_date.getTime());
					String stock = String.valueOf( (int)Math.round(Math.random() * 200 ) );
					String specific_characteristic = product_type + " characteristic ( " + i + " )";
					String unitary_cost = String.valueOf( (int)Math.round(Math.random() * 100 ) );
					String list_price = String.valueOf( ( Integer.valueOf( unitary_cost ) * (int)Math.round(Math.random() * 10 ) ) );
					String related_products = "";
					String url = "http://www." + supplier.toLowerCase() + ".com";
					
					output.append( name ).append( ";" ).
								append( external_id ).append( ";" ).
								append( description ).append( ";" ).
								append( supplier ).append( ";" ).
								append( start_date ).append( ";" ).
								append( end_date ).append( ";" ).
								append( stock ).append( ";" ).
								append( product_type ).append( ";" ).
								append( specific_characteristic ).append( ";" ).
								append( unitary_cost ).append( ";" ).
								append( list_price ).append( ";" ).
								append( related_products ).append( ";" ).
								append( url ).append( ";" ).append( "\n" );
						      
				}				
				
				logger.info( output.toString() );
				
				IOFileUtils.saveResource( output, folder, IMPORT_FILE );
				
				
			}
					
		} catch( Exception e ) {
			System.out.println( e.getMessage());
		}
	
	}
	
}
