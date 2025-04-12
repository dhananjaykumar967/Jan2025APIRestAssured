package PostAPIWithDifferentBodyTypes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

public class PostAPIWithDifferentBody {

    @Test
    public void bodyWithTextTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        RestAssured.given().log().all()
                .contentType(ContentType.TEXT)
                .body("This is Post call")
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithJavaScriptTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String payload = "<script>\n"
                + "document.getElementById(\"demo\").innerHTML = 10.50;\n"
                + "</script>";

        RestAssured.given().log().all()
                .contentType("application/javascript;charset=utf-8")
                .body(payload)
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithHTMLTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String payload = "<html>\n" +
                "<head>\n" +
                "    <title>Demo Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2 id=\"demo\">Original Text</h2>\n" +
                "\n" +
                "<script>\n" +
                "    document.getElementById(\"demo\").innerHTML = 10.50;\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        RestAssured.given().log().all()
                .contentType("text/html;charset=utf-8")
                .body(payload)
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithXMLTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String payload = "<page>\n" +
                "    <title>Demo Page</title>\n" +
                "    <body>\n" +
                "        <element id=\"demo\">Original Text</element>\n" +
                "        <script>\n" +
                "            <![CDATA[\n" +
                "                document.getElementById(\"demo\").innerHTML = 10.50;\n" +
                "            ]]>\n" +
                "        </script>\n" +
                "    </body>\n" +
                "</page>";

        RestAssured.given().log().all()
                .contentType("text/xml;charset=utf-8")
                .body(payload)
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithMultipartTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        RestAssured.given().log().all()
                .contentType(ContentType.MULTIPART)
                .multiPart("resume", new File("/Users/dhananjaykumar/Downloads/AnjaniPandey.pdf"))
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void bodyWithImageFileTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        RestAssured.given().log().all()
                .contentType(ContentType.MULTIPART)
                .multiPart("resume", new File("/Users/dhananjaykumar/Downloads/image.jpeg"))
                .when()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
