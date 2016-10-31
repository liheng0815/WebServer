package com.leeh.server;

public class Servlet {
	
	public void server(Request request, Response response){
		if (request.getMethod().equalsIgnoreCase(Request.REQUEST_METHOD_GET)) {
			doGet(request, response);
		}else if (request.getMethod().equalsIgnoreCase(Request.REQUEST_METHOD_POST)) {
			doPost(request, response);
		}
	}
	
	public void doGet(Request request, Response response) {
		String response_content = "<html><head><title>Test</title></head><body> ÄãºÃ"
				+ request.getParameter("userName") + "</body></html>";
		response.createContent(response_content);
	}
	
	public void doPost(Request request, Response response){
		doGet(request, response);
	}
}
