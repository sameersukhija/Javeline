package com.lumata.expression.operators.dao.administration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.pojo.administration.Bonuses;

public class BonusesDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BonusesDAO.class);
	
	private ArrayList<Bonuses> list;
	
	public BonusesDAO() {
		
		this.list = new ArrayList<Bonuses>();
		
	}
	
	public BonusesDAO( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}	
	
	public BonusesDAO( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject bonusesDAO = new JSONObject();
				
		try {
		
			this.load(env, tenant, filteredIds);
			
			switch( loadingType ) {
			
				case FILE: {  
					bonusesDAO = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					bonusesDAO = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
			
			JSONArray bonuses = bonusesDAO.getJSONArray( "bonuses" );
			
			for( int i = 0; i < bonuses.length(); i++ ) {
				
				Bonuses bonus = new Bonuses( bonuses.getJSONObject( i ) );
				
				this.insert( env, tenant, filteredIds, bonus );
				
			}
			
			this.load(env, tenant, filteredIds);
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
			
		
	}

	public Bonuses get( int bonusIndex ) {
		
		return this.list.get( bonusIndex );
				
	}
	
	public Bonuses get( String bonusName ) {
		
		if( bonusName == null || bonusName.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( ( this.list.get( i ).getBonusName() ).equals( bonusName ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isBonus( String bonusName ) {
		
		if( this.get(bonusName) != null ) { return true; }
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, BonusesDAO bonusesDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		//String query = "DELETE FROM " + tenant + ".catalog_offer_content " + ( catalogOfferContentDAO != null ? "WHERE " + CatalogOfferContentDAO.Fields.offer_id.toString() + " = '" + catalogOfferContentDAO.getCharacteristicName() + "'": "" ) + ";";
				
		String query = "DELETE FROM " + tenant + ".bonuses;";
				
		index = mysql.execUpdate( query );
				
		mysql.close();
		
		//this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public int deleteAll( Environment env, String tenant, ArrayList<Integer> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}

	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject tokenType ) {
		
		int index = -1;
		
		/*
		try {
			
			this.insert(env, tenant, filteredIds, tokenType.getString( "" ), expirationDuration, expirationDurationUnit, qtyMaxReddems, singleUseRedeemDurationTimeout, tokenFormat, description, salesChannelsList)
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		*/
		return index;
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, Bonuses bonuses ) {
		
		int bonusID = -1; 
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		if( this.get( bonuses.getBonusName() ) == null ) {
			
			// Insert Bonuses
			String query = "INSERT INTO " + tenant + ".bonuses " +
					"( " +
					Bonuses.Fields.bonus_name.toString() +  ", " +
					Bonuses.Fields.unit.toString() +  ", " +
					Bonuses.Fields.optin.toString() +  ", " +
					Bonuses.Fields.in_credit.toString() +  ", " +
					Bonuses.Fields.handleOptOut.toString() +  ", " +
					Bonuses.Fields.inFeedbackRequired.toString() +  ", " +
					Bonuses.Fields.billable.toString() +  ", " +
					Bonuses.Fields.commodity_type.toString() +  ", " +
					Bonuses.Fields.account_type.toString() +  ", " +
					Bonuses.Fields.max_value.toString() +  ", " +
					Bonuses.Fields.default_validity_type.toString() +  ", " +
					Bonuses.Fields.default_period_type.toString() +  ", " +
					Bonuses.Fields.default_qty_period.toString() +  ", " +
					Bonuses.Fields.default_fixed_validity_from.toString() +  ", " +
					Bonuses.Fields.unitary_cost.toString() +  ", " +
					Bonuses.Fields.recommended_price.toString() + 					
					") " +
					"VALUES ( '" + bonuses.getBonusName() + "'," +
					"		  " + bonuses.getUnit() + ", " +
					"		  " + bonuses.getOptin() + ", " +
					"		  " + bonuses.getInCredit() + ", " +
					"		  " + bonuses.getHandleOptOut() + ", " +
					"		  " + bonuses.getInFeedbackRequired() + ", " +
					"		  " + bonuses.getBillable() + ", " +
					"		  " + bonuses.getCommodityType() + ", " +
					"		  " + bonuses.getAccountType() + ", " +
					"		  " + bonuses.getMaxValue() + ", " +
					"		  " + bonuses.getDefaultValidityType() + ", " +
					"		  " + bonuses.getDefaultPeriodType() + ", " +
					"		  " + bonuses.getDefaultQtyPeriod() + ", " +
					"		  " + bonuses.getDefaultFixedValidityFrom() + ", " +
					"		  " + bonuses.getUnitaryCost() + ", " +
					"		  " + bonuses.getRecommendedPrice() +
					");";
			
			bonusID = mysql.execUpdate( query ); 
			
		} else {
			
			bonusID = bonuses.getBonusID();
			
		}
					
		return bonusID;
		
	}
		
	public void load( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.list = new ArrayList<Bonuses>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = 	"SELECT 	 	bn." + Bonuses.Fields.bonus_id.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.bonus_name.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.unit.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.optin.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.in_credit.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.handleOptOut.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.inFeedbackRequired.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.billable.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.commodity_type.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.account_type.toString() + ", " +
						"		 	 	bn." + Bonuses.Fields.max_value.toString() + ", " +
						"				bn." + Bonuses.Fields.default_validity_type.toString() + ", " +
						"				bn." + Bonuses.Fields.default_period_type.toString() + ", " +
						"				bn." + Bonuses.Fields.default_qty_period.toString() + ", " +
						"				bn." + Bonuses.Fields.default_fixed_validity_from.toString() + ", " +
						"				bn." + Bonuses.Fields.unitary_cost.toString() + ", " +
						"				bn." + Bonuses.Fields.recommended_price.toString() + " " +
						"FROM 	" + tenant + ".bonuses bn;";
				
		ResultSet rs = mysql.execQuery( query );
		
		int bonus_id = 0;
		
		try {
		
			Bonuses bonuses = null;
			
			while( rs.next() ) {
							
				bonuses = new Bonuses( rs );
								
				bonus_id = bonuses.getBonusID();
					
				this.list.add( bonuses );
								
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		
	}
		
	public void refresh( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
		
		this.load(env, tenant, filteredIds);
		
	}
	
	public int size() {
		
		if( this == null ) { return -1; }
		else { return this.list.size(); }
		
	}	
	
}
