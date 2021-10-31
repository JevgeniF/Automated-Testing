package icd0004.tests_selenide_with_pom;

import icd0004.pages.HoverPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HoverPageTests  extends BaseTest {

    private HoverPage hoverPage;

    @BeforeEach
    public void goToHomePage() {
        hoverPage = new HoverPage();
        hoverPage.goTo();
    }

    @Test //Opens "Hovers" page by click on link and checks if page name matches.
    public void canOpenHoverPage() {
        assertThat(hoverPage.getPageTitleText()).isEqualTo("Hovers");
    }
}
