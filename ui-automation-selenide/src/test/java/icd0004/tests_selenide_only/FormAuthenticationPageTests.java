package icd0004.tests_selenide_only;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormAuthenticationPageTests {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;

        open("/");
        $(By.linkText("Form Authentication")).click();
    }

    @Test
    public void canOpenFormAuthenticationPage() {
        $("h2").shouldHave(text("Login Page"));
    }

    @Test
    public void shouldLoginToSecureAreaWithValidCredentials() {
        String validUsername = "tomsmith";
        String validPassword = "SuperSecretPassword!";

        $("#username").setValue(validUsername);
        $("#password").setValue(validPassword);
        $("button").click();
        $("h2").shouldHave(text("Secure Area"));
    }

    @Test
    public void shouldNotLoginToSecureAreaWithInvalidCredentials() {
        String inValidUsername = "Horse";
        String inValidPassword = "Lamp!";

        $("#username").setValue(inValidUsername);
        $("#password").setValue(inValidPassword);
        $("button").click();
        $("#flash").shouldHave(text("Your username is invalid!"));
    }
}
