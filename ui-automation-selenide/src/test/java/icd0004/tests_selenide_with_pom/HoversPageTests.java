package icd0004.tests_selenide_with_pom;

import icd0004.pages.HoversPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class HoversPageTests extends BaseTest {

    private HoversPage hoversPage;

    @BeforeEach
    public void goToHoversPage() {
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

    @Test //Hovers over first profile picture and checks that "View profile" link matches the profile.
    public void shouldMatchViewProfileLinkWithFirstUserWhenHoveringOverFirstProfilePicture() {
        assertThat(hoversPage.getHoveredProfileLinkUrl(0)).isEqualTo(baseUrl + "/users/1");
    }

    @Test //Hovers over second profile picture and checks if text field "name: user2" appears.
    public void shouldDisplayUsersNameWhenHoveringOverSecondProfilePicture() {
        assertThat(hoversPage.getHoveredProfileNameFieldText(1)).isEqualTo("name: user2");
    }

    @Test //Hovers over second profile picture and checks if "View profile" link shows.
    public void shouldDisplayLinkNamedViewProfileWhenHoveringOverSecondProfilePicture() {
        assertThat(hoversPage.getHoveredProfileLinkName(1)).isEqualTo("View profile");
    }

    @Test //Hovers over second profile picture and checks that "View profile" link matches the profile.
    public void shouldMatchViewProfileLinkWithSecondUserWhenHoveringOverSecondProfilePicture() {
        assertThat(hoversPage.getHoveredProfileLinkUrl(1)).isEqualTo(baseUrl + "/users/2");
    }

    @Test //Hovers over third profile picture and checks if text field "name: user3" appears.
    public void shouldDisplayUsersNameWhenHoveringOverThirdProfilePicture() {
        assertThat(hoversPage.getHoveredProfileNameFieldText(2)).isEqualTo("name: user3");
    }

    @Test //Hovers over third profile picture and checks if "View profile" link shows.
    public void shouldDisplayLinkNamedViewProfileWhenHoveringOverThirdProfilePicture() {
        assertThat(hoversPage.getHoveredProfileLinkName(2)).isEqualTo("View profile");
    }

    @Test //Hovers over third profile picture and checks that "View profile" link matches the profile.
    public void shouldMatchViewProfileLinkWithThirdUserWhenHoveringOverThirdProfilePicture() {
        assertThat(hoversPage.getHoveredProfileLinkUrl(2)).isEqualTo(baseUrl + "/users/3");
    }
}
