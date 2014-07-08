package com.lumata.e4o.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.GeneratorParameter.GeneratorParameterType;
import com.lumata.e4o.system.fields.FieldString;


public class VoucherGenerator implements IGeneratorVoucherParameters {

	private static final Logger logger = LoggerFactory.getLogger( VoucherGenerator.class );
	
	public enum VoucherFileType { csv }
	
	GeneratorParametersList parameters;
	
	/** Default VOUCHER LENGTH */
	Integer voucherLenght = 5;
	
	/** Default VOUCHER LISTS TO CREATE */
	Long voucherLists = 1L;
	
	/** Default VOUCHER FILE EXTENSION */
	VoucherFileType voucherFileType = VoucherFileType.csv;	
	
	/** Default VOUCHER PREFIX */
	String voucherPrefix = "";
	
	/** voucher field management */
	FieldString fieldVoucher;
	
	VoucherGenerator( GeneratorParametersList parameters ) {
		
		this.parameters = parameters;
		
		this.fieldVoucher = new FieldString();
	
	}
	
	public VoucherGenerator environment( final NetworkEnvironment env ) {
		
		parameters.add( GeneratorParameter.environment( env ) );
		
		return this;
		
	}
	
	public VoucherGenerator mysql( final Mysql mysql ) {
		
		parameters.add( GeneratorParameter.mysql( mysql ) );

		return this;
	
	}
	
	public VoucherGenerator voucherFixed( final String voucherCode ) {
	
		parameters.add( GeneratorParameterType.voucher_strategy, GeneratorParameter.voucherFixed( voucherCode ) );

		return this;
	
	}
	
	public VoucherGenerator voucherIncremental( final String voucherPrefix, final Integer startValue, final Integer increment ) {
		
		parameters.add( GeneratorParameterType.voucher_strategy, GeneratorParameter.voucherIncremental( voucherPrefix, startValue, increment ) );

		return this;
	
	}
	
	public VoucherGenerator voucherRandom( final Integer voucherLength ) {
		
		parameters.add( GeneratorParameterType.voucher_strategy, GeneratorParameter.voucherRandom( voucherLength ) );

		return this;
	
	}
	
	public VoucherGenerator voucherLength( final Integer voucherLength ) {
		
		parameters.add( GeneratorParameterType.voucher_options, GeneratorParameter.voucherLength( voucherLength ) );
		
		return this;
		
	}
	
	public VoucherGenerator voucherLists( final Long voucherLists ) {
		
		parameters.add( GeneratorParameterType.voucher_lists, GeneratorParameter.voucherLists( voucherLists ) );
		
		return this;
		
	}
	
	private void configureParameters() throws GeneratorException {
						
		String mandatoryFieldMissing = "the mandatory field ${fieldType} is missing";
		
		String mandatoryFieldNull = "the mandatory field ${fieldType} is null";
		
		/*
		if( !parameters.containsKey( GeneratorParameterType.environment ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.environment.name() ) ); }
		else if( null == parameters.getParameter( GeneratorParameterType.environment ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.environment.name() ) ); }
		
		if( !parameters.containsKey( GeneratorParameterType.mysql ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.mysql.name() ) ); }
		else if( null == parameters.getParameter( GeneratorParameterType.mysql ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.mysql.name() ) ); }
		*/
		if( !parameters.containsKey( GeneratorParameterType.voucher_strategy ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.voucher_strategy.name() ) + ".It needs to configure a parameter among " + GeneratorParameterType.voucher_fixed.name() + " or " + GeneratorParameterType.voucher_incremental.name()  + " or " + GeneratorParameterType.voucher_random.name() ); }
		else {
			if( null == parameters.getParameter( GeneratorParameterType.voucher_strategy ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.voucher_strategy.name() ) + ".It needs to configure a parameter among " + GeneratorParameterType.voucher_fixed.name() + " or " + GeneratorParameterType.voucher_incremental.name()  + " or " + GeneratorParameterType.voucher_random.name() ); }
			else {
				
				try {
					
					switch( parameters.getParameterType( GeneratorParameterType.voucher_strategy ) ) {
					
						case voucher_fixed: {
							
							fieldVoucher.setStringStrategyFixed( (String)parameters.getParameterValue( GeneratorParameterType.voucher_strategy ) );
							
							break;
							
						} 
						case voucher_incremental: {
							
							fieldVoucher.setStringStrategyIncrement(
									(String)parameters.getParameterValue( GeneratorParameterType.voucher_strategy ), 
									(Integer)parameters.getParameterLeftValue( GeneratorParameterType.voucher_strategy ), 
									(Integer)parameters.getParameterRightValue( GeneratorParameterType.voucher_strategy )									
							);	
							
							break;
							
						} 
						case voucher_random: {
							
							fieldVoucher.setStringStrategyRandom(
									(Integer)parameters.getParameterValue( GeneratorParameterType.voucher_strategy )								
							);
							
							break;
							
						} 
						default: { break; }
					}
				
				} catch( CDRException e ) {
					
					System.out.println( e.getMessage() );
					
					e.printStackTrace();
					
				}

			}
			
		}

		if( parameters.containsKey( GeneratorParameterType.voucher_options ) ) { 
						
			voucherLenght = (Integer)parameters.getParameter( GeneratorParameterType.voucher_options ).getGeneratorParameterValue();
		
		}
		
		if( parameters.containsKey( GeneratorParameterType.voucher_lists ) ) { 
			
			voucherLists = (Long)parameters.getParameter( GeneratorParameterType.voucher_lists ).getGeneratorParameterValue();
		
		}
		
	}
	
	public void createCSVFile( String folderName, String fileName, Long qtyVouchers ) throws GeneratorException {
		
		configureParameters();
		
		try {
			
						
			StringBuffer output;
			
			long splitValue =  qtyVouchers / voucherLists;			
						
			for( int l = 1; l <= voucherLists; l++ ) {
				
				output = new StringBuffer();
								
				for( long i = ((l - 1) * splitValue ); i < ( l * splitValue ); i++ ) {
					
					output.append( fieldVoucher.getString() ).append( "\n" );
						      
				}				
							
				output.trimToSize();
				
				logger.info( Log.SAVING.createMessage( "voucher file" ) );
				
				IOFileUtils.saveResource( output, folderName, fileName + "_" + l + "." + voucherFileType.name() );			
				
			}
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
			
	}
	
}
