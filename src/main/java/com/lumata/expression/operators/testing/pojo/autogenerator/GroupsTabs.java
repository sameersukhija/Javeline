package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "groups_tabs" )
public class GroupsTabs { 

	public enum Fields { tab_id, group_id }

	@Column(
			table = "groups_tabs",
			field = "tab_id",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 1,
			getMethod = "getTabId",
			setMethod = "setTabId"
	)
	private Boolean tab_id;

	@Column(
			table = "groups_tabs",
			field = "group_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getGroupId",
			setMethod = "setGroupId"
	)
	private Short group_id;


	public GroupsTabs() {} 

	public GroupsTabs( ResultSet rs ) throws SQLException {

		this.tab_id = rs.getBoolean( GroupsTabs.Fields.tab_id.name() );
		this.group_id = rs.getShort( GroupsTabs.Fields.group_id.name() );

	}

	public GroupsTabs( JSONObject jo ) throws JSONException {

		this.tab_id = jo.getBoolean( GroupsTabs.Fields.tab_id.name() );
		this.group_id = (short)jo.getInt( GroupsTabs.Fields.group_id.name() );

	}

	public Boolean getTabId() {

		return this.tab_id;

	}

	public void setTabId( Boolean tab_id ) {

		this.tab_id = tab_id;

	}

	public Short getGroupId() {

		return this.group_id;

	}

	public void setGroupId( Short group_id ) {

		this.group_id = group_id;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tab_id\": \"" ).append( this.getTabId() ).append( "\", " )
			.append( "\"group_id\": \"" ).append( this.getGroupId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }