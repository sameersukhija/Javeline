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

	public void setName( String name ) {

		this.name = name;

	}

	public String getHandler() {

		return this.handler;

	}

	public void setHandler( String handler ) {

		this.handler = handler;

	}

	public String getAssignerId() {

		return this.assigner_id;

	}

	public void setAssignerId( String assigner_id ) {

		this.assigner_id = assigner_id;

	}

	public String getAssignerName() {

		return this.assigner_name;

	}

	public void setAssignerName( String assigner_name ) {

		this.assigner_name = assigner_name;

	}

	public Integer getPiece() {

		return this.piece;

	}

	public void setPiece( Integer piece ) {

		this.piece = piece;

	}

	public Integer getPieces() {

		return this.pieces;

	}

	public void setPieces( Integer pieces ) {

		this.pieces = pieces;

	}

	public String getPart() {

		return this.part;

	}

	public void setPart( String part ) {

		this.part = part;

	}

	public Long getCurrentPos() {

		return this.current_pos;

	}

	public void setCurrentPos( Long current_pos ) {

		this.current_pos = current_pos;

	}

	public String getStatus() {

		return this.status;

	}

	public void setStatus( String status ) {

		this.status = status;

	}

	public String getMessage() {

		return this.message;

	}

	public void setMessage( String message ) {

		this.message = message;

	}

	public Byte getAttempts() {

		return this.attempts;

	}

	public void setAttempts( Byte attempts ) {

		this.attempts = attempts;

	}

	public Timestamp getCreationDate() {

		return this.creation_date;

	}

	public void setCreationDate( Timestamp creation_date ) {

		this.creation_date = creation_date;

	}

	public Timestamp getAssignedDate() {

		return this.assigned_date;

	}

	public void setAssignedDate( Timestamp assigned_date ) {

		this.assigned_date = assigned_date;

	}

	public Timestamp getUpdateDate() {

		return this.update_date;

	}

	public void setUpdateDate( Timestamp update_date ) {

		this.update_date = update_date;

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