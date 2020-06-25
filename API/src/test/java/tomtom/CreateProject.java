package tomtom;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateProject extends BaseRequest {
	@Test(dependsOnMethods = { "tomtom.GenerateAdminKey.generateAdminKey" })
	public void createProject() {
		File createProject = new File("testData/CreateProject.json");
		response = RestAssured.given().queryParam("key", apiKey).contentType(ContentType.JSON)
				.queryParam("adminKey", adminKey).body(createProject).post("projects/project");
		jsonResponse = response.jsonPath();
		projectName = jsonResponse.get("name");
		System.out.println(projectName);
		if (response.getStatusCode() == 201) {
			System.out.println("Project is created");
		}

	}

}
