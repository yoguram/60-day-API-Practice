package ui_bank;

import java.io.File;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LoginToAccount extends BaseRequest {
	@Test
	public void login() {
		File login = new File("testData/UIBankLogin.json");
		response = RestAssured.given()
				.header("Content-Type", "application/json").body(login).post("users/login");
		//response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		if(response.getStatusCode()==200)
		{
			auth = jsonResponse.getString("id");
			userId = jsonResponse.getString("userId");
			
		}
	}

}
