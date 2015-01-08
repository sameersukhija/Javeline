{
	"rules": [
		<#list rulesList as rule >
		<#if rule_index gt 0 >,{<#else>{</#if>
			"enabled": true,
			"id": ${rule.rulesetId},
			"name":"${rule.name}",
			"description":"${rule.description}",
			"tokenType":"${(tokenTypesList[rule.tokenTypeId?string].tokenTypeName)!""}",
			"channels":[ 
				<#list rulesetChannelList[rule.rulesetId?string]!"" as rulesetChannel >
				<#if rulesetChannel_index gt 0 >,</#if>{
					"name":"${(salesChannelsList[rulesetChannel.channelId?string].channelName)!""}",
					"mandatory":${rulesetChannel.mandatory?string},
					"unlimited": <#if rulesetChannel.maxOffer == -1 >true<#else>false</#if>,
					"maxOffer" : ${rulesetChannel.maxOffer}
				}
				</#list>					
			],
			"optimizationAlgorithmId": ${rule.algorithmId},
			"optimizationAlgorithm":"${(offoptimAlgorithmList[rule.algorithmId?string].name)!""}",
			"keepOffersConsistentAcrossMultipleRedraws": <#if rule.keepOffersConsistent == 1 >true<#else>false</#if>,
			"includePreviouslyAcceptedOffers": <#if rule.allowRedeemedOffers == 1 >true<#else>false</#if>,
			"unlimitedOffers": false,
			"maximumNumberOfOffers": 100,
			"errorActions" : {
				"ELEMENT_ALREADY_EXISTS" : "ABORT_CANCEL",
				"GENERAL_ERROR" : "RETURN_ERROR" 
			}
		}			
		</#list>
	]
}