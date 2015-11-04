package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.dao.tenant.DAOTokenType;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenFormat;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;
import com.lumata.e4o.schema.tenant.TokenType;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
@TCMysqlMaster
public class TestTokenTypeForm extends ParentTestCase {

	private TokenTypeForm tokenTypeForm;
	private JSONTokenType jsonTokenType;
	private String TOKEN_TYPE_NAME = null;

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 1)
	@Parameters({ "jsonFilePath_tokenType", "jsonFileName_tokenType"})
	public void testUc23_01CreateNewTokenRelative(@Optional String jsonFilePath_tokenType,
			@Optional String jsonFileName_tokenType) throws FormException,
			JSONException, JSONSException {
		Reporter.log("Creation of \"Token Form\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT
				+ jsonFilePath_tokenType;
		String resourceFile = jsonFileName_tokenType;
		jsonTokenType = new JSONTokenType(resourcePath, resourceFile);
		Reporter.log("\"Token Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		tokenTypeForm = new TokenTypeForm(seleniumWebDriver, jsonTokenType,
				TIMEOUT, ATTEMPT_TIMEOUT);
		JSONArray tokenTypes = jsonTokenType.getList();
		for (int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++) {
			jsonTokenType.setTokenTypeById(tokenTypeIndex);
	if(tokenTypeIndex==0)
	{
	 TOKEN_TYPE_NAME = Format.addTimestamp(jsonTokenType.getName()+"_");

		/** save form with all fields empty **/
		Assert.assertTrue(tokenTypeForm.openForm().addBtn().saveBtn()
				.formIsNotValid());

		/** save form only with name, the others fields are not valid **/
		Assert.assertTrue(tokenTypeForm.setName(TOKEN_TYPE_NAME).saveBtn()
				.formIsNotValid());

		/** save form with name and format, the others fields are not valid **/
		Assert.assertTrue(tokenTypeForm
				.setFormat(jsonTokenType.getFormat()).saveBtn()
				.formIsNotValid());

		/**
		 * save form with name, format and validity, the others fields are not
		 * valid
		 **/
		Assert.assertTrue(tokenTypeForm
				.setValidityType(
						TokenTypeForm.TokenValidityType.Relative.value())
				.setValidityValue("")
				.setValidityUnit(TokenTypeForm.TokenValidityUnit.days.value())
				.saveBtn().formIsNotValid());

		/**
		 * save form with name, format and validity and wrong redraw number, the
		 * others fields are not valid
		 **/
		Assert.assertTrue(tokenTypeForm.setValidityValue("10")
				.setUnlimitedRedraw(false).setNumberOfRedraw("").saveBtn()
				.formIsNotValid());
		//tokenTypeForm.cancelBtn().goToHome();

		/** save valid form and check if the token type has been stored **/
		
			tokenTypeForm
					.setName(TOKEN_TYPE_NAME)
					.setDescription(TOKEN_TYPE_NAME + "description")
					.setImgUrl(jsonTokenType.getImageUrl())
					.setFormat(jsonTokenType.getFormat())
					.setValidityType(
							jsonTokenType.getValidityType())
					.setValidityValue(jsonTokenType.getValidityValue())
					.setValidityUnit(
							jsonTokenType.getValidityUnit())
					.setUnlimitedRedraw(jsonTokenType.getUsageUnlimited()).
					// setNumberOfRedraw(jsonTokenType.getUsage()).
					saveBtn();
			if (tokenTypeForm.isTokenTypeInList(TOKEN_TYPE_NAME)) {
				Assert.assertTrue(true,
						"Token created successfully and exist in list");
				Reporter.log("Token created successfully and exist in list",
						LOG_TO_STD_OUT);
			} else {
				Assert.fail("Token doesn't create successfully");
				Reporter.log("Token doesn't exist in list", LOG_TO_STD_OUT);
			}
	
		/** open token type in editing mode **/
		tokenTypeForm.editByName(TOKEN_TYPE_NAME);

		/** get stored token type **/
		TokenType tokenType = DAOTokenType.getInstance(mysqlMaster)
				.getTokenTypeByName(TOKEN_TYPE_NAME);

		Assert.assertEquals(tokenTypeForm.getName(),
				tokenType.getTokenTypeName());
		Assert.assertEquals(tokenTypeForm.getDescription(),
				tokenType.getDescription());
		Assert.assertEquals(tokenTypeForm.getImgUrl(), tokenType.getImageUrl());
		Assert.assertEquals(tokenTypeForm.getFormat(),
				tokenType.getTokenFormat());
		Assert.assertEquals(
				tokenTypeForm.getValidityType(),
				(null == tokenType.getExpirationDate() ? TokenTypeForm.TokenValidityType.Relative
						.name() : TokenTypeForm.TokenValidityType.Absolute
						.name()));
		Assert.assertEquals(
				Integer.valueOf(tokenTypeForm.getValidityValue()),
				(null == tokenType.getExpirationDate() ? tokenType
						.getExpirationDuration() : tokenType
						.getExpirationDate()));
		Assert.assertEquals(tokenTypeForm.getValidityUnit(),
				tokenType.getExpirationDurationUnit());
		Assert.assertEquals(
				tokenTypeForm.getUnlimitedRedraw(),
				Boolean.valueOf((tokenType.getSingleUseRedeemDurationTimeout() == 0 ? true
						: false)));

		/** go to home form **/
		tokenTypeForm.cancelBtn().goToHome();

	}
		}
		}
	

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 2)
	public void testUc23_01CreateNewTokenAbsolute() throws FormException, JSONException,
			JSONSException,ParseException{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JSONArray tokenTypes = jsonTokenType.getList();	
		for (int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++) {
			if(tokenTypeIndex==1)
			{
					TOKEN_TYPE_NAME = Format.addTimestamp(jsonTokenType.getName()+"_");
	
					/** save form with all fields empty **/
					Assert.assertTrue(tokenTypeForm.openForm().addBtn().saveBtn()
					.formIsNotValid());
	
			tokenTypeForm.cancelBtn();
	
			/** save form only with name, the others fields are not valid **/
			Assert.assertTrue(tokenTypeForm.addBtn().setName(TOKEN_TYPE_NAME)
					.saveBtn().formIsNotValid());
	
			tokenTypeForm.cancelBtn();
	
			/** save form with name and format, the others fields are not valid **/
			Assert.assertTrue(tokenTypeForm.addBtn().setName(TOKEN_TYPE_NAME)
					.setFormat(TokenTypeForm.TokenFormat.br.value()).saveBtn()
					.formIsNotValid());
	
			tokenTypeForm.cancelBtn();
	
			/**
			 * save form with name, format and validity, the others fields are not
			 * valid
			 **/
			Assert.assertTrue(tokenTypeForm
					.addBtn()
					.setName(TOKEN_TYPE_NAME)
					.setFormat(TokenTypeForm.TokenFormat.br.value())
					.setValidityType(
							TokenTypeForm.TokenValidityType.Relative.value())
					.setValidityValue("")
					.setValidityUnit(TokenTypeForm.TokenValidityUnit.days.value())
					.saveBtn().formIsNotValid());
	
			tokenTypeForm.cancelBtn();
	
			/**
			 * save form with name, format and validity and wrong redraw number, the
			 * others fields are not valid
			 **/
			Assert.assertTrue(tokenTypeForm
					.addBtn()
					.setName(TOKEN_TYPE_NAME)
					.setFormat(TokenTypeForm.TokenFormat.br.value())
					.setValidityType(
							TokenTypeForm.TokenValidityType.Relative.value())
					.setValidityValue("")
					.setValidityUnit(TokenTypeForm.TokenValidityUnit.days.value())
					.setValidityValue("10").setUnlimitedRedraw(false)
					.setNumberOfRedraw("").saveBtn().formIsNotValid());
	
			tokenTypeForm.cancelBtn();
	
				tokenTypeForm.addBtn()
						.setName(TOKEN_TYPE_NAME)
						.setDescription(TOKEN_TYPE_NAME + "description")
						.setImgUrl(jsonTokenType.getImageUrl())
						.setFormat(jsonTokenType.getFormat())
						.setValidityType(
								jsonTokenType.getValidityType());
				if(jsonTokenType.getValidityType().trim().equals("Absolute"))
				{
					
					Calendar date=tokenTypeForm.parsePlaceHolderDate(jsonTokenType.getValidityValue());
					tokenTypeForm.setAbsoluteDate( "validity.value", date );
				}
				else
				{
					tokenTypeForm.setValidityValue(jsonTokenType.getValidityValue()).setValidityUnit(
								jsonTokenType.getValidityUnit());
				}
						
				tokenTypeForm.setUnlimitedRedraw(jsonTokenType.getUsageUnlimited()).
						// setNumberOfRedraw(jsonTokenType.getUsage()).
						saveBtn();
				if (tokenTypeForm.isTokenTypeInList(TOKEN_TYPE_NAME)) {
					Assert.assertTrue(true,
							"Token created successfully and exist in list");
					Reporter.log("Token created successfully and exist in list",
							LOG_TO_STD_OUT);
			} else {
				Assert.fail("Token doesn't create successfully");
				Reporter.log("Token doesn't exist in list", LOG_TO_STD_OUT);
			}

		/** save valid form and check if the token type has been stored **/

		/** open token type in editing mode **/
		tokenTypeForm.editByName(TOKEN_TYPE_NAME);

		/** get stored token type **/
		TokenType tokenType = DAOTokenType.getInstance(mysqlMaster)
				.getTokenTypeByName(TOKEN_TYPE_NAME);

		Assert.assertEquals(tokenTypeForm.getName(),
				tokenType.getTokenTypeName());
		Assert.assertEquals(tokenTypeForm.getDescription(),
				tokenType.getDescription());
		Assert.assertEquals(tokenTypeForm.getImgUrl(), tokenType.getImageUrl());
		Assert.assertEquals(tokenTypeForm.getFormat(),
				tokenType.getTokenFormat());
		Assert.assertEquals(
				tokenTypeForm.getValidityType(),
				(null == tokenType.getExpirationDate() ? TokenTypeForm.TokenValidityType.Relative
						.name() : TokenTypeForm.TokenValidityType.Absolute
						.name()));
		Assert.assertEquals(
				tokenTypeForm.getUnlimitedRedraw(),
				Boolean.valueOf((tokenType.getSingleUseRedeemDurationTimeout() == 0 ? true
						: false)));

		/** go to home form **/
		tokenTypeForm.cancelBtn().goToHome();
			}
		}

	}

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 3)
	public void testUc23_04CreateNewTokenInvalid() throws FormException, JSONException,
			JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(TOKEN_TYPE_NAME.length() > 0);

		/** save form with duplicated token type **/
		Assert.assertTrue(tokenTypeForm.openForm()
				.addBtn()
				.setName(TOKEN_TYPE_NAME)
				.setDescription(TOKEN_TYPE_NAME + " Description")
				.setImgUrl("test.jpg")
				.setFormat(TokenTypeForm.TokenFormat.br.value())
				.setValidityType(
						TokenTypeForm.TokenValidityType.Relative.value())
				.setValidityValue("20")
				.setValidityUnit(TokenTypeForm.TokenValidityUnit.days.value())
				.setUnlimitedRedraw(false).setNumberOfRedraw("10").saveBtn()
				.isTokenTypeDuplicated());

		tokenTypeForm.cancelBtn().goToHome();

	}
	

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 4)
	public void testUc23_02EditTokenNameDisabled() throws FormException, JSONException,
			JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(TOKEN_TYPE_NAME.length() > 0);
		
		tokenTypeForm.openForm().editByName(TOKEN_TYPE_NAME);
		if(tokenTypeForm.isTokenNameFieldDisabled())
		{
			Assert.assertFalse(false, "Field name is disabled");
			Reporter.log("TokenNamefield is disabled", LOG_TO_STD_OUT);
		}
		else
		{
			Assert.fail("TokenNameField is not disabled");
			Reporter.log("TokenNameField is not disabled", LOG_TO_STD_OUT);
		}
		tokenTypeForm.cancelBtn().goToHome();

	}
	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 5)
	public void testUc23_03EditToken() throws FormException, JSONException,
			JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(TOKEN_TYPE_NAME.length() > 0);
		
		tokenTypeForm.openForm().editByName(TOKEN_TYPE_NAME);
		tokenTypeForm.setDescription("newDescription");
		tokenTypeForm.setFormat("[ACDEFGHJKMNPQRTWXY34679]{6}");
		tokenTypeForm.setUnlimitedRedraw(false);
		tokenTypeForm.editSaveBtn();
		/** open token type in editing mode **/
		tokenTypeForm.editByName(TOKEN_TYPE_NAME);

		/** get stored token type **/
		TokenType tokenType = DAOTokenType.getInstance(mysqlMaster)
				.getTokenTypeByName(TOKEN_TYPE_NAME);

		Assert.assertEquals(tokenTypeForm.getName(),
				tokenType.getTokenTypeName());
		Assert.assertEquals(tokenTypeForm.getDescription(),
				tokenType.getDescription());
		Assert.assertEquals(tokenTypeForm.getImgUrl(), tokenType.getImageUrl());
		Assert.assertEquals(tokenTypeForm.getFormat(),
				tokenType.getTokenFormat());
		Assert.assertEquals(
				tokenTypeForm.getValidityType(),
				(null == tokenType.getExpirationDate() ? TokenTypeForm.TokenValidityType.Relative
						.name() : TokenTypeForm.TokenValidityType.Absolute
						.name()));
		Assert.assertEquals(
				tokenTypeForm.getUnlimitedRedraw(),
				Boolean.valueOf((tokenType.getSingleUseRedeemDurationTimeout() == 0 ? true
						: false)));

		/** go to home form **/
		tokenTypeForm.cancelBtn().goToHome();

	}
	

}
