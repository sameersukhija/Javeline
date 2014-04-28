package com.lumata.e4o.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.system.Security;
import com.lumata.e4o.gui.json.campaign.JSONAction;
import com.lumata.e4o.gui.json.campaign.JSONActionTime;
import com.lumata.e4o.gui.json.campaign.JSONCampaignModel;
import com.lumata.e4o.gui.json.campaign.JSONEvent;
import com.lumata.e4o.gui.json.campaign.JSONNotification;

public class general_test {

	@Test( enabled = true )
	public void test() throws JSONSException, IOFileException, JSONException {
		
		/*
		JSONCampaignModel jcm = new JSONCampaignModel( "input/campaigns", "campaign_model_all_events" );
		jcm.setCampaignModelById( 0 );
		
		JSONEvent event = jcm.getEventByIndex( 0 );
		
		/*
		JSONAction action = event.getActionByIndex( 0 );
		
		JSONActionTime actionTime = action.getActionTime();
		
		System.out.println( action.hasActionTime() );
		*/
		
		//JSONNotification notif = event.getNotificationByIndex( 0 );
		/*
		System.out.println( event.hasNotification() );
		
		JSONNotification notif = event.getNotificationByIndex( 0 );
		
		System.out.println( notif.getText() );
		*/
		
		//String xml = IOFileUtils.loadResourceAsString( "input/testplan", "testplan.xml" );
		
		//System.out.println( xml );
		
		
		
		//JSONObject xmlJSONObj = JSONUtils.loadJSONResourceFromXML( "input/testplan", "testplan.xml" );
        
		//IOFileUtils.saveResource( xmlJSONObj.toString( 2 ), "output/testplan", "testplan.json");
		
		
		
		
		//String jsonPrettyPrintString = xmlJSONObj.toString( 2 );
        
		
		
		//System.out.println(jsonPrettyPrintString);	
		
		
		
		//System.out.println( Security.getMD5( "super2010Man" ) );
      		
	}
	
	
	
}
