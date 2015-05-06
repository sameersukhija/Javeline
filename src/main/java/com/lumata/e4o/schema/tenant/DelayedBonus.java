package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "delayed_bonus" )
public class DelayedBonus { 

	public enum Fields { message_id, message, queue_parameters, bonus_creation_date, max_expiration_timestamp }

	@Column(
			table = "delayed_bonus",
			field = "message_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getMessageId",
			setMethod = "setMessageId"
	)
	private Long message_id;

	@Column(
			table = "delayed_bonus",
			field = "message",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getMessage",
			setMethod = "setMessage"
	)
	private String message;

	@Column(
			table = "delayed_bonus",
			field = "queue_parameters",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getQueueParameters",
			setMethod = "setQueueParameters"
	)
	private String queue_parameters;

	@Column(
			table = "delayed_bonus",
			field = "bonus_creation_date",
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
			comment = "",
			getMethod = "getBonusCreationDate",
			setMethod = "setBonusCreationDate"
	)
	private Timestamp bonus_creation_date;

	@Column(
			table = "delayed_bonus",
			field = "max_expiration_timestamp",
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
			comment = "",
			getMethod = "getMaxExpirationTimestamp",
			setMethod = "setMaxExpirationTimestamp"
	)
	private Timestamp max_expiration_timestamp;


	public DelayedBonus() {} 

	public DelayedBonus( ResultSet rs ) throws SQLException {

		this.message_id = rs.getLong( DelayedBonus.Fields.message_id.name() );
		this.message = rs.getString( DelayedBonus.Fields.message.name() );
		this.queue_parameters = rs.getString( DelayedBonus.Fields.queue_parameters.name() );
		this.bonus_creation_date = rs.getTimestamp( DelayedBonus.Fields.bonus_creation_date.name() );
		this.max_expiration_timestamp = rs.getTimestamp( DelayedBonus.Fields.max_expiration_timestamp.name() );

	}

	public DelayedBonus( JSONObject jo ) throws JSONException, ParseException {

		this.message_id = (long)jo.getLong( DelayedBonus.Fields.message_id.name() );
		this.message = jo.getString( DelayedBonus.Fields.message.name() );
		this.queue_parameters = jo.getString( DelayedBonus.Fields.queue_parameters.name() );
		this.bonus_creation_date = new Timestamp( Format.getMysqlDateTime( jo.getString( DelayedBonus.Fields.bonus_creation_date.name() ) ).getTime() );
		this.max_expiration_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( DelayedBonus.Fields.max_expiration_timestamp.name() ) ).getTime() );

	}

	public Long getMessageId() {

		return this.message_id;

	}

	public DelayedBonus setMessageId( Long message_id ) {

		this.message_id = message_id;

		return this;

	}

	public String getMessage() {

		return this.message;

	}

	public DelayedBonus setMessage( String message ) {

		this.message = message;

		return this;

	}

	public String getQueueParameters() {

		return this.queue_parameters;

	}

	public DelayedBonus setQueueParameters( String queue_parameters ) {

		this.queue_parameters = queue_parameters;

		return this;

	}

	public Timestamp getBonusCreationDate() {

		return this.bonus_creation_date;

	}

	public DelayedBonus setBonusCreationDate( Timestamp bonus_creation_date ) {

		this.bonus_creation_date = bonus_creation_date;

		return this;

	}

	public Timestamp getMaxExpirationTimestamp() {

		return this.max_expiration_timestamp;

	}

	public DelayedBonus setMaxExpirationTimestamp( Timestamp max_expiration_timestamp ) {

		this.max_expiration_timestamp = max_expiration_timestamp;

		return this;

	}

	public Fields[] getEntityFields() {

		return DelayedBonus.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"message_id\": \"" ).append( this.getMessageId() ).append( "\", " )
			.append( "\"message\": \"" ).append( this.getMessage() ).append( "\", " )
			.append( "\"queue_parameters\": \"" ).append( this.getQueueParameters() ).append( "\", " )
			.append( "\"bonus_creation_date\": \"" ).append( this.getBonusCreationDate() ).append( "\", " )
			.append( "\"max_expiration_timestamp\": \"" ).append( this.getMaxExpirationTimestamp() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }