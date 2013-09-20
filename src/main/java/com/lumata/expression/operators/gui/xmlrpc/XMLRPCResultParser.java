package com.lumata.expression.operators.gui.xmlrpc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLRPCResultParser {

	private static final Logger logger = LoggerFactory.getLogger( XMLRPCResultParser.class );
	
	public enum ResultType { FAULT, OFFERS }
	
	public enum EventType { 
		
		DTD, 
		START_ELEMENT,
		END_ELEMENT,
		ENTITY_REFERNCE,
		CHARACTERS,
		PROCESSING_INSTRUCTION,
		COMMENT,
		START_DOCUMENT,
		END_DOCUMENT,
		ATTRIBUTE,
		NAMESPACE; 
		
		public static EventType get( int n ) { 
			
			return values()[n];
			
		}
	
	}
	
	
	private String xml;
	private XMLInputFactory inputFactory;
	private XMLEventReader inputEventReader;
	
	public XMLRPCResultParser( String xml ) {
		
		this.setXML( xml ); 
		 
		this.setXMLInputFactory( XMLInputFactory.newInstance() );
				
		this.setXMLEventReader();
		
	}
	
	public String getXML() {
		
		return this.xml;
		
	}
	
	public XMLInputFactory getXMLInputFactory() {
		
		return this.inputFactory;
		
	}
	
	public XMLEventReader getXMLEventReader() {
		
		return this.inputEventReader;
		
	}

	public void setXML( String xml ) {
		
		this.xml = xml;
		
	}
	
	public void setXMLInputFactory( XMLInputFactory inputFactory ) {
		
		this.inputFactory = inputFactory;
		
	}
	
	public void setXMLEventReader() {
		
		InputStream is = new ByteArrayInputStream( this.xml.getBytes() );
    	
    	try {
		
    		this.inputEventReader = this.inputFactory.createXMLEventReader( is );
		
    	} catch ( XMLStreamException e ) {
    		
    		logger.error( e.getMessage(), e );
    		
    	}
    	
	}
	
	public Map<ResultType, Object> parse() {
		
		Map<ResultType, Object> result = new HashMap<ResultType, Object>();
		
		
		
		while( this.inputEventReader.hasNext() ) {
    		
			try {
	    		
				XMLEvent event = this.inputEventReader.nextEvent(); 
	    		
				switch( XMLRPCResultParser.EventType.get( event.getEventType() ) ) {
				
					case START_DOCUMENT: {
											
						break;
					}
					case START_ELEMENT: {
						
						logger.info( "##### START_ELEMENT #####" );
						
						StartElement startElement = event.asStartElement(); 
						
						switch( ResultType.valueOf( startElement.getName().getLocalPart().toUpperCase() ) ) {
						
							case FAULT: {
								
								
								
								break;
							}
							case OFFERS: {
								
								
								break;
							}
						
						}
						
						logger.info( startElement.getName().getLocalPart() );
						
						break;
					}
					case CHARACTERS: {
						
						logger.info( "##### CHARACTERS #####" );
						
						break;
					}					
					case END_ELEMENT: {
						
						logger.info( "##### END_ELEMENT #####" );
						
						break;
					}
					case END_DOCUMENT: {
						
						logger.info( "##### END_DOCUMENT #####" );
						
						break;
					}
				}
				
				/*
				logger.info( "EVENT: " + event.getEventType() + " - " + XMLRPCResultParser.EventType.get( event.getEventType() ).toString() );
				
	    		if( event.isStartElement() ) { 
	    			
	    			StartElement startElement = event.asStartElement(); 
	    			
	    			logger.info( "##### START ELEMENT #####" );
	    			
	    			logger.info( startElement.getName().getLocalPart() );
	    			
	    			logger.info( startElement.getName().getNamespaceURI() );
	    			
	    			logger.info( startElement.getName().getPrefix() );  
	    			        			
	    		}
	    		
	    		if( event.isCharacters() ) { 
	    			
	    			Characters characters = event.asCharacters(); 
	    			
	    			logger.info( "##### CHARACTERS #####" );
	    			
	    			logger.info( characters.getData() );
	    		        			        			
	    		}
	    		
	    		if( event.isEndElement() ) { 
	    			
	    			EndElement endElement = event.asEndElement(); 
	    			
	    			logger.info( "##### END ELEMENT #####" );
	    			
	    			logger.info( endElement.getName().getLocalPart() );
	    			
	    			logger.info( endElement.getName().getNamespaceURI() );
	    			
	    			logger.info( endElement.getName().getPrefix() );  
	    			        			
	    		}
	    		*/
			
			} catch ( XMLStreamException e ) {
	    		
	    		logger.error( e.getMessage(), e );
	    		
	    	}
			
    	}
		
		return result;
		
	}
	
}
