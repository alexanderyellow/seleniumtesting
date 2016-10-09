package selenium.webconfigure;

import org.openqa.selenium.Platform;

/**
 * Created by alexander on 08.10.16.
 * <p>
 * Configure browser
 */
public class BrowserConfig {

    private String webDriver;
    private BrowserName browserName;
    private boolean javascriptEnabled;
    private Platform platform;

    public BrowserConfig() {
    }

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }

    public BrowserName getBrowserName() {
        return browserName;
    }

    public void setBrowserName(BrowserName browserName) {
        this.browserName = browserName;
    }

    public boolean getJavascriptEnabled() {
        return javascriptEnabled;
    }

    public void setJavascriptEnabled(boolean javascriptEnabled) {
        this.javascriptEnabled = javascriptEnabled;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

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

}