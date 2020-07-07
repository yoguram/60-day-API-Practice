package tests.rest;

import java.io.File;
import java.util.List;
import static org.hamcrest.Matchers.hasItems;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class Amio_Scenario_1 extends RESTAssuredBase {
	static String channelID;

	@BeforeSuite
	public void setValues() {

		testCaseName = "Scenario 1";
		testDescription = "Create, Verify and Delete the channel created for facebook";
		nodes = "amio - Scenario1";
		authors = "Ram";
		category = "API";
		dataFileName = "createFBChannel";
		dataFileType = "JSON";

	}

	@Test(dataProvider = "fetchData")
	public void fbChannel(File file) {

		// Create New FB Channel
		Response createChannel = postWithBodyAsFileAndUrl(file, "/channels");
		createChannel.prettyPrint();
		channelID = createChannel.jsonPath().get("id");
		
		// Verify the channel created

		Response getChannels = get("/channels");
		getChannels.then().assertThat().body("id",hasItems(channelID));
		//verifyContentsWithKey(getChannels, "id", channelID);
		/*
		 * for (String eachValue : channelsList) { if
		 * (eachValue.equalsIgnoreCase(channelID)) {
		 * System.out.println("Channel created Successfully"); } }
		 */

		// Delete the created Channel

		Response deleteChannel = delete("/channels/" + channelID + "");
		verifyResponseCode(deleteChannel, 204);
	}
}
