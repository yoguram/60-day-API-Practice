package tests.rest;

import static org.hamcrest.Matchers.hasItems;

import java.io.File;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class FreshBooks_CreateTimeEntry extends RESTAssuredBase{
	static String accountId;
	static String businessId;
	static String clientId;
	static String timeEntryId;
	static String projectId = "4134851";
	@BeforeSuite
	public void setValues() {

		testCaseName = "Scenario 1";
		testDescription = "Create, Verify and Delete the timeEntry created for Freshbooks";
		nodes = "FreshBooks - Scenario1";
		authors = "Ram";
		category = "API";
		//dataFileName = "CreateTimeEntry";
		//dataFileType = "JSON";

	}
	@Test
	public void createTimeEntry()
	{
		
		// Get the account id and business id
		Response getDetails = get("auth/api/v1/users/me");
		accountId = getDetails.jsonPath().getString("response.roles[0].accountid");
		businessId = getDetails.jsonPath().getString("response.business_memberships[0].business.id");
		
		//Get ClientID
		Response getClientID = get("accounting/account/"+accountId+"/users/clients");
		clientId = getClientID.jsonPath().getString("response.result.clients[0].id");
				
		// Create Time Entry
		Response createTimeEntry = postWithJsonAsBody("{\r\n" + 
				"    \"time_entry\": {\r\n" + 
				"        \"is_logged\": true,\r\n" + 
				"        \"duration\": 7200,\r\n" + 
				"        \"note\": \"Stuff\",\r\n" + 
				"        \"started_at\": \"2016-08-16T18:03:28Z\",\r\n" + 
				"        \"client_id\": \""+clientId+"\",\r\n" + 
				"        \"project_id\": \""+projectId+"\"\r\n" + 
				"    }\r\n" + 
				"}", "timetracking/business/"+businessId+"/time_entries");
		timeEntryId = createTimeEntry.jsonPath().getString("time_entry.id");
				
		// Verify the newly created TimeEntry
		
		Response getEntry  = get("timetracking/business/"+businessId+"/time_entries");
		getEntry.then().assertThat().body("time_entries.id",hasItems(Integer.parseInt(timeEntryId)));
				
		// Update the Created TimeEntry
		String note = "Stuff updated";
		Response updateEntry = putWithBodyParam("{\r\n" + 
				"    \"time_entry\": {\r\n" + 
				"        \"is_logged\": true,\r\n" + 
				"        \"duration\": 7500,\r\n" + 
				"        \"note\": \""+note+"\",\r\n" + 
				"        \"started_at\": \"2016-08-16T18:03:28Z\",\r\n" + 
				"        \"client_id\": \""+clientId+"\",\r\n" + 
				"        \"project_id\": \""+projectId+"\"\r\n" + 
				"    }\r\n" + 
				"}", "timetracking/business/"+businessId+"/time_entries/"+timeEntryId+"");
		updateEntry.prettyPrint();
		verifyResponseCode(updateEntry, 200);
		// Delete the created Entry
		Response deleteEntry = delete("timetracking/business/"+businessId+"/time_entries/"+timeEntryId+"");
		verifyResponseCode(deleteEntry, 204);
				
	}

}
