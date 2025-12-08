package com.hapifyme.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.partialText;

public class FeedPage {

    private final SelenideElement createPostArea = $("#post_text");
    private final SelenideElement postButton = $("#post_button");
    private final SelenideElement postButtonForCheck = $("#post_button");
    private final String POST_CONTAINER_LOCATOR = ".status_post";

    public boolean isUserLoggedIn() {
        return postButtonForCheck.exists();
    }

    public void createNewPost(String message) {
        createPostArea.setValue(message);
        postButton.click();
    }

    public void verifyPostContent(String expectedMessage) {
        $$(POST_CONTAINER_LOCATOR).findBy(Condition.partialText(expectedMessage)).shouldBe(Condition.visible);
    }
}