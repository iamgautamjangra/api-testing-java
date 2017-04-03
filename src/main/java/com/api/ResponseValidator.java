package com.api;

import java.util.HashMap;
import java.util.Set;

import org.testng.Assert;

/**
 * @author Gautam Jangra
 * 
 */
public class ResponseValidator {
	private ResponseExtractor response;

	ResponseValidator(ResponseExtractor response) {
		this.response = response;
	}

	public ResponseValidator printResponseBody() {
		System.out.println("Response Body : " + response.getResponseBody());
		return this;
	}

	public ResponseValidator printResponseHeaders() {
		System.out.println("Response Headers : " + response.getHeaders());
		return this;
	}

	public ResponseExtractor getResponse() {
		return response;
	}

	public ResponseValidator assertResponseCode(int expectedCode) {
		Assert.assertEquals(expectedCode, response.getResponseCode());
		return this;
	}

	public ResponseValidator assertResponseMessage(String message) {
		Assert.assertEquals(message, response.getResponseMessage());
		return this;
	}

	public ResponseValidator assertResponseHeader(String headerName,
			String headerValue) {
		System.out.println(headerName + " - " + response.getHeader(headerName));
		Assert.assertEquals(headerValue,
				response.getHeader(headerName));
		return this;
	}

	public ResponseValidator assertResponseHeaders(
			HashMap<String, String> headers) {
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			Assert.assertEquals(headers.get(key), response.getHeader(key));
		}
		return this;
	}

	public ResponseValidator assertContentInResponseBody(String content) {
		Assert.assertTrue(response.getResponseBody().contains(content));
		return this;
	}

}