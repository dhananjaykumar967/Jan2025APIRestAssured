package GETAPITestsWithBDD;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class GetUserPostWithPathParam {
    @DataProvider
    public Object[][] getUserData() {
        return new Object[][]{
                {7820539, "Vobis absconditus libero qui aequitas."},
                {7820533, "Solum et vulgivagus angustus supra cribro decet."},
                {7820534, "Damno animus vitiosus vado copia amissio coniuratio."}
        };
    }

    @Test(dataProvider = "getUserData")
    public void getUserPostWithPathParam(int userId, String text) {
        RestAssured.baseURI = "https://gorest.co.in";

        given().log().all()
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .pathParam("userId", userId)
                .when()
                .get("/public/v2/users/{userId}/posts")
                .then().log().all()
                .statusCode(200)
                .and()
                .body("title", hasItem(text));
    }

    @Test
    public void getUserWithPathParamUsingHashMapTest() {
        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("firstpath", "api");
        pathParams.put("secondpath", "users");

        RestAssured.baseURI = "https://reqres.in/"; // No need any Authorization

        given().log().all()
                .pathParams(pathParams)
                .when()
                .get("/{firstpath}/{secondpath}")
                .then().log().all()
                .statusCode(200);
    }
}

