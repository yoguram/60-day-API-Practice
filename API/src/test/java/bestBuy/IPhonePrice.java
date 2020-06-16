package bestBuy;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class IPhonePrice extends BaseRequest{
	@Test
	public void getIphonePrice()
	{
		Map<String,String>params = new HashMap<String,String>();
		params.put("format","json");
		params.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");
		params.put("show","sku,name,regularPrice,salePrice,manufacturer,upc");
		response = RestAssured.given().params(params).get("products(name='Apple - iPhone 11 Pro Max 512GB - Silver (Unlocked)'&manufacturer=apple&salePrice>1000&color=silver)");
		JsonPath jsonResponse = response.jsonPath();
		System.out.println("Iphone details are");
		String sku = jsonResponse.get("products.sku");
		System.out.println(sku);
		System.out.println(jsonResponse.get("products.name"));
		System.out.println(jsonResponse.get("products.regularPrice"));
		System.out.println(jsonResponse.get("products.salePrice"));
		System.out.println(jsonResponse.get("products.manufacturer"));
		System.out.println(jsonResponse.get("products.upc"));
		
	}

}
