package tests.reqres;

import body.reqres.RegisterUserBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RegisterUserTest {
    private String cookie;

    @BeforeClass
    public void setup() throws Exception {
        // Set base URI
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }

    @Test
    public void registerUserTest() throws IOException {
        // Buat body dari class
        RegisterUserBody bodyObj = new RegisterUserBody();
        JSONObject requestBody = bodyObj.getBody();

        // Kirim request POST dengan Cookie
        Response response = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody.toString())
                .when()
                .post("/api/register")
                .then()
                .statusCode(200)
                .extract().response();

        // Print response untuk debug
        System.out.println("Response: " + response.asString());

        String data = response.jsonPath().getString("id");
        Assert.assertNotNull(data, "Data should not be null");

        // Validasi status code
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, 200, "Status code bukan 200! Response: " + response.asString());

    }
}
