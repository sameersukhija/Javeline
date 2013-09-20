package com.lumata.expression.operators.testing.gui.xmlrpc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser;

public class Test_XMLRPCResultParser {

	private static final Logger logger = LoggerFactory.getLogger( Test_XMLRPCResultParser.class );
	
	@Test()
	public void sax() {
		
		String xml = "<fault xmlns:faul=\"com/act750/xmlrpc/fault\"><code>400</code><message>The system was not able to refuse all the offers for the pair &lt;MSISDN, token code&gt;: &lt;331234567, gl-abcdef&gt;</message></fault>";	
		
	    try {
	    	
	    	XMLRPCResultParser xmlrpcResult = new XMLRPCResultParser( xml );
	    	
	    	xmlrpcResult.parse();
	    
	    
        } catch( Exception e ) {
        	
            logger.info(e.getMessage(), e);  
        
        }
		
	}
	
}
