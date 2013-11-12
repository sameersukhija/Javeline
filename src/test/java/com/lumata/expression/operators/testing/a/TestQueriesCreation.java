package com.lumata.expression.operators.testing.a;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.orm.IQueryTemplate;
import com.lumata.common.testing.orm.Query;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.testing.pojo.autogenerator.Agencies;
import com.lumata.expression.operators.testing.pojo.autogenerator.BdrEvents;

public class TestQueriesCreation {

	private static final Logger logger = LoggerFactory.getLogger( TestQueriesCreation.class );
	
	Environment env;	
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Test( enabled = true )
	public void select() {
		
		Agencies agencies = new Agencies();
		agencies.setName( "pippo" );
		
		String query = Query.select().from().build( agencies );
		
		System.out.println( query );
		
		String complexQuery = Query.select()
									.from()
									.where(Arrays.asList(Agencies.Fields.name.name()))
									.order(Arrays.asList(Agencies.Fields.name.name()))
									.build( agencies );
		
		System.out.println( complexQuery );
		
		IQueryTemplate queryTpl = Query.select().from().template();
		
		System.out.println( queryTpl.build( agencies ) );
		
		BdrEvents bdrEvents = new BdrEvents();
		
		System.out.println( queryTpl.build( bdrEvents ) );
	
	}
			
	@Test( enabled = true )
	public void insert() {

		Agencies agencies = new Agencies();
		//agencies.setId( new Short( "1" ) );
		agencies.setName( "pluto" );
		agencies.setAddress( "address" );
		agencies.setPhone( "331234561" );
		
		String query = Query.insert().values().build( agencies );
		
		System.out.println( query );
		
		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		mysql.execUpdate( query );
		
		String complexQuery = Query.insert(Arrays.asList(Agencies.Fields.name.name()))
									.values()
									.build( agencies );

		System.out.println( complexQuery );
		 		
	}
	
	@Test( enabled = true )
	public void update() {

		Agencies agencies = new Agencies();
		agencies.setId( new Short( "1" ) );
		agencies.setName( "pippo" );
		agencies.setAddress( "address" );
		agencies.setPhone( "331234561" );
		
		String query = Query.update().set().build( agencies );
				
		System.out.println( query );
		
		String complexQuery = Query.update(Arrays.asList(Agencies.Fields.name.name()))
				.set()
				.where(Arrays.asList(Agencies.Fields.name.name()))
				.order(Arrays.asList(Agencies.Fields.name.name()))
				.build( agencies );

		System.out.println( complexQuery );

	}	
	
	@Test( enabled = true )
	public void delete() {

		Agencies agencies = new Agencies();
		agencies.setId( new Short( "1" ) );
		agencies.setName( "pippo" );
		agencies.setAddress( "address" );
		agencies.setPhone( "331234561" );
		
		String query = Query.delete().from().build( agencies );
				
		System.out.println( query );

		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		mysql.execUpdate( query );
		
		String complexQuery = Query.delete()
				.from()
				.where(Arrays.asList(Agencies.Fields.name.name()))
				.order(Arrays.asList(Agencies.Fields.name.name()))
				.build( agencies );

		System.out.println( complexQuery );
		
	}
	
}
