package selenium.webconfigure;

import org.openqa.selenium.Platform;

import java.util.UUID;

/**
 * Created by alexander.
 * <p>
 * Configure browser
 */
public class ExecutionContext {

    private String webDriver;
    private Browser browser;
    private boolean javascriptEnabled;
    private Platform platform;
    private boolean isInitialized = false;
    private final String uuid = UUID.randomUUID() + "";

    public ExecutionContext() {
    }

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
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

    /**
     * Is context initialized
     *
     * @return is conext initialized
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Set context initialized
     *
     * @param isInitialized initialized or not
     */
    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    /**
     * Compare contexts
     *
     * @param o Object to compare
     * @return Is objects equals
     */
    public boolean equals(Object o) {
        if (o instanceof ExecutionContext) {
            return ((ExecutionContext) o).uuid.equals(this.uuid);
        } else {
            return super.equals(o);
        }
    }

}