package com.lumata.e4o.regressions;

public class TestCase {

	private String testClassName;
	private String testMethodName;
	private String testStartDate;
	private String testExecutionTime;
	private String testStatus;
	private String testStackTrace;
	
	public TestCase() {}
	
	public TestCase( String testClassName, String testMethodName, String testStatus, String testStartDate, String testExecutionTime, String testStackTrace ) {
		this.setTestClassName( testClassName );
		this.setTestMethodName( testMethodName );
		this.setTestStatus( testStatus );
		this.setTestStartDate( testStartDate );
		this.setTestExecutionTime( testExecutionTime );
		this.setTestStackTrace( testStackTrace );
	}
	
	public String getTestClassName() {
		return this.testClassName;
	}

	public String getTestMethodName() {
		return this.testMethodName;
	}
	
	public String getTestStartDate() {
		return this.testStartDate;
	}
	
	public String getTestExecutionTime() {
		return this.testExecutionTime;
	}
	
	public String getTestStatus() {
		return this.testStatus;
	}
	
	public String getTestStackTrace() {
		return this.testStackTrace;
	}

	public void setTestClassName( String testClassName ) {
		this.testClassName = testClassName;
	}

	public void setTestMethodName( String testMethodName ) {
		this.testMethodName = testMethodName;
	}
	
	public void setTestStartDate( String testStartDate ) {
		this.testStartDate = testStartDate;
	}
	
	public void setTestExecutionTime( String testExecutionTime ) {
		this.testExecutionTime = testExecutionTime;
	}
	
	public void setTestStatus( String testStatus ) {
		this.testStatus = testStatus;
	}

	public void setTestStackTrace( String testStackTrace ) {
		this.testStackTrace = testStackTrace;
	}
	
}
