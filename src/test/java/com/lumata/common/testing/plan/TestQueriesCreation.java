package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Val.*;
import com.lumata.common.testing.moke.Agencies;
import com.lumata.common.testing.moke.BdrEvents;

public class TestQueriesCreation {

	private static final Logger logger = LoggerFactory.getLogger( TestQueriesCreation.class );
	
	@Test( enabled = false, priority = 1 )
	public void selectSimpleQuery() {
		
		Agencies agencies = new Agencies();
		
		String simpleQuery = select().from( agencies ).build();
		
		System.out.println( simpleQuery );
		
	}
	
	@Test( enabled = false, priority = 1 )
	public void selectQuery() {
		
		Agencies agencies = new Agencies();
		agencies.setId( (short)1 );
		agencies.setName( "pippo" );
		
		BdrEvents bdr_events = new BdrEvents();
		
		
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
			
	}
		
	@Test( enabled = false, priority = 2 )
	public void selectQuerySpecialValues() {
		
		Agencies agencies = new Agencies();
		
		String query = select().from( agencies ).where( op( Agencies.Fields.name ).is( NULL ) ).build();
		
		System.out.println( query );
		
		query = select().from( agencies ).where( op( Agencies.Fields.name ).is_not( NULL ) ).build();
		
		System.out.println( query );
		
	}
	
	@Test( enabled = true, priority = 3 )
	public void insertQuery() {

		
		Agencies agencies = new Agencies();
				
		//String querySimpleInsert = insert( agencies, Agencies.Fields.name ).values( 1, 2, 3 ).build();
		
		//System.out.println( querySimpleInsert );
		
		String querySimpleInsert = insert( agencies ).values( 1, 2, 3 ).build();
		
		System.out.println( querySimpleInsert );
		
		
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
