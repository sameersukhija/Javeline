package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "notif_reward_events" )
public class NotifRewardEvents { 

	public enum Fields { notif_id, event_id }

	@Column(
			table = "notif_reward_events",
			field = "notif_id",
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
			getMethod = "getNotifId",
			setMethod = "setNotifId"
	)
	private Long notif_id;

	@Column(
			table = "notif_reward_events",
			field = "event_id",
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
			getMethod = "getEventId",
			setMethod = "setEventId"
	)
	private Long event_id;


	public NotifRewardEvents() {} 

	public NotifRewardEvents( ResultSet rs ) throws SQLException {

		this.notif_id = rs.getLong( NotifRewardEvents.Fields.notif_id.name() );
		this.event_id = rs.getLong( NotifRewardEvents.Fields.event_id.name() );

	}

	public NotifRewardEvents( JSONObject jo ) throws JSONException {

		this.notif_id = (long)jo.getLong( NotifRewardEvents.Fields.notif_id.name() );
		this.event_id = (long)jo.getLong( NotifRewardEvents.Fields.event_id.name() );

	}

	public Long getNotifId() {

		return this.notif_id;

	}

	public void setNotifId( Long notif_id ) {

		this.notif_id = notif_id;

	}

	public Long getEventId() {

		return this.event_id;

	}

	public void setEventId( Long event_id ) {

		this.event_id = event_id;

	}

	public Fields[] getEntityFields() {

		return NotifRewardEvents.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"notif_id\": \"" ).append( this.getNotifId() ).append( "\", " )
			.append( "\"event_id\": \"" ).append( this.getEventId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }