package com.lumata.e4o.system.performance;

import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.dao.tenant.DAOCatalogOffers;


public class RunGenerateSubscriberRechargeAllocateAcceptPurchase {

	//private static final Logger logger = LoggerFactory.getLogger( RunGenerateSubscriberXMLRPCPurchase.class );
		
	ArrayList<GenerateSubscriberRechargeThread> threadsRecharge;
	ArrayList<GenerateXMLRPCAllocateThread> threadsXMLRPCAllocate;
	ArrayList<GenerateXMLRPCAcceptThread> threadsXMLRPCAccept;
	ArrayList<GenerateXMLRPCPurchaseThread> threadsXMLRPCPurchase;
	
	final int START_MSISDN = 200;
	final int N_THREADS = 20;
	final int THREAD_SLEEP = 0;
	final int EXECUTION_TIME = 120000;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	Mysql mysql;
	
	
	RunGenerateSubscriberRechargeAllocateAcceptPurchase() throws NetworkEnvironmentException {
				
		threadsRecharge = new ArrayList<GenerateSubscriberRechargeThread>();
		threadsXMLRPCAllocate = new ArrayList<GenerateXMLRPCAllocateThread>();
		threadsXMLRPCAccept = new ArrayList<GenerateXMLRPCAcceptThread>();
		threadsXMLRPCPurchase = new ArrayList<GenerateXMLRPCPurchaseThread>();
		
		env = new NetworkEnvironment( "input/environments", "e4o_qa3_ne", IOFileUtils.IOLoadingType.RESOURCE );
		
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
		mysql = new Mysql( env.getDataSource( "tenant" ) );
				
	}
	
	private void initializeThreads() {
		
		ArrayList<Subscribers> subscribers = DAOSubscribers.getInstance( mysql ).getSubscriberList();
		
		ArrayList<CatalogOffers> catalogOffersList = DAOCatalogOffers.getInstance( mysql ).getAllCatalogOffers();
		
		String[] channels = { "Ch A", "Ch B", "Ch C" };
						
		if( null != subscribers &&
			null != catalogOffersList &&	
			subscribers.size() > 0 &&
			catalogOffersList.size() > 0
		) {
		
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);	
					
			for( int i = 0; i < N_THREADS; i++ ) {
			    
				int c = (int)( Math.random() * channels.length );
				
				
				Long msisdn = subscribers.get( i ).getMsisdn();
				
				ArrayList<Token> tokensToAllocate = DAOToken.getInstance( mysql ).getAvailableActiveTokens( msisdn );
				
				ArrayList<Token> tokensToAccept = DAOToken.getInstance( mysql ).getAvailableAllocatedActiveTokens( msisdn );
				
				ArrayList<CatalogOffers> offers = null;
				
				int o = (int)( Math.random() * catalogOffersList.size() );
			
				CatalogOffers offerToPurchase = catalogOffersList.get( o );
				
				if( tokensToAccept.size() > 0 ) { offers = DAOCatalogOffers.getInstance( mysql ).getAllCatalogOffersByToken( tokensToAccept.get( 0 ) ); }
				
				GenerateSubscriberRechargeThread threadRecharge = new GenerateSubscriberRechargeThread( ( i + 1 ), Thread.MAX_PRIORITY, THREAD_SLEEP, guiServer, superman, msisdn );
				GenerateXMLRPCAllocateThread threadXMLRPCAllocate = new GenerateXMLRPCAllocateThread( ( i + 1 ), Thread.MAX_PRIORITY, THREAD_SLEEP, guiServer, superman, msisdn, tokensToAllocate );
				if( null != offers ) { 
					GenerateXMLRPCAcceptThread threadXMLRPCAccept = new GenerateXMLRPCAcceptThread( ( i + 1 ), Thread.MAX_PRIORITY, THREAD_SLEEP, guiServer, superman, msisdn, tokensToAccept, Integer.valueOf( offers.get( 0 ).getOfferId() ) ); 
					threadXMLRPCAccept.startThread();
					threadsXMLRPCAccept.add( threadXMLRPCAccept );							
				}
				GenerateXMLRPCPurchaseThread threadXMLRPCPurchase = new GenerateXMLRPCPurchaseThread( ( i + 1 ), Thread.MAX_PRIORITY, THREAD_SLEEP, guiServer, superman, msisdn, offerToPurchase.getOfferName(), channels[ c ] );
											
				threadRecharge.startThread();
				threadXMLRPCAllocate.startThread();
				threadXMLRPCPurchase.startThread();
				    			    
				threadsRecharge.add( threadRecharge );
				threadsXMLRPCAllocate.add( threadXMLRPCAllocate );
				threadsXMLRPCPurchase.add( threadXMLRPCPurchase );
										
			}
		
		}
		
	}
	
	private void waitExecution() {
		
		try {
			
			Thread.sleep( EXECUTION_TIME );
		
		} catch (Exception e){}
		
	}
	
	private void stopThreads() {
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			threadsRecharge.get( i ).stopThread();
			threadsXMLRPCAllocate.get( i ).stopThread();
			threadsXMLRPCAccept.get( i ).stopThread();
			threadsXMLRPCPurchase.get( i ).stopThread();
			
		}	
		
	}
		
	private void printResult() {
		
		StringBuilder result = new StringBuilder();
        
		int total = 0;
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			total = total + threadsRecharge.get( i ).getRequestsCount();
			
			result.append( "Thread ( " )
					.append( i )
					.append( " ) -> requests: " )
					.append( threadsRecharge.get( i ).getRequestsCount() )
					.append( " - fails: " )
					.append( threadsRecharge.get( i ).getFailsCount() )
					.append( "\n" );
			
			
			total = total + threadsXMLRPCAllocate.get( i ).getRequestsCount();
			
			result.append( "Thread ( " )
					.append( i )
					.append( " ) -> requests: " )
					.append( threadsXMLRPCAllocate.get( i ).getRequestsCount() )
					.append( " - fails: " )
					.append( threadsXMLRPCAllocate.get( i ).getFailsCount() )
					.append( "\n" );
			
			
			total = total + threadsXMLRPCAccept.get( i ).getRequestsCount();
			
			result.append( "Thread ( " )
					.append( i )
					.append( " ) -> requests: " )
					.append( threadsXMLRPCAccept.get( i ).getRequestsCount() )
					.append( " - fails: " )
					.append( threadsXMLRPCAccept.get( i ).getFailsCount() )
					.append( "\n" );
			
			
			total = total + threadsXMLRPCPurchase.get( i ).getRequestsCount();
			
			result.append( "Thread ( " )
					.append( i )
					.append( " ) -> requests: " )
					.append( threadsXMLRPCPurchase.get( i ).getRequestsCount() )
					.append( " - fails: " )
					.append( threadsXMLRPCPurchase.get( i ).getFailsCount() )
					.append( "\n" );
			
		}
		
		System.out.println( "\nTotal: " + total + "\n" + result.toString() );
		
	}
	
	public static void main(String args[]) throws NetworkEnvironmentException {
		
		RunGenerateSubscriberRechargeAllocateAcceptPurchase generateSubscriberRecharge = new RunGenerateSubscriberRechargeAllocateAcceptPurchase();
		
		generateSubscriberRecharge.initializeThreads();
		
		generateSubscriberRecharge.waitExecution();
		
		generateSubscriberRecharge.stopThreads();
		
		generateSubscriberRecharge.printResult();
		
	}
	
}
