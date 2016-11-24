package selenium.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.collections.Lists;
import selenium.logger.Logger;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;

/**
 * Element
 */
public class Element {

    /*protected static int elementTimeout = 0;
    protected static int waitBetweenCheck = 1;

    protected final Logger logger = Logger.get();

    //protected static java.util.List<AbstractAjaxLoadManager> ajaxHandlers = Lists.newArrayList();

    //protected final ElementFromListMatcher elementsMatcher;

    *//*public static void addAjaxLoadManager(AbstractAjaxLoadManager manager) {
        ajaxHandlers.add(manager);
    }*//*

    *//**
     * Sets element wait timeout (default is 0)
     *
     * @param newValue newValue in seconds
     * @see WebDriverWait
     * @see WebElementWait
     *//*
    public static void setElementTimeout(int newValue) {
        elementTimeout = newValue;
    }

    *//**
     * Sets element wait timeout check interval (default is 1)
     *
     * @param newValue newValue in milliseconds
     * @see WebDriverWait
     * @see WebElementWait
     *//*
    public static void setElementTimeoutInterval(int newValue) {
        waitBetweenCheck = newValue;
    }

    *//**
     * Gets element wait timeout in seconds
     *
     * @return wait timeout in seconds
     *//*
    public static int getElementTimeout() {
        return elementTimeout;
    }

    *//**
     * Gets wait timeout check interval
     *
     * @return wait timeout check interval in milliseconds
     *//*
    public static int getWaitBetweenCheck() {
        return elementTimeout;
    }

    *//**
     * driver
     *//*
    private final AbstractPage page;
    *//**
     * element locator
     *//*
    protected final By locator;
    *//**
     * element logical name
     *//*
    protected final String logicalName;

    private final By parentLocator;
    private final Element parentElement;

    *//**
     * Constructor
     *
     * @param logicalName     element logical name in application
     * @param page            Page
     * @param locator         element locator
     * @param elementsMatcher Matcher for elements collection
     *//*
    public Element(String logicalName, AbstractPage page, By locator, ElementFromListMatcher elementsMatcher) {
        this.page = page;
        this.locator = locator;
        this.logicalName = logicalName;
        this.parentElement = null;
        this.parentLocator = null;
        this.elementsMatcher = elementsMatcher;
    }

    *//**
     * Constructor
     *
     * @param logicalName     element logical name in application
     * @param page            Page
     * @param locator         element locator
     * @param parent          parent element locator
     * @param elementsMatcher Matcher for elements collection
     *//*
    public Element(String logicalName, AbstractPage page, By locator, By parent,
                   ElementFromListMatcher elementsMatcher) {
        this.page = page;
        this.locator = locator;
        this.logicalName = logicalName;
        this.parentElement = null;
        this.parentLocator = parent;
        this.elementsMatcher = elementsMatcher;
    }

    *//**
     * Constructor
     *
     * @param logicalName     element logical name in application
     * @param locator         element locator
     * @param parent          parent element
     * @param elementsMatcher Matcher for elements collection
     *//*
    public Element(String logicalName, By locator, Element parent, ElementFromListMatcher elementsMatcher) {
        this.page = null;
        this.locator = locator;
        this.logicalName = logicalName;
        this.parentElement = parent;
        this.parentLocator = null;
        this.elementsMatcher = elementsMatcher;
    }

    *//**
     * Constructor
     *
     * @param logicalName     element logical name in application
     * @param page            Page
     * @param locator         element locator
     * @param parentMap       parent element locator map
     * @param parentLocator   parent element locator key in map
     * @param elementsMatcher Matcher for elements collection
     *//*
    public Element(String logicalName, AbstractPage page, By locator, String parentMap, String parentLocator,
                   ElementFromListMatcher elementsMatcher) {
        this(logicalName, page, locator, Locators.get(parentMap, parentLocator), elementsMatcher);
    }

    *//**
     * Constructor
     *
     * @param logicalName     element logical name in application
     * @param page            page
     * @param map             Map path
     * @param locatorName     locator name in map
     * @param elementsMatcher Matcher for elements collection
     *//*
    public Element(String logicalName, AbstractPage page, String map, String locatorName,
                   ElementFromListMatcher elementsMatcher) {
        this(logicalName, page, Locators.get(map, locatorName), elementsMatcher);
    }

    *//**
     * Constructor
     *
     * @param logicalName     element logical name in application
     * @param map             Map path
     * @param locatorName     locator name in map
     * @param parent          parent element
     * @param elementsMatcher Matcher for elements collection
     *//*
    public Element(String logicalName, String map, String locatorName, Element parent,
                   ElementFromListMatcher elementsMatcher) {
        this(logicalName, Locators.get(map, locatorName), parent, elementsMatcher);
    }

    *//**
     * Constructor
     *
     * @param logicalName element logical name in application
     * @param page        Page
     * @param locator     element locator
     *//*
    public Element(String logicalName, AbstractPage page, By locator) {
        this(logicalName, page, locator, (ElementFromListMatcher) null);
    }

    *//**
     * Constructor
     *
     * @param logicalName element logical name in application
     * @param page        page
     * @param locator     element locator
     * @param parent      parent element locator
     *//*
    public Element(String logicalName, AbstractPage page, By locator, By parent) {
        this(logicalName, page, locator, parent, null);
    }

    *//**
     * Constructor
     *
     * @param logicalName element logical name in application
     * @param locator     element locator
     * @param parent      parent element
     *//*
    public Element(String logicalName, By locator, Element parent) {
        this(logicalName, locator, parent, null);
    }

    *//**
     * Constructor
     *
     * @param logicalName   element logical name in application
     * @param page          page
     * @param locator       element locator
     * @param parentMap     parent element locator map
     * @param parentLocator parent element locator key in map
     *//*
    public Element(String logicalName, AbstractPage page, By locator, String parentMap, String parentLocator) {
        this(logicalName, page, locator, Locators.get(parentMap, parentLocator));
    }

    *//**
     * Constructor
     *
     * @param logicalName element logical name in application
     * @param page        page
     * @param map         Map path
     * @param locatorName locator name in map
     *//*
    public Element(String logicalName, AbstractPage page, String map, String locatorName) {
        this(logicalName, page, Locators.get(map, locatorName));
    }


    *//**
     * Constructor
     *
     * @param logicalName element logical name in application
     * @param map         Map path
     * @param locatorName locator name in map
     * @param parent      parent element
     *//*
    public Element(String logicalName, String map, String locatorName, Element parent) {
        this(logicalName, Locators.get(map, locatorName), parent);
    }

    *//**
     * Get native webdriver element
     *
     * @return native webdriver element
     *//*
    protected WebElement getWebElement() {
        return getWebElement(elementTimeout);
    }

    *//**
     * Get native webdriver element
     *
     * @param timeout Timeout to wait
     * @return native webdriver element
     *//*
    protected WebElement getWebElement(int timeout) {

        if (elementsMatcher == null) {
            return (WebElement) getDefaultWait(timeout).until(
                    ExpectedElementConditions.presenceOfElementLocated(locator));
        } else {
            return (WebElement) getDefaultWait(timeout).until(
                    ExpectedElementConditions.presenceOfElementLocated(locator, elementsMatcher));
        }
    }

    public String getCSSClass() {
        debug("Getting css class");
        try {
            String cssClassName = getWebElement().getAttribute("class");
            log("Got CSS class: " + cssClassName);
            return cssClassName;
        } catch (WebDriverException e) {
            throw new UIInteractionException(this, "Get css class", e);
        }

    }

    *//**
     * Checks if element doesn't exists or invisible
     *
     * @param timeout Timeout to wait
     * @return true if element not found or invisible
     *//*
    public boolean doesntExistsOrInvisible(int timeout) {

        debug("Checking if doesn't exists");

        try {
            //if (element == null) {
            if (elementsMatcher == null) {
                getDefaultWait(timeout).until(ExpectedElementConditions.invisibilityOfElementLocated(locator));
            } else {
                getDefaultWait(timeout).until(
                        ExpectedElementConditions.invisibilityOfElementLocated(locator, elementsMatcher));
            }
            *//*} else {
                getDefaultWait(timeout).until(ExpectedElementConditions.invisibilityOfElement(element));
            } *//*

            log("Doesn't exists");
            return true;
        } catch (TimeoutException e) {
            log("Exists. Searched by %s", locator);
            return false;
        }

    }

    *//**
     * Checks if element doesn't exists or invisible<br/>
     * Waits within default timeout
     *
     * @return true if element not found or invisible
     *//*
    public boolean doesntExistsOrInvisible() {
        return doesntExistsOrInvisible(elementTimeout);
    }

    *//**
     * Is element exists
     *
     * @return true if element exists on page (including if element is invisible)
     *//*
    public boolean exists() {
        return exists(elementTimeout);
    }

    *//**
     * Is element exists
     *
     * @param timeout timeout to wait
     * @return true if element exists on page (including if element is invisible)
     *//*
    public boolean exists(int timeout) {
        debug("Checking if exists");
        try {
            getWebElement(timeout);
            log("Exists");
            return true;
        } catch (WebDriverException e) {
            log("Doesn't exists. Searched by %s", locator);
            return false;
        }
    }

    *//**
     * Is element exists and visible
     *
     * @param timeout Timeout to wait in seconds
     * @return true if element exists and visible on page
     *//*
    public boolean existsAndVisible(int timeout) {
        debug("Checking if exists and visible");

        try {

            if (elementsMatcher == null) {
                getDefaultWait(timeout).until(
                        ExpectedElementConditions.visibilityOfElementLocated(locator));
            } else {
                getDefaultWait(timeout).until(
                        ExpectedElementConditions.visibilityOfElementLocated(locator, elementsMatcher));
            }

            log("Exists and visible");
            return true;
        } catch (WebDriverException e) {
            log("Doesn't exists and/or not visible. Searched by %s", locator);
            return false;
        }
    }

    *//**
     * Is element exists and visible within default timeout
     *
     * @return true if element exists and visible on page
     *//*
    public boolean existsAndVisible() {
        return existsAndVisible(elementTimeout);
    }

    *//**
     * Logs component level message
     *
     * @param formattedMessage Message to log
     * @param args             format args
     * @see TestLogger#componentInfo(String)
     * @see String#format(String, Object...)
     *//*
    protected void log(String formattedMessage, Object... args) {
        logger.componentInfo(logicalName + " " + getThisClassName() +
                ": " + String.format(formattedMessage, args));
    }

    *//**
     * Logs debug level message
     *
     * @param formattedMessage Message to log
     * @param args             format args
     * @see TestLogger#debug(String)
     * @see String#format(String, Object...)
     *//*
    protected void debug(String formattedMessage, Object... args) {
        String finalString = formattedMessage;
        if (args != null && args.length > 0) {
            finalString = String.format(finalString.replace("\n", "%n"), args);
        }
        logger.debug(logicalName + " " + getThisClassName() +
                ": " + finalString);
    }

    *//**
     * Provides current class name (for example, Element)
     *
     * @return Current class name
     *//*
    protected String getThisClassName() {
        return getClass().getSimpleName();
    }

    *//**
     * Provides default wait instance to wait for condition
     *
     * @return WebDriverWait if parentLocator and parentElement are not specified, WebElementWait else
     *//*
    protected FluentWait getDefaultWait() {
        return getDefaultWait(elementTimeout);
    }

    *//**
     * Provides default wait instance to wait for condition
     *
     * @param timeout Timeout to wait
     * @return WebDriverWait if parentLocator and parentElement are not specified, WebElementWait else
     *//*
    protected FluentWait getDefaultWait(int timeout) {
        if (parentElement == null && parentLocator == null) {
            return new WebDriverWait(page.getDriver(), timeout, waitBetweenCheck);
        } else {
            if (parentElement == null) {
                return new WebElementWait(new WebDriverWait(page.getDriver(), timeout, waitBetweenCheck)
                        .until(ExpectedConditions.presenceOfElementLocated(parentLocator)),
                        timeout, waitBetweenCheck);
            } else {
                return new WebElementWait(parentElement.getWebElement(timeout), timeout, waitBetweenCheck);
            }
        }
    }

    *//**
     * Get element inner text
     *
     * @return element inner text
     *//*
    public String getText() {
        debug("Getting text");
        try {
            String text = getWebElement().getText();
            log("Got text '%s'", text);
            return text;
        } catch (WebDriverException e) {
            throw new UIInteractionException(this, "Get text", e);
        }
    }

    *//**
     * Get element property title
     *
     * @return element title
     *//*
    public String getTitle() {
        debug("Getting title");
        try {
            String text = getWebElement().getAttribute("title");
            log("Got title %s", text);
            return text;
        } catch (TimeoutException e) {
            throw new UIInteractionException(this, "Get title", e);
        }
    }

    public By getLocator() {
        return locator;
    }

    public ElementFromListMatcher getElementsMatcher() {
        return elementsMatcher;
    }

    public String getLogicalName() {
        return logicalName;
    }

    *//**
     * Is element enabled
     *
     * @return true if element exists and visible and enabled
     *//*
    public boolean enabled() {
        log("Checking if enabled");
        try {
            boolean result = existsAndVisible(0) && getWebElement(0).isEnabled();

            if (!result) {
                log("Doesn't enabled");
            } else {
                log("Enabled");
            }

            return result;
        } catch (WebDriverException e) {
            throw new UIInteractionException(this, "Check if enabled", e);
        }
    }

    public AbstractPage getPage() {
        if (page != null) {
            return page;
        } else {
            return parentElement.getPage();
        }
    }

    *//**
     * Provides with current element driver
     *
     * @return Driver
     *//*
    protected WebDriver getDriver() {
        if (page != null) {
            page.getDriver().switchTo().defaultContent();//trying improve ie stability
            return page.getDriver();
        } else {
            return parentElement.getDriver();
        }
    }

    *//**
     * Executes JavaScript in element driver
     *
     * @param script Script to execute
     * @param args   Script arguments to pass in script
     * @return Script execution result
     * @see RemoteWebDriver#executeScript(String, Object...)
     *//*
    protected Object executeScript(String script, Object... args) {
        WebDriver driver = getDriver();

        debug("Executing script " + script + " with args " +
                (args == null ? "no arguments" : StringUtils.join(args, ", ")));

        return ((RemoteWebDriver) driver).executeScript(script, args);
    }

    public void click() {
        clickPrivate(false, -1, 1);
    }

    public void clickWithRetry(int timeoutToWaitDisappear) {
        clickPrivate(true, timeoutToWaitDisappear, 1);
    }

    *//**
     * Clicks object executing JavaScript<br/>
     * This method will fire click by JS only on IE<br/>
     *
     * @see #clickByJS(boolean)
     *//*
    public void clickByJS() {
        clickByJS(false);
    }

    *//**
     * Clicks object executing JavaScript<br/>
     * Use this method only if default click unavailable
     *
     * @param evenIfNotIE If false and browser not IE default method click will be executed
     * @see #click()
     *//*
    public void clickByJS(boolean evenIfNotIE) {

        if (!evenIfNotIE && !getPage().getBrowser().isIE()) {
            click();
        } else {
            debug("Trying to click on by JS");

            WebElement element = getWebElement(0);

            String script = "";

            String tagName = element.getTagName();

            String elementType;

            try {
                elementType = element.getAttribute("type");
            } catch (WebDriverException e) {
                elementType = "";
            }

            if (elementType == null) elementType = "";

            if (tagName.equalsIgnoreCase("input") && elementType.equalsIgnoreCase("submit")) {
                script = "arguments[0].click('onClick');";
            } else {

                if (getPage().getBrowser().isIE()) {
                    script = "arguments[0].fireEvent('onClick');";
                } else {
                    script = "var evt = document.createEvent('MouseEvents');" +
                            "evt.initMouseEvent('click',true, true, window, " +
                            "0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                            "arguments[0].dispatchEvent(evt);";
                }
            }
            try {
                executeScript(script, element);
                waitAjax(getPage());
            } catch (WebDriverException e) {
                throw new UIInteractionException(this, "Click by JS", e);
            }
        }
    }

    *//**
     * Fires event onFocus on object executing JavaScript
     *//*
    public void fireOnFocus() {

        debug("Trying to fire onFocus event on by JS");

        String script = "";

        if (getPage().getBrowser().isIE()) {
            script = "arguments[0].fireEvent('onFocus');";
        } else {
            script = "var evt = document.createEvent('MouseEvents');" +
                    "evt.initMouseEvent('focus',true, true, window, " +
                    "0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evt);";
        }

        try {
            executeScript(script, getWebElement(0));
            waitAjax(getPage());
        } catch (WebDriverException e) {
            throw new UIInteractionException(this, "Fire on focus event", e);
        }

    }


    *//**
     * Checking if element clickable
     *
     * @return true if element become clickable within default wait element timeout
     *//*
    public boolean clickable() {
        return clickable(elementTimeout);
    }

    *//**
     * Checking if element clickable
     *
     * @param timeout Timeout to wait for clickable
     * @return true if element become clickable within timeout
     *//*
    public boolean clickable(int timeout) {

        debug("Checking if enabled");
        WebElement element;

        try {

            if (!existsAndVisible(0)) {
                if (elementsMatcher == null) {
                    element = (WebElement) getDefaultWait(timeout).until(
                            ExpectedElementConditions.elementToBeClickable(locator));
                } else {
                    element = (WebElement) getDefaultWait(timeout).until(
                            ExpectedElementConditions.elementToBeClickable(locator, elementsMatcher));
                }
            } else {
                element = (WebElement) getDefaultWait(timeout).until(
                        ExpectedElementConditions.elementToBeClickable(getWebElement(0)));
            }
            log("Clickable");
            return true;
        } catch (TimeoutException e) {
            log("Doesn't clickable");
            return false;
        }
    }

    *//**
     * clicks element<br/>
     * Waits element to be clickable before click
     *
     * @param doRetry                Retry click if element visible after first click
     * @param timeoutToWaitDisappear Timeout to wait for element disappear before retry
     * @param tryNumber              Number of retry count (starts with 1)
     *//*
    private void clickPrivate(boolean doRetry, int timeoutToWaitDisappear, int tryNumber) {
        debug("Trying click on");

        if (timeoutToWaitDisappear > 5) {
            logger.warn("Timeout to wait for element disappear after click is too great: " + timeoutToWaitDisappear);
            timeoutToWaitDisappear = 5;
        }

        int timeout = timeoutToWaitDisappear >= 0 ? 0 : elementTimeout;

        boolean inRetryNow = tryNumber > 1;

        try {

            WebElement element = getWebElement(0);

            if (getPage().getBrowser().isIE()) {
                try {
                    executeScript("arguments[0].scrollIntoView();", element);
                } catch (WebDriverException e) {
                }
            }

            try {
                element.click();
            } catch (WebDriverException e) {
                if (!inRetryNow) {
                    throw new UIInteractionException(this, "Click", e);
                }
                return;
            }
            log("Click");

            waitAjax(getPage());

            if (doRetry && !doesntExistsOrInvisible(timeoutToWaitDisappear)) {
                log("Still visible after click. Trying to retry");
                clickPrivate(false, 0, tryNumber + 1);
            }

        } catch (TimeoutException e) {
            if (!inRetryNow) {
                throw new UIInteractionException(this, "Click", e);
            }
        }
    }

    public String toString() {
        return getLogicalName() + " " + getThisClassName();
    }

    *//**
     * Scrolling element into view using JS
     *
     * @return Self
     *//*
    public Element scrollIntoView() {
        debug("Scrolling into view");
        try {
            executeScript("arguments[0].scrollIntoView();", getWebElement());
            log("Scroll Into view performed");
        } catch (Exception e) {
            throw new UIInteractionException(this, "Scroll into view", e);
        }
        return this;
    }

    *//**
     * Setting attribute 'style' value 'visibility: hidden;'"
     *//*
    public void makeHidden() {
        debug("Setting attribute 'style' value 'visibility: hidden;'");
        try {
            executeScript("arguments[0].setAttribute('style', 'visibility: hidden;')", getWebElement());
            log("Set attribute 'style' value 'visibility: hidden;'");
        } catch (Exception e) {
            throw new UIInteractionException(this, "Make hidden", e);
        }
    }

    public static void waitAjax(AbstractPage page) {
        for (AbstractAjaxLoadManager manager : ajaxHandlers) {
            manager.waitForLoad(page);
        }
    }


    *//**
     * Move mouse in this element area
     *//*
    public void moveMouseIn() {
        debug("Moving mouse in");
        try {
            scrollIntoView();
            new Actions(getDriver()).moveToElement(getWebElement()).perform();
            log("Mouse moved in");
        } catch (Exception e) {
            throw new UIInteractionException(this, "Move mouse in", e);
        }
    }

    *//**
     * Move mouse out of this element area
     *//*
    public void moveMouseOut() {
        debug("Moving mouse out");
        try {
            new Actions(getDriver()).moveToElement(getWebElement(), -100, -100).perform();
            log("Mouse moved out");
        } catch (Exception e) {
            throw new UIInteractionException(this, "Move mouse out", e);
        }
    }

    public <T extends Element> java.util.List<T> children(By by, Class<T> tClass) {
        debug("Searching children by " + by);
        try {
            java.util.List<WebElement> elementList = getWebElement().findElements(by);

            java.util.List<T> result = new ArrayList<>();
            for (int i = 0; i < elementList.size(); i++) {
                ElementFromListMatcher matcher = new ElementFromListMatcherByIndex(i + 1);
                T element = createElement(this, getLogicalName() + " child element #" + (i + 1),
                        by, matcher, tClass);
                result.add(element);
            }
            log("Found " + result.size() + " child elements");
            return result;
        } catch (Exception e) {
            throw new UIInteractionException(this, "Find children", new Object[]{by, tClass}, e);
        }
    }

    public java.util.List<Element> children(By by) {
        return children(by, Element.class);
    }

    private <T extends Element> T createElement(Element parent, String name, By by, ElementFromListMatcher matcher,
                                                Class<T> tClass) throws Exception {
        Constructor<T> constructor = tClass.getConstructor(String.class, By.class, Element.class, ElementFromListMatcher.class);
        return constructor.newInstance(name, by, parent, matcher);
    }

    public Element getParentElement() {
        return parentElement;
    }

    public Point getLocation() {
        debug("Get location");
        try {
            scrollIntoView();
            Point point = getWebElement(0).getLocation();
            log("Got location: " + point);
            return point;
        } catch (Throwable t) {
            throw new UIInteractionException(this, "Get location", t);
        }
    }

    public Dimension getSize() {
        debug("Get size");
        try {
            scrollIntoView();
            Dimension dimension = getWebElement(0).getSize();
            log("Got size: " + dimension);
            return dimension;
        } catch (Throwable t) {
            throw new UIInteractionException(this, "Get size", t);
        }
    }

    public String getValidationMsg() {
        try {
            debug("Getting text of validation message");
            String text = getWebElement().getAttribute("alf-validation-msg");
            log("Got text %s", text);
            return text;
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String getInnerHtml() {
        return getProperty("innerHTML");
    }

    public String getProperty(String propertyName) {
        debug("Get property " + propertyName);
        try {
            String value = getWebElement().getAttribute(propertyName);
            log("Got property " + propertyName + " value: " + value);
            return value;
        } catch (Throwable t) {
            throw new UIInteractionException(this, "Get property", new Object[]{propertyName}, t);
        }
    }

    public boolean existsAfterRefresh() {
        return existsAfterRefresh(elementTimeout);
    }

    public boolean existsAfterRefresh(int timeout) {
        long now = new Date().getTime();
        boolean exists;
        while (true) {
            exists = exists(1);
            if (!exists) {
                getPage().refresh();
                TestUtils.sleep(1000);
            } else {
                break;
            }
            if (new Date().getTime() - now > timeout * 1000) break;
        }

        return exists;
    }

    public boolean waitPropertyValueChange(String propertyName) {
        return waitPropertyValueChange(propertyName, elementTimeout);
    }

    public boolean waitPropertyValueChange(String propertyName, int timeOut) {

        String currentValue = this.getProperty(propertyName);
        try {
            getDefaultWait(timeOut).until(
                    ExpectedElementConditions.propertyOfElementChanged(locator, propertyName, currentValue));
        } catch (TimeoutException e) {
            log("Property was not changed");
            return false;
        }
        return true;
    }*/

}
