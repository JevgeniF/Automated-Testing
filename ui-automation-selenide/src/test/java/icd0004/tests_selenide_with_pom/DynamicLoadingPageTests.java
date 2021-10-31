package icd0004.tests_selenide_with_pom;

import icd0004.pages.DynamicLoadingPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicLoadingPageTests extends BaseTest {

    private static DynamicLoadingPage dynamicLoadingPage;

    @BeforeEach
    public void goToDynamicLoadingPage() {
        dynamicLoadingPage = new DynamicLoadingPage();
        dynamicLoadingPage.goTo();
    }

    @Test
    public void canOpenDynamicLoadingPage() {
        assertThat(dynamicLoadingPage.getPageTitleText()).isEqualTo("Dynamically Loaded Page Elements");
    }

    @Test
    public void shouldSeeHelloWorldAfterLoadingExampleOne() {
        dynamicLoadingPage.openExampleOneLink();
        dynamicLoadingPage.pressStart();

        assertThat(dynamicLoadingPage.getGreetingText()).isEqualTo("Hello World!");
    }

    @Test
    public void shouldSeeHelloWorldAfterLoadingExampleTwo() {
        dynamicLoadingPage.openExampleTwoLink();
        dynamicLoadingPage.pressStart();

        assertThat(dynamicLoadingPage.getGreetingText()).isEqualTo("Hello World!");
    }
}
