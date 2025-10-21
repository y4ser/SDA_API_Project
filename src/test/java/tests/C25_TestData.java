package tests;

import base_urls.JPHBaseUrl;
import io.restassured.response.Response;
import json_data.JPHData;
import org.testng.annotations.Test;
import pojos.ToDoPojo;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C25_TestData extends JPHBaseUrl {
    /*
        Task:
        1. Send a POST request to the URL: https://jsonplaceholder.typicode.com/todos
        2. Use the following request body:
           {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
           }
        3. Validate the following:
           - The status code is 201.
           - The response body matches:
             {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false,
                 "id": 201
             }
    */

    @Test
    void objectMapperTestMap() {

        //Prepare the payload
        Map payload = ObjectMapperUtils.convertJsonToJava(JPHData.TO_DO_JSON, Map.class);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/todos");
        response.prettyPrint();


        //Do assertion
        Map actualData = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), payload.get("userId"));
        assertEquals(actualData.get("title"), payload.get("title"));
        assertEquals(actualData.get("completed"), payload.get("completed"));

        //OR:
        response
                .then()
                .statusCode(201)
                .body(
                        "userId", equalTo(payload.get("userId")),
                        "title", equalTo(payload.get("title")),
                        "completed", equalTo(payload.get("completed"))
                );
    }

    @Test
    void objectMapperTestPOJO(){

        //Prepare the payload

        ToDoPojo payload = ObjectMapperUtils.convertJsonToJava(JPHData.TO_DO_JSON, ToDoPojo.class);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/todos");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "userId", equalTo(payload.getUserId()),
                        "title", equalTo(payload.getTitle()),
                        "completed", equalTo(payload.getCompleted())
                );
    }

}