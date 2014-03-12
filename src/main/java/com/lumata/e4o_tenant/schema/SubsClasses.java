package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "subs_classes" )
public class SubsClasses { 

	public enum Fields { msisdn, project_id, class_id, project_enrolment_date, class_update_date }

	@Column(
			table = "subs_classes",
			field = "msisdn",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "subs_classes",
			field = "project_id",
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
			getMethod = "getProjectId",
			setMethod = "setProjectId"
	)
	private Byte project_id;

	@Column(
			table = "subs_classes",
			field = "class_id",
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
			getMethod = "getClassId",
			setMethod = "setClassId"
	)
	private Byte class_id;

	@Column(
			table = "subs_classes",
			field = "project_enrolment_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getProjectEnrolmentDate",
			setMethod = "setProjectEnrolmentDate"
	)
	private Date project_enrolment_date;

	@Column(
			table = "subs_classes",
			field = "class_update_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getClassUpdateDate",
			setMethod = "setClassUpdateDate"
	)
	private Date class_update_date;


	public SubsClasses() {} 

	public SubsClasses( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( SubsClasses.Fields.msisdn.name() );
		this.project_id = rs.getByte( SubsClasses.Fields.project_id.name() );
		this.class_id = rs.getByte( SubsClasses.Fields.class_id.name() );
		this.project_enrolment_date = rs.getDate( SubsClasses.Fields.project_enrolment_date.name() );
		this.class_update_date = rs.getDate( SubsClasses.Fields.class_update_date.name() );

	}

	public SubsClasses( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( SubsClasses.Fields.msisdn.name() );
		this.project_id = (byte)jo.getInt( SubsClasses.Fields.project_id.name() );
		this.class_id = (byte)jo.getInt( SubsClasses.Fields.class_id.name() );
		this.project_enrolment_date = Format.getMysqlDateTime( jo.getString( SubsClasses.Fields.project_enrolment_date.name() ) );
		this.class_update_date = Format.getMysqlDateTime( jo.getString( SubsClasses.Fields.class_update_date.name() ) );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

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

	public Date getProjectEnrolmentDate() {

		return this.project_enrolment_date;

	}

	public void setProjectEnrolmentDate( Date project_enrolment_date ) {

		this.project_enrolment_date = project_enrolment_date;

	}

	public Date getClassUpdateDate() {

		return this.class_update_date;

	}

	public void setClassUpdateDate( Date class_update_date ) {

		this.class_update_date = class_update_date;

	}

	public Fields[] getEntityFields() {

		return SubsClasses.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"project_id\": \"" ).append( this.getProjectId() ).append( "\", " )
			.append( "\"class_id\": \"" ).append( this.getClassId() ).append( "\", " )
			.append( "\"project_enrolment_date\": \"" ).append( this.getProjectEnrolmentDate() ).append( "\", " )
			.append( "\"class_update_date\": \"" ).append( this.getClassUpdateDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }