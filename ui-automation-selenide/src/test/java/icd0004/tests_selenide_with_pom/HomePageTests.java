package icd0004.tests_selenide_with_pom;

import icd0004.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTests extends BaseTest {

    private static HomePage homePage;

    @BeforeEach
    public void goToHomePage() {
        homePage = new HomePage();
        homePage.goTo();
    }

    @Test
    public void canOpenHomePage() {
        assertThat(homePage.getPageTitleText()).isEqualTo("Welcome to the-internet");
    }

}
