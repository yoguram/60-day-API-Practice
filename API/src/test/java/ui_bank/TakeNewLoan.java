package ui_bank;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TakeNewLoan extends BaseRequest{
	@Test
	public void takeLoan()
	{
		File loan = new File("testData/NewLoan.json");
		response = RestAssured.given().header("Content-Type", "application/json").body(loan).post("quotes/newquote");
		JsonPath jsonResponse = response.jsonPath();
		loanId = jsonResponse.getString("quoteid");
		System.out.println("Loan Applied successfully");
		System.out.println("Loand Id is: "+loanId);
		
	}

}
