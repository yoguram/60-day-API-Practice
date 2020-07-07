package tests.rest;

import java.io.File;
import java.util.List;
import static org.hamcrest.Matchers.hasItems;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class Amio_Scenario_2 extends RESTAssuredBase {
	static String fileMessageID;
	static String messageID;

	@BeforeTest
	public void setValues() {
		testCaseName = "Scenario 1";
		testDescription = "Create, Verify and Delete the channel created for facebook";
		nodes = "amio - Scenario1";
		authors = "Ram";
		category = "API";
		dataFileName = "PictureMessage";
		dataFileType = "JSON";

	}

	@Test(dataProvider = "fetchData")
	public void viberBot(File file) {

		// Send a picture message
		Response pictureMessage = postWithBodyAsFileAndUrl(file, "/messages");
		pictureMessage.prettyPrint();
		fileMessageID = pictureMessage.jsonPath().get("id");

		// Send a text message
		Response textMessage = postWithJsonAsBody("{\r\n" + "  \"channel\": {\r\n"
				+ "    \"id\": \"6686165288008567142\"\r\n" + "  },\r\n" + "  \"contact\": {\r\n"
				+ "    \"id\": \"6686165406120168208\"\r\n" + "  },\r\n" + "  \"content\": {\r\n"
				+ "    \"type\": \"text\",\r\n" + "    \"payload\": \"Hello world!\"\r\n" + "  }\r\n" + "}",
				"/messages");

		textMessage.prettyPrint();
		messageID = textMessage.jsonPath().get("id");

		// Verify the messages sent

		Response verifyMessages = get("/channels/6686165288008567142/contacts/6686165406120168208/messages");
		verifyMessages.prettyPrint();
		verifyMessages.then().assertThat().body("id",hasItems(messageID,fileMessageID ));
		//verifyContentsWithKey(verifyMessages, "id", messageID);
		//verifyContentsWithKey(verifyMessages, "id", fileMessageID);
		/*
		 * List<String> messages = verifyMessages.jsonPath().get("id"); for (String
		 * eachMessage : messages) { if (eachMessage.equalsIgnoreCase(fileMessageID)) {
		 * System.out.println("Picture Message Sent Successfully"); } else if
		 * (eachMessage.equalsIgnoreCase(messageID)) {
		 * System.out.println("Text message Sent Successfully"); } }
		 */

	}
}
