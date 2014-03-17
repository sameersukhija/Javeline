package com.lumata.e4o.system.csv.types;

import java.util.ArrayList;

import com.lumata.expression.operators.exceptions.CDRException;

public class CSVSchema {

	private ArrayList<String> values;
	private String table_current_value;
	private Integer table_curr_pos;
	private String table_next_value;
	private Integer table_next_pos;
	private Integer table_increment;
	private Boolean table_random;
		
	public CSVSchema( Object entity ) {
			
		
		table_current_value = null;
		table_random = false;
		table_next_value = null;
		table_next_pos = null;
		table_increment = null;
						
	}

	public String getTable() throws CDRException {
						
		if( this.table_current_value == null && this.table_random == false ) { return ""; } 
		else {
			
			if( this.table_next_value != null && this.table_increment != null ) {
				
				this.table_current_value = this.table_next_value;
				
				this.table_curr_pos = this.table_next_pos;
				
				this.setNextTable();
				
			} else {
				
				if( this.table_increment != null ) {
					
					this.setNextTable();
					
				} else {
					
					if( this.table_random ) {
						
						this.table_current_value = this.generateRandomTable();
						
					}
					
				}
				
			}			
			
		}
		
		return String.valueOf( this.table_current_value ); 
		
	}

	public void setTableStrategyFixed( /*final Table<? extends ICSVTable> value*/ ) throws CDRException {	
		
		//this.table_current_value = ( value != null && tableeration != null ? ((ICSVTable)value).value() : null );
		
		this.cleanTableStrategyIncrement();
		
		this.cleanTableStrategyRandom();
		
	}
	
	public void setTableStrategyIncrement( /*final Table<? extends ICSVTable> value,*/ final Integer increment ) throws CDRException {
		
		//if( value == null ) { throw new CDRException( "The field cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The field increment cannot be null." ); }
		
		//this.table_current_value = ((ICSVTable)value).value();
		
		this.table_increment = Math.abs( increment );
		
		this.cleanTableStrategyRandom();
		
	}
	
	public void setTableStrategyRandom() throws CDRException {
		
		this.table_random = true;
		
		this.cleanTableStrategyIncrement();
		
	}
	
	public void cleanTableStrategyFixed() {
		
		this.table_current_value = null;
			
	}
		
	public void cleanTableStrategyIncrement() {
		
		this.table_increment = null;
		
		this.table_next_value = null;
		
	}
	
	public void cleanTableStrategyRandom() {
		
		this.table_random = false;
		
	}

	private String generateRandomTable() {
		
		//int table_pos = (int)( Math.random() * tableeration.length );
				
		return "";//((ICSVTable)tableeration[table_pos]).value();
								
	}
	
	private int getTablePosition() {
		
		return 0;
		
	}
	
	private void setNextTable() {
		
		//this.table_next_pos = ( ( this.table_curr_pos != null ? this.table_curr_pos : 0 ) + this.table_increment ) % this.tableeration.length;
		
		//this.table_next_value = ((ICSVTable)this.tableeration[ this.table_next_pos ]).value();
		
	}
	
	private void loadTable( Object entity ) {
		
		
		
	}
	
}
