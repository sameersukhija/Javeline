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
import java.sql.Timestamp;


@Table( "daily_data" )
public class DailyData { 

	public enum Fields { qty_session, volume_upload, volume_download, amount_data, msisdn, agg_date, update_time }

	@Column(
			table = "daily_data",
			field = "qty_session",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getQtySession",
			setMethod = "setQtySession"
	)
	private Short qty_session;

	@Column(
			table = "daily_data",
			field = "volume_upload",
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
			getMethod = "getVolumeUpload",
			setMethod = "setVolumeUpload"
	)
	private Integer volume_upload;

	@Column(
			table = "daily_data",
			field = "volume_download",
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
			getMethod = "getVolumeDownload",
			setMethod = "setVolumeDownload"
	)
	private Integer volume_download;

	@Column(
			table = "daily_data",
			field = "amount_data",
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
			comment = "",
			getMethod = "getAmountData",
			setMethod = "setAmountData"
	)
	private Integer amount_data;

	@Column(
			table = "daily_data",
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
			table = "daily_data",
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
			table = "daily_data",
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


	public DailyData() {} 

	public DailyData( ResultSet rs ) throws SQLException {

		this.qty_session = rs.getShort( DailyData.Fields.qty_session.name() );
		this.volume_upload = rs.getInt( DailyData.Fields.volume_upload.name() );
		this.volume_download = rs.getInt( DailyData.Fields.volume_download.name() );
		this.amount_data = rs.getInt( DailyData.Fields.amount_data.name() );
		this.msisdn = rs.getLong( DailyData.Fields.msisdn.name() );
		this.agg_date = rs.getDate( DailyData.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailyData.Fields.update_time.name() );

	}

	public DailyData( JSONObject jo ) throws JSONException, ParseException {

		this.qty_session = (short)jo.getInt( DailyData.Fields.qty_session.name() );
		this.volume_upload = (int)jo.getInt( DailyData.Fields.volume_upload.name() );
		this.volume_download = (int)jo.getInt( DailyData.Fields.volume_download.name() );
		this.amount_data = (int)jo.getInt( DailyData.Fields.amount_data.name() );
		this.msisdn = (long)jo.getLong( DailyData.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailyData.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyData.Fields.update_time.name() ) ).getTime() );

	}

	public Short getQtySession() {

		return this.qty_session;

	}

	public DailyData setQtySession( Short qty_session ) {

		this.qty_session = qty_session;

		return this;

	}

	public Integer getVolumeUpload() {

		return this.volume_upload;

	}

	public DailyData setVolumeUpload( Integer volume_upload ) {

		this.volume_upload = volume_upload;

		return this;

	}

	public Integer getVolumeDownload() {

		return this.volume_download;

	}

	public DailyData setVolumeDownload( Integer volume_download ) {

		this.volume_download = volume_download;

		return this;

	}

	public Integer getAmountData() {

		return this.amount_data;

	}

	public DailyData setAmountData( Integer amount_data ) {

		this.amount_data = amount_data;

		return this;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public DailyData setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public DailyData setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public DailyData setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return DailyData.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"qty_session\": \"" ).append( this.getQtySession() ).append( "\", " )
			.append( "\"volume_upload\": \"" ).append( this.getVolumeUpload() ).append( "\", " )
			.append( "\"volume_download\": \"" ).append( this.getVolumeDownload() ).append( "\", " )
			.append( "\"amount_data\": \"" ).append( this.getAmountData() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }