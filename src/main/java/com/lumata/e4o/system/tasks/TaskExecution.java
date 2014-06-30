package com.lumata.e4o.system.tasks;

public class TaskExecution {

	private ExecutionStatus status;
	
	/** tasks execution statuses */
	public enum ExecutionStatus {
		OK,
		KO,
		ALREADY_DONE,
		UNDEFINED;			
	}
	
	public TaskExecution( ExecutionStatus status ) {
		
		this.status = status;
		
	}
	
	public ExecutionStatus getStatus() {
		
		return this.status;
		
	}
	
}
