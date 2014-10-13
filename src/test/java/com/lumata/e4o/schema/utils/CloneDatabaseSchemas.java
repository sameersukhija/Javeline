package com.lumata.e4o.schema.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.generators.container.CampaignTypes;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.schema.tenant.Campaigns;
import com.lumata.e4o.schema.tenant.Token;

import static com.lumata.common.testing.orm.Query.*;

public class CloneDatabaseSchemas {
	
	private static final Logger logger = LoggerFactory.getLogger( CloneDatabaseSchemas.class );
	
	NetworkEnvironment env_origin;	
	NetworkEnvironment env_destination;	
	
	Mysql mysql_origin;
	Mysql mysql_destination;
	
	final List<Class<?>> tablesToClone = Arrays.asList ( 
		CampaignTypes.class,
		Campaigns.class
	);
	
	/* 	Initialize Environment */
	@Parameters({"env_orig", "env_dest", "tenant_orig", "tenant_dest"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String env_orig, @Optional("E4O_VM") String env_dest, @Optional("tenant") String tenant_orig, @Optional("tenant") String tenant_dest ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );

		env_orig = "E4O_O2_STG_NE";
		
		env_dest = "E4O_QA2_NE";
		
		tenant_orig = "tenant";
		
		tenant_dest = "tenant";
				
		env_origin = new NetworkEnvironment( "input/environments", env_orig, IOFileUtils.IOLoadingType.RESOURCE );
		
		env_destination = new NetworkEnvironment( "input/environments", env_dest, IOFileUtils.IOLoadingType.RESOURCE );
					
		mysql_origin = new Mysql( env_origin.getDataSource( tenant_orig ) );
		
		mysql_destination = new Mysql( env_destination.getDataSource( tenant_dest ) );
	
	}

	@Test( enabled = true )
	public void getSchemasDiff() throws SQLException, IOFileException {
		
		ArrayList<String> tablesSchemaOrigin = MysqlUtils.getSchema( mysql_origin );

		ArrayList<String> tablesSchemaDestination = MysqlUtils.getSchema( mysql_destination );
		
		if( !Arrays.equals( tablesSchemaOrigin.toArray(), tablesSchemaDestination.toArray() ) ) {
			
			if( tablesSchemaOrigin.size() > tablesSchemaDestination.size() ) {
				
				Set<String> tablesSchemaOriginSet = new HashSet<String>( tablesSchemaOrigin );
				
				Set<String> tablesSchemaDestinationSet = new HashSet<String>( tablesSchemaDestination );

				tablesSchemaOriginSet.removeAll( tablesSchemaDestinationSet );
				
				System.out.println( tablesSchemaOriginSet.toString() );
								
			}
			
//			Set<String> tablesSchemaOriginSet = new HashSet<String>( tablesSchemaOrigin );
//			
//			Set<String> tablesSchemaDestinationSet = new HashSet<String>( tablesSchemaDestination );
//			
//			
//			Set<String> commonOnes = biggerSet.retainAll(smallerSet);
//			
//			biggerSet.removeAll(commonOnes).add(smallerSet.removeAll(commonOnes))
//			
		}

		
	}
	
	@Test( enabled = false )
	public void cloneSchemas() throws SQLException, IOFileException {
		
		ArrayList<String> tablesSchema = MysqlUtils.getSchema( mysql_origin );
		
		
		/*
		for( Class<?> tableToClone : tablesToClone ) {
		
			try {
				
				Class<?> table = Class.forName( tableToClone.getName() );
				
				Constructor<?> tableConstructor = getPojoConstructor( table, null );
				
				try {
					
					String query = select().from( tableConstructor.newInstance() ).build();
					
					System.out.println( query );
					
					ResultSet rs = mysql_origin.execQuery( query );
						
					tableConstructor = getPojoConstructor( table, new Class<?>[]{ ResultSet.class } );
					
					while( rs.next() ) {
						
						query = insert( tableConstructor.newInstance( rs ) ).values().build();
											
						System.out.println( query );
												
					}
									
				} catch ( 
							InstantiationException | 
							IllegalAccessException | 
							IllegalArgumentException | 
							InvocationTargetException e
				) {
					
					logger.error( e.getMessage(), e );	
					
				}
					
			} catch (ClassNotFoundException e) {
				
				logger.error( e.getMessage(), e );
				
			}
		}
		*/
	}

	public Constructor<?> getPojoConstructor( Class<?> pojo, Class<?>[] arguments ) {
		
		Constructor<?>[] pojoConstructors = pojo.getDeclaredConstructors();
		
		if( null != pojoConstructors && pojoConstructors.length > 0 ) {
			
			for( Constructor<?> pojoConstructor : pojoConstructors ) {
				
				if( null == arguments || arguments.length == 0 ) {
					
					if( pojoConstructor.getParameterTypes().length == 0 ) { return pojoConstructor; }
					
				} else {
					
					if( Arrays.equals( arguments, pojoConstructor.getParameterTypes() ) ) { return pojoConstructor; }
												
				}

			}
			
		}
		
		return null;
		
	}
	
	@AfterSuite
	public void end() {
		
		mysql_origin.close();
		
		mysql_destination.close();
		
	}
	
}
