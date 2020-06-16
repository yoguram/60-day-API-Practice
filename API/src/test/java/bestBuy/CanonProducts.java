package bestBuy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CanonProducts extends BaseRequest {
	@Test
	public void getCanonProducts() {
		Map<String,String>params = new HashMap<String,String>();
		params.put("format","json");
		params.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");
		params.put("show","sku,name,salePrice");
		response = RestAssured.given().params(params).get("products(manufacturer=canon&salePrice>1000&salePrice<1500)");
		//response.prettyPrint();
		JsonPath jsonResponse =  response.jsonPath();
		List<String> canon = jsonResponse.getList("products");
		System.out.println("Canon Products which is more than 1000 and less than 1500 are:");
		for(int i=0;i<canon.size();i++)
		{
			System.out.println("******************");
			System.out.println(jsonResponse.get("products["+i+"].sku"));
			System.out.println(jsonResponse.get("products["+i+"].name"));
			System.out.println(jsonResponse.get("products["+i+"].salePrice"));
		}
		
	}

}
