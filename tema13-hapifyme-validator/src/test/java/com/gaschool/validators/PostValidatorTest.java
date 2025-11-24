package com.gaschool.validators;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class PostValidatorTest {
    private PostValidator postValidator;


    @BeforeClass
    public void setup() {
        postValidator = new PostValidator();
    }

    @DataProvider(name = "postDataProvider")
    public Object[][] postDataProvider() {
        String longPost = "A" + "B".repeat(250);

        return new Object[][]{

                {null, "ERROR_EMPTY"},
                {"", "ERROR_EMPTY"},
                {"Acesta este un post valid, scurt si clar, fara cuvinte interzise.", "POST_VALID"},
                {"Subiectul acestui articol este politica externa.", "ERROR_FORBIDDEN"},
                {longPost, "ERROR_TOO_LONG"},
                {"Vorbim despre politica, nu despre altceva.", "ERROR_FORBIDDEN"}
        };
    }

    @Test(dataProvider = "postDataProvider")
    public void testPostValidationScenarios(String postBody, String expectedStatus) {

        String actualStatus = postValidator.getPostStatus(postBody);
        assertEquals(actualStatus, expectedStatus,
                "Validarea a esuat pentru corpul: \"" + postBody + "\"");
    }
}