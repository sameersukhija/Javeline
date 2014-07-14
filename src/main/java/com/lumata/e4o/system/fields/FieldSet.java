package com.lumata.e4o.system.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.field.types.FieldMethod;
import com.lumata.e4o.system.field.types.FieldTypeSet;

@FieldTypeSet
public class FieldSet {

	private Set<String> set;
	private Set<String> set_current_value;
	private Set<String> set_next_value;
	private Integer set_curr_pos;
	private Integer set_range;
	private Integer set_next_pos;
	private Integer set_increment;
	private Boolean set_random;
	private String set_separator;
	
	public FieldSet( Set<String> set ) {
			
		this.set = set;
		
		set_current_value = null;
		set_random = false;
		set_next_value = null;
		set_next_pos = null;
		set_increment = null;
		set_range = null;
		set_separator = ",";
		
	}
	
	public FieldSet( final Enum<?>... values ) {
		
		this( new LinkedHashSet<String>() );
		
		for( Enum<?> value : values ) {
		
			set.add( value.name() );
			
		}
								
	}
	
	public FieldSet( final String... values ) {
		
		this( new LinkedHashSet<String>( Arrays.asList( values ) ) );
									
	}
	
	@FieldMethod
	public String getSet() throws FieldException {
						
		if( this.set_current_value == null && this.set_random == false ) { return ""; } 
		else {
			
			if( this.set_next_value != null && this.set_increment != null ) {
				
				this.set_current_value = this.set_next_value;
				
				this.set_curr_pos = this.set_next_pos;
				
				this.setNextSet();
				
			} else {
				
				if( this.set_increment != null ) {
					
					this.setNextSet();
					
				} else {
					
					if( this.set_random ) {
						
						this.set_current_value = this.generateRandomSet();
						
					}
					
				}
				
			}			
			
		}
		
		return String.valueOf( this.set_current_value ).replace( ",", set_separator ); 
		
	}

	@FieldMethod
	public void setSetOptions( final String separator ) throws FieldException {
		
		this.set_separator = separator;
		
	}
	
	@FieldMethod
	public void setSetStrategyFixed() throws FieldException {	
		
		this.set_current_value = this.set;
		
		this.cleanSetStrategyIncrement();
		
		this.cleanSetStrategyRandom();
		
	}
	
	@FieldMethod
	public void setSetStrategyFixed( final Set<String> values ) throws FieldException {	
							
		if( null != values ) {
			
			this.set_current_value =  new LinkedHashSet<String>();
			
			for( String value : values ) {
				
				if( set.contains( value ) ) { this.set_current_value.add( value  ); }
				
			}
			
		}
				
		this.cleanSetStrategyIncrement();
		
		this.cleanSetStrategyRandom();
		
	}
	
	@FieldMethod
	public void setSetStrategyFixed( final Enum<?>[] values ) throws FieldException {	
		
		if( null != values ) {
			
			set_current_value = new LinkedHashSet<String>();
			
			for( Enum<?> value : values ) {
									
				if( set.contains( value.name() ) ) {  set_current_value.add( value.name() ); }
					
			}
						
		}
				
		this.cleanSetStrategyIncrement();
		
		this.cleanSetStrategyRandom();
		
	}

	@FieldMethod
	public void setSetStrategyFixed( final String... values ) throws FieldException {	
		
		if( null != values ) {
			
			set_current_value = new LinkedHashSet<String>();
			
			for( String value : values ) {
									
				if( set.contains( value ) ) {  set_current_value.add( value ); }
					
			}
				
		}
		
		this.cleanSetStrategyIncrement();
		
		this.cleanSetStrategyRandom();
		
	}
	
	@FieldMethod
	public void setSetStrategyIncrement( final Enum<?> startValue, final Integer increment, final Integer range ) throws FieldException {	
		
		setSetStrategyIncrement( startValue.name(), increment, range );
		
	}
	
	@FieldMethod
	public void setSetStrategyIncrement( final String startValue, final Integer increment, final Integer range ) throws FieldException {	
		
		if( increment == null ) { throw new FieldException( "The field increment cannot be null." ); }
		
		Integer index = getIndex( startValue );
		
		if( index < 0 ) { throw new FieldException( "The value " + startValue + " is not in the set " + ( null != set ? set.toString() : "" ) + "." ); }
		
		this.set_curr_pos = index;
		
		this.set_increment = Math.abs( increment );
		
		this.set_range = Math.min( range, ( null != set ? set.size() : 0 ) );
		
		this.set_current_value = getSubSet( this.set_curr_pos );
		
		this.cleanSetStrategyIncrement();
		
		this.cleanSetStrategyRandom();
		
	}
	
	@FieldMethod
	public void setSetStrategyRandom() throws FieldException {
		
		this.cleanSetStrategyIncrement();
		
		this.set_random = true;
		
		this.set_range = ( null != set ? set.size() : 0 );
		
	}
	
	@FieldMethod
	public void setSetStrategyRandom( final Integer range ) throws FieldException {

		this.cleanSetStrategyIncrement();

		this.set_random = true;
		
		this.set_range = Math.min( range, ( null != set ? set.size() : 0 ) );
		
	}

	@FieldMethod
	public void cleanSetStrategyFixed() {
		
		this.set_current_value = null;
			
	}
	
	@FieldMethod
	public void cleanSetStrategyIncrement() {
		
		this.set_increment = null;
		
		this.set_next_value = null;
		
		this.set_range = null;
			
	}

	@FieldMethod
	public void cleanSetStrategyRandom() {
		
		this.set_random = false;
		
		this.set_range = null;
			
	}
	
	private int getIndex( String value ) {

		if( null == value || null == set ) { 
			return -1;
		}
		
		int index = 0;
		   
		for( String setValue : set ) {
		    
			if( setValue.equals( value ) ) return index;
		     
				index++;
		   
			}
		   
		return -1;
		 
	} 

	private Set<String> getSubSet( final int startPos ) {
		
		ArrayList<String> values = new ArrayList<String>( set );
		
		Set<String> subSet = new LinkedHashSet<String>();
		
		for( int i = startPos; i < startPos + this.set_range; i++ ) {
			
			int pos = i % values.size();
			
			subSet.add( values.get( pos ) );
			
		}
		
		return subSet;
				
	}
	
	private void setNextSet() {
		
		this.set_next_pos = ( ( this.set_curr_pos != null ? this.set_curr_pos : 0 ) + this.set_increment ) % this.set.size();
		
		this.set_next_value = getSubSet( this.set_next_pos );
		
	}
	
	private Set<String> generateRandomSet() {
		
		ArrayList<String> values = new ArrayList<String>( set );
		
		Set<String> subSet = new LinkedHashSet<String>();
		
		for( int i = 0; i < this.set_range; i++ ) {
			
			int pos = (int)( Math.random() * values.size() );
			
			pos = pos % set.size();
			
			subSet.add( values.get( pos ) );
			
		}
		
		return subSet;
										
	}
	
}
