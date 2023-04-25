package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.DataBuildTest;
import resources.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    RequestSpecification res;
    ResponseSpecification respSpec;
    Response response;
    static String place_id;

    DataBuildTest dataBuildTest = new DataBuildTest();

    @Given("Add Place Payload with {int} {string} {string} {string}")
    public void addPlacePayloadWithAccuracy(int accuracy, String name, String language, String address) throws IOException {

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        dataBuildTest.addPlacePayload(accuracy, name, language, address).setTypes(myList);

        res = given().spec(requestSpecification())
                .body(dataBuildTest.addPlacePayload(accuracy, name, language, address));

        respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("User call {string} with {string} http Request")
    public void userCallWithHttpRequest(String resource, String method) {

        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResource());

        if (method.equalsIgnoreCase("post")) {
            response = res.when()
                    .post(apiResources.getResource());
        }else if (method.equalsIgnoreCase("get")) {
            response = res.when()
                    .get(apiResources.getResource());
        }

        String responseString = response.asString();
    }

    @Then("The API call got successfull with status code {int}")
    public void the_api_call_got_successfull_with_status_code_200(){

        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void something_in_response_body_is_something(String keyValue, String expectedValue) {

        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("The API call got successfull with status code {string}")
    public void theAPICallGotSuccessfullWithStatusCode(String arg0) {

    }

    @And("Verify if place_Id create maps to {string} using {string}")
    public void verifyIfPlace_IdCreateMapsToUsing(String expectedName, String resource) throws IOException {

        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);

        userCallWithHttpRequest(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @When("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecification()).body(dataBuildTest.deletePlacePayload(place_id));
    }
}
