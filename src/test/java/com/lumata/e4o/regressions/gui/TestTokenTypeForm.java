package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.security.Authorization;

public class TestTokenTypeForm {

	private static final Logger logger = LoggerFactory.getLogger( TestTokenTypeForm.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private final boolean testEnabled = true;
	
	private SeleniumWebDriver seleniumWebDriver;
	private NetworkEnvironment env;
	
	private TokenTypeForm tokenTypeForm;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		seleniumWebDriver.setTestName( "init" ); 
		
		/** Login */
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( user ) ).navigate() );
		
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
							open().
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
		
		//tokenTypeForm.goToHome();
	
	}
	
	@Test( enabled=testEnabled, priority = 2 )
	public void checkMandatoryFields2() throws FormException, JSONException, JSONSException {
		
		final String TOKEN_TYPE_NAME = Format.addTimestamp( "TType_" );
	
		
		/** save form with all fields empty **/
		Assert.assertTrue( tokenTypeForm.
							open().
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
		
		tokenTypeForm.goToHome();
	
	}
	
	@AfterClass
	public void end() throws FormException {
		
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).quit().navigate() );
		
	}
		
}
