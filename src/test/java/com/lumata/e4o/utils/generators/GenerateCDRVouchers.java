package com.lumata.e4o.utils.generators;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.expression.operators.generators.CDRVouchersGenerator;
import com.lumata.expression.operators.generators.VouchersGenerator;

@Deprecated /** depends from CDR deprecated class - to be refactoring using the new CDR class ( Vincenzo )*/
public class GenerateCDRVouchers {

	@Test
	public void createProductTypesList() throws IOFileException {
		
		CDRVouchersGenerator.generate("input/catalogue/vouchers", "CDR_VOUCHERS_LIST", "voucher", 1, 3, IOLoadingType.RESOURCE);
		
	}
	
}
