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
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;
import com.lumata.e4o.schema.tenant.KpiCampaign;
import com.lumata.e4o.schema.tenant.LoyaltyBadges;
import com.lumata.e4o.schema.tenant.LoyaltyBadgesTypes;
import com.lumata.e4o.schema.tenant.LoyaltyClasses;
import com.lumata.e4o.schema.tenant.LoyaltyPrograms;
import com.lumata.e4o.schema.tenant.LoyaltySubsTest;
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
import com.lumata.e4o.schema.tenant.SalesChannels;
import com.lumata.e4o.schema.tenant.SetHobbies;
import com.lumata.e4o.schema.tenant.SetOptions;
import com.lumata.e4o.schema.tenant.StatsCampaign;
import com.lumata.e4o.schema.tenant.StatsCampaignArchive;
import com.lumata.e4o.schema.tenant.StatsOffer;
import com.lumata.e4o.schema.tenant.StatsPurchase;
import com.lumata.e4o.schema.tenant.StatsSubs;
import com.lumata.e4o.schema.tenant.StatsSubsAccount;
import com.lumata.e4o.schema.tenant.StatsSubsAccountOld;
import com.lumata.e4o.schema.tenant.StatsSubsBundle;
import com.lumata.e4o.schema.tenant.StatsSubsData;
import com.lumata.e4o.schema.tenant.StatsSubsDataOld;
import com.lumata.e4o.schema.tenant.StatsSubsPostpaid;
import com.lumata.e4o.schema.tenant.StatsSubsPostpaidOld;
import com.lumata.e4o.schema.tenant.StatsSubsPrepaid;
import com.lumata.e4o.schema.tenant.StatsSubsPrepaidOld;
import com.lumata.e4o.schema.tenant.StatsSubsVoice;
import com.lumata.e4o.schema.tenant.StatsSubsVoiceOld;
import com.lumata.e4o.schema.tenant.SubsBadges;
import com.lumata.e4o.schema.tenant.SubsClasses;
import com.lumata.e4o.schema.tenant.SubsData;
import com.lumata.e4o.schema.tenant.SubsNotif;
import com.lumata.e4o.schema.tenant.SubsRelations;
import com.lumata.e4o.schema.tenant.SubsVoice;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.SubscribersAll;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.schema.tenant.TokenEvent;
import com.lumata.e4o.schema.tenant.TokenLabel;
import com.lumata.e4o.schema.tenant.TokenType;
import com.lumata.e4o.schema.tenant.VoucherCodeUnlimited;
import com.lumata.e4o.schema.tenant.VoucherCodes;


public class MysqlDump {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
	Mysql mysql;
	
	private enum E4OManagers {
		
		CampaignManager,
		CatalogManager,
		LoyaltyManager,
		Files,
		OfferOptmizer,
		Subscribers,
		Subs,
		Stats;
		
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
		
		MysqlUtils.dump( 
				mysql, 
				"backup_regression.sql", 
				getTables( 
						E4OManagers.CampaignManager,
						E4OManagers.CatalogManager, 
						E4OManagers.LoyaltyManager,
						E4OManagers.Files,
						E4OManagers.OfferOptmizer,
						E4OManagers.Subscribers,
						E4OManagers.Subs,
						E4OManagers.Stats						
				) 
		);
			
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
				case LoyaltyManager: {
					tables.addAll( getLoyaltyManagerTables() );					
					break;
				}
				case Files: {
					tables.addAll( getFilesTables() );					
					break;
				}
				case OfferOptmizer: {
					tables.addAll( getOfferOptimizerTables() );					
					break;
				}
				case Subscribers: {
					tables.addAll( getSubscribersTables() );					
					break;
				}
				case Subs: {
					tables.addAll( getSubsTables() );					
					break;
				}
				case Stats: {
					tables.addAll( getStatsTables() );					
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
				
		tables.add( new SalesChannels() );
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
		tables.add( new Token() );
		tables.add( new TokenEvent() );
		tables.add( new TokenLabel() );
		tables.add( new TokenType() );
		tables.add( new StatsPurchase() );
		tables.add( new VoucherCodeUnlimited() );
		tables.add( new VoucherCodes() );
		
		return tables;
		
	}
	
	private ArrayList<Object> getLoyaltyManagerTables() {
	
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new LoyaltyBadges() );
		tables.add( new LoyaltyBadgesTypes() );	
		tables.add( new LoyaltyClasses() );
		tables.add( new LoyaltyPrograms() );
		tables.add( new LoyaltySubsTest() );
		
		return tables;
		
	}
	
	private ArrayList<Object> getFilesTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new FilesMeta() );
		tables.add( new FilesData() );	
		
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
		tables.add( new SetOptions() );
		tables.add( new SetHobbies() );
				
		return tables;
		
	}

	private ArrayList<Object> getSubsTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new SubsBadges() );
		tables.add( new SubsClasses() );	
		tables.add( new SubsData() );	
		tables.add( new SubsNotif() );
		tables.add( new SubsRelations() );	
		tables.add( new SubsVoice() );	
				
		return tables;
		
	}
	
	private ArrayList<Object> getStatsTables() {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new StatsSubs() );
		tables.add( new StatsSubsAccount() );	
		tables.add( new StatsSubsAccountOld() );
		tables.add( new StatsSubsBundle() );	
		tables.add( new StatsSubsData() );
		tables.add( new StatsSubsDataOld() );	
		tables.add( new StatsSubsPostpaid() );	
		tables.add( new StatsSubsPostpaidOld() );
		tables.add( new StatsSubsPrepaid() );
		tables.add( new StatsSubsPrepaidOld() );
		tables.add( new StatsSubsVoice() );
		tables.add( new StatsSubsVoiceOld() );
				
		return tables;
		
	}
	
}
