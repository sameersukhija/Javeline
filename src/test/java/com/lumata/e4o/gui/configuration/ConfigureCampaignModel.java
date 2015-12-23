//package com.lumata.e4o.gui.configuration;
//
//import java.lang.reflect.Method;
//import java.text.ParseException;
//
//import org.testng.Reporter;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
//import com.lumata.common.testing.log.Log;
//import com.lumata.common.testing.validating.Format;
//import com.lumata.e4o.exceptions.FormException;
//import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
//import com.lumata.e4o.testing.common.ParentTestCase;
//import com.lumata.e4o.testing.common.TCOwner;
//import com.lumata.e4o.testing.common.TCOwners;
//import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
//
//@TCOwners({
//	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" ),
//})
//@TCSeleniumWebDriver
//public class ConfigureCampaignModel extends ParentTestCase {
//	
//	private final boolean TEST_ENABLED = true;
//	
//	private CampaignModelForm campaignModelForm;
//	
//	@BeforeMethod
//	public void initCampaignsForm( Method method ) throws NetworkEnvironmentException, FormException {		
//	
//		/** Campaigns Form **/
//		campaignModelForm = new CampaignModelForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
//		
//		seleniumWebDriver.setTestName( method.getName() );
//		
//	}
//	
//	private void initTest() {
//		Reporter.log( Log.CREATING.createMessage( "Creation of \"Campaign Model Form\"." ), LOG_TO_STD_OUT);
//	}
//	
//	@Test( enabled=TEST_ENABLED, priority = 1 )
//	public void createCampaignModel() throws FormException, ParseException {
//		
//		initTest();
//		
//		final String CAMPAIGN_MODEL_NAME = Format.addTimestamp( "CModel_" );
//		
//		campaignModelForm.
//			openForm().
//			addBtn().
//			setModelName( CAMPAIGN_MODEL_NAME ).
//			setModelDescription( CAMPAIGN_MODEL_NAME + " description" ).
//			setModelType( "No type" ).
//			setUseHierarchy().
//			/** add 2 events**/
//			addEventBtn().
//			addEventBtn().
//			/** select event n° 1 **/
//			selectEvent( 1 ).
//			/** set event n° 1 **/
//			selectEventType( "Revenue" ).
//			/** add 3 actions to the event n° 1**/
//			addActionBtn().
//			addActionBtn().
//			addActionBtn().
//			/** select action n° 1 of the event n° 1 **/
//			selectEventAction( 1 ).
//			/** set action n° 1 of the event n° 1 **/
//			setEventActionType( "Commodities.Credit.Points" ).
//			setEventActionValue( "100" ).
//			setEventActionUnit( "/Revenue" ).		
//			/** select action n° 3 of the event n° 1 **/
//			selectEventAction( 3 ).
//			/** delete action n° 3 of the event n° 1 **/
//			deleteEventAction().
//			/** add 3 events **/
//			addEventsBtn( 3 ).
//			/** set event n° 4 **/
//			selectEventType( 4, "Revenue" ).
//			/** add 3 actions in the event n° 4 **/
//			addActionsBtn( 4, 3 ).
//			/** set action n° 1 of the event n° 4 in one line **/
//			setEventAction( 4, 1, "Commodities.Credit.Points", "100", "/Revenue", null ).
//			/** delete action n° 2 of the event n° 4 in one line**/
//			deleteEventAction( 4, 2 ).
////			setEventAction( 4, 2, "Commodities.Activate.ExtBonus", null, null ).
//			/** set criteria **/
//			/** set criteria after setting event and criteria rows **/
//			selectEvent( 1 ).
//			selectCriteria( 1 ).
//			addCriteriasBtn( 4 ).
//			setCriteriaType( "Bonus.Points. Points balance" ).
//			setCriteriaOperator( "=" ).
//			setCriteriaValue( "100" ).
//			/** set criteria after setting event row only. The criteria row is passed like parameter **/
//			selectEvent( 2 ).
//			addCriteriasBtn( 3 ).
//			/** note: the criteria line n° 1 and n° 3 can be set like points. The criteria line n° 2 is the AND operator **/
//			setCriteriaType( 1, "Bonus.Points. Points balance" ).
//			setCriteriaOperator( 1, "=" ).
//			setCriteriaValue( 1, "50" ).
//			setCriteriaLogicalOperator( 2, "OR" ).
//			setCriteriaType( 3, "Bonus.Points. Points balance" ).
//			setCriteriaOperator( 3, "=" ).
//			setCriteriaValue( 3, "300" ).
//			/** add 5 criteria at the first level **/
//			addCriteriasBtn( 1, 5 ).
//			/** set criteria type n° 1 of the event n° 1 **/
//			setCriteriaType( 1, "Bonus.Points. Points balance", 1 ).
//			/** set operator of the criteria n° 1 of the event n° 1 **/
//			setCriteriaOperator( 1, "=", 1 ).
//			/** set value of the criteria n° 1 of the event n° 1 **/
//			setCriteriaValue( 1, "100", 1 ).
//			/** set logical criteria n° 2 of the event n° 1 **/
//			setCriteriaLogicalOperator( 1, "OR", 2 ).
//			/** add sub criteria in the criteria n° 3 of the event n° 1 **/
//			addSubCriteriaContainerBtn( 1, 3 ).
//			/** set criteria n° 3 in the sub criteria n° 3 of the criteria n° 3 and event n° 1 **/
//			setCriteriaType( 1, "Bonus.Points. Points balance", 3, 3 ).
//			/** set operator in the sub criteria n° 3 of the criteria n° 3 and event n° 1 **/
//			setCriteriaOperator( 1, "=", 3, 3 ).
//			/** set value in the sub criteria n° 3 of the criteria n° 3 and event n° 1 **/
//			setCriteriaValue( 1, "500", 3, 3 ).
//			/** set logical criteria n° 2 in the sub criteria container of the criteria n° 3 and event n° 1 **/
//			setCriteriaLogicalOperator( 1, "OR", 3, 2 ).
//			/** set criteria type n° 5 of the event n° 1**/
//			setCriteriaType( 1, "Bonus.Points. Points balance", 5 ).
//			/** set criteria n° 9 **/			
//			setCriteriaType( 1, "Bonus.Points. Points balance", 9 ).
//			/** set logical operator n° 8 **/
//			setCriteriaLogicalOperator( 1, "OR", 8 ).
//			/** set operator of the criteria n° 9 and event n° 1 **/
//			setCriteriaOperator( 1, "=", 9 ).
//			/** set value of the criteria n° 9 and event n° 1 **/
//			setCriteriaValue( 1, "600", 9 ).
//			/** add sub criteria container in the criteria n° 9 of the event n° 1 **/
//			addSubCriteriaContainerBtn( 1, 9 ).		
//			/** set sub criteria type n° 1 in the criteria n° 9 of the event n° 1 **/
//			setCriteriaType( 1, "Bonus.Points. Points balance", 9, 1 ).
//			/** set sub criteria operator n° 1 in the criteria n° 9 of the event n° 1 **/
//			setCriteriaOperator( 1, "=", 9, 1 ).
//			/** set sub criteria value n° 1 in the criteria n° 9 of the event n° 1 **/
//			setCriteriaValue( 1, "700", 9, 1 ).
//			/** set sub logical operator n° 2 in the criteria n° 9 of the event n° 1 **/
//			setCriteriaLogicalOperator( 1, "OR", 9, 2 ). 
//			/** add 5 new criteria at the first level **/
//			addCriteriasBtn( 1, 5 ).
//			/** add sub criteria container in the criteria n° 11 of the event n° 1 **/
//			addSubCriteriaContainerBtn( 1, 11 ).
//			/** add 5 sub criteria in the criteria n° 11 of the event n° 1 **/
//			addSubCriteriasBtn( 1, 11, 5 )
//					
//			;
//			
//		
////			/** set criteria passing event row and criteria row like parameters **/
////			addCriteriasBtn( 3, 2 ).					
////			setCriteriaType( 3, 2, "Bonus.Points. Points balance" ).
////			setCriteriaOperator( 3, 2, "=" ).
////			setCriteriaValue( 3, 2, "100" )
//			
//			
////			/** set action time **/
////			addActionTimeBtn( 4, 2 ).
////			setActionTimeStartTime( "01:00:00" ).
////			cancelActionTimeBtn().
////			addActionTimeBtn( 4, 2 ).
////			setActionTimeStartTime( "05:00:00" ).
////			setActionTimeDurationRelative( "3", "Days" ).
////			setActionTimeDurationAbsolute( Format.getMysqlDate( Calendar.getInstance() ), "02:30:00" ).
////			cancelActionTimeBtn().
//			/** set beneficiary **/
////			setCampaignModelBeneficiary( 3, true ).
////			setCampaignModelBeneficiaryDisabled( 3 ).
////			setCampaignModelBeneficiaryEnabled( 3 ).
//			/** set notification **/
////			openDialogueNotification( 2 ).
////			cancelDialogueNotification().
////			openDialogueNotification().
////			editDialogueNotification( English, SMS ).
////			setDialogueNotificationMessage( "notification message" ).
////			cancelDialogueNotificationEditing().
////			editDialogueNotification( English, SMS ).
////			setDialogueNotificationMessage( "notification message" ).
////			saveDialogueNotificationEditing().
////			editDialogueNotification( English, SMS ).
////			setDialogueNotificationMessage( "modify notification message" ).
////			saveDialogueNotificationEditing().
////			saveDialogueNotification().
////			deleteEventBtn( 1 )
//			;
//		
//	}
//	
//}