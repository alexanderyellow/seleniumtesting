package selenium.webconfigure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import selenium.webconfigure.ExecutionContext.BrowserName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Configure working with the Browser.
 * <p>
 * Created by alexander.
 */
public class Browser {

    public enum BrowserName {
        CHROME("CHROME"),
        IE("IE"),
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

    private final static List<WebDriver> drivers = new ArrayList<WebDriver>();

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
    private ExecutionContext executionContext;

    public Browser(ExecutionContext executionContext) {
        this.executionContext = executionContext;
        this.id = UUID.randomUUID() + "";
        initDriver();
    }

    /**
     * Initialize driver.
     * @return driver
     */
    private synchronized Browser initDriver() {

        switch (executionContext.getBrowserName()) {
            case CHROME:
                //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(executionContext.getJavascriptEnabled());
                capabilities.setPlatform(executionContext.getPlatform());
                capabilities.setBrowserName(executionContext.getBrowserName().toString());

                driver = new ChromeDriver(new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(executionContext.getWebDriver()))
                        .usingAnyFreePort()
                        .build(), capabilities);

                System.out.println("Chrome");
                break;
            case IE:
                System.out.println("IE");
                break;
            case FF:
                System.out.println("FF");
                break;
            default:
                System.out.println("Chrome");
                break;
        }

        drivers.add(driver);
        return null;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * Navigate to url
     * @param url navigation url
     */
    public void get(String url) {
        driver.navigate().to(url);
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

    public String getScreenShot() {
        try {
            return driver.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            return null;
        }
    }

    public String extractScreenShot(WebDriverException e) {
        Throwable cause = e.getCause();
        if (cause instanceof ScreenshotException) {
            return ((ScreenshotException) cause).getBase64EncodedScreenshot();
        }
        return null;
    }

    public String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Throwable t) {
            return "Couldn't get page source:\n"; //+ TestUtils.getThrowableFullDescription(t);
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
}