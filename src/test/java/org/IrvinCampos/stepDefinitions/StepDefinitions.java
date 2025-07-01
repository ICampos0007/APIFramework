package org.IrvinCampos.stepDefinitions;

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

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {

        RequestSpecification response = given().spec(requestSpecificationUtil()).body(testDataBuild.addPlacePayLoad());
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res=given().spec(requestSpecificationUtil())
                .body(testDataBuild.addPlacePayLoad());
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response is {string}")
    public void in_response_is(String key, String value) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        Assert.assertEquals(jsonPath.get(key).toString(),value);
    }
}
