package tests.rest;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class Scenario2 extends RESTAssuredBase {
	public static String user_id = "mb6q0u3r";
	@BeforeTest
	public void setValues() {
		testCaseName = "Scenario 2";
		testDescription = "Get the user List and update the user details";
		nodes = "newsLetter - Scenario2";
		authors = "Ram";
		category = "API";
		dataFileName = "Update User";
		dataFileType = "JSON";
	}

	@Test
	public void scenario2() {
		// Get the users list
		
		Response getUser = get("users");
		getUser.prettyPrint();
		List<String> userList = getUser.jsonPath().getList("value.id");
		for (String user : userList) {
			System.out.println(user);
		}
		
		// Update the details for an user
		Response updateUser = patchWithJsonAsBody("{\r\n" + 
				"    \"last_name\": \"Bruce\"\r\n" + 
				"}", "users/"+user_id+"");
		verifyResponseCode(updateUser, 200);
		
		//Get the details and verify the last name updated
		
		Response getUserDetails = get("users/"+user_id+"");
		getUserDetails.prettyPrint();
		List<String> lastName = getUserDetails.jsonPath().getList("value.last_name");
		System.out.println(lastName);
		if(lastName.get(0).equalsIgnoreCase("bruce"))
		{
			System.out.println("User updated successfully");
		}
		
		
		
	}

}
