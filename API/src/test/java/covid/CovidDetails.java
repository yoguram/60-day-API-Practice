package covid;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Test
public class CovidDetails {

	public void getCovidDetails() {

		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
		String newCases, newDeathCases;

		Response response = RestAssured.given().log().all().accept(ContentType.JSON).get();

		// response.prettyPrint();

		JsonPath jsonPath = response.jsonPath();

		List<Map<Object, Object>> list = jsonPath.getList("");
		TreeMap<Object, Object> newCase = new TreeMap<Object, Object>();
		TreeMap<Object, Object> newDeaths = new TreeMap<Object, Object>();

		for (Map<Object, Object> eachValue : list) {

			if (eachValue.containsKey("New Cases_text")
					&& eachValue.get("New Cases_text").toString().replaceAll("\\D", "").length() > 1) {

				newCases = eachValue.get("New Cases_text").toString().replaceAll("\\D", "");

				newCase.put(Integer.parseInt(newCases), eachValue.get("Country_text"));
			}

			if (eachValue.containsKey("New Deaths_text")
					&& eachValue.get("New Deaths_text").toString().replaceAll("\\D", "").length() > 1) {

				newDeathCases = eachValue.get("New Deaths_text").toString().replaceAll("\\D", "");

				newDeaths.put(Integer.parseInt(newDeathCases), eachValue.get("Country_text"));
			}

		}

		// to get the top 5 lowest death cases that has non zero deaths

		int size = newCase.size();
		for (int i = size - 2; i >= size - 6; i--) {

			Object sorted = newCase.keySet().toArray()[i];

			Object country = newCase.values().toArray()[i];

			System.out.println(country + " with New Cases : " + sorted);

			System.out.println();
		}

		System.out.println("**********************************");

		for (int i = 1; i <= 5; i++) {

			Object death = newDeaths.keySet().toArray()[i];

			Object country = newDeaths.values().toArray()[i];

			System.out.println(country + " with Lowest Death Cases : " + death);

			System.out.println();

		}

		if (response.getTime() < 600) {

			System.out.println("Time not exceeded 600MS");
		} else {
			System.out.println("Time is exceeded 600MS");
		}

		if (response.contentType().equalsIgnoreCase("application/json")) {

			System.out.println("Content type is JSON");
		} else {
			System.out.println("Content type is not JSON");
		}

	}
}
