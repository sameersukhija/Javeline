package com.lumata.expression.operators.testing.generate.catalogue;

import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.expression.operators.generator.catalogue.ProductTypeGenerator;

public class TestGenerateProductType {

	@Test
	public void createProductTypesList() {
		
		ProductTypeGenerator ptg = new ProductTypeGenerator();
		
		ptg.generate("output/catalog", "test", IOLoadingType.RESOURCE);
		
	}
	
}
