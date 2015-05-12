package com.lumata.e4o.system.configuration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOConf;
import com.lumata.e4o.schema.tenant.Conf;

public class ConfProperties {
	
	private ArrayList<Conf> propertiesList = new ArrayList<Conf>(); 
	
	public ConfProperties() {}
	
	public static ConfProperties getInstance() {
		
		return new ConfProperties();
		
	}
	
	public static ConfProperties getInstance( Mysql mysql ) {
		
		return new ConfProperties();
		
	}
	
	/** begin inmanager section **/	
	public ConfProperties waiting_time( Integer position, String current ) {
		
		Conf conf = new Conf()
			.setName( "waiting_time" )
			.setPosition( ( null != position ? position : 0 ) )
			.setSection( "inmanager" )
			.setProcessId( "NULL" )
			.setAuthGroup( "Admin" )
			.setCurrent( ( null != current ? current : "30000" ) )
			.setPrevious( "NULL" )
			.setDynStatic( "RW" )
			.setTime( null )
			.setType( "Value" )
			.setDescription( "" );
		
		this.propertiesList.add( conf );
		
		return this;
		
	}
	
	public ConfProperties sql_attempt_clause( Integer position, String current ) {
		
		// (>0) {1  MINUTE},(>2) {2 MINUTE}
		
		Conf conf = new Conf()
			.setName( "sql_attempt_clause" )
			.setPosition( ( null != position ? position : 0 ) )
			.setSection( "inmanager" )
			.setProcessId( "NULL" )
			.setAuthGroup( "Admin" )
			.setCurrent( ( null != current ? current : "(=1) {1 HOUR},(<3){8 HOUR},(=3){1 DAY},(>4){1 WEEK}" ) )
			.setPrevious( "NULL" )
			.setDynStatic( "RW" )
			.setTime( null )
			.setType( "Value" )
			.setDescription( "" );
		
		this.propertiesList.add( conf );
		
		return this;
		
	}	
	/** end inmanager section **/

	/** begin xmlrpc section 
	 * @throws ParseException **/
	public ConfProperties allow_list( Integer position, String current ) throws ParseException {
		
		Conf conf = new Conf()
			.setName( "allow_list" )
			.setPosition( ( null != position ? position : 0 ) )
			.setSection( "xmlrpc" )
			.setProcessId( "NULL" )
			.setAuthGroup( "Internal" )
			.setCurrent( ( null != current ? current : "" ) )
			.setPrevious( "NULL" )
			.setDynStatic( "RW" )
			.setTime( Format.getMysqlDate( Calendar.getInstance().getTime().toString() ) )
			.setType( "Value" )
			.setDescription( "Do we support the listing of XMLRPC signatures" );
		
		this.propertiesList.add( conf );
		
		return this;
		
	}	
	/** end xmlrpc section **/
		
	/** begin qa_in section **/
	public ConfProperties qa_unknown_msisdn( Integer position, String current ) {
		
		Conf conf = new Conf()
			.setName( "QA_UNKNOWN_MSISDN" )
			.setPosition( ( null != position ? position : 0 ) )
			.setSection( "qa_in" )
			.setProcessId( "NULL" )
			.setAuthGroup( "Admin" )
			.setCurrent( ( null != current ? current : "" ) )
			.setPrevious( "NULL" )
			.setDynStatic( "RW" )
			.setTime( null )
			.setType( "Value" )
			.setDescription( "" );
		
		this.propertiesList.add( conf );
		
		return this;
		
	}	
	/** end qa_in section **/
	
	public ArrayList<Conf> list() {
		
		return this.propertiesList;
		
	}
	
	public static void main(String [] args) {		
		
		DAOConf.getInstance( null ).qa_unknown_msisdn( Arrays.asList( 3399900001L, 3399900002L ) ).apply();
		
	}
	
}
