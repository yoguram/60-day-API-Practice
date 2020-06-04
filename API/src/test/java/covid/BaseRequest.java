package covid;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseRequest {
	static Response response;
	static JsonPath jsonResponse;
	
	
	@BeforeSuite
public void baseRequest()
{
	RestAssured.baseURI="https://covid-19.dataflowkit.com/v1";
	RestAssured.authentication = RestAssured.DEFAULT_AUTH;
}
}
