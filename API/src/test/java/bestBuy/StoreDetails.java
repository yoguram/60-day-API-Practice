package bestBuy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StoreDetails extends BaseRequest{
	@Test
	public void getStoreDetails()
	{
		response = RestAssured.given().param("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ").param("format","json").get("stores(postalCode="+postal+")");
		//response.prettyPrint();
		JsonPath jsonResponse =  response.jsonPath();
		List<String> stores = jsonResponse.getList("stores");
		System.out.println("Store Details are:");
		for(int i=0;i<stores.size();i++)
		{
		System.out.println(jsonResponse.get("stores.storeId["+i+"]"));
		System.out.println(jsonResponse.get("stores.name["+i+"]"));
		System.out.println(jsonResponse.get("stores.city["+i+"]"));
		System.out.println("*********************************");
		}
		
	}

}
