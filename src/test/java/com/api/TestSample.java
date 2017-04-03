package com.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestSample {
	private static final String URL = "https://reqres.in";
	private static RequestExecutor executor;

	@BeforeClass
	public static void setUp() {
		/*
		 * Initialize RestExecutor object using URL
		 */
		executor = new RequestExecutor(URL);
	}

	@Test
	public void testGETMethod() {
		executor.get("/api/users?page=2", null)
				.printResponseBody()
				.printResponseHeaders()
				.assertResponseCode(200)
				.assertResponseMessage("OK")
				.assertResponseHeader("Content-Type", "application/json; charset=utf-8") 
				.assertContentInResponseBody("total_pages");
	}

}