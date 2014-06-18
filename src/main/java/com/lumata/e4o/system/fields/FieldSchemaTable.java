package com.lumata.e4o.system.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.distinct;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.system.field.types.FieldMethod;
import com.lumata.e4o.system.field.types.FieldTypeSchemaTable;

@FieldTypeSchemaTable
public class FieldSchemaTable {

	private ArrayList<String> table_values;
	private JSONObject ds;
	private Object table;
	private Enum<?> table_field;
	private String table_current_value;
	private Integer table_curr_row;
	private String table_next_value;
	private Integer table_next_row;
	private Integer table_increment_value;
	private Boolean table_random_value;
		
	public FieldSchemaTable( final Object entity, final Enum<?> field ) {
					
		ds = null;
		table = entity;
		table_field = field;
		table_values = null;
		table_current_value = null;
		table_random_value = false;
		table_next_value = null;
		table_curr_row = null;
		table_next_row = null;
		table_increment_value = null;
						
	}

	@FieldMethod
	public String getSchemaTable() throws CDRException {
						
		if( this.table_current_value == null && this.table_random_value == false ) { return ""; } 
		else {
			
			if( this.table_next_value != null && this.table_increment_value != null ) {
				
				this.table_current_value = this.table_next_value;
				
				this.table_curr_row = this.table_next_row;
				
				this.setNextSchemaTable();
				
			} else {
				
				if( this.table_increment_value != null ) {
					
					this.setNextSchemaTable();
					
				} else {
					
					if( this.table_random_value ) {
						
						this.generateRandomSchemaTable();
						
					}
					
				}
				
			}			
			
		}
		
		return this.table_current_value; 
		
	}

	@FieldMethod
	public void setSchemaTableValues( final JSONObject dataSource ) throws CDRException {
		
		ds = dataSource;
				
		this.loadSchemaTable();
		
	}
	
	@FieldMethod
	public void setSchemaTableStrategyFixed( final Integer row ) throws CDRException {	
		
		if( row >= this.table_values.size() ) { throw new CDRException( "The row is greater than table size" ); }

		this.cleanSchemaTableStrategyIncrement();
		
		this.cleanSchemaTableStrategyRandom();
		
		this.table_current_value = this.table_values.get( row );
		
		this.table_curr_row = row;
		
	}
	
	
	public void setSchemaTableStrategyFixed( final String field_value ) throws CDRException {	
		
		this.cleanSchemaTableStrategyIncrement();
		
		this.cleanSchemaTableStrategyRandom();
		
		this.setSchemaTableCurrentValue( field_value );
		
	}
		
	@FieldMethod
	public void setSchemaTableStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException {
		
		if( start_row >= this.table_values.size() ) { throw new CDRException( "The start_row is greater than table size" ); }
		
		if( increment == null ) { throw new CDRException( "The field increment cannot be null." ); }
		
		this.cleanSchemaTableStrategyRandom();
		
		this.table_current_value = this.table_values.get( start_row );
		
		this.table_curr_row = start_row;
		
		this.table_increment_value = Math.abs( increment );
		
	}
	
	@FieldMethod
	public void setSchemaTableStrategyRandom() throws CDRException {
		
		this.cleanSchemaTableStrategyIncrement();
		
		this.table_random_value = true;
		
	}
	
	@FieldMethod
	public void cleanSchemaTableOptions() {
		
		this.ds = null;
		
		this.table = null;
		
		this.table_field = null;
			
	}
	
	@FieldMethod
	public void cleanSchemaTableStrategyFixed() {
		
		this.table_current_value = null;
		
		this.table_curr_row = null;
			
	}
		
	@FieldMethod
	public void cleanSchemaTableStrategyIncrement() {
		
		this.cleanSchemaTableStrategyFixed();
		
		this.table_next_value = null;
		
		this.table_increment_value = null;
		
	}
	
	@FieldMethod
	public void cleanSchemaTableStrategyRandom() {
		
		this.cleanSchemaTableStrategyFixed();
		
		this.table_random_value = false;
		
	}

	private void generateRandomSchemaTable() {
		
		this.table_curr_row = (int)( Math.random() * this.table_values.size() );
			
		this.table_current_value = this.table_values.get( this.table_curr_row );
								
	}
	
	private void setNextSchemaTable() {
		
		this.table_next_row = ( ( this.table_curr_row != null ? this.table_curr_row : 0 ) + this.table_increment_value ) % this.table_values.size();
		
		this.table_next_value = this.table_values.get( this.table_next_row );
		
	}
	
	private void setSchemaTableCurrentValue( final String field_value ) {
		
		this.table_current_value = field_value;
		
		for( int i = 0; i < this.table_values.size(); i++ ) {
			
			if( this.table_values.get( i ).equals( field_value ) ) {
				
				this.table_curr_row = i;
				
				break;
				
			}
			
		}
		
	}
	
	private void loadSchemaTable() throws CDRException {
		
		if( this.table.equals( null ) ) { throw new CDRException( "The schema table is not valid." ); }
		
		if( this.table_field == null ) { throw new CDRException( "The table field is not valid." ); }
		
		if( this.ds == null ) { throw new CDRException( "The data source is not valid." ); }
		
		String query = select( distinct( this.table_field ) ).from( this.table ).build();
		
		Mysql mysql = new Mysql( this.ds );
						
		ResultSet rs = mysql.execQuery( query );
		
		this.table_values = new ArrayList<String>();
		
		try {
			
			while( rs.next() ) {
				
				this.table_values.add( rs.getString( this.table_field.name() ) );
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
