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


@Table( "devices" )
public class Devices { 

	public enum Fields { tac, brand, model, OS, bearers, GPS, camera, video, type, screen_size, launch_date, price }

	@Column(
			table = "devices",
			field = "tac",
			type = "char(8)",
			mysqlType = "char",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getTac",
			setMethod = "setTac"
	)
	private String tac;

	@Column(
			table = "devices",
			field = "brand",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getBrand",
			setMethod = "setBrand"
	)
	private String brand;

	@Column(
			table = "devices",
			field = "model",
			type = "varchar(250)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 250,
			getMethod = "getModel",
			setMethod = "setModel"
	)
	private String model;

	@Column(
			table = "devices",
			field = "OS",
			type = "enum('Android','Bada','iOS','other','RIM','Symbian','Windows')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 7,
			getMethod = "getOS",
			setMethod = "setOS"
	)
	private String OS;

	@Column(
			table = "devices",
			field = "bearers",
			type = "set('2G','2.5G','3G','3.5G','4G')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getBearers",
			setMethod = "setBearers"
	)
	private String bearers;

	@Column(
			table = "devices",
			field = "GPS",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getGPS",
			setMethod = "setGPS"
	)
	private Byte GPS;

	@Column(
			table = "devices",
			field = "camera",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getCamera",
			setMethod = "setCamera"
	)
	private Byte camera;

	@Column(
			table = "devices",
			field = "video",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getVideo",
			setMethod = "setVideo"
	)
	private Byte video;

	@Column(
			table = "devices",
			field = "type",
			type = "enum('3G Modem','Fixed Wireless Terminal','GSM Internal Card','GSM Modem','Mobile Phone','Other Device','Payment Terminal','PCMCIA Card','Smartphone','Tablet','USB Key')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getType",
			setMethod = "setType"
	)
	private String type;

	@Column(
			table = "devices",
			field = "screen_size",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getScreenSize",
			setMethod = "setScreenSize"
	)
	private Integer screen_size;

	@Column(
			table = "devices",
			field = "launch_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLaunchDate",
			setMethod = "setLaunchDate"
	)
	private Date launch_date;

	@Column(
			table = "devices",
			field = "price",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getPrice",
			setMethod = "setPrice"
	)
	private Short price;


	public Devices() {} 

	public Devices( ResultSet rs ) throws SQLException {

		this.tac = rs.getString( Devices.Fields.tac.name() );
		this.brand = rs.getString( Devices.Fields.brand.name() );
		this.model = rs.getString( Devices.Fields.model.name() );
		this.OS = rs.getString( Devices.Fields.OS.name() );
		this.bearers = rs.getString( Devices.Fields.bearers.name() );
		this.GPS = rs.getByte( Devices.Fields.GPS.name() );
		this.camera = rs.getByte( Devices.Fields.camera.name() );
		this.video = rs.getByte( Devices.Fields.video.name() );
		this.type = rs.getString( Devices.Fields.type.name() );
		this.screen_size = rs.getInt( Devices.Fields.screen_size.name() );
		this.launch_date = rs.getDate( Devices.Fields.launch_date.name() );
		this.price = rs.getShort( Devices.Fields.price.name() );

	}

	public Devices( JSONObject jo ) throws JSONException, ParseException {

		this.tac = jo.getString( Devices.Fields.tac.name() );
		this.brand = jo.getString( Devices.Fields.brand.name() );
		this.model = jo.getString( Devices.Fields.model.name() );
		this.OS = jo.getString( Devices.Fields.OS.name() );
		this.bearers = jo.getString( Devices.Fields.bearers.name() );
		this.GPS = (byte)jo.getInt( Devices.Fields.GPS.name() );
		this.camera = (byte)jo.getInt( Devices.Fields.camera.name() );
		this.video = (byte)jo.getInt( Devices.Fields.video.name() );
		this.type = jo.getString( Devices.Fields.type.name() );
		this.screen_size = (int)jo.getInt( Devices.Fields.screen_size.name() );
		this.launch_date = Format.getMysqlDateTime( jo.getString( Devices.Fields.launch_date.name() ) );
		this.price = (short)jo.getInt( Devices.Fields.price.name() );

	}

	public String getTac() {

		return this.tac;

	}

	public void setTac( String tac ) {

		this.tac = tac;

	}

	public String getBrand() {

		return this.brand;

	}

	public void setBrand( String brand ) {

		this.brand = brand;

	}

	public String getModel() {

		return this.model;

	}

	public void setModel( String model ) {

		this.model = model;

	}

	public String getOS() {

		return this.OS;

	}

	public void setOS( String OS ) {

		this.OS = OS;

	}

	public String getBearers() {

		return this.bearers;

	}

	public void setBearers( String bearers ) {

		this.bearers = bearers;

	}

	public Byte getGPS() {

		return this.GPS;

	}

	public void setGPS( Byte GPS ) {

		this.GPS = GPS;

	}

	public Byte getCamera() {

		return this.camera;

	}

	public void setCamera( Byte camera ) {

		this.camera = camera;

	}

	public Byte getVideo() {

		return this.video;

	}

	public void setVideo( Byte video ) {

		this.video = video;

	}

	public String getType() {

		return this.type;

	}

	public void setType( String type ) {

		this.type = type;

	}

	public Integer getScreenSize() {

		return this.screen_size;

	}

	public void setScreenSize( Integer screen_size ) {

		this.screen_size = screen_size;

	}

	public Date getLaunchDate() {

		return this.launch_date;

	}

	public void setLaunchDate( Date launch_date ) {

		this.launch_date = launch_date;

	}

	public Short getPrice() {

		return this.price;

	}

	public void setPrice( Short price ) {

		this.price = price;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tac\": \"" ).append( this.getTac() ).append( "\", " )
			.append( "\"brand\": \"" ).append( this.getBrand() ).append( "\", " )
			.append( "\"model\": \"" ).append( this.getModel() ).append( "\", " )
			.append( "\"OS\": \"" ).append( this.getOS() ).append( "\", " )
			.append( "\"bearers\": \"" ).append( this.getBearers() ).append( "\", " )
			.append( "\"GPS\": \"" ).append( this.getGPS() ).append( "\", " )
			.append( "\"camera\": \"" ).append( this.getCamera() ).append( "\", " )
			.append( "\"video\": \"" ).append( this.getVideo() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"screen_size\": \"" ).append( this.getScreenSize() ).append( "\", " )
			.append( "\"launch_date\": \"" ).append( this.getLaunchDate() ).append( "\", " )
			.append( "\"price\": \"" ).append( this.getPrice() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }