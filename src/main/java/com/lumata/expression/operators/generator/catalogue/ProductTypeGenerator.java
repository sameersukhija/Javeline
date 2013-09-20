package com.lumata.expression.operators.generator.catalogue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.validating.Format;

public class ProductTypeGenerator {

	private static final Logger logger = LoggerFactory.getLogger( ProductTypeGenerator.class );
	
	private enum FIELDS { 
		
		NAME,
		EXTERNAL_ID,
		DESCRIPTION,
		SUPPLIER,
		START_DATE,
		END_DATE,
		STOCK,
		PRODUCT_TYPES,
		SPECIFIC_CHARACTERISTICS,
		UNITARY_COST,
		LIST_PRICE,
		RELATED_PRODUCTS,
		URL;
		
	}
	
	private int entries;
	private String loadingFolder;
	private String storingFolder;
	private String loadingFileName;
	private String storingFileName;
	
	public ProductTypeGenerator() {}
	
	public int getEntries() {
		
		return this.entries;
		
	}
	
	public void setEntries( int entries ) {
		
		this.entries = entries;
		
	}
	
	/*
	public ProductTypeGenerator( String environment, IOFileUtils.IOLoadingType loadingType ) throws EnvironmentException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.envCfg = JSONUtils.loadJSONFile( environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.envCfg = JSONUtils.loadJSONResource( environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new EnvironmentException( "You cannot load an environment from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} 			
			
	}
	
	public Environment( String folder, String environment, IOFileUtils.IOLoadingType loadingType ) throws EnvironmentException {
		
		try {
			
			switch( loadingType ) {
			
			case FILE: { this.envCfg = JSONUtils.loadJSONFile( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
			case RESOURCE: { this.envCfg = JSONUtils.loadJSONResource( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
			default: throw new EnvironmentException( "You cannot load an environment from resources different by FILE or RESOURCE" );
		
		}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} 			
			
	}
	*/
	
	
	public void generate( String folder, String productTypesFileName, IOFileUtils.IOLoadingType savingType ) {
    	
		try {
		
			logger.info( Log.PUTTING.createMessage( "Product Type csv file" ) );
			
			//
			
			final int ENTRIES = 100;
			final int LISTS = 1;
			final String FOLDER = "/output/catalog/";
			final String PRODUCT_TYPES_LIST = "PRODUCT_TYPES_LIST_QA";
			final String EXTENSION = ".csv";
			final String IMPORT_FILE = "import_" + PRODUCT_TYPES_LIST + EXTENSION;
			//final String DELETE_FILE = "delete_" + PRODUCT_TYPES_LIST;
			
			StringBuffer output;
			
			int splitValue =  ENTRIES / LISTS;			
			
			String[] suppliers = { "Mobistar", "Orange", "O2", "TIM" };
			String[] product_types = { "Physical", "Network", "Voucher" };
			
			for( int l = 1; l <= LISTS; l++ ) {
				
				output = new StringBuffer();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar now = Calendar.getInstance();
				Calendar random_future_date = Calendar.getInstance();
				
				for( FIELDS field : FIELDS.values() ) {
					output.append( field ).append(";");
				}
				
				output.append( "\n" );
				
				for( int i = ((l - 1) * splitValue ) + 1; i <= ( l * splitValue ); i++ ) {
					
					String product_type = product_types[(int)(Math.random() * product_types.length)];
					String name = product_type + " ( " + i + " )";
					String external_id = String.valueOf( i );
					String description = product_type + " description ( " + i + " )";
					String supplier = suppliers[(int)(Math.random() * suppliers.length)];
					String start_date = dateFormat.format(now.getTime());					
					random_future_date.add( Calendar.DAY_OF_YEAR, ((int)Math.round(Math.random() * 365 )));
					String end_date = dateFormat.format(random_future_date.getTime());
					String stock = String.valueOf( (int)Math.round(Math.random() * 200 ) );
					String specific_characteristic = product_type + " characteristic ( " + i + " )";
					String unitary_cost = String.valueOf( (int)Math.round(Math.random() * 100 ) );
					String list_price = String.valueOf( ( Integer.valueOf( unitary_cost ) * (int)Math.round(Math.random() * 10 ) ) );
					String related_products = "";
					String url = "http://www." + supplier.toLowerCase() + ".com";
					
					output.append( name ).append( ";" ).
								append( external_id ).append( ";" ).
								append( description ).append( ";" ).
								append( supplier ).append( ";" ).
								append( start_date ).append( ";" ).
								append( end_date ).append( ";" ).
								append( stock ).append( ";" ).
								append( product_type ).append( ";" ).
								append( specific_characteristic ).append( ";" ).
								append( unitary_cost ).append( ";" ).
								append( list_price ).append( ";" ).
								append( related_products ).append( ";" ).
								append( url ).append( ";" ).append( "\n" );
						      
				}				
				
				logger.info( output.toString() );
				
				IOFileUtils.saveResource( output, FOLDER, IMPORT_FILE );
				
				/*
				System.out.println("Creating Import List ( " + l + " ) ...");
				String importCSVFile = FOLDER + IMPORT_FILE + "_" + l + EXTENSION;
				BufferedWriter writerImportList = new BufferedWriter(new FileWriter(importCSVFile));
				writerImportList.write(importLine.toString().trim());
				writerImportList.close();
				System.out.println("End Import List creation: " + importCSVFile);
				
				System.out.println("Creating Delete List ( " + l + " ) ...");
				String deleteCSVFile = FOLDER + DELETE_FILE + "_" + l + EXTENSION;			
				BufferedWriter writerDeleteList = new BufferedWriter(new FileWriter(deleteCSVFile));
				writerDeleteList.write(deleteLine.toString().trim());
				writerDeleteList.close();
				System.out.println("End Delete List creation: " + deleteCSVFile);
				*/
			}
			
			/*
			
			
			
			
			String[] mobileOsOpt = {"Android","iOS"};
			//String[] applicationNames = {"Marvel App","Stark App","Nestle","Auchan", "MercatoneUNO"};
			String[] applicationNames = {"Marvel App","Stark App"};
			String[] genders = {"M"};
			//String[] genders = {"M","F"};
			String[] titles = {"Mr","Mrs"};
			//String[] titles = {"Mr","Mrs","Miss","Ms"};
			String[] firstNames = {"Arcangelo","Andrea","Eros","Fabrizio","Alessandro","Marco","Alberto","Fabio","Tommaso","Mauro","Rafael", "Davide"};
			String[] lastNames = {"Di Pasquale","Dal Passo","Pedrini","Giovannetti","Bevilini","Zuffada","Colombo","Maffioletti","Begassi","Marchetto","Fiume", "Rognoni"};
			String[] towns = {"Milano","Londra","Madrid","Parigi","Palermo","Crema","Como","Berlino","Amsterdam", "Torino"};
			String[] countries = {"it","en","uk","de", "pl"};
			//String[] channels = {"sms","app","both"};
			String[] channels = {"sms"};
				
			StringBuffer importLine = new StringBuffer();
			StringBuffer deleteLine = new StringBuffer();
			String nl = System.getProperty("line.separator");
			
			int splitValue =  ENTRIES / LISTS;			
			
			for( int l = 1; l <= LISTS; l++ ) {
				
				importLine = new StringBuffer();
				deleteLine = new StringBuffer();				
				
				if( l == 1 && ADD_LUMATA_USERS ) {
					importLine.append(ManageLists.createLumataUsersToImport());
					deleteLine.append(ManageLists.createLumataUsersToDelete());
				}
				
				for( int i = ((l - 1) * splitValue ) + 1; i <= ( l * splitValue ); i++ ) {
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					
					String phoneNumber = new String();
					String registrationId = new String();
					String mobileOs = new String();
					String applicationName = new String();
					String subscriptionDate = dateFormat.format(cal.getTime());
					String preferredChannel = new String("1");
					String title = titles[(int)(Math.random() * titles.length)];
					int randomUser = (int)(Math.random() * firstNames.length);
					String firstName = firstNames[randomUser];
					String lastName = lastNames[randomUser];
					String addressLine1 = new String("addressLine1");
					String addressLine2 = new String("addressLine2");
					String town = towns[(int)(Math.random() * towns.length)];
					String postCode = RandomStringUtils.randomAlphanumeric(7);
					String country = countries[(int)(Math.random() * countries.length)];
					String randomYear = String.valueOf(1950 + (int)Math.round(Math.random() * (2013 - 1950)));
					String randomMonth = String.valueOf(1 + (int)Math.round(Math.random() * (12 - 1)));
					if( randomMonth.length() < 2 ) randomMonth = "0" + randomMonth;
					String randomDay = String.valueOf(1 + (int)Math.round(Math.random() * (28 - 1)));			
					if( randomDay.length() < 2 ) randomDay = "0" + randomDay;
					String dateOfBirth = randomYear + "-" + randomMonth + "-" + randomDay;
					//String gender = genders[(int)(Math.random() * genders.length)];
					String gender = genders[0];
					String email = firstName.replace(" ", "").toLowerCase() + "." + lastName.replace(" ", "").toLowerCase() + "@lumatagroup.com";
					String loyaltyId = String.valueOf((int)(Math.random() * 1000000));
					String topics = new String("topics");
					
					String channel = channels[(int)(Math.random() * channels.length)];
					
					switch( channel ) {
					
						case "sms": {
							for( int j = 1; j <= String.valueOf(ENTRIES).length() - String.valueOf(i).length(); j++ ) phoneNumber = phoneNumber + "0";
							phoneNumber = phoneNumber + String.valueOf(i);
							deleteLine.append(subscriptionDate + "|msisdn|" + phoneNumber + "\n");
							break;
						}
						case "app": {
							registrationId = RandomStringUtils.randomAlphanumeric(140);
							mobileOs = mobileOsOpt[(int)(Math.random() * mobileOsOpt.length)];
							applicationName = applicationNames[(int)(Math.random() * applicationNames.length)];
							deleteLine.append(subscriptionDate + "|regid|" + registrationId + "\n");
							break;
						}
						case "both": {
							for( int j = 1; j <= String.valueOf(ENTRIES).length() - String.valueOf(i).length(); j++ ) phoneNumber = phoneNumber + "0";
							phoneNumber = phoneNumber + String.valueOf(i);
							deleteLine.append(subscriptionDate + "|msisdn|" + phoneNumber + "\n");
							registrationId = RandomStringUtils.randomAlphanumeric(140);
							mobileOs = mobileOsOpt[(int)(Math.random() * mobileOsOpt.length)];
							applicationName = applicationNames[(int)(Math.random() * applicationNames.length)];
							break;
						}
					}
					
					importLine.append(subscriptionDate + "|" + phoneNumber + "|" + registrationId + "|" + mobileOs + "|" + applicationName + "|" + preferredChannel + "|" + title + "|" + firstName + "|" + lastName + "|" + addressLine1 + "|" + addressLine2 + "|" + town + "|" + postCode + "|" + country + "|" + dateOfBirth + "|" + gender + "|" + email + "|" + loyaltyId + "|" + topics + "\n");
								      
				}				
				
				System.out.println("Creating Import List ( " + l + " ) ...");
				String importCSVFile = FOLDER + IMPORT_FILE + "_" + l + EXTENSION;
				BufferedWriter writerImportList = new BufferedWriter(new FileWriter(importCSVFile));
				writerImportList.write(importLine.toString().trim());
				writerImportList.close();
				System.out.println("End Import List creation: " + importCSVFile);
				
				System.out.println("Creating Delete List ( " + l + " ) ...");
				String deleteCSVFile = FOLDER + DELETE_FILE + "_" + l + EXTENSION;			
				BufferedWriter writerDeleteList = new BufferedWriter(new FileWriter(deleteCSVFile));
				writerDeleteList.write(deleteLine.toString().trim());
				writerDeleteList.close();
				System.out.println("End Delete List creation: " + deleteCSVFile);
				
			}
			
			System.out.println( "END Lists creation");
			
			*/
			
		} catch( Exception e ) {
			System.out.println( e.getMessage());
		}
	
	}
	
	
	/*
	
	private static StringBuffer createLumataUsersToImport() {
		
		StringBuffer users = new StringBuffer();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		String subscriptionDate = dateFormat.format(cal.getTime());
		
		users.append(subscriptionDate + "|393409429107|||||Mr|Marco|Zuffada|||monte cr|26013|it|1980-02-09|M|marco.zuffada@lumatagroup.com|1|topics\n");
		users.append(subscriptionDate + "|393669393643|||||Mr|Arcangelo|Di Pasquale|||Milano|20100|it|1976-03-24|M|arcangelo.dipasquale@lumatagroup.com|2|topics\n");
		users.append(subscriptionDate + "|393280654379|||||Mr|Eros|Pedrini|||monte cr|26013|it|1974-02-09|M|eros.pedrini@lumatagroup.com|3|topics\n");
		users.append(subscriptionDate + "|393277342517|||||Mr|Fabrizio|Giovannetti|Via cavoli suoi 3||senna comasco|22070|it|1983-10-08|M|fabrizio.giovannetti@lumatagroup.com|4|topics\n");
		users.append(subscriptionDate + "|393356902723|||||Mr|Alessandro|Bevilini|Via cavoli suoi 3||Milano|20100|it|1983-10-08|M|alessandro.bevilini@lumatagroup.com|5|topics\n");
		users.append(subscriptionDate + "|393393102978|||||Mr|Alessandro|Bevilini|Via cavoli suoi 3||Milano|20100|it|1983-10-08|M|alessandro.bevilini@lumatagroup.com|6|topics\n");
		users.append(subscriptionDate + "|393396414826|||||Mr|Rafael|Fiume|Via cavoli suoi 3||Milano|20100|it|1983-10-08|M|rafael.fiume@lumatagroup.com|7|topics\n");
		users.append(subscriptionDate + "|393991386345|||||Mr|Fabio|Maffioletti|Via cavoli suoi 3||Milano|20100|it|1983-10-08|M|fabio.maffioletti@lumatagroup.com|8|topics\n");
		users.append(subscriptionDate + "|393357974040|||||Mr|Andrea|Dal Passo|Via cavoli suoi 3||Milano|20100|it|1983-10-08|M|andrea.dalpasso@lumatagroup.com|9|topics\n");
		users.append(subscriptionDate + "|393316257404|||||Mr|Alberto|Colombo|Via cavoli suoi 3||Milano|20100|it|1983-10-08|M|alberto.colombo@lumatagroup.com|10|topics\n");
				
		return users;
		
	}
	
	private static StringBuffer createLumataUsersToDelete() {
		
		StringBuffer users = new StringBuffer();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		String subscriptionDate = dateFormat.format(cal.getTime());
		
		users.append(subscriptionDate + "|msisdn|393409429107\n");
		users.append(subscriptionDate + "|msisdn|393669393643\n");
		users.append(subscriptionDate + "|msisdn|393280654379\n");
		users.append(subscriptionDate + "|msisdn|393277342517\n");
		users.append(subscriptionDate + "|msisdn|393356902723\n");
		users.append(subscriptionDate + "|msisdn|393393102978\n");
		users.append(subscriptionDate + "|msisdn|393396414826\n");
		users.append(subscriptionDate + "|msisdn|393991386345\n");
		users.append(subscriptionDate + "|msisdn|393357974040\n");
		users.append(subscriptionDate + "|msisdn|393316257404\n");
				
		return users;
		
	}
	
	
	*/
	
}
