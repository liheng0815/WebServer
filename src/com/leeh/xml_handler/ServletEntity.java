package com.leeh.xml_handler;

/**
 * ƥ��servlet�����ļ�
 * @author Liheng
 *
 */
public class ServletEntity {
	private String servletName; //���
	private String servletClass;//ȫ��
	
	public ServletEntity(){}
	/**
	 * ���캯��
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
