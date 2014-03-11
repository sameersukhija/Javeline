package com.lumata.expression.operators.system.cdr;

public class CDRDateIncrement {

	private Integer date_increment_year;
	private Integer date_increment_month;
	private Integer date_increment_day;
	private Integer date_increment_hour;
	private Integer date_increment_minute;
	private Integer date_increment_second;
	
	public CDRDateIncrement() {
		date_increment_year = null;
		date_increment_month = null;
		date_increment_day = null;
		date_increment_hour = null;
		date_increment_minute = null;
		date_increment_second = null;
	}
	
	public Integer getYearIncrement() {
		
		return this.date_increment_year;
				
	}
	
	public Integer getMonthIncrement() {
		
		return this.date_increment_month;
				
	}
	
	public Integer getDayIncrement() {
		
		return this.date_increment_day;
				
	}
	
	public Integer getHourIncrement() {
		
		return this.date_increment_hour;
				
	}
	
	public Integer getMinuteIncrement() {
		
		return this.date_increment_minute;
				
	}
	
	public Integer getSecondIncrement() {
		
		return this.date_increment_second;
				
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
