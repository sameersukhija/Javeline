package com.lumata.e4o.utils.generators;

import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.e4o.utils.generators.VouchersGenerator;

public class GenerateVouchers {

	@Test
	public void createProductTypesList() {
		
		VouchersGenerator vouchersGenerator = new VouchersGenerator();
		
		vouchersGenerator.generate("input/catalogue/vouchers", "VOUCHERS_LIST_11_20", "voucher", 11, 20, IOLoadingType.RESOURCE);
		
	}
	
}
