package selenium.webconfigure.context;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import selenium.webconfigure.Browser;
import selenium.webconfigure.BrowserConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ExecutionContextManager
 */
public class ExecutionContextManager {

    private static ExecutionContext executionContext;

    /**
     * List of contexts
     */
    private final static List<ExecutionContext> contexts = new ArrayList<ExecutionContext>();

    public static void createContext(Method method) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        BrowserConfig browserConfig = (BrowserConfig) context.getBean("browserConfig");

        executionContext = new ExecutionContext();
        executionContext.setBrowser(new Browser(browserConfig));
        executionContext.setMethod(method);
        executionContext.setThread(Thread.currentThread());
    }

    public static boolean hasContext(Method method) {
        return getContextForMethod(method) != null;
    }

    /**
     * Check the test method whether has context
     * @param method test method
     * @return result
     */
    private static ExecutionContext getContextForMethod(Method method) {
        ExecutionContext context = null;
        synchronized (contexts) {
            for (ExecutionContext ctx : contexts) {
                if (ctx.getMethod() != null && ctx.getMethod().equals(method)) {
                    context = ctx;
                    break;
                }
            }
        }

        return context;
    }

    /**
     * Get context
     *
     * @return ExecutionContext
     */
    public static synchronized ExecutionContext getContext() {

        ExecutionContext context = null;

        synchronized (contexts) {
            for (ExecutionContext ctx : contexts) {
                if (ctx.getThread() != null && ctx.getThread().getId() == Thread.currentThread().getId()) {
                    context = ctx;
                    System.out.println("Resolved context: " + context);
                //    log(context, "Resolved context: " + context, false);
                    break;
                }
            }
            if (context == null) {
                System.out.println("Context for thread not found: "+ Thread.currentThread().getId() + "\nAll contexts:\n" +
                        StringUtils.join(contexts, "\n"));
                /*log(context, "Context for thread not found: " + Thread.currentThread().getId() + "\nAll contexts:\n" +
                        StringUtils.join(contexts, "\n"), true);*/
            }
        }


        return context;
    }

}
