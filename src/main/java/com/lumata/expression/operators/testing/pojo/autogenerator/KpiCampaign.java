package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "kpi_campaign" )
public class KpiCampaign { 

	public enum Fields { msisdn, campaign_id, start_date, end_date, subs_status, substate_id }

	@Column(
			table = "kpi_campaign",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "kpi_campaign",
			field = "campaign_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "kpi_campaign",
			field = "start_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "kpi_campaign",
			field = "end_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "kpi_campaign",
			field = "subs_status",
			type = "enum('CONTROL','CONTROL_BENEF','PROVISIONED','NOTIFIED','BENEFICIARY','NOTIFIED_BENEFICIARY','NOT_TARGETED_BENEFICIARY','NOT_TARGETED_PROV')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getSubsStatus",
			setMethod = "setSubsStatus"
	)
	private String subs_status;

	@Column(
			table = "kpi_campaign",
			field = "substate_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getSubstateId",
			setMethod = "setSubstateId"
	)
	private Byte substate_id;


	public KpiCampaign() {} 

	public KpiCampaign( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( KpiCampaign.Fields.msisdn.name() );
		this.campaign_id = rs.getShort( KpiCampaign.Fields.campaign_id.name() );
		this.start_date = rs.getDate( KpiCampaign.Fields.start_date.name() );
		this.end_date = rs.getDate( KpiCampaign.Fields.end_date.name() );
		this.subs_status = rs.getString( KpiCampaign.Fields.subs_status.name() );
		this.substate_id = rs.getByte( KpiCampaign.Fields.substate_id.name() );

	}

	public KpiCampaign( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( KpiCampaign.Fields.msisdn.name() );
		this.campaign_id = (short)jo.getInt( KpiCampaign.Fields.campaign_id.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( KpiCampaign.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( KpiCampaign.Fields.end_date.name() ) );
		this.subs_status = jo.getString( KpiCampaign.Fields.subs_status.name() );
		this.substate_id = (byte)jo.getInt( KpiCampaign.Fields.substate_id.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public void setStartDate( Date start_date ) {

		this.start_date = start_date;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public void setEndDate( Date end_date ) {

		this.end_date = end_date;

	}

	public String getSubsStatus() {

		return this.subs_status;

	}

	public void setSubsStatus( String subs_status ) {

		this.subs_status = subs_status;

	}

	public Byte getSubstateId() {

		return this.substate_id;

	}

	public void setSubstateId( Byte substate_id ) {

		this.substate_id = substate_id;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"subs_status\": \"" ).append( this.getSubsStatus() ).append( "\", " )
			.append( "\"substate_id\": \"" ).append( this.getSubstateId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }