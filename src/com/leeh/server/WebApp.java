package com.leeh.server;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.leeh.Utils.XMLPasrseUtil;


public class WebApp {
	private static ServletContext context;
	static{
		try {
			context = XMLPasrseUtil.xmlParse();
			System.out.println("context");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (url == null || url.isEmpty()) {
			return null;
		}
		String className =context.getServlet().get( context.getMapping().get(url));
		System.out.println(className);
		return (Servlet)Class.forName(className).newInstance();
	}
	
}
