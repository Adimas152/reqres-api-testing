package tests.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;

public class GetUserByIdTest {
    private String cookie;

    @BeforeClass
    public void setup() throws Exception {
        // Set base URI dari config.properties
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }

    @Test
    public void getUserByIdTest() throws IOException {
        String idParam = "1";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .queryParam("id", idParam)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .extract().response();

        // Print response
        System.out.println("Response: " + response.asString());

        // Validasi result.id sesuai
        String id = response.jsonPath().getString("data.id");
        Assert.assertEquals(id, idParam, "Id user tidak sesuai");

        String data = response.jsonPath().getString("data");
        Assert.assertNotNull(data, "Data should not be null");

    }
}
