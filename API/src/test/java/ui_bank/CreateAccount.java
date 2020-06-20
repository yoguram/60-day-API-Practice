package ui_bank;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateAccount extends BaseRequest {
	@Test(priority = 1, dependsOnMethods = { "ui_bank.LoginToAccount.login" })
	public void createAccount() {
		File accountCreate = new File("testData/AccountCreate.json");
		response = RestAssured.given().header("Content-Type", "application/json").header("Accept", "application/json")
				.header("Authorization", auth).body(accountCreate).post("accounts");
		JsonPath jsonresponse = response.jsonPath();
		System.out.println("Account details are:");
		System.out.println("Account number:"+jsonresponse.getString("accountNumber"));
		System.out.println("Account username:"+jsonresponse.getString("friendlyName"));
		System.out.println("Account Balance:"+jsonresponse.getString("balance"));
		System.out.println("Account Type:"+jsonresponse.getString("type"));
		
	}
}
