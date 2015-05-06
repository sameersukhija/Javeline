package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "range_pre_prod" )
public class RangePreProd { 

	public enum Fields { var_name, profile_id, range_id, range_name, fromval, toval }

	@Column(
			table = "range_pre_prod",
			field = "var_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getVarName",
			setMethod = "setVarName"
	)
	private String var_name;

	@Column(
			table = "range_pre_prod",
			field = "profile_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "range_pre_prod",
			field = "range_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getRangeId",
			setMethod = "setRangeId"
	)
	private Byte range_id;

	@Column(
			table = "range_pre_prod",
			field = "range_name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getRangeName",
			setMethod = "setRangeName"
	)
	private String range_name;

	@Column(
			table = "range_pre_prod",
			field = "fromval",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getFromval",
			setMethod = "setFromval"
	)
	private Integer fromval;

	@Column(
			table = "range_pre_prod",
			field = "toval",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getToval",
			setMethod = "setToval"
	)
	private Integer toval;


	public RangePreProd() {} 

	public RangePreProd( ResultSet rs ) throws SQLException {

		this.var_name = rs.getString( RangePreProd.Fields.var_name.name() );
		this.profile_id = rs.getByte( RangePreProd.Fields.profile_id.name() );
		this.range_id = rs.getByte( RangePreProd.Fields.range_id.name() );
		this.range_name = rs.getString( RangePreProd.Fields.range_name.name() );
		this.fromval = rs.getInt( RangePreProd.Fields.fromval.name() );
		this.toval = rs.getInt( RangePreProd.Fields.toval.name() );

	}

	public RangePreProd( JSONObject jo ) throws JSONException {

		this.var_name = jo.getString( RangePreProd.Fields.var_name.name() );
		this.profile_id = (byte)jo.getInt( RangePreProd.Fields.profile_id.name() );
		this.range_id = (byte)jo.getInt( RangePreProd.Fields.range_id.name() );
		this.range_name = jo.getString( RangePreProd.Fields.range_name.name() );
		this.fromval = (int)jo.getInt( RangePreProd.Fields.fromval.name() );
		this.toval = (int)jo.getInt( RangePreProd.Fields.toval.name() );

	}

	public String getVarName() {

		return this.var_name;

	}

	public RangePreProd setVarName( String var_name ) {

		this.var_name = var_name;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public RangePreProd setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getRangeId() {

		return this.range_id;

	}

	public RangePreProd setRangeId( Byte range_id ) {

		this.range_id = range_id;

		return this;

	}

	public String getRangeName() {

		return this.range_name;

	}

	public RangePreProd setRangeName( String range_name ) {

		this.range_name = range_name;

		return this;

	}

	public Integer getFromval() {

		return this.fromval;

	}

	public RangePreProd setFromval( Integer fromval ) {

		this.fromval = fromval;

		return this;

	}

	public Integer getToval() {

		return this.toval;

	}

	public RangePreProd setToval( Integer toval ) {

		this.toval = toval;

		return this;

	}

	public Fields[] getEntityFields() {

		return RangePreProd.Fields.values();

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