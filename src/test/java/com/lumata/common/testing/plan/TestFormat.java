package com.lumata.common.testing.plan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.validating.Format.Operators;

public class TestFormat {
	
	/*private static final  Logger logger = LoggerFactory.getLogger( TestFormat.class );*/
	    
	@Test()
	public void isLenght_1() {		
		String test = "test";
		Assert.assertEquals( Format.isLength( test, 3, Operators.MORE_THAN, false ), true );
	}
	
	@Test()
	public void isLenght_2() {		
		String test = "test";
		Assert.assertEquals( Format.isLength( test, 4, Operators.MORE_OR_EQUAL_THAN, false ), true );
	}
	
	@Test()
	public void isLenght_3() {		
		String test = "test";
		Assert.assertEquals( Format.isLength( test, 5, Operators.LESS_THAN, false ), true );
	}
	
	@Test()
	public void isLenght_4() {		
		String test = "test";
		Assert.assertEquals( Format.isLength( test, 4, Operators.LESS_OR_EQUAL_THAN, false ), true );
	}
	
	@Test()
	public void isLenght_5() {		
		String test = "test";
		Assert.assertEquals( Format.isLength( test, 4, Operators.EQUAL, false ), true );
	}
	
	/** Allow blank false */
	@Test()
	public void isLenght_6() {		
		String test = "";
		Assert.assertEquals( Format.isLength( test, 4, Operators.EQUAL, false ), false );
	}
	
	/** Allow blank true */
	@Test()
	public void isLenght_7() {		
		String test = "";
		Assert.assertEquals( Format.isLength( test, 4, Operators.EQUAL, true ), true );
	}
	
	/** Null */
	@Test( expectedExceptions = NullPointerException.class )
	public void isLenght_8() {		
		String test = null;
		Assert.assertEquals( Format.isLength( test, 4, Operators.EQUAL, true ), true );
	}
	
	@Test()
	public void isDate_1() {		
		String date = "2013/06/24";
		String format = "yyyy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, false ), true );
	}
	
	@Test()
	public void isDate_2() {		
		String date = "2013/06/24";
		String format = "yy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, false ), false );
	}
	
	@Test()
	public void isDate_3() {		
		String date = "2013/06/44";
		String format = "yyyy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, false ), false );
	}
	
	/** Allow blank false */
	@Test()
	public void isDate_4() {		
		String date = "";
		String format = "yyyy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, false ), false );
	}
	
	/** Allow blank false */
	@Test()
	public void isDate_5() {		
		String date = null;
		String format = "yyyy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, false ), false );
	}
	
	/** Allow blank true */
	@Test()
	public void isDate_6() {		
		String date = "";
		String format = "yyyy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, true ), true );
	}
	
	/** Allow blank true */
	@Test()
	public void isDate_7() {		
		String date = null;
		String format = "yyyy/mm/dd";
		Assert.assertEquals( Format.isDate( date, format, true ), true );
	}
		
	@Test()
	public void isEmail_1() {		
		String value = "arcangelo.dipasquale@lumatagroup.com";
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, false ), true );
	}
	
	@Test()
	public void isEmail_2() {		
		String value = "arcangelo.dipasquale@lumatagroup";
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, false ), false );
	}
	
	@Test()
	public void isEmail_3() {		
		String value = "arcangelo.dipasquale@.com";
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, false ), false );
	}
	
	@Test()
	public void isEmail_4() {		
		String value = "@lumatagroup.com";
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, false ), false );
	}
	
	/** Allow blank false */
	@Test()
	public void isEmail_5() {		
		String value = "";
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, false ), false );
	}
		
	/** Allow blank true */
	@Test()
	public void isEmail_6() {		
		String value = "";
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, true ), true );
	}
	
	/** Null */
	@Test( expectedExceptions = NullPointerException.class )
	public void isEmail_7() {		
		String value = null;
		String format = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
		Assert.assertEquals( Format.isEmail( value, format, false ), false );
	}
	
	@Test()
	public void isMSISDN_1() {		
		String value = "1234567";
		String format = "[0-9]+";
		String filter = "[ ()+-]";
		Assert.assertEquals( Format.isMSISDN( value, format, filter, false ), true );
	}
	
	@Test()
	public void isMSISDN_2() {		
		String value = "+(1)-23 4567";
		String format = "[0-9]+";
		String filter = "[ ()+-]";
		Assert.assertEquals( Format.isMSISDN( value, format, filter, false ), true );
	}
	
	@Test()
	public void isMSISDN_3() {		
		String value = "123/&456é7";
		String format = "[0-9]+";
		String filter = "[ ()+-]";
		Assert.assertEquals( Format.isMSISDN( value, format, filter, false ), false );
	}
	
	@Test()
	public void isMSISDN_4() {		
		String value = "";
		String format = "[0-9]+";
		String filter = "[ ()+-]";
		Assert.assertEquals( Format.isMSISDN( value, format, filter, false ), false );
	}
	
	@Test()
	public void isMSISDN_5() {		
		String value = "";
		String format = "[0-9]+";
		String filter = "[ ()+-]";
		Assert.assertEquals( Format.isMSISDN( value, format, filter, true ), true );
	}
	
	@Test( expectedExceptions = NullPointerException.class )
	public void isMSISDN_6() {		
		String value = null;
		String format = "[0-9]+";
		String filter = "[ ()+-]";
		Assert.assertEquals( Format.isMSISDN( value, format, filter, false ), true );
	}
	
	@Test()
	public void isEnum_1() {		
		String value = "FIREFOX";
		String format = "com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType";
		Assert.assertEquals( Format.isEnum( value, format, false ), true );
	}
	
	@Test()
	public void isEnum_2() {		
		String value = "WRONG_FIREFOX";
		String format = "com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType";
		Assert.assertEquals( Format.isEnum( value, format, false ), false );
	}
	
	/** Allow blank true */
	@Test()
	public void isEnum_3() {		
		String value = "";
		String format = "com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType";
		Assert.assertEquals( Format.isEnum( value, format, false ), false );
	}
	
	/** Null */
	@Test( expectedExceptions = NullPointerException.class )
	public void isEnum_4() {		
		String value = null;
		String format = "com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType";
		Assert.assertEquals( Format.isEnum( value, format, false ), false );
	}
	
	public void isNumeric_1() {		
		String value = null;
		String format = "com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType";
		Assert.assertEquals( Format.isEnum( value, format, false ), false );
	}
	
	/*
	@Test()
	public void isMapKey_1() {		
		TestMap tm = new TestMap();
		Assert.assertEquals( Format.isMapKey( "key_1", "TEST_MAP", tm, false ), true );
	}
	
	@Test()
	public void isMapKey_2() {		
		TestMap tm = new TestMap();
		Assert.assertEquals( Format.isMapKey( "wrong_key", "TEST_MAP", tm, false ), false );
	}
	
	/** Allow blank */
/*	@Test()
	public void isMapKey_3() {		
		TestMap tm = new TestMap();
		Assert.assertEquals( Format.isMapKey( "", "TEST_MAP", tm, true ), true );
	}
	
	/** Null */
/*	@Test( expectedExceptions = NullPointerException.class )
	public void isMapKey_4() {		
		TestMap tm = new TestMap();
		Assert.assertEquals( Format.isMapKey( null, "TEST_MAP", tm, true ), false );
	}
	
	/** Wrong Map */
/*	@Test()
	public void isMapKey_5() {		
		TestMap tm = new TestMap();
		Assert.assertEquals( Format.isMapKey( "key_1", "WRONG_MAP", tm, false ), false );
	}
	
	/** Wrong Class */
/*	@Test()
	public void isMapKey_6() {		
		TestMap tm = new TestMap();
		Assert.assertEquals( Format.isMapKey( "key_1", "TEST_MAP", new Object(), false ), false );
	}
	*/
/*	@Test()
	public void isFile_1() {		
		Assert.assertEquals( Format.isFile( "test.txt" ), true );
	}
	
	@Test()
	public void isFile_2() {		
		Assert.assertEquals( Format.isFile( "test." ), false );
	}
	
	@Test()
	public void isFile_3() {		
		Assert.assertEquals( Format.isFile( ".txt" ), false );
	}
	
	@Test()
	public void isFile_4() {		
		Assert.assertEquals( Format.isFile( "." ), false );
	}
	
	/** Not valid characters: ."'?$&* */ 
/*	@Test()
	public void isFile_5() {		
		Assert.assertEquals( Format.isFile( ".\"'?$&*.txt" ), false );
	}
	*/
}
