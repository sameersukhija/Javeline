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


@Table( "stats_subs_data_old" )
public class StatsSubsDataOld { 

	public enum Fields { qty_session, volume_upload, volume_download, amount_data, last_session_date, msisdn, agg_date }

	@Column(
			table = "stats_subs_data_old",
			field = "qty_session",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtySession",
			setMethod = "setQtySession"
	)
	private Integer qty_session;

	@Column(
			table = "stats_subs_data_old",
			field = "volume_upload",
			type = "float unsigned",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getVolumeUpload",
			setMethod = "setVolumeUpload"
	)
	private Float volume_upload;

	@Column(
			table = "stats_subs_data_old",
			field = "volume_download",
			type = "float unsigned",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getVolumeDownload",
			setMethod = "setVolumeDownload"
	)
	private Float volume_download;

	@Column(
			table = "stats_subs_data_old",
			field = "amount_data",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountData",
			setMethod = "setAmountData"
	)
	private Float amount_data;

	@Column(
			table = "stats_subs_data_old",
			field = "last_session_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastSessionDate",
			setMethod = "setLastSessionDate"
	)
	private Date last_session_date;

	@Column(
			table = "stats_subs_data_old",
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
			table = "stats_subs_data_old",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;


	public StatsSubsDataOld() {} 

	public StatsSubsDataOld( ResultSet rs ) throws SQLException {

		this.qty_session = rs.getInt( StatsSubsDataOld.Fields.qty_session.name() );
		this.volume_upload = rs.getFloat( StatsSubsDataOld.Fields.volume_upload.name() );
		this.volume_download = rs.getFloat( StatsSubsDataOld.Fields.volume_download.name() );
		this.amount_data = rs.getFloat( StatsSubsDataOld.Fields.amount_data.name() );
		this.last_session_date = rs.getDate( StatsSubsDataOld.Fields.last_session_date.name() );
		this.msisdn = rs.getLong( StatsSubsDataOld.Fields.msisdn.name() );
		this.agg_date = rs.getDate( StatsSubsDataOld.Fields.agg_date.name() );

	}

	public StatsSubsDataOld( JSONObject jo ) throws JSONException, ParseException {

		this.qty_session = (int)jo.getInt( StatsSubsDataOld.Fields.qty_session.name() );
		this.volume_upload = (float)jo.getDouble( StatsSubsDataOld.Fields.volume_upload.name() );
		this.volume_download = (float)jo.getDouble( StatsSubsDataOld.Fields.volume_download.name() );
		this.amount_data = (float)jo.getDouble( StatsSubsDataOld.Fields.amount_data.name() );
		this.last_session_date = Format.getMysqlDateTime( jo.getString( StatsSubsDataOld.Fields.last_session_date.name() ) );
		this.msisdn = (long)jo.getLong( StatsSubsDataOld.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsDataOld.Fields.agg_date.name() ) );

	}

	public Integer getQtySession() {

		return this.qty_session;

	}

	public void setQtySession( Integer qty_session ) {

		this.qty_session = qty_session;

	}

	public Float getVolumeUpload() {

		return this.volume_upload;

	}

	public void setVolumeUpload( Float volume_upload ) {

		this.volume_upload = volume_upload;

	}

	public Float getVolumeDownload() {

		return this.volume_download;

	}

	public void setVolumeDownload( Float volume_download ) {

		this.volume_download = volume_download;

	}

	public Float getAmountData() {

		return this.amount_data;

	}

	public void setAmountData( Float amount_data ) {

		this.amount_data = amount_data;

	}

	public Date getLastSessionDate() {

		return this.last_session_date;

	}

	public void setLastSessionDate( Date last_session_date ) {

		this.last_session_date = last_session_date;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"qty_session\": \"" ).append( this.getQtySession() ).append( "\", " )
			.append( "\"volume_upload\": \"" ).append( this.getVolumeUpload() ).append( "\", " )
			.append( "\"volume_download\": \"" ).append( this.getVolumeDownload() ).append( "\", " )
			.append( "\"amount_data\": \"" ).append( this.getAmountData() ).append( "\", " )
			.append( "\"last_session_date\": \"" ).append( this.getLastSessionDate() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }