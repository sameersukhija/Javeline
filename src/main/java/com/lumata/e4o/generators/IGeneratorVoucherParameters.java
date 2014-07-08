package com.lumata.e4o.generators;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.exceptions.GeneratorException;

public interface IGeneratorVoucherParameters {

	public IGeneratorVoucherParameters environment( final NetworkEnvironment env );
	
	public IGeneratorVoucherParameters mysql( final Mysql mysql );
	
	public IGeneratorVoucherParameters voucherFixed( final String voucherCode );
	
	public IGeneratorVoucherParameters voucherIncremental( final String voucherPrefix, final Integer startValue, final Integer increment );
	
	public IGeneratorVoucherParameters voucherRandom( final Integer voucherLength );
	
	public IGeneratorVoucherParameters voucherLength( final Integer voucherLength );
	
	public IGeneratorVoucherParameters voucherLists( final Long voucherLists );
	
	public void createCSVFile( String folderName, String fileName, Long qtyVouchers ) throws GeneratorException;
	
}
