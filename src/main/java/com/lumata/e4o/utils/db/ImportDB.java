package com.lumata.e4o.utils.db;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;

/**
 * This class is the main generic utility used to import the database tables (from "tenant", "global" or other)
 * 
 */
public class ImportDB {

	/**
	 * This method is used to dump the schema only
	 * 
	 * @param tablesList
	 */
	public static void dumpStruct(String[] tablesList, NetworkEnvironment nEnv) {
		// TODO...
	}
	
	/**
	 * This method is used to dump the data only, all the records (configuration tables)
	 * 
	 * @param tablesList
	 */
	public static void dumpLight(String[] tablesList, NetworkEnvironment nEnv) {
		// TODO...
	}
	
	/**
	 * This method is used to dump the data only, some records using the where condition (user tables)
	 * 
	 * @param tablesList
	 */
	public static void dumpBig(String[] tablesList, NetworkEnvironment nEnv) {
		// TODO...
	}
	
	/**
	 * This main is used to dev/test this utility 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		NetworkEnvironment nEnv = new NetworkEnvironment("input/environments", "e4o_qa4_ne", IOFileUtils.IOLoadingType.RESOURCE);
		
		System.out.println(nEnv);
	}
}
