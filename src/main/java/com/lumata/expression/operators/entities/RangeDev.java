package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "range_dev" )
public class RangeDev { 

	public enum Fields { var_name, profile_id, range_id, range_name, fromval, toval }

	@Column(
			table = "range_dev",
			field = "var_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getVarName",
			setMethod = "setVarName"
	)
	private String var_name;

	@Column(
			table = "range_dev",
			field = "profile_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "range_dev",
			field = "range_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getRangeId",
			setMethod = "setRangeId"
	)
	private Byte range_id;

	@Column(
			table = "range_dev",
			field = "range_name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getRangeName",
			setMethod = "setRangeName"
	)
	private String range_name;

	@Column(
			table = "range_dev",
			field = "fromval",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getFromval",
			setMethod = "setFromval"
	)
	private Integer fromval;

	@Column(
			table = "range_dev",
			field = "toval",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getToval",
			setMethod = "setToval"
	)
	private Integer toval;


	public RangeDev() {} 

	public RangeDev( ResultSet rs ) throws SQLException {

		this.var_name = rs.getString( RangeDev.Fields.var_name.name() );
		this.profile_id = rs.getByte( RangeDev.Fields.profile_id.name() );
		this.range_id = rs.getByte( RangeDev.Fields.range_id.name() );
		this.range_name = rs.getString( RangeDev.Fields.range_name.name() );
		this.fromval = rs.getInt( RangeDev.Fields.fromval.name() );
		this.toval = rs.getInt( RangeDev.Fields.toval.name() );

	}

	public RangeDev( JSONObject jo ) throws JSONException {

		this.var_name = jo.getString( RangeDev.Fields.var_name.name() );
		this.profile_id = (byte)jo.getInt( RangeDev.Fields.profile_id.name() );
		this.range_id = (byte)jo.getInt( RangeDev.Fields.range_id.name() );
		this.range_name = jo.getString( RangeDev.Fields.range_name.name() );
		this.fromval = (int)jo.getInt( RangeDev.Fields.fromval.name() );
		this.toval = (int)jo.getInt( RangeDev.Fields.toval.name() );

	}

	public String getVarName() {

		return this.var_name;

	}

	public void setVarName( String var_name ) {

		this.var_name = var_name;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public Byte getRangeId() {

		return this.range_id;

	}

	public void setRangeId( Byte range_id ) {

		this.range_id = range_id;

	}

	public String getRangeName() {

		return this.range_name;

	}

	public void setRangeName( String range_name ) {

		this.range_name = range_name;

	}

	public Integer getFromval() {

		return this.fromval;

	}

	public void setFromval( Integer fromval ) {

		this.fromval = fromval;

	}

	public Integer getToval() {

		return this.toval;

	}

	public void setToval( Integer toval ) {

		this.toval = toval;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"var_name\": \"" ).append( this.getVarName() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"range_id\": \"" ).append( this.getRangeId() ).append( "\", " )
			.append( "\"range_name\": \"" ).append( this.getRangeName() ).append( "\", " )
			.append( "\"fromval\": \"" ).append( this.getFromval() ).append( "\", " )
			.append( "\"toval\": \"" ).append( this.getToval() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }