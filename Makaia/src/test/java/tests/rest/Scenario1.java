package tests.rest;

import java.io.File;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class Scenario1 extends RESTAssuredBase{
	public static String listId;
	public static String segmentId;
	boolean idPresent;
	@BeforeTest
	public void setValues()
	{
		testCaseName = "Scenario 1";
		testDescription = "Creat New List,Create new Segment,Get All list and Verify the lis i present,Delete the created new list";
		nodes = "newsLetter - Scenario1";
		authors = "Ram";
		category = "API";
		dataFileName = "AccessToken";
		dataFileType = "JSON";
	}
	@Test(dataProvider="fetchData")
	public void scenario1(File file)
	{
		//Create the List
		//setLogs();
		Response createList =  postWithBodyAsFileAndUrl(file, "lists");
		createList.prettyPrint();
		List<String> list = createList.jsonPath().getList("value.id");
		listId = list.get(0);
		System.out.println(listId);

		// Get All List and verify the existence of new list
		Response getList = get("lists");
		List<String> idList = getList.jsonPath().getList("value.id");

		for (String id : idList) {
			if (id.contains(listId)) {
				idPresent = true;
			}
		}
		if(idPresent==true)
		{
			System.out.println("List is created successfully");
		}
		
		// Create new segment in the list
		
		Response createSegment = postWithJsonAsBody("{\r\n" + "	\"list_id\": \"" + listId + "\",\r\n"
				+ "	\"name\": \"Segment New\",\r\n" + "	\"is_dynamic\": false\r\n" + "}", "groups");
		createSegment.prettyPrint();
		segmentId = list.get(0);
		System.out.println(segmentId);

		// Update Created Segment 
		setLogs();
		Response updateSegment = patchWithJsonAsBody("{\r\n" + "	\"name\": \"update\"\r\n" + "}",
				"groups/" + segmentId + "");

		verifyResponseCode(updateSegment, 200);

		// Delete created New List
		Response deletList = delete("lists/" + listId + "");
		verifyResponseCode(deletList, 204);

		
	}

}
