package com.lumata.expression.operators.generators;

import java.util.ArrayList;

public class CDRGenerator {

	private ArrayList<FieldType> fields;
	private ArrayList<String> datetime_format;
	private ArrayList<String> code_prefix;
	
	
	public enum FieldType {
		MSISDN, CODE, DATETIME, LOCATION;
	}
	
	
	
}
