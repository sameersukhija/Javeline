package com.lumata.expression.operators.testing.a;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlString;
import org.codehaus.stax2.XMLInputFactory2;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.generators.ClassGenerator;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser;

public class TestRestClient {

	private static final Logger logger = LoggerFactory.getLogger( TestRestClient.class );
	
	Environment env;
	
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Test
	public void post() {
				
		//options.put( RestClient.Options.REQUEST_BODY.name() , "<methodCall><methodName>system.listMethods</methodName><params></params></methodCall>" );
		//options.put( RestClient.Options.REQUEST_BODY.name() , "<methodCall><methodName>system.listMethods</methodName><params></params></methodCall>" );
		
		
		/*
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getStringParam( "331234560") );
		params.add( HTTPXMLRPCForm.getStringParam( "gl-324235") );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.offeroptimizer_allocate.call( env.getLink() + "xmlrpc/" , params );
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><fault xmlns:faul=\"com/act750/xmlrpc/fault\"><code>400</code><message>The system was not able to allocate the offers for the pair &lt;MSISDN, token code&gt;: &lt;331234560, gl-324235&gt;</message></fault></value></param></params></methodResponse>";
		
		XMLRPCResultParser resultParser = new XMLRPCResultParser( xml );
		
		resultParser.parse();
		*/
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getCustoEventParam( "331234560", HTTPXMLRPCForm.EventTypes.revenue, new HashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); } } ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( env.getLink() + "xmlrpc/" , params );
		
		System.out.println( response.getEntity().toString() );
		
		/*
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( RestClient.Options.REQUEST_CONTENT_TYPE.name(), RestClient.ContentTypes.APPLICATION_JSON.getValue() );
		options.put( RestClient.Options.REQUEST_BODY.name(), HTTPXMLRPCForm.getPOSTBody( "offeroptimizer.allocate", params ) );
		*/
		
		//"http://10.120.38.25:7070/xmlrpc/"
		
		
		/*
		System.out.println( response.getEntity().toString() );
		
		try {
		
			InputStream in = new ByteArrayInputStream( response.getEntity().toString().getBytes("UTF-8")); 
			
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		
			XMLEventReader reader = inputFactory.createXMLEventReader( in );
			
			while( reader.hasNext() ) {
		        
				XMLEvent event = (XMLEvent)reader.next();

				/*
		        if( event.isStartElement() ) {
		            StartElement element = event.asStartElement();

		            if (element.getName().getLocalPart().equals("ElementOne"))
		            {
		                event = (XMLEvent) reader.next();

		                if (event.isCharacters())
		                {
		                     String elementOne = event.asCharacters().getData();
		                     object.setElementOne(elementOne);
		                }
		                continue;
		            }
		            if (element.getName().getLocalPart().equals("ElementTwo"))
		            {
		                event = (XMLEvent) reader.next();
		                if (event.isCharacters())
		                {
		                     String elementTwo = event.asCharacters().getData();
		                     object.setElementTwo(elementTwo);
		                }
		                continue;
		            }
		        }
		        */
		/*    }
			
		} catch( XMLStreamException e ) {
			
			logger.info( e.getMessage(), e );
			
		} catch( UnsupportedEncodingException e ) {
			
			logger.info( e.getMessage(), e );
			
		}
			
		*/	
	}
	
}
