package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_throughput_rule" )
public class DmThroughputRule { 

	public enum Fields { throughput_rule_id, queue_from_id, throttle, time_period_millis, queue_to_id }

	@Column(
			table = "dm_throughput_rule",
			field = "throughput_rule_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 11,
			getMethod = "getThroughputRuleId",
			setMethod = "setThroughputRuleId"
	)
	private Integer throughput_rule_id;

	@Column(
			table = "dm_throughput_rule",
			field = "queue_from_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQueueFromId",
			setMethod = "setQueueFromId"
	)
	private Integer queue_from_id;

	@Column(
			table = "dm_throughput_rule",
			field = "throttle",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getThrottle",
			setMethod = "setThrottle"
	)
	private Integer throttle;

	@Column(
			table = "dm_throughput_rule",
			field = "time_period_millis",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getTimePeriodMillis",
			setMethod = "setTimePeriodMillis"
	)
	private Integer time_period_millis;

	@Column(
			table = "dm_throughput_rule",
			field = "queue_to_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQueueToId",
			setMethod = "setQueueToId"
	)
	private Integer queue_to_id;


	public DmThroughputRule() {} 

	public DmThroughputRule( ResultSet rs ) throws SQLException {

		this.throughput_rule_id = rs.getInt( DmThroughputRule.Fields.throughput_rule_id.name() );
		this.queue_from_id = rs.getInt( DmThroughputRule.Fields.queue_from_id.name() );
		this.throttle = rs.getInt( DmThroughputRule.Fields.throttle.name() );
		this.time_period_millis = rs.getInt( DmThroughputRule.Fields.time_period_millis.name() );
		this.queue_to_id = rs.getInt( DmThroughputRule.Fields.queue_to_id.name() );

	}

	public DmThroughputRule( JSONObject jo ) throws JSONException {

		this.throughput_rule_id = (int)jo.getInt( DmThroughputRule.Fields.throughput_rule_id.name() );
		this.queue_from_id = (int)jo.getInt( DmThroughputRule.Fields.queue_from_id.name() );
		this.throttle = (int)jo.getInt( DmThroughputRule.Fields.throttle.name() );
		this.time_period_millis = (int)jo.getInt( DmThroughputRule.Fields.time_period_millis.name() );
		this.queue_to_id = (int)jo.getInt( DmThroughputRule.Fields.queue_to_id.name() );

	}

	public Integer getThroughputRuleId() {

		return this.throughput_rule_id;

	}

	public void setThroughputRuleId( Integer throughput_rule_id ) {

		this.throughput_rule_id = throughput_rule_id;

	}

	public Integer getQueueFromId() {

		return this.queue_from_id;

	}

	public void setQueueFromId( Integer queue_from_id ) {

		this.queue_from_id = queue_from_id;

	}

	public Integer getThrottle() {

		return this.throttle;

	}

	public void setThrottle( Integer throttle ) {

		this.throttle = throttle;

	}

	public Integer getTimePeriodMillis() {

		return this.time_period_millis;

	}

	public void setTimePeriodMillis( Integer time_period_millis ) {

		this.time_period_millis = time_period_millis;

	}

	public Integer getQueueToId() {

		return this.queue_to_id;

	}

	public void setQueueToId( Integer queue_to_id ) {

		this.queue_to_id = queue_to_id;

	}

	public Fields[] getEntityFields() {

		return DmThroughputRule.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"throughput_rule_id\": \"" ).append( this.getThroughputRuleId() ).append( "\", " )
			.append( "\"queue_from_id\": \"" ).append( this.getQueueFromId() ).append( "\", " )
			.append( "\"throttle\": \"" ).append( this.getThrottle() ).append( "\", " )
			.append( "\"time_period_millis\": \"" ).append( this.getTimePeriodMillis() ).append( "\", " )
			.append( "\"queue_to_id\": \"" ).append( this.getQueueToId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }