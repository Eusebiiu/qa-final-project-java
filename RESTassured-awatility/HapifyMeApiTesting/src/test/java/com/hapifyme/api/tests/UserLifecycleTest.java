package com.hapifyme.api.tests;

import com.hapifyme.api.models.*;
import com.hapifyme.api.utils.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class UserLifecycleTest {
    private String username, email, password;
    private String apiKey;
    private String bearerToken;
    private String userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.BASE_URL;
        email = DataGenerator.generateUniqueEmail();
        username = email.split("@")[0];
        password = "Password123!";
    }

    @Test
    public void testUserLifecycle() {
        System.out.println("========== INITIATING USER LIFECYCLE TEST ==========");

        // --- 1. REGISTER ---
        System.out.println("\n[STEP 1] USER REGISTRATION (CREATE)");
        RegisterRequest reg = new RegisterRequest(username, email, password, "Initial", "User");
        Response regResponse = given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(reg)
                .post("/user/register.php");

        regResponse.then().statusCode(201);
        apiKey = regResponse.jsonPath().getString("api_key");
        String serverUsername = regResponse.jsonPath().getString("username");
        if (serverUsername != null) this.username = serverUsername;
        System.out.println("-> OK: Account created for: " + this.username + " (" + email + ")");

        // --- 2. POLLING TOKEN ---
        System.out.println("\n[STEP 2] POLLING FOR EMAIL CONFIRMATION TOKEN");
        System.out.println("   - Looking for token assigned to: " + email);
        String confirmToken = ApiPoller.retrieveConfirmationToken(email, apiKey);
        Assert.assertNotNull(confirmToken, "Error: Polling failed, token not found for " + email);
        System.out.println("-> OK: Confirmation token retrieved: " + confirmToken);

        // --- 3. CONFIRMATION ---
        System.out.println("\n[STEP 3] EMAIL CONFIRMATION (ACTIVATION)");
        given()
                .log().uri()
                .header("Authorization", apiKey)
                .queryParam("token", confirmToken)
                .get("/user/confirm_email.php")
                .then().statusCode(200);
        System.out.println("-> OK: Email verified successfully.");

        // --- 4. LOGIN ---
        System.out.println("\n[STEP 4] USER AUTHENTICATION (LOGIN)");
        LoginRequest login = new LoginRequest(this.username, password);
        Response loginRes = given()
                .log().uri()
                .contentType(ContentType.JSON)
                .header("Authorization", apiKey)
                .body(login)
                .post("/user/login.php");

        loginRes.then().statusCode(200);
        bearerToken = loginRes.jsonPath().getString("token");
        userId = loginRes.jsonPath().getString("user.id");

        System.out.println("-> OK: Authentication successful.");
        System.out.println("   - Assigned User ID: " + userId);

        // --- 5. WAIT FOR PROFILE ACTIVE ---
        System.out.println("\n[STEP 5] PROFILE READINESS CHECK (READ)");
        try {
            Awaitility.await("Profile Availability")
                    .atMost(Duration.ofSeconds(25))
                    .pollInterval(Duration.ofSeconds(3))
                    .until(() -> {
                        boolean isReady = checkProfileStatus(bearerToken, apiKey, userId);
                        if (!isReady) System.out.println("   ... profile status not 'success' yet, retrying...");
                        return isReady;
                    });
            System.out.println("-> OK: Profile data is accessible.");
        } catch (ConditionTimeoutException e) {
            System.err.println("-> ERROR: Profile did not become active in time!");
            throw e;
        }

        // --- STEP 5.1: UPDATE PROFILE ---
        System.out.println("\n[STEP 5.1] MODIFYING USER PROFILE (UPDATE)");

        UpdateUserRequest updateData = new UpdateUserRequest("", "");

        updateData.setFirst_name("UpdatedFirstName");
        updateData.setLast_name("UpdatedLastName");

        Response updateRes = given()
                .log().uri()
                .header("Authorization", apiKey)
                .queryParam("token", bearerToken)
                .queryParam("user_id", userId)
                .contentType(ContentType.JSON)
                .body(updateData)
                .when()
                .post("/user/update_profile.php");

        updateRes.then().statusCode(200);
        updateRes.then().statusCode(200);


        System.out.println("-> OK: Profile updated with details from object:");
        System.out.println("   New First Name: " + updateData.getFirst_name());
        System.out.println("   New Last Name: " + updateData.getLast_name());

        // --- 6. DELETE ---
        System.out.println("\n[STEP 6] USER PROFILE PERMANENT DELETION (DELETE)");
        given()
                .log().uri()
                .header("Authorization", "Bearer " + bearerToken)
                .queryParam("user_id", userId)
                .when()
                .delete("/user/delete_profile.php")
                .then()
                .log().status()
                .statusCode(200);

        System.out.println("-> OK: Cleanup successful. User removed.");

        // --- FINAL SUMMARY REPORT ---
        printFinalReport();
    }

    private boolean checkProfileStatus(String token, String key, String userId) {
        Response response = given()
                .header("Authorization", key)
                .queryParam("token", token)
                .queryParam("user_id", userId)
                .when()
                .get("/user/get_profile.php");

        if (response.getStatusCode() == 200) {
            ProfileResponse profile = response.jsonPath().getObject("user", ProfileResponse.class);

            System.out.println("   [DEBUG] Checking profile for: " + profile.email);
            System.out.println("   [DEBUG] Checking profile for: " + profile.first_name);

            String currentStatus = response.jsonPath().getString("status");
            return "success".equalsIgnoreCase(currentStatus);
        }
        return false;
    }

    private void printFinalReport() {
        System.out.println("\n================================================");
        System.out.println("           FINAL TEST EXECUTION SUMMARY");
        System.out.println("================================================");
        System.out.println("1. Create (Register):   [PASSED] (201)");
        System.out.println("2. Email Polling:       [PASSED] (Token OK)");
        System.out.println("3. Account Activation:  [PASSED] (200)");
        System.out.println("4. Login/Auth:          [PASSED] (JWT OK)");
        System.out.println("5. Read (Profile):      [PASSED] (Success)");
        System.out.println("5.1 Update (Profile):   [PASSED] (200)");
        System.out.println("6. Delete (Profile):    [PASSED] (200)");
        System.out.println("------------------------------------------------");
        System.out.println("OVERALL STATUS: SUCCESSFUL CRUD LIFECYCLE");
        System.out.println("================================================\n");
    }
}