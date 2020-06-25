package tomtom;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseRequest {
	static String apiKey = "cTrOqogTDiAhU9VXtyPf0GVGeDbC9srG";
	static String projectId = "b912514e-a609-4144-a946-9a67fe2159fc";
	static Response response;
	static String adminKey;
	static JsonPath jsonResponse;
	static String projectName;
	static String fenceName;
	@BeforeSuite
	public void basicRequest()
	{
		RestAssured.baseURI="https://api.tomtom.com/geofencing/1/";
	}

}
