package tomtom;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GenerateAdminKey extends BaseRequest {
	@Test
	public void generateAdminKey() {
		File adminJson = new File("testData/AdminKey.json");
		response = RestAssured.given().queryParam("key", apiKey).contentType(ContentType.JSON).body(adminJson)
				.post("regenerateKey");
		jsonResponse = response.jsonPath();
		adminKey = jsonResponse.get("adminKey");
	}
}
