package ui_bank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetLoanStatus extends BaseRequest{
	@Test(dependsOnMethods = {"ui_bank.TakeNewLoan.takeLoan"})
	public void getLoanStatus()
	{
		response = RestAssured.given().header("Content-Type", "application/json").get("quotes/"+loanId+"");
		JsonPath jsonResponse =  response.jsonPath();
		System.out.println("Loan Details are:");
		System.out.println("Loan Amount: "+jsonResponse.getString("amount"));
		System.out.println("Loan Interest Rate: "+jsonResponse.getString("rate"));
		System.out.println("Loan term in years: "+jsonResponse.getString("term"));
		System.out.println("Person Income: "+jsonResponse.getString("income"));
	}

}
