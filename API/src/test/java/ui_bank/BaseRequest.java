package ui_bank;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseRequest {
	static String auth;
	static String userId;
	static Response response;
	static String loanId;
	@BeforeSuite
	public void baseRequest()
	{
		RestAssured.baseURI = "https://uibank-api.azurewebsites.net/api/";
		
	}

}
