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


@Table( "prediction_accuracy" )
public class PredictionAccuracy { 

	public enum Fields { question_id, agg_date, profile_id, status_id, network_id, arpu_id, rank, campaign_beneficiary, ucg, qty_predicted, qty_tp }

	@Column(
			table = "prediction_accuracy",
			field = "question_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getQuestionId",
			setMethod = "setQuestionId"
	)
	private Byte question_id;

	@Column(
			table = "prediction_accuracy",
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
			table = "prediction_accuracy",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "prediction_accuracy",
			field = "status_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "prediction_accuracy",
			field = "network_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "prediction_accuracy",
			field = "arpu_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "prediction_accuracy",
			field = "rank",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getRank",
			setMethod = "setRank"
	)
	private Byte rank;

	@Column(
			table = "prediction_accuracy",
			field = "campaign_beneficiary",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getCampaignBeneficiary",
			setMethod = "setCampaignBeneficiary"
	)
	private Byte campaign_beneficiary;

	@Column(
			table = "prediction_accuracy",
			field = "ucg",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getUcg",
			setMethod = "setUcg"
	)
	private Byte ucg;

	@Column(
			table = "prediction_accuracy",
			field = "qty_predicted",
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
			getMethod = "getQtyPredicted",
			setMethod = "setQtyPredicted"
	)
	private Integer qty_predicted;

	@Column(
			table = "prediction_accuracy",
			field = "qty_tp",
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
			getMethod = "getQtyTp",
			setMethod = "setQtyTp"
	)
	private Integer qty_tp;


	public PredictionAccuracy() {} 

	public PredictionAccuracy( ResultSet rs ) throws SQLException {

		this.question_id = rs.getByte( PredictionAccuracy.Fields.question_id.name() );
		this.agg_date = rs.getDate( PredictionAccuracy.Fields.agg_date.name() );
		this.profile_id = rs.getByte( PredictionAccuracy.Fields.profile_id.name() );
		this.status_id = rs.getByte( PredictionAccuracy.Fields.status_id.name() );
		this.network_id = rs.getByte( PredictionAccuracy.Fields.network_id.name() );
		this.arpu_id = rs.getByte( PredictionAccuracy.Fields.arpu_id.name() );
		this.rank = rs.getByte( PredictionAccuracy.Fields.rank.name() );
		this.campaign_beneficiary = rs.getByte( PredictionAccuracy.Fields.campaign_beneficiary.name() );
		this.ucg = rs.getByte( PredictionAccuracy.Fields.ucg.name() );
		this.qty_predicted = rs.getInt( PredictionAccuracy.Fields.qty_predicted.name() );
		this.qty_tp = rs.getInt( PredictionAccuracy.Fields.qty_tp.name() );

	}

	public PredictionAccuracy( JSONObject jo ) throws JSONException, ParseException {

		this.question_id = (byte)jo.getInt( PredictionAccuracy.Fields.question_id.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( PredictionAccuracy.Fields.agg_date.name() ) );
		this.profile_id = (byte)jo.getInt( PredictionAccuracy.Fields.profile_id.name() );
		this.status_id = (byte)jo.getInt( PredictionAccuracy.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( PredictionAccuracy.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( PredictionAccuracy.Fields.arpu_id.name() );
		this.rank = (byte)jo.getInt( PredictionAccuracy.Fields.rank.name() );
		this.campaign_beneficiary = (byte)jo.getInt( PredictionAccuracy.Fields.campaign_beneficiary.name() );
		this.ucg = (byte)jo.getInt( PredictionAccuracy.Fields.ucg.name() );
		this.qty_predicted = (int)jo.getInt( PredictionAccuracy.Fields.qty_predicted.name() );
		this.qty_tp = (int)jo.getInt( PredictionAccuracy.Fields.qty_tp.name() );

	}

	public Byte getQuestionId() {

		return this.question_id;

	}

	public PredictionAccuracy setQuestionId( Byte question_id ) {

		this.question_id = question_id;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public PredictionAccuracy setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public PredictionAccuracy setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public PredictionAccuracy setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public PredictionAccuracy setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public PredictionAccuracy setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

		return this;

	}

	public Byte getRank() {

		return this.rank;

	}

	public PredictionAccuracy setRank( Byte rank ) {

		this.rank = rank;

		return this;

	}

	public Byte getCampaignBeneficiary() {

		return this.campaign_beneficiary;

	}

	public PredictionAccuracy setCampaignBeneficiary( Byte campaign_beneficiary ) {

		this.campaign_beneficiary = campaign_beneficiary;

		return this;

	}

	public Byte getUcg() {

		return this.ucg;

	}

	public PredictionAccuracy setUcg( Byte ucg ) {

		this.ucg = ucg;

		return this;

	}

	public Integer getQtyPredicted() {

		return this.qty_predicted;

	}

	public PredictionAccuracy setQtyPredicted( Integer qty_predicted ) {

		this.qty_predicted = qty_predicted;

		return this;

	}

	public Integer getQtyTp() {

		return this.qty_tp;

	}

	public PredictionAccuracy setQtyTp( Integer qty_tp ) {

		this.qty_tp = qty_tp;

		return this;

	}

	public Fields[] getEntityFields() {

		return PredictionAccuracy.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"question_id\": \"" ).append( this.getQuestionId() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"rank\": \"" ).append( this.getRank() ).append( "\", " )
			.append( "\"campaign_beneficiary\": \"" ).append( this.getCampaignBeneficiary() ).append( "\", " )
			.append( "\"ucg\": \"" ).append( this.getUcg() ).append( "\", " )
			.append( "\"qty_predicted\": \"" ).append( this.getQtyPredicted() ).append( "\", " )
			.append( "\"qty_tp\": \"" ).append( this.getQtyTp() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }