package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "files_data_backup" )
public class FilesDataBackup { 

	public enum Fields { id, backuptime, user, content }

	@Column(
			table = "files_data_backup",
			field = "id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;

	@Column(
			table = "files_data_backup",
			field = "backuptime",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getBackuptime",
			setMethod = "setBackuptime"
	)
	private Date backuptime;

	@Column(
			table = "files_data_backup",
			field = "user",
			type = "varchar(256)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 256,
			comment = "",
			getMethod = "getUser",
			setMethod = "setUser"
	)
	private String user;

	@Column(
			table = "files_data_backup",
			field = "content",
			type = "longblob",
			mysqlType = "longblob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getContent",
			setMethod = "setContent"
	)
	private String content;


	public FilesDataBackup() {} 

	public FilesDataBackup( ResultSet rs ) throws SQLException {

		this.id = rs.getInt( FilesDataBackup.Fields.id.name() );
		this.backuptime = rs.getDate( FilesDataBackup.Fields.backuptime.name() );
		this.user = rs.getString( FilesDataBackup.Fields.user.name() );
		this.content = rs.getString( FilesDataBackup.Fields.content.name() );

	}

	public FilesDataBackup( JSONObject jo ) throws JSONException, ParseException {

		this.id = (int)jo.getInt( FilesDataBackup.Fields.id.name() );
		this.backuptime = Format.getMysqlDateTime( jo.getString( FilesDataBackup.Fields.backuptime.name() ) );
		this.user = jo.getString( FilesDataBackup.Fields.user.name() );
		this.content = jo.getString( FilesDataBackup.Fields.content.name() );

	}

	public Integer getId() {

		return this.id;

	}

	public void setId( Integer id ) {

		this.id = id;

	}

	public Date getBackuptime() {

		return this.backuptime;

	}

	public void setBackuptime( Date backuptime ) {

		this.backuptime = backuptime;

	}

	public String getUser() {

		return this.user;

	}

	public void setUser( String user ) {

		this.user = user;

	}

	public String getContent() {

		return this.content;

	}

	public void setContent( String content ) {

		this.content = content;

	}

	public Fields[] getEntityFields() {

		return FilesDataBackup.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"backuptime\": \"" ).append( this.getBackuptime() ).append( "\", " )
			.append( "\"user\": \"" ).append( this.getUser() ).append( "\", " )
			.append( "\"content\": \"" ).append( this.getContent() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }