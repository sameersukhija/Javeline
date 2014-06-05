package com.lumata.e4o.gui.xmlrpc.type;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;

import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.system.Server;

public enum XMLRPCRequest {

	badgemanager_getBadgeList,
	badgemanager_getSubscriberBadgeInformation,
	bonusmanager_activateBonus,
	bonusmanager_creditBonus,
	bonusmanager_deactivateBonus,
	bonusmanager_debitBonus,
	bonusmanager_deleteBonus,
	bonusmanager_getSubscribersBonusDetails,
	bonusmanager_setBonus,
	bonusmanager_transferBonus,
	campaignmanager_getAccessibleSubscribersCampaignDetails,
	campaignmanager_getCampaigns,
	campaignmanager_getSubscribersCampaign,
	campaignmanager_getSubscribersCampaignDetails,
	campaignmanager_provisionCampaign,
	catalogmanager_getOffers,
	catalogmanager_getProducts,
	catalogmanager_getProductTypes,
	catalogmanager_purchase,
	eventmanager_generateCustomEvent,
	hierarchymanager_createRelation,
	hierarchymanager_deleteRelation,
	hierarchymanager_getRelatedSubscribers,
	hierarchymanager_getSponsor,
	hierarchymanager_updateRelation,
	historybonusmanager_getHistoryBonus,
	historyusagemanager_getHistoryUsage,
	loyaltymanager_getUserLoyaltyProgramClass,
	loyaltymanager_setUserLoyaltyProgramClass,
	offeroptimizer_accept,
	offeroptimizer_allocate {
		
		public Object call() {
			
			return new Object();
			
		}
		
	},
	offeroptimizer_getOffersList,
	offeroptimizer_getTokensList,
	offeroptimizer_refuseAll,
	offeroptimizer_resendAllActiveTokens,
	predictionmanager_getPredictionScore,
	predictionmanager_getPredictionScoreTrend,
	subscribermanager_addSubscriberChannel,
	subscribermanager_createSubscriber,
	subscribermanager_deleteSubscriber,
	subscribermanager_deleteSubscriberChannel,
	subscribermanager_getServiceInfo,
	subscribermanager_getSubscriber,
	subscribermanager_getSubscriberChannels,
	subscribermanager_subscribeService,
	subscribermanager_unsubscribeService,
	subscribermanager_updateServiceInfo,
	subscribermanager_updateSubscriber,
	subscribermanager_updateSubscriberChannel,
	subscribermanager_updateUserMsisdn,
	system_listMethods,
	user_create,
	user_delete,
	user_update;
	
	public ClientResponse<String> call( Server server, XMLRPCComponent xmlrpcBody ) throws Exception {
		
		return call( server, xmlrpcBody, null );
		
	};
	
	public ClientResponse<String> call( Server server, XMLRPCComponent xmlrpcBody, XMLRPCComponent validator ) throws Exception {
		
		String url = server.getLink() + "xmlrpc/";
		
		RestClient restClient = new RestClient( url );
		
		restClient.body( RestClient.ContentTypes.APPLICATION_JSON.getValue(), body( xmlrpcBody ) );
		
		ClientResponse<String> response = restClient.post();
				
		return response;
		
	};
	
	private String body( XMLRPCComponent xmlrpcBody ) {
		
		return "<?xml version=\"1.0\"?><methodCall><methodName>" + this.name().replace( "_", "." ) + "</methodName><params>" + ( null != xmlrpcBody.component ? xmlrpcBody.component.toString() : "" ) + "</params></methodCall>";
		
	}
	
	
}
