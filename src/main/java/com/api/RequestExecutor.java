package main.java.com.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class RequestExecutor {
	private HttpClient client;
	private String url;

	public RequestExecutor(String url) {
		client = HttpClientBuilder.create().build();
		this.url = url;
	}

	/**
	 * Method to executes GET request.
	 * 
	 */
	public ResponseValidator get(String path, HashMap<String, String> headers) {
		HttpGet request = new HttpGet(url + path);
		HttpResponse response;
		/*
		 * The response object which holds the details of the response.
		 */
		ResponseExtractor resResponse = new ResponseExtractor();
		StringBuffer responseString = new StringBuffer();
		try {
			/*
			 * Setting the headers for the request
			 */
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					request.addHeader(key, headers.get(key));
				}
			}
			/*
			 * Executing the GET operation
			 */
			response = client.execute(request);
			/*
			 * Obtaining the response body from the response stream.
			 */
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			/*
			 * Setting values for the response object
			 */
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine()
					.getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine()
					.getReasonPhrase());
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * Returns the ResponseValidator object providing the response object
		 */
		return new ResponseValidator(resResponse);
	}

	/**
	 * Method to executes POST request.
	 * 
	 */
	public ResponseValidator post(String path, HashMap<String, String> headers,
			String requestBody, String contentType) {
		HttpPost post = new HttpPost(url + path);
		ResponseExtractor resResponse = new ResponseExtractor();
		StringBuffer responseString = new StringBuffer();
		try {
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					post.addHeader(key, headers.get(key));
				}
			}
            
			StringEntity requestEntity = new StringEntity(
					requestBody,
				    ContentType.APPLICATION_JSON);
			post.setEntity(requestEntity);
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine()
					.getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine()
					.getReasonPhrase());
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new ResponseValidator(resResponse);
	}

	/**
	 * Method to executes DELETE request.
	 * 
	 */
	public ResponseValidator delete(String path, HashMap<String, String> headers) {
		HttpDelete delete = new HttpDelete(url + path);
		ResponseExtractor resResponse = new ResponseExtractor();
		StringBuffer responseString = new StringBuffer();
		try {
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					delete.addHeader(key, headers.get(key));
				}
			}
			
			HttpResponse response = client.execute(delete);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine()
					.getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine()
					.getReasonPhrase());
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseValidator(resResponse);
	}

	/**
	 * Method to executes PUT request.
	 * 
	 */
	public ResponseValidator put(String path, HashMap<String, String> headers,
			String xmlContent, String contentType) {
		HttpPut put = new HttpPut(url + path);
		ResponseExtractor resResponse = new ResponseExtractor();
		StringBuffer responseString = new StringBuffer();
		try {
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					put.addHeader(key, headers.get(key));
				}
			}
			StringEntity input = new StringEntity(xmlContent);
			input.setContentType(contentType);
			put.setEntity(input);
			HttpResponse response = client.execute(put);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine()
					.getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine()
					.getReasonPhrase());
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new ResponseValidator(resResponse);
	}

	/**
	 * Method to executes PATCH request.
	 * 
	 */
	public ResponseValidator patch(String path,
			HashMap<String, String> headers, String xmlContent,
			String contentType) {
		HttpPatch patch = new HttpPatch(url + path);
		ResponseExtractor resResponse = new ResponseExtractor();
		StringBuffer responseString = new StringBuffer();
		try {
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					patch.addHeader(key, headers.get(key));
				}
			}
			StringEntity input = new StringEntity(xmlContent);
			input.setContentType(contentType);
			patch.setEntity(input);
			HttpResponse response = client.execute(patch);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine()
					.getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine()
					.getReasonPhrase());
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new ResponseValidator(resResponse);
	}
/*
	*//**
	 * Gets the hashmap turns it in HttpEntity nameValuePair.
	 * 
	 * @param inputEntities
	 * @return
	 *//*
	private HttpEntity getEntities(HashMap<String, String> inputEntities) {
		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(
				inputEntities.size());
		Set<String> keys = inputEntities.keySet();
		for (String key : keys) {
			nameValuePairs.add(new BasicNameValuePair(key, inputEntities
					.get(key)));
		}
		try {
			return new UrlEncodedFormEntity(nameValuePairs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
}