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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
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
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 4,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
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
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 4,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
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

	public void setQuestionId( Byte question_id ) {

		this.question_id = question_id;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public void setStatusId( Byte status_id ) {

		this.status_id = status_id;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public void setNetworkId( Byte network_id ) {

		this.network_id = network_id;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public void setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

	}

	public Byte getRank() {

		return this.rank;

	}

	public void setRank( Byte rank ) {

		this.rank = rank;

	}

	public Byte getCampaignBeneficiary() {

		return this.campaign_beneficiary;

	}

	public void setCampaignBeneficiary( Byte campaign_beneficiary ) {

		this.campaign_beneficiary = campaign_beneficiary;

	}

	public Byte getUcg() {

		return this.ucg;

	}

	public void setUcg( Byte ucg ) {

		this.ucg = ucg;

	}

	public Integer getQtyPredicted() {

		return this.qty_predicted;

	}

	public void setQtyPredicted( Integer qty_predicted ) {

		this.qty_predicted = qty_predicted;

	}

	public Integer getQtyTp() {

		return this.qty_tp;

	}

	public void setQtyTp( Integer qty_tp ) {

		this.qty_tp = qty_tp;

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