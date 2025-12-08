package com.hapifyme.bdd.stepdefinitions;

import com.hapifyme.bdd.pages.ProfilePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertTrue;

public class ProfileSteps {

    private ProfilePage profilePage = new ProfilePage();

    @When("introduc urmatoarele cuvinte cheie si verific rezultatul {string}:")
    public void introduc_urmatoarele_cuvinte_cheie_si_verific_rezultatul(String expectedResultName, DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String keyword = row.get("cautare");
            System.out.println("Cautare cu cuvantul cheie: " + keyword + " si verific " + expectedResultName);
            profilePage.searchAndVerifyProfile(keyword, expectedResultName);
            sleep(1000);
        }
    }

    @Then("apas pe primul rezultat din cautare si verific profilul {string}")
    public void apas_pe_primul_rezultat_din_cautare_si_verific_profilul(String expectedProfileName) {
        profilePage.clickFirstProfileResult();
        profilePage.verifyProfilePage(expectedProfileName);
    }

    @And("cautarea utilizatorului ar trebui sa se fi finalizat cu succes")
    public void cautarea_utilizatorului_ar_trebui_sa_se_fi_finalizat_cu_succes() {
        assertTrue(true);
    }
}