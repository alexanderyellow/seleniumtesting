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

    /**
     * List of contexts
     */
    private static ExecutionContext executionContext;

    public static ExecutionContext createContext(Method method) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        BrowserConfig browserConfig = (BrowserConfig) context.getBean("browserConfig");

        executionContext = new ExecutionContext();
        executionContext.setBrowser(new Browser(browserConfig));
        executionContext.setMethod(method);

        return executionContext;
    }

}