package com.leeh.xml_handler;

/**
 * 匹配servlet配置文件
 * @author Liheng
 *
 */
public class ServletEntity {
	private String servletName; //简称
	private String servletClass;//全称
	
	public ServletEntity(){}
	/**
	 * 构造函数
	 * @param servletName name
	 * @param servletClass class
	 */
	public ServletEntity(String servletName, String servletClass) {
		this.servletName = servletName;
		this.servletClass = servletClass;
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getServletClass() {
		return servletClass;
	}

	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	
	
}
