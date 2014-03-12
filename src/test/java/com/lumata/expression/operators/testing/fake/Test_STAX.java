package com.lumata.expression.operators.testing.fake;

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

public class Test_STAX {

	private static final Logger logger = LoggerFactory.getLogger( Test_STAX.class );
	
	@Test()
	public void sax() {
		
		String xmlStr = "<fault xmlns:faul=\"com/act750/xmlrpc/fault\"><code>400</code><message>The system was not able to refuse all the offers for the pair &lt;MSISDN, token code&gt;: &lt;331234567, gl-abcdef&gt;</message></fault>";	
		
	    InputStream is = new ByteArrayInputStream( xmlStr.getBytes() ); 
	    
	    logger.info( xmlStr );
	    
	    //List<item> items = new LinkedList<item>();  
        try {  
            
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        	
        	XMLEventReader inputEventReader = inputFactory.createXMLEventReader( is );
        	
        	while( inputEventReader.hasNext() ) {
        		
        		XMLEvent event = inputEventReader.nextEvent(); 
        		
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

        	}
        	
        	
        	
        	
        	
        	/*
        	//create xml reader event with inputstream  
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();  
            InputStream input = new FileInputStream(xmlFile);  
            XMLEventReader inputEventReader = inputFactory.createXMLEventReader(input);  
              
            Item newItem = new Item();  
              
            while(inputEventReader.hasNext()){  
                //xml reader event to get event and determine start element and end element  
                XMLEvent event = inputEventReader.nextEvent();  
                  
                if (event.isStartElement()) {  
                    StartElement startElement = event.asStartElement();  
                    String startElementName = startElement.getName().getLocalPart();  
                    if(startElementName.equals("item")) {  
                        newItem = new Item();  
                        Iterator<attribute> attributes = startElement.getAttributes();  
                        while(attributes.hasNext()){  
                            Attribute attribute = attributes.next();  
                            String attributeName = attribute.getName().getLocalPart();   
                            if(attributeName.equals("date")){  
                                newItem.setDate(attribute.getValue());  
                            }  
                        }  
                    }  
                      
                    if(startElementName.equals("mode"))  
                    {  
                        event = inputEventReader.nextEvent();  
                        newItem.setMode(event.asCharacters().getData());  
                    }  
                      
                    if(startElementName.equals("unit"))  
                    {  
                        event = inputEventReader.nextEvent();  
                        newItem.setUnit(event.asCharacters().getData());  
                    }  
                      
                    if(startElementName.equals("current"))  
                    {  
                        event = inputEventReader.nextEvent();  
                        newItem.setCurrent(event.asCharacters().getData());  
                    }  
                      
                    if(startElementName.equals("interactive"))  
                    {  
                        event = inputEventReader.nextEvent();  
                        newItem.setInteractive(event.asCharacters().getData());  
                    }  
                }  
                  
                if(event.isEndElement())  
                {  
                    EndElement endElement = event.asEndElement();  
                    String endElementName = endElement.asEndElement().getName().getLocalPart();  
                    if(endElementName.equals("item")){  
                        items.add(newItem);  
                    }  
                }  
            }  
            */
        } catch( Exception e ) {
        	
            logger.info(e.getMessage(), e);  
        
        }
		
	}
	
}
