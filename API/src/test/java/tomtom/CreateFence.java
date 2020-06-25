package tomtom;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateFence extends BaseRequest {
	@Test(dependsOnMethods = { "tomtom.GenerateAdminKey.generateAdminKey" })
	public void createFence() {
		File createFence = new File("testData/CreateFence.json");
		response = RestAssured.given().queryParam("key", apiKey).contentType(ContentType.JSON)
				.queryParam("adminKey", adminKey).body(createFence).post("projects/" + projectId + "/fence");
		response.prettyPrint();
		jsonResponse = response.jsonPath();
		fenceName = jsonResponse.get("name");
				
	}

}
