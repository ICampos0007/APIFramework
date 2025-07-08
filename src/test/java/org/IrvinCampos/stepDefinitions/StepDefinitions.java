package org.IrvinCampos.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.IrvinCampos.pojo.AddPlace;
import org.IrvinCampos.pojo.Location;
import org.IrvinCampos.resources.APIResources;
import org.IrvinCampos.resources.TestDataBuild;
import org.IrvinCampos.resources.Utils;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class StepDefinitions extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    Utils utils = new Utils();
    static String place_id;

    @Before
    public void setup() {
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        RequestSpecification response = given().spec(requestSpecificationUtil()).body(testDataBuild.addPlacePayLoad(name, language, address));
//        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res=given().spec(requestSpecificationUtil())
                .body(testDataBuild.addPlacePayLoad(name, language, address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResource());
        if (method.equalsIgnoreCase("POST")) {
            response =res.when().post(apiResources.getResource()).
                    then().spec(resspec).extract().response();
        } else if (method.equalsIgnoreCase("GET")) {
            response =res.when().get(apiResources.getResource()).
                    then().spec(resspec).extract().response();
        }

    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response is {string}")
    public void in_response_is(String key, String value) {
        Assert.assertEquals(getJSonPath(response,key),value);
    }

    @Then("verify place id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using_get_place_api(String ExpectedName, String resource) throws IOException {
        // requestSpec
        place_id = getJSonPath(response,"place_id");
        res=given().spec(requestSpecificationUtil()).queryParam("place_id",place_id);
        user_calls_with_post_http_request(resource,"GET");
        String actualName = getJSonPath(response,"name");
        Assert.assertEquals(actualName, ExpectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
//        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res=given().spec(requestSpecificationUtil()).body(testDataBuild.deletePlacePayload(place_id));
    }
}
