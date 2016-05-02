package com.lumata.e4o.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceHolderDateTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * It validates the string as placeholder and checks if data matches with expected
	 * 
	 * @param testedPlaceHolder
	 * @param expDateObject
	 */
	private void checkValidPlaceHolderString(String testedPlaceHolder, Date expDateObject) {

		PlaceHolderDate underTest = PlaceHolderDate.getInstance(testedPlaceHolder); 
		
		Assert.assertTrue(underTest.isPlaceHolderDate(), testedPlaceHolder + " is not recognized as place holder string!");
		
		Assert.assertEquals( sdf.format(underTest.parse().getTime()), sdf.format(expDateObject));
	}
	
	@Test
	public void TestIsPlaceHolderDate() {
	
		Calendar now = Calendar.getInstance();
		
		checkValidPlaceHolderString( "@current", now.getTime());
		
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1year").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1Year").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1YEAR").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+3year").isPlaceHolderDate());
		
		now.add(Calendar.YEAR, 1);
		
		checkValidPlaceHolderString( "@current+1year", now.getTime());
		
		now.add(Calendar.YEAR, -1);
		
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1month").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1Month").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1Month").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current-2month").isPlaceHolderDate());
		
		now.add(Calendar.MONTH, -1);
		
		checkValidPlaceHolderString( "@current-1month", now.getTime());
		
		now.add(Calendar.MONTH, 1);		
		
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1week").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1Week").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1WEEK").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current-7week").isPlaceHolderDate());		
/*		
		now.add(Calendar.WEEK_OF_YEAR, 1);
		
		checkValidPlaceHolderString( "@current+1week", now.getTime());
		
		now.add(Calendar.WEEK_OF_YEAR, -1);	
*/		
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1day").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1Day").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1DAY").isPlaceHolderDate());
		Assert.assertTrue(PlaceHolderDate.getInstance("@current-7day").isPlaceHolderDate());
		
		now.add(Calendar.DAY_OF_YEAR, -1);
		
		checkValidPlaceHolderString( "@current-1Day", now.getTime());
		
		now.add(Calendar.DAY_OF_YEAR, 1);	
		
		now.add(Calendar.YEAR, 1);
		now.add(Calendar.MONTH, 2);
		now.add(Calendar.DAY_OF_YEAR, 5);
		
		checkValidPlaceHolderString( "@current+1year+2month+5day", now.getTime());
		
		now.add(Calendar.YEAR, -1);
		now.add(Calendar.MONTH, -2);
		now.add(Calendar.DAY_OF_YEAR, -5);		
		
		Assert.assertTrue(PlaceHolderDate.getInstance("@2015-02-13+1year+2month+5day").isPlaceHolderDate());
		
		Assert.assertFalse(PlaceHolderDate.getInstance("funnyString").isPlaceHolderDate());
		Assert.assertFalse(PlaceHolderDate.getInstance("2015-02-13").isPlaceHolderDate());
		Assert.assertFalse(PlaceHolderDate.getInstance("@WoW_BaBy").isPlaceHolderDate());
		Assert.assertFalse(PlaceHolderDate.getInstance("@CURRENT").isPlaceHolderDate());
		Assert.assertFalse(PlaceHolderDate.getInstance("@current-7moonmonth").isPlaceHolderDate());
		Assert.assertFalse(PlaceHolderDate.getInstance("@currents").isPlaceHolderDate());
		
		
//		
//		Assert.assertTrue(PlaceHolderDate.getInstance("@current+1year").isPlaceHolderDate());
//		Assert.assertTrue(PlaceHolderDate.getInstance("@current").isPlaceHolderDate());
//		Assert.assertTrue(PlaceHolderDate.getInstance("@current").isPlaceHolderDate());
//		Assert.assertTrue(PlaceHolderDate.getInstance("@current").isPlaceHolderDate());
//		
///		PlaceHolderDate.getInstance( StringDateField ).isPlaceHolderDate()
	}
	
	 
}
