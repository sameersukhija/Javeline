package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaign_substates" )
public class CampaignSubstates { 

	public enum Fields { substate_id, substate_name, description }

	@Column(
			table = "campaign_substates",
			field = "substate_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			comment = "",
			getMethod = "getSubstateId",
			setMethod = "setSubstateId"
	)
	private Byte substate_id;

	@Column(
			table = "campaign_substates",
			field = "substate_name",
			type = "varchar(55)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 55,
			comment = "",
			getMethod = "getSubstateName",
			setMethod = "setSubstateName"
	)
	private String substate_name;

	@Column(
			table = "campaign_substates",
			field = "description",
			type = "varchar(250)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 250,
			comment = "",
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;


	public CampaignSubstates() {} 

	public CampaignSubstates( ResultSet rs ) throws SQLException {

		this.substate_id = rs.getByte( CampaignSubstates.Fields.substate_id.name() );
		this.substate_name = rs.getString( CampaignSubstates.Fields.substate_name.name() );
		this.description = rs.getString( CampaignSubstates.Fields.description.name() );

	}

	public CampaignSubstates( JSONObject jo ) throws JSONException {

		this.substate_id = (byte)jo.getInt( CampaignSubstates.Fields.substate_id.name() );
		this.substate_name = jo.getString( CampaignSubstates.Fields.substate_name.name() );
		this.description = jo.getString( CampaignSubstates.Fields.description.name() );

	}

	public Byte getSubstateId() {

		return this.substate_id;

	}

	public void setSubstateId( Byte substate_id ) {

		this.substate_id = substate_id;

	}

	public String getSubstateName() {

		return this.substate_name;

	}

	public void setSubstateName( String substate_name ) {

		this.substate_name = substate_name;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public Fields[] getEntityFields() {

		return CampaignSubstates.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"substate_id\": \"" ).append( this.getSubstateId() ).append( "\", " )
			.append( "\"substate_name\": \"" ).append( this.getSubstateName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }