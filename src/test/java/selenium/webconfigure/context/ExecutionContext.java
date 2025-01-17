package selenium.webconfigure.context;

import selenium.webconfigure.Browser;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * ExecutionContext
 */
public class ExecutionContext {

    private final String uuid = UUID.randomUUID() + "";
    private Browser browser;
    private boolean isInitialized = false;
    //private AbstractPage currentPage = null;
    private Method method = null;

    public ExecutionContext() {
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    /**
     * Is context initialized
     *
     * @return is conext initialized
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    /**
     * Set context initialized
     *
     * @param isInitialized initialized or not
     */
    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    /*public AbstractPage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(AbstractPage currentPage) {
        this.currentPage = currentPage;
    }*/

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
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

    /**
     * String info about context
     *
     * @return String info about context
     */
    public String toString() {
        return "UUID: " + uuid //+ ", current page: " + currentPage + ","
                + ", method: " + method;
    }

}