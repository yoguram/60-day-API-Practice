package covid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HighNewCases extends BaseRequest{
	@Test
	public void highCases()
	{
		Response response = RestAssured.get();
		//response.prettyPrint();
		jsonResponse = response.jsonPath();
		List<String> allItems = jsonResponse.get("Country_text");
		int totalCountries = allItems.size();
		String newCaseValue;
		String country;
		String todayCases;
		int value;
		Map<String,Integer> map= new HashMap<String,Integer>();
		for(int i=0;i<totalCountries-1;i++)
		{
			newCaseValue = jsonResponse.get("'New Cases_text'["+i+"]");
			country = jsonResponse.getString("'Country_text'["+i+"]");
			todayCases = newCaseValue.replaceAll("\\D", "");
			if(newCaseValue=="")
			newCaseValue.replace("", "0");
			value = Integer.parseInt(newCaseValue);
			map.put("country",value);
		}
	}

}
