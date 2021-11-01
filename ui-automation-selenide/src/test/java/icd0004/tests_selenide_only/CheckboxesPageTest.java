package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckboxesPageTest {

    private static List<WebElement> checkboxesList;

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;

        open("/");
        $(By.linkText("Checkboxes")).click();

        checkboxesList = WebDriverRunner.getWebDriver().findElements(By.xpath("//input"));
    }

    @Test //Opens "Checkboxes" page by click on link and checks if page name matches.
    public void canOpenCheckboxesPage() {
        $("h3").shouldHave(text("Checkboxes"));
    }

    @Test //Checks if all checkboxes on page can be selected.
    public void canSelectAllCheckboxes() {
        checkboxesStatusChanger(false);
        for (WebElement checkbox : checkboxesList) {
            assertTrue(checkbox.isSelected());
        }
    }

    @Test //Checks if all checkboxes on page can be selected.
    public void canDeSelectAllCheckboxes() {
        checkboxesStatusChanger(true);
        for (WebElement checkbox : checkboxesList) {
            assertFalse(checkbox.isSelected());
        }
    }

    //Helper to check current checkboxes status and change it.
    private void checkboxesStatusChanger(boolean selected) {
        for (WebElement checkbox : checkboxesList) {
            if (checkbox.isSelected() == selected) checkbox.click();
        }
    }
}
