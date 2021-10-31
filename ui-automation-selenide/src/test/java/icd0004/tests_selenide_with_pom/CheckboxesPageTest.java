package icd0004.tests_selenide_with_pom;

import icd0004.pages.CheckboxesPage;
import icd0004.pages.HoversPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxesPageTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    public void goToCheckboxesPage() {
        checkboxesPage = new CheckboxesPage();
        checkboxesPage.goTo();
    }

    @Test //Opens "Checkboxes" page by click on link and checks if page name matches.
    public void canOpenCheckboxesPage() {
        assertThat(checkboxesPage.getPageTitleText()).isEqualTo("Checkboxes");
    }
}
