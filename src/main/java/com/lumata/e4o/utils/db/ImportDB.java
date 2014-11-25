package com.lumata.e4o.utils.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
	public final static String DS_TENANT_CRM = "tenant_crm";
	
	// TODO find other big tables and configure this list in JSON
	public final static String[] BIG_TENANT_TABLES = {
		"^daily_.+", // more
		"^campaigns_.+", // more
		"^odr_events$",
		"^purchase_repartition$",
		"^stats_.+", // more
		"^subs_.+", // more
		"^subscriber$",
		"^subscribers_all$",
		"^token$",
		"^token_event$",
		"^voucher_codes$"
	};
	
	/* Comparing the Java list of tables (tenant) with the O2 environment we have:
		 + composite_bundle
		 + daily_bundle
		 + daily_data
		 + daily_postpaid
		 + daily_voice
		 + overall_data
		 + overall_postpaid
		 + overall_voice
		 + set_hobbies
		 + set_options
		 + stats_subs_bundle
		 + stats_subs_data
		 + stats_subs_data_old
		 + stats_subs_postpaid
		 + stats_subs_voice
		 + subs_data
		 + subs_voice
		 - catalog_offer_price_channels_old
		 - catalog_offer_price_old
		 - catalog_offers_eligibility
		 - catalog_offers_old
		 - catalog_offers_old2
		 - composite_tarif_optionen
		 - daily_tarif_optionen
		 - dp_Engagement_subprofiles
		 - files_data_gas
		 - id_ARPU_klasse
		 - id_smartphone_data_usage
		 - id_voice_oder_data
		 - nagios_support
		 - offer_stock_old
		 - option_to_export
		 - rtm_disrangemon_range_pre_prod
		 - rtm_disrangemon_range_prod
		 - set_tarif_optionen
		 - stats_dp_Engagement_subprofiles
		 - stats_subs_account_old
		 - stats_subs_prepaid_old
		 - stats_subs_tarif_optionen
	 */
	// TODO configure this list in JSON
	public final static String[] ALL_TENANT_TABLES = {
		"agencies",
		"agrp_caps",
		"bdr_events",
		"billables",
		"bonus_balance",
		"bonus_campaign_limits",
		"bonuses",
		"bonuses_all",
		"campaign_expected_kpi",
		"campaign_infos",
		"campaign_master",
		"campaign_notif",
		"campaign_params",
		"campaign_rewards",
		"campaign_substates",
		"campaign_types",
		"campaigns",
		"campaigns_history",
		"campaigns_hourly",
		"campaigns_weekly",
		"campaigns_weekly_subs_status",
		"catalog_offer_content",
		"catalog_offer_price",
		"catalog_offer_price_channels",
		"catalog_offer_price_channels_old",
		"catalog_offer_price_old",
		"catalog_offer_price_prices",
		"catalog_offers",
		"catalog_offers_eligibility",
		"catalog_offers_old",
		"catalog_offers_old2",
		"catalog_product_characteristics",
		"catalog_product_specific_characteristics",
		"catalog_product_type_characteristics",
		"catalog_product_types",
		"catalog_products",
		"catalog_related_offers",
		"catalog_related_products",
		"channel_references",
		"collected_files",
		"collected_files_stats",
		"composite_tarif_optionen",
		//O2: "composite_bundle",
		"conf", // TODO this table is complex to import (check the info inside)
		"conf_update_log",
		"conf_update_log_rrd_key",
		"daily_account",
		"daily_bonus",
		//O2: "daily_bundle",
		//O2: "daily_data",
		//O2: "daily_postpaid",
		"daily_prepaid",
		"daily_subs",
		"daily_tarif_optionen",
		//O2: "daily_voice",
		"delayed_bonus",
		"devices",
		"distributed_jobs",
		"dp_Engagement_subprofiles",
		"dynamic_profiles",
		"event_files",
		"event_formats",
		"events",
		"files_data",
		"files_data_gas",
		"files_data_backup",
		"files_meta",
		"groups",
		"groups_tabs",
		"gui_users",
		"gui_users_groups",
		"id_ARPU_klasse",
		"id_smartphone_data_usage",
		"id_voice_oder_data",
		"integers",
		"kpi_campaign",
		"library_bonuses",
		"library_profiles",
		"library_ranges",
		"library_statuses",
		"locks",
		"logical_lock",
		"loyalty_badges",
		"loyalty_badges_types",
		"loyalty_classes",
		"loyalty_programs",
		"loyalty_subs_test",
		"members_all",
		"nagios_support",
		"nb_relations",
		"networks",
		"notif_reward_events",
		"odr_events",
		"offer_stock",
		"offer_stock_old",
		"offoptim_algorithm",
		"offoptim_customer_items",
		"offoptim_customer_items_released",
		"offoptim_customer_pack",
		"offoptim_customer_pack_released",
		"offoptim_offer_history",
		"offoptim_ruleset",
		"offoptim_ruleset_requestor",
		"option_to_export",
		"overall_account",
		//O2: "overall_data",
		//O2: "overall_postpaid",
		"overall_prepaid",
		//O2: "overall_voice",
		"prediction_accuracy",
		"prediction_repartition",
		"product_stock",
		"profiles",
		"purchase_repartition",
		"range_dev",
		"range_pre_prod",
		"range_prod",
		"recommended_campaigns",
		"relation_types",
		"revenue_events",
		"reward_events",
		"rtm_disrangemon_range_pre_prod",
		"rtm_disrangemon_range_prod",
		"ruleset_channel",
		"sales_channels",
		"scheduled_tasks",
		"services",
		//O2: "set_hobbies",
		//O2: "set_options",
		"set_tarif_optionen",
		"stats",
		"stats_bonus",
		"stats_campaign",
		"stats_campaign_archive",
		"stats_churn",
		"stats_dp_Engagement_subprofiles",
		"stats_offer",
		"stats_purchase",
		"stats_range",
		"stats_range_custom",
		"stats_subs",
		"stats_subs_account",
		"stats_subs_account_old",
		//O2: "stats_subs_bundle",
		//O2: "stats_subs_data",
		//O2: "stats_subs_data_old",
		//O2: "stats_subs_postpaid",
		"stats_subs_prepaid",
		"stats_subs_prepaid_old",
		"stats_subs_tarif_optionen",
		//O2: "stats_subs_voice",
		"statuses",
		"subs_badges",
		"subs_classes",
		//O2: "subs_data",
		"subs_notif",
		"subs_relations",
		//O2: "subs_voice",
		"subscribers",
		"subscribers_all",
		"suppliers",
		"supported_rate_plan",
		"token",
		"token_event",
		"token_label",
		"token_type",
		"ussd_events",
		"voucher_code_unlimited",
		"voucher_codes"
	};
	
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

	/**
	 * This command is used to show all databases via MySQL command
	 * 
	 * @param nEnv
	 * @param dataSourceName
	 * @throws IOException
	 */
	public static void showAllDatabases(DataSource ds) throws IOException {
		System.out.println("\nmysql> show databases");
		System.out.println(  "---------------------");
		String[] command = {"mysql", "-h"+ds.getHostAddress(), "-P"+ds.getHostPort(), "-u"+ds.getUser(), "-p"+Security.decrypt(ds.getPassword()), "-e", "show databases"};
		exec(command);
	}

	/**
	 * This command is used to show all tables via MySQL command
	 * 
	 * @param nEnv
	 * @param dataSourceName
	 * @throws IOException
	 */
	public static void showAllTables(DataSource ds) throws IOException {
		System.out.println("\nmysql> show tables");
		System.out.println(  "------------------");
		String[] command = {"mysql", "-h"+ds.getHostAddress(), "-P"+ds.getHostPort(), "-u"+ds.getUser(), "-p"+Security.decrypt(ds.getPassword()), "-D"+ds.getHostName(), "-e", "show tables"};
		exec(command);
	}
	
	/**
	 * Show the count of all the records for each tenant table
	 * 
	 * @param nEnv
	 * @param dataSourceName
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void showAllTenantTablesCount(DataSource ds) throws ClassNotFoundException {
		System.out.println("\nTenant tables count");
		System.out.println(  "-------------------");
		for (String table : ALL_TENANT_TABLES) {
			try {
				System.out.println(String.format("%s: %d", table, execCount(table, ds)));
			} catch (SQLException e) {
				System.out.println(String.format("%s: ERROR: %s", table, e.getMessage()));
			}
		}
	}
	public static void showAllTenantTablesCountJSON(DataSource ds) throws ClassNotFoundException {
		JSONArray array = new JSONArray(); 
		
		for (String table : ALL_TENANT_TABLES) {
			System.out.println(table);
			
			JSONObject object = new JSONObject();
			object.accumulate("table_name", table);
			
			try {
				object.accumulate("row_count", execCount(table, ds));
				
			} catch (SQLException e) {
				// pass
			}
			
			array.put(object);
		}
		
		System.out.println(array.toString(4));
	}
	
	/**
	 * Show the tenant tables with zero records, from the cached JSON
	 * 
	 * @throws IOException
	 */
	public static void showAllTenantTablesZeroRows() throws IOException {
		System.out.println("\nTenant tables count zero");
		System.out.println(  "------------------------");
		
		JSONArray array = parseJSONArray("src/main/resources/input/database/e4o_o2_prod_tenant_crm.json");
		
		for (int i=0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			if (object.getInt("row_count") == 0) {
				System.out.println(object.getString("table_name"));
			}
		}
	}
	
	/**
	 * Show the difference between the Array of tenant tables and the database
	 * 
	 * @param nEnv
	 * @param dataSourceName
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void diffTenantTables(DataSource ds) throws ClassNotFoundException, SQLException {
		System.out.println("\nDiff tenant tables");
		System.out.println(  "------------------");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				String.format("jdbc:mysql://%s:%s/information_schema", ds.getHostAddress(), ds.getHostPort()),
					ds.getUser(), Security.decrypt(ds.getPassword()));
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				String.format("SELECT TABLE_NAME FROM TABLES WHERE TABLE_SCHEMA = '%s'", ds.getHostName()));
		
		List<String> tables = new ArrayList<String>();
		while(rs.next()) {
			tables.add(rs.getString(1));
		}
		
		for (String table : ALL_TENANT_TABLES) {
			if (!tables.contains(table)) {
				System.out.println(" + " + table);
			}
		}
		
		List<String> allTenantTable = Arrays.asList(ALL_TENANT_TABLES); 
		for (String table : tables) {
			if (!allTenantTable.contains(table)) {
				System.out.println(" - " + table);				
			}
		}
	}
	
	/**
	 * This method is used to dump the schema only
	 * 
	 * @param tablesList
	 * @throws IOException 
	 */
	public static void dumpStruct(String[] tablesList, DataSource ds, String filename) throws IOException {

		deleteOldFileIfExists(filename);
		
		execFile(String.format(
				"mysqldump -h%s -u%s -p%s -P%s --lock-tables=false --no-data --skip-triggers %s %s",
				ds.getHostAddress(),
				ds.getUser(),
				Security.decrypt(ds.getPassword()),
				ds.getHostPort(),
				ds.getHostName(),
				convertArrayToString(tablesList, " ")), filename);
	}
	
	/**
	 * This method is used to dump the data only, all the records (configuration tables)
	 * 
	 * @param tablesList
	 * @throws IOException 
	 */
	public static void dumpLight(String[] tablesList, DataSource ds, String filename) throws IOException {

		deleteOldFileIfExists(filename);
		
		execFile(String.format(
				"mysqldump -h%s -u%s -p%s -P%s --lock-tables=false --no-create-info %s %s",
				ds.getHostAddress(),
				ds.getUser(),
				Security.decrypt(ds.getPassword()),
				ds.getHostPort(),
				ds.getHostName(),
				convertArrayToString(tablesList, " ")), filename);
	}
	
	/**
	 * This method is used to dump the data only, some records using the where condition (user tables)
	 * 
	 * Example:
	 * ... --where=userId in (...)
	 * 
	 * @param tablesList
	 */
	public static void dumpBig(String[] tablesList, String where, DataSource ds) {
		// TODO...
	}

	// ---------------------------------------------------------------------
	// Private static methods
	// ---------------------------------------------------------------------

	private static void deleteOldFileIfExists(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
			System.out.println("Old file deleted: " + filename);
		}
	}
	
	private static String convertArrayToString(String[] list, String sep) {
		String res = "";
		
		for (String element : list) {
			if (res.length() == 0) {
				res += element;
			} else {
				res += sep + element;
			}
		}
		
		return res;
	}
	
	private static JSONArray parseJSONArray(String filepath) throws IOException {
		String text = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);
		return new JSONArray(text);
	}
	
	// to exclude elements from the first list, set the "include" parameter to false
	@SuppressWarnings("unused")
	private static String[] excludeElementsFrom(String[] fromList, String[] exclusionsList, boolean include) {
		List<String> list = new ArrayList<String>();
		
		for (String from : fromList) {
			boolean found = false;
			
			for (String exclusion : exclusionsList) {
				found = from.matches(exclusion);
				
				if (found == true) {
					break;
				}
			}
			
			if (found == include) {
				list.add(from);
			}
		}
		
		return list.toArray(new String[list.size()]);
	}
	
	// low-level Process input/error
	private static void execOutput(Process p) throws IOException {
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
	private static void execFileOutput(Process p, String filename) throws IOException {
		// input
		FileWriter filew = new FileWriter(filename, true);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = input.readLine()) != null) {
			filew.write(line + "\n");
		}
		input.close();
		filew.close();
		
		// error
		BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while ((line = error.readLine()) != null) {
			System.out.println(line);
		}
		error.close();
	}
	
	// low-level command call
	private static void exec(String command) throws IOException {
		Process p = Runtime.getRuntime().exec(command);
		execOutput(p);
	}
	private static void execFile(String command, String filename) throws IOException {
		Process p = Runtime.getRuntime().exec(command);
		execFileOutput(p, filename);
	}

	// low-level command call
	private static void exec(String[] command) throws IOException {
		Process p = Runtime.getRuntime().exec(command);
		execOutput(p);
	}
	
	// count(*) SQL
	private static long execCount(String tableName, DataSource ds) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				String.format("jdbc:mysql://%s:%s/%s", ds.getHostAddress(), ds.getHostPort(), ds.getHostName()),
					ds.getUser(), Security.decrypt(ds.getPassword()));
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
		
		long count = 0;
		while(rs.next()) {
			count = rs.getLong(1);
		}
		
		return count;
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
		
		NetworkEnvironment nEnv = new NetworkEnvironment("input/environments", "e4o_o2_prod_ne", IOFileUtils.IOLoadingType.RESOURCE);
		
		// parameters for mysqldump
		DataSource ds = nEnv.getDataSources().get(DS_TENANT_CRM);
		System.out.println("HostAddress: " + ds.getHostAddress());
		System.out.println("HostPort: " + ds.getHostPort());
		System.out.println("User: " + ds.getUser());
		System.out.println("Password: " + Security.decrypt(ds.getPassword()));
		System.out.println("HostName: " + ds.getHostName()); // DB name
		
		if (!checkMysqldump()) {
			System.out.println("WARNING: mysqldump command is not present on your machine");
			return;
		}
		
		/*
		showAllDatabases(ds);
		showAllTables(ds);
		showAllTenantTablesCount(ds);
		showAllTenantTablesCountJSON(ds);
		showAllTenantTablesZeroRows();
		diffTenantTables(ds);
		*/
		showAllTenantTablesZeroRows();
		/*
		dumpStruct(ALL_TENANT_TABLES, ds, "struct.sql");
		dumpLight(excludeElementsFrom(ALL_TENANT_TABLES, BIG_TENANT_TABLES, false),
				ds, "light.sql");
		*/
		
		/*String[] lightTenantTables = excludeElementsFrom(ALL_TENANT_TABLES, BIG_TENANT_TABLES, true);
		for (String table : lightTenantTables) {
			System.out.println(table);
		}*/
	}
}
