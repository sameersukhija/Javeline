package com.lumata.e4o.utils.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.DataSource;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Security;

/**
 * This class is the main generic utility used to import the database tables (from "tenant", "global" or other)
 * 
 */
public class ImportDB {

	public final static String DS_TENANT = "tenant";
	
	// ---------------------------------------------------------------------
	// Public static methods
	// ---------------------------------------------------------------------
	
	/**
	 * This command is used to check if mysqldump exist on your machine
	 * 
	 */
	public static boolean checkMysqldump() {
		try {
			exec("mysqldump --version");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void showAllDatabases(NetworkEnvironment nEnv, String dataSourceName) throws IOException {
		DataSource ds = nEnv.getDataSources().get(dataSourceName);
		
		String command = String.format("mysql -h%s -u%s -p%s -e'show databases'", ds.getHostAddress(), ds.getUser(), Security.decrypt(ds.getPassword()));
		System.out.println(command);
		exec(command);
	}

	public static void showAllTables(NetworkEnvironment nEnv, String dataSourceName) throws IOException {
		DataSource ds = nEnv.getDataSources().get(dataSourceName);
		
		// -e'show tables' 
		String command = String.format("mysql -h%s -u%s -p%s -D%s -e'show tables'", ds.getHostAddress(), ds.getUser(), Security.decrypt(ds.getPassword()), ds.getHostName());
		System.out.println(command);
		exec(command);
	}
	
	/**
	 * This method is used to dump the schema only
	 * 
	 * @param tablesList
	 */
	public static void dumpStruct(String[] tablesList, NetworkEnvironment nEnv, String dataSourceName) {
		// TODO...
	}
	
	/**
	 * This method is used to dump the data only, all the records (configuration tables)
	 * 
	 * @param tablesList
	 */
	public static void dumpLight(String[] tablesList, NetworkEnvironment nEnv, String dataSourceName) {
		// TODO...
	}
	
	/**
	 * This method is used to dump the data only, some records using the where condition (user tables)
	 * 
	 * Example:
	 * ... --where=userId in (...)
	 * 
	 * @param tablesList
	 */
	public static void dumpBig(String[] tablesList, String where, NetworkEnvironment nEnv, String dataSourceName) {
		// TODO...
	}

	// ---------------------------------------------------------------------
	// Private static methods
	// ---------------------------------------------------------------------

	// low-level command call
	private static void exec(String command) throws IOException {
		Process p = Runtime.getRuntime().exec(command);
		
		// input
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
		input.close();
		
		// error
		BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while ((line = error.readLine()) != null) {
			System.out.println(line);
		}
		error.close();
	}
	
	// ---------------------------------------------------------------------
	// MAIN
	// ---------------------------------------------------------------------

	/**
	 * This main is used to dev/test this utility 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		NetworkEnvironment nEnv = new NetworkEnvironment("input/environments", "e4o_qa2_ne", IOFileUtils.IOLoadingType.RESOURCE);
		
		// parameters for mysqldump
		DataSource ds = nEnv.getDataSources().get(DS_TENANT);
		System.out.println("HostAddress: " + ds.getHostAddress());
		System.out.println("HostPort: " + ds.getHostPort());
		System.out.println("User: " + ds.getUser());
		System.out.println("Password: " + Security.decrypt(ds.getPassword()));
		System.out.println("HostName" + ds.getHostName()); // DB name
		
		if (!checkMysqldump()) {
			System.out.println("WARNING: mysqldump command is not present on your machine");
			return;
		}
		
		showAllDatabases(nEnv, DS_TENANT);
		
		// TODO...
	}
}
