package com.hapifyme.api.utils;

import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ApiPoller {

    public static String retrieveConfirmationToken(String email, String apiKey) {
        return await()
                .atMost(20, SECONDS)
                .pollInterval(2, SECONDS)
                .ignoreExceptions()
                .until(
                        () -> given()
                                .header("Authorization", apiKey)
                                .queryParam("username_or_email", email)
                                .get(ConfigManager.BASE_URL + "/user/retrieve_token.php")
                                .then()
                                .statusCode(200)
                                .body("status", equalTo("success"))
                                .extract().jsonPath().getString("confirmation_token"),
                        token -> token != null && !token.isEmpty()
                );
    }
}