package com.lumata.unit.json.loyaltymanager;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationElement.JsonErrorActions;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;

public class JSONLoyaltiesCreationTest {

	private JSONLoyaltiesCreation underTest = null;
	
	@Parameters({"loyaltiesCreationFile"})
	@BeforeTest
	public void init(@Optional("loyaltyCreationTemplate") String loyaltiesCreationFile) throws JSONSException {

		String resourcePath = "input/loyaltymanager";
		String resourceFile = loyaltiesCreationFile;

		underTest = new JSONLoyaltiesCreation(resourcePath, resourceFile);
	}
	
	@Test
	public void badCharacteristicsReading() throws JSONSException {
		
		underTest.setCurrentElementById(0);
		
		try {
			underTest.getBadges();
			
			Assert.assertTrue(false, "\"getBadges\" methods must fail!");
		} catch ( JSONSException e ) {
			
			Assert.assertTrue(true);
		}
		
		underTest.setCurrentElementById(1);
		
		try {
			underTest.getClasses();
			
			Assert.assertTrue(false, "\"getClasses\" methods must fail!");
		} catch ( JSONSException e ) {
			
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void readEntire() throws JSONSException {
		
		Assert.assertEquals( underTest.getList().size(), 2, "The lenght does not match!");
		
		underTest.setCurrentElementById(0);
		
		Assert.assertEquals(underTest.getCurrentElement().getEnabled(), Boolean.TRUE);
		Assert.assertEquals(underTest.getCurrentElement().getDelete(), Boolean.FALSE);
		Assert.assertEquals(underTest.getName(), "Points Program");
		Assert.assertEquals(underTest.getDescription(), "The points description");
		Assert.assertEquals(underTest.getType(), LoyaltyTypes.Points);		
		Assert.assertEquals(underTest.getClasses(), Arrays.asList("Gold", "Silver", "Bronze"));

		JsonErrorActions errorActions = underTest.getCurrentElement().getErrorActions();
		
		Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS), ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD);
		Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.GENERAL_ERROR), ElementErrorActionType.RETURN_ERROR);	
		
		underTest.setCurrentElementById(1);
		
		Assert.assertEquals(underTest.getCurrentElement().getEnabled(), Boolean.TRUE);
		Assert.assertEquals(underTest.getCurrentElement().getDelete(), Boolean.FALSE);
		Assert.assertEquals(underTest.getName(), "Badges Program");
		Assert.assertEquals(underTest.getDescription(), "The badges description");
		Assert.assertEquals(underTest.getType(), LoyaltyTypes.Badges);		
		Assert.assertEquals(underTest.getBadges(), Arrays.asList("Archery", "Astronomy", "Camping"));

		errorActions = underTest.getCurrentElement().getErrorActions();
		
		Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS), ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD);
		Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.GENERAL_ERROR), ElementErrorActionType.RETURN_ERROR);	
				
	}
}
