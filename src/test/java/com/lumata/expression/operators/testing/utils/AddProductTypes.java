package com.lumata.expression.operators.testing.utils;

import java.sql.SQLException;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.catalog.SuppliersDAOList;

public class AddProductTypes {

	Environment env;
	Mysql mysql;
	
	@Parameters({"environment", "tenant"})
	@BeforeMethod
	public void init( @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1 )
	public void loadSuppliers( @Optional("qa") String tenant ) throws SQLException {
		
		//SuppliersDAOList suppliersList = new SuppliersDAOList( env, tenant, null, "input/catalog/suppliers", "suppliers.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		//Assert.assertEquals( suppliersList.size(), 3 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_product_types", mysql ), 0 );
				
	}
	
}
