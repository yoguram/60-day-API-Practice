package tomtom;

import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class GetProjectList extends BaseRequest {
	@Test(dependsOnMethods = { "tomtom.CreateProject.createProject" })
	public void getProjectList() {
		response = RestAssured.given().param("key", apiKey).get("projects");
		jsonResponse = response.jsonPath();
		String projectsName;
		List<String> project = jsonResponse.get("projects.name");
		for (int i = 0; i < project.size(); i++) {
			projectsName = project.get(i);
			if (projectsName.contains(projectName)) {
				System.out.println("Testing Project is created Successfully");
			}
		}
	}

}
