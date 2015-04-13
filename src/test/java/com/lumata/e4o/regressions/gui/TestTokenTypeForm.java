package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Browser;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.dao.tenant.DAOTokenType;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.schema.tenant.TokenType;

public class TestTokenTypeForm {

	private static final Logger logger = LoggerFactory.getLogger( TestTokenTypeForm.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private final boolean testEnabled = true;
	
	private SeleniumWebDriver seleniumWebDriver;
	private NetworkEnvironment env;
	private Mysql mysql;
	
	private TokenTypeForm tokenTypeForm;
			
	/* 	Initialize Environment */
	@Parameters({"e4oEnv", "seleniumDriver"})
	@BeforeClass
	public void init( @Optional() String e4oEnv, @Optional() String seleniumDriver ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		JSONObject jsonE4OEnv = new JSONObject( e4oEnv );
		
		JSONObject jsonSeleniumDriver = new JSONObject( seleniumDriver );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", jsonE4OEnv.getString( "environment" ), IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create mysql connection */
		mysql = new Mysql( env.getDataSource( jsonE4OEnv.getString( "schemaMaster" ) ) );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( jsonE4OEnv.getString( "guiServer" ) );
		
		Assert.assertNotNull( seleniumDriver );
		
		seleniumWebDriver = SeleniumWebDriver.getInstance( jsonSeleniumDriver ).openBrowser( gui.getLink() );
			
		seleniumWebDriver.setTestName( "init" );
		
		/** Login */
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( jsonE4OEnv.getString( "user" ) ) ).navigate() );
		
		/** Token Type Form **/
		tokenTypeForm = new TokenTypeForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test( enabled=testEnabled, priority = 1 )
	public void checkMandatoryFields1() throws FormException, JSONException, JSONSException {
		
		final String TOKEN_TYPE_NAME = Format.addTimestamp( "TType_" );
		
		/** save form with all fields empty **/
		Assert.assertTrue( tokenTypeForm.
							openForm().
							addBtn().
							saveBtn().
							formIsNotValid() 
		);
				
		/** save form only with name, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							setName( TOKEN_TYPE_NAME ).
							saveBtn().
							formIsNotValid() 
		);
		
		/** save form with name and format, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							setFormat( TokenTypeForm.TokenFormat.br.value() ).
							saveBtn().
							formIsNotValid() 
		);
		
		/** save form with name, format and validity, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							setValidityType( TokenTypeForm.TokenValidityType.Relative.value() ).
							setValidityValue( "" ).
							setValidityUnit( TokenTypeForm.TokenValidityUnit.days.value() ).
							saveBtn().
							formIsNotValid() 
		);
		
		/** save form with name, format and validity and wrong redraw number, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							setValidityValue( "10" ).
							setUnlimitedRedraw( false ).
							setNumberOfRedraw( "" ).
							saveBtn().
							formIsNotValid() 
		);
		
		/** save valid form and check if the token type has been stored **/
		Assert.assertTrue( tokenTypeForm.
				setName( TOKEN_TYPE_NAME ).
				setDescription( TOKEN_TYPE_NAME + " Description" ).
				setImgUrl( "test.jpg" ).
				setFormat( TokenTypeForm.TokenFormat.br.value() ).
				setValidityType( TokenTypeForm.TokenValidityType.Relative.value() ).
				setValidityValue( "20" ).
				setValidityUnit( TokenTypeForm.TokenValidityUnit.days.value() ).
				setUnlimitedRedraw( false ).
				setNumberOfRedraw( "10" ).
				saveBtn().
				isTokenTypeInList( TOKEN_TYPE_NAME )				 
		);

		/** open token type in editing mode **/
		tokenTypeForm.editByName( TOKEN_TYPE_NAME );

		/** get stored token type **/
		TokenType tokenType = DAOTokenType.getInstance( mysql ).getTokenTypeByName( TOKEN_TYPE_NAME );
	
		Assert.assertEquals( tokenTypeForm.getName(), tokenType.getTokenTypeName() );
		Assert.assertEquals( tokenTypeForm.getDescription(), tokenType.getDescription() );
		Assert.assertEquals( tokenTypeForm.getImgUrl(), tokenType.getImageUrl() );
		Assert.assertEquals( tokenTypeForm.getFormat(), tokenType.getTokenFormat() );
		Assert.assertEquals( tokenTypeForm.getValidityType(), ( null == tokenType.getExpirationDate() ? TokenTypeForm.TokenValidityType.Relative.name() : TokenTypeForm.TokenValidityType.Absolute.name()  ));
		Assert.assertEquals( Integer.valueOf( tokenTypeForm.getValidityValue() ), ( null == tokenType.getExpirationDate() ? tokenType.getExpirationDuration() : tokenType.getExpirationDate() ) );
		Assert.assertEquals( tokenTypeForm.getValidityUnit(), tokenType.getExpirationDurationUnit() );
		Assert.assertEquals( tokenTypeForm.getUnlimitedRedraw(), Boolean.valueOf( ( tokenType.getSingleUseRedeemDurationTimeout() == 0 ? true : false ) ) );
		Assert.assertEquals( Byte.valueOf( tokenTypeForm.getNumberOfRedraw() ), ( tokenType.getSingleUseRedeemDurationTimeout() == 0 ? tokenType.getQtyMaxRedeems() : Byte.valueOf( "-1" ) ) );
	
		/** go to home form **/
		tokenTypeForm.cancelBtn().goToHome();
	
	}
	
	@Test( enabled=testEnabled, priority = 2 )
	public void checkMandatoryFields2() throws FormException, JSONException, JSONSException {
		
		final String TOKEN_TYPE_NAME = Format.addTimestamp( "TType_" );
			
		/** save form with all fields empty **/
		Assert.assertTrue( tokenTypeForm.
							openForm().
							addBtn().
							saveBtn().
							formIsNotValid() 
		);
		
		tokenTypeForm.cancelBtn();
		
		/** save form only with name, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							addBtn().			
							setName( TOKEN_TYPE_NAME ).
							saveBtn().
							formIsNotValid() 
		);
		
		tokenTypeForm.cancelBtn();
		
		/** save form with name and format, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							addBtn().
							setName( TOKEN_TYPE_NAME ).
							setFormat( TokenTypeForm.TokenFormat.br.value() ).
							saveBtn().
							formIsNotValid() 
		);
		
		tokenTypeForm.cancelBtn();
		
		/** save form with name, format and validity, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							addBtn().
							setName( TOKEN_TYPE_NAME ).
							setFormat( TokenTypeForm.TokenFormat.br.value() ).
							setValidityType( TokenTypeForm.TokenValidityType.Relative.value() ).
							setValidityValue( "" ).
							setValidityUnit( TokenTypeForm.TokenValidityUnit.days.value() ).
							saveBtn().
							formIsNotValid() 
		);
		
		tokenTypeForm.cancelBtn();
		
		/** save form with name, format and validity and wrong redraw number, the others fields are not valid **/
		Assert.assertTrue( tokenTypeForm.
							addBtn().
							setName( TOKEN_TYPE_NAME ).
							setFormat( TokenTypeForm.TokenFormat.br.value() ).
							setValidityType( TokenTypeForm.TokenValidityType.Relative.value() ).
							setValidityValue( "" ).
							setValidityUnit( TokenTypeForm.TokenValidityUnit.days.value() ).
							setValidityValue( "10" ).
							setUnlimitedRedraw( false ).
							setNumberOfRedraw( "" ).
							saveBtn().
							formIsNotValid() 
		);
		
		tokenTypeForm.cancelBtn();
		
		/** save valid form and check if the token type has been stored **/
		Assert.assertTrue( tokenTypeForm.
				addBtn().
				setName( TOKEN_TYPE_NAME ).
				setDescription( TOKEN_TYPE_NAME + " Description" ).
				setImgUrl( "test.jpg" ).
				setFormat( TokenTypeForm.TokenFormat.br.value() ).
				setValidityType( TokenTypeForm.TokenValidityType.Relative.value() ).
				setValidityValue( "20" ).
				setValidityUnit( TokenTypeForm.TokenValidityUnit.days.value() ).
				setUnlimitedRedraw( false ).
				setNumberOfRedraw( "10" ).
				saveBtn().
				isTokenTypeInList( TOKEN_TYPE_NAME )				 
		);
		
		/** open token type in editing mode **/
		tokenTypeForm.editByName( TOKEN_TYPE_NAME );

		/** get stored token type **/
		TokenType tokenType = DAOTokenType.getInstance( mysql ).getTokenTypeByName( TOKEN_TYPE_NAME );
	
		Assert.assertEquals( tokenTypeForm.getName(), tokenType.getTokenTypeName() );
		Assert.assertEquals( tokenTypeForm.getDescription(), tokenType.getDescription() );
		Assert.assertEquals( tokenTypeForm.getImgUrl(), tokenType.getImageUrl() );
		Assert.assertEquals( tokenTypeForm.getFormat(), tokenType.getTokenFormat() );
		Assert.assertEquals( tokenTypeForm.getValidityType(), ( null == tokenType.getExpirationDate() ? TokenTypeForm.TokenValidityType.Relative.name() : TokenTypeForm.TokenValidityType.Absolute.name()  ));
		Assert.assertEquals( Integer.valueOf( tokenTypeForm.getValidityValue() ), ( null == tokenType.getExpirationDate() ? tokenType.getExpirationDuration() : tokenType.getExpirationDate() ) );
		Assert.assertEquals( tokenTypeForm.getValidityUnit(), tokenType.getExpirationDurationUnit() );
		Assert.assertEquals( tokenTypeForm.getUnlimitedRedraw(), Boolean.valueOf( ( tokenType.getSingleUseRedeemDurationTimeout() == 0 ? true : false ) ) );
		Assert.assertEquals( Byte.valueOf( tokenTypeForm.getNumberOfRedraw() ), ( tokenType.getSingleUseRedeemDurationTimeout() == 0 ? tokenType.getQtyMaxRedeems() : Byte.valueOf( "-1" ) ) );
	
		/** go to home form **/
		tokenTypeForm.cancelBtn().goToHome();
	
	}
	
	@Test( enabled=testEnabled, priority = 3 )
	public void checkDuplicatedTokenType() throws FormException, JSONException, JSONSException {
			
		String TOKEN_TYPE_NAME = tokenTypeForm.openForm().getTokenTypeNameByIndex( 0 );		
		
		Assert.assertTrue( TOKEN_TYPE_NAME.length() > 0 );
		
		/** save form with duplicated token type **/
		Assert.assertTrue( tokenTypeForm.							
							addBtn().
							setName( TOKEN_TYPE_NAME ).
							setDescription( TOKEN_TYPE_NAME + " Description" ).
							setImgUrl( "test.jpg" ).
							setFormat( TokenTypeForm.TokenFormat.br.value() ).
							setValidityType( TokenTypeForm.TokenValidityType.Relative.value() ).
							setValidityValue( "20" ).
							setValidityUnit( TokenTypeForm.TokenValidityUnit.days.value() ).
							setUnlimitedRedraw( false ).
							setNumberOfRedraw( "10" ).
							saveBtn().
							isTokenTypeDuplicated()							
		);
				
		tokenTypeForm.cancelBtn().goToHome();
	
	}
	
	@AfterClass
	public void end() throws FormException {
		
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).quit().navigate() );
		
	}
		
}
