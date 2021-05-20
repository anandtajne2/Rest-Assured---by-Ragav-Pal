package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetAndPostExamples {

	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";

		given().get("/users?page=2").then().statusCode(200).body("data[4].first_name", equalTo("George"))
				.body("data.first_name", hasItems("George", "Rachel"));
	}

	@Test
	public void testPost() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Anand");
		jsonObject.put("Job", "Automation Tester");

		baseURI = "https://reqres.in/api";
		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonObject.toJSONString()).when().post("/users").then().statusCode(201).log().all();
	}

}