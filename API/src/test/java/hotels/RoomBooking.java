package hotels;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RoomBooking extends GetSignature {

	@Test(dependsOnMethods= {"hotels.RoomAvailability.checkAvailability"})
	public void roomBooking() {


		Response response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				.body("{\r\n" + 
						"	\"holder\": {\r\n" + 
						"		\"name\": \"Ram\",\r\n" + 
						"		\"surname\": \"Chandran\"\r\n" + 
						"	},\r\n" + 
						"	\"rooms\": [{\r\n" + 
						"		\"rateKey\": \"{{"+rateKey+"}}\",\r\n" + 
						"		\"paxes\": [{\r\n" + 
						"			\"roomId\": \"1\",\r\n" + 
						"			\"type\": \"AD\",\r\n" + 
						"			\"name\": \"Test\",\r\n" + 
						"			\"surname\": \"1\"\r\n" + 
						"		}]\r\n" + 
						"	}],\r\n" + 
						"	\"clientReference\": \"IntegrationAgency\",\r\n" + 
						"	\"remark\": \"Booking remarks are to be written here.\"\r\n" + 
						"	\r\n" + 
						"}")
				.post("bookings");


		response.prettyPrint();
		JsonPath bookingResponse = response.jsonPath();

		bookingRef = bookingResponse.get("booking.reference");
		
		System.out.println(" Booking Reference : " +bookingRef );
		response = RestAssured
				.given()
				.headers(headerMap)
				.log().all()
				.get("/bookings/"+ bookingRef);

		response.prettyPrint();

	}

}

