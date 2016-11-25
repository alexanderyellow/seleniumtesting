package selenium.webconfigure.context;

/**
 * Environment
 */
public class Environment {

    /**
     * Current active instance of Environment
     */
    private static Environment _instance = null;
    private String appURL;
    private int elementTimeout;
    private int pageTimeout;
    private int elementTimeoutInterval;

    /**
     * Constructor
     */
    private Environment() {}

    /**
     * Get current active instance. #init(String) should be called before at least once
     *
     * @return Environment
     */
    public static Environment get() {
        if (_instance == null) {
            _instance = new Environment();
        }

        return _instance;
    }

    /**
     * App domain URL (without any additional paths)
     *
     * @return URL to APP
     */
    public String getAppDomainURL() {
        return appURL;
    }

    /**
     * Provides URL for portal
     *
     * @return portal URL or null
     */
    public String getAppURL() {
        String fullUrl = appURL;

        if (!fullUrl.endsWith("/")) {
            fullUrl += "/";
        }
        return fullUrl;
    }

    public void setAppURL(String appURL) {
        this.appURL = appURL;
    }

    /**
     * Provides element timeout
     *
     * @return Element timeout
     */
    public int getElementTimeout() {
        return elementTimeout;
    }

    public void setElementTimeout(int elementTimeout) {
        this.elementTimeout = elementTimeout;
    }

    /**
     * Provides element timeout  interval
     *
     * @return Element timeout
     */
    public int getElementTimeoutInterval() {
        return elementTimeoutInterval;
    }

    public void setElementTimeoutInterval(int elementTimeoutInterval) {
        this.elementTimeoutInterval = elementTimeoutInterval;
    }

    /**
     * Provides page timeout
     *
     * @return Page timeout
     */
    public int getPageTimeout() {
        return pageTimeout;
    }

    public void setPageTimeout(int pageTimeout) {
        this.pageTimeout = pageTimeout;
    }

}