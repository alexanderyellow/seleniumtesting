package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.ExecutionContext;
import selenium.webconfigure.context.ContextConfiguration;
import selenium.webconfigure.context.ExecutionContextManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aliaksandr_Sheikin on 11/17/2016.
 */
public class AbstractTest {

    /**
     * Test browser
     */
    public ThreadLocal<Browser> browser = new ThreadLocal<Browser>();

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {



        TestRunner tr = (TestRunner) iTestContext;
    //    tr.addListener(DefaultListener.getInstance());

        List<String> groups = new ArrayList<String>();



        /*TestDescription testDescription = new TestDescription()
                .setClassName(null)
                .setDescription(null)
                .setIsConfig(false)
                .setMethodName(null)
                .setName(null);

        logger.set(Logger.init(Environment.get().getBrowserName(),
                Environment.get().getPlatform(), Environment.get().getAppURL(), groups));
        logger.get().startNewTestSession(testDescription);

        Element.setElementTimeout(Environment.get().getElementTimeout());
        Element.setElementTimeoutInterval(Environment.get().getElementTimeoutInterval());
        Element.addAjaxLoadManager(new AjaxProcessingManager());*/
    }

    /**
     * Init logging of current test
     */
    @BeforeMethod
    public void beforeMethod(Method method) {
    //    initLogger(method);
        ExecutionContextManager.createContext(method);
        browser.set(ExecutionContextManager.getContext().getBrowser());
        browser.get().maximize();
    }

    /**
     * Finish logging of actual test
     */
    @AfterMethod
    public void afterMethod(Method method) {
        /*if (ExecutionContextManager.hasContext(method)) {
            ExecutionContextManager.releaseContext(ExecutionContextManager.getContext(method));
        }
        if (logger.get().hasTestSession()) {
            logger.get().endTestSession();
        }*/
    }

}
