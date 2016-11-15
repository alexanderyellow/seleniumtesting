package selenium.webconfigure;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

/**
 * Created by alexander on 03.09.16.
 */
public class Browser1 {

    private final DesiredCapabilities capabilities;
    private RemoteWebDriver driver;

    public Browser1(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
        initDriver();
    }

    /*private void resetProperties() {
        properties.clear();
    }

    public void setProperty(String propertyName, Object propertyValue) {
        properties.put(propertyName, propertyValue);
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }*/

    public static Browser1 createNew(String browserName) {

        DesiredCapabilities capabilities;

        if (browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("iexplore")
                || browserName.equalsIgnoreCase("internet explore") ||
                browserName.equalsIgnoreCase("internet explorer")) {
            capabilities = DesiredCapabilities.internetExplorer();
        } else {
            if (browserName.equalsIgnoreCase("ff") || browserName.equalsIgnoreCase("firefox")) {
                capabilities = DesiredCapabilities.firefox();
            } else {
                if (browserName.equalsIgnoreCase("chrome")) {
                    capabilities = DesiredCapabilities.chrome();
                } else {
                    throw new RuntimeException("Unsupported browser. Browser1 type " + browserName + " is unknown");
                }
            }
        }

        return new Browser1(capabilities);
    }

    public Capabilities getRuntimeCapabilities() {
        return getDriver().getCapabilities();
    }

    public void refresh() {
        getDriver().navigate().refresh();
    }

    public void navigateTo(String to) {
        getDriver().navigate().to(to);
    }

    private synchronized void initDriver() {

        String browserName = capabilities.getBrowserName();

        try {

            if (browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("iexplore")
                    || browserName.equalsIgnoreCase("internet explore") || browserName.equalsIgnoreCase("internet explorer")) {
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                driver = new InternetExplorerDriver(new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(new File(Browser1.class.getClassLoader()
                                .getResource(System.getProperty("iedriver.x32")).toURI())).build(),
                        capabilities);
            } else {
                if (browserName.equalsIgnoreCase("ff") || browserName.equalsIgnoreCase("firefox")) {
                    driver = new FirefoxDriver(capabilities);
                } else {
                    if (browserName.equalsIgnoreCase("chrome")) {
                        driver = new ChromeDriver(
                                new ChromeDriverService.Builder()
                                        .usingAnyFreePort()
                                        .usingDriverExecutable(new File(Browser1.class.getClassLoader()
                                                .getResource(System.getProperty("chromedriver")).toURI()))
                                        .build(),
                                capabilities
                        );

                    } else {
                        if (browserName.equalsIgnoreCase("safari")) {
                            driver = new SafariDriver(capabilities);
                        } else {
                            throw new RuntimeException("Unsupported browser. Browser1 type " + browserName + " is unknown");
                        }

                    }
                }
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

    }

    public void clearCookies() {
        if (isIE()) {
            try {
                getDriver().manage().deleteAllCookies();
                quit();
                initDriver();
            } catch (Throwable t) {
                //LoggerConfiguration.get().debug("Failed to clean IE cookies: " + TestUtils.getThrowableFullDescription(t));
            }
        } else {
            getDriver().manage().deleteAllCookies();
        }
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void get(String url) {
        //    LoggerConfiguration.get().componentInfo("Navigating to " + url);
        getDriver().navigate().to(url);
    }

    public boolean isIE() {
        return getDriver().getCapabilities().getBrowserName().equalsIgnoreCase(
                DesiredCapabilities.internetExplorer().getBrowserName()
        );
    }

    public boolean isFF() {
        return getDriver().getCapabilities().getBrowserName().equalsIgnoreCase(
                DesiredCapabilities.firefox().getBrowserName()
        );
    }

    public boolean isChrome() {
        return getDriver().getCapabilities().getBrowserName().equalsIgnoreCase(
                DesiredCapabilities.chrome().getBrowserName()
        );
    }

    public void maximize() {
        getDriver().manage().window().maximize();
    }

    public void reopenIfDead() {
        try {
            getDriver().getTitle();
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

    /*public String getPageSource() {
        try {
            return getDriver().getPageSource();
        } catch (Throwable t) {
        //    return "Couldn't get page source:\n" + TestUtils.getThrowableFullDescription(t);
        }
    }*/

    public void quit() {
        try {
            getDriver().quit();
        } catch (Throwable ignore) {

        }

        //    resetProperties();
    }

    /*static {
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
    }*/

}
