package tomtom;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetFenceList extends BaseRequest {
	@Test(dependsOnMethods = {"tomtom.CreateFence.createFence"})
	public void getFenceList()
	{
		response = RestAssured.given().queryParam("key", apiKey).get("projects/"+projectId+"/fences");
		jsonResponse = response.jsonPath();
		response.prettyPrint();
		String name;
		List<String> fencesName = jsonResponse.get("fences.name");
		for(int i=0;i<fencesName.size();i++)
		{
			name = fencesName.get(i);
			if(name.contains(fenceName))
			{
				System.out.println("Fence Created Successfully");
			}
		}
		
	}

}
