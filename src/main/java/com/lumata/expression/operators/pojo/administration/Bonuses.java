/*
+-----------------------------+-----------------------------------------------------------------------------------------------------+------+-----+---------+----------------+
| Field                       | Type                                                                                                | Null | Key | Default | Extra          |
+-----------------------------+-----------------------------------------------------------------------------------------------------+------+-----+---------+----------------+
| bonus_id                    | smallint(3) unsigned                                                                                | NO   | PRI | NULL    | auto_increment |
| bonus_name                  | varchar(30)                                                                                         | NO   | UNI | NULL    |                |
| unit                        | varchar(15)                                                                                         | NO   |     | none    |                |
| optin                       | tinyint(4)                                                                                          | NO   |     | 0       |                |
| in_credit                   | tinyint(1)                                                                                          | NO   |     | NULL    |                |
| handleOptOut                | varchar(5)                                                                                          | YES  |     | false   |                |
| inFeedbackRequired          | varchar(5)                                                                                          | YES  |     | false   |                |
| billable                    | tinyint(4)                                                                                          | NO   |     | 0       |                |
| commodity_type              | varchar(20)                                                                                         | YES  |     | NULL    |                |
| account_type                | varchar(25)                                                                                         | YES  |     | NULL    |                |
| max_value                   | mediumint(9)                                                                                        | YES  |     | NULL    |                |
| default_validity_type       | enum('Fixed','Variable_Days','Variable_Weeks','Variable_Months','Variable_Quarters')                | NO   |     | NULL    |                |
| default_period_type         | enum('Days','Weeks','Months','Quarters','Years')                                                    | NO   |     | NULL    |                |
| default_qty_period          | smallint(6)                                                                                         | NO   |     | 1       |                |
| default_fixed_validity_from | enum('BEGINNING_OF_CURRENT_PERIOD','BEGINNING_OF_NEXT_PERIOD','LAST_SUBSCRIPTION_ANNIVERSARY_DATE') | YES  |     | NULL    |                |
| unitary_cost                | float                                                                                               | YES  |     | NULL    |                |
| recommended_price           | float                                                                                               | YES  |     | NULL    |                |
+-----------------------------+-----------------------------------------------------------------------------------------------------+------+-----+---------+----------------+
*/

package com.lumata.expression.operators.pojo.administration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bonuses {

	private static final Logger logger = LoggerFactory.getLogger( Bonuses.class );
	
	public enum Fields { 
							bonus_id, 
							bonus_name, 
							unit, 
							optin, 
							in_credit, 
							handleOptOut, 
							inFeedbackRequired, 
							billable, 
							commodity_type, 
							account_type,
							max_value,
							default_validity_type,
							default_period_type,
							default_qty_period,
							default_fixed_validity_from,
							unitary_cost,
							recommended_price; 
	}
	
	private enum DefaultValidityType { Fixed, Variable_Days, Variable_Weeks, Variable_Months, Variable_Quarters };
	private enum DefaultPeriodType { Days, Weeks, Months, Quarters, Years };
	private enum DefaultFixedValidityFrom { BEGINNING_OF_CURRENT_PERIOD, BEGINNING_OF_NEXT_PERIOD, LAST_SUBSCRIPTION_ANNIVERSARY_DATE }; 
	
	private int bonus_id;
	private String bonus_name;
	private String unit;
	private int optin;
	private int in_credit;
	private String handleOptOut;
	private String inFeedbackRequired;
	private int billable;
	private String commodity_type;
	private String account_type;
	private int max_value;
	private String default_validity_type;
	private String default_period_type;
	private int default_qty_period;
	private String default_fixed_validity_from;
	private float unitary_cost;
	private float recommended_price;
		
	public Bonuses() {
		
		this.set( -1, "", "", -1, -1, "", "", -1, "", "", -1, "", "", -1, "", -1F, -1F );
		
	}
	
	public Bonuses( int bonous_id, String bonus_name, String unit, int optin, int in_credit, String handleOptOut, String inFeedbackRequired, int billable, String commodity_type, String account_type, int max_value, String default_validity_type, String default_period_type, int default_qty_period, String default_fixed_validity_from, float unitary_cost, float recommended_price ) {
		
		this.set( bonous_id, bonus_name, unit, optin, in_credit, handleOptOut, inFeedbackRequired, billable, commodity_type, account_type, max_value, Bonuses.DefaultValidityType.valueOf( default_validity_type ).toString(), Bonuses.DefaultPeriodType.valueOf( default_period_type ).toString(), default_qty_period, Bonuses.DefaultFixedValidityFrom.valueOf( default_fixed_validity_from ).toString(), unitary_cost, recommended_price );
					
	}
	
	public Bonuses( ResultSet rs ) {
		
		try {
					
			this.set( 
					rs.getInt(Bonuses.Fields.bonus_id.toString()), 
					rs.getString(Bonuses.Fields.bonus_name.toString()), 
					rs.getString(Bonuses.Fields.unit.toString()), 
					rs.getInt(Bonuses.Fields.optin.toString()), 
					rs.getInt(Bonuses.Fields.in_credit.toString()), 
					rs.getString(Bonuses.Fields.handleOptOut.toString()), 
					rs.getString(Bonuses.Fields.inFeedbackRequired.toString()), 
					rs.getInt(Bonuses.Fields.billable.toString()), 
					rs.getString(Bonuses.Fields.commodity_type.toString()), 
					rs.getString(Bonuses.Fields.account_type.toString()), 
					rs.getInt(Bonuses.Fields.max_value.toString()), 
					rs.getString(Bonuses.Fields.default_validity_type.toString()), 
					rs.getString(Bonuses.Fields.default_period_type.toString()), 
					rs.getInt(Bonuses.Fields.default_qty_period.toString()), 
					rs.getString(Bonuses.Fields.default_fixed_validity_from.toString()), 
					rs.getFloat(Bonuses.Fields.unitary_cost.toString()), 
					rs.getFloat(Bonuses.Fields.recommended_price.toString()) 
			);
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public Bonuses( JSONObject bonuses ) {
		
		try {
						
			logger.info( ( bonuses.getString( Bonuses.Fields.max_value.toString() ).equals( "NULL1" ) ) ? "true" : "false" );
			
			/*
			this.set(
					( 	bonuses.isNull(Bonuses.Fields.bonus_id.toString()) ? -1 : ( !( bonuses.getString( Bonuses.Fields.bonus_id.toString() ) == "" || bonuses.getString( Bonuses.Fields.bonus_id.toString() ) == "NULL"  ) ? bonuses.getInt( Bonuses.Fields.bonus_id.toString() ) : -1 ) ), 
						bonuses.getString(Bonuses.Fields.bonus_name.toString()), 
						bonuses.getString(Bonuses.Fields.unit.toString()), 
						(!( bonuses.getString( Bonuses.Fields.optin.toString() ) == "" || bonuses.getString( Bonuses.Fields.optin.toString() ) == "NULL"  ) ? bonuses.getInt(Bonuses.Fields.optin.toString()) : -1 ),
						(!( bonuses.getString( Bonuses.Fields.in_credit.toString() ) == "" || bonuses.getString( Bonuses.Fields.in_credit.toString() ) == "NULL"  ) ? bonuses.getInt(Bonuses.Fields.in_credit.toString()) : -1 ), 
						bonuses.getString(Bonuses.Fields.handleOptOut.toString()), 
						bonuses.getString(Bonuses.Fields.inFeedbackRequired.toString()), 
						(!( bonuses.getString( Bonuses.Fields.billable.toString() ) == "" || bonuses.getString( Bonuses.Fields.billable.toString() ) == "NULL"  ) ? bonuses.getInt(Bonuses.Fields.billable.toString()) : -1 ), 
						bonuses.getString(Bonuses.Fields.commodity_type.toString()), 
						bonuses.getString(Bonuses.Fields.account_type.toString()), 
						(( bonuses.getString( Bonuses.Fields.max_value.toString() ) != "" && bonuses.getString( Bonuses.Fields.max_value.toString() ) != "NULL"  ) ? bonuses.getInt(Bonuses.Fields.max_value.toString()) : -1 ), 
						bonuses.getString(Bonuses.Fields.default_validity_type.toString()), 
						bonuses.getString(Bonuses.Fields.default_period_type.toString()), 
						(!( bonuses.getString( Bonuses.Fields.default_qty_period.toString() ) == "" || bonuses.getString( Bonuses.Fields.default_qty_period.toString() ) == "NULL"  ) ? bonuses.getInt(Bonuses.Fields.default_qty_period.toString()) : -1 ), 
						bonuses.getString(Bonuses.Fields.default_fixed_validity_from.toString()), 
						Float.parseFloat(bonuses.getString(Bonuses.Fields.unitary_cost.toString())), 
						Float.parseFloat(bonuses.getString(Bonuses.Fields.recommended_price.toString()))
			);
			*/		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getBonusID() {
		
		return this.bonus_id;
		
	}
		
	public String getBonusName() {
		
		return this.bonus_name;

	}
		
	public String getUnit() {
	
		return this.unit;

	}
	
	public int getOptin() {

		return this.optin;
		
	}	
	
	public int getInCredit() {
	
		return this.in_credit;
		
	}
	
	public String getHandleOptOut() {
		
		return this.handleOptOut;
	
	}
	
	public String getInFeedbackRequired() {
	
		return this.inFeedbackRequired;

	}
	
	public int getBillable() {
		
		return this.billable;
		
	}
	
	public String getCommodityType() {
	
		return this.commodity_type;
		
	}
	
	public String getAccountType() {
	
		return this.account_type;
		
	}
	
	public int getMaxValue() {
	
		return this.max_value;
		
	}
	
	public String getDefaultValidityType() {
		
		return this.default_validity_type;
		
	}
	
	public String getDefaultPeriodType() {
		
		return this.default_period_type;
	
	}
	
	public int getDefaultQtyPeriod() {
		
		return this.default_qty_period;
		
	}	
		
	public String getDefaultFixedValidityFrom() {	
		
		return this.default_fixed_validity_from;
		
	}	
		
	public float getUnitaryCost() {
	
		return this.unitary_cost;
		
	}
	
	public float getRecommendedPrice() {
		
		return this.recommended_price;
	
	}
	
	public void set( int bonous_id, String bonus_name, String unit, int optin, int in_credit, String handleOptOut, String inFeedbackRequired, int billable, String commodity_type, String account_type, int max_value, String default_validity_type, String default_period_type, int default_qty_period, String default_fixed_validity_from, float unitary_cost, float recommended_price ) {
		
		this.setBonusID( bonous_id );
		this.setBonusName( bonus_name );
		this.setUnit( unit );
		this.setOptin( optin );
		this.setInCredit( in_credit );
		this.setHandleOptOut( handleOptOut );
		this.setInFeedbackRequired( inFeedbackRequired );
		this.setBillable( billable );
		this.setCommodityType( commodity_type );
		this.setAccountType( account_type );
		this.setMaxValue( max_value );
		this.setDefaultValidityType( default_validity_type );
		this.setDefaultPeriodType( default_period_type );
		this.setDefaultQtyPeriod( default_qty_period );
		this.setDefaultFixedValidityFrom( default_fixed_validity_from );
		this.setUnitaryCost( unitary_cost );
		this.setRecommendedPrice( recommended_price );
				
	}

	public void setBonusID( int bonous_id ) {
		
		this.bonus_id = bonous_id;
		
	}
		
	public void setBonusName( String bonus_name ) {
		
		this.bonus_name = bonus_name;

	}
		
	public void setUnit( String unit ) {
	
		this.unit = unit;

	}
	
	public void setOptin( int optin ) {

		this.optin = optin;
		
	}	
	
	public void setInCredit( int in_credit ) {
	
		this.in_credit = in_credit;
		
	}
	
	public void setHandleOptOut( String handleOptOut ) {
		
		this.handleOptOut = handleOptOut;
	
	}
	
	public void setInFeedbackRequired( String inFeedbackRequired ) {
	
		this.inFeedbackRequired = inFeedbackRequired;

	}
	
	public void setBillable( int billable ) {
		
		this.billable = billable;
		
	}
	
	public void setCommodityType( String commodity_type ) {
	
		this.commodity_type = commodity_type;
		
	}
	
	public void setAccountType( String account_type ) {
	
		this.account_type = account_type;
		
	}
	
	public void setMaxValue( int max_value ) {
	
		this.max_value = max_value;
		
	}
	
	public void setDefaultValidityType( String default_validity_type ) {
		
		this.default_validity_type = default_validity_type;
		
	}
	
	public void setDefaultPeriodType( String default_period_type ) {
		
		this.default_period_type = default_period_type;
	
	}
	
	public void setDefaultQtyPeriod( int default_qty_period ) {
		
		this.default_qty_period = default_qty_period;
		
	}	
		
	public void setDefaultFixedValidityFrom( String default_fixed_validity_from ) {	
		
		this.default_fixed_validity_from = default_fixed_validity_from;
		
	}	
		
	public void setUnitaryCost( float unitary_cost ) {
	
		this.unitary_cost = unitary_cost;
		
	}
	
	public void setRecommendedPrice( float recommended_price ) {
		
		this.recommended_price = recommended_price;
	
	}
	
}
