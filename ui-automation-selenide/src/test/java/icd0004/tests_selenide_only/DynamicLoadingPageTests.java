package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DynamicLoadingPageTests {
    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 7000;
    }

    @BeforeEach
    public void goToDynamicLoadingPage() {
        open("/");
        $(By.linkText("Dynamic Loading")).click();
    }

    @Test
    public void canOpenDynamicLoadingPage() {
        $("h3").shouldHave(text("Dynamically Loaded Page Elements"));
    }

    @Test
    public void shouldSeeHelloWorldAfterLoadingExampleOne() {
        $(By.linkText("Example 1: Element on page that is hidden")).click();
        $("#start > button").click();
        $("#finish > h4").shouldHave(text("Hello World!"));
    }

    @Test
    public void shouldSeeHelloWorldAfterLoadingExampleTwo() {
        $(By.linkText("Example 2: Element rendered after the fact")).click();
        $("#start > button").click();
        $("#finish > h4").shouldHave(text("Hello World!"));
    }
}
