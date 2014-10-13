package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaign_types" )
public class CampaignTypes { 

	public enum Fields { type_id, type_name, max_occurence, waiting_period, max_simultaneous, churn_impact, full_stats }

	@Column(
			table = "campaign_types",
			field = "type_id",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 6,
			getMethod = "getTypeId",
			setMethod = "setTypeId"
	)
	private Short type_id;

	@Column(
			table = "campaign_types",
			field = "type_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getTypeName",
			setMethod = "setTypeName"
	)
	private String type_name;

	@Column(
			table = "campaign_types",
			field = "max_occurence",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getMaxOccurence",
			setMethod = "setMaxOccurence"
	)
	private Short max_occurence;

	@Column(
			table = "campaign_types",
			field = "waiting_period",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getWaitingPeriod",
			setMethod = "setWaitingPeriod"
	)
	private Short waiting_period;

	@Column(
			table = "campaign_types",
			field = "max_simultaneous",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getMaxSimultaneous",
			setMethod = "setMaxSimultaneous"
	)
	private Short max_simultaneous;

	@Column(
			table = "campaign_types",
			field = "churn_impact",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 4,
			getMethod = "getChurnImpact",
			setMethod = "setChurnImpact"
	)
	private Byte churn_impact;

	@Column(
			table = "campaign_types",
			field = "full_stats",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 3,
			getMethod = "getFullStats",
			setMethod = "setFullStats"
	)
	private Byte full_stats;


	public CampaignTypes() {} 

	public CampaignTypes( ResultSet rs ) throws SQLException {

		this.type_id = rs.getShort( CampaignTypes.Fields.type_id.name() );
		this.type_name = rs.getString( CampaignTypes.Fields.type_name.name() );
		this.max_occurence = rs.getShort( CampaignTypes.Fields.max_occurence.name() );
		this.waiting_period = rs.getShort( CampaignTypes.Fields.waiting_period.name() );
		this.max_simultaneous = rs.getShort( CampaignTypes.Fields.max_simultaneous.name() );
		this.churn_impact = rs.getByte( CampaignTypes.Fields.churn_impact.name() );
		this.full_stats = rs.getByte( CampaignTypes.Fields.full_stats.name() );

	}

	public CampaignTypes( JSONObject jo ) throws JSONException {

		this.type_id = (short)jo.getInt( CampaignTypes.Fields.type_id.name() );
		this.type_name = jo.getString( CampaignTypes.Fields.type_name.name() );
		this.max_occurence = (short)jo.getInt( CampaignTypes.Fields.max_occurence.name() );
		this.waiting_period = (short)jo.getInt( CampaignTypes.Fields.waiting_period.name() );
		this.max_simultaneous = (short)jo.getInt( CampaignTypes.Fields.max_simultaneous.name() );
		this.churn_impact = (byte)jo.getInt( CampaignTypes.Fields.churn_impact.name() );
		this.full_stats = (byte)jo.getInt( CampaignTypes.Fields.full_stats.name() );

	}

	public Short getTypeId() {

		return this.type_id;

	}

	public void setTypeId( Short type_id ) {

		this.type_id = type_id;

	}

	public String getTypeName() {

		return this.type_name;

	}

	public void setTypeName( String type_name ) {

		this.type_name = type_name;

	}

	public Short getMaxOccurence() {

		return this.max_occurence;

	}

	public void setMaxOccurence( Short max_occurence ) {

		this.max_occurence = max_occurence;

	}

	public Short getWaitingPeriod() {

		return this.waiting_period;

	}

	public void setWaitingPeriod( Short waiting_period ) {

		this.waiting_period = waiting_period;

	}

	public Short getMaxSimultaneous() {

		return this.max_simultaneous;

	}

	public void setMaxSimultaneous( Short max_simultaneous ) {

		this.max_simultaneous = max_simultaneous;

	}

	public Byte getChurnImpact() {

		return this.churn_impact;

	}

	public void setChurnImpact( Byte churn_impact ) {

		this.churn_impact = churn_impact;

	}

	public Byte getFullStats() {

		return this.full_stats;

	}

	public void setFullStats( Byte full_stats ) {

		this.full_stats = full_stats;

	}

	public Fields[] getEntityFields() {

		return CampaignTypes.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"type_id\": \"" ).append( this.getTypeId() ).append( "\", " )
			.append( "\"type_name\": \"" ).append( this.getTypeName() ).append( "\", " )
			.append( "\"max_occurence\": \"" ).append( this.getMaxOccurence() ).append( "\", " )
			.append( "\"waiting_period\": \"" ).append( this.getWaitingPeriod() ).append( "\", " )
			.append( "\"max_simultaneous\": \"" ).append( this.getMaxSimultaneous() ).append( "\", " )
			.append( "\"churn_impact\": \"" ).append( this.getChurnImpact() ).append( "\", " )
			.append( "\"full_stats\": \"" ).append( this.getFullStats() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }