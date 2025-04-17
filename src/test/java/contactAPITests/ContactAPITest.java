package contactAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ContactAPITest {

    String tokenID;
    String contactId;
    String baseUri = "https://thinking-tester-contact-list.herokuapp.com";

    @BeforeTest
    public void createToken() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/users";
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

    @BeforeMethod
    public void beforeMethod() {
        contactId = createContactAndGetContactId();
    }

    @Test(priority = 1)
    public void createContact() {
        Assert.assertNotNull(contactId);
    }

    @Test(priority = 2)
    public void getContactDetails() {
        hitGetContactDetails()
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("_id", equalTo(contactId));
    }

    @Test(priority = 3)
    public void updateContactDetails() {
        RestAssured.baseURI = this.baseUri;
        ContactsPojoClass contactsPojoClass = new ContactsPojoClass.ContactsPojoClassBuilder()
                .firstName(getRandomName())
                .lastName("Keshari")
                .build();

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenID)
                .body(contactsPojoClass)
                .when()
                .put("/contacts/" + contactId)
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("_id", equalTo(contactId));

        getContactDetails();
    }

    @Test(priority = 4)
    public void patchContactDetails() {
        RestAssured.baseURI = this.baseUri;
        ContactsPojoClass contactsPojoClass = new ContactsPojoClass.ContactsPojoClassBuilder()
                .firstName(getRandomName())
                .build();

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenID)
                .body(contactsPojoClass)
                .when()
                .patch("/contacts/" + contactId)
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("_id", equalTo(contactId));

        getContactDetails();
    }

    @Test(priority = 5)
    public void deleteContactDetails() {
        RestAssured.baseURI = this.baseUri;
        ContactsPojoClass contactsPojoClass = new ContactsPojoClass.ContactsPojoClassBuilder()
                .firstName(getRandomName())
                .lastName("Keshari")
                .build();

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenID)
                .body(contactsPojoClass)
                .when()
                .delete("/contacts/" + contactId)
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(containsString("Contact deleted"));

        hitGetContactDetails()
                .then().log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    public String createContactAndGetContactId() {
        ContactsPojoClass contactsPojoClass = new ContactsPojoClass.ContactsPojoClassBuilder()
                .firstName(getRandomName())
                .lastName("Kumar")
                .birthdate("1995-10-01")
                .email(getRandomEmailId())
                .phone("8989898989")
                .street1("Rohini Sector 17")
                .street2("New Delhi")
                .stateProvince("Delhi")
                .country("India")
                .postalCode("110089")
                .build();

        RestAssured.baseURI = this.baseUri;

        return given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenID)
                .body(contactsPojoClass)
                .post("/contacts")
                .then().log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");
    }

    public Response hitGetContactDetails() {
        RestAssured.baseURI = this.baseUri;

        return given().log().all()
                .header("Authorization", tokenID)
                .get("/contacts/" + contactId);
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
