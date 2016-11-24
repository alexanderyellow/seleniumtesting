package selenium.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.logger.Logger;
import selenium.ui.Element;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.ExecutionContextManager;

/**
 * AbstractPage
 */
public abstract class AbstractPage {

    /*protected static int pageTimeout = 0;
    protected final Logger logger = Logger.get();

    protected final Browser browser;
    protected final String pageLogicalName;

    *//**
     * Constructor
     *
     * @param browser         Browser
     * @param pageLogicalName Logical page name
     *//*
    protected AbstractPage(Browser browser, String pageLogicalName) {
        this.browser = browser;
        this.pageLogicalName = pageLogicalName;
        ExecutionContextManager.get().getExecutionContext().setCurrentPage(this);
    }

    *//**
     * Sets page wait timeout (default is 0)
     *
     * @param newValue newValue in seconds
     *//*
    public static void setPageTimeout(int newValue) {
        pageTimeout = newValue;
    }

    *//**
     * Browser
     *
     * @return Browser
     *//*
    public Browser getBrowser() {
        return browser;
    }

    *//**
     * Browser driver
     *
     * @return Browser driver
     *//*
    public WebDriver getDriver() {
        return getBrowser().getWebDriver();
    }

    *//**
     * Is page opened within default element timeout
     *
     * @return true if page is opened
     *//*
    public boolean isOpened() {
        return isOpened(getPageTimeout());
    }

    *//**
     * Is page opened
     *
     * @param timeout Time out to wait
     * @return true if page is opened
     *//*
    public abstract boolean isOpened(int timeout);

    *//**
     * Asserts if page is opened
     *
     * @param message Message to log
     * @see #isOpened()
     *//*
    public void assertIsOpened(String message) {
        assertTrue(isOpened(), message);
    }

    *//**
     * Asserts if page is opened
     *
     * @param timeout timeout to wait
     * @param message Message to log
     * @see #isOpened()
     *//*
    public void assertIsOpened(String message, int timeout) {
        assertTrue(isOpened(timeout), message + "\nTimeout of " + timeout + " seconds");
    }

    *//**
     * Asserts if page is opened<br/>
     * Places default message pageLogicalName + " should be opened" in log
     *
     * @see #assertIsOpened(String)
     *//*
    public void assertIsOpened() {
        assertIsOpened(pageLogicalName + " should be opened");
    }

    *//**
     * Asserts if page is opened<br/>
     * Places default message pageLogicalName + " should be opened" in log
     *
     * @param timeout Timeout to wait
     * @see #assertIsOpened(String, int)
     *//*
    public void assertIsOpened(int timeout) {
        assertIsOpened(pageLogicalName + " should be opened", timeout);
    }

    *//**
     * Checks if current browser URL ends with text
     *
     * @param endsWith text to check
     * @param timeout  Timeout to wait
     * @return true if URL ends with expected text
     *//*
    protected boolean isPageUrlEndsWith(final String endsWith, final int timeout, final AbstractPage page) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeout, Element.getWaitBetweenCheck());
            return wait.until(new ExpectedCondition<Boolean>() {

                private String lastUrlPath = "FAILED TO RETRIEVE";

                public Boolean apply(WebDriver driver) {
                    //        try {
                    if (driver == null) return false;

                    //        lastUrlPath = new URL(driver.getCurrentUrl()).getPath();  return /masquerade/
                    lastUrlPath = driver.getCurrentUrl();

                    boolean result = lastUrlPath.toLowerCase().endsWith(endsWith.toLowerCase());

                    if (result) {
                        Element.waitAjax(page);
                    }

                    return result;

                    //        } catch (MalformedURLException e) {
                    //          return false;
                    //    }
                }

                public String toString() {
                    return "url path ends with " + endsWith + " current url is " + lastUrlPath;
                }
            });
        } catch (TimeoutException e) {
            logger.componentInfo(String.format("%s: Page not opened:\n%s", pageLogicalName, e.getMessage()));
            return false;
        }

    }

    *//**
     * Gets page wait timeout in seconds
     *
     * @return wait timeout in seconds
     *//*
    public static int getPageTimeout() {
        return pageTimeout;
    }

    public String toString() {
        return pageLogicalName + " (" + getClass().getSimpleName() + ")";
    }

    public void refresh() {
        getBrowser().refresh();
    }*/
}
