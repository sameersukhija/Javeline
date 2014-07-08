package com.lumata.e4o.generators;

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
