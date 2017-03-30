package com.api;

import java.util.HashMap;
import java.util.Set;
import org.junit.Assert;

public class ResponseValidator {
	private ResponseExtractor response;

	ResponseValidator(ResponseExtractor response) {
		this.response = response;
	}

	public ResponseValidator expectCode(int expectedCode) {
		Assert.assertEquals("Incorrect Response Code", expectedCode,
				response.getResponseCode());
		return this;
	}

	public ResponseValidator expectMessage(String message) {
		Assert.assertEquals("Incorrect Response Message", message,
				response.getResponseMessage());
		return this;
	}

	public ResponseValidator expectHeader(String headerName, String headerValue) {
		Assert.assertEquals("Incorrect header - " + headerName, headerValue,
				response.getHeader(headerName));
		return this;
	}

	public ResponseValidator expectHeaders(HashMap<String, String> headers) {
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			Assert.assertEquals("Incorrect header - " + key, headers.get(key),
					response.getHeader(key));
		}
		return this;
	}

	public ResponseValidator expectInBody(String content) {
		Assert.assertTrue("Body doesnt contain string : " + content, response
				.getResponseBody().contains(content));
		return this;
	}

	public ResponseValidator printBody() {
		System.out.println(response.getResponseBody());
		return this;
	}

	public ResponseExtractor getResponse() {
		return response;
	}
}