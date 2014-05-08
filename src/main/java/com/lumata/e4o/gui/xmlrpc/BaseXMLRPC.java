package com.lumata.e4o.gui.xmlrpc;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class BaseXMLRPC<T extends BaseXMLRPC<?>> {

	protected Document doc = null;
	
	public BaseXMLRPC(String xml) throws Exception {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
	}
	
	protected void setValue(Node element, Object obj) throws Exception {
		String tmp = element.getNodeName().replaceAll("_", " ");
		String methodString = "set" + WordUtils.capitalizeFully(tmp).replaceAll(" ", "");
		Method method = obj.getClass().getDeclaredMethod(methodString, String.class);
		method.invoke(obj, element.getTextContent());
	}

	
	public abstract T parse()throws Exception ;
			
}
