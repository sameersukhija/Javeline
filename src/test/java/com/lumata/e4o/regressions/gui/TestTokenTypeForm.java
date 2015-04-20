package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOTokenType;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.schema.tenant.TokenType;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;


@TCSeleniumWebDriver
@TCMysqlMaster
public class TestTokenTypeForm extends ParentTestCase {

	private TokenTypeForm tokenTypeForm;
	
	@BeforeMethod
	public void initTokenTypeForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		/** Token Type Form **/
		tokenTypeForm = new TokenTypeForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Test( enabled=TEST_ENABLED, priority = 1 )
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
		TokenType tokenType = DAOTokenType.getInstance( mysqlMaster ).getTokenTypeByName( TOKEN_TYPE_NAME );
	
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
	
	@Test( enabled=TEST_ENABLED, priority = 2 )
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
		TokenType tokenType = DAOTokenType.getInstance( mysqlMaster ).getTokenTypeByName( TOKEN_TYPE_NAME );
	
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
	
	@Test( enabled=TEST_ENABLED, priority = 3 )
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

}
