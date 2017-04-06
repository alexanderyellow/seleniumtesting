package selenium.webconfigure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Configure working with the Browser
 */
public class Browser {

    private final static List<WebDriver> drivers = new ArrayList<WebDriver>();
    private static final long PAGE_LOAD_TIMEOUT = 10;
    private static RemoteWebDriver driver;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                for (WebDriver driver : drivers) {
                    if (driver != null) {
                        try {
                            driver.quit();
                        } catch (Exception ignore) {

                        }
                    }
                }
            }
        });
    }

    private final String id;
    private BrowserConfig browserConfig;
    public Browser(BrowserConfig browserConfig) {
        this.id = UUID.randomUUID() + "";
        this.browserConfig = browserConfig;
        initDriver();
    }

    /**
     * Initialize driver.
     * @return driver
     */
    private synchronized void initDriver() {
        DesiredCapabilities capabilities;

        switch (browserConfig.getBrowserName()) {
            case CHROME:
            //    System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(browserConfig.getJavascriptEnabled());
                capabilities.setPlatform(browserConfig.getPlatform());
                capabilities.setBrowserName(browserConfig.getBrowserName().toString());

                driver = new ChromeDriver(new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(browserConfig.getWebDriver()))
                        .usingAnyFreePort()
                        .build(), capabilities);
                break;
            case IE:
                //TODO add
                break;
            case SAFARI:
                capabilities = DesiredCapabilities.safari();
                capabilities.setJavascriptEnabled(browserConfig.getJavascriptEnabled());
                capabilities.setPlatform(browserConfig.getPlatform());
                capabilities.setBrowserName(browserConfig.getBrowserName().toString());
                driver = new SafariDriver();
            case FF:
                //it's necessary
                System.setProperty("webdriver.firefox.marionette","src\\test\\resources\\drivers");
                capabilities = DesiredCapabilities.firefox();
                capabilities.setJavascriptEnabled(browserConfig.getJavascriptEnabled());
                capabilities.setPlatform(browserConfig.getPlatform());
                capabilities.setBrowserName(browserConfig.getBrowserName().toString());

                driver = new FirefoxDriver(capabilities);
                break;
            default:
                //TODO rework duplicate code
                capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(browserConfig.getJavascriptEnabled());
                capabilities.setPlatform(browserConfig.getPlatform());
                capabilities.setBrowserName(browserConfig.getBrowserName().toString());

                driver = new ChromeDriver(new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(browserConfig.getWebDriver()))
                        .usingAnyFreePort()
                        .build(), capabilities);
                break;
        }

        setPageLoadTimeout(PAGE_LOAD_TIMEOUT);
        drivers.add(driver);
    }

    public void setPageLoadTimeout(long timeoutInS) {
        driver.manage().timeouts().pageLoadTimeout(timeoutInS, TimeUnit.SECONDS);
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * Navigate to url
     *
     * @param url navigation url
     */
    public void get(String url) {
        driver.navigate().to(url);
        //driver.get(url);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Check using browser.
     * @param browserName browser name
     * @return result
     */
    public boolean isExpectedBrowser(BrowserName browserName) {
        String currentBrowserName = ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName();
        String expectedBrowser = browserName.toString();

        return (expectedBrowser.equalsIgnoreCase(currentBrowserName));
    }

    /**
     * Maximize window
     */
    public void maximize() {
        driver.manage().window().maximize();
    }

    public void reopenIfDead() {
        try {
            driver.getTitle();
        } catch (WebDriverException ignore) {
            quit();
            initDriver();
        }
    }

    public String getScreenshot() {
        try {
            return driver.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            return null;
        }
    }

    public String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Throwable t) {
            return "Couldn't get page source:\n";
        }
    }

    /**
     * Quite driver
     */
    public void quit() {
        try {
            driver.quit();
        } catch (Throwable ignore) {

        }
    }

    protected String getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (o instanceof Browser) {
            return ((Browser) o).getId().equals(this.id);
        }

        return super.equals(o);
    }

    public enum BrowserName {
        CHROME("CHROME"),
        IE("IE"),
        SAFARI("SAFARI"),
        FF("FF");

        private final String browserName;

        BrowserName(String value) {
            browserName = value;
        }

        public static BrowserName fromString(String value) {
            return valueOf(value.toUpperCase());
        }

        public String toString() {
            return browserName;
        }
    }

}