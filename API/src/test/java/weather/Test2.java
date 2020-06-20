package weather;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test2 {
	@Test
	public static void getOauth()
	{
		String clientId = "1000.6YDG41BKDX3OVPLJOU1K4TJ2YFZFHH";
//		String clientSecret = "c18f6b9ed723fa172402d69417e66cc13338260730";
//		RestAssured.baseURI = "https://accounts.zoho.com/oauth/v2/token?";
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("scope ","ZohoSubscriptions.fullaccess.all");
		parameter.put("client_id",clientId);
		parameter.put("response_type","code");
		parameter.put("access_type","online");
		
//		parameter.put("client_secret",clientSecret);
		parameter.put("redirect_uri","https://www.testing.com");
//		parameter.put("state","testing");
//		parameter.put("code","1000.54d38cdb6be7e35809a4457eb8d57516.22971b29a2b6fe0e867203eab00ee25b");
//		parameter.put("grant_type","authorization_code");
//		Response response =  RestAssured.given().params(parameter).post();
//		response.prettyPeek();
	
		RestAssured.baseURI = "https://accounts.zoho.com/oauth/v2/auth?";
		Response response =  RestAssured.given().params(parameter).post();
		response.prettyPeek();
		
	
	
	}

}
