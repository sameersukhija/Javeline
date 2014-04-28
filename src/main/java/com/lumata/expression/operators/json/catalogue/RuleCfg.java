package com.lumata.expression.operators.json.catalogue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.RuleException;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class RuleCfg {

	private static final Logger logger = LoggerFactory.getLogger(RuleCfg.class);

	public enum RuleValidity {
		seconds, minutes, hours, days
	}

	private JSONObject rlCfg;

	public RuleCfg(JSONObject rlCfg) {

		this.rlCfg = rlCfg;

	}

	public RuleCfg(String rule, IOLoadingType loadingType) throws RuleException {

		try {

			switch (loadingType) {

			case FILE: {
				this.rlCfg = JSONUtils.loadJSONFile(rule.toLowerCase() + Format.JSON_EXTENSION);
				break;
			}
			case RESOURCE: {
				this.rlCfg = JSONUtils.loadJSONResource(rule.toLowerCase() + Format.JSON_EXTENSION);
				break;
			}
			default:
				throw new RuleException("You cannot load an Campaign from resources different by FILE or RESOURCE");

			}

		} catch (JSONSException e) {

			logger.error(e.getMessage(), e);

			throw new RuleException(e.getMessage(), e);

		} 

	}

	public RuleCfg(String folder, String rule, IOLoadingType loadingType) throws RuleException {

		try {

			switch (loadingType) {

			case FILE: {
				this.rlCfg = JSONUtils.loadJSONFile(folder, rule.toLowerCase() + Format.JSON_EXTENSION);
				break;
			}
			case RESOURCE: {
				this.rlCfg = JSONUtils.loadJSONResource(folder, rule.toLowerCase() + Format.JSON_EXTENSION);
				break;
			}
			default:
				throw new RuleException("You cannot load an Campaign from resources different by FILE or RESOURCE");

			}

		} catch (JSONSException e) {

			logger.error(e.getMessage(), e);

			throw new RuleException(e.getMessage(), e);

		} 

	}

	public static ArrayList<RuleCfg> createRuleList(String folder, String path, IOLoadingType loadingType) throws RuleException {

		ArrayList<RuleCfg> finalList = new ArrayList<RuleCfg>();

		try {

			JSONObject rule_list;

			switch (loadingType) {

			case FILE: {
				rule_list = JSONUtils.loadJSONFile(folder, path.toLowerCase() + Format.JSON_EXTENSION);
				break;
			}
			case RESOURCE: {
				rule_list = JSONUtils.loadJSONResource(folder, path.toLowerCase() + Format.JSON_EXTENSION);
				break;
			}
			default:
				throw new RuleException("You cannot load an Campaign from resources different by FILE or RESOURCE");

			}

			JSONArray ruleList = rule_list.getJSONArray("rule_list");

			for (int i = 0; i < ruleList.length(); i++) {

				finalList.add(new RuleCfg(ruleList.getJSONObject(i)));

			}

		} catch (JSONException e) {

			logger.error(e.getMessage(), e);

			throw new RuleException(e.getMessage(), e);

		} catch (JSONSException e) {

			logger.error(e.getMessage(), e);

			throw new RuleException(e.getMessage(), e);

		} 

		return finalList;

	}

	public String getName() {

		try {

			if (!rlCfg.isNull("name")) {
				return rlCfg.getString("name");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public String getDescription() {

		try {

			if (!rlCfg.isNull("description")) {
				return rlCfg.getString("description");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public String getTokenTypeName() {

		try {

			if (!rlCfg.isNull("tokenTypeName")) {
				return rlCfg.getString("tokenTypeName");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public JSONArray getChannelNameList() {

		try {

			if (!rlCfg.isNull("channelNamesList")) {
				return rlCfg.getJSONArray("channelNamesList");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public String getOfferSelectionAlgorithmId() {

		try {

			if (!rlCfg.isNull("offerSelectionAlgorithmId")) {
				return rlCfg.getString("offerSelectionAlgorithmId");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public JSONArray getRequestorIdsList() {

		try {

			if (!rlCfg.isNull("requestorIdsList")) {
				return rlCfg.getJSONArray("requestorIdsList");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public Boolean getKeepOffersConsistent() {

		try {

			if (!rlCfg.isNull("keepOffersConsistent")) {
				return rlCfg.getBoolean("keepOffersConsistent");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public Boolean getPreviousOffersDrawnIncluded() {

		try {

			if (!rlCfg.isNull("previousOffersDrawnIncluded")) {
				return rlCfg.getBoolean("previousOffersDrawnIncluded");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public Boolean getDuplicatedOfferWithinSingleDrawEnabled() {

		try {

			if (!rlCfg.isNull("duplicatedOfferWithinSingleDrawEnabled")) {
				return rlCfg.getBoolean("duplicatedOfferWithinSingleDrawEnabled");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public Boolean getNumOfOffersToDrawUnlimited() {

		try {

			if (!rlCfg.isNull("numOfOffersToDrawUnlimited")) {
				return rlCfg.getBoolean("numOfOffersToDrawUnlimited");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public Integer getNumOfOffersToDraw() {

		try {

			if (!rlCfg.isNull("numOfOffersToDraw")) {
				return rlCfg.getInt("numOfOffersToDraw");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public Integer getAllocationExpiration() {

		try {

			if (!rlCfg.isNull("allocationExpiration")) {
				return rlCfg.getInt("allocationExpiration");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public String getAllocationExpirationUnit() {

		try {

			if (!rlCfg.isNull("allocationExpirationUnit")) {
				return rlCfg.getString("allocationExpirationUnit");
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);

		}

		return null;

	}

	public JSONObject getErrorActions() {
		
		try {
		
			if( !rlCfg.isNull("error_actions") ) { return rlCfg.getJSONObject("error_actions"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

}
