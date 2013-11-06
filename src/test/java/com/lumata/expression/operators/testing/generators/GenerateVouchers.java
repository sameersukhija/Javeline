package com.lumata.expression.operators.testing.generators;

import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.expression.operators.generators.VouchersGenerator;

public class GenerateVouchers {

	@Test
	public void createProductTypesList() {
		
		VouchersGenerator vouchersGenerator = new VouchersGenerator();
		
		vouchersGenerator.generate("output/catalog", "VOUCHERS_LIST", "offer_voucher", IOLoadingType.RESOURCE);
		
	}
	
}
