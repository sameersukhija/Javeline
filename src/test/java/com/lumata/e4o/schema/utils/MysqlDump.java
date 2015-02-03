package com.lumata.e4o.schema.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.schema.tenant.BonusCampaignLimits;
import com.lumata.e4o.schema.tenant.CampaignExpectedKpi;
import com.lumata.e4o.schema.tenant.CampaignInfos;
import com.lumata.e4o.schema.tenant.CampaignMaster;
import com.lumata.e4o.schema.tenant.CampaignNotif;
import com.lumata.e4o.schema.tenant.CampaignParams;
import com.lumata.e4o.schema.tenant.CampaignRewards;
import com.lumata.e4o.schema.tenant.CampaignSubstates;
import com.lumata.e4o.schema.tenant.CampaignTypes;
import com.lumata.e4o.schema.tenant.Campaigns;
import com.lumata.e4o.schema.tenant.CampaignsHistory;
import com.lumata.e4o.schema.tenant.CampaignsHourly;
import com.lumata.e4o.schema.tenant.CampaignsWeekly;
import com.lumata.e4o.schema.tenant.CampaignsWeeklySubsStatus;
import com.lumata.e4o.schema.tenant.CatalogOfferContent;
import com.lumata.e4o.schema.tenant.CatalogOfferPrice;
import com.lumata.e4o.schema.tenant.CatalogOfferPriceChannels;
import com.lumata.e4o.schema.tenant.CatalogOfferPricePrices;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.CatalogProductCharacteristics;
import com.lumata.e4o.schema.tenant.CatalogProductSpecificCharacteristics;
import com.lumata.e4o.schema.tenant.CatalogProductTypeCharacteristics;
import com.lumata.e4o.schema.tenant.CatalogProductTypes;
import com.lumata.e4o.schema.tenant.CatalogProducts;
import com.lumata.e4o.schema.tenant.CatalogRelatedOffers;
import com.lumata.e4o.schema.tenant.CatalogRelatedProducts;
import com.lumata.e4o.schema.tenant.KpiCampaign;
import com.lumata.e4o.schema.tenant.OfferStock;
import com.lumata.e4o.schema.tenant.OffoptimAlgorithm;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItemsReleased;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPackReleased;
import com.lumata.e4o.schema.tenant.OffoptimOfferHistory;
import com.lumata.e4o.schema.tenant.OffoptimRuleset;
import com.lumata.e4o.schema.tenant.OffoptimRulesetRequestor;
import com.lumata.e4o.schema.tenant.RecommendedCampaigns;
import com.lumata.e4o.schema.tenant.StatsCampaign;
import com.lumata.e4o.schema.tenant.StatsCampaignArchive;
import com.lumata.e4o.schema.tenant.StatsOffer;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.SubscribersAll;


public class MysqlDump {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
	Mysql mysql;
	
	private enum E4OManagers {
		
		CampaignManager,
		CatalogManager,
		OfferOptmizer,
		Subscribers;
		
	}
	
	/* 	Initialize Environment */
	@Parameters({"environment", "nfsdataServer", "user", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String nfsdataServer, @Optional("superman") String user, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		environment = "E4O_QA3_NE";
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Test( enabled = true )
	public void mysqldump() throws IOFileException, FieldException, IOException, InterruptedException {
		
		MysqlUtils.dump( mysql, "backup_regression.sql", getTables( E4OManagers.CatalogManager, E4OManagers.OfferOptmizer ) );
			
	}
	
	private ArrayList<Object> getTables( E4OManagers... managers ) {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		for( E4OManagers manager : managers) {
		
			switch( manager ) {
			
				case CampaignManager: {
					tables.addAll( getCampaignManagerTables() );						
					break;
				}
				case CatalogManager: {
					tables.addAll( getCatalogManagerTables() );					
					break;
				}
				case OfferOptmizer: {
					tables.addAll( getOfferOptimizerTables() );					
					break;
				}
				case Subscribers: {
					tables.addAll( getOfferOptimizerTables() );					
					break;
				}
				default: break;
				
			}			
			
		}
		
		return tables;		
		
	}
	
	private ArrayList<Object> getCampaignManagerTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new BonusCampaignLimits() );
		tables.add( new CampaignExpectedKpi() );	
		tables.add( new CampaignInfos() );
		tables.add( new CampaignMaster() );
		tables.add( new CampaignNotif() );
		tables.add( new CampaignParams() );
		tables.add( new CampaignRewards() );
		tables.add( new CampaignSubstates() );
		tables.add( new CampaignTypes() );
		tables.add( new Campaigns() );
		tables.add( new CampaignsHistory() );
		tables.add( new CampaignsHourly() );
		tables.add( new CampaignsWeekly() );
		tables.add( new CampaignsWeeklySubsStatus() );
		tables.add( new KpiCampaign() );
		tables.add( new RecommendedCampaigns() );
		tables.add( new StatsCampaign() );
		tables.add( new StatsCampaignArchive() );	
		
		return tables;
		
	}
	
	private ArrayList<Object> getCatalogManagerTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new CatalogOfferContent() );
		tables.add( new CatalogOfferPrice() );	
		tables.add( new CatalogOfferPriceChannels() );
		tables.add( new CatalogOfferPricePrices() );
		tables.add( new CatalogOffers() );
		tables.add( new CatalogProductCharacteristics() );
		tables.add( new CatalogProductSpecificCharacteristics() );
		tables.add( new CatalogProductTypeCharacteristics() );
		tables.add( new CatalogProductTypes() );
		tables.add( new CatalogProducts() );
		tables.add( new CatalogRelatedOffers() );
		tables.add( new CatalogRelatedProducts() );
		tables.add( new OfferStock() );
		tables.add( new StatsOffer() );
		
		return tables;
		
	}
	
	private ArrayList<Object> getOfferOptimizerTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new OffoptimAlgorithm() );
		tables.add( new OffoptimCustomerItems() );	
		tables.add( new OffoptimCustomerItemsReleased() );
		tables.add( new OffoptimCustomerPack() );
		tables.add( new OffoptimCustomerPackReleased() );
		tables.add( new OffoptimOfferHistory() );
		tables.add( new OffoptimRuleset() );
		tables.add( new OffoptimRulesetRequestor() );
		
		return tables;
		
	}
	
	private ArrayList<Object> getSubscribersTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new Subscribers() );
		tables.add( new SubscribersAll() );	
		tables.add( new OffoptimCustomerItemsReleased() );
		tables.add( new OffoptimCustomerPack() );
		tables.add( new OffoptimCustomerPackReleased() );
		tables.add( new OffoptimOfferHistory() );
		tables.add( new OffoptimRuleset() );
		tables.add( new OffoptimRulesetRequestor() );
		
		return tables;
		
	}

}
