package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Val.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.schema.tenant.CatalogProductTypes;

public class DAOProductType extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOProductType.class );

	
	SimpleDateFormat sdf;
	
	public DAOProductType( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOProductType getInstance( Mysql mysql ) {
		return new DAOProductType( mysql );
	}
	

	private CatalogProductTypes getProductType( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		CatalogProductTypes ProductType = null;
		
		try {
			
			while( rs.next() ) {
				
				ProductType = new CatalogProductTypes( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return ProductType;
		
	}
	public String getCatalogProductTypes(String type_name) {
			String query = select() . from(new CatalogProductTypes() ).orderBy( CatalogProductTypes.Fields.type_name).limit( 1 ).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getCatalogProductTypes(query);
		
	}
	
}
