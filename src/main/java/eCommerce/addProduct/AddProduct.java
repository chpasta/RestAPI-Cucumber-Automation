package eCommerce.addProduct;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.File;

import static io.restassured.RestAssured.given;

public class AddProduct {

    public static void main(String[] args) {


//        **** Attention: Need to create token in another file to be called!!!
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDQwMTUwNDU2OGMzZTlmYjE1NmUwNDQiLCJ1c2VyRW1haWwiOiJjaGNAZ21haWwuY29tIiwidXNlck1vYmlsZSI6NTE0ODI2MTUxNSwidXNlclJvbGUiOiJjdXN0b21lciIsImlhdCI6MTY4MjA3MzU2MCwiZXhwIjoxNzEzNjMxMTYwfQ.I9rCaE2xxD_JUpGDIEG71D1P9SzsUs3_Ohk-VnP97lU";
        String userId = "64401504568c3e9fb156e044";

        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .addHeader("Authorization", token).build();

        RequestSpecification reqSpec =
                given().log().all()
                .spec(addProductBaseReq)
                .param("productName", "car")
                .param("productAddedBy", userId)
                .param("productCategory", "game")
                .param("productSubCategory","shirts" )
                .param("productPrice", "11000")
                .param("productDescription", "Test" )
                .param("productFor", "kids")
                .multiPart("productImage", new File("/Users/carloscavalcante/Postman/files/dados.jpeg"));


        String addProdProductResponse = reqSpec.when()
                .post("/api/ecom/product/add-product")
                .then().log().all()
                .assertThat().extract().response().asString();

        JsonPath jsonPath = new JsonPath(addProdProductResponse);
        String productId = jsonPath.get("productId");

    }
}
