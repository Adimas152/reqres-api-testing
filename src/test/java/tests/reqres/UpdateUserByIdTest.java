package tests.reqres;

import body.reqres.UpdateUserBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class UpdateUserByIdTest {
    @Test
    public void updateStatusTicketById() throws Exception {
        // Set Base URI dari ConfigReader
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        // Baca body dari file JSON
        UpdateUserBody bodyHelper = new UpdateUserBody();
        JSONObject requestBody = bodyHelper.getBodyFromFile("src/resources/json/token.json");

        // Update field ticketId dan createdAt

        requestBody.put("name", "updated morpheus");
        requestBody.put("job", "updated job");

        String idParam = "2";
        // Kirim request PUT
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .queryParam("id", idParam)
                .body(requestBody.toString())
                .when()
                .put("/api/users/2" )
                .then()
                .statusCode(200)
                .extract().response();

        // Print response
        System.out.println("Response: " + response.asString());

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Headers: " + response.getHeaders());
    }
}
