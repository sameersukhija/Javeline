package com.lumata.expression.operators.dao.campaigns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Campaigns {
	
	private static final Logger logger = LoggerFactory.getLogger(Campaigns.class);
	
	private int campaign_id;
	private String campaign_name;
	private Date start_date;
	private Date end_date;
	private String model_name;
	
	public enum CampaignStatuses { SAVED, ACTIVATED, SUSPENDED, EOL, PAUSED };
		
	public Campaigns() {}
	
	public Campaigns( int campaign_id, String campaign_name, Date start_date, Date end_date, String model_name ) {
		
		this.campaign_id = campaign_id;
		this.campaign_name = campaign_name;
		this.start_date = start_date;		
		this.end_date = end_date;
		this.model_name = model_name;
	
	}

	public Campaigns( int campaign_id, String campaign_name, String start_date, String end_date, String model_name ) {
		
		try {
		
			this.campaign_id = campaign_id;
			this.campaign_name = campaign_name;
			this.start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( start_date );			
			this.end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( end_date );
			this.model_name = model_name;
		
		} catch ( ParseException e  ) {
			
			logger.error( e.getMessage(), e );
			
		}	
	
	}
	
	public Campaigns( ResultSet rs ) {
		
		try {
		
			this.campaign_id = rs.getInt( "campaign_id" );
			this.campaign_name = rs.getString( "campaign_name" );
			this.start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( rs.getString( "start_date" ) );
			this.end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( rs.getString( "end_date" ) );
			this.model_name = rs.getString( "model_name" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch ( ParseException e  ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getCampaignID() { return this.campaign_id; }
	
	public String getCampaignName() { return this.campaign_name; }
	
	public String getStartDateAsString() { return this.start_date.toString(); }
	
	public Date getStartDateAsDate() { return this.start_date; }
	
	public String getEndDateAsString() { return this.end_date.toString(); }
	
	public Date getEndDateAsDate() { return this.end_date; }
	
	public String getModelName() { return this.model_name; }
		
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		str.append( "campaign_id:").append( this.campaign_id ).append( "," )
			.append( "campaign_name:" ).append( this.campaign_name ).append( "," )
			.append( "start_date:" ).append( this.start_date.toString() ).append( "," )
			.append( "end_date:" ).append( this.end_date.toString() ).append( "," )
			.append( "model_name:" ).append( this.model_name );
			
		return str.toString();
				
	}
	
}
