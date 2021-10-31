package icd0004.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxesPage {
    private final By checkboxesPageLink = By.linkText("Checkboxes");

    public void goTo() {
        open("/");
        $(checkboxesPageLink).click();
    }

    public String getPageTitleText() { return $("h3").getText(); }
}
