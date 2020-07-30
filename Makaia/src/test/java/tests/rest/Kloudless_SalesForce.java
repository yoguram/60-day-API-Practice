package tests.rest;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class Kloudless_SalesForce extends RESTAssuredBase {
	static String accountName;
	static String contactName;
	static String oppName;
	static String taskID;
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Scenario 1";
		testDescription = "Create the app,Update the logo and verify the app with new logo";
		nodes = "Kloudless - Scenario1";
		authors = "Ram";
		category = "API";
		//dataFileName = "CreateClient";
		//dataFileType = "JSON";
	}
	@Test
	public void createOpportunityAndContacts()
	{
		// Get the Account name
		Response getAccount = get("accounts");
		accountName = getAccount.jsonPath().getString("objects[1].name");
		
		//Create Contact
		
		Response createContact = postWithJsonAsBody("{\r\n" + 
				"       \r\n" + 
				"        \"first_name\": \"David New\",\r\n" + 
				"        \"last_name\": \"Yu\",\r\n" + 
				"        \"title\": \"Customer Service Advisor\"\r\n" + 
				"}", "contacts");
		contactName = createContact.jsonPath().getString("name");
		
		//Create Opportunity
		
		Response createOpportunity = postWithJsonAsBody("{\r\n" + 
				"    \"owner\":\""+accountName+"\",\r\n" + 
				"    \"name\": \""+contactName+"\",\r\n" + 
				"    \"stage_name\": \"Prospect\",\r\n" + 
				"    \"closing_date\": \"2016-01-01T01:01:01.010000Z\"\r\n" + 
				"}", "opportunities");
		
		oppName = createOpportunity.jsonPath().getString("name");
		
		//Verify the Opportunity
		Response getOpportunity = get("opportunities");
		List<String> list = getOpportunity.jsonPath().getList("objects.name");
		for (String eachValue : list) {
			if(eachValue==oppName)
			{
				System.out.println("Opportunity is in the list");
			}
		}
		
		
		// Create Task
		Response createTask = postWithJsonAsBody("{\r\n" + 
				"     \r\n" + 
				"        \"status\": \"Completed\",\r\n" + 
				"        \"priority\": \"low\",\r\n" + 
				"        \"subject\": \"Testing 1\",\r\n" + 
				"        \"description\": \"Bi-weekly marketing report\"\r\n" + 
				"}", "tasks");
		taskID = createTask.jsonPath().getString("id");
		
		//Update the Task
		
		Response updateTask = patchWithJsonAsBody("{\"subject\": \"Testing updated\"}", "tasks/"+taskID+"");
		verifyResponseCode(updateTask, 200);
		
		//Delete the Task
		
		Response deleteTask = delete("tasks/"+taskID+"");
		verifyResponseCode(deleteTask, 204);
	}

}
