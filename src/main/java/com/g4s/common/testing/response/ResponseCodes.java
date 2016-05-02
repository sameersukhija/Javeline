package com.g4s.common.testing.response;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public enum ResponseCodes {
	
	/**
	 	* Status code (100) indicating the client can continue.
	*/	
	SC_CONTINUE ( 100 ) {
		public int getCode() { return 100; }
		public String getType() { return "Information"; }
		public String getMessage() { return "Continue"; }		
	},
	/**
	    * Status code (101) indicating the server is switching protocols
	    * according to Upgrade header.
	*/
	SC_SWITCHING_PROTOCOLS ( 101 ) {
		public int getCode() { return 101; }
		public String getType() { return "Information"; }
		public String getMessage() { return "Switching Protocols"; }
	},
	/**
	    * Status code (200) indicating the request succeeded normally.
	*/
	SC_OK ( 200 ) {
		public int getCode() { return 200; }
		public String getType() { return "Success"; }
		public String getMessage() { return "OK"; }
	},
	/**
	    * Status code (201) indicating the request succeeded and created
	    * a new resource on the server.
	*/
	SC_CREATED ( 201 ) {
		public int getCode() { return 201; }
		public String getType() { return "Success"; }
		public String getMessage() { return "Created"; }
	},
	/**
	    * Status code (202) indicating that a request was accepted for
	    * processing, but was not completed.
	*/
	SC_ACCEPTED ( 202 ) {
		public int getCode() { return 202; }
		public String getType() { return "Success"; }
		public String getMessage() { return "Accepted"; }
	},
	/**
	    * Status code (203) indicating that the meta information presented
	    * by the client did not originate from the server.
	*/
	SC_NON_AUTHORITATIVE_INFORMATION ( 203 ) {
		public int getCode() { return 203; }
		public String getType() { return "Success"; }
		public String getMessage() { return "Non-Authoritative Information"; }
	},
	/**
	    * Status code (204) indicating that the request succeeded but that
	    * there was no new information to return.
	*/
	SC_NO_CONTENT ( 204 ) {
		public int getCode() { return 204; }
		public String getType() { return "Success"; }
		public String getMessage() { return "No Content"; }
	},
	/**
	    * Status code (205) indicating that the agent <em>SHOULD</em> reset
	    * the document view which caused the request to be sent.
	*/
	SC_RESET_CONTENT ( 205 ) {
		public int getCode() { return 205; }
		public String getType() { return "Success"; }
		public String getMessage() { return "Reset Content"; }
	},
	/**
	    * Status code (206) indicating that the server has fulfilled
	    * the partial GET request for the resource.
	*/
	SC_PARTIAL_CONTENT ( 206 ) {
		public int getCode() { return 206; }
		public String getType() { return "Success"; }
		public String getMessage() { return "Partial Content"; }
	},
	/**
	    * Status code (300) indicating that the requested resource
	    * corresponds to any one of a set of representations, each with
	    * its own specific location.
	*/
	SC_MULTIPLE_CHOICES ( 300 ) {
		public int getCode() { return 300; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "Multiple Choices"; }
	},
	/**
	    * Status code (301) indicating that the resource has permanently
	    * moved to a new location, and that future references should use a
	    * new URI with their requests.
	*/
	SC_MOVED_PERMANENTLY ( 301 ) {
		public int getCode() { return 301; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "Moved Permanently"; }
	},
	/**
	    * Status code (302) indicating that the resource has temporarily
	    * moved to another location, but that future references should
	    * still use the original URI to access the resource.
	    * <p/>
	    * This definition is being retained for backwards compatibility.
	    * SC_FOUND is now the preferred definition.
	*/
	SC_MOVED_TEMPORARILY ( 302 ) {
		public int getCode() { return 302; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "Moved Temporarily"; }
	},
	/**
	    * Status code (302) indicating that the resource reside
	    * temporarily under a different URI. Since the redirection might
	    * be altered on occasion, the client should continue to use the
	    * Request-URI for future requests.(HTTP/1.1) To represent the
	    * status code (302), it is recommended to use this variable.
	*/
	SC_FOUND( 302 ) {
		public int getCode() { return 302; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "FOUND"; }
	},
	/**
	    * Status code (303) indicating that the response to the request
	    * can be found under a different URI.
	*/
	SC_SEE_OTHER( 303 ) {
		public int getCode() { return 303; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "See Other"; }
	},
	/**
	    * Status code (304) indicating that a conditional GET operation
	    * found that the resource was available and not modified.
	*/
	SC_NOT_MODIFIED( 304 ) {
		public int getCode() { return 304; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "Not Modified"; }
	},
	/**
	    * Status code (305) indicating that the requested resource
	    * <em>MUST</em> be accessed through the proxy given by the
	    * <code><em>Location</em></code> field.
	*/
	SC_USE_PROXY( 305 ) {
		public int getCode() { return 305; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "Use Proxy"; }
	},
	/**
	    * Status code (307) indicating that the requested resource
	    * resides temporarily under a different URI. The temporary URI
	    * <em>SHOULD</em> be given by the <code><em>Location</em></code>
	    * field in the response.
	*/
	SC_TEMPORARY_REDIRECT( 307 ) {
		public int getCode() { return 307; }
		public String getType() { return "Redirection Error"; }
		public String getMessage() { return "Temporary Redirect"; }
	},
	/**
	    * Status code (400) indicating the request sent by the client was
	    * syntactically incorrect.
	*/
	SC_BAD_REQUEST( 400 ) {
		public int getCode() { return 400; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Bad Request"; }
	},
	/**
	    * Status code (401) indicating that the request requires HTTP
	    * authentication.
	*/
	SC_UNAUTHORIZED( 401 ) {
		public int getCode() { return 401; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Unauthorized"; }
	},
	/**
	    * Status code (402) reserved for future use.
	*/
	SC_PAYMENT_REQUIRED( 402 ) {
		public int getCode() { return 402; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Payment Required"; }
	},
	/**
	    * Status code (403) indicating the server understood the request
	    * but refused to fulfill it.
	*/
	SC_FORBIDDEN( 403 ) {
		public int getCode() { return 403; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Forbidden"; }
	},
	/**
	    * Status code (404) indicating that the requested resource is not
	    * available.
	*/
	SC_NOT_FOUND( 404 ) {
		public int getCode() { return 404; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Not Found"; }
	},
	/**
	    * Status code (405) indicating that the method specified in the
	    * <code><em>Request-Line</em></code> is not allowed for the resource
	    * identified by the <code><em>Request-URI</em></code>.
	*/
	SC_METHOD_NOT_ALLOWED( 405 ) {
		public int getCode() { return 405; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Method Not Allowed"; }
	},
	/**
	    * Status code (406) indicating that the resource identified by the
	    * request is only capable of generating response entities which have
	    * content characteristics not acceptable according to the accept
	    * headers sent in the request.
	*/
	SC_NOT_ACCEPTABLE( 406 ) {
		public int getCode() { return 406; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Not Acceptable"; }
	},
	/**
	 	* Status code (407) indicating that the client <em>MUST</em> first
	    * authenticate itself with the proxy.
	*/
	SC_PROXY_AUTHENTICATION_REQUIRED( 407 ) {
		public int getCode() { return 407; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Proxy Authentication Required"; }
	},
	/**
	    * Status code (408) indicating that the client did not produce a
	    * request within the time that the server was prepared to wait.
	*/
	SC_REQUEST_TIMEOUT( 408 ) {
		public int getCode() { return 408; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Request Time-out"; }
	},
	/**
	    * Status code (409) indicating that the request could not be
	    * completed due to a conflict with the current state of the
	    * resource.
	*/
	SC_CONFLICT( 409 ) {
		public int getCode() { return 409; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Conflict"; }
	},
	/**
	    * Status code (410) indicating that the resource is no longer
	    * available at the server and no forwarding address is known.
	    * This condition <em>SHOULD</em> be considered permanent.
	*/
	SC_GONE( 410 ) {
		public int getCode() { return 410; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Gone"; }
	},	
	/**
	    * Status code (411) indicating that the request cannot be handled
	    * without a defined <code><em>Content-Length</em></code>.
	*/
	SC_LENGTH_REQUIRED( 411 ) {
		public int getCode() { return 411; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Length Required"; }
	},
	/**
	    * Status code (412) indicating that the precondition given in one
	    * or more of the request-header fields evaluated to false when it
	    * was tested on the server.
	*/
	SC_PRECONDITION_FAILED( 412 ) {
		public int getCode() { return 412; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Precondition Failed"; }
	},
	/**
	    * Status code (413) indicating that the server is refusing to process
	    * the request because the request entity is larger than the server is
	    * willing or able to process.
	*/
	SC_REQUEST_ENTITY_TOO_LARGE( 413 ) {
		public int getCode() { return 413; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Request Entity Too Large"; }
	},
	/**
	    * Status code (414) indicating that the server is refusing to service
	    * the request because the <code><em>Request-URI</em></code> is longer
	    * than the server is willing to interpret.
	*/
	SC_REQUEST_URI_TOO_LONG( 414 ) {
		public int getCode() { return 414; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Request-URI Too Large"; }
	},
	/**
	    * Status code (415) indicating that the server is refusing to service
	    * the request because the entity of the request is in a format not
	    * supported by the requested resource for the requested method.
	*/
	SC_UNSUPPORTED_MEDIA_TYPE( 415 ) {
		public int getCode() { return 415; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Unsupported Media Type"; }
	},
	/**
	    * Status code (416) indicating that the server cannot serve the
	    * requested byte range.
	*/
	SC_REQUESTED_RANGE_NOT_SATISFIABLE( 416 ) {
		public int getCode() { return 416; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Requested Range Not Satisfiable"; }
	},
	/**
	    * Status code (417) indicating that the server could not meet the
	    * expectation given in the Expect request header.
	*/
	SC_EXPECTATION_FAILED( 417 ) {
		public int getCode() { return 417; }
		public String getType() { return "Client Error"; }
		public String getMessage() { return "Expectation Failed"; }
	},
	/**
	    * Status code (500) indicating an error inside the HTTP server
	    * which prevented it from fulfilling the request.
	*/
	SC_INTERNAL_SERVER_ERROR( 500 ) {
		public int getCode() { return 500; }
		public String getType() { return "Server Error"; }
		public String getMessage() { return "Internal Server Error"; }
	},
	/**
	    * Status code (501) indicating the HTTP server does not support
	    * the functionality needed to fulfill the request.
	*/
	SC_NOT_IMPLEMENTED( 501 ) {
		public int getCode() { return 501; }
		public String getType() { return "Server Error"; }
		public String getMessage() { return "Not Implemented"; }
	},
	/**
	    * Status code (502) indicating that the HTTP server received an
	    * invalid response from a server it consulted when acting as a
	    * proxy or gateway.
	*/
	SC_BAD_GATEWAY( 502 ) {
		public int getCode() { return 502; }
		public String getType() { return "Server Error"; }
		public String getMessage() { return "Bad Gateway"; }
	},
	/**
	    * Status code (503) indicating that the HTTP server is
	    * temporarily overloaded, and unable to handle the request.
	*/
	SC_SERVICE_UNAVAILABLE( 503 ) {
		public int getCode() { return 503; }
		public String getType() { return "Server Error"; }
		public String getMessage() { return "Service Unavailable"; }
	},
	/**
	    * Status code (504) indicating that the server did not receive
	    * a timely response from the upstream server while acting as
	    * a gateway or proxy.
	*/
	SC_GATEWAY_TIMEOUT( 504 ) {
		public int getCode() { return 504; }
		public String getType() { return "Server Error"; }
		public String getMessage() { return "Gateway Timeout"; }
	},
	/**
	    * Status code (505) indicating that the server does not support
	    * or refuses to support the HTTP protocol version that was used
	    * in the request message.
	*/
	SC_HTTP_VERSION_NOT_SUPPORTED( 505 ) {
		public int getCode() { return 505; }
		public String getType() { return "Server Error"; }
		public String getMessage() { return "Http Version Not Supported"; }
	},
	/**
	 * Status code (10000) indicating generic error.
	 */	
	SC_GENERIC_ERROR ( 10000 ) {
		public int getCode() { return 10000; }
		public String getType() { return "Generic Error"; }
		public String getMessage() { return "Generic Error"; }
	},
	/**
	 * Status code (10001) indicating sql error.
	 */	
	SC_QUERY ( 10001 ) {
		public int getCode() { return 10001; }
		public String getType() { return "SQL Error"; }
		public String getMessage() { return "SQL Statement Error"; }
	},
	/**
	 * Status code (10002) indicating component error.
	 */	
	SC_COMPONENT ( 10002 ) {
		public int getCode() { return 10001; }
		public String getType() { return "Component Error"; }
		public String getMessage() { return "Component Error"; }
	},
	/**
	 * Status code (10003) indicating io error.
	 */	
	SC_IO ( 10003 ) {
		public int getCode() { return 10003; }
		public String getType() { return "IO Error"; }
		public String getMessage() { return "IO Error"; }
	},
	/**
	 * Status code (10004) indicating json error.
	 */	
	SC_JSON ( 10004 ) {
		public int getCode() { return 10004; }
		public String getType() { return "Malformed JSON"; }
		public String getMessage() { return "Malformed JSON"; }
	};
	
	public abstract int getCode();
	public abstract String getMessage();
	public abstract String getType();
	
	private final int code;
	
	ResponseCodes( int code ) {   // constructor
	      this.code = code;	      
	}	
	
}
