package com.lumata.expression.operators.testing.generators;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.expression.operators.generators.CDRVouchersGenerator;
import com.lumata.expression.operators.generators.VouchersGenerator;

public class GenerateCDRVouchers {

	@Test
	public void createProductTypesList() throws IOFileException {
		
		CDRVouchersGenerator.generate("input/catalogue/vouchers", "CDR_VOUCHERS_LIST", "voucher", 1, 3, IOLoadingType.RESOURCE);
		
	}
	
}
