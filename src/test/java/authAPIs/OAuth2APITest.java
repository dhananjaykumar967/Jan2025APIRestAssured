package authAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OAuth2APITest {
    private String accessToken;

    @BeforeMethod
    public void getAccessToken() {
        RestAssured.baseURI = "https://test.api.amadeus.com";

        Response response = RestAssured.given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", "47Ae5NUW3JK1AzmHO6AGdANAjAcSJ6D0")
                .formParam("client_secret", "KRBtzUdpBgSoL6Kz")
                .when()
                .post("/v1/security/oauth2/token");

        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        accessToken = response.jsonPath().get("access_token");
        System.out.println("Access Token: " + accessToken);

    }

    @Test
    public void getFlightInfo() {
    }
}
