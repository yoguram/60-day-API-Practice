package zoho;

import java.io.File;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateCustomer extends BaseRequest {

	@Test
	public void createCustomer() {
		File create = new File("testData/Zoho-CreateCustomer.json");
		response = RestAssured.given().headers(header).body(create).post();
		// response.prettyPrint();

		JsonPath jsonResponse = response.jsonPath();

		String createdName = jsonResponse.get("customer.display_name");

		createdId = jsonResponse.get("customer.customer_id");
		String phone = jsonResponse.get("customer.phone");

		System.out.println("Newly created customer is:" + createdName);
		System.out.println("Newly created customerId is:" + createdId);
		System.out.println("Phone numnber is :" + phone);
		if (response.getStatusCode() == 201)
			System.out.println("User is created successfully");
		else
			System.out.println("Some issues on creating an user");
	}

	@Test
	public void updateUserDetails() {
		File update = new File("testData/Zoho-updateDetails.json");

		response = RestAssured.given().headers(header).body(update).put("/" + createdId + "");
		response.prettyPrint();

		JsonPath jsonResponse = response.jsonPath();

		String changedPhone = jsonResponse.get("customer.phone");
		System.out.println("Updated city is :" + changedPhone);
		if (response.getStatusCode() == 200)
			System.out.println("User details are updated successfully");
		else
			System.out.println("Some issues on updating the given user");
	}

}
