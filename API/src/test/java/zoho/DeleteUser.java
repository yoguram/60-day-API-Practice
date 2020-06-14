package zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteUser extends BaseRequest {
	@Test
	public void deleteUser() {
		response = RestAssured.given().headers(header).delete("/2219376000000071033");
		if (response.getStatusCode() == 200)
			System.out.println("User is deleted successfully");
		else
			System.out.println("Some issues on deleting the given user");
	}
		
	}
