package tests.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetUsersTest {

    private String cookie;

    @BeforeClass
    public void setup() throws Exception {
        // Set base URI dari config.properties
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }

    @Test
    public void getUserTest() throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .extract().response();

        // Print response
        System.out.println("Response: " + response.asString());

        String data = response.jsonPath().getString("data");
        Assert.assertNotNull(data, "Data should not be null");


    }
}
