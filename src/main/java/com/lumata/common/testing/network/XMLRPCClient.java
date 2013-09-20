package com.lumata.common.testing.network;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLRPCClient {

	private static final  Logger logger = LoggerFactory.getLogger( XMLRPCClient.class );
	
	public static void main (String [] args) {
		  
		Object result;
		
		try {

			XmlRpcClientConfigImpl xmlrpcCfg = new XmlRpcClientConfigImpl();
			xmlrpcCfg.setServerURL( new URL("http://10.120.38.25:7070/angular/xmlrpcTest/index.html#/xmlrpc" ));
			//xmlrpcCfg.setEnabledForExtensions( true );  
			//xmlrpcCfg.setConnectionTimeout( 60 * 1000 );
			//xmlrpcCfg.setReplyTimeout( 60 * 1000 );
			
			//xmlrpcCfg.setBasicUserName( "superman" );
			//xmlrpcCfg.setBasicPassword( "super2010Man" );
			
			XmlRpcClient xmlrpcClient = new XmlRpcClient();
			
			xmlrpcClient.setConfig( xmlrpcCfg );
			
			/*
			Object[] params = new Object[] { 
					
					new String("2")
					
			};*/
		 
			HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("login", "superman");
            map.put("password","super2010Man");
            
			//Integer result = (Integer) 
					
			//xmlrpcClient.execute("Calculator.add", params);
		    
			logger.info( "#############" );
			
			result = xmlrpcClient.execute("offeroptimizer.allocate", new Vector<Object>() );
            
			logger.info( "--------------------------" );
			
			/*
			for (String a : args) {
                Integer freq = (Integer) result.get(a);
                result.put(a, (freq == null) ? 1 : freq + 1);
            }
            */
			logger.info( "--------------------------" );
			
			
			//System.out.println( xmlrpcClient.getConfig().toString());
			
	    /*
	     Vector params = new Vector();
	     params.addElement(new Integer(17));
	     params.addElement(new Integer(13));

	     Object result = server.execute("sample.sum", params);

	     int sum = ((Integer) result).intValue();
	     System.out.println("The sum is: "+ sum);
		*/
	     
		} catch ( IOException ex ) {
            System.out.println( "Network error: The server may not exist." );
            return;
        } catch ( XmlRpcException ex ) {
            System.out.println( "Procedure returned error message: '" + ex.getMessage() + "'." );
            return;
        }

        if ( result == null ) {
            System.out.println( "The server didn't return XML." );
            return;
        }

        if ( ! (result instanceof Boolean) ) {
            System.out.println( "The procedure didn't return a boolean." );
            return;
        }

        if ( ((Boolean) result) != Boolean.TRUE ) {
            System.out.println( "This should never happen." );
            return;
        }

        System.out.println( "Successfully pinged guest account." );

	}
	
}
