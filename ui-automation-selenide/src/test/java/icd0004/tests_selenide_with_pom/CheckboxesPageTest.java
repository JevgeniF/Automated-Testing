package icd0004.tests_selenide_with_pom;

import icd0004.pages.CheckboxesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test //Checks if all checkboxes on page can be selected.
    public void canSelectAllCheckboxes() {
        checkboxesPage.checkboxesStatusChanger(false);
        assertTrue(checkboxesPage.getCheckBoxesStatus());
    }

    @Test //Checks if all checkboxes on page can be de-selected.
    public void canDeSelectAllCheckboxes() {
       checkboxesPage.checkboxesStatusChanger(true);
       assertFalse(checkboxesPage.getCheckBoxesStatus());
    }
}
