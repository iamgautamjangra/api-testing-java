package main.java.com.api;

import java.util.HashMap;

import org.json.JSONObject;

public class ResponseExtractor {
	private int responseCode;
	private String responseBody;
	private HashMap<String, String> headers;
	private String responseMessage;

	public ResponseExtractor() {
		headers = new HashMap<String, String>();
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public String getHeader(String name) {
		return headers.get(name);
	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	

	public <T> T getValueFromResponseBody(String key, Class<T> type) {
		JSONObject jsonObj = new JSONObject(responseBody);
	    return type.cast(jsonObj.get(key));
	}
}