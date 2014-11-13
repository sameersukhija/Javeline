package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Val.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.StatsPurchase;
import com.lumata.e4o.schema.tenant.VoucherCodes;

public class DAOStatsPurchase extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOStatsPurchase.class );
	
	SimpleDateFormat sdf;
	
	public DAOStatsPurchase( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOStatsPurchase getInstance( Mysql mysql ) {
		return new DAOStatsPurchase( mysql );
	}
	
	public Boolean isVoucher( String voucherCode ) {
		
		VoucherCodes vouchercode = new VoucherCodes();
		
		vouchercode.setCode( voucherCode );
		
		String query = select().from( vouchercode ).where( op( VoucherCodes.Fields.code ).eq() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		boolean found = false;
		
		try {
			
			while( rs.next() ) {
				
				found = true;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return found;
		
	}

	private StatsPurchase getPurchase( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		StatsPurchase purchase = null;
		
		try {
			
			while( rs.next() ) {
				
				purchase = new StatsPurchase( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return purchase;
		
	}
	
	private ArrayList<StatsPurchase> getPurchaseList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<StatsPurchase> purchaseList = new ArrayList<StatsPurchase>();
		
		try {
			
			while( rs.next() ) {
				
				StatsPurchase purchase = new StatsPurchase( rs );

				purchaseList.add( purchase );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return purchaseList;
		
	}
	
	public StatsPurchase getPurchaseListById( Long purchase_id ) {
		
		String query = select().
						from( new StatsPurchase() ).
						where( op( StatsPurchase.Fields.purchase_id ).eq( purchase_id ) ).
						build();
		
		return getPurchase( query );
		
	}
	
}
