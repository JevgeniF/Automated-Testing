package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePageTests {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.startMaximized = true;
        Configuration.browser = "edge";
    }

    @Test
    public void canOpenHomePage() {
        open("/");
        $("h1").shouldHave(text("Welcome to the-internet"));
    }

}
