package covid;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HighNewCases extends BaseRequest {
	@Test
	public void highCases() throws InterruptedException {
		Response response = RestAssured.get();
		// response.prettyPrint();
		jsonResponse = response.jsonPath();
		List<String> allItems = jsonResponse.get("Country_text");
		int totalCountries = allItems.size();
		String newCaseValue;
		String country;
		String newCase;
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> sortMap = new HashMap<String, Integer>();
		for (int i = 0; i < totalCountries - 1; i++) {
			country = jsonResponse.getString("'Country_text'[" + i + "]");
			newCaseValue = jsonResponse.get("'New Cases_text'[" + i + "]");
			if (newCaseValue == "") {
				newCase = newCaseValue.replaceAll("", "0");
				map.put(country, Integer.parseInt(newCase));
			} else {
				newCase = newCaseValue.replaceAll("\\D", "");
				map.put(country, Integer.parseInt(newCase));

			}
		}
		Set < Entry < String, Integer >> entries = map.entrySet();

		Comparator < Entry < String, Integer >> valueComparator = new Comparator < Entry < String, Integer >> () 
		{
			public int compare(Entry < String, Integer > e1, Entry < String, Integer > e2) 
			{
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				return v2.compareTo(v1);
        	}
		};
	}

}
