package selenium.webconfigure.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import selenium.webconfigure.Browser;
import selenium.webconfigure.BrowserConfig;

import java.lang.reflect.Method;

/**
 * ExecutionContextManager
 */
public class ExecutionContextManager {

    private static ExecutionContextManager _instance;
    private ExecutionContext executionContext;

    private ExecutionContextManager() {
    }

    public ExecutionContext createContext(/*Method method*/) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        BrowserConfig browserConfig = (BrowserConfig) context.getBean("browserConfig");

        executionContext = new ExecutionContext();
        executionContext.setBrowser(new Browser(browserConfig));

        return executionContext;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public static ExecutionContextManager get() {
        if (_instance == null) {
            _instance = new ExecutionContextManager();
            return _instance;
        }

        return _instance;
    }

}