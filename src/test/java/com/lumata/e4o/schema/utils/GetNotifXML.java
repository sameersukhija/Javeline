package com.lumata.e4o.schema.utils;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;


@TCMysqlMaster
public class GetNotifXML extends ParentTestCase {

	@Test( enabled=true, priority = 1 )
	public void getCampaignXML() throws Exception {
		
		final String NOTIF_XML = "notif.xml";
		
		FilesMeta fm = new FilesMeta();
		fm.setName( NOTIF_XML );
		
		String query = select().from( fm ).where( op( FilesMeta.Fields.name  ).eq() ).build();
		
		ResultSet rs = mysqlMaster.execQuery( query );
		
		FilesData fd = new FilesData();
		
		while( rs.next() ) {
						
			FilesMeta fmRow = new FilesMeta( rs );
			
			fd.setId( fmRow.getId() );
			
		} 
		
		query = select().from( fd ).where( op( FilesData.Fields.id  ).eq() ).build();
		
		rs = mysqlMaster.execQuery( query );
		
		String xml = new String();
		
		while( rs.next() ) {
			
			xml = rs.getString( FilesData.Fields.content.name() );
			
		} 
		
		IOFileUtils.saveResource( xml, "campaignmanager", "notif.xml" );

	}

}
