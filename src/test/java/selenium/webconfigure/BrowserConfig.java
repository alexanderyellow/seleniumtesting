package selenium.webconfigure;

import org.openqa.selenium.Platform;
import selenium.webconfigure.Browser.BrowserName;

/**
 * Created by alexander.
 */
public class BrowserConfig {

    private String webDriver;
    private BrowserName browserName;
    private boolean javascriptEnabled;
    private Platform platform;

    public BrowserConfig(String webDriver, BrowserName browserName, boolean javascriptEnabled, Platform platform) {
        this.webDriver = webDriver;
        this.browserName = browserName;
        this.javascriptEnabled = javascriptEnabled;
        this.platform = platform;
    }

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }

    public BrowserName getBrowser() {
        return browserName;
    }

    public void setBrowser(BrowserName browserName) {
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

}