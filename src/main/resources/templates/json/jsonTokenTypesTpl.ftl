{
	"tokenTypes": [
		<#list tokenTypesList as tokenType >
			<#if tokenType_index gt 0 >,</#if>{
				"enabled": true,
				"id": ${tokenType.tokenTypeId},
				"name": "${tokenType.tokenTypeName}",
				"description":"${tokenType.description}",
				"imageUrl":"${tokenType.imageUrl}",
				"format":"${tokenType.tokenFormat}",
				"validityType":<#if tokenType.expirationDurationUnit == "fixed" >"Absolute"<#else>"Relative"</#if>,
				"validityUnit":"${tokenType.expirationDurationUnit}",
				"validityValue":<#if tokenType.expirationDurationUnit == "fixed" >"${tokenType.expirationDate?iso_utc}"<#else>"${tokenType.expirationDuration}"</#if>,
				"usageUnlimited": <#if tokenType.qtyMaxRedeems == -1 >true<#else>false</#if>,
				"usage":"${tokenType.qtyMaxRedeems}",
				"errorActions": {
					"ELEMENT_ALREADY_EXISTS": "ABORT"	
				},
				"options": {
					"TOKEN_TYPE_AUTOINCREMENT": true	
				}
			}
		</#list>
	]
}