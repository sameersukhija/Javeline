package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "loyalty_subs_test" )
public class LoyaltySubsTest { 

	public enum Fields { program_id, msisdn }

	@Column(
			table = "loyalty_subs_test",
			field = "program_id",
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
			getMethod = "getProgramId",
			setMethod = "setProgramId"
	)
	private Byte program_id;

	@Column(
			table = "loyalty_subs_test",
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


	public LoyaltySubsTest() {} 

	public LoyaltySubsTest( ResultSet rs ) throws SQLException {

		this.program_id = rs.getByte( LoyaltySubsTest.Fields.program_id.name() );
		this.msisdn = rs.getLong( LoyaltySubsTest.Fields.msisdn.name() );

	}

	public LoyaltySubsTest( JSONObject jo ) throws JSONException {

		this.program_id = (byte)jo.getInt( LoyaltySubsTest.Fields.program_id.name() );
		this.msisdn = (long)jo.getLong( LoyaltySubsTest.Fields.msisdn.name() );

	}

	public Byte getProgramId() {

		return this.program_id;

	}

	public void setProgramId( Byte program_id ) {

		this.program_id = program_id;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Fields[] getEntityFields() {

		return LoyaltySubsTest.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"program_id\": \"" ).append( this.getProgramId() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }