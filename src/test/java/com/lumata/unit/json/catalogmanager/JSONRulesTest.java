package com.lumata.unit.json.catalogmanager;

import java.text.ParseException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
//import com.lumata.e4o.json.gui.catalogmanager.JSONRules.JSONChannel;

public class JSONRulesTest {
	
	private JSONRules underTest = null;
	/*
	@Parameters({"rulesFile"})
	@BeforeTest
	public void init(@Optional("rulesetTemplate") String rulesFile) throws JSONSException {

		String resourcePath = "input/catalogmanager/rules";
		String resourceFile = rulesFile;

		underTest = new JSONRules(resourcePath, resourceFile);
	}
	
	@Test
	public void readEntire() throws JSONSException, ParseException {
		
		Assert.assertEquals( underTest.getList().size(), 5);
		
		underTest.setCurrentElementById(0);
		
		Assert.assertEquals( underTest.getName(), "RuleA");
		Assert.assertEquals( underTest.getDescription(), "RuleA");
		Assert.assertEquals( underTest.getTokenType(), "TokenA");
		
		Assert.assertEquals( underTest.getOptimizationAlgorithm(), "Random Assigment");
		Assert.assertEquals( underTest.getKeepOffersConsistentAcrossMultipleRedraws(), Boolean.TRUE);
		Assert.assertEquals( underTest.getIncludePreviouslyAcceptedOffers(), Boolean.TRUE);
		Assert.assertEquals( underTest.getMaximumNumberOfOffers(), new Integer(10));
		
		List<JSONChannel> channels = underTest.getChannels();
		
		Assert.assertNotNull(channels);
		Assert.assertEquals( channels.size(), 1);
		
		JSONChannel ch = channels.get(0);
		
		Assert.assertEquals( ch.getName(), "Ch A");
		Assert.assertEquals( ch.isMandatory(), Boolean.FALSE);
		Assert.assertEquals( ch.isUnlimited(), Boolean.TRUE);
		Assert.assertEquals( ch.getMaxOffers(), new Integer(0));
		
		underTest.setCurrentElementById(4);
		
		Assert.assertEquals( underTest.getName(), "RuleWithMaxOffer");
		Assert.assertEquals( underTest.getDescription(), "RuleWithMaxOffer");
		Assert.assertEquals( underTest.getTokenType(), "TokenC");
		
		Assert.assertEquals( underTest.getOptimizationAlgorithm(), "Random Assigment");
		Assert.assertEquals( underTest.getKeepOffersConsistentAcrossMultipleRedraws(), Boolean.TRUE);
		Assert.assertEquals( underTest.getIncludePreviouslyAcceptedOffers(), Boolean.TRUE);
		Assert.assertEquals( underTest.getMaximumNumberOfOffers(), new Integer(10));
		
		channels = underTest.getChannels();
		
		Assert.assertNotNull(channels);
		Assert.assertEquals( channels.size(), 3);
		
		ch = channels.get(0);
		
		Assert.assertEquals( ch.getName(), "Ch A");
		Assert.assertEquals( ch.isMandatory(), Boolean.FALSE);
		Assert.assertEquals( ch.isUnlimited(), Boolean.TRUE);
		Assert.assertEquals( ch.getMaxOffers(), new Integer(0));		
		
		ch = channels.get(1);
		
		Assert.assertEquals( ch.getName(), "Ch B");
		Assert.assertEquals( ch.isMandatory(), Boolean.FALSE);
		Assert.assertEquals( ch.isUnlimited(), Boolean.FALSE);
		Assert.assertEquals( ch.getMaxOffers(), new Integer(3));
		
		ch = channels.get(2);
		
		Assert.assertEquals( ch.getName(), "Ch C");
		Assert.assertEquals( ch.isMandatory(), Boolean.TRUE);
		Assert.assertEquals( ch.isUnlimited(), Boolean.FALSE);
		Assert.assertEquals( ch.getMaxOffers(), new Integer(2));
	}	
	*/
}
