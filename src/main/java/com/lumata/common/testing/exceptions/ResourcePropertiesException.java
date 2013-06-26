package com.lumata.common.testing.exceptions;

import javax.ws.rs.core.Response;

import com.lumata.common.testing.system.ResponseCodes;

/**
 * This is thrown when a IOSExceptions occurs.
 *
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class ResourcePropertiesException extends Exception
{

	private static final long serialVersionUID = 1L;
	protected int errorCode = -1;
	protected String errorType = "";
	protected boolean loggable;
	protected Response response;
      
	public ResourcePropertiesException() {
      super(ResponseCodes.SC_IO.getMessage());
      this.errorCode = ResponseCodes.SC_IO.getCode();
	}
     	
   public ResourcePropertiesException(String s, Response response)
   {
      super(s);
      this.response = response;
   }

   public ResourcePropertiesException(String s, Throwable throwable, Response response)
   {
      super(s, throwable);
      this.response = response;
   }

   public ResourcePropertiesException(Throwable throwable, Response response)
   {
      super(ResponseCodes.SC_IO.getMessage(), throwable);      
      this.response = response;
   }

   public ResourcePropertiesException(String s, Throwable throwable)
   {
      super(s, throwable);
      this.errorCode = ResponseCodes.SC_IO.getCode();
   }

   public ResourcePropertiesException(Throwable throwable)
   {
      super(ResponseCodes.SC_IO.getMessage(), throwable);
      this.errorCode = ResponseCodes.SC_IO.getCode();
   }

   public ResourcePropertiesException(String s)
   {
	   super(s);
	   this.errorCode = ResponseCodes.SC_IO.getCode();
   }

   public ResourcePropertiesException(int errorCode)
   {
      this.errorCode = errorCode;
   }

   public ResourcePropertiesException(String s, int errorCode)
   {
      super(s);
      this.errorCode = errorCode;
   }
   
   public ResourcePropertiesException(String s, String errorType, int errorCode)
   {
      super(s);
      this.errorType = errorType;
      this.errorCode = errorCode;
   }

   public ResourcePropertiesException(String s, Throwable throwable, int errorCode)
   {
      super(s, throwable);
      this.errorCode = errorCode;
   }

   public ResourcePropertiesException(Throwable throwable, int errorCode)
   {
      super(ResponseCodes.SC_IO.getMessage(), throwable);
      this.errorCode = errorCode;
   }

   public int getErrorCode()
   {
      return this.errorCode;
   }
   
   public String getErrorType()
   {
      return this.errorType;
   }

   public void setErrorCode(int errorCode)
   {
      this.errorCode = errorCode;
   }
   
   public void setErrorType(String errorType)
   {
      this.errorType = errorType;
   }

   public boolean isLoggable()
   {
      return this.loggable;
   }

   public void setLoggable(boolean loggable)
   {
      this.loggable = loggable;
   }

   public Response getResponse()
   {
      return response;
   }

}

