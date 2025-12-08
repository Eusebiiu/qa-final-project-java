package com.hapifyme.bdd.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

import java.time.Duration;

public class ProfilePage {


    private final SelenideElement searchInput = $("input[placeholder='Search...']");
    private final String searchResultLinkLocator = "div.search-result a, div.search_result a";
    private final SelenideElement profileInfoContainer = $(".profile_info");

    /**
     * Realizează căutarea și verifică vizibilitatea rezultatului așteptat.
     */
    public void searchAndVerifyProfile(String keyword, String expectedResultName) {

        searchInput.shouldBe(Condition.visible, Duration.ofSeconds(5)).setValue(keyword);
        searchInput.pressEnter();
        $$(searchResultLinkLocator)
                .find(Condition.partialText(expectedResultName))
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    /**
     * Click pe primul link de profil găsit în rezultate.
     */
    public void clickFirstProfileResult() {
        $(searchResultLinkLocator).shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
    }

    /**
     * Verifică dacă s-a ajuns pe pagina de profil corectă.
     */
    public void verifyProfilePage(String expectedName) {
        String expectedSlug = expectedName.toLowerCase().replace(" ", "_");
        webdriver().shouldHave(urlContaining(expectedSlug), Duration.ofSeconds(10));
        profileInfoContainer.shouldBe(Condition.visible);
    }
}