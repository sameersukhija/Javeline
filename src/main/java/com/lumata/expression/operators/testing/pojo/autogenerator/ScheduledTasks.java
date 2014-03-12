package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "scheduled_tasks" )
public class ScheduledTasks { 

	public enum Fields { name, start_date, stop_date, worker_id, pending_worker_id }

	@Column(
			table = "scheduled_tasks",
			field = "name",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "scheduled_tasks",
			field = "start_date",
			type = "varchar(10)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private String start_date;

	@Column(
			table = "scheduled_tasks",
			field = "stop_date",
			type = "varchar(19)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 19,
			getMethod = "getStopDate",
			setMethod = "setStopDate"
	)
	private String stop_date;

	@Column(
			table = "scheduled_tasks",
			field = "worker_id",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 15,
			getMethod = "getWorkerId",
			setMethod = "setWorkerId"
	)
	private String worker_id;

	@Column(
			table = "scheduled_tasks",
			field = "pending_worker_id",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "",
			extra = "",
			length = 100,
			getMethod = "getPendingWorkerId",
			setMethod = "setPendingWorkerId"
	)
	private String pending_worker_id;


	public ScheduledTasks() {} 

	public ScheduledTasks( ResultSet rs ) throws SQLException {

		this.name = rs.getString( ScheduledTasks.Fields.name.name() );
		this.start_date = rs.getString( ScheduledTasks.Fields.start_date.name() );
		this.stop_date = rs.getString( ScheduledTasks.Fields.stop_date.name() );
		this.worker_id = rs.getString( ScheduledTasks.Fields.worker_id.name() );
		this.pending_worker_id = rs.getString( ScheduledTasks.Fields.pending_worker_id.name() );

	}

	public ScheduledTasks( JSONObject jo ) throws JSONException {

		this.name = jo.getString( ScheduledTasks.Fields.name.name() );
		this.start_date = jo.getString( ScheduledTasks.Fields.start_date.name() );
		this.stop_date = jo.getString( ScheduledTasks.Fields.stop_date.name() );
		this.worker_id = jo.getString( ScheduledTasks.Fields.worker_id.name() );
		this.pending_worker_id = jo.getString( ScheduledTasks.Fields.pending_worker_id.name() );

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getStartDate() {

		return this.start_date;

	}

	public void setStartDate( String start_date ) {

		this.start_date = start_date;

	}

	public String getStopDate() {

		return this.stop_date;

	}

	public void setStopDate( String stop_date ) {

		this.stop_date = stop_date;

	}

	public String getWorkerId() {

		return this.worker_id;

	}

	public void setWorkerId( String worker_id ) {

		this.worker_id = worker_id;

	}

	public String getPendingWorkerId() {

		return this.pending_worker_id;

	}

	public void setPendingWorkerId( String pending_worker_id ) {

		this.pending_worker_id = pending_worker_id;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"stop_date\": \"" ).append( this.getStopDate() ).append( "\", " )
			.append( "\"worker_id\": \"" ).append( this.getWorkerId() ).append( "\", " )
			.append( "\"pending_worker_id\": \"" ).append( this.getPendingWorkerId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }