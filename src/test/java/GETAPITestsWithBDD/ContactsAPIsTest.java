package GETAPITestsWithBDD;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsAPIsTest {

    //    AAA: Arrange, Act and Assert
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
    }

    @Test(priority = 1)
    public void getContactsAPITest() {

        given().log().all()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2FhNDY3NDg0NzA4ZTAwMTNmODhmNDQiLCJpYXQiOjE3NDQxMzQ3OTB9.cv4jzPnoPDdFCMgw2NWwuLZ3MifQyDdXTddZNM9roig")
                .when()
                .get("/contacts")
                .then().log().all()
                .statusCode(200)
                .and()
                .contentType("application/json");
    }

    @Test(priority = 2)
    public void getContactsAPIAuthErrorTest() {

        given().log().all()
                .header("Authorization", "Bearer 435345345")
                .when()
                .get("/contacts")
                .then().log().all()
                .statusCode(401)
                .and()
                .contentType("application/json")
                .and()
                .body("error", equalTo("Please authenticate."));
    }

    @Test(priority = 3)
    public void getContactsAPIInvalidTokenTest() {

        String errorMessage = given().log().all()
                .header("Authorization", "Bearer 435345345")
                .when()
                .get("/contacts")
                .then().log().all()
                .extract()
                .path("error");
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage, "Please authenticate.", "Error message is not correct");
    }
}
