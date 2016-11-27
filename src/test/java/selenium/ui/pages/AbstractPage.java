package selenium.ui.pages;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.*;
import selenium.logger.Logger;
import selenium.webconfigure.Browser;

import java.util.Iterator;
import java.util.List;
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
    private final String pageName;

    protected AbstractPage(Browser browser, String pageName) {
        this.browser = browser;
        this.driver = browser.getWebDriver();
        this.pageName = pageName;
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
        logOpeningPage();

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

    protected boolean isOpened(final String urlPart) {
        logOpeningPage();
        return isUrlEnding(urlPart);
    }

    private void logOpeningPage() {
        Logger.get().debug(pageName + " page is opening.");
    }

    protected void waitElementToBeClickable(WebElement element) {
        (new WebDriverWait(driver, elementTimeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitElementToBeVisible(WebElement element) {
        (new WebDriverWait(driver, elementTimeout))
                .until(ExpectedConditions.visibilityOf(element));
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

    protected void selectByIndex(Select select, String option) {

        if (!"".equals(option) && option != null) {
            List<WebElement> options = select.getOptions();

            String actualOption = "";
            int index = 0;

            Iterator<WebElement> iterator = options.iterator();

            while (iterator.hasNext()) {
                actualOption = iterator.next().getText();

                if (option.equals(actualOption)) {
                    select.selectByIndex(index);
                    return;
                } else {
                    index++;
                }
            }

            Logger.get().error("There is no such option '" + option + "'!");
        }
    }

    protected boolean isUrlEnding(final String urlPart) {
        String currentUrl = driver.getCurrentUrl();

        Wait<String> wait = new FluentWait<String>(currentUrl)
                .withTimeout(elementTimeout, TimeUnit.SECONDS)
                .pollingEvery(intervalTimeout, TimeUnit.SECONDS);

        return wait.until(new Function<String, Boolean>() {
            public Boolean apply(String url) {
                return url.contains(urlPart);
            }
        });
    }

    protected WebElement getTableCell(WebElement table, int rowN, int columnN) {
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));
        WebElement row = rows.get(rowN);

        List<WebElement> columns = row.findElements(By.cssSelector("td"));
        WebElement column = columns.get(columnN);

        return column;
    }

}