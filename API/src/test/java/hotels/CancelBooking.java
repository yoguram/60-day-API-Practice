package hotels;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CancelBooking extends GetSignature{
	@Test(dependsOnMethods = {"hotels.RoomAvailability.checkAvailability"})
public void cancelBooking()
{
	Response response = RestAssured
			.given()
			.headers(headerMap)
			.log().all()
			.param("cancellationFlag", "CANCELLATION")
			.delete("/bookings/"+ bookingRef);

	response.prettyPrint();

}
}
