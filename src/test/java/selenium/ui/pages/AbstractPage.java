package selenium.ui.pages;

import com.google.common.base.Function;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webconfigure.Browser;

import java.util.concurrent.TimeUnit;

/**
 * AbstractPage
 */
public abstract class AbstractPage {

    protected static long elementTimeout = 10;
    protected static int pageTimeout = 10;
    protected static int intervalTimeout = 1;
    protected final WebDriver driver;
    protected final Browser browser;

    protected AbstractPage(Browser browser) {
        this.browser = browser;
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

    public static void setIntervalTimeout(int intervalTimeout) {
        AbstractPage.intervalTimeout = intervalTimeout;
    }

    protected boolean isOpened(final WebElement element) {
        Wait<WebElement> wait = new FluentWait<WebElement>(element)
                .withTimeout(elementTimeout, TimeUnit.SECONDS)
                .pollingEvery(intervalTimeout, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement driver) {
                return element.isDisplayed();
            }
        });
    }

    protected void waitElementToBeClickable(WebElement element) {
        (new WebDriverWait(driver, elementTimeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean waitElementToBeEnabled(final WebElement element) {
        Wait<WebElement> wait = new FluentWait<WebElement>(element)
                .withTimeout(elementTimeout, TimeUnit.SECONDS)
                .pollingEvery(intervalTimeout, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement driver) {
                return element.isEnabled();
            }
        });
    }

}