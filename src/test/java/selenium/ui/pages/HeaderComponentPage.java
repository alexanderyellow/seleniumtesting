package selenium.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.common.Language;
import selenium.webconfigure.Browser;

/**
 * HeaderComponent common for all pages
 */
public abstract class HeaderComponentPage extends AbstractPage {

    @FindBy(css = "div#main_table > span.menu_lang > a")
    private WebElement languageLink;

    protected HeaderComponentPage(Browser browser) {
        super(browser);
    }

    public void changeLanguage(Language lang) {

        if (isLangMatch(lang)) {
            languageLink.click();
        }
    }

    public boolean isLangMatch(Language lang) {
        (new WebDriverWait(driver, elementTimeout))
                .until(ExpectedConditions.elementToBeClickable(languageLink));

        String expectedLang = lang.toString();
        String actualLang = languageLink.getText();

        if (actualLang.equalsIgnoreCase(expectedLang)) {
            return true;
        }

        return false;
    }

}