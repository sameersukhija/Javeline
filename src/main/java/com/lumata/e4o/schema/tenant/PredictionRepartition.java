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


@Table( "prediction_repartition" )
public class PredictionRepartition { 

	public enum Fields { question_id, agg_date, profile_id, proba, qty_predicted }

	@Column(
			table = "prediction_repartition",
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
			table = "prediction_repartition",
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
			table = "prediction_repartition",
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
			table = "prediction_repartition",
			field = "proba",
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
			getMethod = "getProba",
			setMethod = "setProba"
	)
	private Byte proba;

	@Column(
			table = "prediction_repartition",
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


	public PredictionRepartition() {} 

	public PredictionRepartition( ResultSet rs ) throws SQLException {

		this.question_id = rs.getByte( PredictionRepartition.Fields.question_id.name() );
		this.agg_date = rs.getDate( PredictionRepartition.Fields.agg_date.name() );
		this.profile_id = rs.getByte( PredictionRepartition.Fields.profile_id.name() );
		this.proba = rs.getByte( PredictionRepartition.Fields.proba.name() );
		this.qty_predicted = rs.getInt( PredictionRepartition.Fields.qty_predicted.name() );

	}

	public PredictionRepartition( JSONObject jo ) throws JSONException, ParseException {

		this.question_id = (byte)jo.getInt( PredictionRepartition.Fields.question_id.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( PredictionRepartition.Fields.agg_date.name() ) );
		this.profile_id = (byte)jo.getInt( PredictionRepartition.Fields.profile_id.name() );
		this.proba = (byte)jo.getInt( PredictionRepartition.Fields.proba.name() );
		this.qty_predicted = (int)jo.getInt( PredictionRepartition.Fields.qty_predicted.name() );

	}

	public Byte getQuestionId() {

		return this.question_id;

	}

	public PredictionRepartition setQuestionId( Byte question_id ) {

		this.question_id = question_id;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public PredictionRepartition setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public PredictionRepartition setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getProba() {

		return this.proba;

	}

	public PredictionRepartition setProba( Byte proba ) {

		this.proba = proba;

		return this;

	}

	public Integer getQtyPredicted() {

		return this.qty_predicted;

	}

	public PredictionRepartition setQtyPredicted( Integer qty_predicted ) {

		this.qty_predicted = qty_predicted;

		return this;

	}

	public Fields[] getEntityFields() {

		return PredictionRepartition.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"question_id\": \"" ).append( this.getQuestionId() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"proba\": \"" ).append( this.getProba() ).append( "\", " )
			.append( "\"qty_predicted\": \"" ).append( this.getQtyPredicted() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }