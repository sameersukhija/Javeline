package com.lumata.expression.operators.gui.xmlrpc;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.common.collect.Lists;

public class XMLRPCTokenList {
	private List<Token> foundTokenList = Lists.newArrayList();

	public XMLRPCTokenList(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
		NodeList tokenList = doc.getElementsByTagName("token");
		for (int i = 0; i < tokenList.getLength(); i++) {
			Token token = getSingleToken((Element) tokenList.item(i));
			foundTokenList.add(token);
		}
	}

	private Token getSingleToken(Element token) throws Exception {
		Token newToken = new Token();
		Requestor requestor = null;
		for (TokenFieldList tokenTag : TokenFieldList.values()) {			
			Node tokenElement = token.getElementsByTagName(tokenTag.name()).item(0);
			if (TokenFieldList.requestor == tokenTag) {
				requestor = new Requestor();
				for (RequestorFieldList requestorTag : RequestorFieldList.values()) {
					Node requestorElement = ((Element) tokenElement).getElementsByTagName(requestorTag.name()).item(0);
					setValue(requestorElement, requestor);
				}
				if (null != requestor) {
					newToken.setRequestor(requestor);
				}
			} else {
				setValue(tokenElement, newToken);
			}			
		}
		return newToken;
	}

	private void setValue(Node element, Object obj) throws Exception {
		String tmp = element.getNodeName().replaceAll("_", " ");
		String methodString = "set" + WordUtils.capitalizeFully(tmp).replaceAll(" ", "");
		Method method = obj.getClass().getDeclaredMethod(methodString, String.class);
		method.invoke(obj, element.getTextContent());
	}

	public int getTokenNumber() {
		return foundTokenList.size();
	}

	public List<Token> getTokenList() {
		return foundTokenList;
	}

	private enum TokenFieldList {
		msisdn, code, label, status, sent_date, expiry_date, consumed_date, is_already_allocated, description, image_url, requestor
	}

	private enum RequestorFieldList {
		id, name, description, type
	}

	public class Token {
		private String msisdn;
		private String code;
		private String label;
		private String status;
		private String sentDate;
		private String expiryDate;
		private String consumedDate;
		private String isAlreadyAllocated;
		private String description;
		private String imageUrl;
		private Requestor requestor;

		public String getMsisdn() {
			return msisdn;
		}

		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getSentDate() {
			return sentDate;
		}

		public void setSentDate(String sentDate) {
			this.sentDate = sentDate;
		}

		public String getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}

		public String getConsumedDate() {
			return consumedDate;
		}

		public void setConsumedDate(String consumedDate) {
			this.consumedDate = consumedDate;
		}

		public String getIsAlreadyAllocated() {
			return isAlreadyAllocated;
		}

		public void setIsAlreadyAllocated(String isAlreadyAllocated) {
			this.isAlreadyAllocated = isAlreadyAllocated;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public Requestor getRequestor() {
			return requestor;
		}

		public void setRequestor(Requestor requestor) {
			this.requestor = requestor;
		}

	}

	public class Requestor {
		private String id;
		private String name;
		private String description;
		private String type;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}
}
