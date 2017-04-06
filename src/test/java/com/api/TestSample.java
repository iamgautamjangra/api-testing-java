package test.java.com.api;

import main.java.com.api.RequestExecutor;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSample {
	private static final String URL = "https://reqres.in";
	private static RequestExecutor executor;

	@BeforeClass
	public static void setUp() {
		executor = new RequestExecutor(URL);
	}

	@Test(priority = 1)
	public void testGETMethod() {
		executor.get("/api/users?page=2", null)
				.printResponseBody()
				.printResponseHeaders()
				.assertResponseCode(200)
				.assertResponseMessage("OK")
				.assertResponseHeader("Content-Type",
						"application/json; charset=utf-8")
				.assertContentInResponseBody("total_pages");

	}

	@Test(priority = 2)
	public void testPOSTMethod() {
		String request = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
		executor.post("/api/users", null, request, "application/json")
				.printResponseBody()
				.printResponseHeaders()
				.assertResponseCode(201)
				.assertResponseMessage("Created")
				.assertResponseHeader("Content-Type",
						"application/json; charset=utf-8")
				.assertContentInResponseBody("morpheus")
				.assertContentWithKeyResponseBody("job", "leader");

	}

}