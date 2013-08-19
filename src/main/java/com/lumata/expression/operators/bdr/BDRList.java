package com.lumata.expression.operators.bdr;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;

public class BDRList {

	private static final Logger logger = LoggerFactory.getLogger( BDRList.class );
	
	private ArrayList<BDR> bdrList;
	private final String CDR_DIR = "/opt/lumata/server_test_expression-qa/cdr/";
	public enum BDRLoadingType { FILE, DATABASE }
	
	public BDRList( Environment env, BDRLoadingType bdrLoadingType) {
				
		
		
	}
		

}
