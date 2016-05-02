package com.g4s.common.testing.exceptions;

/**
 * This is thrown when a DataBaseException occurs.
 * 
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 * 
 */
public class DataBaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2131035682210202359L;

	public DataBaseException() {
		super();
	}

	public DataBaseException(String s) {
		super(s);
	}
}
