package com.lumata.expression.operators.testing.generators;

import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.expression.operators.generators.VouchersGenerator;

public class GenerateVouchers {

	@Test
	public void createProductTypesList() {
		
		VouchersGenerator vouchersGenerator = new VouchersGenerator();
		
		vouchersGenerator.generate("input/catalogue/vouchers", "VOUCHERS_LIST_350000", "voucher", 1, 350000, IOLoadingType.RESOURCE);
		
	}
	
}
