package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "files_data_backup" )
public class FilesDataBackup { 

	public enum Fields { id, backuptime, content }

	@Column(
			table = "files_data_backup",
			field = "id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;

	@Column(
			table = "files_data_backup",
			field = "backuptime",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			getMethod = "getBackuptime",
			setMethod = "setBackuptime"
	)
	private Timestamp backuptime;

	@Column(
			table = "files_data_backup",
			field = "content",
			type = "longblob",
			mysqlType = "longblob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getContent",
			setMethod = "setContent"
	)
	private String content;


	public FilesDataBackup() {} 

	public FilesDataBackup( ResultSet rs ) throws SQLException {

		this.id = rs.getInt( FilesDataBackup.Fields.id.name() );
		this.backuptime = rs.getTimestamp( FilesDataBackup.Fields.backuptime.name() );
		this.content = rs.getString( FilesDataBackup.Fields.content.name() );

	}

	public FilesDataBackup( JSONObject jo ) throws JSONException, ParseException {

		this.id = (int)jo.getInt( FilesDataBackup.Fields.id.name() );
		this.backuptime = new Timestamp( Format.getMysqlDateTime( jo.getString( FilesDataBackup.Fields.backuptime.name() ) ).getTime() );
		this.content = jo.getString( FilesDataBackup.Fields.content.name() );

	}

	public Integer getId() {

		return this.id;

	}

	public void setId( Integer id ) {

		this.id = id;

	}

	public Timestamp getBackuptime() {

		return this.backuptime;

	}

	public void setBackuptime( Timestamp backuptime ) {

		this.backuptime = backuptime;

	}

	public String getContent() {

		return this.content;

	}

	public void setContent( String content ) {

		this.content = content;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"backuptime\": \"" ).append( this.getBackuptime() ).append( "\", " )
			.append( "\"content\": \"" ).append( this.getContent() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }