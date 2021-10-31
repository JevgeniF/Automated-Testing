package icd0004.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {

    public void goTo() {
        open("/");
    }

    public String getPageTitleText() {
        return $("h1").getText();
    }
}
