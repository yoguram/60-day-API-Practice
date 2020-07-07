package tests.rest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class UpdateApp {
	
	static String id = "0oaj9e6v7OU1XEm1V4x6";
	@Test
	public void updateApp()
	{
		RestAssured.baseURI="https://dev-728215-admin.okta.com";
		File app = new File("./data/ss.png");
		Response response =  RestAssured
				.given()
				.header("Authorization", "SSWS00TLzU6N8V0T7oIdHOmWi3dbXOk6CW18GN-80Sx3gT")
				.header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryllizY2O8QqNCPLdi")
				.multiPart("_xsrfToken", "879374d803f02b6487a54d7ee097de440afae400ce33ebd64edee2bee36b41a1")
				.multiPart("linkId","aln177a159h7Zf52X0g8")
				.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
				.header("Origin","https://dev-728215-admin.okta.com")
				.multiPart("file",app)
				.log()
				.all()
				.post("/admin/app/oidc_client/instance/"+id+"/edit-link")
				.then()
				.log()
				.all().extract().response();
		System.out.println(response.getStatusCode());
		
	}

}
