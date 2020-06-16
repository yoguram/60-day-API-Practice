package bestBuy;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseRequest {
	static String postal = "55423";
	static Response response;
	static String sku;
	@BeforeSuite
	public void baseRequest()
	{		
		RestAssured.baseURI = "https://api.bestbuy.com/v1/?";
		RestAssured.authentication = RestAssured.DEFAULT_AUTH;
		
	}

}
