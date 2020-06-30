package tests.rest;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC001_CreateIncident extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {

		testCaseName = "Create a new Incident (REST)";
		testDescription = "Create a new Incident and Verify";
		nodes = "Incident";
		authors = "Babu";
		category = "REST";
		dataFileName = "TC001";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData")
	public void createIncident(File file) {		
		
		// Post the request
		Response response = postWithBodyAsFileAndUrl(file,"table/incident");
				
		//Verify the Content by Specific Key
		verifyContentWithKey(response, "result.short_description", "This is Rest Assured Automation framework - Makaia");
		
		// Verify the Content type
		verifyContentType(response, "JSON");
		
		// Verify the response status code
		verifyResponseCode(response, 201);	
		
		// Verify the response time
		verifyResponseTime(response, 10000);
		
	}


}





