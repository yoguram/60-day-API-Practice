package weather;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.list.TreeList;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Testing {
@Test
public void getMumbaiReport()
{
	int value=0;
	//URL for api call
	RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/onecall/timemachine?";
	
	//Authentication
	RestAssured.authentication=RestAssured.DEFAULT_AUTH;
	
	//Params
	Map<String, String> param = new HashMap<String, String>();
	param.put("appid","f4e9279040b7e72cdc01a0fdd4d3c27d");
	param.put("lat", "60.99");
	param.put("lon","30.9");
		
	//getting the current time in milliseconds
	Date today = new Date();
	long time = (today.getTime())/1000;
	time = time+86400;
	long date = time;
 	for(int i=1;i<=1;i++)
	{
		date = date-86400;
	//Response of the call
	Response response = RestAssured.given().log().all().params(param).param("dt", date).contentType(ContentType.JSON).get();
	
	//response.prettyPrint();
	
	JsonPath jsonResponse = response.jsonPath();
	//List<Object> weatherDate = jsonResponse.getList("hourly.dt");
	
	List<Integer> weatherTime = jsonResponse.getList("hourly.dt");
	List<Float> rainList = new TreeList<Float>();
	float rain = jsonResponse.get("hourly.rain.1h");
	
	
	}
	//Printing the response
	
	
	//Converting the response to JSON
	
	
	
}
}
