package hotels;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RoomAvailability extends GetSignature {
	
	@Test
	public void checkAvailability() {
		File hotel = new File("testData/Hotel Availability.json");

		
		Response response = RestAssured.given().headers(headerMap).body(hotel).post("hotels");

		response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		List<String> hotels = jsonResponse.getList("hotels.hotels");
		for (int i = 0; i < hotels.size(); i++) {
			hotelName = jsonResponse.get("hotels.hotels[" + i + "].name");
			System.out.println("HoteName: "+hotelName);
			roomMinRate = jsonResponse.get("hotels.hotels[" + i + "].minRate");
			System.out.println("Minimum Rate: "+roomMinRate);
			roomMaxRate = jsonResponse.get("hotels.hotels[" + i + "].minRate");
			System.out.println("Maximum Rate: "+roomMaxRate);
		}
		
		for(int j=1;j<hotels.size();j++)
		{
		   rateType = jsonResponse.get("hotels.hotels[j].rooms[1].rates[0].rateType");
		   if(rateType=="BOOKABLE")
		   rateKey = jsonResponse.get("hotels.hotels[j].rooms[1].rates[0].rateKey");
		    
		
		    }

	}
}
