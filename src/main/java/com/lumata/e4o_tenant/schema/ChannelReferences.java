package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "channel_references" )
public class ChannelReferences { 

	public enum Fields { channel_id, ref_type, ref_id, rights }

	@Column(
			table = "channel_references",
			field = "channel_id",
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
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Long channel_id;

	@Column(
			table = "channel_references",
			field = "ref_type",
			type = "enum('agency','user','group')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "agency",
			extra = "",
			length = 3,
			getMethod = "getRefType",
			setMethod = "setRefType"
	)
	private String ref_type;

	@Column(
			table = "channel_references",
			field = "ref_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getRefId",
			setMethod = "setRefId"
	)
	private Short ref_id;

	@Column(
			table = "channel_references",
			field = "rights",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getRights",
			setMethod = "setRights"
	)
	private Short rights;


	public ChannelReferences() {} 

	public ChannelReferences( ResultSet rs ) throws SQLException {

		this.channel_id = rs.getLong( ChannelReferences.Fields.channel_id.name() );
		this.ref_type = rs.getString( ChannelReferences.Fields.ref_type.name() );
		this.ref_id = rs.getShort( ChannelReferences.Fields.ref_id.name() );
		this.rights = rs.getShort( ChannelReferences.Fields.rights.name() );

	}

	public ChannelReferences( JSONObject jo ) throws JSONException {

		this.channel_id = (long)jo.getLong( ChannelReferences.Fields.channel_id.name() );
		this.ref_type = jo.getString( ChannelReferences.Fields.ref_type.name() );
		this.ref_id = (short)jo.getInt( ChannelReferences.Fields.ref_id.name() );
		this.rights = (short)jo.getInt( ChannelReferences.Fields.rights.name() );

	}

	public Long getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Long channel_id ) {

		this.channel_id = channel_id;

	}

	public String getRefType() {

		return this.ref_type;

	}

	public void setRefType( String ref_type ) {

		this.ref_type = ref_type;

	}

	public Short getRefId() {

		return this.ref_id;

	}

	public void setRefId( Short ref_id ) {

		this.ref_id = ref_id;

	}

	public Short getRights() {

		return this.rights;

	}

	public void setRights( Short rights ) {

		this.rights = rights;

	}

	public Fields[] getEntityFields() {

		return ChannelReferences.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"ref_type\": \"" ).append( this.getRefType() ).append( "\", " )
			.append( "\"ref_id\": \"" ).append( this.getRefId() ).append( "\", " )
			.append( "\"rights\": \"" ).append( this.getRights() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }