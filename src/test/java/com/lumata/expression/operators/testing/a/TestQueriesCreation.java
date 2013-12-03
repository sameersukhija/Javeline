package com.lumata.expression.operators.testing.a;

import java.util.Arrays;
import java.util.Date;

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
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Query.*;
import com.lumata.common.testing.orm.IQueryTemplate;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.entities.Agencies;
import com.lumata.expression.operators.entities.BdrEvents;
//import com.lumata.expression.operators.testing.pojo.autogenerator.Agencies;
//import com.lumata.expression.operators.testing.pojo.autogenerator.BdrEvents;

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
	
	@Test( enabled = false, priority = 1 )
	public void selectQuery() {
		
		//Agencies agencies = new Agencies();
		//BdrEvents bdr_events = new BdrEvents();
		//agencies.setId( (short)1 );
		//agencies.setName( "pippo" );
		
		/*
		//String query = Query.select().from().build( agencies );
		
		//System.out.println( query );
		
		/*
		String complexQuery = Query.select( Agencies.Fields.id, Agencies.Fields.name )
									.from( agencies )
									.join( bdr_events )									
									.where( op(Agencies.Fields.name.name()).eq( "1" ) )									
									/*.where( op(Agencies.Fields.name.name()).eq( "1" ),
											and(op(Agencies.Fields.name.name()).eq( "1" )),
											or(op(Agencies.Fields.name.name()).eq( "1" ))
									)*/
		/*							.order( Agencies.Fields.id.name(), Agencies.Fields.name.name() )
									.limit( 1, 2 )
									.build();
		*/
		
		
		/*
		System.out.println( 
				select().
				from( agencies ).
				where( 
						op( Agencies.Fields.name ).eq(), 
						and( op( Agencies.Fields.name ).eq() )
				)
				.build() );
		*/
		/*
		long elapsed_time = 0;
		
		for( int i = 0; i < 10; i++ ) {
		
			Date d1 = new Date();
		*/	
			
		/*
		String complexQuery = select( 	max(Agencies.Fields.id),
											min(Agencies.Fields.id),
											avg(Agencies.Fields.id),
											all(Agencies.Fields.id),
											count(),
											//count(Agencies.Fields.id),
											ucase(Agencies.Fields.id),
											lcase(Agencies.Fields.id),
											round(Agencies.Fields.id, 1),
											now(),
											mid(Agencies.Fields.id, 3 ),
											//mid(Agencies.Fields.id, 1, 2),
											//sum( Agencies.Fields.id ),
											sum( " - ( Agencies.Fields.id * 20 ) + BdrEvents.Fields.msisdn " ),
											distinct(Agencies.Fields.id),
											concat(Agencies.Fields.id, "pippo1", 3),
											Agencies.Fields.id, 
											Agencies.Fields.name 
									)
								   .from( agencies )
								   .join( bdr_events )
								   .on( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
									    and( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
												 op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )	
											),
										and( and( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
												 op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )
											 ),
											 and( 
												or( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
													op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )
												) 
											)
										),
										or( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
												 op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )	
										)   
								   )
								   .join( agencies )
								   .on(	op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ))
								   .where( 	op( Agencies.Fields.id ).eq(1),
										   	and( op( Agencies.Fields.name ).in( 1, "pluto", "paperino" ) ),
											and( op( Agencies.Fields.id ).eq(),
												 op( Agencies.Fields.id ).lt(),
												 op( Agencies.Fields.id ).let(),
												 op( Agencies.Fields.id ).gt(),
												 op( Agencies.Fields.name ).get()
											),
											and( and( op( BdrEvents.Fields.msisdn ).eq( "331234561" ),
													 op( Agencies.Fields.id ).eq(1)
												 ),
												 and( 
													or( op( Agencies.Fields.id ).eq(1),
														op( Agencies.Fields.id ).eq(1)
													) 
												)
											),
											or( op( Agencies.Fields.id ).eq(1),
													 op( Agencies.Fields.id ).eq(1)	
											)
									)
									.groupBy( Agencies.Fields.id )
									.having(op( Agencies.Fields.phone ).eq())									
									.orderBy( Agencies.Fields.id )
									.limit( 1, 2 )
									.build();
		
			System.out.println( complexQuery );
			*/
			/*	
			Date d2 = new Date();
			
			elapsed_time = elapsed_time + ( d2.getTime() - d1.getTime() );			
			
			System.out.println( elapsed_time );
			
		}	
				
		System.out.println( complexQuery );
		
		
		/*		
		//.where( and( op( Agencies.Fields.id ).eq(1) ) )		
		/*
		IQueryTemplate queryTpl = Query.select().from().template();
		
		agencies.setName( "pluto" );
		
		System.out.println( queryTpl.build( agencies ) );
		
		BdrEvents bdrEvents = new BdrEvents();
		
		System.out.println( queryTpl.build( bdrEvents ) );
		*/
		
	}
			
	@Test( enabled = true, priority = 2 )
	public void insertQuery() {

		/*
		Agencies agencies = new Agencies();
				
		String querySimpleInsert = insert( agencies, Agencies.Fields.name ).values().build();
		
		System.out.println( querySimpleInsert );
		*/
		
		/*
		Agencies agencies = new Agencies();
		//agencies.setId( new Short( "1" ) );
		agencies.setName( "pluto" );
		agencies.setAddress( "address" );
		agencies.setPhone( "331234561" );
		
		String query = Query.insert().values().build();
		
		System.out.println( query );
		
		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		//mysql.execUpdate( query );
		
		String complexQuery = Query.insert(Arrays.asList(Agencies.Fields.name.name()))
									.values()
									.build();

		System.out.println( complexQuery );
	*/	 		
	}
	
	@Test( enabled = false, priority = 3 )
	public void update() {
/*
		Agencies agencies = new Agencies();
		agencies.setId( new Short( "1" ) );
		agencies.setName( "pippo" );
		agencies.setAddress( "address" );
		agencies.setPhone( "331234561" );
		
		String query = Query.update().set().build();
				
		System.out.println( query );
		
		String complexQuery = Query.update(Arrays.asList(Agencies.Fields.name.name()))
				.set()
				.where(Arrays.asList(Agencies.Fields.name.name()))
				//.order(Arrays.asList(Agencies.Fields.name.name()))
				.build();

		System.out.println( complexQuery );
*/
	}	
	
	@Test( enabled = false, priority = 4 )
	public void delete() {
/*
		Agencies agencies = new Agencies();
		agencies.setId( new Short( "1" ) );
		agencies.setName( "pippo" );
		agencies.setAddress( "address" );
		agencies.setPhone( "331234561" );
		
		String query = Query.delete().from().build();
				
		System.out.println( query );

		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		//mysql.execUpdate( query );
		
		String complexQuery = Query.delete()
				.from()
				//.where(Arrays.asList(Agencies.Fields.name.name()))
				//.order(Arrays.asList(Agencies.Fields.name.name()))
				.build();

		System.out.println( complexQuery );
*/		
	}
	
}
