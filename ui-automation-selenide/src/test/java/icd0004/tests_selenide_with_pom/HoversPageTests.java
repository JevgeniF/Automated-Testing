package icd0004.tests_selenide_with_pom;

import icd0004.pages.HoversPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class HoversPageTests extends BaseTest {

    private HoversPage hoversPage;

    @BeforeEach
    public void goToHomePage() {
        hoversPage = new HoversPage();
        hoversPage.goTo();
    }

    @Test //Opens "Hovers" page by click on link and checks if page name matches.
    public void canOpenHoverPage() {
        assertThat(hoversPage.getPageTitleText()).isEqualTo("Hovers");
    }

    @Test //Hovers over first profile picture and checks if text field "name: user1" appears.
    public void shouldDisplayUsersNameWhenHoveringOverFirstProfilePicture() {
        assertThat(hoversPage.getHoveredProfileNameFieldText(0)).isEqualTo("name: user1");
    }

    @Test //Hovers over first profile picture and checks if "View profile" link shows.
    public void shouldDisplayLinkNamedViewProfileWhenHoveringOverFirstProfilePicture() {
        assertThat(hoversPage.getHoveredProfileLinkName(0)).isEqualTo("View profile");
    }

    @Test //Hovers over second profile picture and checks if text field "name: user2" appears.
    public void shouldDisplayUsersNameWhenHoveringOverSecondProfilePicture() {
        assertThat(hoversPage.getHoveredProfileNameFieldText(1)).isEqualTo("name: user2");
    }

    @Test //Hovers over third profile picture and checks if text field "name: user3" appears.
    public void shouldDisplayUsersNameWhenHoveringOverThirdProfilePicture() {
        assertThat(hoversPage.getHoveredProfileNameFieldText(2)).isEqualTo("name: user3");
    }
}
