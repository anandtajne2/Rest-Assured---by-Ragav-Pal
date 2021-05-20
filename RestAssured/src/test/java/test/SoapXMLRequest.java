package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class SoapXMLRequest {

	@Test
	public void validateSoapXML() throws IOException {

		File file = new File("./SoapRequestFile/Add.xml");
		if (file.exists()) {
			System.out.println("file present");
		}
		FileInputStream inputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(inputStream, "UTF-8");
		baseURI = "http://www.dneonline.com";

		given().contentType("text/xml").accept(ContentType.XML).body(requestBody).when().post("/calculator.asmx").then()
				.statusCode(200).log().all().and().body("//*:AddResult.text()", equalTo("4"));
	}

}