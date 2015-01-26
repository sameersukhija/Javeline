package com.lumata.e4o.webservices.xmlrpc.request;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.jayway.restassured.path.xml.XmlPath;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponse;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCRequest {

	private static final Logger logger = LoggerFactory.getLogger( XMLRPCRequest.class );
		
	private String callName;
	private String request;
	private XMLRPCResponse response;
	private XMLRPCResponseValidator[] validators;
	private Integer repeat;
	private ArrayList<Long> samples;
	private Integer leftSample;
	private Integer rightSample;
	private Long sleep;
	private Long average;
	private Long startTime;
	private Long endTime;
	private Long expiredTime;
	private String requestFolderName;
	private String requestFileName;
	private String responseFolderName;
	private String responseFileName;
	private IOFileUtils.IOLoadingType requestLoadingType;
	private IOFileUtils.IOLoadingType responseLoadingType;
	private Boolean storeRequest;
	private Boolean storeResponse;
	
	private final String ASSERTION_ERROR_ = "expected ${tag} ${expected} but found ${tag} ${actual}";
	
	XMLRPCRequest( String callName ) {
		this.callName = callName;
		this.request = null;
		this.response = null;
		this.validators = null;
		this.repeat = 1;
		this.samples = null;
		this.sleep = 100L;
		this.average = null;
		this.startTime = null;
		this.endTime = null;
		this.expiredTime = null;
		this.storeRequest = false;
		this.storeResponse = false;
		
	}
	
	public static XMLRPCRequest badgemanager_getBadgeList() {
		return new XMLRPCRequest( "badgemanager.getBadgeList" );
	}
	
	public static XMLRPCRequest badgemanager_getSubscriberBadgeInformation() {
		return new XMLRPCRequest( "badgemanager.getSubscriberBadgeInformation" );
	}
	
	public static XMLRPCRequest bonusmanager_activateBonus() {
		return new XMLRPCRequest( "bonusmanager.activateBonus" );
	}
	
	public static XMLRPCRequest bonusmanager_creditBonus() {
		return new XMLRPCRequest( "bonusmanager.creditBonus" );
	}
	
	public static XMLRPCRequest bonusmanager_deactivateBonus() {
		return new XMLRPCRequest( "bonusmanager.deactivateBonus" );
	}
	
	public static XMLRPCRequest bonusmanager_debitBonus() {
		return new XMLRPCRequest( "bonusmanager.debitBonus" );
	}
	
	public static XMLRPCRequest bonusmanager_deleteBonus() {
		return new XMLRPCRequest( "bonusmanager.deleteBonus" );
	}
	
	public static XMLRPCRequest bonusmanager_getSubscribersBonusDetails() {
		return new XMLRPCRequest( "bonusmanager.getSubscribersBonusDetails" );
	}
	
	public static XMLRPCRequest bonusmanager_setBonus() {
		return new XMLRPCRequest( "bonusmanager.setBonus" );
	}
	
	public static XMLRPCRequest bonusmanager_transferBonus() {
		return new XMLRPCRequest( "bonusmanager.transferBonus" );
	}
	
	public static XMLRPCRequest campaignmanager_getAccessibleSubscribersCampaignDetails() {
		return new XMLRPCRequest( "campaignmanager.getAccessibleSubscribersCampaignDetails" );
	}
	
	public static XMLRPCRequest campaignmanager_getCampaigns() {
		return new XMLRPCRequest( "campaignmanager.getCampaigns" );
	}
	
	public static XMLRPCRequest campaignmanager_getSubscribersCampaign() {
		return new XMLRPCRequest( "campaignmanager.getSubscribersCampaign" );
	}
	
	public static XMLRPCRequest campaignmanager_getSubscribersCampaignDetails() {
		return new XMLRPCRequest( "campaignmanager.getSubscribersCampaignDetails" );
	}
	
	public static XMLRPCRequest campaignmanager_provisionCampaign() {
		return new XMLRPCRequest( "campaignmanager.provisionCampaign" );
	}
	
	public static XMLRPCRequest catalogmanager_getOffers() {
		return new XMLRPCRequest( "catalogmanager.getOffers" );
	}
	
	public static XMLRPCRequest catalogmanager_getProducts() {
		return new XMLRPCRequest( "catalogmanager.getProducts" );
	}
	
	public static XMLRPCRequest catalogmanager_getProductTypes() {
		return new XMLRPCRequest( "catalogmanager.getProductTypes" );
	}
	
	public static XMLRPCRequest catalogmanager_purchase() {
		return new XMLRPCRequest( "catalogmanager.purchase" );
	}
	
	public static XMLRPCRequest eventmanager_generateCustomEvent() {
		return new XMLRPCRequest( "eventmanager.generateCustomEvent" );
	}
	
	public static XMLRPCRequest hierarchymanager_createRelation() {
		return new XMLRPCRequest( "hierarchymanager.createRelation" );
	}
	
	public static XMLRPCRequest hierarchymanager_deleteRelation() {
		return new XMLRPCRequest( "hierarchymanager.deleteRelation" );
	}
	
	public static XMLRPCRequest hierarchymanager_getRelatedSubscribers() {
		return new XMLRPCRequest( "hierarchymanager.getRelatedSubscribers" );
	}
	
	public static XMLRPCRequest hierarchymanager_getSponsor() {
		return new XMLRPCRequest( "hierarchymanager.getSponsor" );
	}
	
	public static XMLRPCRequest hierarchymanager_updateRelation() {
		return new XMLRPCRequest( "hierarchymanager.updateRelation" );
	}
	
	public static XMLRPCRequest historybonusmanager_getHistoryBonus() {
		return new XMLRPCRequest( "historybonusmanager.getHistoryBonus" );
	}
	
	public static XMLRPCRequest historyusagemanager_getHistoryUsage() {
		return new XMLRPCRequest( "historyusagemanager.getHistoryUsage" );
	}
	
	public static XMLRPCRequest loyaltymanager_getUserLoyaltyProgramClass() {
		return new XMLRPCRequest( "loyaltymanager.getUserLoyaltyProgramClass" );
	}
	
	public static XMLRPCRequest loyaltymanager_setUserLoyaltyProgramClass() {
		return new XMLRPCRequest( "loyaltymanager.setUserLoyaltyProgramClass" );
	}
	
	public static XMLRPCRequest offeroptimizer_accept() {
		return new XMLRPCRequest( "offeroptimizer.accept" );
	}
	
	public static XMLRPCRequest offeroptimizer_allocate() {
		return new XMLRPCRequest( "offeroptimizer.allocate" );
	}
	
	public static XMLRPCRequest offeroptimizer_getOffersList() {
		return new XMLRPCRequest( "offeroptimizer.getOffersList" );
	}
	
	public static XMLRPCRequest offeroptimizer_getTokensList() {
		return new XMLRPCRequest( "offeroptimizer.getTokensList" );
	}
	
	public static XMLRPCRequest offeroptimizer_refuseAll() {
		return new XMLRPCRequest( "offeroptimizer.refuseAll" );
	}
	
	public static XMLRPCRequest offeroptimizer_resendAllActiveTokens() {
		return new XMLRPCRequest( "offeroptimizer.resendAllActiveTokens" );
	}
	
	public static XMLRPCRequest predictionmanager_getPredictionScore() {
		return new XMLRPCRequest( "predictionmanager.getPredictionScore" );
	}
	
	public static XMLRPCRequest predictionmanager_getPredictionScoreTrend() {
		return new XMLRPCRequest( "predictionmanager.getPredictionScoreTrend" );
	}
	
	public static XMLRPCRequest subscribermanager_addSubscriberChannel() {
		return new XMLRPCRequest( "subscribermanager.addSubscriberChannel" );
	}
	
	public static XMLRPCRequest subscribermanager_createSubscriber() {
		return new XMLRPCRequest( "subscribermanager.createSubscriber" );
	}
	
	public static XMLRPCRequest subscribermanager_deleteSubscriber() {
		return new XMLRPCRequest( "subscribermanager.deleteSubscriber" );
	}
	
	public static XMLRPCRequest subscribermanager_deleteSubscriberChannel() {
		return new XMLRPCRequest( "subscribermanager.deleteSubscriberChannel" );
	}
	
	public static XMLRPCRequest subscribermanager_getServiceInfo() {
		return new XMLRPCRequest( "subscribermanager.getServiceInfo" );
	}
	
	public static XMLRPCRequest subscribermanager_getSubscriber() {
		return new XMLRPCRequest( "subscribermanager.getSubscriber" );
	}
	
	public static XMLRPCRequest subscribermanager_getSubscriberChannels() {
		return new XMLRPCRequest( "subscribermanager.getSubscriberChannels" );
	}
	
	public static XMLRPCRequest subscribermanager_subscribeService() {
		return new XMLRPCRequest( "subscribermanager.subscribeService" );
	}
	
	public static XMLRPCRequest subscribermanager_unsubscribeService() {
		return new XMLRPCRequest( "subscribermanager.unsubscribeService" );
	}
	
	public static XMLRPCRequest subscribermanager_updateServiceInfo() {
		return new XMLRPCRequest( "subscribermanager.updateServiceInfo" );
	}
	
	public static XMLRPCRequest subscribermanager_updateSubscriber() {
		return new XMLRPCRequest( "subscribermanager.updateSubscriber" );
	}
	
	public static XMLRPCRequest subscribermanager_updateSubscriberChannel() {
		return new XMLRPCRequest( "subscribermanager.updateSubscriberChannel" );
	}
	
	public static XMLRPCRequest subscribermanager_updateUserMsisdn() {
		return new XMLRPCRequest( "subscribermanager.updateUserMsisdn" );
	}
	
	public static XMLRPCRequest system_listMethods() {
		return new XMLRPCRequest( "system.listMethods" );
	}
	
	public static XMLRPCRequest user_create() {
		return new XMLRPCRequest( "user.create" );
	}
	
	public static XMLRPCRequest user_delete() {
		return new XMLRPCRequest( "user.delete" );
	}
	
	public static XMLRPCRequest user_update() {
		return new XMLRPCRequest( "user.update" );
	}
		
	public XMLRPCResponse call( Server server, XMLRPCComponent... xmlrpcComponents ) throws Exception {
		
		response = new XMLRPCResponse();
		
		String url = server.getLink() + "xmlrpc/";
		
		RestClient restClient = new RestClient( url );
				
		parseComponents( restClient, xmlrpcComponents );
		
		for( int r = 1; r <= repeat; r++ ) {
			
			sleep( sleep );
			
			startTime = System.currentTimeMillis();
			
			response.setResponse( restClient.post() );
			
			request = restClient.getRequest().getBody().toString();
			
			endTime = System.currentTimeMillis();
			
			expiredTime = endTime - startTime;
			
			if( null != samples ) {
				
				samples.add( expiredTime );
			
			}
			
			logger.info( Log.GETTING.createMessage( "Response: " + response.getResponse().getEntity().toString() ) );
			
			validate();
				
		}
			
		if( null != samples ) {
			
			for( int s = 0; s < samples.size(); s++ ) {
				System.out.println( samples.get( s ) );
			}
			
			System.out.println( "A: " + leftSample );
			System.out.println( "B: " + rightSample );
			//average = Arithmetic.average( samples, leftSample, ( null != rightSample ? rightSample : samples.size() ) );
		
			System.out.println( "AVERAGE: " + average );
			
		}
		
		if( storeRequest ) { storeRequestFile(); }
		
		if( storeResponse ) { storeResponseFile(); }
		
		return response;
		
	};
	
	private String body( String xmlrpcBody ) {
		
		StringBuilder body = new StringBuilder();
		
		body.append( "<?xml version=\"1.0\"?><methodCall><methodName>" )
			.append( this.callName )
			.append( "</methodName><params>" )
			.append( xmlrpcBody )
			.append( "</params></methodCall>" );
		
		return body.toString();
		
	}
	
	private void parseComponents( RestClient restClient, XMLRPCComponent... xmlrpcComponents ) {
		
		try {
			
			for( int c = 0; c < xmlrpcComponents.length; c++ ) {
				
				Object[] componentValues = xmlrpcComponents[ c ].getComponentValues();
								
				switch( xmlrpcComponents[ c ].getComponentType() ) {
				
					case xmlrpcBody: {
						
						restClient.body( RestClient.ContentTypes.APPLICATION_JSON.getValue(), body( ( null != componentValues && componentValues.length > 0 ? (String)componentValues[ 0 ] : "" ) ) );
						
						break;
						
					}
					case xmlrpcValidator: {
						
						validators = (XMLRPCResponseValidator[])componentValues;
												
						break;
						
					}
					case xmlrpcOption: {
						
						parseOptions( componentValues );
												
						break;
						
					}
					default: { break; }
					
				}
				
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	private void parseOptions( Object... options ) {
		
		for( int opt = 0; opt < options.length; opt++ ) {
						
			XMLRPCOption option = (XMLRPCOption)options[ opt ];
			
			try {
				
				switch( option.getOptionType() ) {
				
					case sleep: {
						
						sleep = (Long)option.getOptionValue();
												
						break;
					
					}
					case repeat: {
						
						repeat = (Integer)option.getOptionValue();
						
						break;
					
					}
					case average: {
						
						samples = new ArrayList<Long>();
						
						average = 0L;
						
						leftSample = (Integer)option.getOptionValues()[ 0 ];
						
						rightSample = (Integer)option.getOptionValues()[ 1 ];
						
						break;
					
					}
					case storeRequestAsResource: {
						
						requestFolderName = (String)option.getOptionValues()[ 0 ];
						
						requestFileName = (String)option.getOptionValues()[ 1 ];
						
						requestLoadingType = IOFileUtils.IOLoadingType.RESOURCE;
						
						storeRequest = true;
						
						break;
					
					}
					case storeResponseAsResource: {
						
						responseFolderName = (String)option.getOptionValues()[ 0 ];
						
						responseFileName = (String)option.getOptionValues()[ 1 ];
						
						responseLoadingType = IOFileUtils.IOLoadingType.RESOURCE;
						
						storeResponse = true;
						
						break;
					
					}
					case storeRequestAsFile: {
						
						requestFolderName = (String)option.getOptionValues()[ 0 ];
						
						requestFileName = (String)option.getOptionValues()[ 1 ];
						
						requestLoadingType = IOFileUtils.IOLoadingType.FILE;
						
						storeRequest = true;
						
						break;
					
					}
					case storeResponseAsFile: {
						
						responseFolderName = (String)option.getOptionValues()[ 0 ];
						
						responseFileName = (String)option.getOptionValues()[ 1 ];
						
						responseLoadingType = IOFileUtils.IOLoadingType.FILE;
						
						storeResponse = true;
						
						break;
					
					}
					default: { break; }
					
				}
				
			} catch ( Exception e ) {
				
				logger.error( e.getMessage(), e ); 
			
			}
			
		}
		
	}
	
	private void sleep( Long time ) {
		
		try {
			
			Thread.sleep( time );
			
		} catch(  InterruptedException e ) {}
			
	}
	
	private void validate() {
		
		if( null != validators  ) {
			
			XmlPath xmlPath = new XmlPath( response.getResponse().getEntity().toString() );
			
			xmlPath.setRoot("methodResponse");
			
			boolean validation = true;
					
			for( XMLRPCResponseValidator validator : validators ) {
				
				Object actual = null;
				
				String expected = validator.getMatcher().toString();
				
				try {
					
					switch( validator.getMatcherArgumentType().getSimpleName() ) {

						case "Long": {
							
							actual = String.valueOf( xmlPath.getLong( validator.getPath() ) );
							
							expected = Format.toNumeric( expected );
							
							break;
						}
						case "Integer": {
							
							actual = String.valueOf( xmlPath.getInt( validator.getPath() ) );
							
							expected = Format.toNumeric( expected );
							
							break;
						}
						case "String": {
							
							actual = xmlPath.getString( validator.getPath() );	
							
							break;
						}
						default: {
							actual = xmlPath.get( validator.getPath()  );
						}
					
					}
					
				} catch( IllegalArgumentException iae ) {
					
					throw new AssertionError( "the validator " + validator.getTag() + " is not valid for the current response " );
					
				}
				
				validation = validator.getMatcher().matches( actual );
				
				String errorMessage = ASSERTION_ERROR_.
						replace( "${tag}" , validator.getTag().trim() ).
						replace( "${expected}", expected ).
						replace( "${actual}", String.valueOf( actual ) );

				try {
				
					Assert.assertTrue( validation );
				
				} catch( AssertionError ae ) {
					
					throw new AssertionError( "validation failed: " + errorMessage );
															
				}
				
			}
										
		}
		
	}
	
	private void storeRequestFile() {
		
		try {
		
			switch( requestLoadingType ) {
		
				case RESOURCE: {
					
					IOFileUtils.saveResource( request, requestFolderName, requestFileName );
					
					break;
					
				}
				case FILE: {
					
					IOFileUtils.saveFile( request, requestFolderName, requestFileName );
					
					break;
					
				}
			}
			
		} catch (IOFileException e) {
			
			logger.error( Log.FAILED.createMessage( e.getMessage() ) );
			
		}
				
	}
	
	private void storeResponseFile() {
		
		try {
		
			switch( responseLoadingType ) {
		
				case RESOURCE: {
					
					IOFileUtils.saveResource( response.getResponse().getEntity().toString(), responseFolderName, responseFileName );
					
					break;
					
				}
				case FILE: {
					
					IOFileUtils.saveFile( response.getResponse().getEntity().toString(), responseFolderName, responseFileName );
					
					break;
					
				}
			}
			
		} catch (IOFileException e) {
			
			logger.error( Log.FAILED.createMessage( e.getMessage() ) );
			
		}
				
	}
	
}
