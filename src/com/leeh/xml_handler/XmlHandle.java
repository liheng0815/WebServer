package com.leeh.xml_handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * ½âÎöServlet xmlÎÄ¼þ
 * 
 * @author Liheng
 * 
 */
public class XmlHandle extends DefaultHandler {

	private static String SERVLET = "servlet";
	private static String MAPPING = "mapping";
	private static String SERVLET_NAME = "servlet-name";
	private static String SERVLET_CLASS = "servlet-class";
	private static String URL_PATTERN = "url-pattern";

	private List<ServletEntity> lstEntities;
	private List<ServletMapping> lstMappings;

	private ServletEntity entity;
	private ServletMapping mapping;

	private String qName =null;
	
	
	

	@Override
	public void startDocument() throws SAXException {
		lstEntities = new ArrayList<ServletEntity>();
		lstMappings = new ArrayList<ServletMapping>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName == null) {
			return;
		}
		if (qName.equals(SERVLET)) {
			entity = new ServletEntity();
		} else if (qName.equals(MAPPING)) {
			mapping = new ServletMapping();
		}
		
		this.qName = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (this.qName == null) {
			return;
		}
		if (entity != null) {
			String strServlet = new String(ch, start, length);
			if (this.qName.equals(SERVLET_NAME)) {
				entity.setServletName(strServlet);
			} else if (this.qName.equals(SERVLET_CLASS)) {
				entity.setServletClass(strServlet);
			}
		}
		if (mapping != null) {
			String strMapping = new String(ch, start, length);
			if (this.qName.equals(SERVLET_NAME)) {
				mapping.setServletName(strMapping);
			} else if (this.qName.equals(URL_PATTERN)) {
				mapping.setUrlPattern(strMapping);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName == null) {
			return;
		}
		if (qName.equals(SERVLET)) {
			lstEntities.add(entity);
			entity = null;
		} else if (qName.equals(MAPPING)) {
			lstMappings.add(mapping);
			mapping = null;
		}
		
		this.qName =  null;
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	public List<ServletEntity> getLstEntities() {
		return lstEntities;
	}

	public List<ServletMapping> getLstMappings() {
		return lstMappings;
	}
}
