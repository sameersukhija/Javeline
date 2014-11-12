package com.lumata.e4o.generators.vouchers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOVoucher;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.VoucherCodes;
import com.lumata.e4o.system.cdr.types.CDRVoucherRedemption;


public class GenerateVouchersRedemption {

	private static final Logger logger = LoggerFactory.getLogger( GenerateVouchersRedemption.class );
	
//	final boolean GENERATE_FIXED_SUBSCRIBER = false;
//	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = true;
//	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
	NetworkEnvironment env;	
	Mysql mysql;
	Service sshService;
	String sshUser;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		sshService = env.getService( Service.Type.ssh, "collector" );
		
		sshUser = "root";
			
	}

	@Test( enabled = true )
	public void redeemVouchers() throws GeneratorException, FieldException, IOFileException {
		
		Calendar date = Calendar.getInstance();
		
		date.set( Calendar.MONTH, Calendar.JULY );
		date.set( Calendar.DAY_OF_MONTH, 06 );
		
		//ArrayList<VoucherCodes> voucherPurchasedByDate = DAOVoucher.getInstance( mysql ).getPurchasedVoucherOnDate( date );
		ArrayList<VoucherCodes> voucherPurchasedByDate = DAOVoucher.getInstance( mysql ).getPurchasedVoucher();
				
		CDRVoucherRedemption cdrVR = new CDRVoucherRedemption();
		
		String currentTimestamp = Format.getSystemTimestamp();
		
		String fileName = "cdr_voucher_" + currentTimestamp + ".csv";
		
		cdrVR.setOutputPath( "/cdr/", fileName );
				
		Calendar redemptionDate = Calendar.getInstance();
		
		redemptionDate.set( Calendar.MONTH, Calendar.OCTOBER );
		redemptionDate.set( Calendar.DAY_OF_MONTH, 30 );
		
		if( null != voucherPurchasedByDate && voucherPurchasedByDate.size() > 0 ) {
			
			int minVouchersToRedeem = ((int)(voucherPurchasedByDate.size() * 0.1 ));
					
			int vouchersToRedeem = ( minVouchersToRedeem + (int)Math.random() * ( voucherPurchasedByDate.size() - minVouchersToRedeem ) ); 
			
			Collections.shuffle( voucherPurchasedByDate );
			
			for( int v = 0; v <= vouchersToRedeem; v++ ) {
			
				Subscribers subscriber = DAOSubscribers.getInstance( mysql ).getSubscriberFromStatsPurchase( voucherPurchasedByDate.get( v ).getPurchaseId() );
				
				cdrVR.setMsisdnStrategyFixed( subscriber.getMsisdn() );
				cdrVR.setVoucherCodeStrategyFixed( voucherPurchasedByDate.get( v ).getCode() );
				cdrVR.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
				cdrVR.setDateStrategyFixed( redemptionDate );
				cdrVR.setLocationStrategyFixed( "Milan" );
				cdrVR.setPartnerStrategyFixed( voucherPurchasedByDate.get( v ).getPartnerId() );
						
				cdrVR.addLines( 1 );
							
			}
		
		}
		
		cdrVR.print();
		
		cdrVR.save();
		
		cdrVR.send( sshService, "/nfsdata/files/cdr/deposit/VOUCHER_CDR/", sshUser );
		
		System.out.println( "File name: " + cdrVR.getFileName() );
		
	}
	
	@AfterSuite
	public void end() {
		mysql.close();
	}
	
}
