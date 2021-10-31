package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HoverPageTests {

    private static List<WebElement> hoversOnPage;

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;

        open("/");
        $(By.linkText("Hovers")).click();

        hoversOnPage = WebDriverRunner.getWebDriver().findElements(By.className("figure"));
    }

    @Test //Opens "Hovers" page by click on link and checks if page name matches.
    public void canOpenHoversPage() {
        $("h3").shouldHave(text("Hovers"));
    }

    @Test //Hovers over first profile picture and checks if text field "name: user1" appears.
    public void shouldDisplayUsersNameWhenHoveringOverFirstProfilePicture() {
        $(hoversOnPage.get(0)).hover().$("h5").shouldHave(text("name: user1"));
    }

    @Test //Hovers over first profile picture and checks if page "View profile" link appears.
    public void shouldDisplayViewProfileWhenHoveringOverFirstProfilePicture() {
        $(hoversOnPage.get(0)).hover().$("a").shouldHave(text("View profile"));
    }

    @Test //Hovers over second profile picture and checks if text field "name: user2" appears.
    public void shouldDisplayUsersNameWhenHoveringOverSecondProfilePicture() {
        $(hoversOnPage.get(1)).hover().$("h5").shouldHave(text("name: user2"));
    }

    @Test //Hovers over third profile picture and checks if text field "name: user3" appears.
    public void shouldDisplayUsersNameWhenHoveringOverThirdProfilePicture() {
        $(hoversOnPage.get(2)).hover().$("h5").shouldHave(text("name: user3"));
    }
}
