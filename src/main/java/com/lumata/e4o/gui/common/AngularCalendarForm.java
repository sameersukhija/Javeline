package com.lumata.e4o.gui.common;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class AngularCalendarForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger( AngularCalendarForm.class );
	
	private final String CALENDAR_FORM_XPATH_ = "//div[@ng-model='date']";
	private final String CALENDAR_FORM_MONTH_YEAR_XPATH_ = CALENDAR_FORM_XPATH_ + "//button[@class='btn btn-block']";
	private final String CALENDAR_FORM_DAY_XPATH_ = CALENDAR_FORM_XPATH_ + "//button[@class='btn btn-info']";
	
	private final String CALENDAR_FORM_MONTH_SELECTION_XPATH_ = CALENDAR_FORM_XPATH_ + "//span[text()='${month}']/parent::button";
	private final String CALENDAR_FORM_DAY_SELECTION_XPATH_ = CALENDAR_FORM_XPATH_ + "//span[text()='${day}']/parent::button";
	
	private final String CALENDAR_FORM_PULL_LEFT_XPATH_ = CALENDAR_FORM_XPATH_ + "//button[@class='btn pull-left']";
	private final String CALENDAR_FORM_PULL_RIGHT_XPATH_ = CALENDAR_FORM_XPATH_ + "//button[@class='btn pull-right']";
	
	private Calendar currDate;
	
	private enum Month {
		
		January( Calendar.JANUARY ),
		February( Calendar.FEBRUARY ),
		March( Calendar.MARCH ),
		April( Calendar.APRIL ),
		May( Calendar.MAY ),
		June( Calendar.JUNE ),
		July( Calendar.JULY ),
		August( Calendar.AUGUST ),
		September( Calendar.SEPTEMBER ),
		October( Calendar.OCTOBER ),
		November( Calendar.NOVEMBER ),
		December( Calendar.DECEMBER );
		
		private int month;
		
		Month( int month ) {
			this.month = month;
		}
		
		public int month() {
			return month;
		}
		
		@SuppressWarnings("unused")
		public String formatMonth() {
			return String.format("%02d", month );
		}
		
	}
	
	public AngularCalendarForm( SeleniumWebDriver selenium, Calendar date, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.currDate = date;
		
	}	

	public static AngularCalendarForm create( SeleniumWebDriver selenium, Calendar date, long timeout, long interval ) {
		
		return new AngularCalendarForm( selenium, date, timeout, interval );
		
	}
	
	public AngularCalendarForm openByName( String name ) throws FormException {
		
		clickName( name );
		
		return this;
		
	}
	
	public AngularCalendarForm setDate( Calendar date ) throws FormException {
		
		try {
			
			buildCurrDate().
			setYear( date.get( Calendar.YEAR ) ).
			setMonth( date.get( Calendar.MONTH ) ).
			setDay( date.get( Calendar.DATE ) );
			
		} catch ( FormException ne ) {
			
			logger.error( ne.getMessage(), ne );
			
		}
		
		return this;
		
	}	
	
	public AngularCalendarForm setYear( int year ) throws FormException {
		
		int diffYear = year - currDate.get( Calendar.YEAR );
		
		if( 0 != diffYear ) {
			
			clickXPath( CALENDAR_FORM_MONTH_YEAR_XPATH_ );
			
			if( diffYear > 0 ) {
				
				increment( diffYear );
				
			} else {
				
				decrement( Math.abs( diffYear ) );
				
			}
			
			clickXPath( CALENDAR_FORM_MONTH_YEAR_XPATH_ ).
			clickXPath( CALENDAR_FORM_MONTH_YEAR_XPATH_ );
						
		}
				
		return this;
		
	}	
	
	public AngularCalendarForm setMonth( int month ) throws FormException {
		
		if( currDate.get( Calendar.MONTH ) != month ) {
			
			clickXPath( CALENDAR_FORM_MONTH_YEAR_XPATH_ ).
			clickXPath( CALENDAR_FORM_MONTH_SELECTION_XPATH_.replace( "${month}", Month.values()[ month ].name() ) );
		
		}	
			
		return this;
		
	}	
	
	public AngularCalendarForm setDay( int day ) throws FormException {
		
		clickXPath( CALENDAR_FORM_DAY_SELECTION_XPATH_.replace( "${day}", String.format("%02d", day ) ) );
		
		return this;
		
	}	
	
	private AngularCalendarForm buildCurrDate() throws FormException {
		
		String[] currentMonthYear = getTextByXPath( CALENDAR_FORM_MONTH_YEAR_XPATH_ ).split( " " );
		
		try {
			
			if( currentMonthYear.length == 2 ) {
				
				currDate.set( Calendar.YEAR, Integer.valueOf( currentMonthYear[ 1 ] ) );
				
				currDate.set( Calendar.MONTH, Month.valueOf( currentMonthYear[ 0 ] ).month() );
								
			} else {
								
				throw new FormException( "no valid month and year" );
				
			}
						
			try {
				
				currDate.set( Calendar.DATE, Integer.valueOf( getTextByXPath( CALENDAR_FORM_DAY_XPATH_, 1000L, 100L ) ) );
								
			} catch( FormException fe ) {
				
				currDate.set( Calendar.DATE, Calendar.getInstance().get( Calendar.DATE ) );
				
			}
						
		} catch ( NumberFormatException ne ) {
			
			logger.error( ne.getMessage(), ne );
			
			throw new FormException( ne.getMessage(), ne );
			
		}	
		
		return this;
		
	}
	
	private AngularCalendarForm decrement( int decrement ) throws FormException {
		
		for( int d = 0; d < decrement; d++ ) {
			
			clickXPath( CALENDAR_FORM_PULL_LEFT_XPATH_);
			
		}		
		
		return this;
		
	}

	private AngularCalendarForm increment( int increment ) throws FormException {
		
		for( int i = 0; i < increment; i++ ) {
			
			clickXPath( CALENDAR_FORM_PULL_RIGHT_XPATH_);
			
		}
		
		return this;
		
	}
	
	@Override
	public AngularCalendarForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public AngularCalendarForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public String getTextByXPath( String xpath ) throws FormException {
		
		return super.getTextByXPath( xpath );
		
	}
	
	public String getTextByXPath( String xpath, Long timeout, Long interval ) throws FormException {
		
		return super.getTextByXPath( xpath, timeout, interval );
		
	}
	
}
