package selenium.ui.pages;

import common.constants.Language;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.webconfigure.Browser;

/**
 * HeaderComponent common for all pages
 */
public abstract class HeaderComponentPage extends AbstractPage {

    @FindBy(css = "div#main_table > span.menu_lang > a")
    private WebElement languageLink;

    @FindBy(css = "div#main_table > span.page_header_menu a[title='Искать объявления']")
    private WebElement searchLink;

    protected HeaderComponentPage(Browser browser) {
        super(browser);
    }

    public void changeLanguage(Language lang) {
        if (isLangMatch(lang)) {
            languageLink.click();
        }
    }

    public boolean isLangMatch(Language lang) {
        super.waitElementToBeClickable(languageLink);

        String expectedLang = lang.toString();
        String actualLang = languageLink.getText();

        if (actualLang.equalsIgnoreCase(expectedLang)) {
            return true;
        }

        return false;
    }

    public SearchingPage openSearchingPage() {
        super.waitElementToBeClickable(searchLink);
        searchLink.click();

        return new SearchingPage(browser);
    }

}