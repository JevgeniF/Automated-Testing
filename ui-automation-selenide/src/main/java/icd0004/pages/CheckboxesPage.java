package icd0004.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxesPage {
    private static List<WebElement> checkboxesList;
    private final By checkboxesPageLink = By.linkText("Checkboxes");

    public void goTo() {
        open("/");
        $(checkboxesPageLink).click();

        checkboxesList = WebDriverRunner.getWebDriver().findElements(By.xpath("//input"));
    }

    public String getPageTitleText() {
        return $("h3").getText();
    }

    public void selectCheckBoxes() {
        for (WebElement checkbox : checkboxesList) {
            if (!checkbox.isSelected()) checkbox.click();
        }
    }

    public void deSelectCheckBoxes() {
        for (WebElement checkbox : checkboxesList) {
            if (checkbox.isSelected()) checkbox.click();
        }
    }


    public boolean isCheckBoxSelected() {
        for (WebElement checkbox : checkboxesList) {
            if (checkbox.isSelected()) {
                return true;
            }
        }
        return false;
    }
}
