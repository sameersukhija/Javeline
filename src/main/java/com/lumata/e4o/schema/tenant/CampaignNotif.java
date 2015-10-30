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


@Table( "campaign_notif" )
public class CampaignNotif { 

	public enum Fields { msisdn, campaign_id, channel_id, message_id, message_state, channel_state, update_time }

	@Column(
			table = "campaign_notif",
			field = "msisdn",
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
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "campaign_notif",
			field = "campaign_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaign_notif",
			field = "channel_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Byte channel_id;

	@Column(
			table = "campaign_notif",
			field = "message_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getMessageId",
			setMethod = "setMessageId"
	)
	private Long message_id;

	@Column(
			table = "campaign_notif",
			field = "message_state",
			type = "enum('NOT_SENT','SENT','DELIVERED','EXPIRED','ERROR','NO_CHANNEL_LANGUAGE')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "NOT_SENT",
			extra = "",
			length = 6,
			comment = "",
			getMethod = "getMessageState",
			setMethod = "setMessageState"
	)
	private String message_state;

	@Column(
			table = "campaign_notif",
			field = "channel_state",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getChannelState",
			setMethod = "setChannelState"
	)
	private Short channel_state;

	@Column(
			table = "campaign_notif",
			field = "update_time",
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
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public CampaignNotif() {} 

	public CampaignNotif( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( CampaignNotif.Fields.msisdn.name() );
		this.campaign_id = rs.getShort( CampaignNotif.Fields.campaign_id.name() );
		this.channel_id = rs.getByte( CampaignNotif.Fields.channel_id.name() );
		this.message_id = rs.getLong( CampaignNotif.Fields.message_id.name() );
		this.message_state = rs.getString( CampaignNotif.Fields.message_state.name() );
		this.channel_state = rs.getShort( CampaignNotif.Fields.channel_state.name() );
		this.update_time = rs.getTimestamp( CampaignNotif.Fields.update_time.name() );

	}

	public CampaignNotif( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( CampaignNotif.Fields.msisdn.name() );
		this.campaign_id = (short)jo.getInt( CampaignNotif.Fields.campaign_id.name() );
		this.channel_id = (byte)jo.getInt( CampaignNotif.Fields.channel_id.name() );
		this.message_id = (long)jo.getLong( CampaignNotif.Fields.message_id.name() );
		this.message_state = jo.getString( CampaignNotif.Fields.message_state.name() );
		this.channel_state = (short)jo.getInt( CampaignNotif.Fields.channel_state.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( CampaignNotif.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public CampaignNotif setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public CampaignNotif setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public CampaignNotif setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

		return this;

	}

	public Long getMessageId() {

		return this.message_id;

	}

	public CampaignNotif setMessageId( Long message_id ) {

		this.message_id = message_id;

		return this;

	}

	public String getMessageState() {

		return this.message_state;

	}

	public CampaignNotif setMessageState( String message_state ) {

		this.message_state = message_state;

		return this;

	}

	public Short getChannelState() {

		return this.channel_state;

	}

	public CampaignNotif setChannelState( Short channel_state ) {

		this.channel_state = channel_state;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public CampaignNotif setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return CampaignNotif.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"message_id\": \"" ).append( this.getMessageId() ).append( "\", " )
			.append( "\"message_state\": \"" ).append( this.getMessageState() ).append( "\", " )
			.append( "\"channel_state\": \"" ).append( this.getChannelState() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }