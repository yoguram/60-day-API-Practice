package hotels;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CheckRates extends GetSignature {
	@Test(dependsOnMethods = {"hotels.RoomAvailability.checkAvailability"})
	public void getRates()
	{
		Response response = RestAssured.given().headers(headerMap).log().all().body("{\r\n" + 
						"    \"rooms\": [\r\n" + 
						"        {\r\n" + 
						"            \"rateKey\": \"" +rateKey+ "\"\r\n" + 
						"        }\r\n" + 
						"    ]\r\n" + 
						"}")
				.post("checkrates");
		response.prettyPrint();
	}

}
