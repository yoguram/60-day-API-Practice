package tests.rest;

import java.io.File;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC002_DeleteFirstIncident extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {

		testCaseName = "Delete the first incident (REST)";
		testDescription = "Get all incidents from the search and delete the first incident";
		nodes = "Incident";
		authors = "Babu";
		category = "REST";
		dataFileName = "TC002";
		dataFileType = "JSON";
	}

	@Test()
	public void deleteIncident() {		
		
		// Post the request
		Response response = get("table/incident");		
			
		// Verify the Content type
		verifyContentType(response, "JSON");
		
		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 10000);
		
		// Get the Incidents
		List<String> contents = getContentsWithKey(response, "result.sys_id");
		
		// Delete the first incident
		response = delete("table/incident/"+contents.get(0));
		
		System.out.println(contents.get(0));
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Verify the response status code
		verifyResponseCode(response, 204);	
		
		
	}


}





