package com.lumata.unit.webservices.xmlrpc;

import org.json.JSONObject;
import org.json.XML;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;

public class XMLRPCResponseTest {
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		
		String s = "<?xmlversion=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><array><data><value><productType xmlns:prod=\"com/act750/xmlrpc/productType\"><name>Phone</name><description/><characteristics><characteristicItem><name>Manufacturer</name><availableValue>Nokia</availableValue><availableValue>Sagem</availableValue><availableValue>Sony</availableValue><availableValue>Apple</availableValue><unit xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/></characteristicItem><characteristicItem><name>Memory</name><availableValue>3</availableValue><availableValue>5</availableValue><availableValue>8</availableValue><unit>Gb</unit></characteristicItem></characteristics><products><productItem><name>Sony352</name></productItem><productItem><name>NokiaN8</name></productItem><productItem><name>NokiaK12</name></productItem></products></productType></value><value><productType xmlns:prod=\"com/act750/xmlrpc/productType\"><name>Package</name><description/><characteristics><characteristicItem><name>Duration</name><defaultValue>1</defaultValue><availableValue>2</availableValue><availableValue>3</availableValue><unit>hour(s)</unit></characteristicItem><characteristicItem><name>SMS</name><availableValue>100</availableValue><availableValue>illimity</availableValue><unit xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/></characteristicItem></characteristics><products><productItem><name>Package 2h + 100SMS</name></productItem></products></productType></value><value><productType xmlns:prod=\"com/act750/xmlrpc/productType\"><name>Insurance</name><description/></productType></value></data></array></value></param></params></methodResponse>";
		
		JSONObject jsonXML = XML.toJSONObject( s );
		
		System.out.println( jsonXML );
		
		
		XMLRPCResponseProductTypes token = new XMLRPCResponseProductTypes( jsonXML );
		
		System.out.println( token.getMsisdn() );
		
		
	}
	
}