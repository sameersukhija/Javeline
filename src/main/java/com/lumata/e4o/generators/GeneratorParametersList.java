package com.lumata.e4o.generators;

import java.util.LinkedHashMap;
import java.util.Map;

import com.lumata.e4o.generators.GeneratorParameter.GeneratorParameterType;

public class GeneratorParametersList {

	Map<GeneratorParameterType, GeneratorParameter> parameters;
	
	GeneratorParametersList() {
		parameters = new LinkedHashMap<GeneratorParameterType, GeneratorParameter>();
	}
	
	public void add( GeneratorParameter parameter ) {
		parameters.put( parameter.getGeneratorParameterType(), parameter );
	}

	public void add( GeneratorParameterType generatorParameterType, GeneratorParameter parameter ) {
		parameters.put( generatorParameterType, parameter );
	}
		
	public Boolean containsKey( GeneratorParameterType key ) {
		
		return ( null == parameters ? false : parameters.containsKey( key ) );  
		
	}

	public Boolean containsValue( Object value ) {
		
		return ( null == parameters ? false : parameters.containsValue( value ) );  
		
	}
	
	public GeneratorParameter getParameter( GeneratorParameterType key ) {
		
		return ( null == parameters ? null : parameters.get( key ) );  
		
	}
	
	public GeneratorParameterType getParameterType( GeneratorParameterType key ) {
		
		return getParameter( key ).getGeneratorParameterType();  
		
	}

	public Object getParameterValue( GeneratorParameterType key ) {
		
		return getParameter( key ).getGeneratorParameterValue();  
		
	}

	public Object getParameterLeftValue( GeneratorParameterType key ) {
		
		return getParameter( key ).getGeneratorParameterLeftValue();  
		
	}

	public Object getParameterRightValue( GeneratorParameterType key ) {
		
		return getParameter( key ).getGeneratorParameterRightValue();  
		
	}

}
