package tests;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C34_FormData extends ReqresBaseUrl {
    /*
    Given
        https://reqres.in/api/register
    And
          "email":"eve.holt@reqres.in"
          "password": "pistol"
    When
        Send the post request
    Then
        Status code should be 200
    And
        Response body should be like this:
        {
            "id": 4,
            "token": "QpwL5tke4Pnpja7X4"
        }
     */

    @Test
    void formDataTest() {

        //Set the payload
        Map<String, String> params = new HashMap<>();
        params.put("email", "eve.holt@reqres.in");
        params.put("password", "pistol");

        //Send the request
        Response response = given(spec)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .params(params)
                //OR:
//                .formParam("email", "eve.holt@reqres.in")
//                .formParam("password", "pistol")
                .post("/register");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "id", Matchers.notNullValue(),
                        "token", Matchers.notNullValue()
                );
    }
}