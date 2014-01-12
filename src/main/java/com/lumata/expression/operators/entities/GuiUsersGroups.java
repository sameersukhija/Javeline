package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "gui_users_groups" )
public class GuiUsersGroups { 

	public enum Fields { gui_users_id, group_id, rights }

	@Column(
			table = "gui_users_groups",
			field = "gui_users_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getGuiUsersId",
			setMethod = "setGuiUsersId"
	)
	private Short gui_users_id;

	@Column(
			table = "gui_users_groups",
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

	@Column(
			table = "gui_users_groups",
			field = "rights",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getRights",
			setMethod = "setRights"
	)
	private Short rights;


	public GuiUsersGroups() {} 

	public GuiUsersGroups( ResultSet rs ) throws SQLException {

		this.gui_users_id = rs.getShort( GuiUsersGroups.Fields.gui_users_id.name() );
		this.group_id = rs.getShort( GuiUsersGroups.Fields.group_id.name() );
		this.rights = rs.getShort( GuiUsersGroups.Fields.rights.name() );

	}

	public GuiUsersGroups( JSONObject jo ) throws JSONException {

		this.gui_users_id = (short)jo.getInt( GuiUsersGroups.Fields.gui_users_id.name() );
		this.group_id = (short)jo.getInt( GuiUsersGroups.Fields.group_id.name() );
		this.rights = (short)jo.getInt( GuiUsersGroups.Fields.rights.name() );

	}

	public Short getGuiUsersId() {

		return this.gui_users_id;

	}

	public void setGuiUsersId( Short gui_users_id ) {

		this.gui_users_id = gui_users_id;

	}

	public Short getGroupId() {

		return this.group_id;

	}

	public void setGroupId( Short group_id ) {

		this.group_id = group_id;

	}

	public Short getRights() {

		return this.rights;

	}

	public void setRights( Short rights ) {

		this.rights = rights;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"gui_users_id\": \"" ).append( this.getGuiUsersId() ).append( "\", " )
			.append( "\"group_id\": \"" ).append( this.getGroupId() ).append( "\", " )
			.append( "\"rights\": \"" ).append( this.getRights() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }