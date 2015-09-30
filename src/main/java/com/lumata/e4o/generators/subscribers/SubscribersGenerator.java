package com.lumata.e4o.generators.subscribers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSetHobbies;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.exceptions.XMLRPCException;
import com.lumata.e4o.generators.common.GeneratorParameter;
import com.lumata.e4o.generators.common.GeneratorParametersList;
import com.lumata.e4o.generators.common.GeneratorParameter.GeneratorParameterType;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.SetHobbies;
import com.lumata.e4o.schema.tenant.SetOptions;
import com.lumata.e4o.schema.tenant.SubsNotif;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.system.fields.FieldMsisdn;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.ParameterType;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.EventType.*;
import static com.lumata.common.testing.orm.Query.*;

public class SubscribersGenerator implements IGeneratorSubscriberParameters {

	private static final Logger logger = LoggerFactory.getLogger( SubscribersGenerator.class );
	
	GeneratorParametersList parameters;
	
	/** Default Subscriber channels configuration  */
	Boolean subscriberHasSMSChannel = false;
	Boolean subscriberHasMailChannel = false;
	
	/** Default Max MSISDN length */
	Integer msisdnMaxLenght = 10;
	
	/** Default MSISDN PREFIX */
	String subcriberPrefix = "";
	
	/** msisdn field management */
	FieldMsisdn fieldMsisdn;
	
	/** Default Min and Max events */
	Integer minRandomEvents = 1;
	Integer maxRandomEvents = 1;
	
	Integer repeat = 1;
	
	Boolean randomEvents;

	SubscriberAction actionType;
	
	private enum SubscriberAction {
		insertSubscriber, insertHobbies, insertOptions, recharge, tokenAllocation, tokenAccepting, tokenRefusing, purchaseOffer
	}
	
	
	public SubscribersGenerator( GeneratorParametersList parameters ) {
		
		this.parameters = parameters;
		
		this.fieldMsisdn = new FieldMsisdn();
	
	}
	
	public SubscribersGenerator environment( final NetworkEnvironment env ) {
		
		parameters.add( GeneratorParameter.environment( env ) );
		
		return this;
		
	}
	
	public SubscribersGenerator mysql( final Mysql mysql ) {
		
		parameters.add( GeneratorParameter.mysql( mysql ) );

		return this;
	
	}
	
	public SubscribersGenerator server( final Server server ) {
		
		parameters.add( GeneratorParameter.server( server ) );

		return this;
	
	}
	
	public SubscribersGenerator user( final User user ) {
		
		parameters.add( GeneratorParameter.user( user ) );

		return this;
	
	}
	
	public SubscribersGenerator msisdnFixed( final Long msisdn ) {
	
		parameters.add( GeneratorParameterType.msisdn_strategy, GeneratorParameter.msisdnFixed( msisdn ) );

		return this;
	
	}
	
	public SubscribersGenerator msisdnIncremental( final Long msisdn, final Integer increment  ) {
		
		parameters.add( GeneratorParameterType.msisdn_strategy, GeneratorParameter.msisdnIncremental( msisdn, increment ) );

		return this;
	
	}
	
	public SubscribersGenerator msisdnRandom( final Long leftMsisdn, final Long rightMsisdn  ) {
		
		parameters.add( GeneratorParameterType.msisdn_strategy, GeneratorParameter.msisdnRandom( leftMsisdn, rightMsisdn ) );

		return this;
	
	}
	
	public SubscribersGenerator msisdnOptions( final Integer msisdnPrefix, final Integer msisdnLenght  ) {
		
		parameters.add( GeneratorParameterType.msisdn_options, GeneratorParameter.msisdnOptions( msisdnPrefix, msisdnLenght ) );

		return this;
	
	}

	public SubscribersGenerator subscriberHasSMSChannel( final Boolean hasChannel ) {
		
		parameters.add( GeneratorParameter.subscriberHasSMSChannel( hasChannel ) );

		return this;
	
	}

	public SubscribersGenerator subscriberHasMAILChannel( final Boolean hasChannel ) {
		
		parameters.add( GeneratorParameter.subscriberHasMAILChannel( hasChannel ) );

		return this;
	
	}
	
	public SubscribersGenerator minRandomEvents( final Integer minEvents ) {
		
		parameters.add( GeneratorParameter.minEvents( minEvents ) );

		return this;
	
	}

	public SubscribersGenerator maxRandomEvents( final Integer maxEvents ) {
		
		parameters.add( GeneratorParameter.maxEvents( maxEvents ) );

		return this;
	
	}
	
	public SubscribersGenerator repeat( final Integer repeat ) {
		
		parameters.add( GeneratorParameter.repeat( repeat ) );

		return this;
	
	}
	
	private void configureParameters() throws GeneratorException {
		
		String mandatoryFieldMissing = "the mandatory field ${fieldType} is missing";
		
		String mandatoryFieldNull = "the mandatory field ${fieldType} is null";
		
		if( actionType.equals( SubscriberAction.insertSubscriber ) || actionType.equals( SubscriberAction.insertOptions ) || actionType.equals( SubscriberAction.tokenAllocation ) ) {
			
			if( !parameters.containsKey( GeneratorParameterType.environment ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.environment.name() ) ); }
			else if( null == parameters.getParameter( GeneratorParameterType.environment ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.environment.name() ) ); }
			
			if( !parameters.containsKey( GeneratorParameterType.mysql ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.mysql.name() ) ); }
			else if( null == parameters.getParameter( GeneratorParameterType.mysql ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.mysql.name() ) ); }

		}
		
		if( actionType.equals( SubscriberAction.recharge ) || actionType.equals( SubscriberAction.tokenAllocation ) ) {
			
			if( !parameters.containsKey( GeneratorParameterType.server ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.server.name() ) ); }
			else if( null == parameters.getParameter( GeneratorParameterType.server ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.server.name() ) ); }

			if( !parameters.containsKey( GeneratorParameterType.user ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.user.name() ) ); }
			else if( null == parameters.getParameter( GeneratorParameterType.user ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.user.name() ) ); }

		}
		
		if( !actionType.equals( SubscriberAction.insertOptions ) ) {
		
			if( !parameters.containsKey( GeneratorParameterType.msisdn_strategy ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.msisdn_strategy.name() ) + ".It needs to configure a parameter among " + GeneratorParameterType.msisdn_fixed.name() + " or " + GeneratorParameterType.msisdn_incremental.name()  + " or " + GeneratorParameterType.msisdn_random.name() ); }
			else {
				if( null == parameters.getParameter( GeneratorParameterType.msisdn_strategy ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.msisdn_strategy.name() ) + ".It needs to configure a parameter among " + GeneratorParameterType.msisdn_fixed.name() + " or " + GeneratorParameterType.msisdn_incremental.name()  + " or " + GeneratorParameterType.msisdn_random.name() ); }
				else {
					
					try {
						
						switch( parameters.getParameterType( GeneratorParameterType.msisdn_strategy ) ) {
						
							case msisdn_fixed: {
								
								fieldMsisdn.setMsisdnStrategyFixed( (Long)parameters.getParameterValue( GeneratorParameterType.msisdn_strategy ) );
								
								break;
								
							} 
							case msisdn_incremental: {
								
								fieldMsisdn.setMsisdnStrategyIncrement( 
										(Long)parameters.getParameterLeftValue( GeneratorParameterType.msisdn_strategy ), 
										(Integer)parameters.getParameterRightValue( GeneratorParameterType.msisdn_strategy )									
								);	
								
								break;
								
							} 
							case msisdn_random: {
								
								fieldMsisdn.setMsisdnStrategyRandom(
										(Long)parameters.getParameterLeftValue( GeneratorParameterType.msisdn_strategy ), 
										(Long)parameters.getParameterRightValue( GeneratorParameterType.msisdn_strategy )									
								);
								
								break;
								
							} 
							default: { break; }
						}
					
					} catch( FieldException e ) {
						
						logger.error( Log.FAILED.createMessage( e.getMessage() ) );
						
					}
	
				}
				
			}
		
		}
		
		if( parameters.containsKey( GeneratorParameterType.msisdn_options ) ) { 
			
			subcriberPrefix = (String)parameters.getParameter( GeneratorParameterType.msisdn_options ).getGeneratorParameterLeftValue(); 
			
			msisdnMaxLenght = (Integer)parameters.getParameter( GeneratorParameterType.msisdn_options ).getGeneratorParameterRightValue();
		
		}

		if( parameters.containsKey( GeneratorParameterType.subscriber_sms_channel ) ) { subscriberHasSMSChannel = (Boolean)parameters.getParameter( GeneratorParameterType.subscriber_sms_channel ).getGeneratorParameterValue(); }
		
		if( parameters.containsKey( GeneratorParameterType.subscriber_mail_channel ) ) { subscriberHasMailChannel = (Boolean)parameters.getParameter( GeneratorParameterType.subscriber_mail_channel ).getGeneratorParameterValue(); }

		randomEvents = false;
		
		if( parameters.containsKey( GeneratorParameterType.min_events ) ) { 
			
			minRandomEvents = (Integer)parameters.getParameter( GeneratorParameterType.min_events ).getGeneratorParameterValue(); 
			
			randomEvents = true;
				
		}
		
		if( parameters.containsKey( GeneratorParameterType.max_events ) ) { 
			
			maxRandomEvents = (Integer)parameters.getParameter( GeneratorParameterType.max_events ).getGeneratorParameterValue(); 
			
			randomEvents = true;
			
		}

		if( parameters.containsKey( GeneratorParameterType.repeat ) ) { 
			
			repeat = (Integer)parameters.getParameter( GeneratorParameterType.repeat ).getGeneratorParameterValue(); 
			
		}
		
		if( randomEvents && ( minRandomEvents > maxRandomEvents ) ) { throw new GeneratorException( "The min events must be less than or equals to max events" ); }
		
		if( randomEvents && ( maxRandomEvents < minRandomEvents ) ) { throw new GeneratorException( "The max events must be greater than or equals to min events" ); }
		
		if( randomEvents && ( minRandomEvents < 0 || maxRandomEvents < 0 ) ) { throw new GeneratorException( "The min events and max events must be positive numbers" ); }
				
	}
	
	public void insertIntoEnvironment( final Long qtySubscribers ) throws GeneratorException {
		
		actionType = SubscriberAction.insertSubscriber;
		
		configureParameters();
	
		for( long s = 0; s < qtySubscribers; s++ ) {
			
			try {
			
				insertSubscriber( Long.valueOf( fieldMsisdn.getMsisdn() ) );
				
			} catch( FieldException e ) {
				
				logger.error( e.getMessage(), e );
				
			}	
		}
				
	}
	
	private void insertSubscriber( Long msisdn ) {
				
		/** configure subscriber */
		Subscribers subscriber = new Subscribers();
		
		String channelIdList = "";
		
		if( subscriberHasSMSChannel ) { channelIdList = "1"; }
		
		if( subscriberHasMailChannel ) { if( channelIdList.length() == 0 ) { channelIdList = "2"; } else { channelIdList = channelIdList + ",2"; } }
				
		subscriber.setMsisdn( msisdn );			
		subscriber.setSubscriptionDate( new Date() );
		subscriber.setProfileId( (byte)2 );
		subscriber.setRatePlanId( (byte)1 );
		subscriber.setStatusId( (byte)1 );
		subscriber.setChannelIdList( channelIdList );
		subscriber.setTongue( "ENG" );
		subscriber.setInTag( "QAIN" );
		subscriber.setUpdateTime( new Timestamp( Calendar.getInstance().getTimeInMillis() ) );
		
		
		/** insert subscriber query */
		String queryInsert = insert_ignore( subscriber ).values().build();		
		
		/** update subscriber query */
		//String queryUpdate = update( subscriber ).set().build();
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		mysql.execUpdate( queryInsert );
		
		if( subscriberHasSMSChannel ) { insertChannel( msisdn, String.valueOf( msisdn ), (byte)1 ); }
			
		if( subscriberHasMailChannel ) { insertChannel( msisdn, RandomStringUtils.randomAlphanumeric(10).toLowerCase() + "@lumatagroup.com", (byte)2 ); }
		
		logger.info( Log.CREATING.createMessage( "msisdn " + msisdn ) );
								
	}
	
	private void insertChannel( final Long msisdn, final String address, final Byte channelId ) {
		
		SubsNotif subsNotif = new SubsNotif();
		
		subsNotif.setMsisdn( msisdn );
		subsNotif.setAddress( address );
		subsNotif.setChannelId( channelId );
		
		String query = insert_ignore( subsNotif ).values().build();
	
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		mysql.execUpdate( query );
		
	}

	public void insertDefaultHobbies() throws GeneratorException {
		
		actionType = SubscriberAction.insertOptions;
		
		configureParameters();
		
		DAOSetHobbies.getInstance( (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql ) ).insertDefaultHobbies();
			
	}
	
	public void insertHobbies( final String[] hobbies ) throws GeneratorException {
		
		actionType = SubscriberAction.insertOptions;
		
		configureParameters();
			
		long countHobbies = 0;
		
		for( String hobbyName : hobbies ) {
		
			countHobbies++;
			
			SetHobbies hobby = new SetHobbies();
			
			hobby.setHobbies( countHobbies );
			
			hobby.setHobbiesName( hobbyName );

			addSets( hobby );
		
			logger.info( Log.STORED.createMessage( "hobby ( " + hobby.getHobbies() + " - " + hobby.getHobbiesName() + " )" ) );
			
		}		
				
	}
	
	public void insertHobbies( final String prefix, final Long qtyHobbies ) throws GeneratorException {
		
		actionType = SubscriberAction.insertOptions;
		
		configureParameters();
			
		for( long hb = 1; hb <= qtyHobbies; hb++ ) {
		
			SetHobbies hobby = new SetHobbies();
			
			hobby.setHobbies( (long)hb );
			
			hobby.setHobbiesName( prefix + hb );
			
			addSets( hobby );
		
			logger.info( Log.STORED.createMessage( "hobby ( " + hobby.getHobbies() + " - " + hobby.getHobbiesName() + " )" ) );
			
		}		
				
	}
	
	public void insertOptions( final String prefix, final Long qtyOptions ) throws GeneratorException {
		
		actionType = SubscriberAction.insertOptions;
		
		configureParameters();
			
		for( long opt = 1; opt <= qtyOptions; opt++ ) {
		
			SetOptions option = new SetOptions();
			
			option.setOptions( (long)opt );
			
			option.setOptionsName( prefix + opt );
			
			addSets( option );
		
			logger.info( Log.STORED.createMessage( "option ( " + option.getOptions() + " - " + option.getOptionsName() + " )" ) );
			
		}		
				
	}
	
	private void addSets( final Object table ) {
		
			
		String query = insert_ignore( table ).values().build();
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		mysql.execUpdate( query );
						
	}
	
	public void xmlrpcAllTokenAllocation() throws GeneratorException, NumberFormatException, FieldException {
		
		xmlrpcAllTokenAllocation( null );
		
	}
	
	public void xmlrpcAllTokenAllocation( Calendar event_date ) throws GeneratorException, NumberFormatException, FieldException {
		
		actionType = SubscriberAction.tokenAllocation;
		
		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
			
			Long msisdn = Long.valueOf( fieldMsisdn.getMsisdn() );
			
			ArrayList<Token> tokens;
			
			if( event_date == null ) { tokens = DAOToken.getInstance( mysql ).getAvailableActiveTokens( msisdn ); }
			else { tokens = DAOToken.getInstance( mysql ).getAvailableActiveTokensByEventDate( msisdn, event_date ); }
			
			int allocation_calls = 0;
			
			for ( Token token : tokens ) {
				
				try {
					
					XMLRPCRequest.offeroptimizer_allocate().call( 	
						server, 
						xmlrpcBody(
							authentication( user ),
							string( msisdn ),
							string( token.getTokenCode() )
						),
						xmlrpcOptions(
							storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
							storeResponseAsResource( "xmlrpc/response/", "response.xml" ),
							sleep( 2000L )
						)
					);
					
					allocation_calls++;
					
					logger.info( Log.CREATING.createMessage( "allocation ( " + allocation_calls + " )" ) );
					
				} catch (XMLRPCException e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				} catch (Exception e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				}
				
			}
		
		}
		
	}
	
	public void xmlrpcRandomTokenAllocation() throws GeneratorException, NumberFormatException, FieldException {
		
		xmlrpcRandomTokenAllocation( null );
		
	}
	
	public void xmlrpcRandomTokenAllocation( Calendar event_date ) throws GeneratorException, NumberFormatException, FieldException {
		
		actionType = SubscriberAction.tokenAllocation;
		
		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
		
			Long msisdn = Long.valueOf( fieldMsisdn.getMsisdn() );
			
			ArrayList<Token> tokens;
			
			if( null == event_date ) { tokens = DAOToken.getInstance( mysql ).getAvailableActiveTokens( msisdn ); }
			else { tokens = DAOToken.getInstance( mysql ).getAvailableActiveTokensByEventDate( msisdn, event_date ); }
			
			int randomQtyTokenToAllocate = minRandomEvents + (int)( Math.random() * ( maxRandomEvents - minRandomEvents ) ); 
			
			int maxTokenToAllocate = Math.min( ( tokens.size() - 1 ), randomQtyTokenToAllocate );
			
			int tokensToAllocate = ( (int)( 1 + Math.random() * maxTokenToAllocate ) );
					
			for ( int tta = 0; tta < tokensToAllocate; tta++ ) {
				
				try {
					
					XMLRPCRequest.offeroptimizer_allocate().call( 	
						server, 
						xmlrpcBody(
							authentication( user ),
							string( msisdn ),
							string( tokens.get( tta ).getTokenCode() )
						),
						xmlrpcOptions(
							storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
							storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
						)
					);
					
				} catch (XMLRPCException e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				} catch (Exception e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				}
				
			}
		
		}
		
	}
	
	public void xmlrpcAllTokenAccepting() throws GeneratorException, NumberFormatException, FieldException {
		
		xmlrpcAllTokenAccepting( null );
		
	}
	
	public void xmlrpcAllTokenAccepting( Calendar event_date ) throws GeneratorException, NumberFormatException, FieldException {
		
		actionType = SubscriberAction.tokenAllocation;
		
		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
			
			Long msisdn = Long.valueOf( fieldMsisdn.getMsisdn() );
			
			ArrayList<Token> tokens;
			
			tokens = DAOToken.getInstance( mysql ).getAvailableAllocatedTokens( msisdn );
			
			// TO DO
			//if( event_date == null ) { tokens = DAOToken.getInstance( mysql ).getAvailableAllocatedTokens( msisdn ); }
			//else { tokens = DAOToken.getInstance( mysql ).getAvailableAllocatedTokens( msisdn, event_date ); }
			
			int tokensToAccept = tokens.size();
			
			int accepting_calls = 0;
			
			for( int tta = 0; tta < tokensToAccept; tta++ ) {
				
				ArrayList<CatalogOffers> offers = DAOToken.getInstance( mysql ).getAssociatedOffers( msisdn, tokens.get( tta ).getTokenCode() );
				
				if( offers.size() > 0 ) {
				
					int offerToAcceptIndex = (int)( Math.random() * ( offers.size() ) );
										
					final Object[] offer_id = new Integer[]{ Integer.valueOf( offers.get( offerToAcceptIndex ).getOfferId() ) };
				
					try {
					
						XMLRPCRequest.offeroptimizer_accept().call( 	
							server, 
							xmlrpcBody(
								authentication( user ),
								string( msisdn ),
								string( tokens.get( tta ).getTokenCode() ),
								arrayInt( offer_id ),
								string( "web" )
							),
							xmlrpcOptions(
								storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
								storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
							)
						);
						
						accepting_calls++;
						
						logger.info( Log.CREATING.createMessage( "accepting ( " + accepting_calls + " )" ) );
						
					} catch (XMLRPCException e) {
						
						logger.error( Log.FAILED.createMessage( e.getMessage() ) );
					
					} catch (Exception e) {
						
						logger.error( Log.FAILED.createMessage( e.getMessage() ) );
					
					}
					
				}	
				
			}
		
		}
		
	}
	
	public void xmlrpcRandomTokenAccepting() throws GeneratorException, NumberFormatException, FieldException {
		
		actionType = SubscriberAction.tokenAccepting;
		
		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
		
			Long msisdn = Long.valueOf( fieldMsisdn.getMsisdn() );
			
			ArrayList<Token> tokens = DAOToken.getInstance( mysql ).getAvailableAllocatedActiveTokens( msisdn );
			
			int randomQtyTokenToAccept = minRandomEvents + (int)( Math.random() * ( maxRandomEvents - minRandomEvents ) ); 
			
			int maxTokenToAccept = Math.min( ( tokens.size() - 1 ), randomQtyTokenToAccept );
			
			int tokensToAccept = ( (int)( 1 + Math.random() * maxTokenToAccept ) );
			
			for( int tta = 0; tta < tokensToAccept; tta++ ) {
				
				ArrayList<CatalogOffers> offers = DAOToken.getInstance( mysql ).getAssociatedOffers( msisdn, tokens.get( tta ).getTokenCode() );
				
				if( offers.size() > 0 ) {
				
					int offerToAcceptIndex = (int)( Math.random() * ( offers.size() ) );
										
					final Object[] offer_id = new Integer[]{ Integer.valueOf( offers.get( offerToAcceptIndex ).getOfferId() ) };
				
					try {
					
						XMLRPCRequest.offeroptimizer_accept().call( 	
							server, 
							xmlrpcBody(
								authentication( user ),
								string( msisdn ),
								string( tokens.get( tta ).getTokenCode() ),
								arrayInt( offer_id ),
								string( "web" )
							),
							xmlrpcOptions(
								storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
								storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
							)
						);
						
					} catch (XMLRPCException e) {
						
						logger.error( Log.FAILED.createMessage( e.getMessage() ) );
					
					} catch (Exception e) {
						
						logger.error( Log.FAILED.createMessage( e.getMessage() ) );
					
					}
					
				}	
				
			}
		
		}
		
	}
	
	public void xmlrpcRandomTokenRefusing() throws GeneratorException, NumberFormatException, FieldException {
		
		actionType = SubscriberAction.tokenRefusing;
		
		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
		
			Long msisdn = Long.valueOf( fieldMsisdn.getMsisdn() );
			
			ArrayList<Token> tokens = DAOToken.getInstance( mysql ).getAvailableAllocatedActiveTokens( msisdn );
			
			int tokensToRefuse = (int)( 1 + Math.random() * ( tokens.size() - 1 ) );
			
			for( int tta = 0; tta < tokensToRefuse; tta++ ) {
				
				try {
				
					XMLRPCRequest.offeroptimizer_refuseAll().call( 	
						server, 
						xmlrpcBody(
							authentication( user ),
							string( msisdn ),
							string( tokens.get( tta ).getTokenCode() ),
							string( "web" )
						),
						xmlrpcOptions(
							storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
							storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
						)
					);
					
				} catch (XMLRPCException e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				} catch (Exception e) {
					
					logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
				}
				
			}
		
		}
		
	}
	
	public void xmlrpcRecharge() throws GeneratorException {
		
		xmlrpcRecharge( 1L );
		
	}
	
	public void xmlrpcRecharge( final Long qtyRecharges ) throws GeneratorException {
	
		xmlrpcRecharge( qtyRecharges, XMLRPCParameter.parameter( ParameterType.recharge, true ) );
						
	}
	
	public void xmlrpcRecharge( final Long qtyRecharges, XMLRPCParameter... parameterList ) throws GeneratorException {
		
		actionType = SubscriberAction.recharge;
		
		Boolean hasRechargeParameter = false;
		
		//Boolean hasEventTimeParameter = false;
		
		for( int p = 0; p < parameterList.length; p++ ) {
			
			if( parameterList[ p ].getParameter().toString().contains( "<name>recharge</name>" ) ) { 
				hasRechargeParameter = true;
			}
			/*
			if( parameterList[ p ].getParameter().toString().contains( "<name>event_time</name>" ) ) { 
				hasEventTimeParameter = true;
			}*/
			
		}
		
		if( !hasRechargeParameter ) {
			parameterList[ parameterList.length ] = XMLRPCParameter.parameter( ParameterType.recharge, true );
			parameterList = Arrays.copyOf( parameterList, parameterList.length + 1 );
			parameterList[ parameterList.length - 1 ] = XMLRPCParameter.parameter( ParameterType.event_time, new SimpleDateFormat("HH:mm:ss").format( new Date() ) );
		}
		/*
		if( !hasEventTimeParameter ) {
			parameterList = Arrays.copyOf( parameterList, parameterList.length + 1 );
			parameterList[ parameterList.length - 1 ] = XMLRPCParameter.parameter( ParameterType.event_time, new SimpleDateFormat("HH:mm:ss").format( new Date() ) );
		}
		*/
		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
		
			try {
				
				String msisdn = fieldMsisdn.getMsisdn();
				
				Long rechargeToGenerate = ( !randomEvents ? qtyRecharges : minRandomEvents + (long)( Math.random() * ( maxRandomEvents - minRandomEvents ) ) );
				
				for( long rc = 0; rc < rechargeToGenerate; rc++ ) {
									
					XMLRPCRequest.eventmanager_generateCustomEvent().call( 	
							server, 
							xmlrpcBody(
								authentication( user ),
								custoEvent( Long.valueOf( msisdn ), 
											revenue,
											parameterList
								)
							),
							xmlrpcOptions( 
								sleep( 100L )
							)
					);								
									
				}
			
			} catch (Exception e) {
				
				logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
			}
			
		}
						
	}

	public void xmlrpcPurchaseOffer( final String offerName, final String channelName ) throws GeneratorException {
		
		actionType = SubscriberAction.purchaseOffer;

		configureParameters();
		
		Server server = (Server)parameters.getParameterValue( GeneratorParameterType.server );
		
		User user = (User)parameters.getParameterValue( GeneratorParameterType.user );
		
		for( int rp = 1; rp <= repeat; rp++ ) {
		
			try {
				
				String msisdn = fieldMsisdn.getMsisdn();
											
				XMLRPCRequest.catalogmanager_purchase().call( 	
						server, 
						xmlrpcBody(
							authentication( user ),
							string( msisdn ),
							string( offerName ),
							string( channelName ),
							arrayProductPrices( 
								/* price() */	
							),
							arraySelectedProducts( 
								/* product() */	
							),
							string(1)
						),
						xmlrpcOptions( 
							sleep( 100L )
						)
				);								
						
			} catch (Exception e) {
				
				logger.error( Log.FAILED.createMessage( e.getMessage() ) );
				
			}
			
		}
						
	}
	
}
