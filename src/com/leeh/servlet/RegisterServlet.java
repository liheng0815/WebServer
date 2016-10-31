package com.leeh.servlet;

import com.leeh.server.Request;
import com.leeh.server.Response;
import com.leeh.server.Servlet;

public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) {
		doPost(request, response);
	}

	@Override
	public void doPost(Request request, Response response) {
		String response_content = "<html><head><title>Test</title></head><body>»¶Ó­×¢²á£¡"
				+ "</body></html>";
		response.createContent(response_content);
	}

}
