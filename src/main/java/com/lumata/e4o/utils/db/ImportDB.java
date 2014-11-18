package com.lumata.e4o.utils.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		"catalog_offer_price_prices",
		"catalog_offers",
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
		"composite_bundle",
		"conf",
		"conf_update_log",
		"conf_update_log_rrd_key",
		"daily_account",
		"daily_bonus",
		"daily_bundle",
		"daily_data",
		"daily_postpaid",
		"daily_prepaid",
		"daily_subs",
		"daily_voice",
		"delayed_bonus",
		"devices",
		"distributed_jobs",
		"dynamic_profiles",
		"event_files",
		"event_formats",
		"events",
		"files_data",
		"files_data_backup",
		"files_meta",
		"groups",
		"groups_tabs",
		"gui_users",
		"gui_users_groups",
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
		"nb_relations",
		"networks",
		"notif_reward_events",
		"odr_events",
		"offer_stock",
		"offoptim_algorithm",
		"offoptim_customer_items",
		"offoptim_customer_items_released",
		"offoptim_customer_pack",
		"offoptim_customer_pack_released",
		"offoptim_offer_history",
		"offoptim_ruleset",
		"offoptim_ruleset_requestor",
		"overall_account",
		"overall_data",
		"overall_postpaid",
		"overall_prepaid",
		"overall_voice",
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
		"ruleset_channel",
		"sales_channels",
		"scheduled_tasks",
		"services",
		"set_hobbies",
		"set_options",
		"stats",
		"stats_bonus",
		"stats_campaign",
		"stats_campaign_archive",
		"stats_churn",
		"stats_offer",
		"stats_purchase",
		"stats_range",
		"stats_range_custom",
		"stats_subs",
		"stats_subs_account",
		"stats_subs_bundle",
		"stats_subs_data",
		"stats_subs_data_old",
		"stats_subs_postpaid",
		"stats_subs_prepaid",
		"stats_subs_voice",
		"statuses",
		"subs_badges",
		"subs_classes",
		"subs_data",
		"subs_notif",
		"subs_relations",
		"subs_voice",
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
	public static void showAllDatabases(NetworkEnvironment nEnv, String dataSourceName) throws IOException {
		DataSource ds = nEnv.getDataSources().get(dataSourceName);
		
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
	public static void showAllTables(NetworkEnvironment nEnv, String dataSourceName) throws IOException {
		DataSource ds = nEnv.getDataSources().get(dataSourceName);
		
		System.out.println("\nmysql> show tables");
		System.out.println(  "------------------");
		String[] command = {"mysql", "-h"+ds.getHostAddress(), "-P"+ds.getHostPort(), "-u"+ds.getUser(), "-p"+Security.decrypt(ds.getPassword()), "-D"+ds.getHostName(), "-e", "show tables"};
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
	
	// low-level command call
	private static void exec(String command) throws IOException {
		Process p = Runtime.getRuntime().exec(command);
		execOutput(p);
	}

	// low-level command call
	private static void exec(String[] command) throws IOException {
		Process p = Runtime.getRuntime().exec(command);
		execOutput(p);
	}
	
	// count(*) SQL
	private static long execCount(String tableName, NetworkEnvironment nEnv, String dataSourceName) throws ClassNotFoundException, SQLException {
		DataSource ds = nEnv.getDataSources().get(dataSourceName);
		
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
		
		// showAllDatabases(nEnv, DS_TENANT);
		// showAllTables(nEnv, DS_TENANT);
		
		System.out.println(execCount("token", nEnv, DS_TENANT));
	}
}
