package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "billables" )
public class Billables { 

	public enum Fields { billable_id, billable_name, commodity_type, bonus_id }

	@Column(
			table = "billables",
			field = "billable_id",
			type = "smallint(3)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getBillableId",
			setMethod = "setBillableId"
	)
	private Short billable_id;

	@Column(
			table = "billables",
			field = "billable_name",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 30,
			getMethod = "getBillableName",
			setMethod = "setBillableName"
	)
	private String billable_name;

	@Column(
			table = "billables",
			field = "commodity_type",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 15,
			getMethod = "getCommodityType",
			setMethod = "setCommodityType"
	)
	private String commodity_type;

	@Column(
			table = "billables",
			field = "bonus_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getBonusId",
			setMethod = "setBonusId"
	)
	private Short bonus_id;


	public Billables() {} 

	public Billables( ResultSet rs ) throws SQLException {

		this.billable_id = rs.getShort( Billables.Fields.billable_id.name() );
		this.billable_name = rs.getString( Billables.Fields.billable_name.name() );
		this.commodity_type = rs.getString( Billables.Fields.commodity_type.name() );
		this.bonus_id = rs.getShort( Billables.Fields.bonus_id.name() );

	}

	public Billables( JSONObject jo ) throws JSONException {

		this.billable_id = (short)jo.getInt( Billables.Fields.billable_id.name() );
		this.billable_name = jo.getString( Billables.Fields.billable_name.name() );
		this.commodity_type = jo.getString( Billables.Fields.commodity_type.name() );
		this.bonus_id = (short)jo.getInt( Billables.Fields.bonus_id.name() );

	}

	public Short getBillableId() {

		return this.billable_id;

	}

	public void setBillableId( Short billable_id ) {

		this.billable_id = billable_id;

	}

	public String getBillableName() {

		return this.billable_name;

	}

	public void setBillableName( String billable_name ) {

		this.billable_name = billable_name;

	}

	public String getCommodityType() {

		return this.commodity_type;

	}

	public void setCommodityType( String commodity_type ) {

		this.commodity_type = commodity_type;

	}

	public Short getBonusId() {

		return this.bonus_id;

	}

	public void setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"billable_id\": \"" ).append( this.getBillableId() ).append( "\", " )
			.append( "\"billable_name\": \"" ).append( this.getBillableName() ).append( "\", " )
			.append( "\"commodity_type\": \"" ).append( this.getCommodityType() ).append( "\", " )
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }