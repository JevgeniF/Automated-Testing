package icd0004.pages;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DynamicLoadingPage {

    private final By dynamicLoadingPageLink = By.linkText("Dynamic Loading");
    private final By exampleOneLink = By.linkText("Example 1: Element on page that is hidden");
    private final By exampleTwoLink = By.linkText("Example 2: Element rendered after the fact");
    private final By startButton = By.xpath("//button");
    private final By greetingField = By.cssSelector("#finish > h4");

    public void goTo() {
        open("/");
        $(dynamicLoadingPageLink).click();
    }

    public String getPageTitleText() {
        return $("h3").getText();
    }

    public void openExampleOneLink() {
        $(exampleOneLink).click();
    }

    public void openExampleTwoLink() {
        $(exampleTwoLink).click();
    }

    public void pressStart() {
        $(startButton).click();
    }

    public String getGreetingText() {
        return $(greetingField).shouldBe(visible, Duration.ofMillis(7000)).getText(); // waitUntil() is deprecated
    }
}
