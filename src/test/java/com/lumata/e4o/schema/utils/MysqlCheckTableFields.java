package com.lumata.e4o.schema.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;

public class MysqlCheckTableFields {

	NetworkEnvironment env;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "nfsdataServer", "user", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String nfsdataServer, @Optional("superman") String user, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		environment = "E4O_QA3_NE";
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		mysql = new Mysql( env.getDataSource( tenant ) );
			
	}
	
	@Test
	public void matchTableFields() throws SQLException {
		
		String[] campaignsWeeklySubsStatusFields = "campaign_id, week_id, profile_id, status_id, network_id, arpu_id, seniority_id, qty_control, qty_control_benef, qty_provisioned, qty_notified, qty_beneficiary, qty_not_targeted, control_amount_usage, provisioned_amount_usage, notified_amount_usage, beneficiary_amount_usage, not_targeted_amount_usage, control_qty_msisdn_with_amount_usage, provisioned_qty_msisdn_with_amount_usage, notified_qty_msisdn_with_amount_usage, beneficiary_qty_msisdn_with_amount_usage, not_targeted_qty_msisdn_with_amount_usage, control_amount_recharge, provisioned_amount_recharge, notified_amount_recharge, beneficiary_amount_recharge, not_targeted_amount_recharge, control_qty_msisdn_with_amount_recharge, provisioned_qty_msisdn_with_amount_recharge, notified_qty_msisdn_with_amount_recharge, beneficiary_qty_msisdn_with_amount_recharge, not_targeted_qty_msisdn_with_amount_recharge, control_amount_call, provisioned_amount_call, notified_amount_call, beneficiary_amount_call, not_targeted_amount_call, control_qty_msisdn_with_amount_call, provisioned_qty_msisdn_with_amount_call, notified_qty_msisdn_with_amount_call, beneficiary_qty_msisdn_with_amount_call, not_targeted_qty_msisdn_with_amount_call, control_amount_message, provisioned_amount_message, notified_amount_message, beneficiary_amount_message, not_targeted_amount_message, control_qty_msisdn_with_amount_message, provisioned_qty_msisdn_with_amount_message, notified_qty_msisdn_with_amount_message, beneficiary_qty_msisdn_with_amount_message, not_targeted_qty_msisdn_with_amount_message, control_amount_data, provisioned_amount_data, notified_amount_data, beneficiary_amount_data, not_targeted_amount_data, control_qty_msisdn_with_amount_data, provisioned_qty_msisdn_with_amount_data, notified_qty_msisdn_with_amount_data, beneficiary_qty_msisdn_with_amount_data, not_targeted_qty_msisdn_with_amount_data, control_proba, provisioned_proba, notified_proba, beneficiary_proba, not_targeted_proba".split(", ");

		for( String field : campaignsWeeklySubsStatusFields ) {
			
			System.out.println( field );
						
		}
		
		System.out.println( "--------------------------------------" );
		
		ResultSet rs = mysql.execQuery( "DESC campaigns_weekly_subs_status" );
		
		while( rs.next() ) {
			
			System.out.println( rs.getString( "Field" ) );
			
		}
				
	}
	
}





/*
*/
