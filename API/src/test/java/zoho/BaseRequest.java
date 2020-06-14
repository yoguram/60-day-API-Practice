package zoho;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseRequest {

	// Organization id
	static String orgId = "717023702";
	static Response response;
	static Map<String, String> header = new HashMap<String, String>();
	static String  createdId;
	@BeforeSuite
	public void baseClass() {

		header.put("oauthscope", "ZohoSubscriptions.fullaccess.all");
		header.put("X-com-zoho-subscriptions-organizationid", orgId);
		header.put("Content-Type", "application/json;charset=UTF-8");
		// Base URL
		RestAssured.baseURI = "https://subscriptions.zoho.com/api/v1/customers";

		// Authentication
		RestAssured.authentication = RestAssured
				.oauth2("1000.637bc0428748ffdf19237e363578fcb2.50c559e22b82cac64b7623857b0ff20c");
	}
}
