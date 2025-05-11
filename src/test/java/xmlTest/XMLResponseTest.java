package xmlTest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class XMLResponseTest {
    @Test
    public void getResponse() {
        // BaseURL
        // given - query parameters, headers, authorization
        // when - get, post, put, delete
        // then - assertions, extract response, extract headers
        // extract response
        // extract header
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.given()

                .when()
                .get("/public/v2/users.xml")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test
    public void getAllUserTestWithXml_Deserilization() {
        // BaseURL
        // given - query parameters, headers, authorization
        // when - get, post, put, delete
        // then - assertions, extract response, extract headers
        // extract response
        // extract header
        RestAssured.baseURI="XXXXXXXXXXXXXXXXXXXX";
        RestAssured.given()

                .when()
                .get("/public/v2/users.xml")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8");
    }
}
