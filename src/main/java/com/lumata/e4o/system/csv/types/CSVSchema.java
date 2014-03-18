package com.lumata.e4o.system.csv.types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import static com.lumata.common.testing.orm.Query.select;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.exceptions.CDRException;

public class CSVSchema {

	private ArrayList<String> table_values;
	private JSONObject ds;
	private Object table;
	private Enum<?> table_field;
	private String table_current_value;
	private Integer table_curr_pos;
	private String table_next_value;
	private Integer table_next_pos;
	private Integer table_increment_value;
	private Boolean table_random_value;
		
	public CSVSchema( JSONObject dataSource, Object entity, Enum<?> field ) throws CDRException {
					
		ds = dataSource;
		table = entity;
		table_field = field;
		table_values = null;
		table_current_value = null;
		table_random_value = false;
		table_next_value = null;
		table_curr_pos = null;
		table_next_pos = null;
		table_increment_value = null;
		
		this.loadTable();
						
	}

	public String getValue() throws CDRException {
						
		if( this.table_current_value == null && this.table_random_value == false ) { return ""; } 
		else {
			
			if( this.table_next_value != null && this.table_increment_value != null ) {
				
				this.table_current_value = this.table_next_value;
				
				this.table_curr_pos = this.table_next_pos;
				
				this.setNextTable();
				
			} else {
				
				if( this.table_increment_value != null ) {
					
					this.setNextTable();
					
				} else {
					
					if( this.table_random_value ) {
						
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
		
		this.table_increment_value = Math.abs( increment );
		
		this.cleanTableStrategyRandom();
		
	}
	
	public void setTableStrategyRandom() throws CDRException {
		
		this.table_random_value = true;
		
		this.cleanTableStrategyIncrement();
		
	}
	
	public void cleanTableStrategyFixed() {
		
		this.table_current_value = null;
			
	}
		
	public void cleanTableStrategyIncrement() {
		
		this.table_increment_value = null;
		
		this.table_next_value = null;
		
	}
	
	public void cleanTableStrategyRandom() {
		
		this.table_random_value = false;
		
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
	
	private void loadTable() throws CDRException {
		
		if( this.table == null ) { throw new CDRException( "The schema table is not valid." ); }
		
		if( this.table_field == null ) { throw new CDRException( "The table field is not valid." ); }
		
		if( this.ds == null ) { throw new CDRException( "The data source is not valid." ); }
		
		String query = select( this.table_field ).from( this.table ).build();
		
		Mysql mysql = new Mysql( this.ds );
						
		ResultSet rs = mysql.execQuery( query );
		
		try {
			while( rs.next() ) {
				this.table_values.add( rs.getString( this.table_field.name() ) );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
