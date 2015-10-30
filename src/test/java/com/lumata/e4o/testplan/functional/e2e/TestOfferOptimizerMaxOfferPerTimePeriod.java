package com.lumata.e4o.testplan.functional.e2e;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm.ExpiredOfferBehaviour;
import com.lumata.e4o.gui.catalogmanager.RulesForm.MaxOffersPerTimePeriodUnit;
import com.lumata.e4o.gui.catalogmanager.RulesForm.OptimizationAlgorithm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenFormat;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenValidityType;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenValidityUnit;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;


@TCSeleniumWebDriver
public class TestOfferOptimizerMaxOfferPerTimePeriod extends ParentTestCase {
	
	private final boolean TEST_ENABLED = false;
		
	private SalesChannelsForm salesChannelsForm;
	private TokenTypeForm tokenTypesForm;
	private RulesForm rulesForm;
	private CampaignModelForm campaignModelsForm;
	private CampaignsForm campaignForm;
	
	private enum SalesChannels { CHA, CHB, CHC }

	private final String TOKEN_TYPE = "TokenTypeAB";
	private final String RULE = "RuleAB";
	private final String CAMPAIGN_MODEL = "CMRuleAB";
	private final String CAMPAIGN = "CMRuleAB1";
	
	@BeforeClass
	public void initCampaignsForm() throws NetworkEnvironmentException, FormException {		
		
		/** SalesChannels Form **/
		salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Token Type Form **/
		tokenTypesForm = new TokenTypeForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Rules Form **/
		rulesForm = new RulesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Campaign Model Form **/
		campaignModelsForm = new CampaignModelForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Campaigns Form **/
		campaignForm = new CampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
	}
	
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void configureSalesChannels() throws FormException {
		
		salesChannelsForm.open();
		
		for( SalesChannels salesChannel : SalesChannels.values() ) {
			
			if( !salesChannelsForm.isSalesChannelExisting( salesChannel.name() ) ) {
				
				salesChannelsForm.
					clickAddButton().
					setSalesChannelName( salesChannel.name() ).
					saveSalesChannel().
					activateSalesChannel( salesChannel.name() );
				
			}
			
		}
		
	}

	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void configureTokenTypes() throws FormException {
		
		tokenTypesForm.openForm();
		
		Boolean isTokenTypeInList = tokenTypesForm.isTokenTypeInList( TOKEN_TYPE );
		
		if( !isTokenTypeInList ) { tokenTypesForm.addBtn(); } 
		else { tokenTypesForm.editSaveBtn(); }

		tokenTypesForm.
			setDescription( TOKEN_TYPE ).
			setFormat( TokenFormat.imm5 ).
			setValidityType( TokenValidityType.Relative ).
			setValidityValue( 1 ).
			setValidityUnit( TokenValidityUnit.days ).
			setUnlimitedRedraw( true );
		
		if( !isTokenTypeInList ) { tokenTypesForm.saveBtn(); } 
		else { tokenTypesForm.editSaveBtn(); }
		
		tokenTypesForm.goToHome();
		
	}

	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void configureRules() throws FormException {

		rulesForm.openForm();
		
		Boolean isRuleInList = rulesForm.isRuleNameInList( RULE );
		
		if( !isRuleInList ) {
							
			rulesForm.
				addBtn().
				setName( RULE );			
		
		} else {
			
			rulesForm.
				editByName( RULE ).
				removeAllSelectedChannels();
			
		}
		
		rulesForm.
			setDescription( RULE ).
			setTokenType( TOKEN_TYPE ).
			addChannel( SalesChannels.CHA.name() ).
			setMaxOffersInTimePeriodValue( SalesChannels.CHA.name(), 1 ).
			setMaxOffersInTimePeriodUnit( SalesChannels.CHA.name(), MaxOffersPerTimePeriodUnit.Day ).
			checkMandatoryChannel( SalesChannels.CHA.name() ).
			setPriorityChannel( SalesChannels.CHA.name(), 2 ).
			addChannel( SalesChannels.CHB.name() ).
			setMaxOffersInTimePeriodValue( SalesChannels.CHB.name(), 1 ).
			setMaxOffersInTimePeriodUnit( SalesChannels.CHB.name(), MaxOffersPerTimePeriodUnit.Day ).
			checkMandatoryChannel( SalesChannels.CHB.name() ).
			setPriorityChannel( SalesChannels.CHB.name(), 1 ).
			addChannel( SalesChannels.CHC.name() ).
			setMaxOffersPerAllocation( SalesChannels.CHC.name(), 1 ).
			setAlgorithm( OptimizationAlgorithm.Stockbased.value() ).
			setKeepOfferConsistentYes().
			setPrevioslyAcceptedOfferYes().
			setMaxNumberOfOffers( 10 ).
			setExpiredOfferBehaviour( ExpiredOfferBehaviour.Pickupnewoffer );
		
		if( !isRuleInList ) { rulesForm.saveBtn(); } 
		else { rulesForm.saveEditBtn(); }
					
		rulesForm.goToHome();
		
	}
	
	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void configureCampaignModels() throws FormException {
		
		campaignModelsForm.
			openForm().
			addBtn().
			setName( CAMPAIGN_MODEL ).
			setDescription( CAMPAIGN_MODEL + " description" ).
			addEventBtn().
			selectEventType( 1, "Revenue" ).
			addActionBtn().
			selectEventAction( 1 ).
			setEventActionType( "Rulesets.RuleAB" ).
			setBeneficiaryEnabled().
			saveBtn();
		
	}
	
	//@Test( enabled=TEST_ENABLED, priority = 2 )
	@Test( enabled=true, priority = 2 )
	public void configureCampaigns() throws FormException {
		
		campaignForm.
			openForm().
			addBtn();
			//.setName( CAMPAIGN ) ;
			
			
//			setName( CAMPAIGN_MODEL ).
//			setDescription( CAMPAIGN_MODEL + " description" ).
//			addEventBtn().
//			selectEventType( 1, "Revenue" ).
//			addActionBtn().
//			selectEventAction( 1 ).
//			setEventActionType( "Rulesets.RuleAB" ).
//			setBeneficiaryEnabled().
//			saveBtn();
		
	}
	
}
