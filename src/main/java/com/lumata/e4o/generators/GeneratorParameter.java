package com.lumata.e4o.generators;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;

public class GeneratorParameter {

	GeneratorParameterType generatorParameterType;
	
	Object value;
	
	Object leftValue;
	
	Object rightValue;
	
	public enum GeneratorParameterType {
		
		environment, mysql, msisdn_strategy, msisdn_options, fixed_msisdn, incremental_msisdn, random_msisdn, subscriber_sms_channel, subscriber_mail_channel, min_events, max_events
		
	}

	GeneratorParameter( GeneratorParameterType generatorParameterType, Object value ) {
		
		this.generatorParameterType = generatorParameterType;
		
		this.value = value;
	
	}
	
	GeneratorParameter( GeneratorParameterType generatorParameterType, Object leftValue, Object rightValue ) {
		
		this.generatorParameterType = generatorParameterType;
		
		this.leftValue = leftValue;
		
		this.rightValue = rightValue;
	
	}
	
	public GeneratorParameterType getGeneratorParameterType() {
		return this.generatorParameterType;
	}

	public Object getGeneratorParameterValue() {
		return this.value;
	}

	public Object getGeneratorParameterLeftValue() {
		return this.leftValue;
	}

	public Object getGeneratorParameterRightValue() {
		return this.rightValue;
	}

	public static GeneratorParameter environment( final NetworkEnvironment env ) {
		
		return new GeneratorParameter( GeneratorParameterType.environment, env );
			
	} 

	public static GeneratorParameter mysql( final Mysql mysql ) {
		
		return new GeneratorParameter( GeneratorParameterType.mysql, mysql );

	}
	
	public static GeneratorParameter msisdnFixed( final Long msisdn ) {
		
		return new GeneratorParameter( GeneratorParameterType.fixed_msisdn, msisdn );

	}
	
	public static GeneratorParameter msisdnIncremental( final Long msisdn, final Integer increment ) {
		
		return new GeneratorParameter( GeneratorParameterType.incremental_msisdn, msisdn, increment );

	}
	
	public static GeneratorParameter msisdnRandom( final Long leftMsisdn, final Long rightMsisdn ) {
		
		return new GeneratorParameter( GeneratorParameterType.random_msisdn, leftMsisdn, rightMsisdn );

	}

	public static GeneratorParameter msisdnOptions( final Integer msisdnPrefix, final Integer msisdnLength ) {
		
		return new GeneratorParameter( GeneratorParameterType.msisdn_options, msisdnPrefix, msisdnLength );

	}

	public static GeneratorParameter subscriberHasSMSChannel( final Boolean hasChannel ) {
		
		return new GeneratorParameter( GeneratorParameterType.subscriber_sms_channel, hasChannel );

	}

	public static GeneratorParameter subscriberHasMAILChannel( final Boolean hasChannel ) {
		
		return new GeneratorParameter( GeneratorParameterType.subscriber_mail_channel, hasChannel );

	}
	
	public static GeneratorParameter minEvents( final Integer minEvents ) {
		
		return new GeneratorParameter( GeneratorParameterType.min_events, minEvents );

	}

	public static GeneratorParameter maxEvents( final Integer maxEvents ) {
		
		return new GeneratorParameter( GeneratorParameterType.max_events, maxEvents );

	}
	
}
