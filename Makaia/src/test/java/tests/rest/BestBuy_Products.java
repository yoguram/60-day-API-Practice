package tests.rest;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.collections4.list.TreeList;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class BestBuy_Products extends RESTAssuredBase {
	static String name;
	static String salePrice;
	static String manufacturer;
	static String url = "https://api.bestbuy.com/v1/products(search=Flat&search=Screen&categoryPath.name='TV & Home Theater')";
	static int count;
	static float shippingCost;
	static Map<String, String> param = new HashedMap<String, String>();

	@BeforeTest
	public void setValues() {
		testCaseName = "Scenario 1";
		testDescription = "Products Scenarios";
		nodes = "Bestbuy - Scenario1";
		authors = "Ram";
		category = "API";
		// dataFileName = "createFBChannel";
		// dataFileType = "JSON";
		param.put("format", "json");
		param.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
		param.put("pageSize", "100");
		param.put("show", "name,regularPrice,salePrice,manufacturer");
	}

	@Test
	public void getFlatScreenTV() {

		///// Searching for the Flat Screen TV /////////////

		Map<String, String> flatTV = new TreeMap<String, String>();
		Response sortFlatTV = getWithParam(param, url);
		int pageSize = sortFlatTV.jsonPath().getInt("totalPages");
		List<String> product = sortFlatTV.jsonPath().getList("products");
		for (int i = 1; i < pageSize; i++) {
			count = i;
			Response nextPage = getWithParam("page", count, param, url);
			for (int j = 0; j < product.size(); j++) {
				name = nextPage.jsonPath().getString("products[" + j + "].name");
				salePrice = nextPage.jsonPath().getString("products[" + j + "].salePrice");
				if (name.contains("Flat-Screen")) {

					flatTV.put(name, salePrice);
				}
			}
		}
		for (Entry<String, String> entry : flatTV.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

	}

	@Test
	public void getFlatPanelTV() {
		Map<String, String> flatPanelTV = new TreeMap<String, String>();
		Response sortFlatPanelTV = getWithParam(param, url);
		int pageSize = sortFlatPanelTV.jsonPath().getInt("totalPages");
		List<String> product = sortFlatPanelTV.jsonPath().getList("products");
		for (int i = 1; i < pageSize; i++) {
			count = i;
			Response nextPage = getWithParam("page", count, param, url);

			for (int j = 0; j < product.size(); j++) {
				name = nextPage.jsonPath().getString("products[" + j + "].name");
				manufacturer = nextPage.jsonPath().getString("products[" + j + "].manufacturer");
				if (name.contains("Flat-Panel")) {
					flatPanelTV.put(name, manufacturer);
				}
			}
		}
		for (Entry<String, String> entry : flatPanelTV.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

	}

	@Test
	public void getAllProductDetails() {
		param.put("show", "name,regularPrice,salePrice,offers.startDate,offers.endDate");
		Response allproducts = getWithParam(param, url);
		Map<String, String> allProducts = new TreeMap<String, String>();
		int pageSize = allproducts.jsonPath().getInt("totalPages");
		List<String> product = allproducts.jsonPath().getList("products");
		for (int i = 1; i < pageSize; i++) {
			count = i;
			Response nextPage = getWithParam("page", count, param, url);
			List<Map<String, String>> list = nextPage.jsonPath().getList("products");
			for (Map<String, String> each : list) {
				System.out.println(each);
			}
		}
	}

	@Test
	public void sortProductsShippingCost() {
		param.put("show", "name,shippingCost");
		Response sortShippingCost = getWithParam(param, "https://api.bestbuy.com/v1/products");
		Map<String, Float> productDetails = new TreeMap<String, Float>();
		int pageSize = sortShippingCost.jsonPath().getInt("totalPages");
		List<String> product = sortShippingCost.jsonPath().getList("products");
		for (int j = 0; j < product.size(); j++) {
			name = sortShippingCost.jsonPath().getString("products[" + j + "].name");
			String shipping = sortShippingCost.jsonPath().getString("products[" + j + "].shippingCost");
			if (shipping != "")
				shippingCost = Float.parseFloat(shipping);
			else
				shippingCost = (float) 0.0;
			productDetails.put(name, shippingCost);
		}

		for (Entry<String, Float> entry : productDetails.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

	@Test
	public void getTotalCategories() {
		param.put("show", "name,id");
		Response getCategories = getWithParam(param, "https://api.bestbuy.com/v1/categories");
		List<String> category = getCategories.jsonPath().getList("categories");
		List<String> categoryName = new TreeList<String>();
		for (int i = 0; i < category.size(); i++) {
			categoryName.add(getCategories.jsonPath().getString("categories[" + i + "].name"));
		}
		System.out.println("Categories Count:" + category.size());
		System.out.println("Categories Names are:");
		for (String each : categoryName) {
			System.out.println(each);
		}
	}

	
	@Test
	public void getServices()
	{
		Map<String,String>params = new HashedMap<String, String>();
		params.put("format", "json");
		params.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
		Response getServices = getWithParam(params, "https://api.bestbuy.com/v1/stores(name=ATTLEBORO MA&postalCode=02760)");
		List<String> services = getServices.jsonPath().getList("stores.services");
		System.out.println(services.size());
		List<String> service = new TreeList<String>();
		for(int i=0;i<services.size();i++)
		{
			service.add(getServices.jsonPath().getString("stores.services["+i+"].service"));
			System.out.println(service);
		}
		System.out.println("Services are:");
		for (String each : service) {
			System.out.println(each);
		}
		
		List<String> hours = getServices.jsonPath().getList("stores.hoursAmPm");
		System.out.println(hours.get(0));
	}
}
