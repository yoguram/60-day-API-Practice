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
		sortMap.putAll(map);
		sortMap = new TreeMap<String, Integer>(Collections.reverseOrder());
		System.out.println(sortMap);
	}

}
