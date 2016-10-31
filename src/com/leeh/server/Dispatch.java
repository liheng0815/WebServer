package com.leeh.server;

import java.io.IOException;
import java.net.Socket;

import com.leeh.Utils.CloseUtil;

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
		try {
			System.out.println(request.toString());
			System.out.println(request.getUrl());
			Servlet servlet = WebApp.getServlet(request.getUrl());
			if (servlet == null) {
				code = 404;
			}else{
				servlet.server(request, response);
				response.pushClient(code);
			}
		} catch (IOException e) {
			code = 500;
		} catch (InstantiationException e) {
			code = 404;
		} catch (IllegalAccessException e) {
			code = 404;
		} catch (ClassNotFoundException e) {
			code = 404;
		}finally{
			CloseUtil.closeSocket(client);
		}
	}

}
