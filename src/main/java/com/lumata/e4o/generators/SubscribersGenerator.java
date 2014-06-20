package com.lumata.e4o.generators;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.GeneratorParameter.GeneratorParameterType;
import com.lumata.e4o.schema.tenant.SubsNotif;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.system.fields.FieldMsisdn;

import static com.lumata.common.testing.orm.Query.*;


public class SubscribersGenerator implements IGeneratorSubscriberParameters {

	GeneratorParametersList parameters;
	
	/** Default Subscriber channels configuration  */
	Boolean SUBSCRIBER_HAS_SMS_CHANNEL = false;
	Boolean SUBSCRIBER_HAS_MAIL_CHANNEL = false;
	
	/** Default Max MSISDN length */
	Integer MSISDN_MAX_LENGTH = 10;
	
	/** Default MSISDN PREFIX */
	String SUBSCRIBER_PREFIX = "";
	
	/** msisdn field management */
	FieldMsisdn csvMsisdn;
	
	
	SubscribersGenerator( GeneratorParametersList parameters ) {
		
		this.parameters = parameters;
		
		this.csvMsisdn = new FieldMsisdn();
	
	}
	
	public SubscribersGenerator environment( final NetworkEnvironment env ) {
		
		parameters.add( GeneratorParameter.environment( env ) );
		
		return this;
		
	}
	
	public SubscribersGenerator mysql( final Mysql mysql ) {
		
		parameters.add( GeneratorParameter.mysql( mysql ) );

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

	private void configureParameters() throws GeneratorException {
		
		String mandatoryFieldMissing = "the mandatory field ${fieldType} is missing";
		
		String mandatoryFieldNull = "the mandatory field ${fieldType} is null";
		
		if( !parameters.containsKey( GeneratorParameterType.environment ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.environment.name() ) ); }
		else if( null == parameters.getParameter( GeneratorParameterType.environment ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.environment.name() ) ); }
		
		if( !parameters.containsKey( GeneratorParameterType.mysql ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.mysql.name() ) ); }
		else if( null == parameters.getParameter( GeneratorParameterType.mysql ) ) { throw new GeneratorException( mandatoryFieldNull.replace( "${fieldType}", GeneratorParameterType.mysql.name() ) ); }

		if( !parameters.containsKey( GeneratorParameterType.msisdn_strategy ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.msisdn_strategy.name() ) + ".It needs to configure a parameter among " + GeneratorParameterType.fixed_msisdn.name() + " or " + GeneratorParameterType.incremental_msisdn.name()  + " or " + GeneratorParameterType.random_msisdn.name() ); }
		else {
			if( null == parameters.getParameter( GeneratorParameterType.msisdn_strategy ) ) { throw new GeneratorException( mandatoryFieldMissing.replace( "${fieldType}", GeneratorParameterType.msisdn_strategy.name() ) + ".It needs to configure a parameter among " + GeneratorParameterType.fixed_msisdn.name() + " or " + GeneratorParameterType.incremental_msisdn.name()  + " or " + GeneratorParameterType.random_msisdn.name() ); }
			else {
				
				try {
					
					switch( parameters.getParameterType( GeneratorParameterType.msisdn_strategy ) ) {
					
						case fixed_msisdn: {
							
							csvMsisdn.setMsisdnStrategyFixed( (Long)parameters.getParameterValue( GeneratorParameterType.msisdn_strategy ) );
							
							break;
							
						} 
						case incremental_msisdn: {
							
							csvMsisdn.setMsisdnStrategyIncrement( 
									(Long)parameters.getParameterLeftValue( GeneratorParameterType.msisdn_strategy ), 
									(Integer)parameters.getParameterRightValue( GeneratorParameterType.msisdn_strategy )									
							);	
							
							break;
							
						} 
						case random_msisdn: {
							
							csvMsisdn.setMsisdnStrategyRandom(
									(Long)parameters.getParameterLeftValue( GeneratorParameterType.msisdn_strategy ), 
									(Long)parameters.getParameterRightValue( GeneratorParameterType.msisdn_strategy )									
							);
							
							break;
							
						} 
						default: { break; }
					}
				
				} catch( CDRException e ) {
					
					System.out.println( e.getMessage() );
					
					e.printStackTrace();
					
				}

			}
			
		}

		if( parameters.containsKey( GeneratorParameterType.msisdn_options ) ) { 
			
			SUBSCRIBER_PREFIX = (String)parameters.getParameter( GeneratorParameterType.msisdn_options ).getGeneratorParameterLeftValue(); 
			
			MSISDN_MAX_LENGTH = (Integer)parameters.getParameter( GeneratorParameterType.msisdn_options ).getGeneratorParameterRightValue();
		
		}

		if( parameters.containsKey( GeneratorParameterType.subscriber_sms_channel ) ) { SUBSCRIBER_HAS_SMS_CHANNEL = (Boolean)parameters.getParameter( GeneratorParameterType.subscriber_sms_channel ).getGeneratorParameterValue(); }
		
		if( parameters.containsKey( GeneratorParameterType.subscriber_mail_channel ) ) { SUBSCRIBER_HAS_MAIL_CHANNEL = (Boolean)parameters.getParameter( GeneratorParameterType.subscriber_mail_channel ).getGeneratorParameterValue(); }
		
	}
	
	public void insertIntoEnvironment( final Long qtySubscribers ) throws GeneratorException {
		
		configureParameters();
	
		for( long s = 0; s < qtySubscribers; s++ ) {
			
			try {
			
				insertSubscriber( Long.valueOf( csvMsisdn.getMsisdn() ) );
				
			} catch( CDRException e ) {
				
				System.out.println( e.getMessage() );
				
			}	
		}
				
	}
	
	private void insertSubscriber( Long msisdn ) {
				
		/** configure subscriber */
		Subscribers subscriber = new Subscribers();
		
		String channelIdList = "";
		
		if( SUBSCRIBER_HAS_SMS_CHANNEL ) { channelIdList = "1"; }
		
		if( SUBSCRIBER_HAS_MAIL_CHANNEL ) { if( channelIdList.length() == 0 ) { channelIdList = "2"; } else { channelIdList = channelIdList + ",2"; } }
				
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
		
		int result = mysql.execUpdate( queryInsert );
		
		if( SUBSCRIBER_HAS_SMS_CHANNEL ) { insertChannel( msisdn, String.valueOf( msisdn ), (byte)1 ); }
			
		if( SUBSCRIBER_HAS_MAIL_CHANNEL ) { insertChannel( msisdn, RandomStringUtils.randomAlphanumeric(10).toLowerCase() + "@lumatagroup.com", (byte)2 ); }
								
	}
	
	private void insertChannel( Long msisdn, String address, Byte channelId ) {
		
		SubsNotif subsNotif = new SubsNotif();
		
		subsNotif.setMsisdn( msisdn );
		subsNotif.setAddress( address );
		subsNotif.setChannelId( channelId );
		
		String query = insert_ignore( subsNotif ).values().build();
	
		Mysql mysql = (Mysql)parameters.getParameterValue( GeneratorParameterType.mysql );
		
		mysql.execUpdate( query );
		
	}

}
