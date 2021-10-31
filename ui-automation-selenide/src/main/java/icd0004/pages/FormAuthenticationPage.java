package icd0004.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormAuthenticationPage {
    private final By dropdownPageLink = By.linkText("Form Authentication");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By notification = By.id("flash");


    public void goTo() {
        open("/");
        $(dropdownPageLink).click();
    }

    public String getPageTitleText() {
        return $("h2").getText();
    }

    public void loginWithCredentials(String username, String password) {
        $(usernameField).setValue(username);
        $(passwordField).setValue(password);
        $(submitButton).click();
    }

    public SelenideElement getNotification() {
        return $(notification);
    }
}
