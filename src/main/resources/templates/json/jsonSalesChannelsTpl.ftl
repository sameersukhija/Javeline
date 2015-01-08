{
	"salesChannels": [
		<#list salesChannelsList as salesChannel>
			<#if salesChannel_index gt 0 >,</#if>{
				"enabled": true,
				"id": ${salesChannel.channelId},
				"name":"${salesChannel.channelName}",
				"active":${salesChannel.active},
				"errorActions": {
					"ELEMENT_ALREADY_EXISTS": "ABORT"	
				}
			}
		</#list>	
	]
}