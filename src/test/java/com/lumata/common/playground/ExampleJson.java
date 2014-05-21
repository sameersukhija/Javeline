package com.lumata.common.playground;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonConfigurationElement.JsonErrorActions;

public class ExampleJson {

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * SEZIONE OGGETTI ESEMPIO
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */	
	
	/**
	 * Esempio Basico :
	 * 
	 * Solo un JsonConfigurationFile SENZA JsonConfigurationElement esplicito ma astratto
	 * 
	 * es. commodities
	 */
	private class JsonBasic extends JsonConfigurationFile {

		public JsonBasic(String path, String jsonFile) throws JSONSException {
			super(path, jsonFile);
		}

		@Override
		public String getElementsSectionLabel() {

			return "commodities";
		}

		/**
		 * Da usarsi sul CURRENT
		 * 
		 * @return
		 */
		public String getName() {

			return getCurrentElement().getStringFromPath("name");
		}

	}

	/**
	 * Esempio Avanzato :
	 * 
	 * Solo un JsonConfigurationFile CON JsonConfigurationElement
	 */
	private class JsonAdvance extends JsonConfigurationFile {

		public JsonAdvance(String path, String jsonFile) throws JSONSException {
			super(path, jsonFile);
		}

		public class JsonNested extends JsonConfigurationElement {

			public JsonNested(Map<String, Object> newObject) {
				super(newObject);
			}

			/**
			 * Applicato a questo oggetto innestato
			 * 
			 * @return
			 */
			public String getName() {

				return this.getStringFromPath("name");
			}
		}

		/**
		 * Ritorna innestato - da aggiungersi se si vuole manipolare verso l'esterno l'oggetto innestato
		 * 
		 * @return
		 * @throws JSONSException
		 */
		public JsonNested getTypedElement() throws JSONSException {

			return new JsonNested(getCurrentElement().getJsonMapFromPath("."));
		}

		@Override
		public String getElementsSectionLabel() {

			return "commodities";
		}

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * SEZIONE TEST
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	@Test
	public void testBasic() throws JSONSException {

		/**
		 * 
		 * Oggetto file json semplice che non utilizza un elemento json innestato
		 * 
		 */
		
		// creo istanza json file basic
		JsonBasic tmp = new JsonBasic("jsonconfigtest", "sample_commodities");

		// quanti elementi sono
		int numb = tmp.getList().size();

		Reporter.log("Elementi " + numb, true);

		// for su tutti gli elementi e cerco proprietà "name"
		for (int i = 0; i < numb; i++) {

			tmp.setCurrentElementById(i);

			// cerco proprietà "name"
			Reporter.log("Nomi elementi " + tmp.getName(), true);
		}
	}

	@Test
	public void testAdvance() throws JSONSException {

		/**
		 * 
		 * Oggetto file json avanzato che utilizza un elemento json innestato
		 * 
		 */
		
		// creo istanza json file adv
		JsonAdvance tmp = new JsonAdvance("jsonconfigtest",
				"sample_commodities");

		// quanti elementi sono
		int numb = tmp.getList().size();

		Reporter.log("Elementi " + numb, true);

		for (int i = 0; i < numb; i++) {

			tmp.setCurrentElementById(i);

			JsonAdvance.JsonNested tmp2 = tmp.getTypedElement();

			// cerco proprietà "name"
			Reporter.log("Nomi elementi " + tmp2.getName(), true);
		}

	}

	@Test
	public void testErrorAction() throws JSONSException {

		/**
		 * QUESTO TEST FALLISCE
		 * 
		 * Oggetto file json avanzato che utilizza un elemento json innestato
		 * che accede all'oggetto "errorActions" e legge condizioni/azioni errore
		 * 
		 * PS questo test fallisce perchè non è stato definito comportamento di "default"
		 * cioè quando un elemento json non esplicità elemento "errorActions"
		 */
		
		// creo istanza json file adv
		JsonAdvance tmp = new JsonAdvance("jsonconfigtest",
				"sample_commodities");

		// quanti elementi sono
		int numb = tmp.getList().size();

		Reporter.log("Elementi " + numb, true);

		for (int i = 0; i < numb; i++) {

			tmp.setCurrentElementById(i);

			JsonAdvance.JsonNested tmp2 = tmp.getTypedElement();

			// cerco proprietà "name"
			Reporter.log("Nomi elementi " + tmp2.getName(), true);

			// cerco oggetto error action stampo cosa fanno nel caso
			// duplicazione entita
			JsonErrorActions errTmp = tmp2.getErrorActions();

			// stampo caratteristiche di error Action
			for (ElementErrorConditionType errCondition : ElementErrorConditionType
					.values()) {
				ElementErrorActionType errAction = errTmp
						.getAction(errCondition);

				Reporter.log(errCondition + " -> " + errAction, true);
			}
		}
	}
}
