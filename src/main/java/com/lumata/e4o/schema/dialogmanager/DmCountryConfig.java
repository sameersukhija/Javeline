package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_country_config" )
public class DmCountryConfig { 

	public enum Fields { id, country, jndi, queue }

	@Column(
			table = "dm_country_config",
			field = "id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;

	@Column(
			table = "dm_country_config",
			field = "country",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getCountry",
			setMethod = "setCountry"
	)
	private String country;

	@Column(
			table = "dm_country_config",
			field = "jndi",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getJndi",
			setMethod = "setJndi"
	)
	private String jndi;

	@Column(
			table = "dm_country_config",
			field = "queue",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getQueue",
			setMethod = "setQueue"
	)
	private String queue;


	public DmCountryConfig() {} 

	public DmCountryConfig( ResultSet rs ) throws SQLException {

		this.id = rs.getInt( DmCountryConfig.Fields.id.name() );
		this.country = rs.getString( DmCountryConfig.Fields.country.name() );
		this.jndi = rs.getString( DmCountryConfig.Fields.jndi.name() );
		this.queue = rs.getString( DmCountryConfig.Fields.queue.name() );

	}

	public DmCountryConfig( JSONObject jo ) throws JSONException {

		this.id = (int)jo.getInt( DmCountryConfig.Fields.id.name() );
		this.country = jo.getString( DmCountryConfig.Fields.country.name() );
		this.jndi = jo.getString( DmCountryConfig.Fields.jndi.name() );
		this.queue = jo.getString( DmCountryConfig.Fields.queue.name() );

	}

	public Integer getId() {

		return this.id;

	}

	public void setId( Integer id ) {

		this.id = id;

	}

	public String getCountry() {

		return this.country;

	}

	public void setCountry( String country ) {

		this.country = country;

	}

	public String getJndi() {

		return this.jndi;

	}

	public void setJndi( String jndi ) {

		this.jndi = jndi;

	}

	public String getQueue() {

		return this.queue;

	}

	public void setQueue( String queue ) {

		this.queue = queue;

	}

	public Fields[] getEntityFields() {

		return DmCountryConfig.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"country\": \"" ).append( this.getCountry() ).append( "\", " )
			.append( "\"jndi\": \"" ).append( this.getJndi() ).append( "\", " )
			.append( "\"queue\": \"" ).append( this.getQueue() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }