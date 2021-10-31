package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HoverPageTests {

    private static List<WebElement> figureClassElementsList;

    @BeforeAll
    public static void setUp() {
        baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;

        open("/");
        $(By.linkText("Hovers")).click();

        figureClassElementsList = WebDriverRunner.getWebDriver().findElements(By.className("figure"));
    }

    @Test //Opens "Hovers" page by click on link and checks if page name matches.
    public void canOpenHoversPage() {
        $("h3").shouldHave(text("Hovers"));
    }

    @Test //Hovers over first profile picture and checks if text field "name: user1" appears.
    public void shouldDisplayUsersNameWhenHoveringOverFirstProfilePicture() {
        $(figureClassElementsList.get(0)).hover().$("h5").shouldHave(text("name: user1"));
    }

    @Test //Hovers over first profile picture and checks if page "View profile" link for this profile appears.
    public void shouldDisplayViewProfileForThisProfileWhenHoveringOverFirstProfilePicture() {
        $(figureClassElementsList.get(0)).hover().$("a")
                .shouldHave(attribute("href", baseUrl + "/users/1"))
                .shouldHave(text("View profile"));
    }

    @Test //Hovers over second profile picture and checks if text field "name: user2" appears.
    public void shouldDisplayUsersNameWhenHoveringOverSecondProfilePicture() {
        $(figureClassElementsList.get(1)).hover().$("h5").shouldHave(text("name: user2"));
    }

    @Test //Hovers over second profile picture and checks if page "View profile" link for this profile appears.
    public void shouldDisplayViewProfileForThisProfileWhenHoveringOverSecondProfilePicture() {
        $(figureClassElementsList.get(1)).hover().$("a")
                .shouldHave(attribute("href", baseUrl + "/users/2"))
                .shouldHave(text("View profile"));
    }

    @Test //Hovers over third profile picture and checks if text field "name: user3" appears.
    public void shouldDisplayUsersNameWhenHoveringOverThirdProfilePicture() {
        $(figureClassElementsList.get(2)).hover().$("h5").shouldHave(text("name: user3"));
    }

    @Test //Hovers over third profile picture and checks if page "View profile" link for this profile appears.
    public void shouldDisplayViewProfileForThisProfileWhenHoveringOverThirdProfilePicture() {
        $(figureClassElementsList.get(2)).hover().$("a")
                .shouldHave(attribute("href", baseUrl + "/users/3"))
                .shouldHave(text("View profile"));
    }
}
