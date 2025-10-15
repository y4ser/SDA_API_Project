package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class C01_SendRequestGetResponse {

    public static void main(String[] args) {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/101");//Send get request to

        response.prettyPrint();//Prints the response body in pretty format

        int statusCode =  response.statusCode();//Gets the status code
        System.out.println("statusCode = " + statusCode);

        System.out.println("response.statusLine() = " + response.statusLine());//Gets the statusLine

        System.out.println("response.getContentType() = " + response.getContentType());//Gets the getContentType

        System.out.println("response.time() = " + response.time());//Gets the response time in millisecond

        System.out.println("response.header(\"Server\") = " + response.header("Server"));//Gets the Server header

        System.out.println("All Headers:");
        System.out.println(response.headers());//Gets all headers

    }

}
