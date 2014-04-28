package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "token_label" )
public class TokenLabel { 

	public enum Fields { token_label_id, label }

	@Column(
			table = "token_label",
			field = "token_label_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getTokenLabelId",
			setMethod = "setTokenLabelId"
	)
	private Byte token_label_id;

	@Column(
			table = "token_label",
			field = "label",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getLabel",
			setMethod = "setLabel"
	)
	private String label;


	public TokenLabel() {} 

	public TokenLabel( ResultSet rs ) throws SQLException {

		this.token_label_id = rs.getByte( TokenLabel.Fields.token_label_id.name() );
		this.label = rs.getString( TokenLabel.Fields.label.name() );

	}

	public TokenLabel( JSONObject jo ) throws JSONException {

		this.token_label_id = (byte)jo.getInt( TokenLabel.Fields.token_label_id.name() );
		this.label = jo.getString( TokenLabel.Fields.label.name() );

	}

	public Byte getTokenLabelId() {

		return this.token_label_id;

	}

	public void setTokenLabelId( Byte token_label_id ) {

		this.token_label_id = token_label_id;

	}

	public String getLabel() {

		return this.label;

	}

	public void setLabel( String label ) {

		this.label = label;

	}

	public Fields[] getEntityFields() {

		return TokenLabel.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"token_label_id\": \"" ).append( this.getTokenLabelId() ).append( "\", " )
			.append( "\"label\": \"" ).append( this.getLabel() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }