package paypal;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateProduct {
	static String id;
	@DataProvider(name="files")
	public String[] getFiles()
	{
		String file[] = new String[3];
		file[0]="testData/1.json";
		file[1]="testData/2.json";
		file[2]="testData/3.json";
		return file;
	}
	@Test(dataProvider="files")
	public void createProduct(String filename)
	{
		File file = new File(filename);
		RestAssured.baseURI="https://api.sandbox.paypal.com";
		RestAssured.authentication = RestAssured.oauth2("A21AAFPHIThpe8EFNKhjp-f9s7G3uvw4KJCVBqA8zUC1WancUaQM4kuqz7ZIiO01EAeOo0_cpzhgb4NFlWh6MypyKtp_4jlOw");
		Response response = RestAssured.given().contentType(ContentType.JSON).body(file).post("/v1/catalogs/products");
		//response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		String id = jsonResponse.getString("id");
		System.out.println(id);
		Response getResponse = RestAssured.given().get("/v1/catalogs/products/"+id);
		JsonPath resp = getResponse.jsonPath();
		resp.prettyPrint();
		System.out.println(resp.getString("category"));
	}
		
	}
			

