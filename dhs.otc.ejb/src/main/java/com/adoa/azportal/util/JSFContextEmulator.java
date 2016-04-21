package com.adoa.azportal.util;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class JSFContextEmulator {

	Response response = new Response();

	public Response getResponse() {
		return response;
	}

}
