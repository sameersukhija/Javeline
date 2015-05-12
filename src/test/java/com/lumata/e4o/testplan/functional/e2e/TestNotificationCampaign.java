package com.lumata.e4o.testplan.functional.e2e;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.utils.Arithmetic;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.schema.global.ChannelReturnCode;
import com.lumata.e4o.schema.global.E4oModule;
import com.lumata.e4o.schema.global.NotifLogs;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlGlobal;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;

import static com.lumata.common.testing.orm.Query.*;

@TCOwners(
	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" )
)
@TCMysqlGlobal
public class TestNotificationCampaign extends ParentTestCase {

	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void fillDatabase() throws FormException, JSONException, JSONSException, ParseException, SQLException {
	
		Integer days = 25;
		
		Calendar log_date = Calendar.getInstance(); 
		log_date.add( Calendar.DATE, -days );
		
		String query = select().from( new E4oModule() ).build();
		
		ResultSet rs = mysqlGlobal.execQuery( query );
		
		ArrayList<String> e4o_modules = new ArrayList<String>();
		
		while( rs.next() ) {
			
			E4oModule module = new E4oModule( rs );
			
			if( 
				module.getModuleId() == 1 ||
				module.getModuleId() == 2 ||
				module.getModuleId() == 3 ||
				module.getModuleId() == 7 ||
				module.getModuleId() == 12
			) {
				
				e4o_modules.add( module.getModuleName() );
			
			}
			
		}
		
		String[] categories = new String [] { "GLOBAL", "SMS", "MAIL" };
						
		String[][] channel_return_codes = new String [][] { 
			{ "0", "NO_CHANNEL_LANGUAGE", "0", "NO_CHANNEL_LANGUAGE" },
			{ "1", "NOT_SENT", "0", "NOT_SENT" },
			{ "2", "NOT_SENT", "1", "NOT_SENT" },
			{ "3", "ACCEPTED", "1", "SENT" },
			{ "4", "DELIVERED", "1", "DELIVERED" },
			{ "5", "NOT_SENT", "2", "NOT_SENT" },
			{ "6", "DELIVERED", "2", "DELIVERED" },
			{ "7", "EXPIRED", "2", "EXPIRED" },
			{ "8", "NotDelivered", "1", "ERROR" },
			{ "9", "FROZEN", "1", "ERROR" },
			{ "10", "EXPIRED", "1", "EXPIRED" },
			{ "11", "ACCEPTED", "2", "SENT" },
			{ "12", "NotDelivered", "2", "ERROR" },
			{ "13", "FROZEN", "2", "ERROR" }			
		};
	
		String[] msisdn_list = new String [] { "3399900001", "3399900002" };
		
		ArrayList<ChannelReturnCode> channelsReturnCodeByGlobal = new ArrayList<ChannelReturnCode>();
		ArrayList<ChannelReturnCode> channelsReturnCodeBySMS = new ArrayList<ChannelReturnCode>();
		ArrayList<ChannelReturnCode> channelsReturnCodeByMAIL = new ArrayList<ChannelReturnCode>();
		
		for( String[] channel_return_code : channel_return_codes ) {
			
			ChannelReturnCode crc = new ChannelReturnCode();
			crc.setReturnCode( Short.valueOf( channel_return_code[0] ) );
			crc.setDescription( channel_return_code[1] );
			crc.setChannelId( Byte.valueOf( channel_return_code[2] ) );
			crc.setMessageState( channel_return_code[3] );
			
			query = insert_ignore( crc ).values().build();
			
			mysqlGlobal.execUpdate( query );
			
			switch( crc.getChannelId() ) {
			
				case 0: { /*channelsReturnCodeByGlobal.add( crc );*/ break; }				
				case 1: { channelsReturnCodeBySMS.add( crc ); break; }				
				case 2: { channelsReturnCodeByMAIL.add( crc ); break; }				
				default: break;
			
			}
			
		}
		
		for( int day = 0; day <= days; day++ ) {
						
			for( String e4o_module : e4o_modules ) {
								
				for( String msisdn : msisdn_list ) {
				
					insertNotif( log_date, e4o_module, msisdn, categories[0], channelsReturnCodeByGlobal );
					
					log_date.add( Calendar.SECOND, 1 );
					
					insertNotif( log_date, e4o_module, msisdn, categories[1],channelsReturnCodeBySMS );
					
					log_date.add( Calendar.SECOND, 1 );
										
					insertNotif( log_date, e4o_module, msisdn, categories[2],channelsReturnCodeByMAIL );
					
					log_date.add( Calendar.SECOND, 1 );
					
				}
				
			}
			
			log_date.add( Calendar.DATE, 1 );
			
		}
		
	}
	
	private void insertNotif( Calendar log_date, String e4o_module, String msisdn, String category, ArrayList<ChannelReturnCode> crc  ) {
		
		Integer featureId = getFeatureId( e4o_module );
		
		String text = e4o_module + "( " + featureId + " ) ";
		
		switch( category ) {
		
			case "SMS": { 
				
				text+= " sms notification";
			
				break;
				
			}					
			case "MAIL": {
											
				text += " ###campaign_id###|Applestar1||###customer_id###||###subscriber_msisdn###||###forname###|###name###|###tongue###|###gender###|###customer_email###||||||Applestar1|###vouchure_code###||###production_date###|###end_validity_date###";
			
				break;
				
			}
			default: break;
		
		}
		
		Integer returnCodeToInsert = (int)( Math.random() * crc.size() );
		
		Integer returnCodeToStart = (int)( Math.random() * crc.size() );
		
		Long id = Arithmetic.random( 12479266000000000L, 124792669999999999L );
		
		for( int returnCodeIndex = returnCodeToStart; returnCodeIndex < returnCodeToStart + returnCodeToInsert; returnCodeIndex++ ) {
			
			Integer index = ( ( returnCodeIndex / crc.size() ) + ( returnCodeIndex % crc.size() ) );
			
			NotifLogs nf = new NotifLogs();
			
			nf.setLogDate( log_date.getTime() );
			nf.setId( id );
			nf.setCategory( category );
			nf.setAction( e4o_module );
			nf.setFeatureId( featureId );
			nf.setIdentifier( msisdn );
			nf.setText( text );
			nf.setState( crc.get( index ).getReturnCode().toString() );
			nf.setTenantId( (short)1 );
						
			System.out.println( nf.toString() );
			
			String query = insert( nf ).values().build();
			
			mysqlGlobal.execUpdate( query );
		
		}	
		
	}

	private Integer getFeatureId( String e4o_module ) {
		
		switch( e4o_module ) {
		
			case "CampaignManager": { 								
				
				return 1;
				
			}
			case "CampaignProvisioning": { 								
				
				return 2;
				
			}
			case "Catalog": { 								
				
				return 1000;
				
			}
			case "LoyaltyBadgeProgram": { 								
				
				return 1;
				
			}			
			default: return null;
		
		}
		
	}
	
}
