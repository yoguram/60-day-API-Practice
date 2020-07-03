package tests.rest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class Scenario1_Client extends RESTAssuredBase {
	static String apiKey = "00xbu2IjSEmeokeXg40rszYS-cUyDZWhvZIIc-xx4v";
	static Map<String, String> header = new HashMap<String, String>();
	static String clientID;
	static String clientName = "New Client Updated";
	static String updatedClientName;

	@BeforeTest
	public void setValues() {
		testCaseName = "Scenario 1";
		testDescription = "Create the app,Update the logo and verify the app with new logo";
		nodes = "Okta - Scenario1";
		authors = "Ram";
		category = "API";
		dataFileName = "CreateClient";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData")
	public void scenario1(File file) throws Exception {
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/json");
		header.put("Authorization", "SSWS " + apiKey + "");

		// Create the Client
		Response createClient = postWithHeaderAndJsonAsFile(header, file, "oauth2/v1/clients");
		createClient.prettyPrint();
		clientID = createClient.jsonPath().get("client_id");

		// Verify the created Client

		Response verifyClient = getWithHeader(header, "clients");
		verifyClient.prettyPrint();
		List<String> clientIDList = verifyClient.jsonPath().get("client_id");
		for (String eachClient : clientIDList) {
			if (eachClient.equalsIgnoreCase(clientID)) {
				System.out.println("Created Client is in the List");
				break;
			}

		}

		// Update the client name

		 Response updateClient = putWithHeaderAndBodyParam(header,"{\r\n" + 
		 		"	\"client_id\": \""+clientID+"\",\r\n" + 
		 		"	\"client_name\": \""+clientName+"\",\r\n" + 
		 		"	\"redirect_uris\": [\r\n" + 
		 		"		\"https://httpbin.org/get\"\r\n" + 
		 		"	],\r\n" + 
		 		"	\"response_types\": [\r\n" + 
		 		"		\"code\",\r\n" + 
		 		"		\"token\",\r\n" + 
		 		"		\"id_token\"\r\n" + 
		 		"	],\r\n" + 
		 		"	\"grant_types\": [\r\n" + 
		 		"		\"refresh_token\",\r\n" + 
		 		"		\"authorization_code\",\r\n" + 
		 		"		\"implicit\"\r\n" + 
		 		"	],\r\n" + 
		 		"	\"token_endpoint_auth_method\": \"client_secret_basic\",\r\n" + 
		 		"	\"application_type\": \"web\"\r\n" + 
		 		"}", "oauth2/v1/clients/"+clientID+"");
		 System.out.println(updateClient.getStatusCode());
		 verifyResponseCode(updateClient, 200);
		 

		// Validate the updated client

		Response getClient = getWithHeader(header, "oauth2/v1/clients/" + clientID + "");
		updatedClientName = getClient.jsonPath().get("client_name");
		if (updatedClientName.equalsIgnoreCase(clientName)) {
			System.out.println("Client Name is updated");
		}

		// Delete the client
		Response deleteClient = deleteWithHeaderAndPathParamWithoutRequestBody(header, "oauth2/v1/clients/" + clientID + "");
		System.out.println(deleteClient.getStatusCode());
		verifyResponseCode(deleteClient, 204);

	}

}
