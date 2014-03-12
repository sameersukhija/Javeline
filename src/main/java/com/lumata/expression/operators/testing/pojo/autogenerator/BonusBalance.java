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
import java.sql.Timestamp;


@Table( "bonus_balance" )
public class BonusBalance { 

	public enum Fields { msisdn, bonus_id, qty_balance, qty_balance_in, start_date, end_date, validity_type, update_time }

	@Column(
			table = "bonus_balance",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "bonus_balance",
			field = "bonus_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getBonusId",
			setMethod = "setBonusId"
	)
	private Short bonus_id;

	@Column(
			table = "bonus_balance",
			field = "qty_balance",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyBalance",
			setMethod = "setQtyBalance"
	)
	private Integer qty_balance;

	@Column(
			table = "bonus_balance",
			field = "qty_balance_in",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyBalanceIn",
			setMethod = "setQtyBalanceIn"
	)
	private Integer qty_balance_in;

	@Column(
			table = "bonus_balance",
			field = "start_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "bonus_balance",
			field = "end_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "bonus_balance",
			field = "validity_type",
			type = "enum('Fixed','Variable_Days','Variable_Weeks','Variable_Months','Variable_Quarters')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getValidityType",
			setMethod = "setValidityType"
	)
	private String validity_type;

	@Column(
			table = "bonus_balance",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public BonusBalance() {} 

	public BonusBalance( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( BonusBalance.Fields.msisdn.name() );
		this.bonus_id = rs.getShort( BonusBalance.Fields.bonus_id.name() );
		this.qty_balance = rs.getInt( BonusBalance.Fields.qty_balance.name() );
		this.qty_balance_in = rs.getInt( BonusBalance.Fields.qty_balance_in.name() );
		this.start_date = rs.getDate( BonusBalance.Fields.start_date.name() );
		this.end_date = rs.getDate( BonusBalance.Fields.end_date.name() );
		this.validity_type = rs.getString( BonusBalance.Fields.validity_type.name() );
		this.update_time = rs.getTimestamp( BonusBalance.Fields.update_time.name() );

	}

	public BonusBalance( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( BonusBalance.Fields.msisdn.name() );
		this.bonus_id = (short)jo.getInt( BonusBalance.Fields.bonus_id.name() );
		this.qty_balance = (int)jo.getInt( BonusBalance.Fields.qty_balance.name() );
		this.qty_balance_in = (int)jo.getInt( BonusBalance.Fields.qty_balance_in.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( BonusBalance.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( BonusBalance.Fields.end_date.name() ) );
		this.validity_type = jo.getString( BonusBalance.Fields.validity_type.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( BonusBalance.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Short getBonusId() {

		return this.bonus_id;

	}

	public void setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

	}

	public Integer getQtyBalance() {

		return this.qty_balance;

	}

	public void setQtyBalance( Integer qty_balance ) {

		this.qty_balance = qty_balance;

	}

	public Integer getQtyBalanceIn() {

		return this.qty_balance_in;

	}

	public void setQtyBalanceIn( Integer qty_balance_in ) {

		this.qty_balance_in = qty_balance_in;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public void setStartDate( Date start_date ) {

		this.start_date = start_date;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public void setEndDate( Date end_date ) {

		this.end_date = end_date;

	}

	public String getValidityType() {

		return this.validity_type;

	}

	public void setValidityType( String validity_type ) {

		this.validity_type = validity_type;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\", " )
			.append( "\"qty_balance\": \"" ).append( this.getQtyBalance() ).append( "\", " )
			.append( "\"qty_balance_in\": \"" ).append( this.getQtyBalanceIn() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"validity_type\": \"" ).append( this.getValidityType() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }