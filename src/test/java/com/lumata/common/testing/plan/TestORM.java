package com.lumata.common.testing.plan;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.generators.container.Token;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Filter.DATE_UNIT.*;

public class TestORM {
	
	@Test
	public void validateSelect() throws NetworkEnvironmentException {		
		
		String query = select( count() ).from( new Token() ).build();
		
		System.out.println( query );
		
		query = select( date_add( Token.Fields.event_date, 3, DAY ) ).from( new Token() ).build();
		
		System.out.println( query );
		
		query = select( date_add( "2014-10-10", 3, DAY ) ).from( new Token() ).build();
		
		System.out.println( query );
		
		query = select( date_add( "2014-10-10", 3, DAY ) ).from( new Token() ).where( op( Token.Fields.event_date ).eq("2014-10-10") ).build();
		
		System.out.println( query );
		
		query = select( date_add( "2014-10-10", 3, DAY ) ).from( new Token() ).where( op( date_add( Token.Fields.event_date, 0, DAY ) ).like("2014-10-24") ).build();
		
		System.out.println( query );
		
		query = select( date_add( "2014-10-10", 3, DAY ) ).from( new Token() ).where( op( date_add( Token.Fields.event_date, 0, DAY ) ).eq( Token.Fields.event_date ) ).build();
		
		System.out.println( query );
		
		query = select( date_add( "2014-10-10", 3, DAY ) ).from( new Token() ).where( op( date_add( Token.Fields.event_date, 0, DAY ) ).eq( date_add( Token.Fields.event_date, 0, DAY ) ) ).build();
		
		System.out.println( query );
		
		query = select( date_add( "2014-10-10", 3, DAY ) ).from( new Token() ).where( op( Token.Fields.event_date ).eq( date_add( Token.Fields.event_date, 0, DAY ) ) ).build();
		
		System.out.println( query );
		
	}

}
