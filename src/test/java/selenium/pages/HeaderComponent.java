package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.webconfigure.Browser;

/**
 * InitPage
 */
public class HeaderComponent {

    public enum Lang {
        RU("ru"),
        LV("lv");

        String value;

        Lang(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    @FindBy()
    private WebElement language;
    private String title;
    private Browser browser;

    public HeaderComponent(String title, Browser browser) {
        this.title = title;
        this.browser = browser;
    }

    /*public boolean isOpened() {

    }

    public HeaderComponent changeLanguage(Lang lang) {

    }*/

}
