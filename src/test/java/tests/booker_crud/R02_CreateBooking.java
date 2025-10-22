package tests.booker_crud;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R02_CreateBooking extends BookerBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        Send post request
    Then
        Status cose should be 200
    And
        Response body should be like:
        {
            "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
        }
     */


    public static int bookingId;

    @Test
    void createBookingTest() {

        //Prepare the payload
        JsonNode payload = getJsonNode("booking");

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "booking.firstname", equalTo(payload.get("firstname").asText()),
                        "booking.lastname", equalTo(payload.get("lastname").asText()),
                        "booking.totalprice", equalTo(payload.get("totalprice").asInt()),
                        "booking.depositpaid", equalTo(payload.get("depositpaid").asBoolean()),
                        "booking.bookingdates.checkin", equalTo(payload.get("bookingdates").get("checkin").asText()),
                        "booking.bookingdates.checkout", equalTo(payload.get("bookingdates").get("checkout").asText()),
                        "booking.additionalneeds", equalTo(payload.get("additionalneeds").asText())
                );

        bookingId = response.jsonPath().getInt("bookingid");


    }

}