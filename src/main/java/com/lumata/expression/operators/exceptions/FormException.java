package com.lumata.expression.operators.exceptions;

import javax.ws.rs.core.Response;

import com.lumata.common.testing.response.ResponseCodes;

/**
 * This is thrown when a EnvironmentException occurs.
 *
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class FormException extends Exception
{

	private static final long serialVersionUID = 1L;
	private int errorCode = -1;
	private String errorType = "";
	private boolean loggable;
	private Response response;
      
	public FormException() {
      super(ResponseCodes.SC_IO.getMessage());
      this.errorCode = ResponseCodes.SC_IO.getCode();
	}
     	
   public FormException(String s, Response response)
   {
      super(s);
      this.response = response;
   }

   public FormException(String s, Throwable throwable, Response response)
   {
      super(s, throwable);
      this.response = response;
   }

   public FormException(Throwable throwable, Response response)
   {
      super(ResponseCodes.SC_IO.getMessage(), throwable);      
      this.response = response;
   }

   public FormException(String s, Throwable throwable)
   {
      super(s, throwable);
      this.errorCode = ResponseCodes.SC_IO.getCode();
   }

   public FormException(Throwable throwable)
   {
      super(ResponseCodes.SC_IO.getMessage(), throwable);
      this.errorCode = ResponseCodes.SC_IO.getCode();
   }

   public FormException(String s)
   {
	   super(s);
	   this.errorCode = ResponseCodes.SC_IO.getCode();
   }

   public FormException(int errorCode)
   {
      this.errorCode = errorCode;
   }

   public FormException(String s, int errorCode)
   {
      super(s);
      this.errorCode = errorCode;
   }
   
   public FormException(String s, String errorType, int errorCode)
   {
      super(s);
      this.errorType = errorType;
      this.errorCode = errorCode;
   }

   public FormException(String s, Throwable throwable, int errorCode)
   {
      super(s, throwable);
      this.errorCode = errorCode;
   }

   public FormException(Throwable throwable, int errorCode)
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

