package authAPIs;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class AuthAPITest {
    //basic token
    //digest
    //api key
    //Oauth1
    //Oauth2
    //JWT
    //Bearer Token

    @Test
    public void basicAuthAPITest() {
        RestAssured.baseURI = "https://the-internet.herokuapp.com";
        RestAssured.given().log().all()
                .auth()
                .basic("admin", "admin")
                .when()
                .get("/basic_auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void digestAuthAPITest() {
        RestAssured.baseURI = "https://postman-echo.com";
        RestAssured.given().log().all()
                .auth()
                .digest("postman", "password")
                .when()
                .get("/digest-auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void preemptiveAuthAPITest() {
        RestAssured.baseURI = "https://the-internet.herokuapp.com";
        RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .get("/basic_auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
