package com.lumata.expression.operators.testplan.mobistar.functional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.entities.Groups;
import com.lumata.expression.operators.gui.security.Authorization;

import static com.lumata.common.testing.orm.Query.*;

public class pippo {
	
	Environment env;
	
	@Parameters({"browser", "environment", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );	
		
	}
	
	@Parameters({"tenant"})
	@Test
	public void test1( @Optional("qa") String tenant ) throws InterruptedException {
		
		Groups groups = new Groups();
		
		String query = select( Groups.Fields.id, Groups.Fields.name, Groups.Fields.properties ).from( groups ).build();
		
		System.out.println( query );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ));
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
			while( rs.next() ) {
				System.out.println( rs.getString( Groups.Fields.name.name() ) );
				System.out.println( rs.getString( Groups.Fields.properties.name() ) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
