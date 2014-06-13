package com.lumata.e4o.generators;

public class Generator {
	
	public static IGeneratorSubscriberParameters subscribers() {
		
		GeneratorParametersList parameters = new GeneratorParametersList();
		
		return new SubscribersGenerator( parameters ); 
		
	}

}
