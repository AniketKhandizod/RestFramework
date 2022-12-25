package com.employeeapi.utilities;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SDETYT {

	public static void wetherApi() {
		RestAssured.baseURI = "https://reqres.in"; // base URL
		RequestSpecification httpRequest = RestAssured.given(); // Request Object
		Response response = httpRequest.request(Method.GET, "/api/users?page=2");

		String responseBody = response.getBody().asString();

		System.out.println("Json Attribute   :" + new JsonPath(responseBody).get("data.size()"));
		System.out.println("Json Attribute   :" + new JsonPath(responseBody).get("data[0].id"));
		System.out.println("Json Attribute   :" + new JsonPath(responseBody).get("support.url"));
		System.out.println("Response Body is :" + response.getBody().asString());
		System.out.println("Response Body is :" + response.getBody().asString());
		System.out.println("Status code is   :" + response.getStatusCode());
	}

	public static void PostApi() {

		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();

		requestParams.put("name", "morpheus");
		requestParams.put("job", "leader");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());

		Response response = httpRequest.request(Method.POST, "/api/users");

		System.out.println("Response Body is :" + response.getBody().asString());
		System.out.println("Status code is : " + response.getStatusCode());
		System.out.println("success Code in Json : " + response.jsonPath().get("createdAt"));
	}

	public void googleApi() {

		RestAssured.baseURI = "https://www.google.co.in";
		RequestSpecification http = RestAssured.given();
		Response response = http.request(Method.GET,
				"/maps/search/Pharmacies/@19.5584537,74.2045742,15z/data=!3m1!4b1!4m7!2m6!3m5!2sSangamner!3s0x3bdd01df1dff34d5:0x406ebb6cd7d4c5a4!4m2!1d74.213329!2d19.5584539?hl=en");

//		System.out.println("Response Body is:" +response.getBody().asString());
		System.out.println("Content Type is:" + response.header("Content-Type"));// capture details of Content-Type
																					// header
		System.out.println("Content Encoding is:" + response.header("Content-Encoding"));// capture details of
																							// Content-Encoding header
		System.out.println("Content date is:" + response.header("Date"));// capture details of Date header
	}

	public void googleApiAllHeaders() {

		RestAssured.baseURI = "https://httpbin.org";
		RequestSpecification http = RestAssured.given();
		Response response = http.request(Method.GET, "/headers");

		Headers allheadr = response.headers();
		for (Header header : allheadr) {
			System.out.println(header.getName() + " -> " + header.getValue());
		}
	}

	public void postmethod() {

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject jo = new JSONObject();
		jo.put("userId", "1");
		jo.put("id", "102");
		jo.put("title", "ANiket");
		jo.put("body", "Aniket");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jo.toJSONString());

		Response response = httpRequest.request(Method.POST, "/posts");

		System.out.println("Response Body is :" + response.getBody().asString());

		Response response2 = httpRequest.request(Method.GET, "/posts");
		System.out.println("2nd reps " + response2.getBody().asString());

	}

	public void authorizastion() {

		RestAssured.baseURI = "https://httpbin.org";
		PreemptiveBasicAuthScheme au = new PreemptiveBasicAuthScheme();
		au.setUserName(null);
		au.setPassword(null);
		RestAssured.authentication = au;

		RequestSpecification http = RestAssured.given();
		Response response = http.request(Method.GET, "/headers");

		Headers allheadr = response.headers();
		for (Header header : allheadr) {
			System.out.println(header.getName() + " -> " + header.getValue());
		}
	}
}
