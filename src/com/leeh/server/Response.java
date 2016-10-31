package com.leeh.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.leeh.Utils.CloseUtil;

public class Response {

	private final static String CRLF = "\r\n";
	private final static String BLANK = " ";
	//��Ӧͷ��
	private StringBuilder response_head;
	//��Ӧ����
	private StringBuilder response_content;
	//���ĳ���
	private int length;
	//�����
	private BufferedWriter bw;
	
	public Response() {
		response_head = new StringBuilder();
		response_content = new StringBuilder();
		length = 0;
		
	}
	public Response(OutputStream os) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(os));
		} catch (Exception e) {
			response_head = null;
		}
		
	}
	/**
	 * ��������
	 * @param content
	 * @return
	 */
	public Response createContent(String content){
		response_content.append(content).append(CRLF);
		
		length += (content + CRLF).getBytes().length;
		
		return this;
	}
	/**
	 * ����ͷ��
	 * @param code
	 */
	private void createHead(int code){
		response_head.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200:
			response_head.append("OK");
			break;
		case 404:
			response_head.append("NOT FOUND");
			break;
		case 505:
			response_head.append("SERVER ERROR");
			break;
		}
		response_head.append(CRLF);
		response_head.append("server:Leeh server/1.0.1").append(CRLF);
		response_head.append("Date:").append(new Date()).append(CRLF);
		response_head.append("Content-type:text/html;charset=GBK").append(CRLF);
		response_head.append("Content-Length:").append(length).append(CRLF);
		response_head.append(CRLF);
	}
	/**
	 * ��Ӧ�ͻ���
	 * @param code
	 * @throws IOException
	 */
	public void pushClient(int code) throws IOException{
			if (bw == null) {
				code = 500;
			}
			createHead(code);
			bw.append(response_head.toString());
			bw.append(response_content.toString());
			bw.flush();
	}
	/**
	 * �ر���
	 */
	public void close(){
		CloseUtil.closeIOStream(bw);
	}
	@Override
	public String toString() {
		return response_head.toString() + response_content.toString();
	}
}
