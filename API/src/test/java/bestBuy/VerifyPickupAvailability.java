package bestBuy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class VerifyPickupAvailability extends BaseRequest{
	@Test
	public void getPickupAvailabilty()
	{
		RestAssured.baseURI="https://api.bestbuy.com/v1/products/6341280/stores.json?";
		Map<String,String>params = new HashMap<String,String>();
		params.put("postalCode","55423");
		params.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");
		params.put("show","sku,name,salePrice");
		response = RestAssured.given().params(params).get();
		//response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		System.out.println("Store details for IPhone 11 pro are");
		List<String> stores = jsonResponse.getList("stores");
		for(int i=0;i<stores.size();i++)
		{
			System.out.println("*********************");
			System.out.println(jsonResponse.get("stores["+i+"].storeID"));
			System.out.println(jsonResponse.get("stores["+i+"].name"));
		}
	
	}
}
