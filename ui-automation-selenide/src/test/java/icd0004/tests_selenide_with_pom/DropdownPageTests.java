package icd0004.tests_selenide_with_pom;

import icd0004.pages.DropdownPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DropdownPageTests extends BaseTest {
    private static DropdownPage dropdownPage;

    @BeforeEach
    public void goToDropdownPage() {
        dropdownPage = new DropdownPage();
        dropdownPage.goTo();
    }

    @Test
    public void canOpenDropdownPage() {
        assertThat(dropdownPage.getPageTitleText()).isEqualTo("Dropdown List");
    }

    @Test
    public void canSelectOptionOne() {
        dropdownPage.selectOptionWithValue(1);

        assertThat(dropdownPage.getSelectedDropdownItem()
                .getText())
                .isEqualTo("Option 1");
    }

    @Test
    public void canSelectionOptionTwo() {
        dropdownPage.selectOptionWithValue(2);

        assertThat(dropdownPage.getSelectedDropdownItem().getText()).isEqualTo("Option 2");
    }
}
