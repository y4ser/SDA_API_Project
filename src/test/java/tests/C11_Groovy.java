package tests;

import base_urls.BookStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C11_Groovy extends BookStoreBaseUrl {

    /*
    Given: https://bookstore.demoqa.com/BookStore/v1/Books
    When: The user sends a GET request to the URL
    Then:
      - The status code should be 200

    And perform the following tasks:

      Task 1: Find the first book that has more than 400 pages
      Task 2: Find all books that have more than 400 pages
      Task 3: Find the author of the book titled "Git Pocket Guide"
      Task 4: Create a list containing only the title and pages of all books
      Task 5: Find the book with the most pages and the one with the fewest pages
      Task 6: Sort all books by page count in descending order (highest to lowest)
      Task 7: Count the total number of books
      Task 8: Check if any book title contains the word "JavaScript"
      Task 9: Check if any book title starts with the word "Learning"
      Task 10: Find books that have more than 250 pages and are published by "O'Reilly Media"
*/

    @Test
    void groovyTest() {

        Response response = given(spec).get("BookStore/v1/Books");
//        response.prettyPrint();

        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();

//        Task 1: Find the first book that has more than 400 pages
        String firstBookThatMore400 = jsonPath.getString("books.find{it.pages > 400}.title");
        System.out.println("firstBookThatMore400 = " + firstBookThatMore400);

//        Task 2: Find all books that have more than 400 pages
        Object allBooksThatMore400 = jsonPath.getList("books.findAll{it.pages > 400}.title");
        System.out.println("allBooksThatMore400 = " + allBooksThatMore400);

//        Task 3: Find the author of the book titled "Git Pocket Guide"
        String author = jsonPath.getString("books.find{it.title == 'Git Pocket Guide'}.author");
        System.out.println("author = " + author);


//        Task 4: Create a list containing only the title and pages of all books
        Object titleAndPage =  jsonPath.getList("books.collect{[title: it.title, pages: it.pages]}");
        System.out.println("titleAndPage = " + titleAndPage);

//        Task 5: Find the book with the most pages and the one with the fewest pages
        System.out.println("Book Title that has Most Pages: " + jsonPath.getString("books.max{it.pages}.title"));
        System.out.println("Book Title that has Fewest Pages: " + jsonPath.getString("books.min{it.pages}.title"));

//        Task 6: Sort all books by page count in descending order (highest to lowest)
        Object sortBookByPages = jsonPath.getList("books.sort{it.pages}.title");
        System.out.println("sortBookByPages = " + sortBookByPages);

//        Task 7: Count the total number of books
        int countBooks = jsonPath.getInt("books.size()");
        System.out.println("countBooks = " + countBooks);

//        Task 8: Check if any book title contains the word "JavaScript"
        boolean titleContains = jsonPath.getBoolean("books.any{it.title.contains('JavaScript')}");
        System.out.println("titleContainsJavaScript = " + titleContains);

//        Task 9: Check if any book title starts with the word "Learning"
        boolean titleStartWith =  jsonPath.getBoolean("books.any{it.title.startsWith('Learning')}");
        System.out.println("titleStartWithLearning = " + titleStartWith);

//        Task 10: Find books that have more than 250 pages AND are published by "O'Reilly Media"
        System.out.println(jsonPath.getList("books.findAll{it.pages>250 && it.publisher==\"O'Reilly Media\"}.title"));

    }


}
