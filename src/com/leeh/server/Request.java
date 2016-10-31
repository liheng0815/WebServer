package com.leeh.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.leeh.Util.CloseUtil;

public class Request {
	private final static String CRLF = "\r\n";
	public final static String REQUEST_METHOD_GET = "GET";
	public final static String REQUEST_METHOD_POST = "POST";
	// ���󷽷�
	private String method;
	// ������Դ
	private String url;
	// �������
	private Map<String, List<String>> parameters;
	// ������
	private InputStream is;
	// ������Ϣ
	private String resquestInfo;

	public Request() {
		method = "";
		url = "";
		parameters = new HashMap<String, List<String>>();
		resquestInfo = "";
	}

	public Request(InputStream is) {
		this();
		this.is = is;
		try {
			StringBuilder sb = new StringBuilder();
			while (is.available() > 0) {
				sb.append((char) is.read());
			}
			resquestInfo = sb.toString();
			
			parseRequestInfo();
		} catch (IOException e) {
			return;
		}

	}

	/**
	 * ����������Ϣ
	 */
	private void parseRequestInfo() {
		if (null == resquestInfo || resquestInfo.trim().isEmpty()) {
			return;
		}
		// ��ȡ����ʽ
		String firstLine = resquestInfo
				.substring(0, resquestInfo.indexOf(CRLF));
		int index = firstLine.indexOf("/");
		this.method = firstLine.substring(0, index).trim();
		String strUrl = firstLine.substring(index, firstLine.indexOf("HTTP/"))
				.trim();
		// ��ȡ�������
		String strParater = "";
		if (method.equalsIgnoreCase(REQUEST_METHOD_POST)) {
			this.url = strUrl;
			strParater = resquestInfo.substring(resquestInfo.lastIndexOf(CRLF));
		} else if (method.equalsIgnoreCase(REQUEST_METHOD_GET)) {
			if (strUrl.contains("?")) {
				String[] urlArray = strUrl.split("\\?");
				this.url = urlArray[0];
				strParater = urlArray[1];
			} else {
				this.url = strUrl;
			}
		}
		if (!strParater.equals("")) {
			parseParameters(strParater);
		}

	}
	
	private String decode(String value,String enc){
		try {
			return URLDecoder.decode(value, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��������
	 * @param parameters �����ַ���
	 */
	private void parseParameters(String parameters){
		StringTokenizer token = new StringTokenizer(parameters, "&");
		while (token.hasMoreTokens()) {
			String[] key_values = token.nextToken().split("=");
			//�������Ϊ id= ��ת��Ϊ���� �� id=null
			if (key_values.length == 1) {
				key_values = Arrays.copyOf(key_values, 2);
				key_values[1] = null;
			}
			//���������뵽map��������
			String key = key_values[0].trim();
			String value = key_values[1] == null ? null : decode(key_values[1].trim(),"gbk");
			//��������������򴴽��µ�
			if (!this.parameters.containsKey(key)) {
				this.parameters.put(key, new ArrayList<String>());
			}
			this.parameters.get(key).add(value);
		}
		
	}
	
	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String[] getArrayParameter(String name) {
		if (parameters.containsKey(name)) {
			List<String> lstValue = parameters.get(name);
			return lstValue.toArray(new String[lstValue.size()]);
		}
		return null;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		String[] values = getArrayParameter(name);
 		if (values == null) {
			return null;
		}
		return values[0];
	}

	public void close(){
		CloseUtil.closeIOStream(is);
	}
	
	@Override
	public String toString() {
		return resquestInfo;
	}
}
