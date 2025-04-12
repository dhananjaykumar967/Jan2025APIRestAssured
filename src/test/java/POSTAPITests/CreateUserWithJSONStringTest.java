package POSTAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateUserWithJSONStringTest {

    public static String getRandomEmailId() {
        return "api" + System.currentTimeMillis() + "@gmail.com";
    }

    @Test
    public void createUserWithJSONStringTest() {
        RestAssured.baseURI = "https://gorest.co.in";

        String emailId = getRandomEmailId();

        given().log().all()
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"John\",\n" +
                        "    \"email\": \"" + emailId + "\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void createUserWithJSONFileTest() {
        RestAssured.baseURI = "https://gorest.co.in";

        given().log().all()
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .contentType(ContentType.JSON)
                .body(new File("./src/test/resources/jsons/user.json"))
                .when()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void createUserWithJsonWithStringReplacementTest() throws IOException {
        RestAssured.baseURI = "https://gorest.co.in";

        String emailId = getRandomEmailId();

        String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));

        String updatedJson = rawJson.replace("{{email}}", emailId);

        Integer userId = given().log().all()
                .header("Authorization", "Bearer 8bfee2d57c4eb858bd2e336c56ea442306360e3ca522db4534b554edc79b5fda")
                .contentType(ContentType.JSON)
                .body(updatedJson)
                .when()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("id");

        System.out.println("userId: " + userId);
    }

}
