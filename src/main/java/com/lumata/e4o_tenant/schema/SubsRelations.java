package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "subs_relations" )
public class SubsRelations { 

	public enum Fields { msisdn, sponsor, relation_type_id }

	@Column(
			table = "subs_relations",
			field = "msisdn",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "subs_relations",
			field = "sponsor",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getSponsor",
			setMethod = "setSponsor"
	)
	private Long sponsor;

	@Column(
			table = "subs_relations",
			field = "relation_type_id",
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
			getMethod = "getRelationTypeId",
			setMethod = "setRelationTypeId"
	)
	private Byte relation_type_id;


	public SubsRelations() {} 

	public SubsRelations( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( SubsRelations.Fields.msisdn.name() );
		this.sponsor = rs.getLong( SubsRelations.Fields.sponsor.name() );
		this.relation_type_id = rs.getByte( SubsRelations.Fields.relation_type_id.name() );

	}

	public SubsRelations( JSONObject jo ) throws JSONException {

		this.msisdn = (long)jo.getLong( SubsRelations.Fields.msisdn.name() );
		this.sponsor = (long)jo.getLong( SubsRelations.Fields.sponsor.name() );
		this.relation_type_id = (byte)jo.getInt( SubsRelations.Fields.relation_type_id.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Long getSponsor() {

		return this.sponsor;

	}

	public void setSponsor( Long sponsor ) {

		this.sponsor = sponsor;

	}

	public Byte getRelationTypeId() {

		return this.relation_type_id;

	}

	public void setRelationTypeId( Byte relation_type_id ) {

		this.relation_type_id = relation_type_id;

	}

	public Fields[] getEntityFields() {

		return SubsRelations.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"sponsor\": \"" ).append( this.getSponsor() ).append( "\", " )
			.append( "\"relation_type_id\": \"" ).append( this.getRelationTypeId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }