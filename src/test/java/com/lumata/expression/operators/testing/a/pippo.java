package com.lumata.expression.operators.testing.a;

import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o_tenant.schema.FilesData;
import com.lumata.e4o_tenant.schema.FilesMeta;
import com.lumata.expression.operators.exceptions.CDR;
import com.lumata.expression.operators.exceptions.CDRException;
import com.lumata.expression.operators.system.cdr.CDRClassGenerator;
import com.lumata.expression.operators.system.cdr.annotations.Msisdn;
import com.lumata.expression.operators.system.cdr.types.CDRHistory;

public class pippo {

	private static final Logger logger = LoggerFactory.getLogger( pippo.class );
	
	Map<String, String> m1 = new HashMap<String, String>();
	Map<String, String> m2 = new HashMap<String, String>();
	
	public enum pippos { A, B; }
	
	pippo() {}
	
	@Test( enabled = true )
	public void create_cdr_subclasses() throws IOFileException {
		
		p( pippo.pippos.values() );

	}
	
	public void p( Enum<?>... e ) {
		
		for( int i = 0; i < e.length; i++ ) {
			System.out.println( e[i].name() );
		}		
		
	}
	
	@Test( enabled = false )
	public void test_replace() throws IOFileException, CDRException {
		
		CDRHistory c = new CDRHistory();
		
		System.out.println( "MSISDN Fixed Strategy" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN options setting" );
		c.setMsisdnOptions( 39, 19 );
		
		System.out.println( "MSISDN Fixed Strategy with options" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy with options" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy with options" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
				
	}
		
	
	@Parameters({"environment", "tenant" })
	@Test( enabled = false )
	public void test_insert( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws EnvironmentException, SQLException, IOFileException  {
		
		Environment env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		// check if notif.xml entry is present in the files_meta table
		FilesMeta fm = new FilesMeta();
		
		String query = select().from( fm ).where( op( FilesMeta.Fields.name ).eq( "notif.xml" ) ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) { fm = new FilesMeta( rs ); }
		
		// add notif.xml entry if not exist
		if( fm.getId() == null ) {
		
			fm.setName( "notif.xml" );
			fm.setPath( "./data/notif/" );
			fm.setUpdatetime( Calendar.getInstance().getTimeInMillis() );
			
			query = insert( fm ).values().build();
			
			System.out.println( query );
			
			fm.setId( mysql.execUpdate( query ) );
			
		}
		
		// check if notif.xml entry is present in the files_data table
		FilesData fd = new FilesData();
		
		query = select().from( fd ).where( op( FilesData.Fields.id ).eq( fm.getId() ) ).build();
		
		rs = mysql.execQuery( query );
		
		while( rs.next() ) { fd = new FilesData( rs ); }
		
		String notifXML = IOFileUtils.loadResourceAsString( "input/configuration", "notif.xml" );
		
		fd.setContent( notifXML );
		
		// add notif.xml entry if not exist
		//if( fd.getId() == null ) {
				
			fd.setId( fm.getId() );
						
			query = insert_delayed( fd ).values().build();
			System.out.println( query );			
			//mysql.execUpdate( query );
			
			logger.info( Log.PUTTING.createMessage( "notif.xml entered in files_data table" ) );
		/*			
		} else {
			
			query = update( fd ).set( op( FilesData.Fields.content ).eq( fd.getContent() ) ).where( op( FilesData.Fields.id ).eq( fd.getId() ) ).build();
			System.out.println( query );
			//mysql.execUpdate( query );
			
			logger.info( Log.PUTTING.createMessage( "notif.xml updated in files_data table" ) );
			
		}
		*/		
	}
	
	@Test( enabled = false )
	public void test1() throws InterruptedException {
		System.out.println( "test" );
		Date d1 = new Date();
				
		Thread.sleep( 1000 ); 
		
		Date d2 = new Date();
		
		long diff = d2.getTime() - d1.getTime(); 
		
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff); 
		
		System.out.println( diff );
		
		System.out.println( seconds );
		
		System.out.println( minutes );
		
		/*
		pippo.print( pippo.m2 );
		
		pippo.m2.putAll( pippo.m1 );
		
		pippo.print( pippo.m2 );
		
		pippo.m1.put( "key2", "val2" );
		
		pippo.m2.putAll( pippo.m1 );
		
		pippo.print( pippo.m2 );
		*/
	}
	
	@Test( enabled = false )
	public void test2() throws InterruptedException {
		
		for( int i = 1; i <= 255; i++ ) {
			
			if( 
				( i >= 33 && i <= 47 ) ||
				( i >= 58 && i <= 64 ) ||
				( i >= 91 && i <= 96 ) ||
				( i >= 123 && i <= 126 ) ||
				i >= 161					
			) {
			
				String s = Character.toString ((char) i );
				System.out.println( s );
			
			}			
		
		}		
		
	}
	
	public void print( Map<String, String> m ) {
		
		System.out.println( m.toString() );
		
	}
	
	@Test( enabled = false )
	public void isFile() throws IOException, IOFileException, EnvironmentException, JSONException {
		
		Environment env = new Environment("input/environments", "E4O_QA2", IOFileUtils.IOLoadingType.RESOURCE);
		
		JSONObject profile = env.getBrowser( "FIREFOX" ).getJSONObject( "profile" ).getJSONObject( "file" );
			
		System.out.println( profile.toString() );
		
		System.out.println( System.getProperty( "user.dir" ) + "/src/main/resources/" + IOFileUtils.buildPath( profile.getString("folder_name"), profile.getString("file_name") ) );
		
		//File f = IOFileUtils.loadResourceAsFile( "input/browsers/profiles", "firefox.default" );
		//Assert.assertNotNull( f );
		//FirefoxProfile fp = new FirefoxProfile(f);
	
	}
	
}
