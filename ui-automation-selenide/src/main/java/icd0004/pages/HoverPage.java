package icd0004.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HoverPage {
    private final By hoverPageLink = By.linkText("Hovers");

    public void goTo() {
        open("/");
        $(hoverPageLink).click();
    }

    public String getPageTitleText()  {
        return $("h3").getText();
    }
}
