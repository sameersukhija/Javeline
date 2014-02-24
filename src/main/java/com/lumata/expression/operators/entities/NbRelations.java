package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "nb_relations" )
public class NbRelations { 

	public enum Fields { relation_type_id, msisdn, nb_subs }

	@Column(
			table = "nb_relations",
			field = "relation_type_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getRelationTypeId",
			setMethod = "setRelationTypeId"
	)
	private Byte relation_type_id;

	@Column(
			table = "nb_relations",
			field = "msisdn",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "nb_relations",
			field = "nb_subs",
			type = "bigint(21)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 21,
			getMethod = "getNbSubs",
			setMethod = "setNbSubs"
	)
	private Long nb_subs;


	public NbRelations() {} 

	public NbRelations( ResultSet rs ) throws SQLException {

		this.relation_type_id = rs.getByte( NbRelations.Fields.relation_type_id.name() );
		this.msisdn = rs.getLong( NbRelations.Fields.msisdn.name() );
		this.nb_subs = rs.getLong( NbRelations.Fields.nb_subs.name() );

	}

	public NbRelations( JSONObject jo ) throws JSONException {

		this.relation_type_id = (byte)jo.getInt( NbRelations.Fields.relation_type_id.name() );
		this.msisdn = (long)jo.getLong( NbRelations.Fields.msisdn.name() );
		this.nb_subs = (long)jo.getLong( NbRelations.Fields.nb_subs.name() );

	}

	public Byte getRelationTypeId() {

		return this.relation_type_id;

	}

	public void setRelationTypeId( Byte relation_type_id ) {

		this.relation_type_id = relation_type_id;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Long getNbSubs() {

		return this.nb_subs;

	}

	public void setNbSubs( Long nb_subs ) {

		this.nb_subs = nb_subs;

	}

	public Fields[] getEntityFields() {

		return NbRelations.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"relation_type_id\": \"" ).append( this.getRelationTypeId() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"nb_subs\": \"" ).append( this.getNbSubs() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }