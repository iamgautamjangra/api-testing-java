package test.java.com.api;

import main.java.com.api.RequestExecutor;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSample {
	private static final String URL = "https://reqres.in";
	private static RequestExecutor executor;
	String id = "";
	@BeforeClass
	public static void setUp() {
		executor = new RequestExecutor(URL);
	}

	@Test(priority = 1)
	public void testGETRequest() {
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
	public void testPOSTRequest() {
		String request = "{\"name\": \"brad\",\"job\": \"leader\"}";
		id = executor.post("/api/users", null, request, "application/json")
				.printResponseBody()
				.printResponseHeaders()
				.assertResponseCode(201)
				.assertResponseMessage("Created")
				.assertResponseHeader("Content-Type",
						"application/json; charset=utf-8")
				.assertContentInResponseBody("brad")
				.assertContentWithKeyResponseBody("job", "leader")
				.getResponse().getValueFromResponseBody("id", String.class);
		
		System.out.println("ID of POST - " + id);

	}
	
	@Test(priority = 2)
	public void testPUTRequest() {
		String request = "{\"name\": \"morpheus\",\"job\": \"manager\"}";
		executor.put("/api/users/"+id, null, request, "application/json")
				.printResponseBody()
				.printResponseHeaders()
				.assertResponseCode(200)
				.assertResponseMessage("OK")
				.assertResponseHeader("Content-Type",
						"application/json; charset=utf-8")
				.assertContentInResponseBody("morpheus")
				.assertContentWithKeyResponseBody("job", "manager");
		
	}

}