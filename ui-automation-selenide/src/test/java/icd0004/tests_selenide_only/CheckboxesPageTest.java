package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxesPageTest {
    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;

        open("/");
        $(By.linkText("Checkboxes")).click();
    }

    @Test //Opens "Checkboxes" page by click on link and checks if page name matches.
    public void canOpenCheckboxesPage() {
        $("h3").shouldHave(text("Checkboxes"));
    }
}
