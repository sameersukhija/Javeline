package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_mail_feedback" )
public class DmMailFeedback { 

	public enum Fields { file_name, message_id }

	@Column(
			table = "dm_mail_feedback",
			field = "file_name",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getFileName",
			setMethod = "setFileName"
	)
	private String file_name;

	@Column(
			table = "dm_mail_feedback",
			field = "message_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getMessageId",
			setMethod = "setMessageId"
	)
	private String message_id;


	public DmMailFeedback() {} 

	public DmMailFeedback( ResultSet rs ) throws SQLException {

		this.file_name = rs.getString( DmMailFeedback.Fields.file_name.name() );
		this.message_id = rs.getString( DmMailFeedback.Fields.message_id.name() );

	}

	public DmMailFeedback( JSONObject jo ) throws JSONException {

		this.file_name = jo.getString( DmMailFeedback.Fields.file_name.name() );
		this.message_id = jo.getString( DmMailFeedback.Fields.message_id.name() );

	}

	public String getFileName() {

		return this.file_name;

	}

	public void setFileName( String file_name ) {

		this.file_name = file_name;

	}

	public String getMessageId() {

		return this.message_id;

	}

	public void setMessageId( String message_id ) {

		this.message_id = message_id;

	}

	public Fields[] getEntityFields() {

		return DmMailFeedback.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"file_name\": \"" ).append( this.getFileName() ).append( "\", " )
			.append( "\"message_id\": \"" ).append( this.getMessageId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }