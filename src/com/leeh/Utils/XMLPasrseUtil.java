package com.leeh.Utils;

import java.io.IOException;

import javax.activation.MailcapCommandMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.leeh.server.ServletContext;
import com.leeh.xml_handler.ServletEntity;
import com.leeh.xml_handler.ServletMapping;
import com.leeh.xml_handler.XmlHandle;

public class XMLPasrseUtil {
	
	private static String SERVLET_CONFIGURE = "web.xml";
	
	public static ServletContext xmlParse()
			throws ParserConfigurationException, SAXException, IOException {
		ServletContext context = new ServletContext();
		// ��ȡxml��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// ��ȡ������
		SAXParser parser = factory.newSAXParser();
		// �Լ�ʵ�ֵĽ�����
		XmlHandle handler = new XmlHandle();
		// ��ȡ��Ҫ�����ļ��ĵ�ַ
		parser.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(SERVLET_CONFIGURE), handler);
		for (ServletEntity entity : handler.getLstEntities()) {
			context.getServlet().put(entity.getServletName(),
					entity.getServletClass());
			System.out.println(entity.getServletName() + ":" + entity.getServletClass());
		}

		for (ServletMapping mapping : handler.getLstMappings()) {
			context.getMapping().put(mapping.getUrlPattern(),
					mapping.getServletName());
			System.out.println(mapping.getUrlPattern() + ":" + mapping.getServletName());
		}

		
		
		return context;
	}
}
