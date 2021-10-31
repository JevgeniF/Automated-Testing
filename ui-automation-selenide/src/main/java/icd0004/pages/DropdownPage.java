package icd0004.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.valueOf;

public class DropdownPage {
    private final By dropdownField = By.id("dropdown");
    private final By dropdownPageLink = By.linkText("Dropdown");

    public void goTo() {
        open("/");
        $(dropdownPageLink).click();
    }

    public String getPageTitleText() {
        return $("h3").getText();
    }

    public void selectOptionWithValue(int optionValue) {
        $(dropdownField).click();
        $(dropdownField).selectOptionByValue(valueOf(optionValue));
    }

    public SelenideElement getSelectedDropdownItem() {
        return $(dropdownField).getSelectedOption();
    }
}
