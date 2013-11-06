package com.lumata.expression.operators.gui.common;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class MenuBar {

	private static final Logger logger = LoggerFactory.getLogger(MenuBar.class);
		
	public enum HomeSections { 
		
		CAMPAIGNS, LOYALTY, CATALOG, CUSTOMER_CARE, REPORTS, BASE_MANAGEMENT,PREDICTIONS, RECOMMENDATIONS, RULE_ENGINE, OPERATIONS, ADMINSTRATION, ABOUT;
		
		public String section_id_prefix = "gwt-debug-BarCaptionHome";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.FIXED;
			
	};
	
	public enum CampaignSections { 
		
		CAMPAIGN_CREATION, CAMPAIGN_MODEL, CAMPAIGN_TYPE, CAMPAIGN_WIZARD, CAMPAIGNS_RESULTS;
		
		public String section_id_prefix = "gwt-debug-InputCM";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.FIXED;
			
	};
	
	public enum LoyaltySections { 
		
		PROGRAMS;
		
		public String section_id_prefix = "gwt-debug-InputCM";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.FIXED;
			
	};
	
	public enum CatalogSections { 
		
		OFFERS, PRODUCTS, PRODUCT_TYPES, SUPPLIERS, OFFER_OPTIMISATION;
		
		public String section_id_prefix = "gwt-debug-actrule-catalog-";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.DYNAMIC;
			
	};
		
	public enum ReportSections { 
		
		IMMEDIATE_REPORTS, SCHEDULED_REPORTS, GRAPHS;
		
		public String section_id_prefix = "gwt-debug-actrule-reports-";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.DYNAMIC;
			
	};	

	public enum BaseManagementSections { 
		
		RANGES, RANGES_MODELS, LIFECYCLE, PROFILING, NET_PRESENT_VALUE, CONFIGURATION;
		
		public String section_id_prefix = "gwt-debug-Enter-";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.DYNAMIC;
			
	};

	public enum PredictionSections { 
		
		ACTIVATION, QUESTIONS, GROUPS;
		
		public String section_id_prefix = "gwt-debug-actrule-prediction-";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.DYNAMIC;
			
	};
	
	public enum RecommendationSections { 
		
		RESULTS;
		
		public String section_id_prefix = "gwt-debug-actrule-recommandation-";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.DYNAMIC;
			
	};
	
	public enum OperationsSections { 
		
		ADMINISTRATION, MONITORING, CONTROL_PANEL, ACTION, QO_S, TRAFFIC_GENERATOR;
		
		public String section_id_prefix = "gwt-debug-InputOP";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.FIXED;
			
	};
	
	public enum AdministrationSections { 
		
		CONFIGURE, LOGIN_MANAGEMENT, SUBSCRIBER_MANAGEMENT, COMMODITIES, CHANNEL, CRITERIA;
		
		public String section_id_prefix = "gwt-debug-actrule-administration-";
		public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.DYNAMIC;
			
	};
	
	public static boolean select( SeleniumWebDriver selenium, ISection section, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=" + section.getSectionID()) );
		
		WebElement sectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, section.getSectionID(), timeout, interval);
		if( sectionTab == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the " + section.getSectionName() + " Section" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the " + section.getSectionName() + " Section" ) );
		sectionTab.click();
		
		return true;
		
	}
	
}
