package selenium.webconfigure.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import selenium.pages.AbstractPage;
import selenium.webconfigure.Browser;
import selenium.webconfigure.Browser.BrowserName;
import selenium.webconfigure.BrowserConfig;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by alexander.
 * <p>
 * Configure browser
 */
@Component
public class ExecutionContext {

    private Browser browser;
    private boolean isInitialized = false;
    private AbstractPage currentPage = null;
    private Thread thread = null;
    private Method method = null;
    private final String uuid = UUID.randomUUID() + "";

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

    /**
     * Set context initialized
     *
     * @param isInitialized initialized or not
     */
    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public AbstractPage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(AbstractPage currentPage) {
        this.currentPage = currentPage;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

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
        return "UUID: " + uuid + ", current page: " + currentPage + "," +
                " thread: " + (thread == null ? "null" : thread.getId() + ", method: " + method);
    }

}