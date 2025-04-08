package GETAPITestsWithBDD;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GoRestAPITest {
    @Test
    public void getSingleUser() {
        RestAssured.baseURI = "https://gorest.co.in";

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .when()
                .get("/public/v2/users/7819756");

        System.out.println("status code:" + response.statusCode());
        System.out.println("status code:" + response.statusLine());
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200, "status code is not 200");
        Assert.assertTrue(response.statusLine().contains("200 OK"));

        JsonPath js = response.jsonPath();
        int userID = js.getInt("id");
        System.out.println("UserId is: " + userID);
        Assert.assertEquals(userID, 7819756);

        String status = js.getString("status");
        System.out.println("email is: " + status);
        Assert.assertEquals(status, "active");
    }

    @Test
    public void getAllUserTest() {
        RestAssured.baseURI = "https://gorest.co.in";

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .when()
                .get("/public/v2/users");

        System.out.println("status code:" + response.statusCode());
        System.out.println("status code:" + response.statusLine());
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200, "status code is not 200");
        Assert.assertTrue(response.statusLine().contains("200 OK"));

        JsonPath js = response.jsonPath(); // JsonPath is a method
        List<Integer> idList = js.getList("id");
        List<String> nameList = js.getList("name");

        System.out.println(idList);
        System.out.println(nameList);
    }

}
