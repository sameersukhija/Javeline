package com.lumata.expression.operators.testing.generators;

import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.expression.operators.generators.ProductsGenerator;

public class GenerateProducts {

	@Test
	public void createProductTypesList() {
		
		ProductsGenerator ptg = new ProductsGenerator();
		
		ptg.generate("output/catalog", "PRODUCT_TYPES_LIST", IOLoadingType.RESOURCE);
		
	}
	
}
