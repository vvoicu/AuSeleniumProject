package ecs.tools.connectors;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConnector {

	public RequestSpecification request = new RequestSpecBuilder().build();
	public static Response response;

	public static void main(String[] args) {
		RestAssuredConnector rac = new RestAssuredConnector();

		rac.postRequest("https://www.google.com/", new Headers(), "");

		System.out.println(response.asString());
	}

	public void postRequest(String uri, Headers headers, String body) {
		request = given().baseUri(uri).headers(headers).body(body);
		response = request.when().post();
	}

	public void postRequest(String uri, String body) {
		request = given().baseUri(uri).body(body);
		response = request.when().post();
	}

	public void postRequest(String uri, Headers headers) {
		request = given().baseUri(uri).headers(headers);
		response = request.when().get();
	}

}
