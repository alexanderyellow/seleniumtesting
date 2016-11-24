package selenium.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import selenium.common.Language;
import selenium.webconfigure.Browser;

/**
 * HeaderComponent common for all pages
 */
public abstract class HeaderComponent {

    @FindBy(css = "div#main_table > span.menu_lang > a")
    private WebElement languageLink;
    private WebDriver driver;

    public HeaderComponent(Browser browser) {
        this.driver = browser.getWebDriver();

        //initialize all elements and wait until they are presenting
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public void changeLanguage(Language lang) {
        /*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(ExpectedConditions.elementToBeClickable(languageLink));*/

        if (isLangMatch(lang)) {
            languageLink.click();
        }
    }

    public boolean isLangMatch(Language lang) {
        String expectedLang = lang.toString();
        String actualLang = languageLink.getText();

        if (actualLang.equalsIgnoreCase(expectedLang)) {
            return true;
        }

        return false;
    }

}
