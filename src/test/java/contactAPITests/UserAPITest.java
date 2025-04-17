package contactAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserAPITest {

    String tokenID;
    String contactId;
    String baseUri = "https://thinking-tester-contact-list.herokuapp.com/users";

    @BeforeTest
    public void createToken() {
        RestAssured.baseURI = this.baseUri;
        CredentialForToken credentialForToken = new CredentialForToken.CredentialForTokenBuilder()
                .email("dhananjay.kumar967@gmail.com")
                .password("jX.9.8hx!i8X.kL")
                .build();

        tokenID = given().log().all()
                .contentType(ContentType.JSON)
                .body(credentialForToken)
                .when()
                .post("/login")
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("token");

    }

    public void hitAddUserApi(){

        RestAssured.baseURI = this.baseUri;
        UserPojoClass userPojoClass = new UserPojoClass.UserPojoClassBuilder()
                .firstName(getRandomName())
                .lastName("Kumar")
                .email(getRandomEmailId())
                .password(getRandomName()+"password")
                .build();

        contactId = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenID)
                .body(userPojoClass)
                .when()
                .post("/")
                .then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");
    }

    public String getRandomEmailId() {
        return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
    }

    public String getRandomName() {
        String[] firstNames = {"John", "Alice", "Michael", "Emma", "David", "Sarah",
                "James", "Emily", "Robert", "Maria"};
        Random random = new Random();
        return firstNames[random.nextInt(firstNames.length)];
    }

}
