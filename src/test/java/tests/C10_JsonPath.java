package tests;

import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class C10_JsonPath extends JPHBaseUrl {

    /*
    Given: https://jsonplaceholder.typicode.com/todos
    When: The user sends a GET request to the URL
    Then:
      - The HTTP status code should be 200
      - The response content type should be "application/json"
      - The response body should contain an array of todo objects

    And verify the following JSONPath queries:

      Task 1: Retrieve the entire array of todos
      Task 2: Retrieve all todo titles
      Task 3: Retrieve the first todo item
      Task 4: Retrieve the title of the last todo
      Task 5: Retrieve the 5th todo item
      Task 6: Retrieve all user IDs
      Task 7: Retrieve the completion status of todos at indices 2, 5, and 8
      Task 8: Retrieve the title of the 10th todo
      Task 9: Retrieve the 15th and 20th todo items
*/

    @Test
    void jsonPathTest(){

        Response response = given(spec).get("/todos");
//        response.prettyPrint();

        response.then().statusCode(200).contentType(ContentType.JSON);
        JsonPath jsonPath = response.jsonPath();

//        Task 1: Retrieve the entire array of todos
        List<Object> entireArray = jsonPath.getList("");
        System.out.println("entireArray = " + entireArray);

//        Task 2: Retrieve all todo titles
        List<String> titleList = jsonPath.getList("title");
        System.out.println("titleList = " + titleList);
//        Task 3: Retrieve the first todo item
        Object firstItem =  jsonPath.getList("").getFirst();
        System.out.println("firstItem = " + firstItem);

//        Task 4: Retrieve the title of the last todo
        String lastTitle = jsonPath.getList("title").getLast().toString();
        //Or
        lastTitle = jsonPath.get("title[-1]").toString();

        System.out.println("lastTitle = " + lastTitle);

//        Task 5: Retrieve the 5th todo item
        Object fifthItem = jsonPath.get("[4]").toString();
        System.out.println("fifthItem = " + fifthItem);

//        Task 6: Retrieve all user IDs
        List<Integer> idList = jsonPath.getList("id");
        System.out.println("idList = " + idList);

//        Task 7: Retrieve the completion status of todos at indices 2, 5, and 8
        boolean status2 = jsonPath.getBoolean("[2].completed");
        boolean status5 = jsonPath.getBoolean("[5].completed");
        boolean status8 = jsonPath.getBoolean("[8].completed");
        System.out.println("status2 = " + status2);
        System.out.println("status5 = " + status5);
        System.out.println("status8 = " + status8);

//        Task 8: Retrieve the title of the 10th todo
        String tenTitle = jsonPath.getString("[9].title");
        System.out.println("tenTitle = " + tenTitle);

//        Task 9: Calculate total number of IDs
        int sum = 0;
        for (int each : idList){
            sum += each;
        }
        System.out.println("sum = " + sum);


    }

}
