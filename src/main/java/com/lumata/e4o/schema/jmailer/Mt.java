package com.lumata.e4o.schema.jmailer;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "mt" )
public class Mt { 

	public enum Fields { code, date, acked, delivered, user, service, ip, phone, sender, message, promoter, id, id_obj, status, ack, error, type, tag, operator, rights, notes, autotimestamp }

	@Column(
			table = "mt",
			field = "code",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getCode",
			setMethod = "setCode"
	)
	private Integer code;

	@Column(
			table = "mt",
			field = "date",
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
			getMethod = "getDate",
			setMethod = "setDate"
	)
	private Timestamp date;

	@Column(
			table = "mt",
			field = "acked",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getAcked",
			setMethod = "setAcked"
	)
	private Timestamp acked;

	@Column(
			table = "mt",
			field = "delivered",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getDelivered",
			setMethod = "setDelivered"
	)
	private Timestamp delivered;

	@Column(
			table = "mt",
			field = "user",
			type = "varchar(16)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 16,
			getMethod = "getUser",
			setMethod = "setUser"
	)
	private String user;

	@Column(
			table = "mt",
			field = "service",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getService",
			setMethod = "setService"
	)
	private String service;

	@Column(
			table = "mt",
			field = "ip",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 15,
			getMethod = "getIp",
			setMethod = "setIp"
	)
	private String ip;

	@Column(
			table = "mt",
			field = "phone",
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
			getMethod = "getPhone",
			setMethod = "setPhone"
	)
	private String phone;

	@Column(
			table = "mt",
			field = "sender",
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
			getMethod = "getSender",
			setMethod = "setSender"
	)
	private String sender;

	@Column(
			table = "mt",
			field = "message",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getMessage",
			setMethod = "setMessage"
	)
	private String message;

	@Column(
			table = "mt",
			field = "promoter",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getPromoter",
			setMethod = "setPromoter"
	)
	private String promoter;

	@Column(
			table = "mt",
			field = "id",
			type = "varchar(80)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 80,
			getMethod = "getId",
			setMethod = "setId"
	)
	private String id;

	@Column(
			table = "mt",
			field = "id_obj",
			type = "varchar(25)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 25,
			getMethod = "getIdObj",
			setMethod = "setIdObj"
	)
	private String id_obj;

	@Column(
			table = "mt",
			field = "status",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getStatus",
			setMethod = "setStatus"
	)
	private Integer status;

	@Column(
			table = "mt",
			field = "ack",
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
			getMethod = "getAck",
			setMethod = "setAck"
	)
	private String ack;

	@Column(
			table = "mt",
			field = "error",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getError",
			setMethod = "setError"
	)
	private String error;

	@Column(
			table = "mt",
			field = "type",
			type = "varchar(4)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getType",
			setMethod = "setType"
	)
	private String type;

	@Column(
			table = "mt",
			field = "tag",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 15,
			getMethod = "getTag",
			setMethod = "setTag"
	)
	private String tag;

	@Column(
			table = "mt",
			field = "operator",
			type = "varchar(16)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 16,
			getMethod = "getOperator",
			setMethod = "setOperator"
	)
	private String operator;

	@Column(
			table = "mt",
			field = "rights",
			type = "varchar(128)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 128,
			getMethod = "getRights",
			setMethod = "setRights"
	)
	private String rights;

	@Column(
			table = "mt",
			field = "notes",
			type = "varchar(128)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 128,
			getMethod = "getNotes",
			setMethod = "setNotes"
	)
	private String notes;

	@Column(
			table = "mt",
			field = "autotimestamp",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getAutotimestamp",
			setMethod = "setAutotimestamp"
	)
	private Timestamp autotimestamp;


	public Mt() {} 

	public Mt( ResultSet rs ) throws SQLException {

		this.code = rs.getInt( Mt.Fields.code.name() );
		this.date = rs.getTimestamp( Mt.Fields.date.name() );
		this.acked = rs.getTimestamp( Mt.Fields.acked.name() );
		this.delivered = rs.getTimestamp( Mt.Fields.delivered.name() );
		this.user = rs.getString( Mt.Fields.user.name() );
		this.service = rs.getString( Mt.Fields.service.name() );
		this.ip = rs.getString( Mt.Fields.ip.name() );
		this.phone = rs.getString( Mt.Fields.phone.name() );
		this.sender = rs.getString( Mt.Fields.sender.name() );
		this.message = rs.getString( Mt.Fields.message.name() );
		this.promoter = rs.getString( Mt.Fields.promoter.name() );
		this.id = rs.getString( Mt.Fields.id.name() );
		this.id_obj = rs.getString( Mt.Fields.id_obj.name() );
		this.status = rs.getInt( Mt.Fields.status.name() );
		this.ack = rs.getString( Mt.Fields.ack.name() );
		this.error = rs.getString( Mt.Fields.error.name() );
		this.type = rs.getString( Mt.Fields.type.name() );
		this.tag = rs.getString( Mt.Fields.tag.name() );
		this.operator = rs.getString( Mt.Fields.operator.name() );
		this.rights = rs.getString( Mt.Fields.rights.name() );
		this.notes = rs.getString( Mt.Fields.notes.name() );
		this.autotimestamp = rs.getTimestamp( Mt.Fields.autotimestamp.name() );

	}

	public Mt( JSONObject jo ) throws JSONException, ParseException {

		this.code = (int)jo.getInt( Mt.Fields.code.name() );
		this.date = new Timestamp( Format.getMysqlDateTime( jo.getString( Mt.Fields.date.name() ) ).getTime() );
		this.acked = new Timestamp( Format.getMysqlDateTime( jo.getString( Mt.Fields.acked.name() ) ).getTime() );
		this.delivered = new Timestamp( Format.getMysqlDateTime( jo.getString( Mt.Fields.delivered.name() ) ).getTime() );
		this.user = jo.getString( Mt.Fields.user.name() );
		this.service = jo.getString( Mt.Fields.service.name() );
		this.ip = jo.getString( Mt.Fields.ip.name() );
		this.phone = jo.getString( Mt.Fields.phone.name() );
		this.sender = jo.getString( Mt.Fields.sender.name() );
		this.message = jo.getString( Mt.Fields.message.name() );
		this.promoter = jo.getString( Mt.Fields.promoter.name() );
		this.id = jo.getString( Mt.Fields.id.name() );
		this.id_obj = jo.getString( Mt.Fields.id_obj.name() );
		this.status = (int)jo.getInt( Mt.Fields.status.name() );
		this.ack = jo.getString( Mt.Fields.ack.name() );
		this.error = jo.getString( Mt.Fields.error.name() );
		this.type = jo.getString( Mt.Fields.type.name() );
		this.tag = jo.getString( Mt.Fields.tag.name() );
		this.operator = jo.getString( Mt.Fields.operator.name() );
		this.rights = jo.getString( Mt.Fields.rights.name() );
		this.notes = jo.getString( Mt.Fields.notes.name() );
		this.autotimestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( Mt.Fields.autotimestamp.name() ) ).getTime() );

	}

	public Integer getCode() {

		return this.code;

	}

	public void setCode( Integer code ) {

		this.code = code;

	}

	public Timestamp getDate() {

		return this.date;

	}

	public void setDate( Timestamp date ) {

		this.date = date;

	}

	public Timestamp getAcked() {

		return this.acked;

	}

	public void setAcked( Timestamp acked ) {

		this.acked = acked;

	}

	public Timestamp getDelivered() {

		return this.delivered;

	}

	public void setDelivered( Timestamp delivered ) {

		this.delivered = delivered;

	}

	public String getUser() {

		return this.user;

	}

	public void setUser( String user ) {

		this.user = user;

	}

	public String getService() {

		return this.service;

	}

	public void setService( String service ) {

		this.service = service;

	}

	public String getIp() {

		return this.ip;

	}

	public void setIp( String ip ) {

		this.ip = ip;

	}

	public String getPhone() {

		return this.phone;

	}

	public void setPhone( String phone ) {

		this.phone = phone;

	}

	public String getSender() {

		return this.sender;

	}

	public void setSender( String sender ) {

		this.sender = sender;

	}

	public String getMessage() {

		return this.message;

	}

	public void setMessage( String message ) {

		this.message = message;

	}

	public String getPromoter() {

		return this.promoter;

	}

	public void setPromoter( String promoter ) {

		this.promoter = promoter;

	}

	public String getId() {

		return this.id;

	}

	public void setId( String id ) {

		this.id = id;

	}

	public String getIdObj() {

		return this.id_obj;

	}

	public void setIdObj( String id_obj ) {

		this.id_obj = id_obj;

	}

	public Integer getStatus() {

		return this.status;

	}

	public void setStatus( Integer status ) {

		this.status = status;

	}

	public String getAck() {

		return this.ack;

	}

	public void setAck( String ack ) {

		this.ack = ack;

	}

	public String getError() {

		return this.error;

	}

	public void setError( String error ) {

		this.error = error;

	}

	public String getType() {

		return this.type;

	}

	public void setType( String type ) {

		this.type = type;

	}

	public String getTag() {

		return this.tag;

	}

	public void setTag( String tag ) {

		this.tag = tag;

	}

	public String getOperator() {

		return this.operator;

	}

	public void setOperator( String operator ) {

		this.operator = operator;

	}

	public String getRights() {

		return this.rights;

	}

	public void setRights( String rights ) {

		this.rights = rights;

	}

	public String getNotes() {

		return this.notes;

	}

	public void setNotes( String notes ) {

		this.notes = notes;

	}

	public Timestamp getAutotimestamp() {

		return this.autotimestamp;

	}

	public void setAutotimestamp( Timestamp autotimestamp ) {

		this.autotimestamp = autotimestamp;

	}

	public Fields[] getEntityFields() {

		return Mt.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"code\": \"" ).append( this.getCode() ).append( "\", " )
			.append( "\"date\": \"" ).append( this.getDate() ).append( "\", " )
			.append( "\"acked\": \"" ).append( this.getAcked() ).append( "\", " )
			.append( "\"delivered\": \"" ).append( this.getDelivered() ).append( "\", " )
			.append( "\"user\": \"" ).append( this.getUser() ).append( "\", " )
			.append( "\"service\": \"" ).append( this.getService() ).append( "\", " )
			.append( "\"ip\": \"" ).append( this.getIp() ).append( "\", " )
			.append( "\"phone\": \"" ).append( this.getPhone() ).append( "\", " )
			.append( "\"sender\": \"" ).append( this.getSender() ).append( "\", " )
			.append( "\"message\": \"" ).append( this.getMessage() ).append( "\", " )
			.append( "\"promoter\": \"" ).append( this.getPromoter() ).append( "\", " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"id_obj\": \"" ).append( this.getIdObj() ).append( "\", " )
			.append( "\"status\": \"" ).append( this.getStatus() ).append( "\", " )
			.append( "\"ack\": \"" ).append( this.getAck() ).append( "\", " )
			.append( "\"error\": \"" ).append( this.getError() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"tag\": \"" ).append( this.getTag() ).append( "\", " )
			.append( "\"operator\": \"" ).append( this.getOperator() ).append( "\", " )
			.append( "\"rights\": \"" ).append( this.getRights() ).append( "\", " )
			.append( "\"notes\": \"" ).append( this.getNotes() ).append( "\", " )
			.append( "\"autotimestamp\": \"" ).append( this.getAutotimestamp() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }