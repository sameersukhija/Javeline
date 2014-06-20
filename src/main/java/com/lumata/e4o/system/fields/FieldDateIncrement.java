package com.lumata.e4o.system.fields;

public class FieldDateIncrement {

	private Integer date_increment_year;
	private Integer date_increment_month;
	private Integer date_increment_day;
	private Integer date_increment_hour;
	private Integer date_increment_minute;
	private Integer date_increment_second;
	
	public FieldDateIncrement() {
		date_increment_year = null;
		date_increment_month = null;
		date_increment_day = null;
		date_increment_hour = null;
		date_increment_minute = null;
		date_increment_second = null;
	}
	
	public Integer getYearIncrement() {
		
		return ( this.date_increment_year != null ? this.date_increment_year : 0 );
				
	}
	
	public Integer getMonthIncrement() {
		
		return ( this.date_increment_month != null ? this.date_increment_month : 0 );
				
	}
	
	public Integer getDayIncrement() {
		
		return ( this.date_increment_day != null ? this.date_increment_day : 0 );
				
	}
	
	public Integer getHourIncrement() {
		
		return ( this.date_increment_hour != null ? this.date_increment_hour : 0 );
				
	}
	
	public Integer getMinuteIncrement() {
		
		return ( this.date_increment_minute != null ? this.date_increment_minute : 0 );
				
	}
	
	public Integer getSecondIncrement() {
		
		return ( this.date_increment_second != null ? this.date_increment_second : 0 );
				
	}
	
	public void setYearIncrement( Integer date_increment_year ) {
		
		this.date_increment_year = date_increment_year;
				
	}
	
	public void setMonthIncrement( Integer date_increment_month ) {
		
		this.date_increment_month = date_increment_month;
				
	}
	
	public void setDayIncrement( Integer date_increment_day ) {
		
		this.date_increment_day = date_increment_day;
				
	}
	
	public void setHourIncrement(  Integer date_increment_hour ) {
		
		this.date_increment_hour = date_increment_hour;
				
	}
	
	public void setMinuteIncrement( Integer date_increment_minute ) {
		
		this.date_increment_minute =  date_increment_minute;
				
	}
	
	public void setSecondIncrement( Integer date_increment_second ) {
		
		this.date_increment_second = date_increment_second ;
				
	}
	
}
