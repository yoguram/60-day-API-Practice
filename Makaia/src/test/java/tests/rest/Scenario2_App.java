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

public class Scenario2_App extends RESTAssuredBase {
	static String apiKey = "00xbu2IjSEmeokeXg40rszYS-cUyDZWhvZIIc-xx4v";
	static Map<String, String> header = new HashMap<String, String>();
	static String appID;
	@BeforeTest
	public void setValues() {
		testCaseName = "Scenario 2";
		testDescription = "Create the app,Update the logo and verify the app with new logo";
		nodes = "Okta - Scenario2";
		authors = "Ram";
		category = "API";
		dataFileName = "CreateApp";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData")
	public void scenario1(File file) throws Exception {
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/json");
		header.put("Authorization", "SSWS " + apiKey + "");
		Response createApp = postWithHeaderAndJsonAsFile(header, file, "api/v1/apps");
		createApp.prettyPrint();
		appID = createApp.jsonPath().get("id");
	}

}
