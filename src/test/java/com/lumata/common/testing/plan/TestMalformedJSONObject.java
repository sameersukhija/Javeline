package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;

public class TestMalformedJSONObject {

	@Test
	public void loadMalformedJSONObject() throws JSONSException, IOFileException {
		
		Assert.assertNotNull( JSONUtils.loadJSONResource( "lumata-common-testing/examples", "product_types.json" ));
		
	}
	
}
