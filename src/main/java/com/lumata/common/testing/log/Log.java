package com.lumata.common.testing.log;

/**
 * This allows to manage the log messages.
 *
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public enum Log {
	
	VALIDATING, 
	LOADING, 
	CHECKING, 
	SELECTING, 
	GETTING, 
	PUTTING, 
	PASSED, 
	FAILED;
	
	/**
	 * @param message The message to write in the log
	 * @return message - The custom message to write in the log
	 */
	public String createMessage( String message ) {
		return this.toString() + " " + message;
	};
		
	/**
	 * @param className The Class Name to write in the log
	 * @param message The message to write in the log
	 * @return message - The custom message to write in the log
	 */
	public String createMessage( String className, String message ) {
		return "[ " + className + " ] " + createMessage( message );
	};

};
