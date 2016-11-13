package selenium.webconfigure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import selenium.webconfigure.BrowserConfig.BrowserName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Configure working with the Browser.
 * <p>
 * Created by alexander on 20.09.16.
 */
public class Browser {

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
    private BrowserConfig browserConfig;

    public Browser(BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
        this.id = UUID.randomUUID() + "";
        initDriver();
    }

    private synchronized Browser initDriver() {

        switch (browserConfig.getBrowserName()) {
            case CHROME:
                //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(browserConfig.getJavascriptEnabled());
                capabilities.setPlatform(browserConfig.getPlatform());
                capabilities.setBrowserName(browserConfig.getBrowserName().toString());

                driver = new ChromeDriver(new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(browserConfig.getWebDriver()))
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

    public void get(String url) {
        driver.navigate().to(url);
    }

    public boolean isExpectedBrowser(BrowserName browserName) {
        String currentBrowserName = ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName();
        String expectedBrowser = browserName.toString();

        return (expectedBrowser.equalsIgnoreCase(currentBrowserName));
    }

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