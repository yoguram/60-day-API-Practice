package tests.rest;

import java.io.File;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC003_DeleteRandomAndCount extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {

		testCaseName = "Delete the incident randomly (REST)";
		testDescription = "Get all incidents from the search and delete the first incident";
		nodes = "Incident";
		authors = "Babu";
		category = "REST";
		dataFileName = "TC002";
		dataFileType = "JSON";
	}

	@Test()
	public void deleteIncidentRandomly() {		

		// Post the request
		Response response = get("table/incident");		

		// Get the Incidents
		List<String> contents = getContentsWithKey(response, "result.sys_id");

		// Get the count
		int initial = contents.size();
		System.out.println("The count before delete : "+initial);

		// Get random number
		int random = (int)(Math.random() * initial);
		System.out.println("The random sys_id to be deleted is : "+contents.get(random));

		// Delete the first incident
		response = delete("table/incident/"+contents.get(random));

		response.prettyPrint();

		// Verify the response status code
		verifyResponseCode(response, 204);	

		// Post the request
		response = get("table/incident");		

		// Get the Incidents
		contents = getContentsWithKey(response, "result.sys_id");

		// Get the count
		int countAfterDelete = contents.size();
		System.out.println("The count after delete : "+countAfterDelete);


	}


}





