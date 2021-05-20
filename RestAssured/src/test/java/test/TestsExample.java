package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class TestsExample {

	@Test
	public void test_1() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println("status code : " + response.getStatusCode());
		System.out.println("time to execute the test : " + response.getTime());
		System.out.println("getBody : " + response.getBody().asString());
		System.out.println("Status Line : " + response.getStatusLine());
		System.out.println("content type : " + response.getHeader("content-type"));

		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 201);
	}

	@Test
	public void test_2() {
		baseURI = "https://reqres.in/api";
		given().get("/users?page=2").then().statusCode(200).body("data[1].id", equalTo(8)).log().all();
	}

}
