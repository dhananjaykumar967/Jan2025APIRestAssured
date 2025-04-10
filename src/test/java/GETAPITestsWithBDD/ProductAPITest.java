package GETAPITestsWithBDD;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductAPITest {
    @Test
    public void getProductTest() {
        RestAssured.baseURI = "http://fakestoreapi.com";

        Response response = given()
                .when()
                .get("/products");

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        response.prettyPrint();

        JsonPath js = response.jsonPath();
        List<Integer> listOfId = js.getList("id");
        List<String> listOfTitle = js.getList("title");
        List<Float> listOfPrice = js.getList("price");
        List<Double> listOfRate = js.getList("rating.rate");
        List<Integer> listOfCount = js.getList("rating.count");

        System.out.println(listOfId);
        System.out.println(listOfPrice);
        System.out.println(listOfTitle);
        System.out.println(listOfCount);
        System.out.println(listOfRate);

        for (int i = 0; i < listOfId.size(); i++) {
            int id = listOfId.get(i);
            Object price = listOfPrice.get(i);
            Object rate = listOfRate.get(i);
            int count = listOfCount.get(i);

            System.out.println(String.format("id: %d, price: %s, rate: %s, count: %d", id, price, rate, count));
        }
    }

    @Test
    public void getProductCountTest() {
        RestAssured.baseURI = "http://fakestoreapi.com";

        given().log().all()
                .when()
                .get("/products")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("$.size", equalTo(20));

    }
}
