package com.lumata.e4o.generators.common;

import com.lumata.e4o.generators.subscribers.IGeneratorSubscriberParameters;
import com.lumata.e4o.generators.subscribers.SubscribersGenerator;
import com.lumata.e4o.generators.vouchers.IGeneratorVoucherParameters;
import com.lumata.e4o.generators.vouchers.VoucherGenerator;

public class Generator {
	
	public static IGeneratorSubscriberParameters subscribers() {
		
		GeneratorParametersList parameters = new GeneratorParametersList();
		
		return new SubscribersGenerator( parameters ); 
		
	}
	
	public static IGeneratorVoucherParameters vouchers() {
		
		GeneratorParametersList parameters = new GeneratorParametersList();
		
		return new VoucherGenerator( parameters ); 
		
	}
	

}
