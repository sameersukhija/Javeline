package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "members_all" )
public class MembersAll { 

	public enum Fields { agg_date, period, project_id, class_id, qty_msisdn }

	@Column(
			table = "members_all",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;

	@Column(
			table = "members_all",
			field = "period",
			type = "enum('day','week','month')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getPeriod",
			setMethod = "setPeriod"
	)
	private String period;

	@Column(
			table = "members_all",
			field = "project_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getProjectId",
			setMethod = "setProjectId"
	)
	private Byte project_id;

	@Column(
			table = "members_all",
			field = "class_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getClassId",
			setMethod = "setClassId"
	)
	private Byte class_id;

	@Column(
			table = "members_all",
			field = "qty_msisdn",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyMsisdn",
			setMethod = "setQtyMsisdn"
	)
	private Integer qty_msisdn;


	public MembersAll() {} 

	public MembersAll( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( MembersAll.Fields.agg_date.name() );
		this.period = rs.getString( MembersAll.Fields.period.name() );
		this.project_id = rs.getByte( MembersAll.Fields.project_id.name() );
		this.class_id = rs.getByte( MembersAll.Fields.class_id.name() );
		this.qty_msisdn = rs.getInt( MembersAll.Fields.qty_msisdn.name() );

	}

	public MembersAll( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( MembersAll.Fields.agg_date.name() ) );
		this.period = jo.getString( MembersAll.Fields.period.name() );
		this.project_id = (byte)jo.getInt( MembersAll.Fields.project_id.name() );
		this.class_id = (byte)jo.getInt( MembersAll.Fields.class_id.name() );
		this.qty_msisdn = (int)jo.getInt( MembersAll.Fields.qty_msisdn.name() );

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public String getPeriod() {

		return this.period;

	}

	public void setPeriod( String period ) {

		this.period = period;

	}

	public Byte getProjectId() {

		return this.project_id;

	}

	public void setProjectId( Byte project_id ) {

		this.project_id = project_id;

	}

	public Byte getClassId() {

		return this.class_id;

	}

	public void setClassId( Byte class_id ) {

		this.class_id = class_id;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public void setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"period\": \"" ).append( this.getPeriod() ).append( "\", " )
			.append( "\"project_id\": \"" ).append( this.getProjectId() ).append( "\", " )
			.append( "\"class_id\": \"" ).append( this.getClassId() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }