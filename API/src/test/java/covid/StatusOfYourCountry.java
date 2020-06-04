package covid;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StatusOfYourCountry {
	@Test
	public void statusOfYourCountry()
	{
		RestAssured.authentication = RestAssured.DEFAULT_AUTH;
		RestAssured.baseURI="https://covid-19.dataflowkit.com/v1/India";
		Response response = RestAssured.get();
		response.prettyPrint();
		 
	}

}
