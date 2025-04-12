package GETAPIWithNonBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetContactsAPITest {
    @Test
    public void getContactsAPITest() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2FhNDY3NDg0NzA4ZTAwMTNmODhmNDQiLCJpYXQiOjE3NDQxMzQ3OTB9.cv4jzPnoPDdFCMgw2NWwuLZ3MifQyDdXTddZNM9roig");
        Response response = request.get("contacts");

        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        response.prettyPrint();
        String contentType = response.header("content-type");
        System.out.println(contentType);

        response.headers().forEach(header -> {
            System.out.println(header.getName() + ":" + header.getValue());
        });

        System.out.println(response.headers().size());
    }
}
