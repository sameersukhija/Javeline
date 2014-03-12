package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "agrp_caps" )
public class AgrpCaps { 

	public enum Fields { cap, transaction_id, task_name, creation_time, completion_date }

	@Column(
			table = "agrp_caps",
			field = "cap",
			type = "char(56)",
			mysqlType = "char",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 56,
			getMethod = "getCap",
			setMethod = "setCap"
	)
	private String cap;

	@Column(
			table = "agrp_caps",
			field = "transaction_id",
			type = "char(56)",
			mysqlType = "char",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 56,
			getMethod = "getTransactionId",
			setMethod = "setTransactionId"
	)
	private String transaction_id;

	@Column(
			table = "agrp_caps",
			field = "task_name",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getTaskName",
			setMethod = "setTaskName"
	)
	private String task_name;

	@Column(
			table = "agrp_caps",
			field = "creation_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "",
			length = 0,
			getMethod = "getCreationTime",
			setMethod = "setCreationTime"
	)
	private Timestamp creation_time;

	@Column(
			table = "agrp_caps",
			field = "completion_date",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getCompletionDate",
			setMethod = "setCompletionDate"
	)
	private Timestamp completion_date;


	public AgrpCaps() {} 

	public AgrpCaps( ResultSet rs ) throws SQLException {

		this.cap = rs.getString( AgrpCaps.Fields.cap.name() );
		this.transaction_id = rs.getString( AgrpCaps.Fields.transaction_id.name() );
		this.task_name = rs.getString( AgrpCaps.Fields.task_name.name() );
		this.creation_time = rs.getTimestamp( AgrpCaps.Fields.creation_time.name() );
		this.completion_date = rs.getTimestamp( AgrpCaps.Fields.completion_date.name() );

	}

	public AgrpCaps( JSONObject jo ) throws JSONException, ParseException {

		this.cap = jo.getString( AgrpCaps.Fields.cap.name() );
		this.transaction_id = jo.getString( AgrpCaps.Fields.transaction_id.name() );
		this.task_name = jo.getString( AgrpCaps.Fields.task_name.name() );
		this.creation_time = new Timestamp( Format.getMysqlDateTime( jo.getString( AgrpCaps.Fields.creation_time.name() ) ).getTime() );
		this.completion_date = new Timestamp( Format.getMysqlDateTime( jo.getString( AgrpCaps.Fields.completion_date.name() ) ).getTime() );

	}

	public String getCap() {

		return this.cap;

	}

	public void setCap( String cap ) {

		this.cap = cap;

	}

	public String getTransactionId() {

		return this.transaction_id;

	}

	public void setTransactionId( String transaction_id ) {

		this.transaction_id = transaction_id;

	}

	public String getTaskName() {

		return this.task_name;

	}

	public void setTaskName( String task_name ) {

		this.task_name = task_name;

	}

	public Timestamp getCreationTime() {

		return this.creation_time;

	}

	public void setCreationTime( Timestamp creation_time ) {

		this.creation_time = creation_time;

	}

	public Timestamp getCompletionDate() {

		return this.completion_date;

	}

	public void setCompletionDate( Timestamp completion_date ) {

		this.completion_date = completion_date;

	}

	public Fields[] getEntityFields() {

		return AgrpCaps.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"cap\": \"" ).append( this.getCap() ).append( "\", " )
			.append( "\"transaction_id\": \"" ).append( this.getTransactionId() ).append( "\", " )
			.append( "\"task_name\": \"" ).append( this.getTaskName() ).append( "\", " )
			.append( "\"creation_time\": \"" ).append( this.getCreationTime() ).append( "\", " )
			.append( "\"completion_date\": \"" ).append( this.getCompletionDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }