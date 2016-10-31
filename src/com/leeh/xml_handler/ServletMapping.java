package com.leeh.xml_handler;

/**
 * ƥ��servlet�����ļ�
 * @author Liheng
 *
 */
public class ServletMapping {
	private String servletName;
	private String urlPattern;
	
	public ServletMapping(){}
	
	/**
	 * �вι���
	 * @param servletName name
	 * @param urlPattern url
	 */
	public ServletMapping(String servletName, String urlPattern) {
		super();
		this.servletName = servletName;
		this.urlPattern = urlPattern;
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	
	
}
