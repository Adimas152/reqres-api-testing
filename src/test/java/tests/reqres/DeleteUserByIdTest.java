package tests.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeleteUserByIdTest {
    @BeforeClass
    public void setup() throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }

    @Test
    public void deleteUseryById() throws IOException {
        String idParam = "1";
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .queryParam("id", idParam)
                .when()
                .delete("/api/rest/deleteTicket")
                .then()
                .statusCode(204)
                .extract().response();

        // validasi empty respon
        Assert.assertTrue(response.getBody().asString().isEmpty());
    }

}
