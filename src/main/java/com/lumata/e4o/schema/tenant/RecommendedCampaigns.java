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


@Table( "recommended_campaigns" )
public class RecommendedCampaigns { 

	public enum Fields { msisdn, agg_date, profile_id, status_id, network_id, arpu_id, seniority_id, model_name }

	@Column(
			table = "recommended_campaigns",
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
			table = "recommended_campaigns",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;

	@Column(
			table = "recommended_campaigns",
			field = "profile_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "recommended_campaigns",
			field = "status_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "recommended_campaigns",
			field = "network_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "recommended_campaigns",
			field = "arpu_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "recommended_campaigns",
			field = "seniority_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "recommended_campaigns",
			field = "model_name",
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
			getMethod = "getModelName",
			setMethod = "setModelName"
	)
	private String model_name;


	public RecommendedCampaigns() {} 

	public RecommendedCampaigns( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( RecommendedCampaigns.Fields.msisdn.name() );
		this.agg_date = rs.getDate( RecommendedCampaigns.Fields.agg_date.name() );
		this.profile_id = rs.getByte( RecommendedCampaigns.Fields.profile_id.name() );
		this.status_id = rs.getByte( RecommendedCampaigns.Fields.status_id.name() );
		this.network_id = rs.getByte( RecommendedCampaigns.Fields.network_id.name() );
		this.arpu_id = rs.getByte( RecommendedCampaigns.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( RecommendedCampaigns.Fields.seniority_id.name() );
		this.model_name = rs.getString( RecommendedCampaigns.Fields.model_name.name() );

	}

	public RecommendedCampaigns( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( RecommendedCampaigns.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( RecommendedCampaigns.Fields.agg_date.name() ) );
		this.profile_id = (byte)jo.getInt( RecommendedCampaigns.Fields.profile_id.name() );
		this.status_id = (byte)jo.getInt( RecommendedCampaigns.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( RecommendedCampaigns.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( RecommendedCampaigns.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( RecommendedCampaigns.Fields.seniority_id.name() );
		this.model_name = jo.getString( RecommendedCampaigns.Fields.model_name.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public RecommendedCampaigns setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public RecommendedCampaigns setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public RecommendedCampaigns setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public RecommendedCampaigns setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public RecommendedCampaigns setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public RecommendedCampaigns setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

		return this;

	}

	public Byte getSeniorityId() {

		return this.seniority_id;

	}

	public RecommendedCampaigns setSeniorityId( Byte seniority_id ) {

		this.seniority_id = seniority_id;

		return this;

	}

	public String getModelName() {

		return this.model_name;

	}

	public RecommendedCampaigns setModelName( String model_name ) {

		this.model_name = model_name;

		return this;

	}

	public Fields[] getEntityFields() {

		return RecommendedCampaigns.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\", " )
			.append( "\"model_name\": \"" ).append( this.getModelName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }