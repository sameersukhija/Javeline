package com.lumata.e4o.schema.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
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
import com.lumata.common.testing.system.Server;
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
import com.lumata.e4o.system.environment.ExpressionKernelCommands;


public class MysqlRestore {
	
	ExpressionKernelCommands ekc;
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "nfsdataServer", "user", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String nfsdataServer, @Optional("superman") String user, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		environment = "E4O_QA3_NE";
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		mysql = new Mysql( env.getDataSource( tenant ) );
		
		ekc = new ExpressionKernelCommands( env.getServer( "actrule" ), env.getSSHService( "actrule" ), "root" );
		
	}
	
	@Test( enabled = true )
	public void mysqlrestore() throws IOFileException, FieldException, IOException, InterruptedException {
		
		Assert.assertTrue ( 
			
			MysqlUtils.restore( 
					mysql, 
					"backup_regression.sql" 
			)
			
		);
		
		Assert.assertTrue( ekc.expressionRestart() );
					
	}
	
}
