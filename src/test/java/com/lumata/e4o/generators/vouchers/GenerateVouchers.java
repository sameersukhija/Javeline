package com.lumata.e4o.generators.vouchers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.log.Log;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;


public class GenerateVouchers {

	private static final Logger logger = LoggerFactory.getLogger( GenerateVouchers.class );
	
	final boolean GENERATE_FIXED_VOUCHER = false;
	final boolean GENERATE_INCREMENTAL_VOUCHERS = false;
	final boolean GENERATE_RANDOM_VOUCHERS = true;
	
	@Test( enabled = GENERATE_FIXED_VOUCHER )
	public void generateFixedVoucherCSVFile() throws GeneratorException {
		
		final String VOUCHER_CODE_PREFIX = "voucher";
		final String VOUCHER_FOLDER = "/catalogmanager/vouchers";
		final String VOUCHER_FILE = "voucherList";
		final Long VOUCHERS_TO_GENERATE = 10L;		
		
		logger.info( Log.CREATING.createMessage( "voucher with fixed code" ) );
		
		Generator.vouchers()
					.voucherFixed( VOUCHER_CODE_PREFIX )
					.createCSVFile( VOUCHER_FOLDER, VOUCHER_FILE, VOUCHERS_TO_GENERATE );
				
	}

	@Test( enabled = GENERATE_INCREMENTAL_VOUCHERS )
	public void generateIncrementalVoucherCSVFile() throws GeneratorException {
		
		final String VOUCHER_CODE_PREFIX = "voucher";
		final String VOUCHER_FOLDER = "/catalogmanager/vouchers";
		final String VOUCHER_FILE = "voucherList";
		final Long VOUCHERS_TO_GENERATE = 100000L;
		final Integer startValue = 1;
		final Integer incrementalValue = 1;
		
		logger.info( Log.CREATING.createMessage( "voucher with incremental code" ) );
		
		Generator.vouchers()
					.voucherIncremental( VOUCHER_CODE_PREFIX, startValue, incrementalValue )
					.createCSVFile( VOUCHER_FOLDER, VOUCHER_FILE, VOUCHERS_TO_GENERATE );
				
	}

	@Test( enabled = GENERATE_RANDOM_VOUCHERS )
	public void generateRandomVoucherCSVFile() throws GeneratorException {
		
		final String VOUCHER_FOLDER = "/catalogmanager/vouchers";
		final String VOUCHER_FILE = "voucherList";
		final Long VOUCHERS_TO_GENERATE = 100L;		
		
		logger.info( Log.CREATING.createMessage( "voucher with random code" ) );
		
		Generator.vouchers()
					.voucherRandom( 8 )
					.createCSVFile( VOUCHER_FOLDER, VOUCHER_FILE, VOUCHERS_TO_GENERATE );
				
	}
	
}
