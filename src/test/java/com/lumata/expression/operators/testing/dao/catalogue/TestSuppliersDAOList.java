package com.lumata.expression.operators.testing.dao.catalogue;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.catalog.SuppliersDAO;
import com.lumata.expression.operators.dao.catalog.SuppliersDAOList;

public class TestSuppliersDAOList {

	Environment env;
	
	@Parameters({"environment"})
	@BeforeMethod
	public void init( @Optional("E4O_QA") String environment ) throws EnvironmentException {
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1 )
	public void loadSuppliers( @Optional("qa") String tenant ) {
		
		SuppliersDAOList suppliersList = new SuppliersDAOList( env, tenant, null, "input/catalog/suppliers", "suppliers.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertEquals( suppliersList.size(), 3 );
		
		suppliersList = new SuppliersDAOList( env, tenant, new ArrayList<Object>( Arrays.asList( 1 ) ) , "input/catalog/suppliers", "suppliers.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertEquals( suppliersList.size(), 0 );
		
		suppliersList = new SuppliersDAOList( env, tenant, new ArrayList<Object>( Arrays.asList( 8, 9 ) ) , "input/catalog/suppliers", "suppliers.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertEquals( suppliersList.size(), 2 );
		
		suppliersList = new SuppliersDAOList( env, tenant, null , "input/catalog/suppliers", "suppliers.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertEquals( suppliersList.size(), 3 );		
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 2, enabled = false )
	public void deleteSuppliers( @Optional("qa") String tenant ) {
		
		SuppliersDAOList suppliersList = new SuppliersDAOList( env, tenant, null );
		
		suppliersList.delete( env, tenant, null, new SuppliersDAO( -1, "mobistar", "", "", "") );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 2, enabled = false )
	public void deleteAllSuppliers( @Optional("qa") String tenant ) {
		
		SuppliersDAOList suppliersList = new SuppliersDAOList( env, tenant, null );
		
		suppliersList.deleteAll( env, tenant, null );
		
	}
	
}
