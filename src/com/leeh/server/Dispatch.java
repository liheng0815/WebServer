package com.leeh.server;

import java.io.IOException;
import java.net.Socket;

import com.leeh.Util.CloseUtil;

public class Dispatch implements Runnable {

	private Socket client;
	private Request request;
	private Response response;
	private int code;
	
	public Dispatch(Socket client){
		this.client = client;
		try {
			request = new Request(client.getInputStream());
			response = new Response(client.getOutputStream());
		} catch (IOException e) {
			code = 500;
		}
	}

	@Override
	public void run() {
		Servlet servlet = new Servlet();
		servlet.server(request, response);
		try {
			response.pushClient(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CloseUtil.closeSocket(client);
		}
	}

}
