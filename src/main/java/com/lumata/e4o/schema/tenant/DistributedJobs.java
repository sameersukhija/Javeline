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


@Table( "distributed_jobs" )
public class DistributedJobs { 

	public enum Fields { name, handler, assigner_id, assigner_name, piece, pieces, part, current_pos, status, message, attempts, creation_date, assigned_date, update_date }

	@Column(
			table = "distributed_jobs",
			field = "name",
			type = "varchar(200)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 200,
			comment = "",
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "distributed_jobs",
			field = "handler",
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
			getMethod = "getHandler",
			setMethod = "setHandler"
	)
	private String handler;

	@Column(
			table = "distributed_jobs",
			field = "assigner_id",
			type = "varchar(10)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getAssignerId",
			setMethod = "setAssignerId"
	)
	private String assigner_id;

	@Column(
			table = "distributed_jobs",
			field = "assigner_name",
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
			comment = "",
			getMethod = "getAssignerName",
			setMethod = "setAssignerName"
	)
	private String assigner_name;

	@Column(
			table = "distributed_jobs",
			field = "piece",
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
			getMethod = "getPiece",
			setMethod = "setPiece"
	)
	private Integer piece;

	@Column(
			table = "distributed_jobs",
			field = "pieces",
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
			getMethod = "getPieces",
			setMethod = "setPieces"
	)
	private Integer pieces;

	@Column(
			table = "distributed_jobs",
			field = "part",
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
			getMethod = "getPart",
			setMethod = "setPart"
	)
	private String part;

	@Column(
			table = "distributed_jobs",
			field = "current_pos",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getCurrentPos",
			setMethod = "setCurrentPos"
	)
	private Long current_pos;

	@Column(
			table = "distributed_jobs",
			field = "status",
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
			getMethod = "getStatus",
			setMethod = "setStatus"
	)
	private String status;

	@Column(
			table = "distributed_jobs",
			field = "message",
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
			getMethod = "getMessage",
			setMethod = "setMessage"
	)
	private String message;

	@Column(
			table = "distributed_jobs",
			field = "attempts",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getAttempts",
			setMethod = "setAttempts"
	)
	private Byte attempts;

	@Column(
			table = "distributed_jobs",
			field = "creation_date",
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
			getMethod = "getCreationDate",
			setMethod = "setCreationDate"
	)
	private Timestamp creation_date;

	@Column(
			table = "distributed_jobs",
			field = "assigned_date",
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
			getMethod = "getAssignedDate",
			setMethod = "setAssignedDate"
	)
	private Timestamp assigned_date;

	@Column(
			table = "distributed_jobs",
			field = "update_date",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			comment = "",
			getMethod = "getUpdateDate",
			setMethod = "setUpdateDate"
	)
	private Timestamp update_date;


	public DistributedJobs() {} 

	public DistributedJobs( ResultSet rs ) throws SQLException {

		this.name = rs.getString( DistributedJobs.Fields.name.name() );
		this.handler = rs.getString( DistributedJobs.Fields.handler.name() );
		this.assigner_id = rs.getString( DistributedJobs.Fields.assigner_id.name() );
		this.assigner_name = rs.getString( DistributedJobs.Fields.assigner_name.name() );
		this.piece = rs.getInt( DistributedJobs.Fields.piece.name() );
		this.pieces = rs.getInt( DistributedJobs.Fields.pieces.name() );
		this.part = rs.getString( DistributedJobs.Fields.part.name() );
		this.current_pos = rs.getLong( DistributedJobs.Fields.current_pos.name() );
		this.status = rs.getString( DistributedJobs.Fields.status.name() );
		this.message = rs.getString( DistributedJobs.Fields.message.name() );
		this.attempts = rs.getByte( DistributedJobs.Fields.attempts.name() );
		this.creation_date = rs.getTimestamp( DistributedJobs.Fields.creation_date.name() );
		this.assigned_date = rs.getTimestamp( DistributedJobs.Fields.assigned_date.name() );
		this.update_date = rs.getTimestamp( DistributedJobs.Fields.update_date.name() );

	}

	public DistributedJobs( JSONObject jo ) throws JSONException, ParseException {

		this.name = jo.getString( DistributedJobs.Fields.name.name() );
		this.handler = jo.getString( DistributedJobs.Fields.handler.name() );
		this.assigner_id = jo.getString( DistributedJobs.Fields.assigner_id.name() );
		this.assigner_name = jo.getString( DistributedJobs.Fields.assigner_name.name() );
		this.piece = (int)jo.getInt( DistributedJobs.Fields.piece.name() );
		this.pieces = (int)jo.getInt( DistributedJobs.Fields.pieces.name() );
		this.part = jo.getString( DistributedJobs.Fields.part.name() );
		this.current_pos = (long)jo.getLong( DistributedJobs.Fields.current_pos.name() );
		this.status = jo.getString( DistributedJobs.Fields.status.name() );
		this.message = jo.getString( DistributedJobs.Fields.message.name() );
		this.attempts = (byte)jo.getInt( DistributedJobs.Fields.attempts.name() );
		this.creation_date = new Timestamp( Format.getMysqlDateTime( jo.getString( DistributedJobs.Fields.creation_date.name() ) ).getTime() );
		this.assigned_date = new Timestamp( Format.getMysqlDateTime( jo.getString( DistributedJobs.Fields.assigned_date.name() ) ).getTime() );
		this.update_date = new Timestamp( Format.getMysqlDateTime( jo.getString( DistributedJobs.Fields.update_date.name() ) ).getTime() );

	}

	public String getName() {

		return this.name;

	}

	public DistributedJobs setName( String name ) {

		this.name = name;

		return this;

	}

	public String getHandler() {

		return this.handler;

	}

	public DistributedJobs setHandler( String handler ) {

		this.handler = handler;

		return this;

	}

	public String getAssignerId() {

		return this.assigner_id;

	}

	public DistributedJobs setAssignerId( String assigner_id ) {

		this.assigner_id = assigner_id;

		return this;

	}

	public String getAssignerName() {

		return this.assigner_name;

	}

	public DistributedJobs setAssignerName( String assigner_name ) {

		this.assigner_name = assigner_name;

		return this;

	}

	public Integer getPiece() {

		return this.piece;

	}

	public DistributedJobs setPiece( Integer piece ) {

		this.piece = piece;

		return this;

	}

	public Integer getPieces() {

		return this.pieces;

	}

	public DistributedJobs setPieces( Integer pieces ) {

		this.pieces = pieces;

		return this;

	}

	public String getPart() {

		return this.part;

	}

	public DistributedJobs setPart( String part ) {

		this.part = part;

		return this;

	}

	public Long getCurrentPos() {

		return this.current_pos;

	}

	public DistributedJobs setCurrentPos( Long current_pos ) {

		this.current_pos = current_pos;

		return this;

	}

	public String getStatus() {

		return this.status;

	}

	public DistributedJobs setStatus( String status ) {

		this.status = status;

		return this;

	}

	public String getMessage() {

		return this.message;

	}

	public DistributedJobs setMessage( String message ) {

		this.message = message;

		return this;

	}

	public Byte getAttempts() {

		return this.attempts;

	}

	public DistributedJobs setAttempts( Byte attempts ) {

		this.attempts = attempts;

		return this;

	}

	public Timestamp getCreationDate() {

		return this.creation_date;

	}

	public DistributedJobs setCreationDate( Timestamp creation_date ) {

		this.creation_date = creation_date;

		return this;

	}

	public Timestamp getAssignedDate() {

		return this.assigned_date;

	}

	public DistributedJobs setAssignedDate( Timestamp assigned_date ) {

		this.assigned_date = assigned_date;

		return this;

	}

	public Timestamp getUpdateDate() {

		return this.update_date;

	}

	public DistributedJobs setUpdateDate( Timestamp update_date ) {

		this.update_date = update_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return DistributedJobs.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"handler\": \"" ).append( this.getHandler() ).append( "\", " )
			.append( "\"assigner_id\": \"" ).append( this.getAssignerId() ).append( "\", " )
			.append( "\"assigner_name\": \"" ).append( this.getAssignerName() ).append( "\", " )
			.append( "\"piece\": \"" ).append( this.getPiece() ).append( "\", " )
			.append( "\"pieces\": \"" ).append( this.getPieces() ).append( "\", " )
			.append( "\"part\": \"" ).append( this.getPart() ).append( "\", " )
			.append( "\"current_pos\": \"" ).append( this.getCurrentPos() ).append( "\", " )
			.append( "\"status\": \"" ).append( this.getStatus() ).append( "\", " )
			.append( "\"message\": \"" ).append( this.getMessage() ).append( "\", " )
			.append( "\"attempts\": \"" ).append( this.getAttempts() ).append( "\", " )
			.append( "\"creation_date\": \"" ).append( this.getCreationDate() ).append( "\", " )
			.append( "\"assigned_date\": \"" ).append( this.getAssignedDate() ).append( "\", " )
			.append( "\"update_date\": \"" ).append( this.getUpdateDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }