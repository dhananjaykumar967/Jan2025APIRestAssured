package GETAPITestsWithBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetUserWithQueryParam {

    @Test
    public void getSingleUserTest() {
        RestAssured.baseURI = "https://gorest.co.in";

        given().log().all()
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .queryParam("name", "naveen")
                .queryParam("status", "inactive")
                .when()
                .get("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    public void getUsersTestWithQueryParamUsingHashMap() {
        Map<String, String> userQueryMap = new HashMap<>(); // Interface reference variable for the HashMap
        userQueryMap.put("name", "naveen");
        userQueryMap.put("status", "inactive");
        userQueryMap.put("gender", "female");

        RestAssured.baseURI = "https://gorest.co.in";

        given().log().all()
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .queryParams(userQueryMap)
                .when()
                .get("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

}
