package selenium.common;

/**
 * Environment.
 */
public class Environment {

    private static int zeroTimeout;
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
    private Environment() {

        /*String sElementTimeout = System.getProperty("element.timeout");
        if (sElementTimeout == null || sElementTimeout.equals(""))
            elementTimeout = 10;
        else
            elementTimeout = Integer.valueOf(System.getProperty("element.timeout"));

        String sPageTimeout = System.getProperty("page.timeout");
        if (sPageTimeout == null || sPageTimeout.equals(""))
            pageTimeout = 10;
        else
            pageTimeout = Integer.valueOf(System.getProperty("page.timeout"));

        String sElementTimeoutInterval = System.getProperty("element.timeout.interval");
        if (sElementTimeoutInterval == null || sElementTimeoutInterval.equals(""))
            elementTimeoutInterval = 10;
        else
            elementTimeoutInterval = Integer.valueOf(System.getProperty("element.timeout.interval"));

        String sZeroTimeout = System.getProperty("timeout.zero");
        if (sZeroTimeout == null || sZeroTimeout.equals(""))
            zeroTimeout = 10;
        else
            zeroTimeout = Integer.valueOf(System.getProperty("timeout.zero"));

        appURL = System.getProperty("app.url");

        if (appURL == null || appURL.equals("")) {
            throw new RuntimeException("App URL is not defined. Should be provided with system property app.url");
        }*/
    }

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
     * Provides zero timeout
     *
     * @return Element timeout
     */
    public static int getZeroTimeout() {
        return zeroTimeout;
    }

    public static void setZeroTimeout(int zeroTimeout) {
        Environment.zeroTimeout = zeroTimeout;
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