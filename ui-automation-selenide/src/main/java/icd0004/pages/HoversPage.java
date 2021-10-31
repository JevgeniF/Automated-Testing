package icd0004.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HoversPage {
    private final By hoversPageLink = By.linkText("Hovers");
    private final By nameField = By.cssSelector("h5");
    private static List<WebElement> figureClassElementsList;

    public void goTo() {
        open("/");
        $(hoversPageLink).click();
        figureClassElementsList = WebDriverRunner.getWebDriver().findElements(By.className("figure"));
    }

    public String getPageTitleText()  {
        return $("h3").getText();
    }

    public String getHoveredProfileNameFieldText(int index) {
       return $(figureClassElementsList.get(index)).hover().$("h5").getText();
    }

    public String getHoveredProfileLinkName(int index) {
        return $(figureClassElementsList.get(index)).hover().$("a").getText();
    }

    public String getHoveredProfileLinkUrl(int index) {
        return $(figureClassElementsList.get(index)).hover().$("a").getAttribute("href");
    }
}
