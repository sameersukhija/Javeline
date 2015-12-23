package com.lumata.e4o.testplan.functional.e2e;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.dao.tenant.DAOOfferStock;
import com.lumata.e4o.dao.tenant.DAOVoucher;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.schema.tenant.OfferStock;

public class UpdateOfferStockBasedOnVoucherCodeExpiry_2876 {

	private static final Logger logger = LoggerFactory.getLogger( UpdateOfferStockBasedOnVoucherCodeExpiry_2876.class );
	
	private final boolean testEnabled = true;
	
	private NetworkEnvironment env;
	private Mysql mysql;	
	private short offerId;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		offerId = 1011;
		
	}
	
	@Test( enabled=true, priority = 0 )
	public void calculateVoucherReductionsFromRealData() throws FormException, JSONException, JSONSException, SQLException {
		
		System.out.println( "### initial configuration ###" );
		
		Integer validVouchers = DAOVoucher.getInstance( mysql ).getValidVouchers( offerId, 8 );
		
		ArrayList<OfferStock> offerStock = DAOOfferStock.getInstance( mysql ).getOfferStockList( offerId );
				
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		 	
		ArrayList<OfferStock> offerStockAfterReduction = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStockAfterReduction ) );
		
	}
	
	//enabled=testEnabled
	@Test( enabled=false, priority = 1 )
	public void calculateVoucherReductions1() throws FormException, JSONException, JSONSException, SQLException {
				
		System.out.println( "### initial configuration ###" );
		
		//Integer validVouchers = DAOVoucher.getInstance( mysql ).getValidVouchers( offer_id, 8 );
		
		//ArrayList<OfferStock> offerStock = DAOOfferStock.getInstance( mysql ).getOfferStockByOffer( offer_id );
			
		Integer validVouchers = 100000;
		
		@SuppressWarnings("serial")
		ArrayList<OfferStock> offerStock = initializeOfferStockTable(
			new LinkedHashMap<Short, List<Long>>() {{
		        put( (short)0, Arrays.asList( 15000L, 15000L, 0L, 0L, 0L ));
		        put( (short)2, Arrays.asList( 50000L, 50000L, 0L, 0L, 0L ));
		        put( (short)3, Arrays.asList( 30000L, 30000L, 0L, 0L, 0L ));
		        put( (short)4, Arrays.asList( 5000L, 5000L, 0L, 0L, 0L ));
		    }}
		);
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers , offerStock ) );
		

		validVouchers = 86000;
				
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		 	
		ArrayList<OfferStock> offerStock86000 = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock86000 ) );


		validVouchers = 84000;
		
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		
		ArrayList<OfferStock> offerStock84000 = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock84000 ) );

		
	}
	
	// enabled=testEnabled
	@Test( enabled=false, priority = 2 )
	public void calculateVoucherReductions2() throws FormException, JSONException, JSONSException, SQLException {
		
		System.out.println( "### initial configuration ###" );
		
		//Integer validVouchers = DAOVoucher.getInstance( mysql ).getValidVouchers( offer_id, 8 );
		
		//ArrayList<OfferStock> offerStock = DAOOfferStock.getInstance( mysql ).getOfferStockByOffer( offer_id );
			
		Integer validVouchers = 65000;
		
		@SuppressWarnings("serial")
		ArrayList<OfferStock> offerStock = initializeOfferStockTable(
			new LinkedHashMap<Short, List<Long>>() {{
		        put( (short)0, Arrays.asList( 0L, 0L, 0L, 0L, 0L ));
		        put( (short)2, Arrays.asList( 60000L, 60000L, 0L, 0L, 0L ));
		        put( (short)3, Arrays.asList( 35000L, 25000L, 15000L, 0L, 0L ));
		        put( (short)4, Arrays.asList( 5000L, 5000L, 0L, 0L, 0L ));
		    }}
		);
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers , offerStock ) );
		

		validVouchers = 25000;
				
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		 	
		ArrayList<OfferStock> offerStock86000 = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock86000 ) );


		validVouchers = 15000;
		
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		
		ArrayList<OfferStock> offerStock84000 = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock84000 ) );

		
	}
	
	// enabled=testEnabled
	@Test( enabled=false, priority = 3 )
	public void calculateVoucherReductions3() throws FormException, JSONException, JSONSException, SQLException {
		
		System.out.println( "### initial configuration ###" );
		
		//Integer validVouchers = DAOVoucher.getInstance( mysql ).getValidVouchers( offer_id, 8 );
		
		//ArrayList<OfferStock> offerStock = DAOOfferStock.getInstance( mysql ).getOfferStockByOffer( offer_id );
			
		Integer validVouchers = 99910;
		
		@SuppressWarnings("serial")
		ArrayList<OfferStock> offerStock = initializeOfferStockTable(
			new LinkedHashMap<Short, List<Long>>() {{
		        put( (short)0, Arrays.asList( 50L, 0L, 50L, 0L, 0L ));
		        put( (short)2, Arrays.asList( 90000L, 90000L, 0L, 0L, 0L ));
		        put( (short)3, Arrays.asList( 9000L, 8933L, 40L, 8L, 0L ));
		        put( (short)4, Arrays.asList( 950L, 950L, 0L, 0L, 0L ));
		    }}
		);
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers , offerStock ) );
		

		validVouchers = 70000;
				
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		 	
		ArrayList<OfferStock> offerStock70000 = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock70000 ) );


		validVouchers = 17;
		
		System.out.println( "### Before reduction with " + validVouchers + " valid vouchers ###" );
				
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock ) );
		
		ArrayList<OfferStock> offerStock17 = DAOVoucher.getInstance( mysql ).calculateReduction( validVouchers, offerStock );
				
		System.out.println( "### After reduction with " + validVouchers + " valid vouchers ###" );
		
		System.out.println( DAOVoucher.getInstance( mysql ).getVoucherStatusTable( offerId, validVouchers, offerStock17 ) );
		
	}

	private ArrayList<OfferStock> initializeOfferStockTable( Map<Short, List<Long>> offerStockTable ) {
		
		ArrayList<OfferStock> offerStockList = new ArrayList<OfferStock>(); 
		
		for( Map.Entry<Short, List<Long>> offStockByChannel : offerStockTable.entrySet() ) {
			
			OfferStock offerStock = new OfferStock();
			
			offerStock.setChannelId( (short)offStockByChannel.getKey() );
			offerStock.setInitialStock( offStockByChannel.getValue().get( 0 ) );
			offerStock.setAvailable( offStockByChannel.getValue().get( 1 ) );
			offerStock.setPurchased( offStockByChannel.getValue().get( 2 ) );
			offerStock.setRefused( offStockByChannel.getValue().get( 3 ) );
			offerStock.setExpired( offStockByChannel.getValue().get( 4 ) );			
			
			offerStockList.add( offerStock );
			
		}
		
		return offerStockList;
		
	}
	
}
