package com.lumata.e4o.schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.exceptions.OfficeException;
import com.lumata.common.testing.io.ExcelUtils;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;
import com.lumata.expression.operators.dao.configuration.ConfigurationDAO;
import com.lumata.expression.operators.pojo.configuration.Configuration;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;
import com.lumata.testing.office.Excel;

public class TestDefaultConfTables {
	
	private static final Logger logger = LoggerFactory.getLogger( TestDefaultConfTables.class );
	
	//NetworkEnvironment env;
	Environment env;
	Mysql mysql;
	int user_id = 0;
	
	@Parameters({"environment", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws NetworkEnvironmentException, EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		//mysql = new Mysql( env.getDataSource( tenant ) );
			
		//NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
				
	}
	
	@Parameters({"tenant"})
	@Test( enabled = false )
	public void setCfg( @Optional("qa") String tenant ) throws IOException, OfficeException {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put( "excelFolder" , "input/configuration" );
		options.put( "excelFile" , "properties_default_fields" );
		options.put( "excelLoadingType" , IOFileUtils.IOLoadingType.RESOURCE );
		options.put( "excelSheetName" , "GLOBAL" );
		
		ConfigurationDAO allStandardParameters = new ConfigurationDAO( ConfigurationTypes.ALL_STANDARD_PARAMETERS_FROM_FILE, options );
		
		
		/*
		ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
		
		//final int CATEGORY = 0;
		final int SECTION = 1;
		final int NAME = 2;
		final int POSITION = 3;
		final int PROCESS_ID = 4;
		final int AUTH_GROUP = 5;
		final int CURRENT = 6;
		final int DYN_STATIC = 7;
		final int TYPE = 8;
		final int DESCRIPTION = 9;
		//final int CHAR = 10;
		//final int ON_LINE_HELP = 11;
		/*
		try {
			
			
			
			Workbook workbook = ExcelUtils.load( "input/configuration", "properties_default_fields.xls" );
			
			Sheet sheet = workbook.getSheet( "GLOBAL" );
			
			System.out.println( sheet.getLastRowNum() );
			
			//List<List<String>> sheet = ExcelUtils.loadSheet( workbook.getSheetAt( 0 ) );
			
			
			/*
			List<List<String>> sheet = ExcelUtils.loadSheet( workbook.getSheet( "GLOBAL" ) );
							
			String numeric_pattern = "([0-9]+)[.]{0,1}.[0-9]*";
							
			for( int i = 1; i < sheet.size(); i++ ) {
									
				Configuration cfg = new Configuration();
				cfg.setSection( sheet.get( i ).get( SECTION ).replaceAll( "\"", "\\\\\"" ) );
				cfg.setName( sheet.get( i ).get( NAME ).replaceAll( "\"", "\\\\\"" ) );
				cfg.setPosition( sheet.get( i ).get( POSITION ).replaceAll( numeric_pattern, "$1" ) );
				cfg.setProcessID( sheet.get( i ).get( PROCESS_ID ).replaceAll( "\"", "\\\\\"" ) );
				cfg.setAuthGroup( sheet.get( i ).get( AUTH_GROUP ).replaceAll( "\"", "\\\\\"" ) );
				String current = sheet.get( i ).get( CURRENT ).replaceAll( "\"", "\\\\\"" );
				cfg.setCurrent( ( Format.isNumeric( current ) ? current.replaceAll( numeric_pattern, "$1" ) : current ) );
				cfg.setPrevious( "NULL" );
				cfg.setDynStatic( sheet.get( i ).get( DYN_STATIC ) );
				cfg.setTime( "NULL" );
				cfg.setType( sheet.get( i ).get( TYPE ).replaceAll( "\"", "\\\\\"" ) );
				cfg.setDescription( sheet.get( i ).get( DESCRIPTION ).replaceAll( "\"", "\\\\\"" ) );
									
				cfgList.add( cfg );	
				
				this.printConfRow( cfg );
											
				if( i == 10 ) { break; }
				
			}
			
		} catch( IOFileException | IOException e ) {
			
			System.out.println( e.getMessage() );
			
		}	
		*/
		//ConfigurationDAO allStandardParameters = new ConfigurationDAO( ConfigurationTypes.ALL_STANDARD_PARAMETERS_FROM_FILE, null );
		
		//allStandardParameters.checkAll( mysql );
					
	}

	@Parameters({"tenant"})
	@Test( enabled = true )
	public void setDate( @Optional("qa") String tenant ) throws IOException, OfficeException {
		Calendar date = Calendar.getInstance();
		//ExpressionKernelCommands.setDatetime( env, date );
	}
	
	@AfterClass
	public void end() {		
		
		if( mysql != null ) { mysql.close(); }
				
	}
	
}
