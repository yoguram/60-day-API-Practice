package tests.rest;

import static org.hamcrest.Matchers.hasItems;
import java.io.File;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class FreshBooks_Estimate extends RESTAssuredBase {
	static String accountId;
	static String customerId;
	static String estimateId;
	static String address = "New Address";

	@BeforeTest
	public void setValues() {

		testCaseName = "Scenario 2";
		testDescription = "Create, Verify and update the Estimate";
		nodes = "FreshBooks - Scenario2";
		authors = "Ram";
		category = "API";
		//dataFileName = "CreateTimeEntry";
		//dataFileType = "JSON";
	}

@Test//(dataProvider="fetchData")
	public void createEstimate()
	{
			// Get the account id and business id
			Response getDetails = get("auth/api/v1/users/me");
			accountId = getDetails.jsonPath().getString("response.roles[0].accountid");
			
			//Get CustomerID
			Response getClientID = get("accounting/account/"+accountId+"/users/clients");
			customerId = getClientID.jsonPath().getString("response.result.clients[0].id");
			
			// Create an Estimate with single line
			Response createEstimate = postWithJsonAsBody("{\r\n" + 
					"  \"estimate\": {\r\n" + 
					"    \"email\": \"api.freshbooks@freshbooks.com\",\r\n" + 
					"    \"customerid\": "+customerId+",\r\n" + 
					"    \"create_date\": \"2019-04-20\",\r\n" + 
					"    \"presentation\": {\r\n" + 
					"    	\"theme_primary_color\": \"#4f697a\",\r\n" + 
					"    	\"image_logo_src\": \"/service/uploads/images/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50IjoyMTAxMTkzLCJvcmlnaW5hbF9maWxlbmFtZSI6IkZyZXNoQm9va3MtTG9nbyAoMikucG5nIiwiYnVja2V0IjoidXBsb2FkcyIsImZpbGVuYW1lIjoidXBsb2FkLWEyY2QxMmFmZTc0MWQ1MGMwOGMyNmUyNDRhMmQzOWJkZTE2ZDY5OGMiLCJsZW5ndGgiOjg4MjQsImtleSI6Iidkb2NzLSctMjEwMTE5My91cGxvYWQtYTJjZDEyYWZlNzQxZDUwYzA4YzI2ZTI0NGEyZDM5YmRlMTZkNjk4YyJ9.vskvajegEwrVGx7v9qUZEmxWYvlwRjYqCUCKW0i7nds\"\r\n" + 
					"    },\r\n" + 
					"    \"lines\":  {\r\n" + 
					"        \"type\": 0,\r\n" + 
					"        \"description\": \"\",\r\n" + 
					"        \"taxName1\": \"\",\r\n" + 
					"        \"taxAmount1\": 0,\r\n" + 
					"        \"name\": \"Paperwork\",\r\n" + 
					"        \"qty\": 1,\r\n" + 
					"        \"taxName2\": \"\",\r\n" + 
					"        \"taxAmount2\": 0,\r\n" + 
					"        \"unit_cost\": {\r\n" + 
					"            \"amount\": \"5000.00\",\r\n" + 
					"            \"code\": \"USD\"\r\n" + 
					"        }\r\n" + 
					"      }\r\n" + 
					"      }\r\n" + 
					"}", "accounting/account/"+accountId+"/estimates/estimates");
			estimateId = createEstimate.jsonPath().getString("response.result.estimate.id");
			
			//Update the Newly Created Estimate
			Response updateEstimate = putWithBodyParam("{\r\n" + 
					"  \"estimate\": {\r\n" + 
					"     \"customerid\": "+customerId+",\r\n" + 
					"     \"address\": \""+address+"\"\r\n" + 
					"  }\r\n" + 
					"}", "accounting/account/"+accountId+"/estimates/estimates/"+estimateId+"");
			verifyResponseCode(updateEstimate, 200);
			
			//Get the Estimate
			Response getEstimate = get("accounting/account/"+accountId+"/estimates/estimates/"+estimateId+"");
			if(getEstimate.jsonPath().getString("response.result.estimate.address").equalsIgnoreCase(address))
				System.out.println("Estimate is updated");
			
			
	}
}
