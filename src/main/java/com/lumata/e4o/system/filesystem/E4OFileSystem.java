package com.lumata.e4o.system.filesystem;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.lumata.common.testing.network.SFTPClient;

public class E4OFileSystem {

	public enum SystemFolder {
		CONF_FOLDER("./conf/"),
		CONF_PRODUCT_FOLDER("./conf/product/"),
		CONF_CUSTOMER_FOLDER("./conf/customer/"),
		DATA_FOLDER("./data/<tenant>/"),
		DATA_LIFECYCLE_FOLDER("./data/<tenant>/lifecycle/"),
		DATA_RULEENGINE_FOLDER("./data/<tenant>/ruleengine/"),	
		DATA_MICROSCOPE_FOLDER("./data/<tenant>/microscope/"),
		DATA_REPORTS_FOLDER("./data/<tenant>/reports/"),
		DATA_DIALOG_FOLDER("./data/<tenant>/dialog/"),
		DATA_IN_FOLDER("./data/<tenant>/in/"),
		DATA_CORE_FOLDER("./data/<tenant>/core/"),
		DATA_CORE_SUBPROFILES_FOLDER("./data/<tenant>/core/subprofiles/"),
		DATA_CORE_BASEMANAGEMENT_FOLDER("./data/<tenant>/core/basemanagement/"),
		DATA_PCM_FOLDER("./data/<tenant>/pcm/"),
		DATA_CCM_FOLDER("./data/<tenant>/ccm/"),
		DATA_CCM_PREDICTION_FOLDER("./data/<tenant>/ccm/predictionData/"),
		DATA_CCM_LEARN_FOLDER("./data/<tenant>/ccm/learn/"),
		DATA_CCM_MODEL_FOLDER("./data/<tenant>/ccm/model/"),
		DATA_CLM_FOLDER("./data/<tenant>/clm/"),
		IN_ACCESS_CDR_FOLDER("./data/in/"),
		DATA_CLM_REPORT_FOLDER("./data/<tenant>/clm/report/"),
		DATA_CLM_GUI_FOLDER("./data/<tenant>/clm/gui/"),
		DATA_CLM_GUI_CAMPAIGN_FOLDER("./data/<tenant>/clm/campaigns/"),
		LOYALTY_FOLDER("./data/<tenant>/clm/gui/loyalty/"),
		DATA_CLM_ARTIFICIAL_EVENTS_FOLDER("./data/<tenant>/clm/artificial_events/"),
		DATA_NOTIF_FOLDER("./data/<tenant>/notif/"),
		DATA_NOTIF_GLOBAL_FOLDER("./data/notif/"),
		DATA_NOTIF_OUTBOUND_OUT_FOLDER("./data/notif/outbound_out/"),
		DATA_NOTIF_OUTBOUND_RESULT_FOLDER("./data/notif/outbound_result/"),
		DATA_NOTIF_OUTBOUND_TEMP_FOLDER("./data/notif/outbound_temp/"),
		DATA_NOTIF_VOICEXML_IN_FOLDER("./data/notif/voicexml_in/"),
		DATA_NOTIF_VOICEXML_OUT_FOLDER("./data/notif/voicexml_out/"),
		DATA_NOTIF_VOICEXML_TEMP_FOLDER("./data/notif/voicexml_temp/"),
		DATA_NOTIF_VOICEXML_RESULT_FOLDER("./data/notif/voicexml_result/"),
		DATA_NOTIF_MAIL_FOLDER("./data/notif/mail/"),
		DATA_NOTIF_MAIL_TMP_UPLOADED_FOLDER("./data/notif/tmp_uploaded/"),
		DATA_NOTIF_MAIL_TMP_VALIDATED_FOLDER("./data/notif/tmp_validated/"),
		DATA_NOTIF_OUTBOUND_FOLDER("./data/notif/outbound/"),
		DATA_NOTIF_OUTBOUND_TMP_UPLOADED_FOLDER("./data/notif/outbound/tmp_uploaded/"),
		DATA_NOTIF_OUTBOUND_TMP_VALIDATED_FOLDER("./data/notif/outbound/tmp_validated/"),
		TMP_FOLDER("./tmp/<tenant>/"),
		TMP_SUBSCRIBERS_TO_DELETE("./tmp/<tenant>/subscribersToDelete/"),
		SCRIPTS_FOLDER("./scripts/"),
		SCRIPTS_NMS_FOLDER("./scripts/nms/"),
		REPORT_FOLDER("./report/<tenant>/"),
		CATALOG_ALERT_NOTIFICATION_FOLDER("./data/<tenant>/notif/catalog_alert/");
		
		String value;
		
		SystemFolder( String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
		
	}
	
	public static Map<String, String> getFormattedValues( String tenant ) {
		
		Map<String, String> values = new LinkedHashMap<String, String>();
		
		for( SystemFolder folder : SystemFolder.values()) {
			values.put( folder.name(), folder.getValue().replaceAll("^[./](.+)", "/usr/local/actrule$1").replace( "<tenant>", ( null != tenant ? tenant : "" ) ) );
		}
		
		return values;
	
	}
	
	public static void main(String [] args) {
		
		Map<String, String> values = E4OFileSystem.getFormattedValues( "tenant1" );
		
    	String user = "root";
    	String host = "10.120.38.36";
    	int port = 22;
    	
        SFTPClient sftp = new SFTPClient( host, port, user, "cmdISnJjdQ==" );
       
		for( Map.Entry<String, String> value : values.entrySet() ) {
		    try {
		    	Assert.assertTrue( sftp.isDir( value.getValue() ) );
		    } catch( AssertionError ae ) {
		    	System.out.println( "NOT FOUND: " + value.getKey() + ": " + value.getValue() );
			}
		}
		
	}
		
}