package com.leeh.server;

public abstract class Servlet {
	
	public void server(Request request, Response response){
		if (request.getMethod().equalsIgnoreCase(Request.REQUEST_METHOD_GET)) {
			doGet(request, response);
		}else if (request.getMethod().equalsIgnoreCase(Request.REQUEST_METHOD_POST)) {
			doPost(request, response);
		}
	}
	
	public abstract void doGet(Request request, Response response);
	
	public abstract void doPost(Request request, Response response);
}
