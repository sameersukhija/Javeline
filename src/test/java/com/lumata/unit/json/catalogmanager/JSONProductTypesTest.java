package com.lumata.unit.json.catalogmanager;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationElement.JsonErrorActions;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.CharacteristicType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;

public class JSONProductTypesTest {
	
	private JSONProductTypes underTest = null;
	
	@Parameters({"productTypesFile"})
	@BeforeTest
	public void init(@Optional("productTypesTemplate") String productTypesFile) throws JSONSException {

		String resourcePath = "input/catalogmanager/productTypes";
		String resourceFile = productTypesFile;

		underTest = new JSONProductTypes(resourcePath, resourceFile);
	}
	
	@Test
	public void badCharacteristicsReading() throws JSONSException {
		
		underTest.setCurrentElementById(0);
		
		List<JsonCharacteristicElement> chList = underTest.getCharacteristicsList();
		
		try {
			chList.get(3).getList();
			
			Assert.assertTrue(false, "\"getList\" methods must fail!");
		} catch ( JSONSException e ) {
			
			Assert.assertTrue(true);
		}
		
		try {
			chList.get(2).getChoice();
			
			Assert.assertTrue(false, "\"getChoice\" methods must fail!");
		} catch ( JSONSException e ) {
			
			Assert.assertTrue(true);
		}
		
		try {
			chList.get(1).getText();
			
			Assert.assertTrue(false, "\"getText\" must fail!");
		} catch ( JSONSException e ) {
			
			Assert.assertTrue(true);
		}
		
		try {
			chList.get(0).getUnit();
			
			Assert.assertTrue(false, "\"getUnit\" must fail!");
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
		Assert.assertEquals(underTest.getName(), "ProductTypeA");
		Assert.assertEquals(underTest.getDescription(), "ProductTypeA_Description");
		Assert.assertNotNull(underTest.getCurrentElement().getErrorActions());
		
		List<JsonCharacteristicElement> chList = underTest.getCharacteristicsList();
		
		Assert.assertEquals( chList.size(), 8, "Characteristics List size does not match!");
		
		for ( JsonCharacteristicElement chElement : chList ) {
			
			JsonErrorActions errorActions = chElement.getErrorActions();
			
			Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS), ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD);
			Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.GENERAL_ERROR), ElementErrorActionType.RETURN_ERROR);
			
			Integer index = chList.indexOf(chElement); 
			CharacteristicType type = chElement.getType(); 
					
			// list
			if ( type.equals(CharacteristicType.List) ) {
				if ( index != 0 && index != 4 )
					Assert.fail("Unexpected position for \"List\" ("+index+")!");
				else
					if ( index == 0 ) {
						Assert.assertEquals( chElement.getName(), "ListA");
						Assert.assertEquals( chElement.getList().getValue(), Arrays.asList("one", "two", "three"));
						Assert.assertEquals( chElement.getList().getDefault(), Arrays.asList( "two", "three"));
					}
					else { // index == 4
						Assert.assertEquals( chElement.getName(), "ListWithoutDefault");
						Assert.assertEquals( chElement.getList().getValue(), Arrays.asList("e", "and", "und"));
						Assert.assertEquals( chElement.getList().getDefault(), null);						
					}
			}
			else if ( type.equals(CharacteristicType.Choice) ) {
				if ( index != 1 && index != 5 )
					Assert.fail("Unexpected position for \"Choice\" ("+index+")!");
				else
					if ( index == 1 ) {
						Assert.assertEquals( chElement.getName(), "ChoiceA");
						Assert.assertEquals( chElement.getChoice().getValue(), Arrays.asList("bim", "bum", "bam"));
						Assert.assertEquals( chElement.getChoice().getDefault(), "bam");
					}
					else { // index == 5
						Assert.assertEquals( chElement.getName(), "ChoiceWithoutDefault");
						Assert.assertEquals( chElement.getChoice().getValue(), Arrays.asList("qui", "quo", "qua"));
						Assert.assertEquals( chElement.getChoice().getDefault(), null);						
					}
			}
			else if ( type.equals(CharacteristicType.Text) ) {
				if ( index != 2 && index != 6 )
					Assert.fail("Unexpected position for \"Text\" ("+index+")!");
				else
					if ( index == 2 ) {
						Assert.assertEquals( chElement.getName(), "TextA");
						Assert.assertEquals( chElement.getText(), "Super Text");
					}
					else { // index == 6
						Assert.assertEquals( chElement.getName(), "TextWithoutDefault");
						Assert.assertEquals( chElement.getText(), null);						
					}
			}
			else if ( type.equals(CharacteristicType.Unit) ) {
				
				if ( index != 3 && index != 7 )
					Assert.fail("Unexpected position for \"Unit\" ("+index+")!");
				else
					if ( index == 3 ) {
						Assert.assertEquals( chElement.getName(), "UnitA");
						
						Assert.assertEquals( chElement.getUnit().getUnit(), "Peculiarity");
						Assert.assertEquals( chElement.getUnit().getValue(), "Important");
					}
					else { // index == 7
						Assert.assertEquals( chElement.getName(), "UnitWithoutValue");
						
						Assert.assertEquals( chElement.getUnit().getUnit(), "PeculiarityWithoutDefault");
						Assert.assertEquals( chElement.getUnit().getValue(), null);						
					}
			}
		}
	}
}
