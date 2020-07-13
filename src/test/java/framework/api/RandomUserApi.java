package framework.api;

import framework.dto.RandomUserNameDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RandomUserApi extends RestAssuredClient {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private static final String ENDPOINT_GET_RANDOM_USER = "https://randomuser.me/api";

    public RandomUserApi() {
        innit();
    }

    private void innit() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ENDPOINT_GET_RANDOM_USER)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody(containsString("results"))
                .build();
    }

    private Response getRandomUserNameResponse() {
        return given()
                .param("inc", "name")
                .param("results", 1)
                .when()
                .get();
    }

    public RandomUserNameDto getRandomUserName() {
        return convertResponse(getRandomUserNameResponse(), RandomUserNameDto.class);
    }
}
