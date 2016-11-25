package selenium.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import selenium.webconfigure.Browser;

/**
 * AbstractPage
 */
public abstract class AbstractPage {

    protected static long elementTimeout = 10;
    protected static int pageTimeout = 10;
    protected WebDriver driver;

    protected AbstractPage(Browser browser) {
        this.driver = browser.getWebDriver();
        //initialize all elements and wait until they are presenting
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, pageTimeout), this);
    }

    public static void setElementTimeout(long timeout) {
        elementTimeout = timeout;
    }

    public static void setPageTimeout(int timeout) {
        pageTimeout = timeout;
    }
}