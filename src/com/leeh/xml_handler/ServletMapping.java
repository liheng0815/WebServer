package com.leeh.xml_handler;

/**
 * 匹配servlet配置文件
 * @author Liheng
 *
 */
public class ServletMapping {
	private String servletName;
	private String urlPattern;
	
	public ServletMapping(){}
	
	/**
	 * 有参构造
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
